export default interface AddressDto {
  id?: number;
  street: string;
  number: string;
  postalCode: string;
  state: string;
  addressDetails: string;
  cityId: number;
  countryId: number;
}
