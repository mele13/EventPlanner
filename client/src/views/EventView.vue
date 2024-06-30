<template>
  <LangLoadSpinner :modules="modules" />
  <SectionLoadSpinner v-if="isLoading" />
  <template v-else>
    <component :is="templateComponent" v-bind="eventData"></component>
  </template>
</template>

<script lang="ts">
import { defineComponent, computed, ref, onMounted } from 'vue';
import EventTemplate1 from '@/components/templates/EventTemplate1.vue';
import EventTemplate2 from '@/components/templates/EventTemplate1.vue';
import EventTemplate3 from '@/components/templates/EventTemplate1.vue';
import EventTemplate4 from '@/components/templates/EventTemplate1.vue';
import EventTemplate5 from '@/components/templates/EventTemplate1.vue';
import LangLoadSpinner from '@/components/LangLoadSpinner.vue';
import SectionLoadSpinner from '@/components/SectionLoadSpinner.vue';
import { EventService } from '@/services/EventService';
import type { EventDto } from '@/interfaces/dtos/EventDto';
import { loadEvents } from '@/utils/LoadData';
import { useRoute } from 'vue-router';
import { getNumberFromString } from '@/utils/StringModifier';

export default defineComponent({
  components: {
    SectionLoadSpinner,
    LangLoadSpinner,
    EventTemplate1,
    EventTemplate2,
    EventTemplate3,
    EventTemplate4,
    EventTemplate5,
  },
  setup() {
    const modules = ['event-creation', 'location', 'financial'];
    var eventData = ref<EventDto | null>(null);
    const isLoading = ref(true);
    const route = useRoute();
    const { params } = route;

    const loadEventData = async () => {
      const eventId = getNumberFromString(params.eventId);
      try {
        const event = await EventService.getEventById(eventId);
        eventData.value = event;
      } catch (e) {
        const events = loadEvents();
        eventData.value = events.find((event) => event.id === eventId);
      } finally {
        isLoading.value = false;
      }

      console.log("data--------", eventData, eventData.value.template)
    };

    // Load event data when component is mounted
    onMounted(() => {
      loadEventData();
    });

    const templateComponent = computed(() => {
      switch (eventData.value.template) {
        case 'EventTemplate1':
          console.log("caca")
          return EventTemplate1;
        case 'EventTemplate2':
          return EventTemplate2;
        case 'EventTemplate3':
          return EventTemplate3;
        case 'EventTemplate4':
          console.log("puta")
          return EventTemplate4;
        case 'EventTemplate5':
          return EventTemplate5;
        default:
          return null;
      }
    });

    return {
      templateComponent,
      isLoading,
      eventData,
      modules,
    };
  }
});
</script>

