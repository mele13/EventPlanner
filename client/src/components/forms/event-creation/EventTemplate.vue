<template>
  <div class="data-container w-75 mt-3">
    <!-- Event contact -->
    <div class="d-flex align-items-center mb-1">
      <i-material-symbols-light:contact-mail-outline class="tt-icon" />
      <h4 class="mb-0">{{ $t('event_contact') }}</h4>
    </div>

    <div class="form-group py-1 mb-3">
      <label for="contact-email">{{ $t('email') }}<span class="sup obl">*</span></label>
      <input
        type="email"
        class="form-control"
        id="contact-email"
        v-model="contactEmail"
        @input="validateEmail(contactEmail)"
      />
      <div v-if="emailError" class="text-danger mt-1">{{ $t('email_invalid') }}</div>
    </div>

    <!-- Event template -->
    <div class="d-flex align-items-center mb-1">
      <i-material-symbols-light:format-paint-outline class="tt-icon" />
      <h4 class="mb-0">{{ $t('event_template') }}</h4>
    </div>

    <div class="form-group my-3">
      <label>{{ $t('event_template_sel') }}<span class="sup obl">*</span></label>

      <div class="d-flex mt-1 gap-4">
        <button
          class="btn btn-evt-hv"
          @click.prevent="selectTemplate(1)"
          :class="{ 'btn-selected': selectedTemplate === 1 }"
        >
          {{ $t('event_template_tt') }} 1
        </button>
        <button
          class="btn btn-evt-hv"
          @click.prevent="selectTemplate(2)"
          :class="{ 'btn-selected': selectedTemplate === 2 }"
        >
          {{ $t('event_template_tt') }} 2
        </button>
        <button
          class="btn btn-evt-hv"
          @click.prevent="selectTemplate(3)"
          :class="{ 'btn-selected': selectedTemplate === 3 }"
        >
          {{ $t('event_template_tt') }} 3
        </button>
        <button
          class="btn btn-evt-hv"
          @click.prevent="selectTemplate(4)"
          :class="{ 'btn-selected': selectedTemplate === 4 }"
        >
          {{ $t('event_template_tt') }} 4
        </button>
        <button
          class="btn btn-evt-hv"
          @click.prevent="selectTemplate(5)"
          :class="{ 'btn-selected': selectedTemplate === 5 }"
        >
          {{ $t('event_template_tt') }} 5
        </button>
      </div>
    </div>

    <div class="form-group my-3">
      <label>{{ $t('event_palette_sel') }}<span class="sup obl">*</span></label>

      <ColorPaletteGenerator :initialColors="paletteColors" @update:colors="updatePaletteColors" />
    </div>

    <PopupGenerator :isOpen="isTemplateSelectorOpen" @close="closeTemplateSelector">
      <template v-slot:title>
        <h3>{{ $t('event_title') }}</h3>
      </template>
      <template v-slot:form>
        <component :is="selectedTemplateComponent" />
      </template>
    </PopupGenerator>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, ref } from 'vue';
import { validateEmail } from '@/utils/Tools';
import PopupGenerator from '@/components/generators/PopupGenerator.vue';
import ColorPaletteGenerator from '@/components/generators/ColorPaletteGenerator.vue';
import EventTemplate1 from '@/components/templates/EventTemplate1.vue';
import EventTemplate2 from '@/components/templates/EventTemplate2.vue';
import EventTemplate3 from '@/components/templates/EventTemplate3.vue';
import EventTemplate4 from '@/components/templates/EventTemplate4.vue';
import EventTemplate5 from '@/components/templates/EventTemplate5.vue';

export default defineComponent({
  setup() {
    const contactEmail = ref<string>('');
    const emailError = ref<string | null>(null);
    const isTemplateSelectorOpen = ref<boolean>(false);
    const selectedTemplate = ref<number | null>(null);
    const paletteColors = ref<string[]>(['#F9F6EE', '#8D453D', '#FA8072', '#FAA0A0', '#FFC107']);

    // Compute the component to render based on selectedTemplate
    const selectedTemplateComponent = computed(() => {
      switch (selectedTemplate.value) {
        case 1:
          return EventTemplate1;
        case 2:
          return EventTemplate2;
        case 3:
          return EventTemplate3;
        case 4:
          return EventTemplate4;
        case 5:
          return EventTemplate5;
        default:
          return null;
      }
    });

    const selectTemplate = (templateIndex: number) => {
      selectedTemplate.value = templateIndex;
      isTemplateSelectorOpen.value = true;
    }

    const updatePaletteColors = (colors: string[]) => {
      paletteColors.value = colors;
    }

    const openTemplateSelection = () => {
      isTemplateSelectorOpen.value = true;
    }

    const closeTemplateSelector = () => {
      isTemplateSelectorOpen.value = false;
    }

    const confirmTemplateSelection = () => {
      selectedTemplate.value = null;
      isTemplateSelectorOpen.value = false;
    }

    return {
      contactEmail,
      emailError,
      paletteColors,
      selectTemplate,
      selectedTemplate,
      validateEmail,
      isTemplateSelectorOpen,
      selectedTemplateComponent,
      openTemplateSelection,
      closeTemplateSelector,
      confirmTemplateSelection,
      updatePaletteColors,
      PopupGenerator,
      ColorPaletteGenerator,
    }
  }
})
</script>

<style scoped>
.data-container {
  margin-top: 2rem;
}

.btn-selected {
  border-color: var(--color-tertiary) !important;
}
</style>
