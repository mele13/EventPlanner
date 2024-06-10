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
        <RouterLink to="/" class="nav-item nav-link">About</RouterLink>
        <div class="nav-item dropdown">
          <button data-toggle="dropdown" class="nav-item nav-link dropdown-toggle">Services</button>
          <div class="dropdown-menu">
            <RouterLink to="/" class="dropdown-item">Web Design</RouterLink>
            <RouterLink to="/" class="dropdown-item">Web Development</RouterLink>
            <RouterLink to="/" class="dropdown-item">Graphic Design</RouterLink>
            <RouterLink to="/" class="dropdown-item">Digital Marketing</RouterLink>
          </div>
        </div>
        <RouterLink to="/" class="nav-item nav-link active">Pricing</RouterLink>
        <RouterLink to="/" class="nav-item nav-link">Blog</RouterLink>
        <RouterLink to="/" class="nav-item nav-link">Contact</RouterLink>
      </div>
      <form class="navbar-form form-inline">
        <div class="input-group search-box">
          <input type="text" id="search" class="form-control" placeholder="Search here..." />
          <div class="input-group-append">
            <span class="input-group-text h-100">
              <i class="fa fa-search" aria-hidden="true"></i>
            </span>
          </div>
        </div>
      </form>
      <!-- Right side -->
      <div class="ms-auto flex-nowrap">
        <div class="navbar-nav ml-auto action-buttons">
          <!-- User authenticated -->
          <div class="nav-item">
            <RouterLink to="/my_events" class="nav-item nav-link">{{ $t('my_events') }}</RouterLink>
          </div>
          <div class="nav-item">
            <button class="nav-link btn btn-link"><i-material-symbols:person /></button>
          </div>
          <!-- Auth -->
          <div class="nav-item">
            <button @click="openLoginPopup" class="nav-link btn btn-link">{{ $t('login') }}</button>
          </div>
          <div class="nav-item">
            <button @click="openSignupPopup" class="nav-link btn btn-link">{{ $t('signup') }}</button>
          </div>
        </div>
      </div>
    </div>
  </nav>
  <LoginPopup :isOpen="isLoginPopupOpen" @close="isLoginPopupOpen = false" contentWidth="50%" />
  <SignupPopup :isOpen="isSignupPopupOpen" @close="isSignupPopupOpen = false" />
</template>

<script lang="ts">
import LoginPopup from './auth/LoginPopup.vue';
import SignupPopup from './auth/SignupPopup.vue';

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
    }
  },
  mounted() {
    this.activeLink = window.location.hash;
  },
  created() {
    // document.addEventListener('click', this.closeDropdownOnClickOutside);
  },
  unmounted() {
    // document.removeEventListener('click', this.closeDropdownOnClickOutside);
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
    // closeDropdownOnClickOutside(e) {
    //   if (this.$refs.langDropdown !== e.target && !this.$refs.langDropdown.contains(e.target)) {
    //     this.showDropdown = false;
    //   }
    //   if (this.$refs.navbar !== e.target && !this.$refs.navbar.contains(e.target)) {
    //     this.isNavbarOpen = false;
    //   }
    // }
  },
  computed: {
    currentLanguage() {
      return this.$i18n.locale === 'en' ? 'gb' : 'es';
    }
  }
}
</script>

<style scoped></style>
