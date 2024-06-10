<template>
  <PopupGenerator :isOpen="isOpen" @close="closePopup">
    <template v-slot:title>
      <h2>Login</h2>
    </template>
    <template v-slot:form>
      <form @submit.prevent="handleLogin">
        <div class="form-group mb-2">
          <label class="d-block mb-1" for="username">Username</label>
          <input class="w-100" type="text" id="username" v-model="username" required />
        </div>
        <div class="form-group mb-3">
          <label class="d-block mb-1" for="password">Password</label>
          <input class="w-100" type="password" id="password" v-model="password" required />
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
      </form>
    </template>
  </PopupGenerator>
</template>

<script>
import PopupGenerator from '@/components/generators/PopupGenerator.vue';

export default {
  components: {
    PopupGenerator
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
      password: ''
    };
  },
  methods: {
    closePopup() {
      this.$emit('close');
    },
    handleLogin() {
      // Handle login logic here
      alert(`Username: ${this.username}, Password: ${this.password}`);
      this.closePopup();
    }
  }
};
</script>
