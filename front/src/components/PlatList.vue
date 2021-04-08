<template>
  <div class="list_plat" padding="10px">
    <b-card text-variant="green" style="width: auto; margin-top: 10px; max-height: 150rem; align-items: center"
            class="mb-2" border-variant="info" align="center">
      <div style="margin-bottom: 10px">
        <h3 style="font-weight: bold">
          솔루션 리스트
        </h3>
      </div>
      <b-navbar type="light" variant="light" align="center" style="display: flex; justify-content: center">
        <b-nav-form>
          <b-button @click="addPopup" id='btn_addPlat' type="button" variant="outline-primary"
                    style="margin-left: 10px; margin-right: 10px"> 추가
          </b-button>
          <b-button class="btn btn-outline-info" @click="modiPopup" id='btn_modiPlat' type="button" variant="outline-success"
                    style="margin-left: 10px; margin-right: 10px"> 수정
          </b-button>
        </b-nav-form>
      </b-navbar>
      <div style="margin-bottom: 20px;">
        <table class="table table-hover" align="center" style="width: 50%">
          <thead>
          <tr class="table-primary" style="font-weight: bold; color: white; font-size: medium">
            <th scope="row">선택</th>
            <th scope="row">플랫폼 명</th>
          </tr>
          </thead>
          <tbody>
          <tr :key="index" v-for="(p,index) in platform">
            <td> <p style="color: white; float: left">{{ p.plat_code }}</p></td>
            <td> <a v-on:click="modiPopup(p.plat_code)">{{ p.plat_name }}</a></td>
          </tr>
          </tbody>
        </table>
      </div>
      <!--popup div-->
      <div v-if="addOpen === true" id="md_addPlat" class="modal-dialog"></div>
      <div v-if="modiOpen === true" id="md_modiPlat"></div>
    </b-card>
  </div>
</template>

<!--
<div style="margin:20px; color: lightseagreen">
      <h1 align="center">
        {{ title }}
      </h1>
      <b-button v-b-toggle.sideMenu>메뉴보기</b-button>
      <b-sidebar id="sideMenu" title="메뉴" shadow #default="{ hide }">
        <nav class="mb-3">
          <b-nav vertical>
            <b-nav-item href="./ContList.vue">계약 관리</b-nav-item>
            <b-nav-item href="./BusiList.vue" >사업자 관리</b-nav-item>
            <b-nav-item active @click="hide">플랫폼 관리</b-nav-item>
          </b-nav>
        </nav>
        <div align="center" style="justify-content: center">
          <b-button variant="success" block @click="hide" size="sm" style="width: 20vh; justify-content: center">메뉴 닫기</b-button>
        </div>
      </b-sidebar>
    </div>
          /*const checked = document.querySelector('input[name="rd_chk"]:checked')
      if (checked === null) alert('수정할 플랫폼 레코드를 선택해주세요!')
      else {
        const platCode = checked.nextElementSibling.textContent */
        /*searchPlat: function () {
      const platName = document.getElementById('search_plat_name').value
      console.log(platName)
      if (platName === '') {

/!*
        const getURI = 'http://csd-platform-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr:8080/platforms?'
*!/
        const getURI = 'http://localhost:8081/jisun/platforms?'
        axios.get(`${getURI}`)
          .then((response) => {
            console.log(response)
            this.platform = response.data
          })
          .catch(error => console.log(error))
      } else {

/!*
        const getURI02 = 'http://csd-platform-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr:8080/platform?plat_name=' + platName
*!/
        const getURI02 = 'http://localhost:8081/jisun/platform?plat_name=' + platName
        console.log(getURI02)
        axios.get(`${getURI02}`)
          .then((response) => {
            console.log(response)
            if (response.data === '') alert('해당 플랫폼 레코드가 없습니다!')
            else this.platform = response.data
          })
          .catch(error => console.log(error))
      }
    }*/
-->

<script>
import axios from 'axios'
import PlatAdd from "./PlatAdd"
import PlatModify from "./PlatModify"
import VModal from 'vue-js-modal'
import Vue from 'vue'

Vue.use(VModal, {dynamic: true})
export default {
  name: 'PlatList',
  data() {
    return {
      addOpen: false,
      modiOpen: false,
      platform: []
    }
  },
  created() {

/*
    const getURI = 'http://csd-platform.licensemgmt.svc:8080/platforms?'
*/
    let getURI = 'http://localhost:8081/jisun/platforms?'
/*
    let getURI = 'http://csd-platform-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr:8080/platforms?'
*/

    axios.get(`${getURI}`)
      .then((response) => {
        console.log(response)
        this.platform = response.data
      })
      .catch(error => console.log(error))
  },
  methods: {
    addPopup: function () {
      this.addOpen = true
      this.$modal.show(PlatAdd, {
        modal: this.$modal
      })
    },
    modiPopup: function (platCode) {
      console.log(platCode)
      this.modiOpen = true
      this.$modal.show(PlatModify, {
        modal: this.$modal,
        'platCode': platCode
      })
    }
  }
}
</script>

<style scoped>

</style>


