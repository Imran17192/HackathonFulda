<template>
    <v-card>
        <v-tabs v-model="tab" background-color="primary" dark>
            <v-tab value="one">Posts</v-tab>
            <v-tab value="two">Graph</v-tab>
        </v-tabs>

        <v-card-text>
            <v-tabs-window v-model="tab">
                <v-tabs-window-item value="one">
                    <PostComponent/>
                </v-tabs-window-item>

                <v-tabs-window-item value="two">
                    <GraphComponent/>
                </v-tabs-window-item>
            </v-tabs-window>
        </v-card-text>
    </v-card>
</template>

<script setup>
    import PostComponent from "@/components/PostComponant.vue";
    import { ref } from 'vue';
    import GraphComponent from '@/components/GraphComponent.vue';

    const tab = ref('one');
</script>

<style scoped>
.v-card {
    max-width: 600px;
    margin: auto;
}
</style>
