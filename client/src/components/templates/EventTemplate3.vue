<template>
  <section id="new-event-template">
    <!-- Countdown and Landing -->
    <div class="landing-container">
      <div class="landing-image">
        <img :src="landingImg" class="img-fluid" loading="lazy" />
      </div>
      <div class="countdown-section">
        <CountdownClock
          :year="eventDate.year"
          :month="eventDate.month"
          :day="eventDate.day"
          :hour="eventDate.hour"
          :minute="eventDate.minute"
          :second="0"
          :millisecond="0"
        />
      </div>
    </div>

    <!-- Venue Information -->
    <div class="venue-info">
      <div class="venue-header">
        <h1 class="event-title">{{ $t(eventDetails) }}</h1>
      </div>

      <!-- Venue Details -->
      <div class="venue-details">
        <div class="row">
          <div class="col-md-6">
            <div class="venue-box">
              <h3>{{ $t('when') }}</h3>
              <p>{{ $t(eventWhen) }}</p>
            </div>
            <div class="venue-box">
              <h3>{{ $t('where') }}</h3>
              <p>{{ $t(eventWhere) }}</p>
            </div>
          </div>
          <div class="col-md-6">
            <div class="venue-image">
              <img :src="venueLocationImg" alt="Venue photo" class="img-fluid" loading="lazy" />
            </div>
          </div>
        </div>

        <!-- Additional Information -->
        <div class="row mt-4">
          <div class="col-md-6">
            <div class="venue-box">
              <img :src="venueDistanceImg" alt="Venue distance" class="img-fluid" loading="lazy" />
            </div>
          </div>
          <div class="col-md-6">
            <div class="venue-box">
              <h3>{{ $t('get_there') }}</h3>
              <div :v-html="getThereVenue"></div>
            </div>
          </div>
        </div>

        <!-- Map and Address -->
        <div class="map-section mt-4">
          <h3>{{ $t(venueAddress) }}</h3>
          <div class="row">
            <div class="col-md-6">
              <div class="venue-map">
                <MapGenerator :lat="lat" :lng="lng" />
              </div>
            </div>
            <div class="col-md-6">
              <div class="venue-address">
                <p>{{ $t(venueAddress) }}</p>
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
#new-event-template {
  padding: 4rem 0;
  background-color: #F0F2F5;
}

.landing-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 3rem;
}

.landing-image {
  flex: 1;
  max-width: 50%;
}

.countdown-section {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.event-title {
  font-size: 2.5rem;
  color: #333;
  text-align: center;
  margin-bottom: 2rem;
}

.venue-details {
  background-color: #FFFFFF;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.venue-box {
  background-color: #F8F9FA;
  padding: 1.5rem;
  margin-bottom: 1.5rem;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.venue-image img,
.venue-map {
  width: 100%;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.map-section {
  background-color: #FFFFFF;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

@media screen and (max-width: 768px) {
  .landing-container {
    flex-direction: column;
  }
}
</style>
