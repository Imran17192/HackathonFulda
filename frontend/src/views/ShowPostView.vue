<script setup>
import {ref, onMounted} from 'vue';
import {useRoute} from 'vue-router';
import axios from 'axios'; // Ensure you import Axios

const route = useRoute();
const post = ref(null);
const isLoading = ref(true); // Track loading state
const error = ref(null); // Track error state

async function fetchPost(postId) {
  try {
    const response = await axios.get(`http://localhost:8080/api/auth/posts/${postId}`);
    post.value = response.data;
  } catch (err) {
    console.error(err);
    error.value = "Failed to load post. Please try again later.";
  } finally {
    isLoading.value = false; // Set loading to false regardless of success or failure
  }
}

onMounted(() => {
  const postId = route.params.id;
  if (postId) {
    fetchPost(postId);
  } else {
    error.value = "Invalid post ID.";
    isLoading.value = false; // Ensure loading is set to false if postId is invalid
  }
});
</script>

<template>
  <v-card class="pa-2" variant="outlined">
    <template v-if="isLoading">
      <p>Loading...</p>
    </template>

    <template v-else-if="error">
      <p>{{ error }}</p>
    </template>

    <template v-else-if="post">
      <div>Author: {{ post.author }}</div>
      <h1>{{ post.title }}</h1>
      <p>{{ post.content }}</p>
    </template>

    <template v-else>
      <p>Post not found.</p>
    </template>
  </v-card>
</template>

<style scoped>
.pa-2 {
  padding: 16px; /* Adjust padding as needed */
}
</style>
