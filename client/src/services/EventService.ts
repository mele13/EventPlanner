import { sendRequest } from "@/api/Api";
import type { EventDto, EventStatistics } from "@/interfaces/dtos/EventDto";
import type { EventUserDto } from "@/interfaces/dtos/UserDTO";

const EVENT_API_URL = "/api/events";
const EVENT_USERS_API_URL = '/api/event-users';

export const EventService = {
  async getAllEvents(): Promise<EventDto[]> {
    return await sendRequest("GET", EVENT_API_URL);
  },

  async getEventById(id: number): Promise<EventDto> {
    return await sendRequest("GET", `${EVENT_API_URL}/${id}`);
  },

  async createEvent(eventData: EventDto): Promise<EventDto> {
    return await sendRequest("POST", EVENT_API_URL, eventData);
  },

  async updateEvent(eventData: EventDto): Promise<EventDto> {
    return await sendRequest("PUT", EVENT_API_URL, eventData);
  },

  async deleteEvent(id: number): Promise<void> {
    await sendRequest("DELETE", `${EVENT_API_URL}/${id}`);
  },

  async getEventStatistics(id: number): Promise<EventStatistics> {
    return await sendRequest("GET", `${EVENT_API_URL}/${id}/statistics`);
  },
};

export const EventUserService = {
  async getAllEventUsers(): Promise<EventUserDto[]> {
    return sendRequest('GET', EVENT_USERS_API_URL);
  },
  
  async createEventUser(request: EventUserDto): Promise<EventUserDto> {
    return sendRequest('POST', EVENT_USERS_API_URL, request);
  },
  
  async updateEventUser(request: EventUserDto): Promise<EventUserDto> {
    return sendRequest('PUT', EVENT_USERS_API_URL, request);
  },
  
  async deleteEventUser(id: number): Promise<void> {
    await sendRequest('DELETE', `${EVENT_USERS_API_URL}/${id}`);
  },
};
