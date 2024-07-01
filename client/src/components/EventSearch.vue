<template>
  <div class="container mb-5">
    <h3 class="mb-0">{{ $t('search_event') }}</h3>
    <small class="text-secondary"><span class="obl">*</span> {{ $t('select_to_search') }}</small>
    
    <!-- Search form -->
    <form @submit.prevent="handleSearch" class="mt-2">
      <!-- Name -->
      <div class="form-group">
        <label for="name">{{ $t('event_name') }}</label>
        <input type="text" id="name" v-model="searchName" class="form-control" />
      </div>
      
      <!-- Year -->
      <div class="form-group">
        <label for="year">{{ $t('event_year') }}</label>
        <input type="number" id="year" v-model="searchYear" class="form-control" placeholder="YYYY" />
      </div>

      <!-- Event type -->
      <div class="form-group">
        <label for="eventType">{{ $t('event_type') }}</label>
        <select id="eventType" v-model="searchEventType" class="form-control">
          <option v-for="type in eventTypes" :key="type" :value="type">{{ type }}</option>
        </select>
      </div>
      
      <button type="submit" class="btn btn-evt-hv mt-0">{{ $t('search') }}</button>
    </form>

    <!-- Search results -->
    <div v-if="filteredEvents.length" class="my-4">
      <h3>{{ $t('search_results') }}</h3>
      <ul class="list-group">
        <li v-for="event in filteredEvents" :key="event.id" class="list-group-item mb-0">
          <h4>{{ event.name }}</h4>
          <p class="mb-1">{{ event.description }}</p>
          <p class="mb-1"><strong>{{ $t('event_start_date') }}:</strong> {{ new Date(event.startDate).toLocaleDateString() }}</p>
          <p class="mb-1"><strong>{{ $t('event_type') }}:</strong> {{ event.eventType }}</p>
        </li>
      </ul>
    </div>
    <div v-else class="mt-3">
      <h5>{{ $t('no_results') }}</h5>
    </div>
  </div>
</template>

<script>
import { EventType } from "@/interfaces/enums/EventType";
import { EventService } from "@/services/EventService";
import { loadEvents } from '@/utils/LoadData';

export default {
  name: "EventSearch",
  data() {
    return {
      events: [],
      searchName: "",
      searchStartDate: "",
      filteredEvents: [],
      eventTypes: Object.values(EventType),
    };
  },
  async created() {
    await this.fetchEvents();
  },
  methods: {
    async fetchEvents() {
      try {
        this.events = await EventService.getAllEvents();
      } catch (error) {
        this.events = loadEvents();
      }
    },
    handleSearch() {
      this.filteredEvents = this.events.filter(event => {
        const matchesName = this.searchName ? event.name.toLowerCase().includes(this.searchName.toLowerCase()) : true;
        const matchesEventType = this.searchEventType ? event.eventType === this.searchEventType : true;
        const matchesYear = this.searchYear ? new Date(event.startDate).getFullYear() === parseInt(this.searchYear) : true;
        return matchesName && matchesEventType && matchesYear;
      });
    },
  },
};
</script>

<style scoped>
.event-search-container {
  max-width: 600px;
  margin: 0 auto;
}

.form-group {
  margin-bottom: 1rem;
}

.btn {
  margin-top: 1rem;
}

.list-group-item {
  margin-bottom: 0.5rem;
}

.list-group-item h4 {
  margin-bottom: 0.5rem;
}
</style>
