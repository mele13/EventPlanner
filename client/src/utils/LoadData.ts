import type AddressDto from "@/interfaces/dtos/AddressDto";
import type { EventDto } from "@/interfaces/dtos/EventDto";
import type { CityDto, CountryDto } from "@/interfaces/dtos/LocationDto";
import type { UserDto } from "@/interfaces/dtos/UserDto";
import type { UsersEventsDto } from "@/interfaces/dtos/UsersEventsDto";
import type { VenueDto } from "@/interfaces/dtos/VenueDto";
import { AttendanceType } from "@/interfaces/enums/AttendanceType";
import { Currency } from "@/interfaces/enums/Currency";
import { EventState } from "@/interfaces/enums/EventState";
import { EventType } from "@/interfaces/enums/EventType";
import { Lang } from "@/interfaces/enums/Lang";
import { Role } from "@/interfaces/enums/Role";
import { UserEventRelationship } from "@/interfaces/enums/UserEventRelationship";

export function loadUsers(): UserDto[] {
  return [
    { id: 1, name: "Mele Sual", username: "mele13", email: "mele@example.com", alias: "Mele", phone: "1234007890", role: Role.ADMIN },
    { id: 1, name: "John Doe", username: "johndoe", email: "john@example.com", alias: "Johnny", phone: "1234567890", role: Role.USER },
    { id: 2, name: "Jane Smith", username: "janesmith", email: "jane@example.com", alias: "Janey", phone: "0987654321", role: Role.USER },
    { id: 3, name: "Alice Johnson", username: "alicej", email: "alice@example.com", alias: "Ally", phone: "1231231234", role: Role.USER },
    { id: 4, name: "Bob Brown", username: "bobb", email: "bob@example.com", alias: "Bobby", phone: "3213214321", role: Role.USER },
    { id: 5, name: "Charlie Green", username: "charlieg", email: "charlie@example.com", alias: "Chuck", phone: "5555555555", role: Role.USER },
  ];
}

export function loadVenues(): VenueDto[] {
  return [
    { id: 1, name: "Conference Hall", description: "A large hall suitable for conferences and events.", phone: "1234567890", addressId: 1, image: ["conference1.jpg", "conference2.jpg", "conference3.jpg"] },
    { id: 2, name: "Expo Center", description: "A spacious center for exhibitions and large gatherings.", phone: "0987654321", addressId: 2, image: ["expo1.jpg", "expo2.jpg", "expo3.jpg", "expo4.jpg"] },
    { id: 3, name: "Private estate", description: "A private estate for weddings, parties and gatherings.", phone: "1122334455", addressId: 3, image: ["estate1.jpg", "estate2.jpg", "estate3.jpg", "estate4.jpg", "estate5.jpeg", "estate6.jpg"] },
    { id: 4, name: "Outdoor Park", description: "A beautiful outdoor park for open-air events.", phone: "2233445566", addressId: 4, image: ["park1.jpg", "park2.png", "park3.jpg"] },
    { id: 5, name: "Downtown Plaza", description: "A plaza located in the heart of downtown.", phone: "3344556677", addressId: 5, image: ["plaza1.jpg", "plaza2.jpg"] },
    { id: 6, name: "Beachside Resort", description: "A resort by the beach, ideal for retreats.", phone: "4455667788", addressId: 6, image: ["beach1.jpg", "beach2.jpg"] },
  ];
}

export function loadAddress(): AddressDto[] {
  return [
    { id: 1, street: "Main St", number: "123", postalCode: "12345", state: "CA", addressDetails: "Near the park", cityId: 1, countryId: 188 },
    { id: 2, street: "Elm St", number: "456", postalCode: "67890", state: "TX", addressDetails: "Next to the mall", cityId: 2, countryId: 188 },
    { id: 3, street: "Pine St", number: "789", postalCode: "11223", state: "NY", addressDetails: "Across the school", cityId: 3, countryId: 188 },
    { id: 4, street: "Maple St", number: "101", postalCode: "44556", state: "FL", addressDetails: "Behind the stadium", cityId: 4, countryId: 188 },
    { id: 5, street: "Oak St", number: "202", postalCode: "33445", state: "NV", addressDetails: "Near the lake", cityId: 5, countryId: 188 },
    { id: 6, street: "Cedar St", number: "303", postalCode: "55667", state: "WA", addressDetails: "Next to the office", cityId: 6, countryId: 188 },
  ];
}

export function loadCities(): CityDto[] {
  return [
    { id: 1, name: "Los Angeles", code: "LA", countryId: 188 },
    { id: 2, name: "Houston", code: "HOU", countryId: 188 },
    { id: 3, name: "New York", code: "NY", countryId: 188 },
    { id: 4, name: "Miami", code: "MIA", countryId: 188 },
    { id: 5, name: "Las Vegas", code: "LV", countryId: 188 },
    { id: 6, name: "Seattle", code: "SEA", countryId: 188 },
  ];
}

export function loadEvent(): EventDto | undefined {
  const event: EventDto =  {
    id: 1,
    eventType: EventType.WEDDING,
    createdBy: 1,
    ownedBy: 1,
    name: "A & M wedding",
    description: "A sweet young couple's wedding.",
    lang: Lang.EN,
    startDate: new Date("2024-11-16T17:00:00"),
    endDate: new Date("2024-11-17T03:00:00"),
    attendanceType: AttendanceType.PHYSICAL,
    limitNumber: 100,
    editLimit: new Date("2024-11-15T23:59:59"),
    userEditLimit: new Date("2024-11-01T23:59:59"),
    cancellationLimit: new Date("2024-11-15T23:59:59"),
    notification: true,
    tagIds: [1, 2, 3],
    venueId: 3,
    addressId: 3,
    contact: "am.wedding.nov24@gmail.com",
    live: true,
    ticketPrice: 0,
    ticketCurrency: Currency.EUR,
    sellLimit: new Date("2099-01-01T23:59:59"),
    otherDatesIds: [],
    discountCodesIds: [],
    cancellation: null,
    state: EventState.ACTIVE,
    refundsPolicy: '',
    faqIds: [1, 2],
    menuIds: [1],
    eventUserIds: [1, 2],
    palette: ['#F9F6EE', '#8D453D', '#FA8072', '#FAA0A0', '#FFC107'],
    template: "EventTemplate1"
  };

  return event;
}

export function loadEvents(): EventDto[] {
  return [
    {
      id: 1, eventType: EventType.WEDDING, createdBy: 1, ownedBy: 1, name: "A & M wedding",
      description: "A sweet young couple's wedding.", lang: Lang.EN, startDate: new Date("2024-11-16T17:00:00"),
      endDate: new Date("2024-11-17T03:00:00"), attendanceType: AttendanceType.PHYSICAL, limitNumber: 100,
      editLimit: new Date("2024-11-15T23:59:59"), userEditLimit: new Date("2024-11-01T23:59:59"),
      cancellationLimit: new Date("2024-11-15T23:59:59"), notification: true, tagIds: [1, 2, 3], venueId: 3,
      addressId: 3, contact: "am.wedding.nov24@gmail.com", live: true, ticketPrice: 0, ticketCurrency: Currency.EUR,
      sellLimit: new Date("2099-01-01T23:59:59"), otherDatesIds: [], discountCodesIds: [], cancellation: null,
      state: EventState.ACTIVE, refundsPolicy: '', faqIds: [1, 2], menuIds: [1], eventUserIds: [1, 2],
      palette: ['#F9F6EE', '#8D453D', '#FA8072', '#FAA0A0', '#FFC107'], template: "EventTemplate1"
    },
    {
      id: 2, eventType: EventType.GAMING, createdBy: 1, ownedBy: 2, name: "Gaming Tournament",
      description: "A competitive gaming event.", lang: Lang.EN, startDate: new Date("2025-08-16T17:00:00"),
      endDate: new Date("2025-08-17T03:00:00"), attendanceType: AttendanceType.ONLINE, limitNumber: 50,
      editLimit: new Date("2025-08-15T23:59:59"), userEditLimit: new Date("2025-08-01T23:59:59"),
      cancellationLimit: new Date("2025-11-15T23:59:59"), notification: true, tagIds: [4, 5, 6], venueId: 2,
      addressId: 4, contact: "gaming.bb@gmail.com", live: true, ticketPrice: 0, ticketCurrency: Currency.EUR,
      sellLimit: new Date("2099-01-01T23:59:59"), otherDatesIds: [], discountCodesIds: [], cancellation: null,
      state: EventState.ACTIVE, refundsPolicy: '', faqIds: [1, 2], menuIds: [1], eventUserIds: [1, 2],
      palette: ['#F9F6EE', '#8D453D', '#FA8072', '#FAA0A0', '#FFC107'], template: "EventTemplate3"
    },
    {
      id: 3, eventType: EventType.HOLIDAY, createdBy: 3, ownedBy: 3, name: "Holiday Party",
      description: "A fun holiday gathering.", lang: Lang.EN, startDate: new Date("2024-12-23T17:00:00"),
      endDate: new Date("2024-12-17T03:00:00"), attendanceType: AttendanceType.PHYSICAL, limitNumber: 20,
      editLimit: new Date("2024-12-15T23:59:59"), userEditLimit: new Date("2024-12-01T23:59:59"),
      cancellationLimit: new Date("2024-12-15T23:59:59"), notification: false, tagIds: [9, 12, 8], venueId: 6,
      addressId: 4, contact: "holiday.aa@gmail.com", live: true, ticketPrice: 0, ticketCurrency: Currency.EUR,
      sellLimit: new Date("2099-01-01T23:59:59"), otherDatesIds: [], discountCodesIds: [], cancellation: null,
      state: EventState.ACTIVE, refundsPolicy: '', faqIds: [1, 2], menuIds: [1], eventUserIds: [1, 2],
      palette: ['#F9F6EE', '#8D453D', '#FA8072', '#FAA0A0', '#FFC107'], template: "EventTemplate2"
    }
  ];
}

export function loadUserEvents(): UsersEventsDto[] {
  return [
    { eventId: 1, userId: 1, relationship: UserEventRelationship.MANAGES, eventName: "A & M wedding",
    eventDescription: "A sweet young couple's wedding.", eventType: EventType.WEDDING },
    { eventId: 2, userId: 1, relationship: UserEventRelationship.PARTICIPATES, eventName: "Gaming Tournament",
    eventDescription: "A competitive gaming event.", eventType: EventType.GAMING },
    { eventId: 3, userId: 1, relationship: UserEventRelationship.PARTICIPATES, eventName: "Holiday Party",
    eventDescription: "A fun holiday gathering.", eventType: EventType.HOLIDAY },
  ];
}

export function loadCountries(): CountryDto[] {
  return [
    { id: 1,   code: "AF", lang: Lang.PS,  currency: Currency.AFN },
    { id: 2,   code: "AL", lang: Lang.SQ,  currency: Currency.ALL },
    { id: 3,   code: "DZ", lang: Lang.AR,  currency: Currency.DZD },
    { id: 4,   code: "AS", lang: Lang.EN,  currency: Currency.USD },
    { id: 5,   code: "AD", lang: Lang.CA,  currency: Currency.EUR },
    { id: 6,   code: "AO", lang: Lang.PT,  currency: Currency.AOA },
    { id: 7,   code: "AI", lang: Lang.EN,  currency: Currency.XCD },
    { id: 8,   code: "AG", lang: Lang.EN,  currency: Currency.XCD },
    { id: 9,   code: "AR", lang: Lang.ES,  currency: Currency.ARS },
    { id: 10,  code: "AM", lang: Lang.HY,  currency: Currency.AMD },
    { id: 11,  code: "AW", lang: Lang.NL,  currency: Currency.AWG },
    { id: 12,  code: "AU", lang: Lang.EN,  currency: Currency.AUD },
    { id: 13,  code: "AT", lang: Lang.DE,  currency: Currency.EUR },
    { id: 14,  code: "AZ", lang: Lang.AZ,  currency: Currency.AZN },
    { id: 15,  code: "BS", lang: Lang.EN,  currency: Currency.BSD },
    { id: 16,  code: "BH", lang: Lang.AR,  currency: Currency.BHD },
    { id: 18,  code: "BB", lang: Lang.EN,  currency: Currency.BBD },
    { id: 19,  code: "BY", lang: Lang.BE,  currency: Currency.BYN },
    { id: 20,  code: "BE", lang: Lang.NL,  currency: Currency.EUR },
    { id: 21,  code: "BZ", lang: Lang.EN,  currency: Currency.BZD },
    { id: 22,  code: "BJ", lang: Lang.FR,  currency: Currency.XOF },
    { id: 23,  code: "BM", lang: Lang.EN,  currency: Currency.BMD },
    { id: 24,  code: "BT", lang: Lang.DZ,  currency: Currency.BTN },
    { id: 25,  code: "BO", lang: Lang.ES,  currency: Currency.BOB },
    { id: 26,  code: "BA", lang: Lang.BS,  currency: Currency.BAM },
    { id: 27,  code: "BW", lang: Lang.EN,  currency: Currency.BWP },
    { id: 28,  code: "BR", lang: Lang.PT,  currency: Currency.BRL },
    { id: 29,  code: "BN", lang: Lang.MS,  currency: Currency.BND },
    { id: 30,  code: "BG", lang: Lang.BG,  currency: Currency.BGN },
    { id: 31,  code: "BF", lang: Lang.FR,  currency: Currency.XOF },
    { id: 32,  code: "BI", lang: Lang.FR,  currency: Currency.BIF },
    { id: 33,  code: "CV", lang: Lang.PT,  currency: Currency.CVE },
    { id: 34,  code: "KH", lang: Lang.KM,  currency: Currency.KHR },
    { id: 35,  code: "CM", lang: Lang.FR,  currency: Currency.XAF },
    { id: 36,  code: "CA", lang: Lang.EN,  currency: Currency.CAD },
    { id: 37,  code: "KY", lang: Lang.EN,  currency: Currency.KYD },
    { id: 38,  code: "CF", lang: Lang.FR,  currency: Currency.XAF },
    { id: 39,  code: "TD", lang: Lang.FR,  currency: Currency.XAF },
    { id: 40,  code: "CL", lang: Lang.ES,  currency: Currency.CLP },
    { id: 41,  code: "CN", lang: Lang.ZH,  currency: Currency.CNY },
    { id: 42,  code: "CO", lang: Lang.ES,  currency: Currency.COP },
    { id: 43,  code: "KM", lang: Lang.AR,  currency: Currency.KMF },
    { id: 44,  code: "CG", lang: Lang.FR,  currency: Currency.XAF },
    { id: 45,  code: "CD", lang: Lang.FR,  currency: Currency.CDF },
    { id: 46,  code: "CR", lang: Lang.ES,  currency: Currency.CRC },
    { id: 47,  code: "CI", lang: Lang.FR,  currency: Currency.XOF },
    { id: 48,  code: "HR", lang: Lang.HR,  currency: Currency.HRK },
    { id: 49,  code: "CU", lang: Lang.ES,  currency: Currency.CUP },
    { id: 50,  code: "CY", lang: Lang.EL,  currency: Currency.EUR },
    { id: 51,  code: "CZ", lang: Lang.CS,  currency: Currency.CZK },
    { id: 52,  code: "DK", lang: Lang.DA,  currency: Currency.DKK },
    { id: 53,  code: "DJ", lang: Lang.FR,  currency: Currency.DJF },
    { id: 54,  code: "DM", lang: Lang.EN,  currency: Currency.XCD },
    { id: 55,  code: "DO", lang: Lang.ES,  currency: Currency.DOP },
    { id: 56,  code: "EC", lang: Lang.ES,  currency: Currency.USD },
    { id: 57,  code: "EG", lang: Lang.AR,  currency: Currency.EGP },
    { id: 58,  code: "SV", lang: Lang.ES,  currency: Currency.USD },
    { id: 59,  code: "GQ", lang: Lang.ES,  currency: Currency.XAF },
    { id: 60,  code: "ER", lang: Lang.TI,  currency: Currency.ERN },
    { id: 61,  code: "EE", lang: Lang.ET,  currency: Currency.EUR },
    { id: 62,  code: "SZ", lang: Lang.EN,  currency: Currency.SZL },
    { id: 63,  code: "ET", lang: Lang.AM,  currency: Currency.ETB },
    { id: 64,  code: "FJ", lang: Lang.EN,  currency: Currency.FJD },
    { id: 65,  code: "FI", lang: Lang.FI,  currency: Currency.EUR },
    { id: 66,  code: "FR", lang: Lang.FR,  currency: Currency.EUR },
    { id: 67,  code: "GA", lang: Lang.FR,  currency: Currency.XAF },
    { id: 68,  code: "GM", lang: Lang.EN,  currency: Currency.GMD },
    { id: 69,  code: "GE", lang: Lang.KA,  currency: Currency.GEL },
    { id: 70,  code: "DE", lang: Lang.DE,  currency: Currency.EUR },
    { id: 71,  code: "GH", lang: Lang.EN,  currency: Currency.GHS },
    { id: 72,  code: "GR", lang: Lang.EL,  currency: Currency.EUR },
    { id: 73,  code: "GD", lang: Lang.EN,  currency: Currency.XCD },
    { id: 74,  code: "GU", lang: Lang.EN,  currency: Currency.USD },
    { id: 75,  code: "GT", lang: Lang.ES,  currency: Currency.GTQ },
    { id: 76,  code: "GN", lang: Lang.FR,  currency: Currency.GNF },
    { id: 77,  code: "GW", lang: Lang.PT,  currency: Currency.XOF },
    { id: 78,  code: "GY", lang: Lang.EN,  currency: Currency.GYD },
    { id: 79,  code: "HT", lang: Lang.HT,  currency: Currency.HTG },
    { id: 80,  code: "HN", lang: Lang.ES,  currency: Currency.HNL },
    { id: 81,  code: "HU", lang: Lang.HU,  currency: Currency.HUF },
    { id: 82,  code: "IS", lang: Lang.IS,  currency: Currency.ISK },
    { id: 83,  code: "IN", lang: Lang.HI,  currency: Currency.INR },
    { id: 84,  code: "ID", lang: Lang.ID,  currency: Currency.IDR },
    { id: 85,  code: "IR", lang: Lang.FA,  currency: Currency.IRR },
    { id: 86,  code: "IQ", lang: Lang.AR,  currency: Currency.IQD },
    { id: 87,  code: "IE", lang: Lang.EN,  currency: Currency.EUR },
    { id: 88,  code: "IL", lang: Lang.HE,  currency: Currency.ILS },
    { id: 89,  code: "IT", lang: Lang.IT,  currency: Currency.EUR },
    { id: 90,  code: "JM", lang: Lang.EN,  currency: Currency.JMD },
    { id: 91,  code: "JP", lang: Lang.JA,  currency: Currency.JPY },
    { id: 92,  code: "JO", lang: Lang.AR,  currency: Currency.JOD },
    { id: 93,  code: "KZ", lang: Lang.KK,  currency: Currency.KZT },
    { id: 94,  code: "KE", lang: Lang.SW,  currency: Currency.KES },
    { id: 95,  code: "KI", lang: Lang.EN,  currency: Currency.AUD },
    { id: 96,  code: "KP", lang: Lang.KO,  currency: Currency.KPW },
    { id: 97,  code: "KR", lang: Lang.KO,  currency: Currency.KRW },
    { id: 98,  code: "KW", lang: Lang.AR,  currency: Currency.KWD },
    { id: 99,  code: "KG", lang: Lang.KY,  currency: Currency.KGS },
    { id: 100, code: "LA", lang: Lang.LO,  currency: Currency.LAK },
    { id: 101, code: "LV", lang: Lang.LV,  currency: Currency.EUR },
    { id: 102, code: "LB", lang: Lang.AR,  currency: Currency.LBP },
    { id: 103, code: "LS", lang: Lang.EN,  currency: Currency.LSL },
    { id: 104, code: "LR", lang: Lang.EN,  currency: Currency.LRD },
    { id: 105, code: "LY", lang: Lang.AR,  currency: Currency.LYD },
    { id: 106, code: "LI", lang: Lang.DE,  currency: Currency.CHF },
    { id: 107, code: "LT", lang: Lang.LT,  currency: Currency.EUR },
    { id: 108, code: "LU", lang: Lang.LB,  currency: Currency.EUR },
    { id: 109, code: "MG", lang: Lang.MG,  currency: Currency.MGA },
    { id: 110, code: "MW", lang: Lang.EN,  currency: Currency.MWK },
    { id: 111, code: "MY", lang: Lang.MS,  currency: Currency.MYR },
    { id: 112, code: "MV", lang: Lang.DV,  currency: Currency.MVR },
    { id: 113, code: "ML", lang: Lang.FR,  currency: Currency.XOF },
    { id: 114, code: "MT", lang: Lang.MT,  currency: Currency.EUR },
    { id: 115, code: "MH", lang: Lang.EN,  currency: Currency.USD },
    { id: 116, code: "MR", lang: Lang.AR,  currency: Currency.MRU },
    { id: 117, code: "MU", lang: Lang.MT,  currency: Currency.MUR },
    { id: 118, code: "MX", lang: Lang.ES,  currency: Currency.MXN },
    { id: 119, code: "FM", lang: Lang.EN,  currency: Currency.USD },
    { id: 120, code: "MD", lang: Lang.RO,  currency: Currency.MDL },
    { id: 121, code: "MC", lang: Lang.FR,  currency: Currency.EUR },
    { id: 122, code: "MN", lang: Lang.MN,  currency: Currency.MNT },
    { id: 123, code: "ME", lang: Lang.SR,  currency: Currency.EUR },
    { id: 124, code: "MA", lang: Lang.AR,  currency: Currency.MAD },
    { id: 125, code: "MZ", lang: Lang.PT,  currency: Currency.MZN },
    { id: 126, code: "MM", lang: Lang.MY,  currency: Currency.MMK },
    { id: 127, code: "NA", lang: Lang.EN,  currency: Currency.NAD },
    { id: 128, code: "NR", lang: Lang.EN,  currency: Currency.AUD },
    { id: 129, code: "NP", lang: Lang.NE,  currency: Currency.NPR },
    { id: 130, code: "NL", lang: Lang.NL,  currency: Currency.EUR },
    { id: 131, code: "NZ", lang: Lang.EN,  currency: Currency.NZD },
    { id: 132, code: "NI", lang: Lang.ES,  currency: Currency.NIO },
    { id: 133, code: "NE", lang: Lang.FR,  currency: Currency.XOF },
    { id: 134, code: "NG", lang: Lang.EN,  currency: Currency.NGN },
    { id: 135, code: "MK", lang: Lang.MK,  currency: Currency.MKD },
    { id: 136, code: "NO", lang: Lang.NO,  currency: Currency.NOK },
    { id: 137, code: "OM", lang: Lang.AR,  currency: Currency.OMR },
    { id: 138, code: "PK", lang: Lang.UR,  currency: Currency.PKR },
    { id: 139, code: "PW", lang: Lang.EN,  currency: Currency.USD },
    { id: 140, code: "PA", lang: Lang.ES,  currency: Currency.PAB },
    { id: 141, code: "PG", lang: Lang.EN,  currency: Currency.PGK },
    { id: 142, code: "PY", lang: Lang.ES,  currency: Currency.PYG },
    { id: 143, code: "PE", lang: Lang.ES,  currency: Currency.PEN },
    { id: 144, code: "PH", lang: Lang.FI,  currency: Currency.PHP },
    { id: 145, code: "PL", lang: Lang.PL,  currency: Currency.PLN },
    { id: 146, code: "PT", lang: Lang.PT,  currency: Currency.EUR },
    { id: 147, code: "QA", lang: Lang.AR,  currency: Currency.QAR },
    { id: 148, code: "RO", lang: Lang.RO,  currency: Currency.RON },
    { id: 149, code: "RU", lang: Lang.RU,  currency: Currency.RUB },
    { id: 150, code: "RW", lang: Lang.RW,  currency: Currency.RWF },
    { id: 151, code: "WS", lang: Lang.SM,  currency: Currency.WST },
    { id: 152, code: "SM", lang: Lang.IT,  currency: Currency.EUR },
    { id: 153, code: "SA", lang: Lang.AR,  currency: Currency.SAR },
    { id: 154, code: "SN", lang: Lang.FR,  currency: Currency.XOF },
    { id: 155, code: "RS", lang: Lang.SR,  currency: Currency.RSD },
    { id: 156, code: "SC", lang: Lang.FR,  currency: Currency.SCR },
    { id: 157, code: "SL", lang: Lang.EN,  currency: Currency.SLL },
    { id: 158, code: "SG", lang: Lang.EN,  currency: Currency.SGD },
    { id: 159, code: "SK", lang: Lang.SK,  currency: Currency.EUR },
    { id: 160, code: "SI", lang: Lang.SL,  currency: Currency.EUR },
    { id: 161, code: "SB", lang: Lang.EN,  currency: Currency.SBD },
    { id: 162, code: "SO", lang: Lang.SO,  currency: Currency.SOS },
    { id: 163, code: "ZA", lang: Lang.EN,  currency: Currency.ZAR },
    { id: 164, code: "SS", lang: Lang.EN,  currency: Currency.SSP },
    { id: 165, code: "ES", lang: Lang.ES,  currency: Currency.EUR },
    { id: 167, code: "SD", lang: Lang.AR,  currency: Currency.SDG },
    { id: 168, code: "SR", lang: Lang.NL,  currency: Currency.SRD },
    { id: 169, code: "SE", lang: Lang.SV,  currency: Currency.SEK },
    { id: 170, code: "CH", lang: Lang.DE,  currency: Currency.CHF },
    { id: 171, code: "SY", lang: Lang.AR,  currency: Currency.SYP },
    { id: 172, code: "TW", lang: Lang.ZH,  currency: Currency.TWD },
    { id: 173, code: "TJ", lang: Lang.TG,  currency: Currency.TJS },
    { id: 174, code: "TZ", lang: Lang.SW,  currency: Currency.TZS },
    { id: 177, code: "TG", lang: Lang.FR,  currency: Currency.XOF },
    { id: 178, code: "TO", lang: Lang.EN,  currency: Currency.TOP },
    { id: 179, code: "TT", lang: Lang.EN,  currency: Currency.TTD },
    { id: 180, code: "TN", lang: Lang.AR,  currency: Currency.TND },
    { id: 182, code: "TM", lang: Lang.TK,  currency: Currency.TMT },
    { id: 183, code: "TV", lang: Lang.EN,  currency: Currency.AUD },
    { id: 184, code: "UG", lang: Lang.EN,  currency: Currency.UGX },
    { id: 185, code: "UA", lang: Lang.UK,  currency: Currency.UAH },
    { id: 186, code: "AE", lang: Lang.AR,  currency: Currency.AED },
    { id: 187, code: "GB", lang: Lang.EN,  currency: Currency.GBP },
    { id: 188, code: "US", lang: Lang.EN,  currency: Currency.USD },
    { id: 189, code: "UY", lang: Lang.ES,  currency: Currency.UYU },
    { id: 190, code: "UZ", lang: Lang.UZ,  currency: Currency.UZS },
    { id: 191, code: "VU", lang: Lang.EN,  currency: Currency.VUV },
    { id: 192, code: "VA", lang: Lang.LA,  currency: Currency.EUR },
    { id: 193, code: "VE", lang: Lang.ES,  currency: Currency.VES },
    { id: 194, code: "VN", lang: Lang.VI,  currency: Currency.VND },
    { id: 195, code: "YE", lang: Lang.AR,  currency: Currency.YER },
    { id: 196, code: "ZM", lang: Lang.EN,  currency: Currency.ZMW },
    { id: 197, code: "ZW", lang: Lang.EN,  currency: Currency.ZWL },
  ]; 
}
