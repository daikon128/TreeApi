<template>
    <node v-bind:node="tree"></node>
</template>

<script>
    import Node from './Node.vue'
    export default {
        name: "Tree",
        components: {
            Node
        },
        data() {
            return {
                    tree: {
                        "id": 3,
                        "parentId": null,
                        "children": [
                            {
                                "id": 10,
                                "parentId": 3,
                                "children": [
                                    {
                                        "id": 14,
                                        "parentId": 10,
                                        "children": [],
                                        "title": "test",
                                        "ownProgressValue": 100.0,
                                        "importance": 1,
                                        "progress": 100.0
                                    }
                                ],
                                "title": "testUpdateTitle",
                                "ownProgressValue": 100.0,
                                "importance": 100,
                                "progress": 100.0
                            },
                            {
                                "id": 13,
                                "parentId": 3,
                                "children": [],
                                "title": "test",
                                "ownProgressValue": 0.0,
                                "importance": 1,
                                "progress": 0.0
                            }
                        ],
                        "title": "test",
                        "ownProgressValue": 0.0,
                        "importance": 1,
                        "progress": 1.0
                    }
                }
    }

    }
</script>

<style scoped>

</style>