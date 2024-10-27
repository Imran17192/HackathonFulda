import { defineStore } from 'pinia';


export const usePostStore = defineStore('post', () => {
    const posts = [
        {
            id: 1,
            title: 'Title',
            content: 'Content',
            author: 'Author',
        },
        {
            id: 2,
            title: 'Title2',
            content: 'Content2ifoisdjf fsffhsakfhaoi faskf sak fasfksa fk safk as fasgjf gas fas fajs fjas fasf ',
            author: 'Author2',
        },
        {
            id: 3,
            title: 'Title3',
            content: 'Content3',
            author: 'Author3',
        },
        {
            id: 4,
            title: 'Title4',
            content: 'Content4',
            author: 'Author4',
        },
        {
            id: 5,
            title: 'Title5',
            content: 'Content5',
            author: 'Author5',
        },
    ]

    return {
        posts,
    }
})
