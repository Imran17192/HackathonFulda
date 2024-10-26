import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/auth/LoginView.vue';
import SignupView from '@/views/auth/SignupView.vue';
import NotFoundView from '@/views/NotFoundView.vue';
import HomeView from "@/views/HomeView.vue";
import PostView from "@/views/PostView.vue";

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
          path: '/home',
          name: 'home',
          component: HomeView
        },
      {
            path: '/new-post',
            name: 'new-post',
            component: PostView
        },
        {
            path: '/:notFound(.*)',
            component: NotFoundView,
        },
    ]
})

export default router
