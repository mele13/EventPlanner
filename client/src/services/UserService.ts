import { sendRequest } from "@/api/Api";
import type { ChangePassword, ChangeRole, GetUser, UpdateUser, UserDto, UserRegisterDto } from "@/interfaces/dtos/UserDto";
import type { UsersEventsDto } from "@/interfaces/dtos/UsersEventsDto";

const USER_API_URL = "/api/users";
const AUTH_API_URL = "/api/auth";

const UserService = {
  async deleteUser(id: number): Promise<void> {
    await sendRequest("DELETE", `${USER_API_URL}/${id}`);
  },

  async changePassword(request: ChangePassword): Promise<void> {
    await sendRequest("PATCH", `${USER_API_URL}`, request);
  },

  async updateUser(request: UpdateUser): Promise<void> {
    await sendRequest("PUT", `${USER_API_URL}`, request);
  },

  async getUser(id: number): Promise<GetUser> {
    return await sendRequest("GET", `${USER_API_URL}/${id}`);
  },

  async getUsers(): Promise<UserDto[]> {
    return await sendRequest("GET", `${USER_API_URL}`);
  },

  async changeRole(request: ChangeRole): Promise<void> {
    await sendRequest("PATCH", `${USER_API_URL}/change-role`, request);
  },

  async getUserEvents(): Promise<UsersEventsDto[]> {
    return await sendRequest("GET", `${USER_API_URL}/events`);
  },

  async login(username: string, password: string): Promise<void> {
    const response = await sendRequest("POST", `${AUTH_API_URL}/login`, { username, password });
    localStorage.setItem('token', response.token);
  },

  async refreshToken(): Promise<void> {
    const response = await sendRequest("POST", `${AUTH_API_URL}/refresh-token`);
    localStorage.setItem('token', response.token);
  },

  async register(request: UserRegisterDto): Promise<void> {
    return await sendRequest("GET", `${AUTH_API_URL}/register`, request);
  },
};

export default UserService;
