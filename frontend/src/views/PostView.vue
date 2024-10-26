<template>
    <div class="message-form">
        <h2>Neue Nachricht</h2>
        <form @submit.prevent="sendMessage">
            <div class="input-group">
                <label for="subject">Betreff:</label>
                <input type="text" id="subject" v-model="message.subject" required />
            </div>
            <div class="input-group">
                <label for="tags">Tags:</label>
                <vue3-tags-input v-model:tags="tags" placeholder="input tags" class="tags-input" />
            </div>
            <div class="input-group">
                <label for="content">Nachricht:</label>
                <textarea id="content" v-model="message.content" rows="10" maxlength="1500" required></textarea>
                <p class="char-count">{{ 1500 - message.content.length }} Zeichen verbleiben</p>
            </div>
            <div v-if="showFileUpload" class="input-group">
                <label for="file-upload">Dateien hochladen:</label>
                <input type="file" id="file-upload" @change="handleFileUpload" multiple />
                <div v-if="files.length" class="file-preview">
                    <div v-for="(file, index) in files" :key="index" class="file-item">
                        <span>{{ file.name }}</span>
                        <button type="button" @click="removeFile(index)">Entfernen</button>
                    </div>
                </div>
            </div>
            <div class="actions">
                <button type="submit" class="send-button" :disabled="message.content.length > 1500">Senden</button>
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

<script>
import { defineComponent } from 'vue';
import Vue3TagsInput from 'vue3-tags-input';
import axios from 'axios';

export default defineComponent({
    components: {
        Vue3TagsInput
    },
    data() {
        return {
            message: {
                subject: '',
                content: '',
            },
            tags: ['VUE', 'HTML', 'CSS'],
            files: [],
            showFileUpload: false
        }
    },
    methods: {
        async sendMessage() {
            console.log("Nachricht gesendet:", this.message);
            console.log("Tags:", this.tags);
            console.log("Dateien:", this.files);

            try {
                const response = await axios.post('http://localhost:3000/api/messages', {
                    subject: this.message.subject,
                    content: this.message.content,
                    tags: this.tags,
                    files: this.files
                });
                if (response?.status === 200) {
                    console.log("Nachricht erfolgreich gesendet:", response.data);
                }
            } catch (error) {
                console.error("Fehler beim Senden der Nachricht:", error);
            }

            // Reset the form fields
            this.message.subject = '';
            this.message.content = '';
            this.tags = [];
            this.files = [];
            this.showFileUpload = false;
        },
        toggleFileUpload() {
            this.showFileUpload = !this.showFileUpload;
        },
        handleFileUpload(event) {
            const uploadedFiles = Array.from(event.target.files);
            this.files.push(...uploadedFiles);
        },
        removeFile(index) {
            this.files.splice(index, 1);
        }
    }
});
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
    justify-content: space-between;
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

.icons {
    display: flex;
    gap: 10px;
}

.icon {
    font-size: 18px;
    cursor: pointer;
}
</style>
