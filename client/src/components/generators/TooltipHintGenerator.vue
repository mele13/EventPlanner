<template>
  <div class="adm-tooltip">
    <span :class="borderColor" class="adm-tooltip-text p-2" :style="{ fontSize: fontSize, marginLeft: textPosition }" v-html="content"></span>
    <span :class="iconColor">
      <i class="fa fa-question-circle-o" aria-hidden="true" :style="{ fontSize: iconSize }"></i>
    </span>
  </div>
</template>

<script lang="ts">
export default {
  props: {
    content: {
      type: String,
      required: true
    },
    fontSize: {
      type: String,
      default: '16px'
    },
    iconSize: {
      type: String,
      default: '16px',
    },
    iconColor: {
      type: String,
      default: 'obl',
    },
    borderColor: {
      type: String,
      default: 'noresp-border',
    },
    textPosition: {
      type: String,
      default: '',
    },
  }
}
</script>

<style scoped>
.adm-tooltip {
  position: relative;
  display: inline-block;
  z-index: 999;
}

.adm-tooltip .adm-tooltip-text {
  visibility: hidden;
  width: max-content;
  max-width: 450px;
  background-color: var(--color-background);
  color: var(--color-black);
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;

  /* Position the adm-tooltip */
  position: absolute;
  z-index: 1;
  bottom: 100%;
  left: 50%;
}

.adm-tooltip:hover .adm-tooltip-text {
  visibility: visible;
}

.medium-icon {
  font-size: large;
}
</style>
