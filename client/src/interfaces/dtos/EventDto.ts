import type { AttendanceType } from "@/interfaces/enums/AttendanceType";
import type { Currency } from "@/interfaces/enums/Currency";
import type { EventState } from "@/interfaces/enums/EventState";
import type { EventType } from "@/interfaces/enums/EventType";
import type { Lang } from "@/interfaces/enums/Lang";

export interface EventDto {
  id?: number;
  eventType: EventType | null;
  createdBy?: number;
  ownedBy?: number;
  name: string;
  description: string;
  lang: Lang | null;
  startDate: Date | null;
  endDate: Date | null;
  attendanceType: AttendanceType | null;
  limitNumber?: number;
  editLimit: Date | null;
  userEditLimit: Date | null;
  cancellationLimit: Date | null;
  credentialsId?: number;
  notification: boolean;
  tagIds: number[];
  venueId?: number;
  addressId?: number;
  contact: string;
  live: boolean;
  ticketPrice?: number | null;
  ticketCurrency: Currency | null;
  sellLimit: Date | null;
  otherDatesIds: number[];
  discountCodesIds: number[];
  cancellation: Date | null;
  state: EventState | null;
  refundsPolicy: string;
  faqIds: number[];
  menuIds: number[];
  eventUserIds: number[];
  palette: string[];
  template: string;
}

export interface EventStatistics {
  eventId: number;
  attendeesCount: number;
  commentsCount: number;
  pageViews: number;
}
