<script setup>
import * as yup from 'yup';
import ErrorMessage from '@/components/ErrorMessage.vue';
import { useFormHandler } from '@/composables/formHandler.js';
import { errorMessages } from '@/config/errorMessages.js';
import BaseButtonComponent from '@/components/ui/BaseButtonComponent.vue';
import BaseCardComponent from '@/components/ui/BaseCardComponent.vue';

const schema = yup.object({
    email: yup.string().required(errorMessages.required('Email')).email(errorMessages.type.email),
    password: yup.string().required(errorMessages.required('Password')),
});

const { fields, errors, submitForm } = useFormHandler(schema, onSuccess, onInvalidSubmit);

function onSuccess(values) {
    console.log('val success', values);
}

function onInvalidSubmit({ values, errors, results }) {
    console.log(values); // current form values
    console.log(errors); // a map of field names and their first error message
    console.log(results); // a detailed map of field names and their validation results
}
</script>

<template>
<base-card-component class="mx-auto" width="300">
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