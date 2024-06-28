import { sendRequest } from "@/api/Api";
import type AddressDto from "@/interfaces/dtos/AddressDto";

const ADDRESS_API_URL = "/api/addresses";

const AddressService = {
  async getAddressById(id: number): Promise<AddressDto> {
    return await sendRequest("GET", `${ADDRESS_API_URL}/${id}`);
  },

  async createAddress(addressData: AddressDto): Promise<AddressDto> {
    return await sendRequest("POST", ADDRESS_API_URL, addressData);
  },

  async updateAddress(addressData: AddressDto): Promise<AddressDto> {
    return await sendRequest("PUT", ADDRESS_API_URL, addressData);
  },

  async deleteAddress(id: number): Promise<void> {
    await sendRequest("DELETE", `${ADDRESS_API_URL}/${id}`);
  },
};

export default AddressService;
