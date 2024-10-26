<!-- src/components/TabView.vue -->
<template>
    <v-card>
        <!-- Tab Navigation -->
        <v-tabs v-model="tab" background-color="primary" dark>
            <v-tab value="one">Tab One</v-tab>
            <v-tab value="two">Tab Two</v-tab>
            <v-tab value="three">Tab Three</v-tab>
        </v-tabs>

        <!-- Tab Content -->
        <v-tabs-items v-model="tab">
            <v-tab-item value="one">
                <!-- Include PostComponent in Tab One -->
                <PostComponent />
            </v-tab-item>

            <v-tab-item value="two">
                <div>
                    <PostComponent />
                </div>
            </v-tab-item>

            <v-tab-item value="three">
                <div>
                    <h3>Content for Tab Three</h3>
                    <p>This is some content for the third tab.</p>
                </div>
            </v-tab-item>
        </v-tabs-items>
    </v-card>
</template>

<script setup>
        import PostComponent from "@/components/PostComponant.vue";
</script>

<style scoped>
.v-card {
    max-width: 600px;
    margin: auto;
}
</style>
