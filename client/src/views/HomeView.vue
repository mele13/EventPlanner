<template>
  <LangLoadSpinner :modules="modules" />
  <main class="mhvh">
    <section id="cover">
      <HomeCover />
    </section>
  
    <section id="about">
      <AboutView />
    </section>

    <section id="search">
      <EventSearch />
    </section>
  </main>
</template>

<script lang="ts">
import AboutView from '@/views/AboutView.vue';
import LangLoadSpinner from '@/components/LangLoadSpinner.vue';
import EventSearch from '@/components/EventSearch.vue';
import HomeCover from '@/components/HomeCover.vue';
import { defineComponent } from 'vue';

export default defineComponent({
  components: {
    AboutView,
    HomeCover,
    EventSearch,
    LangLoadSpinner,
  },
  setup() {
    const modules = ['home'];
    return {
      modules,
    };
  },
})
</script>


