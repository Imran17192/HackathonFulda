<template>
    <div class="social-post">
        <div class="post-header">
            <div class="profile-info">
                <img src="@/assets/default.png" alt="Profile" class="profile-photo">

                <p><strong> &nbsp;Alexander Geißenbergger</strong> | 25. Juli um 15:15</p>
        </div>
            <div class="header-text">
                <p>Herzlich willkommen! Unser charmantes PPC Team sucht Verstärkung und freut sich auf DICH! <a href="https://karriere.xpose360.de/job/sea-ppc-senior-manager/">karriere.xpose360.de/job/sea-ppc-senior-manager/</a></p>
            </div>
        </div>
        <div class="post-body">
            <img src="@/assets/team-photo.png" alt="Team" class="team-photo">
        </div>
        <div class="post-footer">
            <div class="interaction-stats">
            </div>
            <div class="actions">
                <button class="thumb-button" @click="incrementLike">
                    👍
                </button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'SocialPost',
    data() {
        return {
            // data properties here if needed
        };
    },
    methods: {
        // event handling methods here
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
