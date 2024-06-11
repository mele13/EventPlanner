<!-- <template>
  address: street, number, postal code, city, state, country (select country name), address_details
  venue: address, name, description, phone number
</template> -->

<template>
  <div class="event-location-form">
    <h4>{{ $t('event_location') }}</h4>
    
    <div class="form-group">
      <label>{{ $t('event_address_or_venue') }}<span class="sup obl">*</span></label>
      <select v-model="locationType" class="form-control">
        <option value="address">{{ $t('address') }}</option>
        <option value="venue">{{ $t('venue') }}</option>
      </select>
    </div>

    <div v-if="locationType === 'address'" class="address-form">
      <h5>{{ $t('address_details') }}</h5>
      <div class="form-group">
        <label>{{ $t('street') }}<span class="sup obl">*</span></label>
        <input type="text" v-model="address.street" class="form-control"/>
      </div>
      <div class="form-group">
        <label>{{ $t('number') }}<span class="sup obl">*</span></label>
        <input type="text" v-model="address.number" class="form-control"/>
      </div>
      <div class="form-group">
        <label>{{ $t('postal_code') }}<span class="sup obl">*</span></label>
        <input type="text" v-model="address.postalCode" class="form-control"/>
      </div>
      <div class="form-group">
        <label>{{ $t('city') }}<span class="sup obl">*</span></label>
        <input type="text" v-model="address.city" class="form-control"/>
      </div>
      <div class="form-group">
        <label>{{ $t('state') }}<span class="sup obl">*</span></label>
        <input type="text" v-model="address.state" class="form-control"/>
      </div>
      <div class="form-group">
        <label>{{ $t('country') }}<span class="sup obl">*</span></label>
        <select v-model="address.country" class="form-control">
          <option v-for="country in countries" :key="country.code" :value="country.code">
            {{ country.name }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label>{{ $t('address_details') }}</label>
        <textarea v-model="address.details" class="form-control"></textarea>
      </div>
    </div>

    <div v-if="locationType === 'venue'" class="venue-form">
      <h5>{{ $t('venue_details') }}</h5>
      <div class="form-group">
        <label>{{ $t('select_venue') }}<span class="sup obl">*</span></label>
        <div class="d-flex">
          <select v-model="selectedVenue" class="form-control">
            <option v-for="venue in venues" :key="venue.id" :value="venue.id">
              {{ venue.name }} <small>({{ venue.address.state }}, {{ venue.address.country }})</small>
            </option>
          </select>
          <button @click="showVenueForm = true" class="btn btn-link">{{ $t('create_new_venue') }}</button>
        </div>
      </div>
      
      <div v-if="showVenueForm" class="new-venue-form">
        <div class="form-group">
          <label>{{ $t('venue_name') }}<span class="sup obl">*</span></label>
          <input type="text" v-model="newVenue.name" class="form-control"/>
        </div>
        <div class="form-group">
          <label>{{ $t('venue_address') }}<span class="sup obl">*</span></label>
          <select v-model="newVenue.address" class="form-control">
            <option v-for="address in addresses" :key="address.id" :value="address.id">
              {{ address.street }}, {{ address.city }} ({{ address.state }}, {{ address.country }})
            </option>
          </select>
        </div>
        <div class="form-group">
          <label>{{ $t('venue_description') }}</label>
          <textarea v-model="newVenue.description" class="form-control"></textarea>
        </div>
        <div class="form-group">
          <label>{{ $t('phone_number') }}</label>
          <input type="text" v-model="newVenue.phoneNumber" class="form-control"/>
        </div>
        <button @click="addNewVenue" class="btn btn-primary">{{ $t('add_venue') }}</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

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
      venues: [], // List of venues
      selectedVenue: 0,
      showVenueForm: false,
      newVenue: {
        name: '',
        address: '',
        description: '',
        phoneNumber: ''
      },
      countries: [], // List of countries
      addresses: [] // List of addresses
    };
  },
  methods: {
    addNewVenue() {
      // Logic to add new venue
      const newVenue = {
        ...this.newVenue,
        id: Date.now(),
        address: this.addresses.find(address => address.id === this.newVenue.address)
      };
      this.venues.push(newVenue);
      this.showVenueForm = false;
      this.selectedVenue = newVenue.id;
    }
  },
  created() {
    // Fetch initial data for countries, venues, and addresses
    this.countries = [
      { name: 'Spain', code: 'ES', lang_code: 'es' },
      { name: 'United States', code: 'US', lang_code: 'en' },
      // More countries...
    ];
    this.addresses = [
      { id: 1, street: '123 Main St', city: 'Madrid', state: 'Madrid', country: 'Spain' },
      { id: 2, street: '456 Elm St', city: 'Barcelona', state: 'Catalonia', country: 'Spain' },
      // More addresses...
    ];
    this.venues = [
      { id: 1, name: 'Conference Hall', address: this.addresses[0] },
      { id: 2, name: 'Expo Center', address: this.addresses[1] },
      // More venues...
    ];
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
