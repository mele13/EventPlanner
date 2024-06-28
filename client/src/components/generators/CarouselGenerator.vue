<template>
  <div class="carousel">
    <div
      v-for="(image, index) in photos"
      :key="index"
      :class="['slide', { active: index === slide }]"
    >
      <img :src="`/img/venues/${image}`" alt="" class="carousel-image rounded" />
    </div>
    <a class="prev" @click="prevSlide">&#10094;</a>
    <a class="next" @click="nextSlide">&#10095;</a>
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
  mounted() {
    console.log('images', this.photos)
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
  position: relative;
  overflow: hidden;
  width: 40%;
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

.prev,
.next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  padding: 16px;
  background-color: rgba(255, 255, 255, 0.5);
  border: 1px solid #ddd;
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
