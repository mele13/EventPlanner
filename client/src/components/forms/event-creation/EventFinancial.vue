<template>
  <div class="data-container w-75">
    <div class="row">
      <!-- Sell date -->
      <div class="col">
        <!-- Ticket price -->
        <div class="form-group py-1">
          <label for="ticket-price">{{ $t('ticket_price') }}<span class="sup obl">*</span></label>
          <input type="number" class="form-control" id="ticket-price" v-model="ticketPrice" />
        </div>

        <!-- Sell date -->
        <DatePicker
          :labelContent="$t('sell_date')"
          :loadTooltip="true"
          :isRequired="true" 
          v-model="sellDate"
        />
      </div>

      <div class="col">
        <!-- Ticket currency -->
        <div class="form-group py-1">
          <label for="ticket-price">{{ $t('ticket_price') }}<span class="sup obl">*</span></label>
          <input type="number" class="form-control" id="ticket-price" v-model="ticketPrice" />
        </div>
        <!-- Sell limit -->
        <DatePicker
          :labelContent="$t('sell_limit')"
          :loadTooltip="true"
          :tooltipContent="$t('event_end_date_h')"
          v-model="sellLimitDate"
        />
      </div>
    </div>

    <!-- Refunds policy -->
    <div class="form-group py-1">
      <label for="refunds-policy">{{ $t('refunds_policy') }}<span class="sup obl">*</span></label>
      <textarea class="form-control" id="refunds-policy" rows="3" v-model="refundsPolicy"></textarea>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import DatePicker from '@/components/generators/DatePickerGenerator.vue';

export default defineComponent({
  components: {
    DatePicker,
  },
  setup() {
    const ticketPrice = ref<number>(0);
    const sellLimitDate = ref<string>('');
    const sellDate = ref<string>('');
    const refundsPolicy = ref<string>('');

    return {
      ticketPrice,
      sellLimitDate,
      sellDate,
      refundsPolicy,
    };
  },
});
</script>
