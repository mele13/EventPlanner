

import { sendRequest } from "@/api/Api";
import type { CredentialsAuthDto, CredentialsDto } from "@/interfaces/dtos/CredentialsDto";

const CREDENTIALS_AUTH_API_URL = '/api/credentials-auth';
const CREDENTIALS_API_URL = '/api/credentials';

export const CredentialsAuthService = {
  async getAllCredentialsAuth(): Promise<CredentialsAuthDto[]> {
    return sendRequest('GET', CREDENTIALS_AUTH_API_URL);
  },
  
  async createCredentialsAuth(request: CredentialsAuthDto): Promise<CredentialsAuthDto> {
    return sendRequest('POST', CREDENTIALS_AUTH_API_URL, request);
  },
  
  async updateCredentialsAuth(request: CredentialsAuthDto): Promise<CredentialsAuthDto> {
    return sendRequest('PUT', CREDENTIALS_AUTH_API_URL, request);
  },
  
  async deleteCredentialsAuth(id: number): Promise<void> {
    await sendRequest('DELETE', `${CREDENTIALS_AUTH_API_URL}/${id}`);
  },
};

export const CredentialsService = {
  async getAllCredentials(): Promise<CredentialsDto[]> {
    return sendRequest('GET', CREDENTIALS_API_URL);
  },
   async createCredentials(request: CredentialsDto): Promise<CredentialsDto> {
    return sendRequest('POST', CREDENTIALS_API_URL, request);
  },
  
  async updateCredentials(request: CredentialsDto): Promise<CredentialsDto> {
    return sendRequest('PUT', CREDENTIALS_API_URL, request);
  },
  
  async deleteCredentials(id: number): Promise<void> {
    await sendRequest('DELETE', `${CREDENTIALS_API_URL}/${id}`);
  },
};
