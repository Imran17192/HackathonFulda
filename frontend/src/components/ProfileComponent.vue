<template>
    <div class="profile-view">
        <div class="profile-header">
            <img src="@/assets/profile-photo.png" alt="Profile Photo" class="profile-photo" />
            <h1>{{ name }}</h1>
            <p class="location">{{ location }}</p>
        </div>
        <div class="profile-body">
            <h3>About Me</h3>
            <p class="bio">{{ bio }}</p>
        </div>
        <div class="profile-actions">
            <button @click="sendMessage" class="action-button">Send Message</button>
            <button @click="followUser" class="action-button">Follow</button>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        profilePhoto: {
            type: String,
            default: 'https://via.placeholder.com/150' // Placeholder image
        },
        name: {
            type: String,
            default: 'John Doe'
        },
        location: {
            type: String,
            default: 'City, Country'
        },
        bio: {
            type: String,
            default: 'This is a brief bio about the user. They are interested in various topics and enjoy connecting with others.'
        }
    },
    methods: {
        sendMessage() {
            console.log(`Message sent to ${this.name}`);
            // Logic for sending a message can be implemented here
        },
        followUser() {
            console.log(`Following ${this.name}`);
            // Logic for following the user can be implemented here
        }
    }
};
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
