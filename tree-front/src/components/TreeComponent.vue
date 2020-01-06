<template>
    <div class="tree">
        <node v-bind:node="tree.root"></node>
    </div>
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
    .tree {
        float:left;
        min-width: 200px;
        border: moccasin 2px dashed;
    }

</style>