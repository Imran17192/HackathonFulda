import { defineStore } from 'pinia'
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router/index.js';
import { useSnackbarStore } from '@/stores/snackbar.js';

export const useAuthStore = defineStore('auth', () => {
    const isAuthenticated = ref(false)
    const isLoggingIn = ref(false)
    const isSigningUp = ref(false)

    const { showSnackbar } = useSnackbarStore()

    async function login(email, password) {
        isLoggingIn.value = true
        const url = 'http://localhost:8080/api/auth/login'
        try {
            const response = await axios.post(url, {
                email,
                password,
            })
            console.log('Login response:', response)

            if (200 === response?.status) {
                isAuthenticated.value = true
                localStorage.setItem('isAuthenticated', isAuthenticated.value)
                showSnackbar('Login successful !', 'success')
                router.replace('/profile-view').then(r => r)
            }
        } catch (error) {
            console.error('Login error:', error);
            showSnackbar(error?.response?.data?.error?.message || 'An error occurred during login.', 'error')
            // throw new Error(error?.response?.data?.error?.message || 'An error occurred during login.');
        } finally {
            isLoggingIn.value = false
        }
    }

    async function signup(formData) {
        isSigningUp.value = true
        const url = 'http://localhost:8080/api/auth/signup'
        try {
            const response = await axios.post(url, formData)
            console.log('Login response:', response)
            if (response?.status === 200) {
                await login(formData.email, formData.password)
                showSnackbar('Signup successful ! You are automatically logged in', 'success')
            }
        } catch (error) {
            showSnackbar(error?.response?.data?.error?.message || 'An error occurred during login.', 'error')
            // throw new Error(error?.response?.data?.error?.message || 'An error occurred during signup. Please try again later.');
        } finally {
            isSigningUp.value = false
        }
    }

    function autoLogin() {
        if (localStorage.getItem('isAuthenticated')) {
            isAuthenticated.value = true
        }
    }

    function logout() {
        isAuthenticated.value = false
        localStorage.removeItem('isAuthenticated')
        router.replace('/login').then(r => r)
        showSnackbar('You have been logged out.', 'success')
    }

    return {
        isAuthenticated,
        login,
        isLoggingIn,
        autoLogin,
        logout,
        signup,
        isSigningUp,
    }
})
