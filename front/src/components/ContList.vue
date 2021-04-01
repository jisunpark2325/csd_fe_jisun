<template>
  <div class="list_plat">
    <table>
      <thead>
      <tr>
        <th>사업자번호</th>
        <th>사업자명(회사)</th>
        <th>담당자명</th>
        <th>담당자 유형</th>
        <th>담당자 연락처</th>
        <th>담당자 이메일</th>
        <th>등록일</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(b,index) in business" :key="index">
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
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      business: []
    }
  },
  created: function () {
    const baseURI = 'http://localhost:8082/jisun/business'
    axios.get(`${baseURI}es?`)
      .then((response) => {
        console.log(response)
        this.business = response.data
      })
      .catch(error => console.log(error))
  }
}
</script>

<style scoped>

</style>
/*
axios.get("http://deploy-business.jisun-test.svc:8080/jisun/businesses?").then(response => (this.business = response))
*/
