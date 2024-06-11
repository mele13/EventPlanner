
import { createI18n } from 'vue-i18n';
// import es from '@/locales/global/es.json';
import en from '@/locales/global/en.json';

const locales = ['en'];//, 'es', 'de'];
const i18n = createI18n({
  locale: 'en',
  messages: {
    en,
    // es,
  }
});

export async function loadLangConstants(modules: string | string[]) {
  if (!Array.isArray(modules)) modules = [modules];

  for (const module of modules) {
    for (const locale of locales) {
      const messages = await import(`@/locales/${module}/${locale}.json`);
      i18n.global.setLocaleMessage(locale, {
        ...i18n.global.getLocaleMessage(locale),
        ...messages.default
      });
    }
  }
}

export default i18n;
