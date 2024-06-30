<template>
  <section id="event-template">
    <!-- Countdown Section -->
    <div class="countdown-section">
      <CountdownClock
        :year="eventDate.year"
        :month="eventDate.month"
        :day="eventDate.day"
        :hour="eventDate.hour"
        :minute="eventDate.minute"
        :second="0"
        :millisecond="0"
        class="w-100"
      />
    </div>

    <!-- Landing Image -->
    <div class="landing-image">
      <img :src="landingImg" class="img-fluid w-100" loading="lazy" />
    </div>

    <!-- Venue Details -->
    <div class="venue-details">
      <div class="venue-header">
        <h1 class="venue-title">{{ $t(eventDetails) }}</h1>
      </div>
      
      <!-- Venue Info -->
      <div class="venue-info">
        <div class="row">
          <!-- Left Column -->
          <div class="col-md-6">
            <div class="venue-section">
              <div class="venue-box">
                <h3>{{ $t('when') }}</h3>
                <p class="venue-text">{{ $t(eventWhen) }}</p>
              </div>
              <div class="venue-box">
                <h3>{{ $t('where') }}</h3>
                <p class="venue-text">{{ $t(eventWhere) }}</p>
              </div>
            </div>
          </div>
          
          <!-- Right Column -->
          <div class="col-md-6">
            <div class="venue-image">
              <img :src="venueLocationImg" alt="Venue photo" class="img-fluid rounded" loading="lazy" />
            </div>
          </div>
        </div>

        <!-- Second Row -->
        <div class="row mt-4">
          <!-- Left Column -->
          <div class="col-md-6">
            <div class="venue-section">
              <div class="venue-box">
                <img :src="venueDistanceImg" alt="Venue distance" class="img-fluid rounded" loading="lazy" />
              </div>
            </div>
          </div>

          <!-- Right Column -->
          <div class="col-md-6">
            <div class="venue-section">
              <div class="venue-box">
                <h3>{{ $t('get_there') }}</h3>
                <div :v-html="getThereVenue"></div>
              </div>
            </div>
          </div>
        </div>

        <!-- Map and Address -->
        <div class="row mt-4">
          <div class="col-md-6">
            <div class="venue-map">
              <MapGenerator :lat="lat" :lng="lng" />
            </div>
          </div>
          <div class="col-md-6">
            <div class="venue-address">
              <div class="venue-box">
                <h3>{{ $t(venueAddress) }}</h3>
                <p class="venue-text">{{ $t(venueAddress) }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import CountdownClock from '@/components/CountdownClock.vue';
import MapGenerator from '@/components/generators/MapGenerator.vue';

export default defineComponent({
  components: {
    CountdownClock,
    MapGenerator
  },
  props: {
    landingImg: {
      type: String,
      required: false,
      default: '/img/templates/demo.png'
    },
    eventDate: {
      type: Object,
      required: true,
      default: () => ({ year: 2025, month: 1, day: 1, hour: 12, minute: 0 })
    },
    eventDetails: {
      type: String,
      required: false,
      default: 'event_details'
    },
    eventWhen: {
      type: String,
      required: false,
      default: 'event_when'
    },
    eventWhere: {
      type: String,
      required: false,
      default: 'event_where'
    },
    venueLocationImg: {
      type: String,
      required: false,
      default: '/img/templates/demo.png'
    },
    venueDistanceImg: {
      type: String,
      required: false,
      default: '/img/templates/demo.png'
    },
    getThereVenue: {
      required: false
    },
    getThere: {
      type: String,
      required: false,
      default: 'event_get_there'
    },
    lat: {
      type: Number,
      required: true,
      default: 28.438778011688118
    },
    lng: {
      type: Number,
      required: true,
      default: -16.463526720520367
    },
    venueAddress: {
      type: String,
      required: false,
      default: 'event_address'
    }
  }
});
</script>

<style scoped>
#event-template {
  padding: 4rem 0;
  background-color: #F8F9FA;
}

.landing-image {
  margin-bottom: 2rem;
}

.venue-details {
  background-color: #FFFFFF;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.venue-header {
  text-align: center;
  margin-bottom: 2rem;
}

.venue-title {
  font-size: 2.5rem;
  color: #333333;
}

.venue-info {
  margin-bottom: 2rem;
}

.venue-section {
  margin-bottom: 2rem;
}

.venue-box {
  background-color: #FFFFFF;
  padding: 1.5rem;
  border: 1px solid #CED4DA;
  border-radius: 10px;
}

.venue-text {
  font-size: 1.1rem;
  color: #555555;
}

.venue-image img,
.venue-map {
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

@media screen and (max-width: 768px) {
  .venue-title {
    font-size: 2rem;
  }
}
</style>
