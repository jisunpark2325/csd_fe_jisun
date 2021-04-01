<template>
  <div class="list_busi">
    <b-card header="사업자 관리 페이지" style="max-width: 120rem; margin: auto; margin-top: 10vh; max-height: 150rem;"
            class="mb-2" border-variant="info" align="center" title="사업자 관리">
    <table>
      <thead>
        <tr>
          <td>선택</td>
          <td>사업자번호</td>
          <td>사업자명(회사)</td>
          <td>담당자명</td>
          <td>담당자 유형</td>
          <td>담당자 연락처</td>
          <td>담당자 이메일</td>
          <td>등록일</td>
        </tr>
      </thead>
      <tbody>
        <tr :key="index" v-for="(b,index) in business">
          <td><input type="radio" id="rd_chk"  name="rd_chk" v-model="rd_chk"></input>
            {{ b.busi_code }}</td>
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

    <div v-if="addOpen === true">
    </div>
    <div v-if="modiOpen === true">
    </div>
    <div>
      <div>
        <input id='search_busi_name' placeholder='검색 할 사업자명을 입력하세요' type='text' v-model="search_busi_name">
        <button @click="searchBusi" id="btn_searchBusi" type="button">검색</button>
        <span></span>
      </div>
      <button @click="addPopup" id='btn_addBusi' type="button">
        추가
      </button>
      <button @click="modiPopup" id='btn_modiBusi' type="button">
        수정
      </button>
      <button @click="delPopup" id='btn_delBusi' type="button">
        삭제
      </button>
    </div>
    </b-card>>
  </div>

</template>

<script>
import axios from 'axios'
import BusiAdd from './BusiAdd'
import VModal from 'vue-js-modal'
import Vue from 'vue'
import BusiModify from './BusiModify'

Vue.use(VModal, { dynamic: true })
export default {
  name: 'BusiList',
  data () {
    return {
      title: '사업자 관리',
      addOpen: false,
      modiOpen: false,
      business: []
    }
  },
  created () {
    const baseURI = 'http://localhost:8082/jisun/business'
    axios.get(`${baseURI}es?`)
      .then((response) => {
        console.log(response)
        this.business = response.data.map(r => r.data())
        console.log(this.business)
      })
      .catch(error => console.log(error))
  },
  methods: {
    addPopup: function () {
      this.addOpen = true
      this.$modal.show(BusiAdd, {
        modal: this.$modal
      })
    },
    modiPopup: function () {
      const busiCode = document.querySelector('input[name="rd_chk"]:checked').nextSibling.textContent
      console.log(busiCode)
      this.modiOpen = true
      this.$modal.show(BusiModify, {
        modal: this.$modal,
        'busiCode': busiCode
      })
    },
    delPopup: function () {
      const busiName = document.querySelector('input[name="rd_chk"]:checked').parentElement.nextElementSibling.nextElementSibling.textContent
      console.log(busiName)

      if (confirm('해당 사업자 레코드를 삭제하시겠습니까?') === true) {
        const baseURI = 'http://localhost:8082/jisun/business?busi_name=' + busiName
        axios.delete(`${baseURI}`)
          .then((response) => {
            console.log(response)
            alert('삭제되었습니다!')
          })
          .catch(error => console.log(error))
      } else {
        window.close()
      }
    },
    searchBusi: function () {
      const busiName = document.getElementById('search_busi_name').value
      console.log(busiName)
    }
  }
}
</script>

<style scoped>

</style>
/*
axios.get("http://deploy-business.jisun-test.svc:8080/jisun/businesses?").then(response => (this.business = response))
*/
