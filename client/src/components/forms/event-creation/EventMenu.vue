<template>
  <div class="data-container w-75 mt-3">
    <div class="d-flex align-items-center mb-1">
      <i-map:food class="tt-icon" />
      <h4>{{ $t('event_menu_tt') }}</h4>
    </div>

    <!-- Type -->
    <div class="form-group py-1">
      <label for="menu-type">{{ $t('event_menu_type') }}<span class="sup obl">*</span></label>
      <input type="text" class="form-control" v-model="newType" />
    </div>

    <div class="d-flex align-items-center mb-1 mt-3">
      <i-mdi:fish-food-outline class="tt-icon" />
      <h4>{{ $t('event_menu_dishes') }}</h4>
    </div>

    <!-- Appetizers -->
    <div class="form-group py-1">
      <label for="appetizers">{{ $t('event_menu_appetizers') }}<span class="sup obl">*</span></label>
      <input type="text" class="form-control" v-model="newAppetizers" />
    </div>

    <!-- Soussal -->
    <div class="form-group py-1">
      <label for="soussal">{{ $t('event_menu_soussal') }}</label>
      <input type="text" class="form-control" v-model="newSoussal" />
    </div>

    <!-- Main -->
    <div class="form-group py-1">
      <label for="main">{{ $t('event_menu_main') }}<span class="sup obl">*</span></label>
      <input type="text" class="form-control" v-model="newMain" />
    </div>

    <!-- Side -->
    <div class="form-group py-1">
      <label for="side">{{ $t('event_menu_side') }}</label>
      <input type="text" class="form-control" v-model="newSide" />
    </div>

    <div class="d-flex align-items-center mb-1 mt-3">
      <i-ep:dessert class="tt-icon" />
      <h4>{{ $t('event_menu_other') }}</h4>
    </div>

    <!-- Sorbet -->
    <div class="form-group py-1">
      <label for="sorbet">{{ $t('event_menu_sorbet') }}</label>
      <input type="text" class="form-control" v-model="newSorbet" />
    </div>

    <!-- Dessert -->
    <div class="form-group py-1">
      <label for="dessert">{{ $t('event_menu_dessert') }}</label>
      <input type="text" class="form-control" v-model="newDessert" />
    </div>

    <div class="d-flex align-items-center mb-1 mt-3">
      <i-pepicons-pop:soft-drink class="tt-icon" />
      <h4>{{ $t('event_menu_beverages') }}</h4>
    </div>

    <!-- Cocktails -->
    <div class="form-group py-1">
      <label for="cocktails">{{ $t('event_menu_cocktail') }}</label>
      <input type="text" class="form-control" v-model="newCocktails" />
    </div>

    <!-- Drinks -->
    <div class="form-group py-1">
      <label for="drinks">{{ $t('event_menu_drink') }}<span class="sup obl">*</span></label>
      <input type="text" class="form-control" v-model="newDrinks" />
    </div>

    <div class="d-flex align-items-center mb-1 mt-3">
      <i-fluent:document-text-extract-24-regular class="tt-icon" />
      <h4>{{ $t('event_menu_extra') }}</h4>
    </div>

    <!-- Menu Info -->
    <div class="form-group py-1">
      <label for="menu-info">{{ $t('event_menu_info') }}</label>
      <input type="text" class="form-control" v-model="newMenuInfo" />
    </div>

    <!-- Menu Warning -->
    <div class="form-group py-1">
      <label for="menu-warn">{{ $t('event_menu_warning') }}</label>
      <textarea class="form-control" v-model="newMenuWarn"></textarea>
    </div>

    <!-- New menu button -->
    <div class="d-flex align-items-center mb-1 mt-3">
      <button class="btn btn-evt-hv" @click.prevent="addMenu">{{ $t('add') }}</button>
    </div>

    <ul class="list-group my-3">
      <li
        v-for="(menu, index) in menus"
        :key="index"
        class="list-group-item d-flex justify-content-between align-items-center"
      >
        <div>
          <strong>{{ $t('event_menu_type') }}:</strong> {{ menu.type }}
          <br />
          <strong>{{ $t('event_menu_appetizers') }}:</strong> {{ menu.appetizers }}
          <br />
          <strong>{{ $t('event_menu_soussal') }}:</strong> {{ menu.soussal }}
          <br />
          <strong>{{ $t('event_menu_main') }}:</strong> {{ menu.main }}
          <br />
          <strong>{{ $t('event_menu_side') }}:</strong> {{ menu.side }}
          <br />
          <strong>{{ $t('event_menu_sorbet') }}:</strong> {{ menu.sorbet }}
          <br />
          <strong>{{ $t('event_menu_dessert') }}:</strong> {{ menu.dessert }}
          <br />
          <strong>{{ $t('event_menu_cocktail') }}:</strong> {{ menu.cocktails }}
          <br />
          <strong>{{ $t('event_menu_drink') }}:</strong> {{ menu.drinks }}
          <br />
          <strong>{{ $t('event_menu_info') }}:</strong> {{ menu.menuInfo }}
          <br />
          <strong>{{ $t('event_menu_warning') }}:</strong> {{ menu.menuWarn }}
        </div>

        <button class="btn btn-sm btn-evt-hv" @click.prevent="removeMenu(index)">
          <i-iconoir:cancel />
        </button>
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'

interface Menu {
  menuInfo: string
  menuWarn: string
  appetizers: string
  soussal: string
  main: string
  side: string
  sorbet: string
  type: string
  dessert: string
  cocktails: string
  drinks: string
}

export default defineComponent({
  setup() {
    const newMenuInfo = ref<string>('');
    const newMenuWarn = ref<string>('');
    const newAppetizers = ref<string>('');
    const newSoussal = ref<string>('');
    const newMain = ref<string>('');
    const newSide = ref<string>('');
    const newSorbet = ref<string>('');
    const newDessert = ref<string>('');
    const newCocktails = ref<string>('');
    const newDrinks = ref<string>('');
    const menus = ref<Menu[]>([]);
    const newType = ref<string>('');

    const addMenu = () => {
      if (newAppetizers.value.trim() && newMain.value.trim() && newType.value.trim()) {
        menus.value.push({
          menuInfo: newMenuInfo.value.trim(),
          menuWarn: newMenuWarn.value.trim(),
          appetizers: newAppetizers.value.trim(),
          soussal: newSoussal.value.trim(),
          main: newMain.value.trim(),
          side: newSide.value.trim(),
          sorbet: newSorbet.value.trim(),
          type: newType.value.trim(),
          dessert: newDessert.value.trim(),
          cocktails: newCocktails.value.trim(),
          drinks: newDrinks.value.trim()
        })
        newMenuInfo.value = ''
        newMenuWarn.value = ''
        newAppetizers.value = ''
        newSoussal.value = ''
        newMain.value = ''
        newSide.value = ''
        newSorbet.value = ''
        newDessert.value = ''
        newCocktails.value = ''
        newDrinks.value = ''
      }
    }

    const removeMenu = (index: number) => {
      menus.value.splice(index, 1)
    }

    return {
      newMenuInfo,
      newMenuWarn,
      newAppetizers,
      newSoussal,
      newMain,
      newSide,
      newSorbet,
      newDessert,
      newCocktails,
      newDrinks,
      newType,
      menus,
      addMenu,
      removeMenu
    }
  }
})
</script>

<style scoped>
.menu-manager {
  margin-top: 2rem;
}
</style>
