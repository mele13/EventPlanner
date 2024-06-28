import { sendRequest } from "@/api/Api";
import type { EventMenuDto, MenuDto } from "@/interfaces/dtos/MenuDto";

const MENUS_API_URL = '/api/menus';
const EVENT_MENUS_API_URL = '/api/event-menus';

export const CityService = {
  async getAllEventMenus(): Promise<EventMenuDto[]> {
    return sendRequest('GET', EVENT_MENUS_API_URL);
  },
  
  async createEventMenu(request: EventMenuDto): Promise<EventMenuDto> {
    return sendRequest('POST', EVENT_MENUS_API_URL, request);
  },
  
  async updateEventMenu(request: EventMenuDto): Promise<EventMenuDto> {
    return sendRequest('PUT', EVENT_MENUS_API_URL, request);
  },
  
  async deleteEventMenu(id: number): Promise<void> {
    await sendRequest('DELETE', `${EVENT_MENUS_API_URL}/${id}`);
  },
};

export const CountryService = {
  async getAllMenus(): Promise<MenuDto[]> {
    return sendRequest('GET', MENUS_API_URL);
  },
  
  async createMenu(request: MenuDto): Promise<MenuDto> {
    return sendRequest('POST', MENUS_API_URL, request);
  },
  
  async updateMenu(request: MenuDto): Promise<MenuDto> {
    return sendRequest('PUT', MENUS_API_URL, request);
  },
  
  async deleteMenu(id: number): Promise<void> {
    await sendRequest('DELETE', `${MENUS_API_URL}/${id}`);
  },
};
