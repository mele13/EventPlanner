<template>
  <div class="data-container w-75 mt-3">
    <div class="row">
      <!-- Sell date -->
      <div class="col">
        <!-- Ticket price -->
        <div class="form-group py-1">
          <label for="ticket-price">{{ $t('event_ticket_price') }}<span class="sup obl">*</span></label>
          <input type="number" class="form-control" id="ticket-price" v-model="ticketPrice" />
        </div>

        <!-- Sell date -->
        <DatePicker
          :labelContent="$t('event_ticket_sell_date')"
          :loadTooltip="true"
          :isRequired="true" 
          v-model="sellDate"
        />
      </div>

      <div class="col">
        <!-- Ticket currency -->
        <div class="form-group py-1">
          <label for="ticket-currency">{{ $t('event_ticket_currency') }}<span class="sup obl">*</span></label>
          <select class="form-control" id="credential-role" v-model="selectedCurrency">
            <option v-for="currency in Object.values(Currency)" :key="currency" :value="currency">
              {{ currency }}
            </option>
          </select>
        </div>

        <!-- Sell limit -->
        <DatePicker
          :labelContent="$t('event_ticket_sell_limit')"
          :loadTooltip="true"
          :tooltipContent="$t('event_ticket_sell_limit_h')"
          v-model="sellLimitDate"
        />
      </div>
    </div>

    <!-- Refunds policy -->
    <div class="form-group py-1">
      <label for="refunds-policy">{{ $t('event_ticket_refunds_policy') }}</label>
      <textarea class="form-control" id="refunds-policy" rows="3" v-model="refundsPolicy"></textarea>
    </div>

    <!-- Discount Codes -->
    <div class="form-group py-1">
      <label for="discount-codes">{{ $t('event_ticket_discount_code') }}</label>
      <div class="input-group mb-3">
        <input type="text" class="form-control" v-model="newDiscountCode" placeholder="Add a discount code" />
        <button class="btn btn-evt-hv pointer" type="button" @click.prevent="addDiscountCode">{{ $t('add') }}</button>        
      </div>
      <ul class="list-group">
        <li v-for="(code, index) in discountCodes" :key="index" class="list-group-item d-flex justify-content-between align-items-center">
          {{ code }}
          <button class="btn btn-sm btn-outline-danger" @click.prevent="removeDiscountCode(index)"><i-iconoir:cancel /></button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import DatePicker from '@/components/generators/DatePickerGenerator.vue';
import { Currency } from '@/interfaces/enums/Currency';

export default defineComponent({
  components: {
    DatePicker,
  },
  setup() {
    const ticketPrice = ref<number>(0);
    const sellLimitDate = ref<string>('');
    const sellDate = ref<string>('');
    const refundsPolicy = ref<string>('');
    const selectedCurrency = ref<string>(Object.values(Currency)[0]);
    const newDiscountCode = ref<string>('');
    const discountCodes = ref<string[]>([]);

    const addDiscountCode = () => {
      if (newDiscountCode.value.trim() !== '') {
        discountCodes.value.push(newDiscountCode.value.trim());
        newDiscountCode.value = '';
      }
    };

    const removeDiscountCode = (index: number) => {
      discountCodes.value.splice(index, 1);
    };

    return {
      ticketPrice,
      sellLimitDate,
      sellDate,
      refundsPolicy,
      selectedCurrency,
      Currency,
      newDiscountCode,
      discountCodes,
      addDiscountCode,
      removeDiscountCode,
    };
  },
});
</script>
