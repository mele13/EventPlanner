import { sendRequest } from "@/api/Api";
import type { DiscountCodeDto } from "@/interfaces/dtos/DiscountCodeDto";

const DISCOUNT_CODES_API_URL = '/api/discount-codes';

export async function getAllDiscountCodes(): Promise<DiscountCodeDto[]> {
  return sendRequest('GET', DISCOUNT_CODES_API_URL);
}

export async function createDiscountCode(request: DiscountCodeDto): Promise<DiscountCodeDto> {
  return sendRequest('POST', DISCOUNT_CODES_API_URL, request);
}

export async function updateDiscountCode(request: DiscountCodeDto): Promise<DiscountCodeDto> {
  return sendRequest('PUT', DISCOUNT_CODES_API_URL, request);
}

export async function deleteDiscountCode(id: number): Promise<void> {
  await sendRequest('DELETE', `${DISCOUNT_CODES_API_URL}/${id}`);
}
