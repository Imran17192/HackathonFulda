<!-- Add Post form with title, tags and content and possibility to add a file -->
<template>
  <div class="message-form">
    <h2>Send a message</h2>
    <div class="input-group">
      <input type="text" placeholder="Subject" v-model="subject"/>
    </div>
    <div class="input-group">
      <label for="tags">Tags:</label>
      <vue3-tags-input :tags="tags" placeholder="input tags" class="tags-input" @on-tags-changed="handleChangeTag"/>
    </div>
    <div class="input-group">
            <textarea
                rows="5"
                placeholder="Content"
                v-model="content"
            ></textarea>
    </div>
    <div class="input-group">
      <button @click="toggleFileUpload" class="send-button">Add file</button>
    </div>
    <div v-if="showFileUpload" class="input-group">
      <input type="file" @change="handleFileUpload" multiple/>
    </div>
    <div v-if="files.length > 0" class="file-preview">
      <div v-for="(file, index) in files" :key="index" class="file-item">
        <span>{{ file.name }}</span>
        <div class="actions">
          <span @click="removeFile(index)" class="icon">❌</span>
        </div>
      </div>
    </div>
    <div class="input-group">
      <button @click="sendMessage" class="send-button">Send</button>
    </div>
    <div class="input-group">
    <button @click="back" class="back-button">Back</button>
    </div>
  </div>
</template>

<script>
import {ref} from 'vue';
import Vue3TagsInput from 'vue3-tags-input';
import axios from "axios";
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  components: {
    Vue3TagsInput,
  },
  setup() {
    const subject = ref('');
    const tags = ref([]);
    const content = ref('');
    const showFileUpload = ref(false);
    const files = ref([]);
    const store = useStore();
    const username = computed(() => store.getters.username);

    const toggleFileUpload = () => {
      showFileUpload.value = !showFileUpload.value;
    };

    const handleFileUpload = (event) => {
      files.value = Array.from(event.target.files);
    };

    const removeFile = (index) => {
      files.value.splice(index, 1);
    };

    function handleChangeTag(tag) {
      tags.value = tag;
    }

    const sendMessage = () => {
      console.log({
        subject: subject.value,
        tags: tags.value,
        content: content.value,
        files: files.value,
        username: username.value,
      });

      const formData = new FormData();
      formData.append('subject', subject.value);
      formData.append('tags', tags.value.join(','));
      formData.append('content', content.value);
      formData.append('username', username.value);
      files.value.forEach((file) => {
        formData.append('files', file);
      });

      axios.post('http://localhost:8080/api/auth/messages', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      }).then((response) => {
        console.log(response);
      }).catch((error) => {
        console.error(error);
      });
    };

    const back = () => {
      window.history.back();
    };

    return {
      subject,
      tags,
      content,
      showFileUpload,
      files,
      toggleFileUpload,
      handleFileUpload,
      removeFile,
      handleChangeTag,
      sendMessage,
      back,
    };
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
