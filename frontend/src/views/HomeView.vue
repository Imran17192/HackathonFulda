<script setup>
import { onMounted, ref, computed } from 'vue'; // Import necessary functions
import SearchBarComponent from "@/components/SearchBarComponent.vue";
import PostComponent from "@/components/PostComponant.vue"; // Corrected spelling
import axios from "axios";
import { useSnackbarStore } from '@/stores/snackbar.js';
import { storeToRefs } from 'pinia';

const { snackbar } = storeToRefs(useSnackbarStore());

// Create reactive variables to store posts and users
const posts = ref([]);
const users = ref([]);

// Function to fetch posts
const fetchPosts = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/auth/posts');
        posts.value = response.data; // Store the fetched posts
        console.log(response.data);
    } catch (error) {
        console.error("Error fetching posts:", error);
    }
}

// Function to fetch users
const fetchUsers = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/auth/users');
        users.value = response.data; // Store the fetched users
        console.log(response.data);
    } catch (error) {
        console.error("Error fetching users:", error);
    }
}

const sortedUsers = computed(() => {
    return [...users.value].sort((a, b) => b.treeCount - a.treeCount); // Sort users by treeCount in descending order
});

// Fetch posts and users when the component is mounted
onMounted(() => {
    fetchPosts();
    fetchUsers();
});
</script>

<template>
<div class="home">
    <v-snackbar
        v-model="snackbar.visible"
        :timeout="snackbar.timeout"
        :color="snackbar.color"
    >
        {{ snackbar.message }}
    </v-snackbar>
    <SearchBarComponent/>
    <v-btn to="/new-post" class="send-post-button">+</v-btn>
    <div class="container">
        <div class="users">
            <h2>Users</h2>
            <ul>
                <li v-for="user in users" :key="user.id">{{ user.firstName }} {{ user.lastName }}</li>
            </ul>
        </div>
        <div class="posts">
            <h2>Posts</h2>
            <PostComponent v-for="post in posts" :key="post.id" :post="post"/>
        </div>
        <div class="ranking">
            <h2>Ranking</h2>
            <ul>
                <!-- sort users by treeCount -->
                <li class="trees" v-for="user in sortedUsers" :key="user.id">{{ user.firstName }} {{ user.lastName }} -
                    {{ user.treeCount }} x <img src="./../assets/tree2.png" height="32px" width="29px"></img></li>
            </ul>
        </div>
    </div>
</div>
</template>

<style scoped>
.home {
    display: flex;
    flex-direction: column;
    width: 100%;
    margin: auto;
    max-width: 1200px; /* Maximum width for larger screens */
}

.trees {
    img {
        bottom: -2px;
    }
}

.send-post-button {
    display: flex;
    height: 50px;
    width: 50px;
    background: #007BFF;
    border: none;
    border-radius: 50%;
    color: white;
    font-size: 30px;
    cursor: pointer;
    margin: 20px auto;
}

/* Container for users, posts, and ranking */
.container {
    display: flex;
    justify-content: space-between; /* Space between elements */
}

/* Common styling for left and right sections */
.users, .ranking {
    flex: 1; /* Equal width for left and right sections */
    padding: 10px; /* Add some padding */
    max-width: 250px; /* Limit the width of user and ranking sections */
}

/* Styling for the posts section */
.posts {
    flex: 2; /* Wider than users and ranking sections */
    padding: 10px;
}

/* Adjust list styling */
ul {
    list-style-type: none; /* Remove bullet points */
    padding: 0; /* Remove default padding */
}

h2 {
    text-align: center; /* Center headers */
}
</style>
