<template>
  <div class="tag-input d-flex flex-column">
    <div class="d-flex flex-wrap gap-2">
      <div
        v-for="(tag, index) in tags"
        :key="index"
        :class="['tag', tag.color, 'd-inline-flex', 'align-items-center', 'text-white']"
      >
        <Tag />
        {{ tag.text }}
        <Cancel class="remove-tag pointer" @click="removeTag(index)" />
      </div>
      <button class="btn btn-evt-border pointer" @click.prevent="openPopup"><b>+</b></button>
    </div>

    <PopupGenerator :isOpen="isPopupOpen" @close="closePopup" contentWidth="40%">
      <template v-slot:title>
        <div class="container">
          <h3>{{ $t('new_tag') }}</h3>

          <div class="form-group mb-1 d-flex flex-column">
            <label for="tag-name">{{ $t('add_tag_name') }}</label>
            <input type="text" class="form-control" v-model="newTag" id="tag-name" />
          </div>
          
          <div class="form-group">
            <label for="tag-color">{{ $t('add_tag_color') }}</label>
            <div class="d-flex flex-wrap w-100">
              <div
                v-for="color in tagColors"
                :key="color"
                :class="['color-option', `tag-${color}`]"
                class="d-flex align-items-center p-1 mb-1 pointer"
              >
                <input
                  class="pointer"
                  type="radio"
                  :id="color"
                  :value="color"
                  v-model="selectedColor"
                />
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-center">
            <button @click.prevent="addTagFromPopup" class="btn btn-evt-border pointer mt-1">
              {{ $t('add') }}
            </button>
          </div>
        </div>
      </template>
    </PopupGenerator>
  </div>
</template>

<script>
import PopupGenerator from './PopupGenerator.vue'

// Import icons
import Cancel from '~icons/iconoir/cancel'
import Tag from '~icons/mdi/tag-outline'

export default {
  name: 'TagInput',
  data() {
    return {
      newTag: '',
      selectedColor: '',
      isPopupOpen: false,
      newTagColor: 'tag-red',
      tags: [],
      tagColors: [
        'blue',
        'blue-green',
        'green',
        'yellow-green',
        'yellow',
        'yellow-orange',
        'orange',
        'red-orange',
        'red',
        'red-violet',
        'violet'
      ]
    }
  },
  components: {
    Cancel,
    Tag,
    PopupGenerator
  },
  methods: {
    openPopup() {
      this.isPopupOpen = true
    },
    closePopup() {
      this.isPopupOpen = false
      this.newTag = ''
      this.selectedColor = ''
    },
    addTagFromPopup() {
      if (this.newTag.trim() !== '' && this.selectedColor !== '') {
        this.tags.push({ text: this.newTag, color: `tag-${this.selectedColor}` })
        this.newTag = ''
        this.selectedColor = ''
        this.closePopup()
      }
    },
    removeTag(index) {
      this.tags.splice(index, 1)
    }
  }
}
</script>

<style scoped>
.tag-input {
  gap: 10px;
}

.tag {
  padding: 5px 10px;
  border-radius: 20px;
  font-size: 14px;
}

.remove-tag {
  margin-left: 5%;
}

select {
  padding: 8px;
  border: 1px solid white;
  border-radius: 4px;
}

.color-option {
  border-radius: 5px;
  margin-right: 1%;
  min-width: 10%;
}
</style>
