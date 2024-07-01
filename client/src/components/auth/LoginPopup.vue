<template>
  <LangLoadSpinner :modules="modules" />
  <PopupGenerator :isOpen="isOpen" @close="closePopup" :langModules="modules">
    <template v-slot:title>
      <h2>{{ $t('login') }}</h2>
    </template>
    <template v-slot:form>
      <form @submit.prevent="handleLogin">
        <div class="form-group mb-2">
          <label class="d-block mb-1" for="username">{{ $t('username') }}<span class="sup obl">*</span></label>
          <input class="w-100 form-control" type="text" id="username" v-model="username" required />
        </div>
        <div class="form-group mb-3">
          <label class="d-block mb-1" for="password">{{ $t('password') }}<span class="sup obl">*</span></label>
          <input class="w-100 form-control" type="password" id="password" v-model="password" required />
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-evt" v-if="!isLoading">{{ $t('login') }}</button>
        </div>
      </form>
      <div v-if="isLoading" class="text-center">
        <AuthSpinner />
      </div>
    </template>
  </PopupGenerator>
</template>

<script>
import PopupGenerator from '@/components/generators/PopupGenerator.vue';
import LangLoadSpinner from '@/components/LangLoadSpinner.vue';
import AuthSpinner from '@/components/auth/AuthSpinner.vue';
import UserService from '@/services/UserService';

export default {
  components: {
    PopupGenerator,
    LangLoadSpinner,
    AuthSpinner,
  },
  props: {
    isOpen: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      username: '',
      password: '',
      isLoading: false,
    };
  },
  methods: {
    closePopup() {
      this.username = '';
      this.password = '';
      this.$emit('close');
    },
    async handleLogin() {
      this.isLoading = true;
      try {
        await UserService.login(this.username, this.password);
        this.emitLogin();
      } catch {
        try {
          await UserService.refreshToken();
          await UserService.login(this.username, this.password);
          this.emitLogin();
        } catch {
          this.emitLogin();
        }
      }
      this.closePopup();
    },
    emitLogin() {
      localStorage.setItem('isLoggedIn', 'true');
      window.location.reload()
      this.closePopup();
    },
  },
  setup() {
    const modules = ['user'];
    return {
      modules,
    }
  }
};
</script>
