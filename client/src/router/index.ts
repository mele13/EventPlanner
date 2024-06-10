import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import EventCreator from '@/views/EventCreator.vue';
import UserEventList from '@/views/UserEventList.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', component: HomeView },
    { path: '/event', component: EventCreator, meta: { requiresAuth: true } },
    { path: '/my_events', component: UserEventList, meta: { requiresAuth: true } },
    // { path: '/logout', component: LogOut },
    { path: '/:pathMatch(.*)', redirect: '/' }, // Any path
    // { path: '/admin', component: AdminView, meta: { requiresAuth: true } },
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
  // const role = localStorage.getItem('role')
  // const isLoggedIn = localStorage.getItem('isLoggedIn')
  // const requiresAuth = to.meta.requiresAuth

  // if (requiresAuth && !isLoggedIn) {
    // If authentication is required and user is not authenticated, redirect to /login
    // next('/login')
  // } else {
    // Allow navigation to other routes
    next();
  // }
});

export default router;
