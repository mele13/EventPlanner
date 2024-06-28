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
import type { PropType } from 'vue';

export default defineComponent({
  props: {
    modules: {
      type: [String, Array] as PropType<string | string[]>,
      required: true,
    },
  },
  setup(props, { emit }) {
    const isLoading = ref(true);

    const loadMessages = async () => {
      await loadLangConstants(props.modules);
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
