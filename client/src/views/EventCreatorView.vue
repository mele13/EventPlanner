<template>
  <LangLoadSpinner :modules="modules" />
  <form class="container d-flex flex-column align-items-center mb-2 mhvh">
    <!-- Steps progress bar -->
    <div class="w-75">
      <div class="step-container row br-10 w-100 noresp05-border ml-0i">
        <EventSteps
          v-for="(step, index) in steps"
          :key="index"
          :label="step.label"
          :icon="step.component"
          :index="index"
          :current-step="currentStep"
          :total-steps="steps.length"
          :class="{ 'col-sm': true, 'active-step': currentStep === index }"
          @step-clicked="setCurrentStep"
        />
      </div>
    </div>

    <!-- Event form data -->
    <SectionLoadSpinner v-if="isLoading" />
    <template v-else>
      <EventTypeSelector
        v-if="currentStep === 0"
        @eventSelected="handleEventSelected"
        :selectedEvent="selectedEvent"
      />
      <EventDataForm v-if="currentStep === 1" :users="users" />
      <EventLocation v-if="currentStep === 2" :countries="countries" :venues="venues" :addresses="addresses" :cities="cities" />
      <EventFinancial v-if="currentStep === 3" />
      <EventFaq v-if="currentStep === 4" />
      <EventMenu v-if="currentStep === 5" />
      <EventTemplate v-if="currentStep === 6" :countries="countries" :venues="venues" :addresses="addresses" :cities="cities" />

      <!-- Submit event -->
      <div class="buttons mb-4">
        <button v-if="currentStep > 0" type="button" class="btn btn-evt mx-2" @click.prevent="prevStep">
          {{ $t('back') }}
        </button>
        <button
          v-if="currentStep < steps.length - 1"
          type="button"
          class="btn btn-evt mx-2"
          @click.prevent="nextStep"
        >
          {{ $t('next') }}
        </button>
        <button v-else type="submit" class="btn btn-evt mx-2" @click.prevent="handleSubmit">{{ $t('submit') }}</button>
      </div>
    </template>
  </form>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import LangLoadSpinner from '@/components/LangLoadSpinner.vue';
import SectionLoadSpinner from '@/components/SectionLoadSpinner.vue';
import EventTypeSelector from '@/components/EventTypeSelector.vue';
import EventSteps from '@/components/EventCreationSteps.vue';
import EventDataForm from '@/components/forms/event-creation/EventData.vue';
import EventLocation from '@/components/forms/event-creation/EventLocation.vue';
import EventFinancial from '@/components/forms/event-creation/EventFinancial.vue';
import EventFaq from '@/components/forms/event-creation/EventFaq.vue';
import EventMenu from '@/components/forms/event-creation/EventMenu.vue';
import EventTemplate from '@/components/forms/event-creation/EventTemplate.vue';
import type { UserDto } from '@/interfaces/dtos/UserDto';
import UserService from '@/services/UserService';
import { loadUsers, loadCountries, loadVenues, loadAddress, loadCities, loadEvent } from '@/utils/LoadData'
import { CountryService } from '@/services/LocationService'
import type AddressDto from '@/interfaces/dtos/AddressDto'
import type { VenueDto } from '@/interfaces/dtos/VenueDto'
import { getAllVenues } from '@/services/VenueService'

// Import icons
import ServiceLine from '~icons/ri/customer-service-2-line'
import EventData from '~icons/material-symbols/event-list-outline'
import Location from '~icons/ep/map-location'
import Money from '~icons/solar/hand-money-linear'
import Faq from '~icons/wpf/ask-question'
import Menu from '~icons/fluent/food-16-regular'
import Mail from '~icons/tabler/mail'
import { CityDto, type CountryDto } from '@/interfaces/dtos/LocationDto'
import { EventService } from '@/services/EventService';
import type { EventDto } from '@/interfaces/dtos/EventDto';
import type { Lang } from '@/interfaces/enums/Lang';
import type { EventType } from '@/interfaces/enums/EventType';
import type { AttendanceType } from '@/interfaces/enums/AttendanceType';
import type { Currency } from '@/interfaces/enums/Currency';
import type { EventState } from '@/interfaces/enums/EventState';
import { useRouter } from 'vue-router';
import { getEventName } from '@/utils/Tools';

export default defineComponent({
  components: {
    EventSteps,
    EventLocation,
    EventDataForm,
    EventFinancial,
    EventFaq,
    EventMenu,
    EventTemplate,
    LangLoadSpinner,
    SectionLoadSpinner,
    EventTypeSelector
  },
  setup() {
    const router = useRouter();

    const currentStep = ref(0);
    const selectedEvent = ref<number | null>(null);
    const selectedEventId = ref<string | null>(null);
    const modules = ['home', 'event-creation', 'location', 'financial'];
    const isLoading = ref(true);

    const eventData = ref({
      name: '',
      description: '',
      lang: null,
      startDate: null,
      endDate: null,
      createdBy: null,
      ownedBy: null,
      attendanceType: null,
      limitNumber: null,
      editLimit: null,
      userEditLimit: null,
      cancellationLimit: null,
      notification: false,
      contact: '',
      live: false,
      sellLimit: null,
      cancellation: null,
      state: null,
      refundsPolicy: ''
    });

    const eventLocation = ref({
      venueId: null,
      addressId: null,
    });

    const eventFinancial = ref({
      ticketPrice: null,
      ticketCurrency: null,
    });

    const eventFaq = ref({
      faqIds: []
    });

    const eventMenu = ref({
      menuIds: []
    });

    const eventTemplate = ref({
      palette: [],
      template: ''
    });

    // Event creation steps
    const steps = [
      { label: 'event_type', component: ServiceLine },
      { label: 'event_data', component: EventData },
      { label: 'event_location', component: Location },
      { label: 'event_money', component: Money },
      { label: 'event_faq', component: Faq },
      { label: 'event_menu', component: Menu },
      { label: 'event_confirm', component: Mail }
    ]

    // Step control functions
    const nextStep = () => {
      if (currentStep.value < steps.length - 1) currentStep.value++
    }
    const prevStep = () => {
      if (currentStep.value > 0) currentStep.value--
    }
    const setCurrentStep = (index: number) => {
      currentStep.value = index
    }

    // Component events handlers
    const handleEventSelected = (index: number, id: string) => {
      selectedEvent.value = index
      selectedEventId.value = id
    }

    // Update event data
    const updateEventData = (data: any) => {
      eventData.value = data;
    };
    const updateEventLocation = (data: any) => {
      eventLocation.value = data;
    };
    const updateEventFinancial = (data: any) => {
      eventFinancial.value = data;
    };
    const updateEventFaq = (data: any) => {
      eventFaq.value = data;
    };
    const updateEventMenu = (data: any) => {
      eventMenu.value = data;
    };
    const updateEventTemplate = (palette: any, template: any) => {
      eventTemplate.value.palette = palette;
      eventTemplate.value.template = template;
    };

    // Handle form submit
    const handleSubmit = async () => {
      const event: EventDto = {
        eventType: selectedEventId.value as EventType,
        createdBy: eventData.value.createdBy,
        ownedBy: eventData.value.ownedBy,
        name: eventData.value.name,
        description: eventData.value.description,
        lang: eventData.value.lang as Lang,
        startDate: eventData.value.startDate,
        endDate: eventData.value.endDate,
        attendanceType: eventData.value.attendanceType as AttendanceType,
        limitNumber: eventData.value.limitNumber,
        editLimit: eventData.value.editLimit,
        userEditLimit: eventData.value.userEditLimit,
        cancellationLimit: eventData.value.cancellationLimit,
        credentialsId: undefined,
        notification: eventData.value.notification,
        tagIds: [],
        venueId: eventLocation.value.venueId,
        addressId: eventLocation.value.addressId,
        contact: eventData.value.contact,
        live: eventData.value.live,
        ticketPrice: eventFinancial.value.ticketPrice,
        ticketCurrency: eventFinancial.value.ticketCurrency as Currency,
        sellLimit: eventData.value.sellLimit,
        otherDatesIds: [],
        discountCodesIds: [],
        cancellation: eventData.value.cancellation,
        state: eventData.value.state as EventState,
        refundsPolicy: eventData.value.refundsPolicy,
        faqIds: eventFaq.value.faqIds,
        menuIds: eventMenu.value.menuIds,
        eventUserIds: [],
        palette: eventTemplate.value.palette,
        template: eventTemplate.value.template,
      };
      try {
        const createdEvent = await EventService.createEvent(event);
        const { eventType, id, name } = createdEvent;
        router.push('/my_events');
        window.open(`/event/${eventType}_${id}/${getEventName(name)}?owned=true`, '_blank');
      } catch (e) {
        const event = loadEvent();
        router.push('/my_events');
        window.open(`/event/${event.eventType}_${event.id}/${getEventName(event.name)}?owned=true`, '_blank');
      }
    };

    // Data
    const users = ref<UserDto[]>([]);
    const countries = ref<CountryDto[]>([]);
    const venues = ref<VenueDto[]>([]);
    const addresses = ref<AddressDto[]>([]);
    const cities = ref<CityDto[]>([]);

    // Fetch users when the component is mounted
    onMounted(async () => {
      try {
        users.value = await UserService.getUsers();
        countries.value = await CountryService.getAllCountries();
        venues.value = await getAllVenues();
      } catch (e) {
        users.value = loadUsers();
        countries.value = loadCountries();
        venues.value = loadVenues();
        addresses.value = loadAddress();
        cities.value = loadCities();
      } finally {
        isLoading.value = false;
      }
    })

    return {
      selectedEvent,
      currentStep,
      isLoading,
      steps,
      modules,
      nextStep,
      prevStep,
      setCurrentStep,
      handleEventSelected,
      handleSubmit,
      updateEventData,
      updateEventLocation,
      updateEventFinancial,
      updateEventFaq,
      updateEventMenu,
      updateEventTemplate,
      // Inherits
      users,
      countries,
      venues,
      addresses,
      cities,
    }
  }
});
</script>

<style scoped>
.step-container {
  padding: 1.5%;
  width: 50%;
}

.active-step {
  flex-grow: 2;
}
</style>
