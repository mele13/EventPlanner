<template>
  <div class="data-container w-75 mb-3">
    <!-- Event main data -->
    <div id="main-data">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-ci:main-component class="tt-icon smaller-icon" />
        <h4 class="mb-0">{{ $t('event_data_tt') }}</h4>
      </div>
      <!-- Event name -->
      <div class="form-group py-1">
        <label for="event-name"> {{ $t('event_name') }}<span class="sup obl">*</span> </label>
        <input type="text" class="form-control" id="event-name" />
      </div>

      <!-- Event description -->
      <div class="form-group py-1">
        <label for="event-description">
          {{ $t('event_description') }}<span class="sup obl">*</span>
        </label>
        <textarea class="form-control" id="event-description" rows="3"></textarea>
      </div>

      <!-- Language -->
      <div class="form-group py-1">
        <label for="event-lang"> {{ $t('event_lang') }}<span class="sup obl">*</span> </label>
        <select class="form-control" id="event-lang" v-model="selectedLang">
          <option v-for="lang in Object.values(Lang).filter(lang => allowedLanguages.includes(lang))" :key="lang">
            {{ $t(langToString(lang)) }}
          </option>
        </select>
      </div>

      <!-- Owner -->
      <Checkbox
        id="owner"
        :labelContent="$t('event_owner_q')"
        v-model="isOwnerChecked"
        @input="handleOwnerChecked"
      />
      <div class="form-group py-1" v-if="isOwnerChecked">
        <label for="event-owner">{{ $t('event_owner') }}</label>
        <select class="form-control" id="event-user" v-model="selectedOwner">
          <option value=""></option>
          <option v-for="user in users" :key="user.id" :value="user.id" class="">
            <span class="">{{ upperFirst(user.username) }}</span>
            <span class="">{{ user.email }}</span>
          </option>
        </select>
      </div>

      <!-- Live event -->
      <Checkbox
        id="live-evt"
        :labelContent="$t('event_live')"
        :loadTooltip="true"
        :tooltipContent="$t('event_live_h')"
        tooltipIconSize="80%"
      />
    </div>

    <!-- Event dates -->
    <div id="event-dates">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-ion:calendar-outline class="tt-icon"></i-ion:calendar-outline>
        <h4 class="mb-0">{{ $t('event_dates') }}</h4>
      </div>

      <div class="row">
        <!-- Start date -->
        <div class="col">
          <DatePicker
            :labelContent="$t('event_start_date')"
            :loadTooltip="false"
            :isRequired="true"
            v-model="startDate"
          />
        </div>
        <!-- End date -->
        <div class="col">
          <DatePicker
            :labelContent="$t('event_end_date')"
            :loadTooltip="true"
            :tooltipContent="$t('event_end_date_h')"
            v-model="endDate"
          />
        </div>
      </div>
    </div>

    <!-- Attendance -->
    <div id="attendance-live">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-fluent:people-community-add-28-regular class="tt-icon" />
        <h4 class="mb-0">{{ $t('event_attendance_tt') }}</h4>
      </div>

      <!-- Attendance -->
      <div class="form-group py-1">
        <label for="event-attendance">
          {{ $t('event_attendance') }}
          <span class="sup obl">*</span>
        </label>
        <select class="form-control" id="event-attendance" v-model="selectedAttendance">
            <option v-for="att in Object.values(AttendanceType)" :key="att">
              {{ $t(attendanceTypeToString(att)) }}
            </option>
          </select>
      </div>

      <!-- Attendance limit number -->
      <div class="form-group py-1">
        <label for="limit-num">
          {{ $t('event_att_limit') }}
          <TooltipHint :content="$t('event_att_limit_h')" iconSize="80%" />
        </label>
        <input type="text" class="form-control" id="limit-num" />
        <small class="form-text text-muted">{{ $t('event_att_limit_sm') }}</small>
      </div>
    </div>

    <!-- Edit limit, user edit limit & limit number (limited invitations) -->
    <div id="limitations">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-pajamas:time-out class="tt-icon smaller-icon" />
        <h4 class="mb-0">{{ $t('event_limits') }}</h4>
      </div>

      <div class="row">
        <!-- Event has a limit date -->
        <div class="col">
          <Checkbox
            id="edit-evt"
            :labelContent="$t('event_edit_limit')"
            :loadTooltip="true"
            :tooltipContent="`${$t('event_edit_limit_h')}<br>${$t('event_edit_limit_eg')}`"
            v-model="isEventLimitChecked"
            @input="handleEventLimitChecked"
          />
        </div>
        <!-- Event users can edit until limit date -->
        <div class="col">
          <Checkbox
            id="user-edit-evt"
            :labelContent="$t('event_edit_user_limit')"
            :loadTooltip="true"
            :tooltipContent="`${$t('event_edit_user_limit_h')}<br>${$t('event_edit_user_limit_eg')}`"
            v-model="isUserLimitChecked"
            @input="handleUserLimitChecked"
          />
        </div>
      </div>

      <div class="row">
        <!-- Edit limit date -->
        <div class="col" v-if="isEventLimitChecked">
          <DatePicker
            :labelContent="$t('event_limit_date')"
            :loadTooltip="true"
            :tooltipContent="$t('event_limit_date_h')"
            v-model="editLimitDate"
          />
        </div>
        <!-- User edit limit date -->
        <div class="col" v-if="isUserLimitChecked">
          <DatePicker
            :labelContent="$t('event_user_limit_date')"
            :loadTooltip="true"
            :tooltipContent="$t('event_user_limit_date_h')"
            v-model="userEditLimitDate"
          />
        </div>
      </div>
    </div>

    <!-- Cancellation limit date -->
    <Checkbox
      id="cancel-evt"
      :labelContent="$t('event_cancel_limit')"
      :loadTooltip="true"
      :tooltipContent="$t('event_cancel_limit_h')"
      v-model="isCancellationChecked"
      @input="handleCancellationChecked"
    />

    <!-- Cancellation limit date -->
    <div v-if="isCancellationChecked">
      <DatePicker
        :labelContent="$t('event_cancel_limit_date')"
        :loadTooltip="true"
        :tooltipContent="$t('event_cancel_limit_date_h')"
        v-model="cancelLimitDate"
      />
    </div>

    <!-- Access / Credentials -->
    <div id="access">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-ri:lock-2-line class="tt-icon" />
        <h4 class="mb-0">{{ $t('event_access') }}</h4>
      </div>

      <Checkbox
        id="edit-evt"
        :labelContent="$t('event_credentials')"
        :loadTooltip="true"
        :tooltipContent="$t('event_credentials_h')"
        v-model="isCredentialsChecked"
        @input="handleCredentialsChecked"
      />

      <div class="row" v-if="isCredentialsChecked">
        <!-- Credential type -->
        <div class="form-group py-1 col">
          <label for="credential-type">
            {{ $t('event_credential_type') }}
            <TooltipHint
              :content="`
              ${$t('event_credential_type_h')}
              <ul>
                <li><b>${$t('event_credential_type_auth')}</b>. ${$t('event_credential_type_auth_l')}<br>${$t('event_credential_type_auth_eg')}</li>
                <li><b>${$t('event_credential_type_access')}</b>. ${$t('event_credential_type_access_l')}<br>${$t('event_credential_type_access_eg')}</li>
                </ul>`"
            />
          </label>
          <select v-model="credentialType" class="form-control" id="credential-type">
            <option v-for="type in Object.values(CredentialType)" :key="type" :value="type">
              {{ $t(credentialTypeToString(type)) }}
            </option>
          </select>
        </div>

        <!-- Credential value -->
        <div class="form-group py-1 col">
          <label for="credential-value">
            {{ $t('event_credential_value') }}
            <TooltipHint :content="$t('event_credential_value_h')" />
          </label>
          <input v-model="credentialValue" type="text" class="form-control" id="credential-value" />
        </div>

        <!-- Credential role -->
        <div v-if="credentialType === 'AUTH'" class="form-group py-1">
          <label for="credential-role">
            {{ $t('event_credential_role') }}
            <TooltipHint :content="$t('event_credential_role_h')" />
          </label>
          <select class="form-control" id="credential-role" v-model="selectedCredRole">
            <option v-for="role in Object.values(Role).filter(role => role.toString().startsWith('EVT_'))" :key="role" :value="role">
              {{ $t(roleToString(role)) }}
            </option>
          </select>
        </div>
      </div>
    </div>

    <!-- Notifications -->
    <div id="access">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-material-symbols:notifications-active-outline class="tt-icon" />
        <h4 class="mb-0">{{ $t('event_notifications') }}</h4>
      </div>

      <Checkbox id="edit-evt" :labelContent="$t('event_notifications_tt')" v-model="isNotificationChecked" />
      
      <small class="form-text text-muted">
        {{ $t('event_notifications_h_tt') }}
        <ul class="mb-0">
          <li>{{ $t('event_notifications_h_1') }}</li>
          <li>{{ $t('event_notifications_h_2') }}</li>
          <li>{{ $t('event_notifications_h_3') }}</li>
          <li>{{ $t('event_notifications_h_4') }}</li>
        </ul>
        {{ $t('event_notifications_h_e') }}
      </small>
    </div>

    <!-- Tags -->
    <div id="access">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-mdi:tag-plus-outline class="tt-icon" />
        <h4 class="mb-0">{{ $t('event_tags') }}</h4>
      </div>

      <TagInput />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, type PropType } from 'vue';
import TooltipHint from '@/components/generators/TooltipHintGenerator.vue';
import DatePicker from '@/components/generators/DatePickerGenerator.vue';
import Checkbox from '@/components/generators/CheckboxGenerator.vue';
import TagInput from '@/components/generators/TagInputGenerator.vue';
import { upperFirst } from '@/utils/StringModifier';
import { langToString, roleToString, credentialTypeToString, attendanceTypeToString } from '@/utils/EnumModifier';
import { Lang } from '@/interfaces/enums/Lang';
import { Role } from '@/interfaces/enums/Role';
import { CredentialType } from '@/interfaces/enums/CredentialType';
import { AttendanceType } from '@/interfaces/enums/AttendanceType';
import type { UserDto } from '@/interfaces/dtos/UserDto';

export default defineComponent({
  components: {
    TooltipHint,
    DatePicker,
    Checkbox,
    TagInput,
  },
  props: {
    users: {
      type: Array as PropType<UserDto[]>,
      required: true
    }
  },
  setup() {
    const allowedLanguages = [Lang.EN, Lang.ES, Lang.DE, Lang.PT, Lang.FR];

    // DatePicker variables
    const startDate = ref<string>('');
    const endDate = ref<string>('');
    const editLimitDate = ref<string>('');
    const userEditLimitDate = ref<string>('');
    const cancelLimitDate = ref<string>('');

    // Checkbox variables
    const isEventLimitChecked = ref(false);
    const isUserLimitChecked = ref(false);
    const isCredentialsChecked = ref(false);
    const isCancellationChecked = ref(false);
    const isOwnerChecked = ref(false);
    const isNotificationChecked = ref(false);

    // Select variables
    const credentialType = ref<CredentialType | ''>('');
    const credentialValue = ref('');

    // Selected data
    const selectedOwner = ref<string | number>('');
    const selectedLang = ref<string>('');
    const selectedCredRole = ref<string>('');
    const selectedAttendance = ref<string>('');

    // Checkbox functions
    const handleEventLimitChecked = (value: boolean) => {
      isEventLimitChecked.value = value;
    }
    const handleUserLimitChecked = (value: boolean) => {
      isUserLimitChecked.value = value;
    }
    const handleCredentialsChecked = (value: boolean) => {
      isCredentialsChecked.value = value;
    }
    const handleCancellationChecked = (value: boolean) => {
      isCancellationChecked.value = value;
    }
    const handleOwnerChecked = (value: boolean) => {
      isOwnerChecked.value = value;
    }

    return {
      // DatePicker
      startDate,
      endDate,
      editLimitDate,
      userEditLimitDate,
      cancelLimitDate,
      // Checkbox
      isEventLimitChecked,
      isUserLimitChecked,
      isCredentialsChecked,
      isCancellationChecked,
      isOwnerChecked,
      isNotificationChecked,
      // Select
      credentialType,
      credentialValue,
      handleOwnerChecked,
      handleEventLimitChecked,
      handleUserLimitChecked,
      handleCredentialsChecked,
      handleCancellationChecked,
      // Modifiers
      upperFirst,
      langToString,
      roleToString,
      credentialTypeToString,
      attendanceTypeToString,
      // Enums
      Role,
      Lang,
      CredentialType,
      AttendanceType,
      // Data
      selectedOwner,
      selectedLang,
      selectedCredRole,
      selectedAttendance,
      allowedLanguages,
    }
  }
})
</script>

<style scoped>
.smaller-icon {
  font-size: 20px;
}

option span {
  flex: 1;
}
</style>
