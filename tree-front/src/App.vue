<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <Tree></Tree>
    <button v-on:click="getTree()"></button>
  </div>
</template>

<script>
import Tree from './components/Tree.vue'
const axios = require('axios');

export default {
  name: 'app',
  components: {
    Tree
  },
  methods: {
    getTree() {
      let url = '/tree/1'
      axios.get(url)
              .then(function (response) {
                console.log(response);
              })
              .catch(function (error) {
                alert(error);
              });

    }
  },
}
</script>

<style>
</style>
