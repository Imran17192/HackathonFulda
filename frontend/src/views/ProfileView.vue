<template>
<v-snackbar
    v-model="snackbar.visible"
    :timeout="snackbar.timeout"
    :color="snackbar.color"
>
    {{ snackbar.message }}
</v-snackbar>
<div class="profile-view">
    <div class="profile-header">
        <img :src="profileData.profilePicture || defaultProfilePhoto" alt="Profile Photo" class="profile-photo"/>
        <div class="tree-counter">
            <p class="counter">{{ profileData.treeCount }} trees</p>
            <img src="@/assets/tree.png" alt="Tree Photo" class="tree-photo"/>
        </div>
        <h1>{{ fullName }}</h1>

        <div v-if="isEditing">
            <input type="text" v-model="newLocation" @keyup.enter="saveLocation"/>
            <button @click="saveLocation">Save</button>
        </div>
        <p class="location" @dblclick="editLocation" v-else>{{ profileData.city }}, {{ profileData.country }}</p>
    </div>
    <div class="profile-body">
        <h3>About Me</h3>
        <!-- same thing like location here -->
        <div v-if="isEditingBio">
            <textarea v-model="newBio" @keyup.enter="saveLocation"></textarea>
            <button @click="saveLocation">Save</button>
        </div>
        <p class="bio" @dblclick="editBio" v-else>{{ profileData.bio }}</p>
    </div>
</div>
<div>
    <TabsComponent/>
</div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import axios from "axios";
import TabsComponent from "@/components/TabsComponent.vue";
import { useStore } from 'vuex';
import { useSnackbarStore } from '@/stores/snackbar.js';
import { storeToRefs } from 'pinia';

const store = useStore();

const { snackbar } = storeToRefs(useSnackbarStore());

// Create a reactive object to store all user data
const profileData = ref({
    id: null,
    email: '',
    firstName: '',
    middleName: '',
    lastName: '',
    phoneNumber: '',
    password: '',
    treeCount: 0,
    city: '',
    country: '',
    bio: '',
    profilePicture: '',
});

// State for editing
const isEditing = ref(false);
const isEditingBio = ref(false);
const newLocation = ref(`${ profileData.value.city }, ${ profileData.value.country }`);
const newBio = ref(profileData.value.bio);

// Placeholder for default profile photo
const defaultProfilePhoto = 'https://via.placeholder.com/150';

// Computed property to get the full name
const fullName = computed(() => {
    return `${ profileData.value.firstName } ${ profileData.value.middleName ? profileData.value.middleName + ' ' : '' }${ profileData.value.lastName }`;
});

const username = computed(() => store.getters.username);

// Function to fetch the user's profile data
const fetchProfile = async () => {
    try {
        const userId = username.value; // Placeholder for userId; replace with dynamic value as needed
        const response = await axios.get(`http://localhost:8080/api/auth/users/${ userId }`);

        // Update the profileData with the fetched data
        profileData.value = response.data;
        console.log(profileData.value);

    } catch (error) {
        console.error("Error fetching profile:", error);
    }
};

// Function to toggle editing state
const editLocation = () => {
    isEditing.value = true;
    newLocation.value = `${ profileData.value.city }, ${ profileData.value.country }`;
    // remove location class to allow input field to be displayed
};

const editBio = () => {
    isEditingBio.value = true;
    newBio.value = profileData.value.bio;
};

const saveLocation = async () => {
    isEditing.value = false;

    const [city, country] = newLocation.value.split(',').map(part => part.trim());

    // Update the profileData with new values
    profileData.value.city = city;
    profileData.value.country = country;
    profileData.value.bio = newBio.value;

    try {
        console.log(profileData.value.id);
        await axios.put(`http://localhost:8080/api/auth/users/${ profileData.value.id }`, {
            city,
            country,
            bio: newBio.value,
        });
        console.log("Location updated successfully.");
    } catch (error) {
        console.error("Error updating location:", error);
    }
};

// Fetch the profile when the component is mounted
onMounted(() => {
    fetchProfile();
});

</script>

<style scoped>
.profile-view {
    max-width: 500px;
    margin: 20px auto;
    padding: 20px;
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
    font-family: Arial, sans-serif;
}

.profile-header {
    margin-bottom: 20px;
}

.profile-photo {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    object-fit: cover;
    margin-bottom: 10px;
}

.counter {
    font-size: 20px;
    font-weight: bold;
    margin-right: 2px;
}

.tree-counter {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 10px;
}

.tree-photo {
    width: 15px;
    height: 15px;
}

.location {
    color: #666;
    font-size: 14px;
}

.profile-body {
    text-align: left;
    margin-top: 20px;
}

.bio {
    color: #333;
    font-size: 16px;
    line-height: 1.5;
}

.profile-actions {
    margin-top: 20px;
}

.action-button {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    margin: 5px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
}

.action-button:hover {
    background-color: #0056b3;
}
</style>
