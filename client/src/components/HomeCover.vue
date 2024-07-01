<template>
  <div class="mb-5">
    <div class="cover">
      <div class="cover-overlay">
        <div class="container">
          <h1 class="cover-title">{{ $t(slogan) }}</h1>
          <p class="cover-message">{{ $t(message) }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomePage",
  data() {
    return {
      slogan: 'app_slogan',
      message: 'plan_events',
    };
  },
};
</script>

<style scoped>
.cover {
  height: 60vh;
  background-image: url('img/homepage_cover.jpg');
  background-size: cover;
  background-position: center;
  position: relative;
}

.cover-overlay {
  background-color: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  text-align: center;
  padding: 20px;
}

.cover-title {
  font-size: 3.5rem;
  margin-bottom: 1rem;
  color: #fff;
}

.cover-message {
  font-size: 1.5rem;
  margin-bottom: 2rem;
  color: #fff;
}

.btn-primary {
  background-color: #007bff;
  color: #fff;
  padding: 0.75rem 2rem;
  font-size: 1.2rem;
  border: none;
  border-radius: 5px;
  text-decoration: none;
  cursor: pointer;
}

.btn-primary:hover {
  background-color: #0056b3;
}

.btn-lg {
  font-size: 1.5rem;
}
</style>
