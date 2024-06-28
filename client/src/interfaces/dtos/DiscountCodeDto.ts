import type { DiscountType } from "../enums/DiscountType";

export interface DiscountCodeDto {
  id?: number;
  code: string;
  type: DiscountType;
  value: number;
  limited: boolean;
  limitDate?: Date;
  limitTimes?: number;
}
