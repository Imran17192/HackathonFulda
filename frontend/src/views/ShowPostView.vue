<script setup>
    import { ref, onMounted } from 'vue';
    import { useRoute } from 'vue-router';
    import { usePostStore } from '@/stores/post.js';

    const route = useRoute();
    const postStore = usePostStore();
    const post = ref(null);

    onMounted(() => {
        const postId = route.params.id;
        post.value = postStore.posts.find(p => p.id === parseInt(postId));
    });
</script>

<template>
    <v-card class="pa-2" variant="outlined">
        <div v-if="post">
            <div>Author: {{ post.author }}</div>
            <h1>{{ post.title }}</h1>
            <p>{{ post.content }}</p>
        </div>

        <div v-else>
            <p>Post not found.</p>
        </div>
    </v-card>
</template>