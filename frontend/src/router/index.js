import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/auth/LoginView.vue';
import SignupView from '@/views/auth/SignupView.vue';
import NotFoundView from '@/views/NotFoundView.vue';
import HomeView from "@/views/HomeView.vue";
import PostView from "@/views/PostView.vue";
import GraphView from '@/views/GraphView.vue';
import ProfileView from "@/views/ProfileView.vue";
import { useAuthStore } from '@/stores/auth.js';
import ShowPostView from '@/views/ShowPostView.vue';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            redirect: '/home',
        },
        {
            path: '/login',
            name: 'login',
            component: LoginView,
            meta: {
                requiresUnauth: true
            }
        },
        {
            path: '/signup',
            name: 'signup',
            component: SignupView,
            meta: {
                requiresUnauth: true
            }
        },
        {
            path: '/home',
            name: 'home',
            component: HomeView,
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/posts/:id',
            name: 'post',
            component: ShowPostView,
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/new-post',
            name: 'new-post',
            component: PostView
        },
        {
            path: '/graph',
            name: 'graph',
            component: GraphView
        },
        {
            path: '/profile-view',
            name: 'profile-view',
            component: ProfileView
        },
        {
            path: '/:notFound(.*)',
            component: NotFoundView,
        },
    ]
})

router.beforeEach((to, from) => {
    if (! useAuthStore().isAuthenticated && to.meta.requiresAuth) {
        return '/login'
    }
    if (useAuthStore().isAuthenticated && to.meta.requiresUnauth) {
        return '/'
    }
})

export default router
