import { createStore } from 'vuex';

const store = createStore({
    state: {
        username: null, // Initial state for username
    },
    mutations: {
        setUsername(state, username) {
            state.username = username; // Mutation to set the username
        },
    },
    actions: {
        saveUserLogged({ commit }, username) {
            commit('setUsername', username); // Action to commit the mutation
        },
    },
    getters: {
        username: (state) => state.username, // Getter to access the username
    },
});

export default store;
