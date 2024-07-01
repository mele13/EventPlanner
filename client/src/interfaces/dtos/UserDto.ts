import { Role } from "@/interfaces/enums/Role";

export interface UpdateUser {
  name: string;
  surname: string;
  alias: string;
  phone: string;
}

export interface ChangePassword {
  currentPassword: string;
  newPassword: string;
  confirmationPassword: string;
}

export interface ChangeRole {
  id: number;
  role: Role;
}

export interface GetUser {
  id: number;
  name: string;
  username: string;
  email: string;
  alias: string;
  phone: string;
  role: Role;
}

export interface UserDto {
  id: number;
  name: string;
  username: string;
  email: string;
  alias: string;
  phone: string;
  role: Role;
}

export interface EventUserDto {
  id?: number;
  attending: boolean;
  surname: string;
  allergies: string;
  nicknamePreference: boolean;
  nickname: string;
  role: Role;
  eventId: number;
  userId: number;
  credentialsId?: number;
  menuId?: number;
  image?: ArrayBuffer;
}

export interface UserRegisterDto {
  name: string;
  surname: string;
  email: string;
  password: string;
  role: Role;
}

