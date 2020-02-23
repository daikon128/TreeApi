import Vue from 'vue'
import App from './App.vue'
import { store } from './_store';
import { router} from "./router/router";

// Vue.config.productionTip = false

new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
})
