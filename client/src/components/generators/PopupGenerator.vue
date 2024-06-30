<template>
  <div v-if="isOpen" class="popup-overlay position-fixed top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center" @click="closePopup">
    <div class="popup-content position-relative br-10" :style="{ width: contentWidth }" @click.stop>
      <button class="close-btn pointer border-0 bg-transparent position-absolute" @click="closePopup">&times;</button>
      <slot name="title"></slot>
      <slot name="form"></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    isOpen: {
      type: Boolean,
      required: true
    },
    contentWidth: {
      type: String,
      default: '40%'
    },
  },
  methods: {
    closePopup() {
      this.$emit('close');
    },
    handleEsc(e) {
      if (e.key === 'Escape')  this.closePopup();
    },
  },
  mounted() {
    document.addEventListener('keydown', this.handleEsc);
  },
  beforeUnmount() {
    document.removeEventListener('keydown', this.handleEsc);
  },
};
</script>

<style scoped>
.popup-overlay {
  background: rgba(0, 0, 0, 0.5);
  z-index: 1000;
}

.popup-content {
  background: white;
  padding: 1%;
  max-height: 90vh;
  overflow-y: auto;
}

.close-btn {
  top: 1%;
  right: 1%;
  font-size: 150%;
}
</style>
