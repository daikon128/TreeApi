<template>
  <div class="mainContainer">
    <div id="app">
      <tree v-for="tree in trees" v-bind:tree="tree" v-bind:key="tree.id"></tree>
    </div>
  </div>
</template>

<script>
import Tree from './components/TreeComponent.vue'
const axios = require('axios');

export default {
  name: 'app',
  components: {
    Tree
  },
  data() {
      return {
        trees: null
      }
  },
  mounted() {
    let url = '/tree/1'
    axios.get(url)
            .then(response => (
              this.trees = response.data.trees
            ))
            .catch(function (error) {
              alert(error);
            });

  },
}
</script>

<style scoped>
  .mainContainer {
    margin: 30px;
  }
</style>
