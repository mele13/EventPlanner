<template>
  <div class="d-flex gap-4">
    <input
      v-for="(color, index) in colors"
      :key="index"
      type="color"
      class="color-selector"
      v-model="colors[index]"
      @input="updateColor(index)"
    />
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
  props: {
    initialColors: {
      type: Array as () => string[],
      required: true,
    },
  },
  setup(props, { emit }) {
    const colors = ref<string[]>(props.initialColors);

    const updateColor = (index: number) => {
      emit('update:colors', colors.value);
    };

    return {
      colors,
      updateColor,
    };
  },
});
</script>
