import { sendRequest } from '@/api/Api';
import type { CommentDto } from '@/interfaces/dtos/CommentDto';

const COMMENTS_API_URL = '/api/comments';

export async function getAllComments(): Promise<CommentDto[]> {
  return await sendRequest("GET", COMMENTS_API_URL);
}

export async function createComment(request: CommentDto): Promise<CommentDto> {
  return await sendRequest("POST", COMMENTS_API_URL, request);
}

export async function updateComment(request: CommentDto): Promise<CommentDto> {
  return await sendRequest("PUT", COMMENTS_API_URL, request);
}

export async function deleteComment(id: number): Promise<void> {
  await sendRequest("DELETE", `${COMMENTS_API_URL}/${id}`);
}
