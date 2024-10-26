<template>
    <div class="search-container">
        <input
            v-model="searchQuery"
            @keyup.enter="performSearch"
            type="text"
            placeholder="Search articles..."
            class="search-input"
        />
        <button @click="performSearch" class="search-button">
            Search
        </button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            searchQuery: ''
        };
    },
    methods: {
        performSearch() {
            // You can define how to handle the search here
            console.log('Searching for:', this.searchQuery);
            // For example, emit an event or route to a search page
            this.$emit('search', this.searchQuery);
        }
    }
}
</script>

<style scoped>
.search-container {
    display: flex;
    margin: 20px auto;
    background: #fff;
    border-radius: 20px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    width: 100%;
    max-width: 600px;
    overflow: hidden;
}

.search-input {
    border: none;
    padding: 10px 20px;
    flex-grow: 1;
    font-size: 16px;
}

.search-button {
    background: #007BFF;
    border: none;
    color: white;
    padding: 10px 20px;
    cursor: pointer;
    font-size: 16px;
}

.search-input:focus, .search-button:focus {
    outline: none;
}

.search-input::placeholder {
    color: #ccc;
}

.search-button:hover {
    background: #0056b3;
}
</style>
