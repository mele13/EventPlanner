<template>
  <LangLoadSpinner :locale="locale" module="event-creation" />
  <form class="container d-flex flex-column align-items-center">
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
    <EventTypeSelector
      v-if="currentStep === 0"
      @eventSelected="handleEventSelected"
      :selectedEvent="selectedEvent"
    />
    <EventDataForm v-if="currentStep === 1" />
    <EventAddress v-if="currentStep === 2" />
    <EventAddress v-if="currentStep === 3" /> <!-- financial: ticket price, sell limit, sell date, refunds policy -->
    <EventAddress v-if="currentStep === 4" /> <!-- faq -->
    <EventAddress v-if="currentStep === 5" /> <!-- menu -->
    <EventAddress v-if="currentStep === 6" /> <!-- confirm: event_contact  -->

    <!-- Submit event -->
    <div class="buttons">
      <button v-if="currentStep > 0" type="button" class="btn btn-primary" @click="prevStep">{{ $t('back') }}</button>
      <button v-if="currentStep < steps.length - 1" type="button" class="btn btn-primary" @click="nextStep">{{ $t('next') }}</button>
      <button v-else type="submit" class="btn btn-primary">{{ $t('submit') }}</button>
    </div>
  </form>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import LangLoadSpinner from '@/components/LangLoadSpinner.vue';
import EventTypeSelector from '@/components/EventTypeSelector.vue';
import EventSteps from '@/components/EventCreationSteps.vue';
import EventDataForm from '@/components/forms/event-creation/EventCreationData.vue';
import EventAddress from '@/components/forms/event-creation/EventAddress.vue';

// Import icons
import ServiceLine from '~icons/ri/customer-service-2-line';
import EventData from '~icons/material-symbols/event-list-outline';
import Location from '~icons/ep/map-location';
import Money from '~icons/solar/hand-money-linear';
import Faq from '~icons/wpf/ask-question';
import Menu from '~icons/fluent/food-16-regular';
import Mail from '~icons/tabler/mail';

export default defineComponent({
  components: {
    EventSteps,
    EventAddress,
    EventDataForm,
    EventTypeSelector,
  },
  setup() {
    const currentStep = ref(0);
    const selectedEvent = ref<number | null>(null);
    const selectedEventId = ref<string | null>(null);
    const locale = ref('en');

    const steps = [
      { label: 'event_type', component: ServiceLine },
      { label: 'event_data', component: EventData },
      { label: 'event_location', component: Location },
      { label: 'event_money', component: Money },
      { label: 'event_faq', component: Faq },
      { label: 'event_menu', component: Menu },
      { label: 'event_confirm', component: Mail },
    ]

    // Step control functions
    const nextStep = () => {
      if (currentStep.value < steps.length - 1) currentStep.value++;
    }
    const prevStep = () => {
      if (currentStep.value > 0) currentStep.value--;
    }
    const setCurrentStep = (index: number) => {
      currentStep.value = index;
    }

    // Component events handlers
    const handleEventSelected = (index: number, id: string) => {
      selectedEvent.value = index;
      selectedEventId.value = id;
      console.log('Selected: ', selectedEvent.value, selectedEventId.value);
    }

    return {
      selectedEvent,
      currentStep,
      steps,
      locale,
      nextStep,
      prevStep,
      setCurrentStep,
      handleEventSelected,
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
