
import { createI18n } from 'vue-i18n';
import en from '@/locales/global/en.json';
import es from '@/locales/global/es.json';
import de from '@/locales/global/de.json';
import pt from '@/locales/global/pt.json';
import fr from '@/locales/global/fr.json';

const locales = ['en', 'es', 'de', 'pt', 'fr'];

function getInitialLocale() {
  const storedLocale = localStorage.getItem('lang');
  return storedLocale && locales.includes(storedLocale) ? storedLocale : 'en';
}

const i18n = createI18n({
  legacy: false,
  locale: getInitialLocale(),
  messages: {
    en,
    es,
    de,
    pt,
    fr
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

export function changeLanguage(i18n: any, locale: any) {
  i18n.locale = locale
  localStorage.setItem('lang', locale)
}


export default i18n;
