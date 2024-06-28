import type { Currency } from "@/interfaces/enums/Currency";
import type { Lang } from "@/interfaces/enums/Lang";

export interface CityDto {
  id?: number;
  name: string;
  code: string;
  countryId: number;
}

export interface CountryDto {
  id?: number;
  code: string;
  lang: Lang;
  currency: Currency;
}
