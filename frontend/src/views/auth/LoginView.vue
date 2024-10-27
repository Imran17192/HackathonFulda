<script setup>
import { useFormHandler } from '@/composables/formHandler.js'; // Import your form handler
import { errorMessages } from '@/config/errorMessages.js'; // Import error messages
import BaseButtonComponent from '@/components/ui/BaseButtonComponent.vue';
import BaseCardComponent from '@/components/ui/BaseCardComponent.vue';
import * as yup from "yup";
import { useAuthStore } from "@/stores/auth.js";
import ErrorMessage from "@/components/ErrorMessage.vue";
import { useSnackbarStore } from '@/stores/snackbar.js';

const schema = yup.object({
    email: yup.string().required(errorMessages.required('Email')).email(errorMessages.type.email),
    password: yup.string().required(errorMessages.required('Password')).min(8)
});

const { snackbar } = useSnackbarStore();

const { fields, errors, submitForm } = useFormHandler(schema, onSuccess, onInvalidSubmit);

const { login } = useAuthStore();

function onSuccess(values) {
    login(values.email, values.password);
}

function onInvalidSubmit({ values, errors }) {
    console.log(values);
    console.log(errors);
}
</script>

<template>
<base-card-component class="mx-auto" width="300">
    <v-snackbar
        v-model="snackbar.visible"
        :timeout="snackbar.timeout"
        :color="snackbar.color"
    >
        {{ snackbar.message }}
    </v-snackbar>
    <v-form @submit.prevent="submitForm">
        <div class="d-flex flex-column ga-2">
            <div>
                <BaseTextFieldComponent
                    label="Email"
                    type="email"
                    v-model="fields['email']"
                    prepend-inner-icon="mdi-email"
                />
                <ErrorMessage v-if="errors.email" :message="errors.email"/>
            </div>

            <div>
                <BaseTextFieldComponent
                    label="Password"
                    type="password"
                    v-model="fields['password']"
                    prepend-inner-icon="mdi-lock"
                />
                <ErrorMessage v-if="errors.password" :message="errors.password"/>
            </div>
        </div>

        <div class="d-flex flex-column mt-5">
            <base-button-component
                type="submit"
                text="Login"
                :loading="false"
            ></base-button-component>
        </div>
    </v-form>
</base-card-component>
</template>