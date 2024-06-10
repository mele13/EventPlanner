
import { createStore } from "vuex";

export default createStore({
  state: {
    user: null,
  },
  mutations: {
    setSession(state, sessionData) {
      if (sessionData) {
        localStorage.setItem('isLoggedIn', 'true');
      } else {
        localStorage.removeItem('isLoggedIn');
      }
    },
  },
  actions: {
    login({ commit }, session) {
      commit('setSession', session);
    },
    logout({ commit }) {
      commit('setSession', false);
    },
  }
});
