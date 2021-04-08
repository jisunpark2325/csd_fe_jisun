<template>
  <div class="list_cont" padding="10px">
    <b-card text-variant="green" style="width: auto; margin: auto; margin-top: 10px; max-height: 150rem;"
            class="mb-2" border-variant="info" align="center">
      <div style="margin-bottom: 10px">
        <h3 style="font-weight: bold">
          계약 리스트
        </h3>
      </div>
      <div style="margin-bottom: 20px;">
        <table class="table table-hover" align="center">
          <thead>
          <tr class="table-primary" style="font-weight: bold; color: white; font-size: medium">
            <th scope="row">선택</th>
            <th scope="row">플랫폼명</th>
            <th scope="row">사업자명</th>
            <th scope="row">계약 시작일</th>
            <th scope="row">계약 만료일</th>
            <th scope="row">계약 타입</th>
            <th scope="row">계약한 core 수</th>
            <th scope="row">계약 판매가</th>
            <th scope="row">계약 할인율</th>
            <th scope="row">계약 총 비용</th>
            <th scope="row">상태</th>
            <th scope="row">비고</th>
          </tr>
          </thead>
          <tbody>
          <tr :key="index" v-for="(c,index) in contract">
            <td><input type="radio" id="rd_chk" name="rd_chk" v-model="rd_chk" style="float: left; margin-top: 1rem;">
              <p style="color: white; float: left">{{ c.cont_code }}</p></td>
            <td><span id="td_platName"> </span></td>
            <td><span id="td_busiName"></span></td>
            <td>{{ c.cont_start_date }}</td>
            <td>{{ c.cont_end_date }}</td>
            <td>{{ c.cont_type }}</td>
            <td>{{ c.cont_core }}</td>
            <td>{{ c.cont_sale_price }}</td>
            <td>{{ c.cont_discount }}</td>
            <td>{{ c.cont_all_price }}</td>
            <td>{{ c.cont_status }}</td>
            <td>{{ c.cont_memo }}</td>
          </tr>
          </tbody>
        </table>
      </div>
      <!--popup div-->
      <div v-if="addOpen === true" id="md_addCont" class="modal-dialog"></div>
      <div v-if="modiOpen === true" id="md_modiCont"></div>
      <!-- search & add, modity, delete buttons -->
      <div>
        <b-navbar type="light" variant="light" margin="10px" style="align-items: center; justify-content: center">
          <b-nav-form>
            <b-form-input id='search_cont_name' placeholder='검색 할 명을 입력하세요' type='text' v-model="search_cont_name"
                          style="margin-left: 10px; margin-right: 10px; width: 50vh"></b-form-input>
            <b-button @click="searchCont" id="btn_searchCont" type="button" variant="info"
                      style="margin-left: 10px; margin-right: 10px"> 검색
            </b-button>
          </b-nav-form>
        </b-navbar>
        <b-navbar type="light" variant="light" align="center" style="display: flex; justify-content: center">
          <b-nav-form>
            <b-button @click="addPopup" id='btn_addCont' type="button" variant="outline-primary"
                      style="margin-left: 10px; margin-right: 10px"> 추가
            </b-button>
            <b-button class="btn btn-outline-info" @click="modiPopup" id='btn_modiCont' variant="outline-success"
                      style="margin-left: 10px; margin-right: 10px"> 수정
            </b-button>
            <b-button @click="delPopup" id='btn_delCont' type="button" class="btn btn-danger"
                      style="margin-left: 10px; margin-right: 10px"> 삭제
            </b-button>
          </b-nav-form>
        </b-navbar>
      </div>
    </b-card>
  </div>
</template>

<script>
import axios from 'axios'
import VModal from 'vue-js-modal'
import Vue from 'vue'

Vue.use(VModal, {dynamic: true})

function printPlatBusi(platform, platCode, business, busiCode) {
  console.log('pringPlatBusi')

  var platName = ''
  let busiName = ''

  for (let p of platform){
    if (p.plat_code === platCode) {
      console.log(p.plat_name)
      platName = p.plat_name
      document.getElementById('td_platName').innerHTML = platName.toString()
    }
  }

  for (let b of business) {
    if (b.busi_code === busiCode) {
      console.log(b.busi_name)
      busiName = b.busi_name
      document.getElementById('td_busiName').innerHTML = busiName
    }

  }

  return true;
}

function sleep(delay) {
  console.log('delay!')
  var start = new Date().getTime()
  while (new Date().getTime() < start + delay)
    return
}

export default {
  name: 'ContList',
  data() {
    return {
      addOpen: false,
      modiOpen: false,
      contract: [],
      business: [],
      platform: []
    }
  },
  created: async function () {
/*
    let getPlatURI = 'http://csd-platform-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/platforms?'
*/

    let getPlatURI = 'http://localhost:8081/jisun/platforms?'
    console.log('getPlatURI : ', getPlatURI)
    axios.get(`${getPlatURI}`).then((response2) => {
      console.log(response2)
      this.platform = response2.data
    }).catch(error2 => console.log(error2))

    /*
        let getBusiURI = 'http://csd-business-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/businesses?'
    */
    /*
        let getBusiURI = 'http://csd-business.licensemgmt.svc:8080/businesses?'
    */
    let getBusiURI = 'http://localhost:8082/jisun/businesses?'
    console.log('getBusiURI : ', getBusiURI)
    axios.get(`${getBusiURI}`).then((response3) => {
      console.log(response3)
      this.businss = response3.data
    }).catch(error3 => console.log(error3))

    /*
        const getContURI = 'http://csd-contract-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr:8080/contracts?'
    */
    const getContURI = 'http://localhost:8083/jisun/contracts?'
    /*
        const getContURI = 'http://csd-contract.licensemgmt.svc:8080/contracts?'
    */
    let platCode = 0
    let busiCode = 0
    /* let contLen = 0
     let result = false*/
    await axios.get(`${getContURI}`)
      .then((response) => {
        console.log(response)
        this.contract = response.data

        for (let i of response.data) {
          console.log(i)

          platCode = i.cont_plat_code
          console.log('platCode : ', platCode)
          busiCode = i.cont_busi_code
          console.log('busiCode : ', busiCode)

          console.log(this.platform)

          printPlatBusi(this.platform, platCode, this.business, busiCode)
          sleep(300000)

        }
      })
      .catch(error => console.log(error))


  }
}
</script>

<style scoped>
</style>

