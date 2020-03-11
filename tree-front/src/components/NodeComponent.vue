<template>
    <div>
        <div v-on:mouseover="showAddButton"
             v-on:mouseleave="hiddenAddButton"
            v-on:click="toggleMode"
        >{{ node.id }}:{{ node.title }}<div class="description">{{ node.description }}</div> <span v-show="buttonShown" >+</span></div>
        <span v-on:click="removeChildNode">-</span>
        <ul>
            <li v-if="addCardMode" >
                <input v-model="title" type="text" />
                <input v-model="description" type="text" />
                <button v-on:click="addChildCard">add</button>
                <p v-on:click="toggleMode">-</p>
            </li>
            <li
                class="node"
                v-for="child in node.children"
                v-bind:key="child.id">
                <node
                        v-bind:node="child"
                        @removeChildNodeEmit="removeChildNodeEmit"
                        @addChildEmit="addChildEmit"
                ></node>
            </li>
        </ul>
    </div>
</template>
<script>
    export default {
        name: "node",
        data() {
            return {
                buttonShown: false,
                addCardMode: false,
                title: "",
                description: ""
            }
        } ,
        props: {
            node: {
                type: Object,
                required: true
            }
        },
        methods: {
            init() {
                this.buttonShown = false
                this.addCardMode = false
                this.title = ""
                this.description = ""
            },
            showAddButton() {
                this.buttonShown = true
            },
            hiddenAddButton() {
                this.buttonShown = false
            },
            toggleMode() {
                this.addCardMode = !this.addCardMode
            },
            addChildCard() {
                let params = {
                    "userId": this.$store.state.account.user.id,
                    "parentId": this.node.id,
                    "title": this.title,
                    "description": this.description,
                    "importance": 1,
                    "progress": 0.0
                }

                console.log(params)
                const addSuccessCallback = (data) => {
                    this.node.children.push(data);
                    this.init()
                }
                this.$emit("addChildEmit", [params, addSuccessCallback])
            },
            addChildEmit([params, callback]) {
                this.$emit("addChildEmit", [params, callback])
            },
            removeChildNode() {
                this.$emit("removeChildNodeEmit", this.node.id)
            },
            removeChildNodeEmit(nodeId) {
                // FIXME : 一つ上の親だけが実行するようにしたいな...
                this.node.children = this.node.children.filter((ele) => ele.id !== nodeId)
                this.$emit("removeChildNodeEmit", nodeId)
            }
        }
    }
</script>

<style scoped>
    .description {
        text-decoration-color: gray;
    }
</style>