export interface EventMenuDto {
  id?: number;
  menuInfo: string;
  menuWarn: string;
  eventId: number;
  menuId: number;
}

export interface MenuDto {
  id?: number;
  appetizers: string;
  soussal: string;
  main: string;
  side: string;
  sorbet: string;
  type: Type;
  dessert: string;
  cocktails?: string[];
  drinks?: string[];
}

