import { AttendanceType } from "@/interfaces/enums/AttendanceType";
import { CredentialType } from "@/interfaces/enums/CredentialType";
import { Lang } from "@/interfaces/enums/Lang";
import { Role } from "@/interfaces/enums/Role";

export function langToString(lang: Lang): string {
  switch (lang) {
    case Lang.ES:
      return 'lang_es';
    case Lang.DE:
      return 'lang_de';
    case Lang.FR:
      return 'lang_fr';
    case Lang.PT:
      return 'lang_pt';
    case Lang.EN:
      return 'lang_en';
  }
}

export function roleToString(role: Role): string {
  switch (role) {
    case Role.ADMIN:
      return 'role_admin';
    case Role.USER:
      return 'role_user';
    case Role.EVT_ADMIN:
      return 'role_evt_admin';
    case Role.EVT_OWNER:
      return 'role_evt_owner';
    case Role.EVT_COLLABORATOR:
      return 'role_evt_collaborator';
    case Role.EVT_ORGANIZER:
      return 'role_evt_organizer';
    case Role.EVT_VENDOR:
      return 'role_evt_vendor';
    case Role.EVT_USER:
      return 'role_evt_user';
  }
}

export function credentialTypeToString(credentialType: CredentialType): string {
  switch (credentialType) {
    case CredentialType.AUTH:
      return 'event_credential_type_auth';
    case CredentialType.ACCESS:
      return 'event_credential_type_access';
  }
}

export function attendanceTypeToString(attendanceType: AttendanceType): string {
  switch (attendanceType) {
    case AttendanceType.PHYSICAL:
      return 'phy_att'
    case AttendanceType.NONPHYSICAL:
        return 'nphy_att'
    case AttendanceType.ONLINE:
        return 'onl_att'
    case AttendanceType.TELEPHONIC:
        return 'tlf_att'
  }
}
