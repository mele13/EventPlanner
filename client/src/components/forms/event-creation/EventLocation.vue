<template>
  <div class="event-location-form w-75 mb-3">
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
      <div class="row">
        <!-- Venue images -->
        <div v-if="venueImages.length > 0" class="col">
          <carousel :photos="venueImages" />
        </div>

        <div class="form-group col-5">
          <label>{{ $t('event_select_venue') }}<span class="sup obl">*</span></label>

          <!-- Venue selector -->
          <div class="d-flex">
            <select v-model="selectedVenue" class="form-control" @change="loadVenueDetails">
              <option v-for="venue in venues" :key="venue.id" :value="venue.id">
                {{ venue.name }}
              </option>
            </select>
          </div>

          <!-- Venue data -->
          <small v-if="selectedVenueDetails" class="form-text text-muted">
            <!-- Description -->
            <div class="row mt-2">
              <div class="d-inline-flex">
                <div class="col-auto me-3">
                  <i-gg:details-more />
                </div>
                <div class="col-auto">
                  {{ selectedVenueDetails.description }}
                </div>
              </div>
            </div>

            <!-- Phone number -->
            <div class="row mt-2">
              <div class="d-inline-flex">
                <div class="col-auto me-3">
                  <i-carbon:phone />
                </div>
                <div class="col-auto">
                  {{ selectedVenueDetails.phone }}
                </div>
              </div>
            </div>

            <!-- Address -->
            <div class="row">
              <div class="d-inline-flex">
                <div class="col-auto me-3">
                  <i-fluent-mdl2:input-address />
                </div>
                <div class="col-auto">
                  {{ selectedVenueAddress }}<br />
                  {{ selectedPostalCode }}, {{ $t(`country_code_${selectedCountry}`) }}.
                </div>
              </div>
            </div>
          </small>

          <!-- New venue -->
          <div class="mt-4">
            <label for="new-venue" class="mr-2p">{{ $t('event_venue_new_tt') }}</label>
            <button
              @click.prevent="showVenuePopup"
              class="btn btn-evt-border d-block mt-2"
              id="new-venue"
            >
              {{ $t('event_venue_new') }}
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- New venue popup -->
  <PopupGenerator :isOpen="showPopup" @close="showPopup = false">
    <template v-slot:title>
      <h4>{{ $t('event_venue_new') }}</h4>
    </template>
    <template v-slot:form>
      <!-- Name -->
      <div class="form-group">
        <label>{{ $t('event_name') }}<span class="sup obl">*</span></label>
        <input type="text" v-model="newVenue.name" class="form-control" />
      </div>

      <!-- Description -->
      <div class="form-group">
        <label>{{ $t('event_description') }}<span class="sup obl">*</span></label>
        <textarea v-model="newVenue.description" class="form-control"></textarea>
      </div>

      <!-- Phone -->
      <div class="form-group">
        <label>{{ $t('event_venue_phone') }}<span class="sup obl">*</span></label>
        <input type="text" v-model="newVenue.phoneNumber" class="form-control" />
      </div>

      <!-- Addresss -->
      <div class="form-group">
        <label>{{ $t('event_venue_address') }}<span class="sup obl">*</span></label>
        <input
          type="text"
          v-model="newAddress.street"
          class="form-control"
          :placeholder="$t('event_street')"
        />
        <input
          type="text"
          v-model="newAddress.number"
          class="form-control mt-1"
          :placeholder="$t('event_number')"
        />
        <input
          type="text"
          v-model="newAddress.postalCode"
          class="form-control mt-1"
          :placeholder="$t('event_pcode')"
        />
        <input
          type="text"
          v-model="newAddress.state"
          class="form-control mt-1"
          :placeholder="$t('event_state')"
        />
        <textarea
          v-model="newAddress.addressDetails"
          class="form-control mt-1"
          :placeholder="$t('event_address_details')"
        ></textarea>
      </div>

      <!-- City -->
      <div class="form-group">
        <label>{{ $t('event_city') }}<span class="sup obl">*</span></label>
        <select v-model="newAddress.cityId" class="form-control mt-1">
          <option v-for="city in cities" :key="city.id" :value="city.id">
            {{ city.name }}
          </option>
        </select>
      </div>

      <!-- Country -->
      <div class="form-group">
        <label>{{ $t('event_country') }}<span class="sup obl">*</span></label>
        <select v-model="newAddress.countryId" class="form-control mt-1">
          <option v-for="country in countries" :key="country.id" :value="country.id">
            {{ $t(`country_code_${country.code}`) }}
          </option>
        </select>
      </div>

      <!-- Images -->
      <div class="form-group">
        <label>{{ $t('event_venue_images') }}</label>
        <input type="file" multiple @change="handleImageUpload" class="form-control" />
        <div class="mt-2">
          <span v-for="(image, index) in newVenue.images" :key="index" class="badge bg-secondary me-1">
            {{ image }}
            <button @click="removeImage(index)" class="btn-close btn-close-white ms-1"></button>
          </span>
        </div>
      </div>

      <div class="d-flex justify-content-center">
        <button class="btn btn-evt mx-2" @click="saveNewVenue">{{ $t('add') }}</button>
        <button class="btn btn-evt mx-2" @click="showPopup = false">{{ $t('cancel') }}</button>
      </div>
    </template>
  </PopupGenerator>
</template>

<script lang="ts">
import type { CityDto, CountryDto } from '@/interfaces/dtos/LocationDto';
import type { VenueDto } from '@/interfaces/dtos/VenueDto';
import Carousel from '@/components/generators/CarouselGenerator.vue';
import { defineComponent, type PropType } from 'vue';
import PopupGenerator from '@/components/generators/PopupGenerator.vue';
import type AddressDto from '@/interfaces/dtos/AddressDto';
import AddressService from '@/services/AddressService';
import { createVenue } from '@/services/VenueService';

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
        details: '',
      },
      selectedVenue: 0,
      selectedPostalCode: '',
      selectedCountry: '',
      selectedVenueDetails: null as VenueDto | null,
      selectedVenueAddress: '',
      newVenue: {
        name: '',
        address: '',
        description: '',
        phoneNumber: '',
        images: [] as string[],
      },
      newAddress: {
        street: '',
        number: '',
        postalCode: '',
        state: '',
        addressDetails: '',
        cityId: 0,
        countryId: 0
      },
      venueImages: [] as string[],
      showPopup: false
    }
  },
  components: {
    Carousel,
    PopupGenerator,
  },
  props: {
    countries: {
      type: Array as PropType<CountryDto[]>,
      required: true
    },
    venues: {
      type: Array as PropType<VenueDto[]>,
      required: true
    },
    addresses: {
      type: Array as PropType<AddressDto[]>,
      required: true
    },
    cities: {
      type: Array as PropType<CityDto[]>,
      required: true
    }
  },
  methods: {
    loadVenueDetails() {
      if (this.venues && this.venues.length > 0) {
        const selectedVenue = this.venues.find((venue) => venue.id === this.selectedVenue)
        if (selectedVenue) {
          this.selectedVenueDetails = selectedVenue
          this.venueImages = selectedVenue.image || []

          if (this.addresses && this.addresses.length > 0) {
            const venueAddress = this.addresses.find(
              (address) => address.id === selectedVenue.addressId
            )
            if (venueAddress) {
              const venueCity = this.cities.find((city) => city.id === venueAddress.cityId)
              this.selectedPostalCode = venueAddress.postalCode
              this.selectedCountry = this.countries.find(
                (country) => country.id === venueAddress.countryId
              ).code
              this.selectedVenueAddress = `${venueAddress.street} ${venueAddress.number}, ${venueCity ? venueCity.name : ''}, ${venueAddress.state}.`
            } else {
              this.selectedVenueAddress = ''
            }
          } else {
            this.selectedVenueAddress = ''
          }
        } else {
          this.selectedVenueDetails = null
          this.venueImages = []
          this.selectedVenueAddress = ''
        }
      }
    },
    showVenuePopup() {
      this.showPopup = true
    },
    async saveNewVenue() {
      const newAddress: AddressDto = {
        street: this.newAddress.street,
        number: this.newAddress.number,
        postalCode: this.newAddress.postalCode,
        state: this.newAddress.state,
        addressDetails: this.newAddress.addressDetails,
        cityId: this.newAddress.cityId,
        countryId: this.newAddress.countryId
      }

      const newVenue: VenueDto = {
        name: this.newVenue.name,
        description: this.newVenue.description,
        phone: this.newVenue.phoneNumber,
        addressId: Math.max(...this.addresses.map((a) => a.id || 0)) + 1,
        image: this.newVenue.images,
      }

      this.saveAddress(newAddress)
      this.saveVenue(newVenue)

      this.showPopup = false
    },
    saveAddress(address: AddressDto) {
      try {
        AddressService.createAddress(address)
      } catch {
        this.addresses.push(address)
      }
    },
    saveVenue(venue: VenueDto) {
      try {
        createVenue(venue)
      } catch {
        this.venues.push(venue)
      }
    },
    handleImageUpload(event: Event) {
      const input = event.target as HTMLInputElement
      if (input.files) {
        for (let i = 0; i < input.files.length; i++) {
          const file = input.files[i]
          this.newVenue.images.push(file.name)
        }
      }
    },
    removeImage(index: number) {
      this.newVenue.images.splice(index, 1)
    },
  }
})
</script>

<style scoped>
.event-location-form {
  width: 100%;
}

.form-group {
  margin-bottom: 1rem;
}

.mr-2p {
  margin-right: 1%;
}
</style>
