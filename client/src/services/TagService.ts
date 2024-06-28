import { sendRequest } from "@/api/Api";
import type { TagDto } from "@/interfaces/dtos/TagDto";

const TAGS_API_URL = '/api/tags';

export async function getAllTags(): Promise<TagDto[]> {
  return sendRequest('GET', TAGS_API_URL);
}

export async function createTag(request: TagDto): Promise<TagDto> {
  return sendRequest('POST', TAGS_API_URL, request);
}

export async function updateTag(request: TagDto): Promise<TagDto> {
  return sendRequest('PUT', TAGS_API_URL, request);
}

export async function deleteTag(id: number): Promise<void> {
  await sendRequest('DELETE', `${TAGS_API_URL}/${id}`);
}
