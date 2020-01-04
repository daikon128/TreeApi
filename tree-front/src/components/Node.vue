<template>
    <div>
        <p>{{ node.id }}:{{ node.title }}</p>
        <ul v-if="node.children.length > 0">
            <li class="node"
                v-for="child in node.children"
                v-bind:key="child.id">
                <node v-bind:node="child"></node>
            </li>
        </ul>
    </div>
</template>
<script>
    export default {
        name: "node",
        props: {
            node: {
                type: Object,
                required: true
            }
        }
    }
</script>

<style scoped>

</style>