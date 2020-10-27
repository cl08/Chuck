import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

import { library } from "@fortawesome/fontawesome-svg-core"
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome"

import { fas } from "@fortawesome/free-solid-svg-icons"
import { far } from "@fortawesome/free-regular-svg-icons"

library.add(fas)
library.add(far)

Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.use(Vuetify);

// vue-turnjs install
import { FlippingWidgets } from "vue-turnjs";
import "vue-turnjs/dist/vue-turnjs.esm.css";
// import "./styles/default.css";

Vue.config.productionTip = false
Vue.use(FlippingWidgets);

new Vue({
  render: h => h(App),
  vuetify : new Vuetify(),
  router: router,
  store: store,
}).$mount('#app')
