
import { createI18n } from 'vue-i18n';
// import es from '@/locales/global/es.json';
import en from '@/locales/global/en.json';

const i18n = createI18n({
  locale: 'en',
  messages: {
    en,
    // es,
  }
});

export function loadLangConstants(locale: string, module: string) {
  return import(`@/locales/${module}/${locale}.json`).then(messages => {
    i18n.global.setLocaleMessage(locale, {
      ...i18n.global.getLocaleMessage(locale),
      ...messages.default
    });
  });
}

export default i18n;
