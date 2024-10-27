<template>
    <div class="profile-view">
        <div class="profile-header">
            <img src="@/assets/default.png" alt="Profile Photo" class="profile-photo" />
            <div class="tree-counter">
                <p class="counter">11</p>
                <img src="@/assets/tree.png" alt="Tree Photo" class="tree-photo" />
            </div>
            <h1>{{ name }}</h1>
            <p class="location">{{ location }}</p>
        </div>
        <div class="profile-body">
            <h3>About Me</h3>
            <p class="bio">{{ bio }}</p>
        </div>
        <div class="profile-actions">
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
    border-width: 100px;
    border-color: black;
    object-fit: cover;
    margin-bottom: 10px;
}
.counter {
    font-size: 20px;
    font-weight: bold;
    margin-right: 2px;
    padding: -20px;
}
.tree-counter {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 10px;
}
.tree-photo{
    width: 15%;
    height: 15%;
    border-radius: 1px;
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
