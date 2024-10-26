import { defineStore } from 'pinia';
import { reactive } from 'vue';


export const useSnackbarStore = defineStore('snackbar', () => {
    const DEFAULT_TIMEOUT = 5000;
    const DEFAULT_COLOR = 'success';

    const snackbar = reactive({
        visible: false,
        message: '',
        timeout: DEFAULT_TIMEOUT,
        color: DEFAULT_COLOR,
    });

    function showSnackbar(message = '', color = DEFAULT_COLOR) {
        snackbar.visible = true;
        snackbar.message = message;
        snackbar.color = color;
    }
    return {
        snackbar,
        showSnackbar,
    }
})
