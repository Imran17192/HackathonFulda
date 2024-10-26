import { useField, useForm } from 'vee-validate';
import { reactive } from 'vue';

export function useFormHandler(schema, onSuccess, onInvalidSubmit) {
    const fields = reactive({});
    const { errors, handleSubmit } = useForm({
        validationSchema: schema,
    });

    const fieldsArray = Object.keys(schema.fields)
    fieldsArray.forEach((field) => {
        const { value } = useField(field);
        fields[field] = value;
    })
    const submitForm = handleSubmit(onSuccess, onInvalidSubmit);

    return {
        fields,
        errors,
        submitForm,
    };
}