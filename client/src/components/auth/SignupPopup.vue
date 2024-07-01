<template>
  <LangLoadSpinner :modules="modules" />
  <PopupGenerator :isOpen="isOpen" @close="closePopup" :langModules="modules">
    <template v-slot:title>
      <h2>{{ $t('signup') }}</h2>
    </template>
    <template v-slot:form>
      <form @submit.prevent="handleLogin">
        <!-- Name -->
        <div class="form-group mb-2">
          <label class="d-block mb-1" for="name">{{ $t('name') }}<span class="sup obl">*</span></label>
          <input class="w-100 form-control" type="text" id="name" v-model="name" required />
        </div>
        <!-- Username -->
        <div class="form-group mb-2">
          <label class="d-block mb-1" for="username">{{ $t('username') }}<span class="sup obl">*</span></label>
          <input class="w-100 form-control" type="text" id="username" v-model="username" required />
        </div>
        <!-- Email -->
        <div class="form-group mb-2">
          <label class="d-block mb-1" for="email">{{ $t('email') }}<span class="sup obl">*</span></label>
          <input class="w-100 form-control" type="email" id="email" v-model="email" required />
        </div>
        <!-- Phone -->
        <div class="form-group mb-2">
          <label class="d-block mb-1" for="phone">{{ $t('phone') }}</label>
          <input class="w-100 form-control" type="text" id="phone" v-model="phone" />
        </div>
        <!-- Password -->
        <div class="form-group mb-3">
          <label class="d-block mb-1" for="password">{{ $t('password') }}<span class="sup obl">*</span></label>
          <input class="w-100 form-control" type="password" id="password" v-model="password" required />
        </div>
        <!-- Submit -->
        <div class="text-center">
          <button type="submit" class="btn btn-evt" v-if="!isLoading">{{ $t('signup') }}</button>
        </div>
      </form>
      <!-- Loading spinner -->
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
import { Role } from '@/interfaces/enums/Role';

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
      name: '',
      username: '',
      email: '',
      phone: '',
      password: '',
      isLoading: false,
    };
  },
  methods: {
    closePopup() {
      this.name = '';
      this.username = '';
      this.email = '';
      this.phone = '';
      this.password = '';
      this.$emit('close');
    },
    async handleLogin() {
      this.isLoading = true;
      try {
        await UserService.register({
          name: this.name,
          surname: this.surname,
          email: this.email,
          password: this.password,
          role: Role.USER,
        });
        this.emitSignupSuccess();
      } catch {
        this.emitSignupSuccess();
      }
      this.closePopup();
    },
    emitSignupSuccess() {
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
