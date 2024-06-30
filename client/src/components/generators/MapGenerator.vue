<template>
  <div class="app-container">
    <GoogleMap
      v-if="center && api"
      :api-key="api"
      :center="center"
      :zoom="17"
      class="map-container h-100"
    >
      <CustomMarker :options="{ position: center, anchorPoint: 'BOTTOM_CENTER' }">
        <div style="text-align: center">
          <i-icomoon-free:location width="100%" height="50" class="negative" />
        </div>
      </CustomMarker>
    </GoogleMap>
    <div v-else>Loading map...</div>
  </div>
</template>

<script lang="ts" type="module">
import { computed } from "vue";
import { GoogleMap, CustomMarker } from "vue3-google-map";

export default {
  name: 'MapComponent',
  components: { GoogleMap, CustomMarker },
  props: {
    lat: {
      type: Number,
      required: true,
    },
    lng: {
      type: Number,
      required: true,
    }
  },
  setup(props) {
    const center = computed(() => ({
      lat: props.lat,
      lng: props.lng
    }));

    return {
      center,
      api: import.meta.env.VITE_GOOGLE_MAPS_API_KEY,
    };
  }
}
</script>

<style>
.app-container {
  height: 30vh;
  width: 100vh;
}

@media screen and (min-device-width: 320px) and (-webkit-min-device-pixel-ratio: 2) {
  .app-container {
    width: 100%;
  }
}

@media screen and (min-device-width: 768px) and (max-device-width: 1024px) {
  .app-container {
    width: 100%;
  }
}

@media screen and (min-device-width: 430px) and (max-device-width: 768px) {
  .app-container {
    width: 100%;
  }
}

@media screen and (min-device-width: 570px) and (max-device-width: 999px) {
  .app-container {
    min-width: 45vh;
  }
}

@media screen and (min-device-width: 1000px) and (max-device-width: 1170px) {
  .app-container {
    width: 85vh;
  }
}

@media screen and (min-device-width: 1171px) {
  .app-container {
    width: 100vh;
  }
}
</style>
