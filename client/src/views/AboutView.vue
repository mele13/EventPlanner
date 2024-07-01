<template>
  <div class="container mb-5">
    <h2 class="mb-4">{{ $t('app_welcome') }}</h2>

    <!-- Description -->
    <p class="lead">{{ $t('description') }}</p>

    <!-- Features -->
    <div class="row mt-5">
      <div class="col-md-4 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <h5 class="card-title">{{ $t('create_events_tt') }}</h5>
            <p class="card-text">{{ $t('create_events') }}</p>
          </div>
        </div>
      </div>
      <div class="col-md-4 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <h5 class="card-title">{{ $t('discover_events_tt') }}</h5>
            <p class="card-text">{{ $t('discover_events') }}</p>
          </div>
        </div>
      </div>
      <div class="col-md-4 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <h5 class="card-title">{{ $t('connect_others_tt') }}</h5>
            <p class="card-text">{{ $t('connect_others') }}</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Call to action -->
    <div class="mt-4">
      <router-link to="/event" class="btn btn-evt btn-lg" @click="handleGetStarted">{{ $t('get_started') }}</router-link>
    </div>
  </div>
  <LoginPopup :isOpen="isLoginPopupOpen" @close="isLoginPopupOpen = false" @login-success="handleLoginSuccess" contentWidth="50%" />
</template>

<script>
import LoginPopup from '@/components/auth/LoginPopup.vue';

export default {
  name: 'EventPlannerInfo',
  components: {
    LoginPopup,
  },
  data() {
    return {
      isLoggedIn: localStorage.getItem('isLoggedIn') === 'true',
      isLoginPopupOpen: false,
    }
  },
  methods: {
    handleGetStarted() {
      if (this.isLoggedIn) {
        this.$router.push('/event');
      } else {
        this.isLoginPopupOpen = true;
      }
    },
    openLoginPopup() {
      this.isLoginPopupOpen = true;
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 50%;
  margin: auto;
  text-align: center;
}

.card {
  transition: transform 0.3s ease-in-out;
}

.card:hover {
  transform: translateY(-5px);
  border-color: var(--color-tertiary);
}
</style>
