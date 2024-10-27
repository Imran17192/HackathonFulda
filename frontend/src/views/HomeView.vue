<script setup>
    import SearchBarComponent from "@/components/SearchBarComponent.vue";
    import PostComponent from "@/components/PostComponant.vue";
    import { usePostStore } from '@/stores/post.js';

    const { posts } = usePostStore();
</script>

<template>
    <div class="home">
        <SearchBarComponent/>
        <v-btn to="/new-post" class="send-post-button">+</v-btn>
    </div>

    <template v-for="post in posts" :key="post.id">
        <PostComponent :post="post"/>
    </template>
</template>

<style scoped>
    .home {
        display: flow;
        width: 600px;
        margin: auto;
    }

    .send-post-button {
        display: flex;
        height: 20%;
        width: 20%;
        background: #007BFF;
        border: none;
        border-radius: 15px;
        color: white;
        padding: 10px 30px;
        cursor: pointer;
        font-size: 40px;
        margin: 20px auto;

    }
</style>