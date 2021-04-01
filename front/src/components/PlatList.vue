<template>
  <div class="list_plat">
    <table>
      <thead>
      <tr>
        <th>플랫폼명</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(p,index) in platform" :key="index">
        <td>{{ p.plat_name }}</td>
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
      platform: []
    }
  },
  created: function () {
    const baseURI = 'http://localhost:8081/jisun/platform'
    axios.get(`${baseURI}s?`)
      .then((response) => {
        console.log(response)
        this.platform = response.data
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

