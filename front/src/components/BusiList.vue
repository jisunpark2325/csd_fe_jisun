<template>
  <div class="list_busi" padding="10px">
    <b-card text-variant="green" style="width: auto; margin: auto; margin-top: 10px; max-height: 150rem;"
            class="mb-2" border-variant="info" align="center">
      <div style="margin-bottom: 10px">
        <h3 style="font-weight: bold">
          사업자 리스트
        </h3>
      </div>
      <b-navbar type="light" variant="light" align="center" style="display: grid; justify-content: right">
        <b-nav-form>
          <b-button @click="addPopup" id='btn_addBusi' type="button" class="btn btn-warning"
                    style="margin-left: 10px; margin-right: 10px; font-weight: bold"> 추가
          </b-button>
          <!--<b-button class="btn btn-outline-info" @click="modiPopup" id='btn_modiBusi' variant="outline-success"
                    style="margin-left: 10px; margin-right: 10px"> 수정
          </b-button>-->
          <!--<b-button @click="delPopup" id='btn_delBusi' type="button" class="btn btn-danger"
                    style="margin-left: 10px; margin-right: 10px"> 삭제
          </b-button>-->
        </b-nav-form>
      </b-navbar>
      <div>
        <b-navbar type="light" variant="light" margin="10px" style="align-items: center; justify-content: center">
          <b-nav-form>
            <b-form-input id='search_busi_name' placeholder='검색 할 사업자명을 입력하세요' type='text' v-model="search_busi_name"
                          style="margin-left: 10px; margin-right: 10px; width: 50vh">{{search_busi_name}}</b-form-input>
            <b-button @click="searchBusi" id="btn_searchBusi" type="button" variant="info"
                      style="margin-left: 10px; margin-right: 10px"> 검색
            </b-button>
          </b-nav-form>
        </b-navbar>
      </div>
      <div style="margin-bottom: 20px;">
        <table class="table table-hover" align="center">
          <thead>
          <tr class="table-primary" style="font-weight: bold; color: white; font-size: medium">
            <th scope="row">번호</th>
            <th scope="row">사업자번호</th>
            <th scope="row">사업자명(회사)</th>
            <th scope="row">담당자명</th>
            <th scope="row">담당자 유형</th>
            <th scope="row">담당자 연락처</th>
            <th scope="row">담당자 이메일</th>
            <th scope="row">등록일</th>
          </tr>
          </thead>
          <tbody>
          <tr :key="index" v-for="(b,index) in business" @click="modiPopup(b.busi_code)">
            <td><a>{{ index }}
              <p style="color: white; float: left" hidden>{{ b.busi_code }}</p></a></td>
            <td>{{ b.busi_num }}</td>
            <td>{{ b.busi_name }}</td>
            <td>{{ b.busi_admin_name }}</td>
            <td>{{ b.busi_admin_type }}</td>
            <td>{{ b.busi_admin_phone }}</td>
            <td>{{ b.busi_admin_email }}</td>
            <td>{{ b.busi_date }}</td>
          </tr>
          </tbody>
        </table>
      </div>
      <!--popup div-->
      <div v-if="addOpen === true" id="md_addBusi" class="modal-dialog"></div>
      <div v-if="modiOpen === true" id="md_modiBusi"></div>
      <!-- search & add, modity, delete buttons -->

    </b-card>
  </div>
</template>
<!--
      <h1 align="center">
        {{ title }}
      </h1>
<div>
      <b-button v-b-toggle.sideMenu>메뉴보기</b-button>
      <b-sidebar id="sideMenu" title="메뉴" shadow #default="{ hide }">
        <nav class="mb-3">
          <b-nav vertical>
            <b-nav-item href="./ContList.vue">계약 관리</b-nav-item>
            <b-nav-item active @click="hide">사업자 관리</b-nav-item>
            <b-nav-item href="platformMgmt">플랫폼 관리</b-nav-item>
            <router-link to="/platformMgmt">플랫폼 관리2</router-link>
          </b-nav>
        </nav>
        <div align="center" style="justify-content: center">
          <b-button variant="success" block @click="hide" size="sm" style="width: 20vh; justify-content: center">메뉴 닫기</b-button>
        </div>
      </b-sidebar>
    </div>
    <router-view></router-view>
      this.$modal.show(BusiAdd, {
        modal: this.$modal
      })
-->
<script>
import axios from 'axios'
import BusiAdd from './BusiAdd'
import BusiModify from './BusiModify'
import VModal from 'vue-js-modal'
import Vue from 'vue'

Vue.use(VModal, {dynamic: true})
export default {
  name: 'BusiList',
  data() {
    return {
      addOpen: false,
      modiOpen: false,
      search_busi_name: '',
      business: []
    }
  },
  created() {
    const getURI = 'http://csd-business-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/businesses?'

/*
    const getURI = 'http://localhost:8082/jisun/businesses?'
*/
    axios.get(`${getURI}`)
      .then((response) => {
        console.log(response)
        this.business = response.data
      })
      .catch(error => console.log(error))
  },
  methods: {
    addPopup: function () {
      this.addOpen = true
      this.$modal.show(BusiAdd, {
        modal: this.$modal
      }, {
        height: 'auto'
      })
    },
    modiPopup: function (busiCode) {
      console.log(busiCode)
      this.modiOpen = true
      this.$modal.show(BusiModify, {
        modal: this.$modal,
        'busiCode': busiCode
      }, {
        height: 'auto'
      })
    },

  searchBusi: function () {
/*
    const busiName = document.getElementById('search_busi_name').value
*/
    console.log(this.search_busi_name)
    if (this.search_busi_name === '') {
      const getURI = 'http://csd-business-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/businesses?'
/*
      const getURI = 'http://localhost:8082/jisun/businesses?'
*/
      axios.get(`${getURI}`)
        .then((response) => {
          console.log(response)
          this.business = response.data
        })
        .catch(error => console.log(error))
    } else {

      const getURI02 = 'http://csd-business-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/business?busi_name=' + this.search_busi_name
 /*     const getURI02 = 'http://localhost:8082/jisun/business?busi_name=' + this.search_busi_name*/
      console.log(getURI02)
      axios.get(`${getURI02}`)
        .then((response) => {
          console.log(response)
          if (response.data === '') alert('해당 사업자 정보가 없습니다!')
          else this.business = response.data
        })
        .catch(error => console.log(error))
    }
  }
}}
</script>

<style scoped>

</style>
/*
axios.get("http://deploy-business.jisun-test.svc:8080/jisun/businesses?").then(response => (this.business = response))
*/
/*delPopup: function () {
const checked = document.querySelector('input[name="rd_chk"]:checked')
if (checked === null) alert('삭제할 사업자 레코드를 선택해주세요!')
else if (confirm('해당 사업자 레코드를 삭제하시겠습니까?') === true) {
const busiName = checked.parentElement.nextElementSibling.nextElementSibling.textContent
console.log(busiName)

const delURI = 'http://csd-business-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/business?busi_name=' + busiName
/!*
const delURI = 'http://localhost:8082/jisun/business?busi_name=' + busiName
*!/
axios.delete(`${delURI}`)
.then((response) => {
console.log(response)
alert('삭제되었습니다!')
})
.catch(error => console.log(error))
} else {
window.close()
}
},*/
