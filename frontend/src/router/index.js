import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/auth/LoginView.vue';
import SignupView from '@/views/auth/SignupView.vue';
import NotFoundView from '@/views/NotFoundView.vue';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            redirect: '/login',
        },
        {
            path: '/login',
            name: 'login',
            component: LoginView
        },
        {
            path: '/signup',
            name: 'signup',
            component: SignupView
        },
        {
            path: '/:notFound(.*)',
            component: NotFoundView,
        },
    ]
})

export default router
