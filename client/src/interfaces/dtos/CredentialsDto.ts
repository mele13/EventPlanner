import type { CredentialType } from "@/interfaces/enums/CredentialType";
import type { Role } from "@/interfaces/enums/Role";

export interface CredentialsAuthDto {
  id?: number;
  key: string;
  url: string;
  expiration: Date;
  role: Role;
  userId: number;
}

export interface CredentialsDto {
  id?: number;
  type: CredentialType;
  value: string;
}

