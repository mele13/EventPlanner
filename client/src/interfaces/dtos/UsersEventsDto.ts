import type { EventType } from "../enums/EventType";
import type { UserEventRelationship } from "../enums/UserEventRelationship";

export interface UsersEventsDto {
  eventId: Number;
  userId: Number;
  relationship: UserEventRelationship;
  eventName: String;
  eventDescription: String;
  eventType: EventType;
}
