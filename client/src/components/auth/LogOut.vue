<template>
  <div>
    <p>Logging out...</p>
  </div>
</template>

<script lang="ts">
export default {
  name: 'LogOut',
  mounted() {
    this.logout()
  },
  methods: {
    logout() {
      this.$store.dispatch('logout')
      this.$router.push('/login')
    }
  }
}
</script>
