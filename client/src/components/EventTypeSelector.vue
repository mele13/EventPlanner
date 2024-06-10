<template>
  <div class="container">
    <div class="row">
      <div
        v-for="(icon, index) in icons"
        :key="index"
        class="col-6 col-md-4 col-lg-2"
      >
        <div
          class="icon-container d-flex flex-column align-items-center justify-content-center br-10 p-2"
          :class="{ selected: selectedIcon === index }"
          @click="selectIcon(index, icon.id)"
        >
          <component :id="icon.id" :is="icon.component" class="event-type" />
          <p class="mb-0">{{ icon.text }}</p>
          <span v-if="icon.extraContent" class="extra-content d-block text-center mb-0 noresp">
            {{ icon.extraContent }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, watch } from 'vue'

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

export default defineComponent({
  emits: ['eventSelected'],
  props: {
    selectedEvent: {
      type: Number,
      default: null,
    },
  },
  setup(props, { emit }) {
    const selectedIcon = ref<number | null>(props.selectedEvent)

    const selectIcon = (index: number, id: string) => {
      selectedIcon.value = index;
      emit('eventSelected', index, id);
    }

    watch(props, (newProps) => {
      selectedIcon.value = newProps.selectedEvent;
    });

    const icons = [
      { id: 'birthday', component: BirthdayCake, text: 'Birthday' },
      { id: 'party', component: PartyPopper, text: 'Party | Celebration' },
      { id: 'musical', component: MusicalNotes, text: 'Musical | Concert' },
      { id: 'arts', component: Arts, text: 'Arts', extraContent: 'Movies, fashion, plays, creative events' },
      { id: 'festival', component: Festival, text: 'Festival' },
      { id: 'academic', component: GraduationCap, text: 'Academic' },
      { id: 'religious', component: Pray, text: 'Religious' },
      { id: 'holidays', component: Airplane, text: 'Holidays | Travel' },
      { id: 'wedding', component: Ring, text: 'Wedding' },
      { id: 'business', component: Briefcase, text: 'Business' },
      { id: 'expo', component: Expo, text: 'Expo' },
      { id: 'sports', component: Stadium, text: 'Sports' },
      { id: 'gaming', component: Videogame, text: 'Gaming' },
      { id: 'community', component: Community, text: 'Community', extraContent: 'Conferences, tours, seasonal events' },
      { id: 'political', component: Politics, text: 'Political' },
      { id: 'protest', component: Fist, text: 'Protest', extraContent: 'Demonstrations, marches, protests' },
      { id: 'funeral', component: Headstone, text: 'Funeral' },
      { id: 'warning', component: Warning, text: 'Warning-based', extraContent: 'Disasters, lockdowns, severe weather' },
      { id: 'other', component: More, text: 'Other' },
    ];

    return {
      selectedIcon,
      selectIcon,
      icons
    }
  }
})
</script>

<style scoped>
.event-type {
  font-size: 200%;
}

.icon-container {
  padding: 2%;
  border: 2px solid transparent;
  transition: border-color 0.3s;
  margin: 2%;
  min-height: 150px;
}

.icon-container:hover {
  border-color: var(--color-tertiary);
}

.icon-container.selected {
  border-color: var(--color-tertiary);
}

.icon-container p {
  margin-top: 2%;
  text-align: center;
  font-size: 14px;
}

.extra-content {
  font-size: 75%;
  height: 0px;
}

/* Icon colours */
#protest {
  color: #A856AE;
}
</style>
