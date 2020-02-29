<template>
    <div class="tree">
        <node
                v-bind:node="tree.root"
                @removeChildNodeEmit="removeChildNode"
        ></node>
    </div>
</template>

<script>
    import Node from './NodeComponent.vue'
    import {treeFetch} from "../_helper/request";
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
        methods: {
            removeChildNode(nodeId) {
                this.$emit("removeChildNode", nodeId)
            }

        }
    }
</script>

<style scoped>
    .tree {
        float:left;
        min-width: 200px;
        border: moccasin 2px dashed;
    }

</style>