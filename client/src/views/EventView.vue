<template>
  <LangLoadSpinner :modules="modules" />
  <SectionLoadSpinner v-if="isLoading" />
  <template v-else>
    <component :is="templateComponent" v-bind="eventData" :eventDetails="eventData.name"></component>
    <EditStickyIcon v-if="isOwnedEvent" @click="openEditPopup" />
    <PopupGenerator :isOpen="isPopupOpen" @close="closeEditPopup">
        <template #title>
          <h3>Edit Event</h3>
        </template>
        <template #form>
          <form @submit.prevent="saveEvent">
            <div>
              <label for="eventName">Event Name</label>
              <input type="text" id="eventName" v-model="eventData.name" required />
            </div>
            <div>
              <label for="eventDescription">Event Description</label>
              <textarea id="eventDescription" v-model="eventData.description" required></textarea>
            </div>
            <div>
              <label for="eventStartDate">Start Date</label>
              <input type="datetime-local" id="eventStartDate" v-model="eventData.startDate" required />
            </div>
            <div>
              <label for="eventEndDate">End Date</label>
              <input type="datetime-local" id="eventEndDate" v-model="eventData.endDate" required />
            </div>
            <div>
              <label for="attendanceType">Attendance Type</label>
              <select id="attendanceType" v-model="eventData.attendanceType">
                <option value="PHYSICAL">Physical</option>
                <option value="VIRTUAL">Virtual</option>
              </select>
            </div>
            <div>
              <label for="limitNumber">Limit Number</label>
              <input type="number" id="limitNumber" v-model="eventData.limitNumber" />
            </div>
            <div>
              <label for="notification">Notification</label>
              <input type="checkbox" id="notification" v-model="eventData.notification" />
            </div>
            <div>
              <label for="ticketPrice">Ticket Price</label>
              <input type="number" id="ticketPrice" v-model="eventData.ticketPrice" />
            </div>
            <div>
              <label for="ticketCurrency">Ticket Currency</label>
              <select id="ticketCurrency" v-model="eventData.ticketCurrency">
                <option value="EUR">EUR</option>
                <option value="USD">USD</option>
              </select>
            </div>
            <div>
              <label for="contact">Contact</label>
              <input type="email" id="contact" v-model="eventData.contact" required />
            </div>
            <div>
              <label for="palette">Palette</label>
              <input type="text" id="palette" v-model="eventData.palette" />
            </div>
            <div>
              <label for="template">Template</label>
              <select id="template" v-model="eventData.template">
                <option value="EventTemplate1">Template 1</option>
                <option value="EventTemplate2">Template 2</option>
                <option value="EventTemplate3">Template 3</option>
                <option value="EventTemplate4">Template 4</option>
                <option value="EventTemplate5">Template 5</option>
              </select>
            </div>
            <button type="submit">{{ $t('save') }}</button>
          </form>
        </template>
      </PopupGenerator>
  </template>
</template>

<script lang="ts">
import { defineComponent, computed, ref, onMounted } from 'vue';
import EventTemplate1 from '@/components/templates/EventTemplate1.vue';
import EventTemplate2 from '@/components/templates/EventTemplate2.vue';
import EventTemplate3 from '@/components/templates/EventTemplate3.vue';
import EventTemplate4 from '@/components/templates/EventTemplate4.vue';
import EventTemplate5 from '@/components/templates/EventTemplate5.vue';
import EditStickyIcon from '@/components/EditStickyIcon.vue';
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
    EditStickyIcon,
  },
  setup() {
    const modules = ['event-creation', 'location', 'financial'];
    var eventData = ref<EventDto | null>(null);
    const isLoading = ref(true);
    const route = useRoute();
    const { params, query } = route;
    const isPopupOpen = ref(false);

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
    };

    const openEditPopup = () => {
      isPopupOpen.value = true;
    };

    const closeEditPopup = () => {
      isPopupOpen.value = false;
    };

    const saveEvent = async () => {
      if (eventData.value) {
        await EventService.updateEvent(eventData.value);
        closeEditPopup();
      }
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

    const isOwnedEvent = computed(() => query.owned === 'true');

    return {
      templateComponent,
      isLoading,
      eventData,
      modules,
      isOwnedEvent,
      isPopupOpen,
      openEditPopup,
      closeEditPopup,
      saveEvent,
    };
  }
});
</script>

