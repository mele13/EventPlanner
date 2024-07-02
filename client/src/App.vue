<script setup lang="ts">
import { RouterView, useRoute } from 'vue-router';
import { ref } from 'vue';
import { watch } from 'vue';

import NavBar from './components/NavBar.vue';
import ContactFooterView from '@/views/ContactFooterView.vue';
import { getRoute } from './utils/Tools';

const showNavBar = ref(true);
const route = useRoute();

watch(
  () => route.path,
  (newPath) => {
    showNavBar.value = getRoute(newPath);
  }
);
</script>

<template>
  <header v-if="showNavBar">
    <NavBar />
  </header>
  <RouterView />
  <section id="contact">
    <ContactFooterView />
  </section>
</template>

<style scoped></style>
