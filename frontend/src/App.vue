<script setup>
    import { RouterView } from 'vue-router'
    import TheFooter from '@/components/layout/TheFooter.vue';
    import { useAuthStore } from '@/stores/auth.js';

    const { isAuthenticated } = useAuthStore();
</script>

<template>
    <v-app>
        <v-app-bar class="text-white" :color="`var(--color-primary)`">
            <v-app-bar-title>
                <v-btn to="/" >Home</v-btn>
            </v-app-bar-title>

            <template v-if="isAuthenticated">
                <v-btn icon="mdi-account"></v-btn>
                <v-btn to="/login">
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

        <v-footer class="d-flex flex-column pa-0">
            <TheFooter />
        </v-footer>
    </v-app>
</template>

<script>
import PostForm from './views/PostView.vue';

export default {
    components: {
        PostForm,
    },
};
</script>
