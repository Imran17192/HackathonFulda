<script setup>
    import { RouterView } from 'vue-router'
    import TheFooter from '@/components/layout/TheFooter.vue';
    import { useAuthStore } from '@/stores/auth.js';
    import { storeToRefs } from 'pinia';
    import { onBeforeMount } from 'vue';

    const authStore = useAuthStore();
    const { isAuthenticated } = storeToRefs(authStore);
    const { logout, autoLogin } = authStore;

    onBeforeMount(() => {
        autoLogin();
    })
</script>

<template>
    <v-app>
        <v-app-bar class="text-white" :color="`var(--color-primary)`">
            <v-app-bar-title>
                <v-btn :to="isAuthenticated ? '/' : '/login'">Hercules Home</v-btn>
            </v-app-bar-title>
            <template v-if="isAuthenticated">
                <v-btn icon="mdi-account" to="/profile-view"></v-btn>
                <v-btn to="/login" @click="logout">
                    Logout
                </v-btn>
            </template>

            <template v-else>
                <v-btn to="/login">
                    Login
                </v-btn>
                <v-btn to="/signup">
                    Signup
                </v-btn>
            </template>
        </v-app-bar>

        <v-main>
            <v-container>
                <RouterView/>
            </v-container>
        </v-main>

        <v-footer class="d-flex flex-column-reverse pa-0">
            <TheFooter />
        </v-footer>
    </v-app>
</template>
