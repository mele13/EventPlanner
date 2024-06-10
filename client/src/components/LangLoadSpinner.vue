<template>
  <div v-if="isLoading" class="d-flex justify-content-center align-items-center w-100 spinner-container position-fixed">
    <div class="spinner-border obl">
      <span class="sr-only">{{ $t('loading') }}</span>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { loadLangConstants } from '@/plugins/i18n';

export default defineComponent({
  props: {
    locale: {
      type: String,
      required: true,
    },
    module: {
      type: String,
      required: true,
    },
  },
  setup(props, { emit }) {
    const isLoading = ref(true);

    const loadMessages = async () => {
      await loadLangConstants(props.locale, props.module);
      isLoading.value = false;
      emit('loaded');
    };

    loadMessages();

    return {
      isLoading,
    };
  },
});
</script>

<style scoped>
.spinner-container {
  height: 100vh;
  background-color: var(--color-background);
  z-index: 99999;
}
</style>
