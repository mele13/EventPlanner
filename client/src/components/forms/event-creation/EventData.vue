<template>
  <div class="data-container w-75">
    <!-- Event main data -->
    <div id="main-data">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-ci:main-component class="tt-icon smaller-icon" />
        <h4 class="mb-0">{{ $t('event_data_tt') }}</h4>
      </div>
      <!-- Event name -->
      <div class="form-group py-1">
        <label for="event-name">
          {{ $t('event_name') }}<span class="sup obl">*</span>
        </label>
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
        <label for="event-lang">
          {{ $t('event_lang') }}<span class="sup obl">*</span>
        </label>
        <select class="form-control" id="event-lang">
          <option></option>
          <option>en</option>
          <option>es</option>
        </select>
      </div>

      <!-- Owner -->
      <div class="form-group py-1">
        <label for="event-owner">{{ $t('event_owner') }}</label>
        <select class="form-control" id="event-user">
          <option></option>
          <option>user</option>
          <option>user</option>
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
    <div id="">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-ion:calendar-outline class="tt-icon"></i-ion:calendar-outline>
        <h4 class="mb-0">{{ $t('event_dates') }}</h4>
      </div>
      
      <div class="row">
        <!-- Start date -->
        <div class="col">
          <DatePicker :labelContent="$t('event_start_date')" :loadTooltip="false" :isRequired="true" v-model="startDate"/>
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
        <label for="event-lang">
          {{ $t('event_attendance') }}
          <span class="sup obl">*</span>
        </label>
        <select class="form-control" id="event-lang">
          <option></option>
          <option>{{ $t('phy_att') }}</option>
          <option>{{ $t('nphy_att') }}</option>
          <option>{{ $t('onl_att') }}</option>
          <option>{{ $t('tlf_att') }}</option>
        </select>
      </div>

      <!-- Attendance limit number -->
      <div class="form-group py-1">
        <label for="limit-num">
          {{ $t('event_att_limit') }}
          <TooltipHint :content="$t('event_att_limit_h')" iconSize="80%"/>
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
            <TooltipHint :content="`
              ${$t('event_credential_type_h')}
              <ul>
                <li><b>${$t('event_credential_type_auth')}</b>. ${$t('event_credential_type_auth_l')}<br>${$t('event_credential_type_auth_eg')}</li>
                <li><b>${$t('event_credential_type_access')}</b>. ${$t('event_credential_type_access_l')}<br>${$t('event_credential_type_access_eg')}</li>
              </ul>`"
            />
          </label>
          <select v-model="credentialType" class="form-control" id="credential-type">
            <option value="auth">{{ $t('event_credential_type_auth') }}</option>
            <option value="access">{{ $t('event_credential_type_access') }}</option>
          </select>
        </div>

        <!-- Credential value -->
        <div class="form-group py-1 col">
          <label for="credential-value">
            {{ $t('event_credential_value') }}
            <TooltipHint :content="$t('event_credential_value_h')"/>
          </label>
          <input v-model="credentialValue" type="text" class="form-control" id="credential-value" />
        </div>

        <!-- Credential role -->
        <div v-if="credentialType === 'auth'" class="form-group py-1">
          <label for="credential-role">
            {{ $t('event_credential_role') }}
            <TooltipHint :content="$t('event_credential_role_h')"/>
          </label>
          <input v-model="credentialRole" type="text" class="form-control" id="credential-role" />
          <!-- TODO: one credential per role type?? max users per role?? i can invite 10 admins, 70 normal users, etc -->
        </div>
      </div>
    </div>

    <!-- Notifications -->
    <div id="access">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-material-symbols:notifications-active-outline class="tt-icon" />
        <h4 class="mb-0">{{ $t('event_notifications') }}</h4>
      </div>

      <!-- TODO: Are notifications needed? | Notification per dates: start, end, etc -->
    </div>

    <!-- Tags -->
    <div id="access">
      <div class="d-flex align-items-center mt-3 mb-1">
        <i-mdi:tag-plus-outline class="tt-icon" />
        <h4 class="mb-0">{{ $t('event_tags') }}</h4>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import TooltipHint from '@/components/generators/TooltipHintGenerator.vue';
import DatePicker from '@/components/generators/DatePickerGenerator.vue';
import Checkbox from '@/components/generators/CheckboxGenerator.vue';

export default defineComponent ({
  components: {
    TooltipHint,
    DatePicker,
    Checkbox,
  },
  setup() {
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

    // Select variables
    const credentialType = ref('');
    const credentialValue = ref('');
    const credentialRole = ref('');

    // Checkbox functions
    const handleEventLimitChecked = (value: boolean) => {
      isEventLimitChecked.value = value
    }
    const handleUserLimitChecked = (value: boolean) => {
      isUserLimitChecked.value = value
    }
    const handleCredentialsChecked = (value: boolean) => {
      isCredentialsChecked.value = value
    }
    const handleCancellationChecked = (value: boolean) => {
      isCancellationChecked.value = value
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
      // Select
      credentialType,
      credentialValue,
      credentialRole,
      handleEventLimitChecked,
      handleUserLimitChecked,
      handleCredentialsChecked,
      handleCancellationChecked,
    }
  },
});
</script>

<style scoped>
.tt-icon {
  font-size: 24px;
  margin-right: 8px;
}

.smaller-icon {
  font-size: 20px;
}
</style>
