<template>
  <section id="new-event-template">
    <!-- Landing Section -->
    <div class="landing-section">
      <div class="landing-image">
        <img :src="landingImg" class="img-fluid" loading="lazy" />
      </div>
      <div class="landing-countdown">
        <CountdownClock
          :year="eventDate.year"
          :month="eventDate.month"
          :day="eventDate.day"
          :hour="eventDate.hour"
          :minute="eventDate.minute"
          :second="0"
          :millisecond="0"
          class="countdown-clock"
        />
      </div>
    </div>

    <!-- Venue Information -->
    <div class="venue-section">
      <div class="venue-header">
        <h1 class="styled-title">{{ $t(eventDetails) }}</h1>
      </div>

      <!-- Venue Details -->
      <div class="venue-details">
        <div class="venue-info">
          <div class="venue-info-box">
            <h3>{{ $t('when') }}</h3>
            <p>{{ $t(eventWhen) }}</p>
          </div>
          <div class="venue-info-box">
            <h3>{{ $t('where') }}</h3>
            <p>{{ $t(eventWhere) }}</p>
          </div>
        </div>
        <div class="venue-image">
          <img :src="venueLocationImg" alt="Venue photo" class="img-fluid rounded" loading="lazy" />
        </div>
      </div>

      <!-- Additional Information -->
      <div class="additional-info-section">
        <div class="additional-info-box">
          <div class="additional-info-image">
            <img :src="venueDistanceImg" alt="Venue distance" class="img-fluid" loading="lazy" />
          </div>
          <div class="additional-info-text">
            <h3>{{ $t('get_there') }}</h3>
            <div :v-html="getThereVenue"></div>
          </div>
        </div>
      </div>

      <!-- Map and Address -->
      <div class="map-section">
        <div class="map-details">
          <h3>{{ $t(venueAddress) }}</h3>
          <div class="map-container">
            <MapGenerator :lat="lat" :lng="lng" />
          </div>
        </div>
        <div class="map-address">
          <p>{{ $t(venueAddress) }}</p>
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
  font-family: 'Roboto', sans-serif;
  background-color: #f2f2f2;
  padding: 2rem;
}

.landing-section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
}

.landing-image img {
  max-width: 100%;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.landing-countdown {
  flex: 1;
  text-align: center;
  margin-left: 2rem;
}

.countdown-clock {
  width: 100%;
  max-width: 300px;
}

.styled-title {
  font-size: 2.5rem;
  color: #333;
  text-transform: uppercase;
  text-align: center;
  margin-bottom: 2rem;
}

.venue-section {
  background-color: #ffffff;
  border-radius: 10px;
  padding: 2rem;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin-top: 2rem;
}

.venue-header {
  text-align: center;
  margin-bottom: 2rem;
}

.venue-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
}

.venue-info-box {
  flex: 1;
  background-color: #f3f3f3;
  padding: 1.5rem;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.venue-info-box h3 {
  font-size: 1.5rem;
  margin-bottom: 0.5rem;
}

.venue-image {
  flex: 1;
  margin-left: 2rem;
  text-align: center;
}

.venue-image img {
  max-width: 100%;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.additional-info-section {
  margin-top: 2rem;
}

.additional-info-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #f3f3f3;
  padding: 1.5rem;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.additional-info-image img {
  max-width: 100%;
}

.map-section {
  background-color: #ffffff;
  border-radius: 10px;
  padding: 2rem;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin-top: 2rem;
}

.map-details {
  margin-bottom: 2rem;
}

.map-container {
  max-width: 100%;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.map-address {
  text-align: center;
}

.map-address p {
  font-size: 1.2rem;
}

@media screen and (max-width: 768px) {
  .landing-section {
    flex-direction: column;
  }

  .landing-countdown {
    margin-left: 0;
    margin-top: 2rem;
  }

  .venue-info {
    flex-direction: column;
  }

  .venue-image {
    margin-left: 0;
    margin-top: 1rem;
  }

  .additional-info-box {
    flex-direction: column;
  }
}
</style>
