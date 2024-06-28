export interface VenueDto {
  id?: number;
  name: string;
  description: string;
  phone: string;
  addressId: number;
  image?: Array<string>;
}
