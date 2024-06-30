<template>
  <!-- Landing -->
  <section id="landing">
    <div class="w-100">
      <img :src="landingImg" class="img-fluid w-100 px-2" loading="lazy" />
    </div>

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
  </section>

  <!-- Venue -->
  <section id="venue">
    <div class="d-flex flex-column justify-content-center align-items-center clay p-4">
      <div class="acc-title">
        <h1 class="stylo mx-4 sp-font underlined-wh text-white">~ {{ $t(eventDetails) }} ~</h1>
      </div>
    </div>

    <!-- First row -->
    <div class="row contain-right">
      <!-- Left column -->
      <div class="col-md d-flex flex-column justify-content-center align-items-center">
        <div
          class="border border-warning rp-20 w-70p d-flex flex-column justify-content-center p-4"
          id="where-cont"
        >
          <div id="when">
            <h3>{{ $t('when') }}</h3>
            <p class="p-ven">{{ $t(eventWhen) }}</p>
          </div>
          <div id="where">
            <h3>{{ $t('where') }}</h3>
            <p class="p-ven">{{ $t(eventWhere) }}</p>
          </div>
          <hr class="px2" />
        </div>
      </div>
      <!-- Right column -->
      <div class="col-md d-flex flex-column justify-content-center align-items-center">
        <img
          :src="venueLocationImg"
          alt="Venue photo"
          class="w-100 rounded-left"
          id="venue-loc"
          loading="lazy"
        />
      </div>
    </div>
    <!-- Second row -->
    <div class="row my-4 contain-right" id="sr-cont">
      <!-- Left column -->
      <div
        class="col-md d-flex flex-column justify-content-center align-items-center"
        id="distance"
      >
        <img :src="venueDistanceImg" alt="Venue distance" class="w-100 rp-20" loading="lazy" />
      </div>
      <!-- Right column -->
      <div
        class="col-md d-flex flex-column justify-content-center align-items-center"
        id="get-there-cont"
      >
        <div
          class="border border-warning rp-20 w-70p d-flex flex-column justify-content-center p-4"
        >
          <h3>{{ $t('get_there') }}</h3>
          <div :v-html="getThereVenue"></div>
          <hr class="px2" />
        </div>
      </div>
    </div>
    <!-- Map and address -->
    <div class="d-flex justify-content-center mb-5" id="map-cont">
      <div class="p-4 border-wed rp-20 d-inline-block" id="map-cont-cl">
        <div class="mx-3">
          <h3>{{ $t(venueAddress) }}</h3>
          <div class="row">
            <div class="col-md" id="map"><MapGenerator :lat="lat" :lng="lng" /></div>
            <div
              class="col-md border-wed rp-20 d-flex flex-column justify-content-center align-items-center p-4"
            >
              <div>{{ $t(venueAddress) }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import CountdownClock from '@/components/CountdownClock.vue'
import MapGenerator from '@/components/generators/MapGenerator.vue'

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
})
</script>

<style>
@media screen and (min-device-width: 320px) and (-webkit-min-device-pixel-ratio: 2) and (max-device-width: 500px) {
  #collage {
    padding-right: 0rem !important;
  }
}

.underlined-wh {
  border-bottom: 2px solid white;
}

.clay {
  background-color: var(--color-clay);
  padding: 2%;
}

.gif-fit { width: 50%; }
.m-gif {
  margin-top: 3%;
  margin-bottom: 3%;
}

.rp-20 {
  border-radius: 20px;
}

.rounded-left {
  border-bottom-left-radius: 10px;
}

#map {
  max-width: 80vh;
}
</style>
