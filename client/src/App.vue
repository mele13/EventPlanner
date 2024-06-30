<script setup lang="ts">
import { RouterView, useRoute } from 'vue-router';
import { ref } from 'vue';
import { watch } from 'vue';

import NavBar from './components/NavBar.vue';
import { validateRoute } from './utils/Validators';

const showNavBar = ref(true);
const route = useRoute();

watch(
  () => route.path,
  (newPath) => {
    showNavBar.value = validateRoute(newPath);
  }
);
</script>

<template>
  <header v-if="showNavBar">
    <NavBar />
  </header>
  <RouterView />
</template>

<style scoped></style>
