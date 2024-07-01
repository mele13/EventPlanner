<template>
  <div class="container mt-5 mhvh-c d-flex justify-content-start flex-column w-25">
    <h2 v-if="user">{{ formattedUsername  }}{{ $t('profile') }}</h2>

    <div v-if="user" class="profile-details">
      <p><strong>{{ $t('name') }}:</strong> {{ user.name }}</p>
      <p><strong>{{ $t('email') }}:</strong> {{ user.email }}</p>
      <p><strong>{{ $t('phone') }}:</strong> {{ user.phone }}</p>
    </div>
    <div v-else>
      <p>{{ $t('loading') }}</p>
    </div>
  </div>
</template>

<script>
import UserService from "@/services/UserService";
import { loadUsers } from '@/utils/LoadData';
import { upperFirst } from '@/utils/StringModifier';

export default {
  name: "UserProfile",
  data() {
    return {
      user: null,
    };
  },
  async created() {
    const userId = this.$route.params.id;
    await this.fetchUser(userId);
  },
  methods: {
    async fetchUser(userId) {
      try {
        this.user = await UserService.getUser(userId);
      } catch {
        this.user = loadUsers().find(user => user.id === parseInt(userId));
      }
    },
  },
  computed: {
    formattedUsername() {
      return this.user ? upperFirst(this.user.username) : '';
    }
  }
};
</script>

<style scoped>
.mhvh-c {
  min-height: 62.2vh !important;
}
</style>
