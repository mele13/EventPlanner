<template>
  <LangLoadSpinner :modules="modules" />
  <form class="container d-flex flex-column align-items-center mb-2">
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
      <EventLocation v-if="currentStep === 2" :countries="countries" :venues="venues" />
      <EventFinancial v-if="currentStep === 3" />
      <EventLocation v-if="currentStep === 4" :countries="countries" :venues="venues" />
      <!-- faq -->
      <EventLocation v-if="currentStep === 5" :countries="countries" :venues="venues" />
      <!-- menu -->
      <EventLocation v-if="currentStep === 6" :countries="countries" :venues="venues" />
      <!-- confirm: event_contact  -->

      <!-- Submit event -->
      <div class="buttons">
        <button v-if="currentStep > 0" type="button" class="btn btn-evt" @click="prevStep">
          {{ $t('back') }}
        </button>
        <button
          v-if="currentStep < steps.length - 1"
          type="button"
          class="btn btn-evt"
          @click="nextStep"
        >
          {{ $t('next') }}
        </button>
        <button v-else type="submit" class="btn btn-evt mt-">{{ $t('submit') }}</button>
      </div>
    </template>
  </form>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import LangLoadSpinner from '@/components/LangLoadSpinner.vue'
import SectionLoadSpinner from '@/components/SectionLoadSpinner.vue'
import EventTypeSelector from '@/components/EventTypeSelector.vue'
import EventSteps from '@/components/EventCreationSteps.vue'
import EventDataForm from '@/components/forms/event-creation/EventData.vue'
import EventLocation from '@/components/forms/event-creation/EventLocation.vue'
import EventFinancial from '@/components/forms/event-creation/EventFinancial.vue'
import type { UserDto } from '@/interfaces/dtos/UserDto'
import UserService from '@/services/UserService'
import { loadUsers, loadCountries, loadVenues } from '@/utils/LoadData'

// Import icons
import ServiceLine from '~icons/ri/customer-service-2-line'
import EventData from '~icons/material-symbols/event-list-outline'
import Location from '~icons/ep/map-location'
import Money from '~icons/solar/hand-money-linear'
import Faq from '~icons/wpf/ask-question'
import Menu from '~icons/fluent/food-16-regular'
import Mail from '~icons/tabler/mail'
import type { CountryDto } from '@/interfaces/dtos/LocationDto'
import { CountryService } from '@/services/LocationService'
import type AddressDto from '@/interfaces/dtos/AddressDto'
import type { VenueDto } from '@/interfaces/dtos/VenueDto'
import { getAllVenues } from '@/services/VenueService'

export default defineComponent({
  components: {
    EventSteps,
    EventLocation,
    EventDataForm,
    EventFinancial,
    LangLoadSpinner,
    SectionLoadSpinner,
    EventTypeSelector
  },
  setup() {
    const currentStep = ref(0)
    const selectedEvent = ref<number | null>(null)
    const selectedEventId = ref<string | null>(null)
    const modules = ['event-creation', 'location', 'financial']
    const isLoading = ref(true)

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

    // Data
    const users = ref<UserDto[]>([]);
    const countries = ref<CountryDto[]>([]);
    const venues = ref<VenueDto[]>([]);
    const addresses = ref<AddressDto[]>([]);

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
      // Inherits
      users,
      countries,
      venues,
      addresses,
    }
  }
})
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
