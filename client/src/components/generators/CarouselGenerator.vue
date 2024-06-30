<template>
  <div class="carousel position-relative overflow-hidden">
    <div
      v-for="(image, index) in photos"
      :key="index"
      :class="['slide', { active: index === slide }]"
    >
      <img :src="`/img/venues/${image}`" alt="" class="carousel-image rounded noresp05-border" />
    </div>
    <a class="prev pointer position-absolute" @click="prevSlide"><i-fa-solid:caret-left class="icon-big" /></a>
    <a class="next pointer position-absolute" @click="nextSlide"><i-fa-solid:caret-right class="icon-big" /></a>
  </div>
</template>

<script>
export default {
  data() {
    return {
      slide: 0
    }
  },
  props: {
    photos: {
      type: Array,
      required: true
    }
  },
  methods: {
    prevSlide() {
      this.slide = (this.slide - 1 + this.photos.length) % this.photos.length
    },
    nextSlide() {
      this.slide = (this.slide + 1) % this.photos.length
    }
  }
}
</script>

<style scoped>
.carousel {
  width: 100%;
}

.carousel .slide {
  display: none;
}

.carousel .slide.active {
  display: block;
}

.carousel img {
  max-width: 100%;
  height: auto;
}

.icon-big {
  font-size: 150%;
}

.prev,
.next {
  top: 50%;
  transform: translateY(-50%);
  padding: 1%;
  background-color: rgba(255, 255, 255, 0.5);
  border: 2px solid var(--color-tertiary);
  color: var(--color-tertiary);
  border-radius: 5px;
  z-index: 10;
}

.prev {
  left: 10px;
}

.next {
  right: 10px;
}
</style>
