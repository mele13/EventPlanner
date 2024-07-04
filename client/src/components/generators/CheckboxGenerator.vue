<template>
  <div class="form-check py-1">
    <input 
      type="checkbox" 
      class="form-check-input" 
      :id="id" 
      :checked="value" 
      @change="handleChange($event)"
    />
    <label :class="labelClass" :for="id">
      {{ labelContent }}
      <TooltipHint v-if="loadTooltip"
        :content="tooltipContent" 
        :iconColor="tooltipIconColor" 
        :iconSize="tooltipIconSize" 
        :borderColor="tooltipBorderColor" 
        :textPosition="tooltipTextPosition" 
        :sup="sup" 
      />
    </label>
    <small class="form-text text-muted" v-if="small">
      <br>
      {{ small }}
    </small>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import TooltipHint from '@/components/generators/TooltipHintGenerator.vue';

export default defineComponent({
  components: {
    TooltipHint,
  },
  props: {
    // Checkbox
    id: {
      type: String,
      required: true
    },
    value: {
      type: Boolean,
      default: false
    },
    // Label
    labelContent: {
      type: String,
      required: true
    },
    // Required (*)
    isRequired: {
      type: Boolean,
      default: false
    },
    // Tooltip
    loadTooltip: {
      type: Boolean,
      default: false
    },
    tooltipContent: {
      type: String,
      default: ''
    },
    tooltipIconColor: {
      type: String,
    },
    tooltipIconSize: {
      type: String,
      default: '80%'
    },
    tooltipBorderColor: {
      type: String,
    },
    tooltipTextPosition: {
      type: String,
      default: '10px'
    },
    sup: {
      type: Boolean,
      default: false
    },
    small: {
      type: String,
      default: ''
    },
  },
  setup(props, { emit }) {
    const handleChange = (event) => {
      emit('input', event.target.checked);
    }

    return {
      handleChange,
    };
  },
  computed: {
    labelClass() {
      return this.isRequired ? 'form-check-label required' : 'form-check-label';
    }
  }
});
</script>
