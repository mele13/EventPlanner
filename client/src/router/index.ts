import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import EventCreator from '@/views/EventCreatorView.vue';
import EventView from '@/views/EventView.vue';
import UserEventsView from '@/views/UserEventsView.vue';
import UserProfile from '@/views/UserProfileView.vue';
import LogOut from '@/components/auth/LogOut.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', component: HomeView },
    { path: '/event', component: EventCreator, meta: { requiresAuth: true } },
    { path: '/my_events', component: UserEventsView, meta: { requiresAuth: true } },
    { path: '/logout', component: LogOut },
    { path: '/profile/:id', component: UserProfile, meta: { requiresAuth: true } },
    { path: '/:pathMatch(.*)', redirect: '/' }, // Any path
    // { path: '/admin', component: AdminView, meta: { requiresAuth: true } },

    // Dynamic route for events
    { path: '/event/:eventType_:eventId/:eventName', component: EventView, meta: { requiresAuth: true } },
  ],
  scrollBehavior(to, _from, _savedPosition) {
    if (to.hash) {
      return {
        el: to.hash,
        behavior: 'smooth'
      }
    }
  }
});

router.beforeEach((to, from, next) => {
  const requiresAuth = to.meta.requiresAuth

  if (requiresAuth && !localStorage.getItem('isLoggedIn')) {
    // If authentication is required and user is not authenticated, redirect to /login
    next('/');
  } else if (requiresAuth && to.path === '/admin' && localStorage.getItem('role') !== 'ADMIN') {
    next('/');
  } else {
    // Allow navigation to other routes
    next();
  }
});

export default router;
