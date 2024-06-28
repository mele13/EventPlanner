<template>
  <div class="event-location-form">
    <div class="d-flex align-items-center mt-3 mb-1">
      <i-tabler:location class="tt-icon" />
      <h4 class="mb-0">{{ $t('event_location_tt') }}</h4>
    </div>

    <!-- Address or venue selector -->
    <div class="form-group">
      <label>{{ $t('event_venue_host') }}<span class="sup obl">*</span></label>

      <div class="radio-group d-flex flex-row gap-2">
        <div class="form-check">
          <input
            class="form-check-input"
            type="radio"
            id="locationTypeAddress"
            value="venue"
            v-model="locationType"
          />
          <label class="form-check-label" for="locationTypeAddress">{{ $t('yes') }}</label>
        </div>
        <div class="form-check">
          <input
            class="form-check-input"
            type="radio"
            id="locationTypeVenue"
            value="address"
            v-model="locationType"
          />
          <label class="form-check-label" for="locationTypeVenue">{{ $t('no') }}</label>
        </div>
      </div>
    </div>

    <!-- Address -->
    <div v-if="locationType === 'address'">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-ph:mailbox-bold class="tt-icon" />
        <h4 class="mb-0">{{ $t('event_address_details') }}</h4>
      </div>

      <div class="row">
        <div class="col">
          <!-- Country -->
          <div class="form-group">
            <label>{{ $t('event_country') }}<span class="sup obl">*</span></label>
            <select v-model="address.country" class="form-control">
              <option v-for="country in countries" :key="country.code" :value="country.code">
                {{ $t(`country_code_${country.code}`) }}
              </option>
            </select>
          </div>
        </div>
        <div class="col">
          <!-- State -->
          <div class="form-group">
            <label>{{ $t('event_state') }}<span class="sup obl">*</span></label>
            <input type="text" v-model="address.state" class="form-control" />
          </div>
        </div>
        <div class="col">
          <!-- City -->
          <div class="form-group">
            <label>{{ $t('event_city') }}<span class="sup obl">*</span></label>
            <input type="text" v-model="address.city" class="form-control" />
          </div>
        </div>
      </div>

      <div class="form-group d-flex gap-2">
        <!-- Street -->
        <div class="flex-grow-1">
          <label>{{ $t('event_street') }}<span class="sup obl">*</span></label>
          <input type="text" v-model="address.street" class="form-control" />
        </div>
        <!-- Number -->
        <div>
          <label>{{ $t('event_number') }}<span class="sup obl">*</span></label>
          <input type="text" v-model="address.number" class="form-control" />
        </div>
        <!-- Postal code -->
        <div>
          <label>{{ $t('event_pcode') }}<span class="sup obl">*</span></label>
          <input type="text" v-model="address.postalCode" class="form-control" />
        </div>
      </div>

      <!-- Other details -->
      <div class="form-group">
        <label>{{ $t('event_other_details') }}</label>
        <textarea v-model="address.details" class="form-control"></textarea>
      </div>
    </div>

    <!-- Venue -->
    <div v-if="locationType === 'venue'" class="venue-form">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-tdesign:houses-2 class="tt-icon" />
        <h4 class="mb-0">{{ $t('event_venue_details') }}</h4>
      </div>

      <!-- Venue selector -->
      <div class="form-group">
        <label>{{ $t('select_venue') }}<span class="sup obl">*</span></label>
        <div class="d-flex">
          <select v-model="selectedVenue" class="form-control" @change="loadVenueImages">
            <option v-for="venue in venues" :key="venue.id" :value="venue.id">
              {{ venue.name }}
            </option>
          </select>
          <div class="">
            <label for="new-venue">{{ $t('event_venue_new_tt') }}</label>
            <button @click.prevent="showVenueForm = true" class="btn btn-link" id="new-venue">
              {{ $t('event_venue_new') }}
            </button>
          </div>
        </div>
      </div>

      <!-- Create new venue -->
      <div v-if="showVenueForm" class="new-venue-form">
        <div class="form-group">
          <label>{{ $t('venue_name') }}<span class="sup obl">*</span></label>
          <input type="text" v-model="newVenue.name" class="form-control" />
        </div>
      </div>

      <!-- Carrusel de imágenes del venue -->
      <div v-if="venueImages.length > 0" class="mt-4">
        <h5>{{ $t('event_venue_photos') }}</h5>
        <carousel :photos="venueImages" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import type { CountryDto } from '@/interfaces/dtos/LocationDto';
import type { VenueDto } from '@/interfaces/dtos/VenueDto';
import Carousel from '@/components/generators/CarouselGenerator.vue';
import { defineComponent, type PropType } from 'vue';

export default defineComponent({
  data() {
    return {
      locationType: 'address', // 'address' or 'venue'
      address: {
        street: '',
        number: '',
        postalCode: '',
        city: '',
        state: '',
        country: '',
        details: ''
      },
      selectedVenue: 0,
      showVenueForm: false,
      newVenue: {
        name: '',
        address: '',
        description: '',
        phoneNumber: ''
      },
      venueImages: [] as string[],
    };
  },
  components: {
    Carousel
  },
  props: {
    countries: {
      type: Array as PropType<CountryDto[]>,
      required: true
    },
    venues: {
      type: Array as PropType<VenueDto[]>,
      required: true
    }
  },
  methods: {
    loadVenueImages() {
      console.log("selecting venue")
      const selectedVenue = this.venues.find(venue => venue.id === this.selectedVenue);
      console.log("trying to load images", selectedVenue)
      if (selectedVenue)  this.venueImages = selectedVenue.image || [];
      else this.venueImages = [];
    }
  }
});
</script>

<style scoped>
.event-location-form {
  width: 100%;
}

.form-group {
  margin-bottom: 1rem;
}
</style>
