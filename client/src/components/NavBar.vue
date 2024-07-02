<template>
  <nav class="navbar navbar-expand-lg mx-3" ref="navbar">
    <div class="logo">
      <RouterLink to="/" class="navbar-nav bg-img">
        <img src="@/assets/logo_slogan.svg" alt="Logo" class="bg-img" />
      </RouterLink>
    </div>
    <button
      type="button"
      class="navbar-toggler collapsed"
      data-toggle="collapse"
      data-target="#navbarCollapse"
      aria-expanded="false"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Collection of nav links, forms, and other content for toggling -->
    <div id="navbarCollapse" class="navbar-collapse flex-grow-1 collapse">
      <!-- Left side -->
      <div class="navbar-nav">
        <RouterLink to="/" class="nav-item nav-link bg">{{ $t('home') }}</RouterLink>
        <RouterLink :to="{ path: '/', hash: '#about' }" class="nav-item nav-link bg">{{ $t('about') }}</RouterLink>
        <RouterLink :to="{ path: '/', hash: '#contact' }" class="nav-item nav-link bg">{{ $t('contact') }}</RouterLink>
      </div>
      
      <!-- Right side -->
      <div class="ms-auto flex-nowrap">
        <div class="navbar-nav ml-auto action-buttons">
          <!-- New event -->
          <div class="nav-item" v-if="isLoggedIn">
            <RouterLink to="/event" id="event" class="nav-item nav-link btn btn-evt bg">{{ $t('new_event') }}</RouterLink>
          </div>
          <!-- User's events -->
          <div class="nav-item" v-if="isLoggedIn">
            <RouterLink to="/my_events" class="nav-item nav-link bg">{{ $t('my_events') }}</RouterLink>
          </div>
          <!-- Language dropdown -->
          <div id="changeLang" class="dropdown" ref="langDropdown">
            <button class="nav-link dropdown-toggle bg" @click="toggleLang">
              <flag :iso="currentLanguage" />
            </button>
            <div
              v-if="showDropdown"
              :class="{ 'dropdown-content': true, show: showDropdown }"
              class="dropdown-menu p-1"
            >
              <button v-for="lang in languageOptions" :key="lang.locale" class="dropdown-item" @click="langChange(lang.locale)">
                <flag :iso="lang.flagIso" />{{ $t(lang.translationKey) }}
              </button>
            </div>
          </div>
          <!-- Profile -->
          <div class="nav-item" v-if="isLoggedIn">
            <RouterLink :to="'/profile/' + userId" class="nav-link btn btn-link bg"><i-material-symbols:person /></RouterLink>
          </div>
          <!-- Logout -->
          <div class="nav-item" v-if="isLoggedIn">
            <button class="nav-link btn btn-link bg" @click="handleLogout"><i-material-symbols:logout /></button>
          </div>
          <!-- Auth -->
          <div class="nav-item" v-if="!isLoggedIn">
            <button @click="openLoginPopup" class="nav-link btn btn-link">{{ $t('login') }}</button>
          </div>
          <div class="nav-item" v-if="!isLoggedIn">
            <button @click="openSignupPopup" class="nav-link btn btn-link">{{ $t('signup') }}</button>
          </div>
        </div>
      </div>
    </div>
  </nav>
  <LoginPopup :isOpen="isLoginPopupOpen" @close="isLoginPopupOpen = false" @login-success="handleLoginSuccess" contentWidth="50%" />
  <SignupPopup :isOpen="isSignupPopupOpen" @close="isSignupPopupOpen = false" />
</template>

<script lang="ts">
import LoginPopup from '@/components/auth/LoginPopup.vue';
import SignupPopup from '@/components/auth/SignupPopup.vue';
import { changeLanguage } from '@/plugins/i18n';

export default {
  components: {
    LoginPopup,
    SignupPopup,
  },
  data() {
    return {
      activeLink: null,
      showDropdown: false,
      isNavbarOpen: false,
      isLoginPopupOpen: false,
      isSignupPopupOpen: false,
      isLoggedIn: localStorage.getItem('isLoggedIn') === 'true',
      userId: localStorage.getItem('session') || 1,
      languageOptions: [
        { locale: 'en', flagIso: 'gb', translationKey: 'lang_en' },
        { locale: 'es', flagIso: 'es', translationKey: 'lang_es' },
        { locale: 'pt', flagIso: 'pt', translationKey: 'lang_pt' },
        { locale: 'fr', flagIso: 'fr', translationKey: 'lang_fr' },
        { locale: 'de', flagIso: 'de', translationKey: 'lang_de' }
      ],
    }
  },
  mounted() {
    this.activeLink = window.location.hash;
  },
  methods: {
    openLoginPopup() {
      this.isLoginPopupOpen = true;
    },
    openSignupPopup() {
      this.isSignupPopupOpen = true;
    },
    langChange(locale: any) {
      changeLanguage(this.$i18n, locale);
      this.showDropdown = false;
    },
    toggleLang() {
      this.showDropdown = !this.showDropdown;
    },
    toggleNavbar() {
      this.isNavbarOpen = !this.isNavbarOpen;
    },
    closeNavbar() {
      this.isNavbarOpen = false;
    },
    handleLoginSuccess() {
      this.isLoggedIn = true;
    },
    handleLogout() {
      localStorage.removeItem('isLoggedIn');
      this.$router.push('/logout');
      this.isLoggedIn = false;
    },
    getFlagIso(locale: string) {
      return locale == 'en' ? 'gb' : locale;
    },
  },
  computed: {
    currentLanguage() {
      return this.getFlagIso(this.$i18n.locale);
    },
  }
}
</script>

<style scoped>
.bg {
  font-size: 120%;
}

.bg-img {
  min-height: 55px;
}
</style>
