<template>
  <div class="d-flex align-items-center justify-content-center">
    <div class="d-flex w-75 gap-p justify-content-center">
      <div class="text-center">
        <span class="months time-numbers">{{ displayMonths }}</span>
        <p class="timeRefDays time-description">{{ $t('months') }}</p>
      </div>
      <div class="text-center justify-content-center">
        <span class="days time-numbers">{{ displayDays }}</span>
        <p class="timeRefDays time-description">{{ $t('days') }}</p>
      </div>
      <div class="text-center justify-content-center">
        <span class="hours time-numbers">{{ displayHours }}</span>
        <p class="timeRefHours time-description">{{ $t('hours') }}</p>
      </div>
      <div class="text-center justify-content-center">
        <span class="minutes time-numbers">{{ displayMinutes }}</span>
        <p class="timeRefMinutes time-description">{{ $t('minutes') }}</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  props: ['year', 'month', 'day', 'hour', 'minute', 'second', 'millisecond'],
  data: () => ({
    displayMonths: 0,
    displayDays: 0,
    displayHours: 0,
    displayMinutes: 0,
    displaySeconds: 0
  }),
  computed: {
    _seconds() {
      return 1000
    },
    _minutes() {
      return this._seconds * 60
    },
    _hours() {
      return this._minutes * 60
    },
    _days() {
      return this._hours * 24
    },
    _months() {
      return this._days / 30
    },
    end() {
      return new Date(
        this.year,
        this.month - 1, // Months are zero-based in JavaScript dates
        this.day,
        this.hour,
        this.minute,
        this.second,
        this.millisecond
      )
    }
  },
  mounted() {
    this.showRemaining()
  },
  methods: {
    formatNum: (num) => (num < 10 ? '0' + num : num),
    showRemaining() {
      const timer = setInterval(() => {
        const now = new Date()
        const distance = this.end.getTime() - now.getTime()

        if (distance < 0) {
          clearInterval(timer)
          return
        }

        const months = Math.floor(distance / this._days / 30)
        const days = Math.floor((distance % (this._days * 30)) / this._days)
        const hours = Math.floor((distance % this._days) / this._hours)
        const minutes = Math.floor((distance % this._hours) / this._minutes)

        this.displayMonths = this.formatNum(months)
        this.displayMinutes = this.formatNum(minutes)
        this.displayHours = this.formatNum(hours)
        this.displayDays = this.formatNum(days)
      }, 1000)
    }
  }
}
</script>

<style scoped>
#content {
  margin: 0 auto;
}

p:last-child {
  font-weight: 700;
  letter-spacing: 0.1rem;
}

.time-numbers {
  font-size: 4rem;
}

p.time-description {
  font-size: 1.1rem;
  font-variant: small-caps;
  text-align: center;
}

.gap-p {
  gap: 5%;
}

/* For iPhone 4 Portrait or Landscape View */
@media screen and (min-device-width: 320px) and (-webkit-min-device-pixel-ratio: 2) and (max-device-width: 500px) {
  p.time-description { font-size: 0.8rem; }
  .time-numbers { font-size: 1.3rem; }
}

@media screen and (min-device-width: 501px) and (-webkit-min-device-pixel-ratio: 2) and (max-device-width: 800px) {
  p.time-description { font-size: 1.2rem; }
  .time-numbers { font-size: 2rem; }
}
</style>
