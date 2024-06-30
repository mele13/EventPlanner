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

    <!-- Event Details -->
    <div class="event-details">
      <div class="event-title">
        <h1 class="styled-title">{{ $t(eventDetails) }}</h1>
      </div>

      <!-- Venue Information -->
      <div class="venue-info">
        <!-- Venue Location and Details -->
        <div class="venue-section">
          <div class="venue-image">
            <img :src="venueLocationImg" alt="Venue photo" class="img-fluid" loading="lazy" />
          </div>
          <div class="venue-details">
            <div class="venue-box">
              <h3>{{ $t('where') }}</h3>
              <p class="p-ven">{{ $t(eventWhere) }}</p>
            </div>
            <div class="venue-box">
              <h3>{{ $t('when') }}</h3>
              <p class="p-ven">{{ $t(eventWhen) }}</p>
            </div>
          </div>
        </div>

        <!-- Additional Information -->
        <div class="additional-info">
          <div class="additional-info-item">
            <img :src="venueDistanceImg" alt="Venue distance" class="img-fluid" loading="lazy" />
          </div>
          <div class="additional-info-item">
            <div class="get-there-box">
              <h3>{{ $t('get_there') }}</h3>
              <div :v-html="getThereVenue"></div>
            </div>
          </div>
        </div>

        <!-- Map and Address -->
        <div class="map-section">
          <div class="map-container">
            <MapGenerator :lat="lat" :lng="lng" />
          </div>
          <div class="address-info">
            <h3>{{ $t(venueAddress) }}</h3>
            <p>{{ $t(venueAddress) }}</p>
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
  font-family: 'Roboto', sans-serif;
  background-color: #f9f9f9;
  padding: 2rem;
}

.landing-container {
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

.countdown-section {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.event-title {
  text-align: center;
  margin-bottom: 2rem;
}

.styled-title {
  font-size: 2.5rem;
  color: #333;
  text-transform: uppercase;
  text-align: center;
}

.venue-info {
  background-color: #ffffff;
  border-radius: 10px;
  padding: 2rem;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.venue-section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
}

.venue-image img {
  max-width: 100%;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.venue-details {
  flex: 1;
}

.venue-box {
  padding: 1.5rem;
  background-color: #f8f8f8;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin-bottom: 1rem;
}

.additional-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 2rem;
}

.additional-info-item {
  flex: 1;
  padding: 1.5rem;
  background-color: #f8f8f8;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.get-there-box {
  padding: 1.5rem;
  background-color: #f8f8f8;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.map-section {
  background-color: #ffffff;
  border-radius: 10px;
  padding: 2rem;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin-top: 2rem;
}

.map-container {
  max-width: 100%;
  border-radius: 10px;
  overflow: hidden;
}

.address-info {
  padding: 1.5rem;
  background-color: #f8f8f8;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin-top: 1rem;
}

@media screen and (max-width: 768px) {
  .landing-container {
    flex-direction: column;
  }

  .venue-section {
    flex-direction: column;
  }
}
</style>
