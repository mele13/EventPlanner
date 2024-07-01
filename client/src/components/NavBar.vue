<template>
  <nav class="navbar navbar-expand-lg mx-3" ref="navbar">
    <div class="logo">
      <RouterLink to="/" class="navbar-nav">
        <img src="@/assets/logo_slogan.svg" alt="Logo" />
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
        <RouterLink to="/" class="nav-item nav-link">{{ $t('home') }}</RouterLink>
        <RouterLink :to="{ path: '/', hash: '#about' }" class="nav-item nav-link">{{ $t('about') }}</RouterLink>
        <RouterLink :to="{ path: '/', hash: '#contact' }" class="nav-item nav-link">{{ $t('contact') }}</RouterLink>
      </div>
      
      <!-- Right side -->
      <div class="ms-auto flex-nowrap">
        <div class="navbar-nav ml-auto action-buttons">
          <!-- New event -->
          <div class="nav-item" v-if="isLoggedIn">
            <RouterLink to="/event" id="event" class="nav-item nav-link btn btn-evt">{{ $t('new_event') }}</RouterLink>
          </div>
          <!-- User authenticated -->
          <div class="nav-item" v-if="isLoggedIn">
            <RouterLink to="/my_events" class="nav-item nav-link">{{ $t('my_events') }}</RouterLink>
          </div>
          <div class="nav-item" v-if="isLoggedIn">
            <RouterLink :to="'/profile/' + userId" class="nav-link btn btn-link"><i-material-symbols:person /></RouterLink>
          </div>
          <div class="nav-item" v-if="isLoggedIn">
            <button class="nav-link btn btn-link" @click="handleLogout"><i-material-symbols:logout /></button>
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
      this.showDropdown = false;
    },
    toggleDropdown() {
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
    checkProfile() {},
  },
  computed: {
    currentLanguage() {
      return this.$i18n.locale === 'en' ? 'gb' : 'es';
    },
  }
}
</script>
