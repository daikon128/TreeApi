<template>
    <div class="mainContainer">
        <input type="text" name="new-tree-title"/>
        <input type="text" name="new-tree-description"/>
    <span>
      <tree v-for="tree in trees"
            v-bind:tree="tree"
            v-bind:key="tree.id"
            @addChildNode="addChildNode"
            @removeChildNode="removeChildNode"></tree>
    </span>
    </div>
</template>
<script>
import Tree from '../components/TreeComponent'
import {authHeader} from "../security/auth-header";
import {treeFetch} from "../_helper/request";

export default {
    name: "Trees",
    components: {
        Tree
    },
    data() {
        return {
            trees: null
        }
    },
    mounted() {
        const requestOptions = {
            method: 'GET',
            headers: authHeader()
        };
        fetch(`http://localhost:8888/tree/${this.$store.state.account.user.id}`, requestOptions)
            .then(response => (response.text().then(text => {
                return text && JSON.parse(text)
            }).then(data => this.trees = data.trees)
        ))

    },
    methods: {
        addChildNode([params, callback]) {
            let url = '/tree/add'
            treeFetch(url, {body: JSON.stringify(params)})
                .then(response =>
                    (response.text()
                        .then(text => (text && JSON.parse(text)))
                        .then(callback)))
                .catch(function (error) {
                    console.log("add child error")
                    console.log(error);
                })
        },
        addTree() {
            let params = {
                "userId": this.$store.state.account.user.id,
                "parentId": this.node.id,
                "title": this.title,
                "description": this.description,
                "importance": 1,
                "progress": 0.0
            }
        },
        removeChildNode(nodeId) {
            let url = `/tree/delete/${nodeId}`
            let option = {method: 'DELETE'}
            treeFetch(url, option)
                .then(() =>
                    (
                        console.log("success")
                    ))
                .catch(function (error) {
                    console.log("remove child error")
                    console.log(error);
                });
        }
    }

}
</script>

<style scoped>

</style>