import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

import '@mdi/font/css/materialdesignicons.css'

import BaseCardComponent from '@/components/ui/BaseCardComponent.vue';
import BaseButtonComponent from '@/components/ui/BaseButtonComponent.vue';
import BaseTextFieldComponent from '@/components/ui/BaseTextFieldComponent.vue';

const app = createApp(App)
const vuetify = createVuetify({
    components,
    directives
})


app.use(createPinia())
app.use(router)
app.use(vuetify)
app.component('BaseCardComponent', BaseCardComponent)
app.component('BaseButtonComponent', BaseButtonComponent)
app.component('BaseTextFieldComponent', BaseTextFieldComponent)
app.mount('#app')
