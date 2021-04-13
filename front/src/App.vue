<template>
  <div id="app">
    <h1 style="margin:20px; color: lightseagreen">
      {{ title }}
    </h1>
    <div align="center" v-if="login_ck === true">
      <ul class="nav nav-tabs" style="justify-content: center; align-items: center; font-weight: bold; font-size: large; color: #2c3e50">
        <li class="nav-item">
          <router-link to="/contractMgmt" :class="{}">
            <a class="nav-link active" data-toggle="tab" id="contractMgmt" @click="onclick('contractMgmt')">계약 관리</a>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/businessMgmt">
            <a class="nav-link" data-toggle="tab" id="businessMgmt" @click="onclick('businessMgmt')">사업자 관리</a>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/platformMgmt">
            <a class="nav-link" data-toggle="tab" id="platformMgmt" @click="onclick('platformMgmt')">플랫폼 관리</a>
          </router-link>
        </li>
      </ul>
    </div>
    <div id="div_login" style="display: grid; justify-content: center" v-show="login_ck === false">
      <b-form-input type="text" id="tb_id" placeholder="Input your id" style="margin-top:10px; margin-bottom:10px; width: 50vh" v-model="tb_id">{{tb_id}}</b-form-input>
      <b-form-input type="password" id="tb_pw" placeholder="Input your pw" style="margin-bottom: 20px; width: 50vh" v-model="tb_pw">{{tb_pw}}</b-form-input>
      <b-button id="btn_login" @click="btn_login" style="width: 50vh">로그인</b-button>
    </div>
    <router-view/>
  </div>
</template>
<!--
<b-navbar style="justify-content: center; align-items: center">
<b-button id='btn_goCont' class="btn btn-danger" type="button" style="margin-left: 10px; margin-right: 10px" >
            계약 관리
          </b-button>
-->

<script>
import Vue from 'vue'
import routes from './router/index.js'

export default {
  name: 'App',
  data() {
    return {
      title: 'KTDS License Management',
      login_ck: false,
      tb_id: "",
      tb_pw: ""
    }
  },
  created() {
  },
  methods: {
    onclick: function (id) {
      let selectedId = document.getElementById(id).classList
      let currentId = document.getElementsByClassName('nav-link active')[0].classList

      selectedId.add('active')
      currentId.remove('active')

    },
    btn_login: function () {
      let user_id = this.tb_id
      let user_pw = this.tb_pw

      console.log('user_id = ', user_id)
      console.log('user_pw = ', user_pw)

      if (user_id === 'admin' && user_pw === 'New1234!'){
        alert('로그인 되었습니다')
        this.login_ck = true
        return this.$router.push('/contractMgmt')
      } else {
        alert('로그인 정보를 확인해주세요')
        this.login_ck = false
      }
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
