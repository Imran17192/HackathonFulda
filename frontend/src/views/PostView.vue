<template>
    <div class="message-form">
        <h2>Neue Nachricht</h2>
        <form @submit.prevent="sendMessage">
            <div class="input-group">
                <label for="subject">Betreff:</label>
                <input type="text" id="subject" v-model="subject" required/>
            </div>
            <div class="input-group">
                <label for="tags">Tags:</label>
                <vue3-tags-input :tags="tags" placeholder="input tags" class="tags-input" @on-tags-changed="handleChangeTag" />
            </div>
            <div class="input-group">
                <label for="content">Nachricht:</label>
                <textarea id="content" v-model="content" rows="10" maxlength="1500" required></textarea>
                <p class="char-count">{{ 1500 - content.length }} Zeichen verbleiben</p>
            </div>
            <div v-if="showFileUpload" class="input-group">
                <label for="file-upload">Dateien hochladen:</label>
                <input type="file" id="file-upload" @change="handleFileUpload" multiple/>
                <div v-if="files.length" class="file-preview">
                    <div v-for="(file, index) in files" :key="index" class="file-item">
                        <span>{{ file.name }}</span>
                        <button type="button" @click="removeFile(index)">Entfernen</button>
                    </div>
                </div>
            </div>
            <div class="actions">
                <v-btn to="/home" class="back-button">back</v-btn>
                <button type="submit" class="send-button" :disabled="content.length > 1500">Senden</button>
                <div class="icons">
                    <i class="icon" @click="toggleFileUpload">📎</i>
                    <i class="icon">🔗</i>
                    <i class="icon">😊</i>
                    <i class="icon">🖼️</i>
                    <i class="icon">🔒</i>
                    <i class="icon">✏️</i>
                    <i class="icon">⋮</i>
                </div>
            </div>
        </form>
    </div>
</template>

<script setup>
    import { ref } from 'vue';
    import Vue3TagsInput from 'vue3-tags-input';
    import axios from 'axios';

    const subject = ref('');
    const content = ref('');
    const tags = ref([]);
    const files = ref([]);
    const showFileUpload = ref(false);

    async function sendMessage() {
        try {
            console.log("files...", files.value);
            const response = await axios.post('http://localhost:8080/api/auth/messages', {
                subject: subject.value,
                content: content.value,
                tags: tags.value,
                files: files.value,
            });
            if (response?.status === 200) {
                console.log("Nachricht erfolgreich gesendet:", response.data);
            }
        } catch (error) {
            console.error("Fehler beim Senden der Nachricht:", error);
        }

        // Reset the form fields
        subject.value = '';
        content.value = '';
        tags.value = [];
        files.value = [];
        showFileUpload.value = false;
    }

    function toggleFileUpload() {
        showFileUpload.value = !showFileUpload.value;
    }

    function handleFileUpload(event) {
        const uploadedFiles = Array.from(event.target.files);
        files.value.push(...uploadedFiles);
    }

    function removeFile(index) {
        files.value.splice(index, 1);
    }

    function handleChangeTag(tag) {
        tags.value = tag;
    }
</script>

<style scoped>
    .message-form {
        max-width: 600px;
        margin: auto;
        background-color: #f7f9fc;
        padding: 20px;
        border-radius: 8px;
    }

    h2 {
        margin-bottom: 15px;
        color: #333;
        font-weight: normal;
    }

    .input-group {
        margin-bottom: 10px;
    }

    input[type="text"],
    textarea,
    .tags-input {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 14px;
        background-color: #fff;
    }

    input[type="text"]:focus,
    textarea:focus,
    .tags-input:focus-within {
        border: 1px solid #1a73e8;
    }

    .char-count {
        font-size: 12px;
        color: #666;
        text-align: right;
    }

    .file-preview {
        margin-top: 10px;
    }

    .file-item {
        display: flex;
        justify-content: space-between;
        padding: 5px;
        background-color: #e9f5ff;
        border-radius: 4px;
        margin-bottom: 5px;
    }

    .actions {
        display: flex;
        align-items: center;
        justify-content: space-evenly;
    }

    .send-button {
        background-color: #1a73e8;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        font-size: 14px;
        cursor: pointer;

    }

    .back-button {
        background-color: red;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        font-size: 14px;
        cursor: pointer;
    }

    .icons {
        display: flex;
        gap: 10px;
    }

    .icon {
        font-size: 18px;
        cursor: pointer;
    }
</style>
