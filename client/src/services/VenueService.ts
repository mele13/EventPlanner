import { sendRequest } from '@/api/Api';
import type { VenueDto } from '@/interfaces/dtos/VenueDto';

const VENUES_API_URL = '/api/venues';

export async function getAllVenues(): Promise<VenueDto[]> {
  return await sendRequest("GET", VENUES_API_URL);
}

export async function createVenue(request: VenueDto): Promise<VenueDto> {
  return await sendRequest("POST", VENUES_API_URL, request);
}

export async function updateVenue(request: VenueDto): Promise<VenueDto> {
  return await sendRequest("PUT", VENUES_API_URL, request);
}

export async function deleteVenue(id: number): Promise<void> {
  await sendRequest("DELETE", `${VENUES_API_URL}/${id}`);
}
