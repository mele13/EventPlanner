<template>
  <div
    :class="{ step: true, active: isActive, completed: isCompleted, inactive: !isActive && !isCompleted }"
    class="d-flex align-items-center pointer"
    @click="handleClickedStep"
  >
    <div v-if="isActive" class="step-icon-circle d-flex justify-content-center align-items-center">
      <component :is="icon" class="step-icon" />
    </div>
    <component v-else :is="icon" class="step-icon" />
    <div v-if="isActive" class="step-text ms-2">
      <p class="my-0 step-count">{{ $t('step') }} {{ index + 1 }} / {{ totalSteps }}</p>
      <p class="my-0 step-title">{{ $t(label) }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    label: String,
    icon: { type: [String, Object] },
    index: Number,
    currentStep: Number,
    totalSteps: Number,
  },
  computed: {
    isActive() {
      return this.index === this.currentStep;
    },
    isCompleted() {
      return this.index < this.currentStep;
    },
  },
  methods: {
    handleClickedStep() {
      this.$emit('step-clicked', this.index);
    },
  },
};
</script>

<style scoped>
.step.active .step-icon {
  color: white;
}

.step.active .step-icon-circle {
  padding: 5%;
  background-color: var(--color-tertiary);
}

.step.active {
  color: var(--color-tertiary);
}

.step.inactive .step-icon {
  color: var(--color-grey);
}

.step.completed {
  color: var(--color-tertiary);
}

.step-icon-circle {
  border-radius: 50%;
}

.step-icon {
  font-size: 160%;
}

.step-count {
  font-size: 80%;
}

.step-title {
  color: black;
  font-weight: 600;
}
</style>
