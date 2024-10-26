<template>
    <div class="message-form">
        <h2>Neue Nachricht</h2>
        <form @submit.prevent="sendMessage">
            <div class="input-group">
                <label for="subject">Betreff:</label>
                <input type="text" id="subject" v-model="message.subject" required />
            </div>
            <div class="input-group">
                <label for="content">Nachricht:</label>
                <textarea id="content" v-model="message.content" rows="10" required></textarea>
            </div>
            <div class="actions">
                <button type="submit" class="send-button">Senden</button>
                <div class="icons">
                    <i class="icon">A</i>
                    <i class="icon">📎</i>
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
export default {
    data() {
        return {
            message: {
                subject: '',
                content: '',
            },
        };
    },
    methods: {
        sendMessage() {
            console.log("Nachricht gesendet:", this.message);
            // Hier könnte eine API-Anfrage oder weitere Logik hinzugefügt werden
            this.message.subject = '';
            this.message.content = '';
        },
    },
};
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
textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 14px;
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
