

import { sendRequest } from "@/api/Api";
import type { CityDto, CountryDto } from "@/interfaces/dtos/LocationDto";

const CITIES_API_URL = '/api/cities';
const COUNTRIES_API_URL = '/api/countries';


export const CityService = {
  async getAllCities(): Promise<CityDto[]> {
    return await sendRequest("GET", CITIES_API_URL);
  },
  
  async createCity(request: CityDto): Promise<CityDto> {
    return await sendRequest("POST", CITIES_API_URL, request);
  },
  
  async updateCity(request: CityDto): Promise<CityDto> {
    return await sendRequest("PUT", CITIES_API_URL, request);
  },
  
  async deleteCity(id: number): Promise<void> {
    await sendRequest("DELETE", `${CITIES_API_URL}/${id}`);
  },
};

export const CountryService = {
  async getAllCountries(): Promise<CountryDto[]> {
    return await sendRequest("GET", COUNTRIES_API_URL);
  },
  
  async getCountryByName(name: string): Promise<CountryDto> {
    return await sendRequest("GET", `${COUNTRIES_API_URL}/${name}`);
  },
  
  async createCountry(request: CountryDto): Promise<CountryDto> {
    return await sendRequest("POST", COUNTRIES_API_URL, request);
  },
  
  async updateCountry(request: CountryDto): Promise<CountryDto> {
    return await sendRequest("PUT", COUNTRIES_API_URL, request);
  },
  
  async deleteCountry(id: number): Promise<void> {
    await sendRequest("DELETE", `${COUNTRIES_API_URL}/${id}`);
  },
};
