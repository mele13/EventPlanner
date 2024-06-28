import { sendRequest } from '@/api/Api';
import type { CDateDto } from '@/interfaces/dtos/CDateDto';

const CDATES_API_URL = '/api/cdates';

export async function getAllCDates(): Promise<CDateDto[]> {
  return await sendRequest("GET", CDATES_API_URL);
}

export async function createCDate(request: CDateDto): Promise<CDateDto> {
  return await sendRequest("POST", CDATES_API_URL, request);
}

export async function updateCDate(request: CDateDto): Promise<CDateDto> {
  return await sendRequest("PUT", CDATES_API_URL, request);
}

export async function deleteCDate(id: number): Promise<void> {
  await sendRequest("DELETE", `${CDATES_API_URL}/${id}`);
}
