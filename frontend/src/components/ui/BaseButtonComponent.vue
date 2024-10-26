<script setup>
const props = defineProps({
    text: {
        type: String,
        required: true
    }
})
</script>

<template>
    <v-btn
        class="mb-8"
        color="blue"
        size="large"
        variant="tonal"
        block
    >
        {{ text }}
        <slot></slot>
    </v-btn>
</template>

<style scoped>

</style>