<template>
  <div class="data-container w-75 mt-3 mb-3">
    <div class="d-flex align-items-center mt-3 mb-1">
      <i-wpf:faq class="tt-icon" />
      <h4>{{ $t('event_faq_tt') }}</h4>
    </div>

    <!-- Question -->
    <label for="new-question">{{ $t('event_faq_question') }}</label>
    <div class="row">
      <div class="col">
        <div class="form-group py-1">
          <input
            type="text"
            class="form-control"
            v-model="newQuestion"
            :placeholder="$t('event_faq_question_pl')"
          />
        </div>

        <!-- Answer -->
        <div class="form-group py-1">
          <label for="new-answer">{{ $t('event_faq_answer') }}</label>
          <textarea
            class="form-control"
            v-model="newAnswer"
            :placeholder="$t('event_faq_answer_pl')"
          ></textarea>
        </div>
      </div>
      
      <!-- New faq button -->
      <div class="col-auto d-flex align-items-center">
        <button class="btn btn-evt-hv w-100 h-100" @click.prevent="addFaq">{{ $t('add') }}</button>
      </div>
    </div>
    <ul class="list-group mt-3">
      <li
        v-for="(faq, index) in faqs"
        :key="index"
        class="list-group-item d-flex justify-content-between align-items-center"
      >
        <div>
          <strong>{{ faq.question }}</strong>
          <br />
          {{ faq.answer }}
        </div>
        <button class="btn btn-sm btn-evt-hv" @click.prevent="removeFaq(index)">
          <i-iconoir:cancel />
        </button>
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'

interface Faq {
  question: string
  answer: string
}

export default defineComponent({
  setup() {
    const newQuestion = ref<string>('')
    const newAnswer = ref<string>('')
    const faqs = ref<Faq[]>([])

    const addFaq = () => {
      if (newQuestion.value.trim() && newAnswer.value.trim()) {
        faqs.value.push({ question: newQuestion.value.trim(), answer: newAnswer.value.trim() })
        newQuestion.value = ''
        newAnswer.value = ''
      }
    }

    const removeFaq = (index: number) => {
      faqs.value.splice(index, 1)
    }

    return {
      newQuestion,
      newAnswer,
      faqs,
      addFaq,
      removeFaq
    }
  }
})
</script>

<style scoped>
.faq-manager {
  margin-top: 2rem;
}
</style>
