<template>
  <div id="app">
    <h1 style="margin:20px; color: lightseagreen">
      {{ title }}
    </h1>
    <div align="center">
      <ul class="nav nav-tabs" style="justify-content: center; align-items: center; font-weight: bold; font-size: large; color: #2c3e50">
        <li class="nav-item">
          <router-link to="/contractMgmt">
            <a class="nav-link" data-toggle="tab">계약 관리</a>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/businessMgmt">
            <a class="nav-link active" data-toggle="tab">사업자 관리</a>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/platformMgmt">
            <a class="nav-link" data-toggle="tab">플랫폼 관리</a>
          </router-link>
        </li>
      </ul>
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
export default {
  name: 'App',
  data() {
    return {
      title: 'KTDS License Management'
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
