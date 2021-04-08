import Vue from 'vue'
import Router from 'vue-router'
import ContList from '../components/ContList'
import BusiList from '../components/BusiList'
import PlatList from '../components/PlatList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/contractMgmt',
      name: 'ContList',
      component: ContList
    },
    {
      path: '/businessMgmt',
      name: 'BusiList',
      component: BusiList
    },
    {
      path: '/platformMgmt',
      name: 'PlatList',
      component: PlatList
    }
  ]
})
