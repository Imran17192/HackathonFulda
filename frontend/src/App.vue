<script setup>
    import { RouterView } from 'vue-router'
    import TheFooter from '@/components/layout/TheFooter.vue';
</script>

<template>
    <v-app>
        <v-app-bar class="text-white" :color="`var(--color-primary)`">
            <v-app-bar-title>
                <v-btn to="/" >Home</v-btn>
            </v-app-bar-title>
            <v-btn icon="mdi-account"></v-btn>
            <v-btn to="/login">
                Login
            </v-btn>
            <v-btn to="/signup">
                Signup
            </v-btn>
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
