import './assets/main.css'

import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
// import VueGoogleMaps from '@fawmi/vue-google-maps';

import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import FlagIcon from 'vue-flag-icon';

import 'bootstrap/dist/css/bootstrap.css';
import i18n from '@/plugins/i18n';
import store from './plugins/store';

const app = createApp(App);

app.use(router);
app.use(i18n);
app.use(store);
app.use(FlagIcon);
// app.use(VueGoogleMaps, { load: { key: import.meta.env.VITE_GOOGLE_MAPS_API_KEY  } });
app.component('VueDatePicker', VueDatePicker);

app.mount('#app');
