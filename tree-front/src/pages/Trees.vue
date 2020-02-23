<template>
    <div class="mainContainer">
    <span>
      <tree v-for="tree in trees" v-bind:tree="tree" v-bind:key="tree.id"></tree>
    </span>
    </div>
</template>
<script>
import Tree from '@/components/TreeComponent.vue'
import {authHeader} from "../security/auth-header";

// const axios = require('axios');

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
        fetch(`http://localhost:8888/tree/1`, requestOptions).then(response => (
            response.text().then(text => {
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
}
</script>

<style scoped>

</style>