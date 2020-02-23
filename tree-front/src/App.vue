<template>
  <div class="mainContainer">
    <div id="app">
      <ul>
        <li><router-link class="router-link" to="/">top</router-link></li>
        <li><router-link class="router-link" to="/login">login</router-link></li>
        <li><router-link class="router-link" to="/trees">trees</router-link></li>
      </ul>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app',
}
</script>

<style scoped>
  .mainContainer {
    margin: 30px;
  }

  .router-link {

  }


</style>
