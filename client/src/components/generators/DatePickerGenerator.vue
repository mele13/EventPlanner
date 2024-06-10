<template>
  <div class="form-group d-flex flex-column py-1">
    <label class="align-self-start">
      {{ labelContent }}<span v-if="isRequired" class="sup obl">*</span>
      <span v-if="loadTooltip" class="ml-2r"></span>
      <TooltipHint v-if="loadTooltip"
        :content="tooltipContent" 
        :iconColor="tooltipIconColor" 
        :iconSize="tooltipIconSize" 
        :borderColor="tooltipBorderColor" 
        :textPosition="tooltipTextPosition" 
        :sup="sup" 
      />
    </label>
    <VueDatePicker 
      v-model="dateValue" 
      :year-range="yearRange" 
      :format="format" 
      :readonly="readonly" 
    />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import VueDatePicker from '@vuepic/vue-datepicker';
import TooltipHint from '@/components/generators/TooltipHintGenerator.vue';

export default defineComponent({
  components: {
    VueDatePicker,
    TooltipHint,
  },
  props: {
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
    loadTooltip : {
      type: Boolean,
      required: true,
    },
    tooltipContent: {
      type: String,
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
      default: false,
    },
    // Date picker
    readonly: {
      type: Boolean,
      default: false
    },
    format: {
      type: String,
      default: 'dd/MM/yyyy HH:mm'
    },
    yearRange: {
      type: Array,
      default: () => {
        const currentYear = new Date().getFullYear();
        return [currentYear, currentYear + 10];
      }
    },
    modelValue: {
      type: String,
      required: true
    }
  },
  emits: ['update:modelValue'],
  computed: {
    dateValue: {
      get() {
        return this.modelValue;
      },
      set(value: string) {
        this.$emit('update:modelValue', value);
      }
    },
  }
});
</script>
