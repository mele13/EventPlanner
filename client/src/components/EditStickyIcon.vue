<template>
  <div class="sticky-icon">
    <div class="icon-container" @click="$emit('click')">
      <i-ic:baseline-edit />
    </div>
  </div>
</template>

<style scoped>
.sticky-icon {
  position: fixed;
  bottom: 10%;
  right: 10px;
  transform: translateY(50%);
  z-index: 1000;
  cursor: pointer;
}

.icon-container {
  width: 40px;
  height: 40px;
  background-color: var(--color-white05);
  border: 2px solid var(--color-warning);
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.icon-container i {
  color: var(--color-white);
  font-size: 20px;
}
</style>
