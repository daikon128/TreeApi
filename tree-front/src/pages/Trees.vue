<template>
    <div class="mainContainer">
    <span>
      <tree v-for="tree in trees" v-bind:tree="tree" v-bind:key="tree.id" @removeChildNode="removeChildNode"></tree>
    </span>
    </div>
</template>
<script>
import Tree from '@/components/TreeComponent'
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

        // let url = '/tree/1'
        // axios.get(url)
        //     .then(response => (
        //         this.trees = response.data.trees
        //     ))
        //     .catch(function (error) {
        //         console.log(error)
        //         alert(error);
        //     });

    },
    methods: {
        removeChildNode(nodeId) {
            let url = `/tree/delete/${nodeId}`
            let option = {method: 'DELETE'}
            treeFetch(url, option)
                .then(() =>

                    (
                        console.log("success")
                    ))
                .catch(function (error) {
                    console.log(error);
                });
        }
    }

}
</script>

<style scoped>

</style>