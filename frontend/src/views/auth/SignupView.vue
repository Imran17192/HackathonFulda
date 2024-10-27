<script setup>
import { storeToRefs } from 'pinia';
import * as yup from 'yup';
import ErrorMessage from '@/components/ErrorMessage.vue';
import { useFormHandler } from '@/composables/formHandler.js';
import { errorMessages } from '@/config/errorMessages.js';
import { useSnackbarStore } from '@/stores/snackbar.js';
import BaseCardComponent from '@/components/ui/BaseCardComponent.vue';
import BaseButtonComponent from '@/components/ui/BaseButtonComponent.vue';
import { useAuthStore } from '@/stores/auth.js';

const snackbarStore = useSnackbarStore();
const { snackbar } = storeToRefs(snackbarStore);

const schema = yup.object({
    firstName: yup.string().required(errorMessages.required('First name')),
    middleName: yup.string(),
    lastName: yup.string().required(errorMessages.required('Last name')),
    email: yup.string().required(errorMessages.required).email(errorMessages.type.email),
    phone: yup.string(),
    password: yup.string().required(errorMessages.required('Password')).min(8),
    confirmPassword: yup.string()
        .oneOf([yup.ref('password')], 'Passwords must match')
        .required(errorMessages.required('Please confirm password', true)),
});
const { fields, errors, submitForm } = useFormHandler(schema, onSuccess, onInvalidSubmit);

const authStore = useAuthStore();
const { isSigningUp } = storeToRefs(authStore);
const { signup } = authStore;

async function onSuccess(values) {
    await signup(values);
}

function onInvalidSubmit({ values, errors, results }) {
    console.log('errors:::'); // current form values
    console.log(values); // current form values
    console.log(errors); // a map of field names and their first error message
    console.log(results); // a detailed map of field names and their validation results
}
</script>

<template>
<div class="pa-4 text-center">
    <v-snackbar
        v-model="snackbar.visible"
        :timeout="snackbar.timeout"
        :color="snackbar.color"
    >
        {{ snackbar.message }}
    </v-snackbar>

    <base-card-component>
        <v-form @submit.prevent="submitForm">
            <v-row>
                <v-col cols="12" md="4">
                    <BaseTextFieldComponent label="First name*" v-model.trim="fields['firstName']"/>
                    <ErrorMessage v-if="errors.firstName" :message="errors.firstName"/>
                </v-col>

                <v-col cols="12" md="4">
                    <BaseTextFieldComponent label="Middle name" v-model.trim="fields['middleName']"/>
                </v-col>

                <v-col cols="12" md="4">
                    <BaseTextFieldComponent label="Last name*" v-model.trim="fields['lastName']"/>
                    <ErrorMessage v-if="errors.lastName" :message="errors.lastName"/>
                </v-col>
            </v-row>

            <v-row>
                <v-col cols="12" md="6">
                    <BaseTextFieldComponent
                        label="E-Mail*"
                        type="email"
                        v-model.trim="fields['email']"
                        prepend-inner-icon="mdi-email"
                    />
                    <ErrorMessage v-if="errors.email" :message="errors.email"/>
                </v-col>

                <v-col cols="12" md="6">
                    <BaseTextFieldComponent
                        label="Phone"
                        type="tel"
                        v-model.trim="fields['phone']"
                        prepend-inner-icon="mdi-phone"
                    />
                </v-col>
            </v-row>

            <v-row>
                <v-col cols="12" md="6">
                    <BaseTextFieldComponent
                        label="Password*"
                        type="password"
                        v-model="fields['password']"
                        prepend-inner-icon="mdi-lock"
                    />
                    <ErrorMessage v-if="errors.password" :message="errors.password"/>
                </v-col>

                <v-col cols="12" md="6">
                    <BaseTextFieldComponent
                        label="Confirm password*"
                        type="password"
                        v-model="fields['confirmPassword']"
                        prepend-inner-icon="mdi-lock"
                    />
                    <ErrorMessage v-if="errors.confirmPassword" :message="errors.confirmPassword"/>
                </v-col>
            </v-row>
            <small class="text-caption text-medium-emphasis">*indicates required field</small>
            <div class="d-flex flex-column mt-5">
                <base-button-component
                    type="submit"
                    text="Signup"
                    :loading="isSigningUp"
                ></base-button-component>
            </div>
        </v-form>
    </base-card-component>
</div>
</template>