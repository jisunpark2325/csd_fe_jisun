import Vue from 'vue'
import Router from 'vue-router'
import BusiList from '../components/BusiList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/businessMgmt',
      name: 'BusiList',
      component: BusiList
    }
  ]
})
