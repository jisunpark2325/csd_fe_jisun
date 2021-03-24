import Vue from 'vue'
import Router from 'vue-router'
import RegisterBusi from '@/components/RegisterBusi'
import RegisterCont from "../components/RegisterCont";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/platformMgmt',
      name: 'RegisterPlat',
      component: RegisterPlat
    },
    {
      path: '/businessMgmt',
      name: 'RegisterBusi',
      component: RegisterBusi
    },
    {
      path: '/contractMgmt',
      name: 'RegisterCont',
      component: RegisterCont
    }
  ]
})

function registerBusi (busiData) {
  return instance.post('businessMgmt', busiData)
}

export {registerBusi}
