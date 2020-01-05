<template>
    <node v-bind:node="tree.root"></node>
</template>

<script>
    import Node from './NodeComponent.vue'
    export default {
        name: "Tree",
        components: {
            Node
        },
        props: {
          tree: {
              type: Object,
              required: true
          }
        },
    }
</script>

<style scoped>

</style>