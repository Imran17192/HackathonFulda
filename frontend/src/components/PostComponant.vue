<template>
    <v-card v-if="post" class="mx-auto ma-4" max-width="500" variant="outlined" @click="showPost">
        <v-card-text>
            <div>Author: {{ post.author }}</div>

            <p class="text-h4 font-weight-black">{{ post.title }}</p>

            <div class="text-medium-emphasis">
                {{ post.content }}
            </div>
        </v-card-text>
    </v-card>
</template>

<script setup>
    import router from '@/router/index.js';

    const props = defineProps({
        post: {
            type: Object,
            default: null,
        }
    });

    function showPost() {
        if (props.post?.id) {
            router.push(`/posts/${props.post.id}`);
        }
    }
</script>

<style scoped>
.social-post {
    display: flow;
    width: 600px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background: #fff;
    margin: 20px auto;
    font-family: Arial, sans-serif;
}

.profile-info {
    display: flex;
    align-items: center;
    padding: 1px;
}

.post-header, .post-footer {
    padding: 15px;
}

.profile-photo {
    width: 10%;
    height: 10%;
    border-radius: 60px;
}

.team-photo {
    width: 100%;
    height: auto;
    border-radius: 1px;
}

.header-text p {
    margin: 4px 0;
}

.thumb-button {
    background: none;
    border: none;
    font-size: 30px; /* Increase font size for larger button */
    padding: 10px; /* Add padding for larger button appearance */
    cursor: pointer;
    color: #007bff;
    margin-right: 10px;
}

.interaction-stats span {
    display: block;
    margin-bottom: 5px;
}

.actions .btn {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 8px 16px;
    margin-right: 10px;
    cursor: pointer;
}

.actions .btn:hover {
    background-color: #0056b3;
}
</style>
