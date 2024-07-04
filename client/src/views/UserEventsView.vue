<template>
  <LangLoadSpinner :modules="modules" />
  <div class="user-events mx-5 mt-4 mhvh">
    <h2>{{ $t('my_events') }}</h2>

    <SectionLoadSpinner v-if="isLoading" />

    <!-- Owned -->
    <div v-if="ownedEvents.length" class="mt-3">
      <h4>{{ $t('owned_events') }}</h4>
      
      <div class="d-flex flex-wrap gap-3">
        <div class="border rounded d-inline-block p-3 pointer" v-for="event in ownedEvents" :key="event.eventId.toString()" @click="openEventLink(event)">
          <h6><component :is="getIcon(event.eventType)" class="tt-icon" /> {{ event.eventName }}</h6>
          <p class="mb-0">{{ event.eventDescription }}</p>
        </div>
      </div>
    </div>

    <!-- Participated in -->
    <div v-if="participatedEvents.length" class="mt-3">
      <h4>{{ $t('participated_events') }}</h4>
      
      <div class="d-flex flex-wrap gap-3">
        <div class="border rounded d-inline-block p-3 pointer" v-for="event in participatedEvents" :key="event.eventId.toString()" @click="openEventLink(event)">
          <h6><component :is="getIcon(event.eventType)" class="tt-icon" /> {{ event.eventName }}</h6>
          <p class="mb-0">{{ event.eventDescription }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from "vue";
import { loadUserEvents } from "@/utils/LoadData";
import type { UsersEventsDto } from "@/interfaces/dtos/UsersEventsDto";
import { UserEventRelationship } from "@/interfaces/enums/UserEventRelationship";
import UserService from "@/services/UserService";
import LangLoadSpinner from '@/components/LangLoadSpinner.vue';
import SectionLoadSpinner from '@/components/SectionLoadSpinner.vue';
import { EventType } from "@/interfaces/enums/EventType";

// Import icons
import Ring from '~icons/twemoji/ring';
import PartyPopper from '~icons/twemoji/party-popper';
import BirthdayCake from '~icons/twemoji/birthday-cake';
import Pray from '~icons/fxemoji/pray';
import Headstone from '~icons/twemoji/headstone';
import Briefcase from '~icons/noto-v1/briefcase';
import MusicalNotes from '~icons/noto-v1/musical-notes';
import Stadium from '~icons/noto/stadium';
import Videogame from '~icons/fxemoji/videogame';
import Expo from '~icons/solar/display-bold-duotone';
import Festival from '~icons/twemoji/circus-tent';
import Arts from '~icons/noto-v1/performing-arts';
import Community from '~icons/pepicons-print/people';
import Airplane from '~icons/emojione/airplane';
import Politics from '~icons/map/political';
import GraduationCap from '~icons/game-icons/graduate-cap';
import Fist from '~icons/game-icons/fist';
import Warning from '~icons/emojione-v1/warning';
import More from '~icons/icon-park-solid/more-two';
import { getEventName } from "@/utils/Tools";

export default defineComponent({
  components: {
    LangLoadSpinner,
    SectionLoadSpinner,
  },
  setup() {
    const modules = ['home', 'user-events'];
    const ownedEvents = ref<UsersEventsDto[]>([]);
    const participatedEvents = ref<UsersEventsDto[]>([]);
    const events = ref<UsersEventsDto[]>([]);
    const isLoading = ref(true);

    const loadEvents = async () => {
      try {
        events.value = await UserService.getUserEvents();
      } catch (e) {
        events.value = loadUserEvents();
      } finally {
        isLoading.value = false;
      }

      ownedEvents.value = events.value.filter((event: UsersEventsDto) => event.relationship === UserEventRelationship.MANAGES);
      participatedEvents.value = events.value.filter((event: UsersEventsDto) => event.relationship === UserEventRelationship.PARTICIPATES);
    };

    onMounted(() => {
      loadEvents();
    });

    const getIcon = (eventType: EventType) => {
      switch (eventType) {
        case EventType.ACADEMIC:
          return GraduationCap;
        case EventType.ART:
          return Arts;
        case EventType.BIRTHDAY:
          return BirthdayCake;
        case EventType.BUSINESS:
          return Briefcase;
        case EventType.COMMUNITY:
          return Community;
        case EventType.EXPO:
          return Expo;
        case EventType.FESTIVAL:
          return Festival;
        case EventType.FUNERAL:
          return Headstone;
        case EventType.GAMING:
          return Videogame;
        case EventType.HOLIDAY:
          return Airplane;
        case EventType.MUSICAL:
          return MusicalNotes;
        case EventType.PARTY:
          return PartyPopper;
        case EventType.POLITICAL:
          return Politics;
        case EventType.PROTEST:
          return Fist;
        case EventType.RELIGIOUS:
          return Pray;
        case EventType.SPORTS:
          return Stadium;
        case EventType.WARNING:
          return Warning;
        case EventType.WEDDING:
          return Ring;
        case EventType.OTHER:
          return More;
      }
    };

    const openEventLink = (event: UsersEventsDto) => {
      const eventName = getEventName(event.eventName.toString());
      const isOwned = event.relationship === UserEventRelationship.MANAGES;
      window.open(`/event/${event.eventType}_${event.eventId}/${eventName}?owned=${isOwned}`, '_blank');
    };

    return {
      ownedEvents,
      getIcon,
      openEventLink,
      participatedEvents,
      isLoading,
      modules,
    };
  },
});
</script>

<style scoped>
</style>
