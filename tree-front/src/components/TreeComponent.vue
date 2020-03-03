<template>
    <div class="tree">
        <node
                v-bind:node="tree.root"
                @removeChildNodeEmit="removeChildNode"
                @addChildEmit="addChildNode"
        ></node>
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
        methods: {
            addChildNode([params, callback]) {
                this.$emit("addChildNode", [params, callback])
            },
            removeChildNode(nodeId) {
                this.tree.root.children = this.tree.root.children.filter((ele) => ele.id !== nodeId)
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