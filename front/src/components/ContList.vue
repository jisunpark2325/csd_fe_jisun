<template>
  <div class="list_cont" padding="10px">
    <b-card text-variant="green" style="width: auto; margin: auto; margin-top: 10px; max-height: 150rem;"
            class="mb-2" border-variant="info" align="center">
      <div style="margin-bottom: 10px">
        <h3 style="font-weight: bold">
          계약 리스트
        </h3>
      </div>
      <b-navbar type="light" variant="light" align="right" style="display: grid; justify-content: right">
        <b-nav-form>
          <button @click="addPopup" id='btn_addCont' type="button" class="btn btn-warning"
                  style="margin-left: 10px; margin-right: 10px; font-weight: bold"> 추가
          </button>
          <!--<b-button class="btn btn-outline-info" @click="modiPopup" id='btn_modiCont' variant="outline-success"
                    style="margin-left: 10px; margin-right: 10px"> 수정
          </b-button>-->
          <!--<b-button @click="delPopup" id='btn_delCont' type="button" class="btn btn-danger"
                    style="margin-left: 10px; margin-right: 10px"> 삭제
          </b-button>-->
        </b-nav-form>
      </b-navbar>
      <div style="margin-bottom: 20px;">
        <table class="table table-hover" align="center">
          <thead>
          <tr class="table-primary" style="font-weight: bold; color: white; font-size: medium">
            <th scope="row">번호</th>
            <th scope="row">솔루션</th>
            <th scope="row">사업자</th>
            <th scope="row">계약 시작일</th>
            <th scope="row">계약 만료일</th>
            <th scope="row">계약 타입</th>
            <th scope="row">계약한 core 수</th>
            <th scope="row">계약 판매가(원)</th>
            <th scope="row">계약 할인율(%)</th>
            <th scope="row">계약 총 금액(원)</th>
            <th scope="row">상태</th>
            <th scope="row">비고</th>
          </tr>
          </thead>
          <tbody id="tbody">
          <tr :key="index" v-for="(c,index) in contract" @click="modiPopup(c.cont_code)">
            <td><a>{{ index }}
              <p style="color: white; float: left" hidden>{{ c.cont_code }}</p></a></td>
            <td> {{ c.plat_name }}</td>
            <td> {{ c.busi_name }}</td>
            <td>{{ c.cont_start_date }}</td>
            <td id="td_end_date" :class="{highlight : endDateCheck(index, c.cont_end_date)}">{{ c.cont_end_date }}</td>
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
        <!--<b-navbar type="light" variant="light" margin="10px" style="align-items: center; justify-content: center">
          <b-nav-form>
            <b-form-input id='search_cont_name' placeholder='검색 할 명을 입력하세요' type='text' v-model="search_cont_name"
                          style="margin-left: 10px; margin-right: 10px; width: 50vh"></b-form-input>
            <b-button @click="searchCont" id="btn_searchCont" type="button" variant="info"
                      style="margin-left: 10px; margin-right: 10px"> 검색
            </b-button>
          </b-nav-form>
        </b-navbar>-->

      </div>
    </b-card>
  </div>
</template>

<script>
import axios from 'axios'
import VModal from 'vue-js-modal'
import Vue from 'vue'
import ContAdd from "./ContAdd"
import ContModify from "./ContModify"
import vueMoment from "vue-moment"
import moment from 'moment'

Vue.use(VModal, {dynamic: true})
Vue.use(vueMoment)

/*function printName(item, code) {
  console.log('printName - to retrun the name from code')

  let name = ''
  for (let i of item) {
    console.log(i)
    if (i.busi_code === undefined) {
      console.log('this item is platform')
      if (i.plat_code === code) {
        console.log(i.plat_name)
        return (i.plat_name)
      }
    } else {
      console.log('this item is business')
      if (i.busi_code === code) {
        console.log(i.busi_name)
        return (i.busi_name)
      }
    }
  }
}*/

/*function sleep(delay) {
  console.log('delay!')
  var start = new Date().getTime()
  while (new Date().getTime() < start + delay)
    return
}*/

export default {
  name: 'ContList',
  data() {
    return {
      addOpen: false,
      modiOpen: false,
      platName: '',
      busiName: '',
      contract: [],
      business: [],
      platform: [],
      status: ['예약', '계약', '중단', '종료']
    }
  },
  created: async function () {
    let getPlatURI = 'http://csd-platform-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/platforms?'
/*    let getPlatURI = 'http://localhost:8081/jisun/platforms?'*/
    console.log('getPlatURI : ', getPlatURI)
    axios.get(`${getPlatURI}`).then((response2) => {
      console.log(response2)
      this.platform = response2.data

    }).catch(error2 => console.log(error2))

            let getBusiURI = 'http://csd-business-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/businesses?'
    /*        let getBusiURI = 'http://csd-business.licensemgmt.svc:8080/businesses?'*/

/*    let getBusiURI = 'http://localhost:8082/jisun/businesses?'*/
    console.log('getBusiURI : ', getBusiURI)
    axios.get(`${getBusiURI}`).then((response3) => {
      console.log(response3)
      this.business = response3.data
    }).catch(error3 => console.log(error3))

            const getContURI = 'http://csd-contract-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/contracts?'
/*    const getContURI = 'http://localhost:8083/contracts?'*/
    /*
        const getContURI = 'http://csd-contract.licensemgmt.svc:8080/contracts?'
    */
    let platCode = 0
    let busiCode = 0

    await axios.get(`${getContURI}`)
      .then((response) => {
        console.log(response)
        this.contract = response.data

                for (let i of response.data) {
                  console.log(i)

                  let allPrice = i.cont_all_price
                  console.log('allPrice : ', allPrice)
                }

                   /*         sleep(300000)


                  this.$nextTick(() => {
                    console.log(platName, busiName)
                    console.log(this.$refs.platName)
                    this.$refs.platName.innerHTML = platName
                    document.querySelector('td_busiName').innerHTML = busiName
                  })
                }*/
      })
      .catch(error => console.log(error))
  },
  methods: {
    addPopup: function () {
      this.addOpen = true
      this.$modal.show(ContAdd, {
        modal: this.$modal,
        'platform': this.platform,
        'business': this.business
      }, {
        height: 'auto'
      })
    },
    modiPopup: function (contCode) {
      console.log(contCode)
      this.modiOpen = true
      this.$modal.show(ContModify, {
        modal: this.$modal,
        'contCode': contCode,
        'platform': this.platform,
        'business': this.business
      }, {
        height: 'auto'
      })
    },
    sortStatus: function () {

    },
    endDateCheck: function (index, enddate) {
      console.log(enddate)
      console.log(index)
      /*
            let date_diff = moment(enddate, 'YYYY-MM-DD').fromNow()
      */
      let date_end = moment(enddate)
      let date_diff = date_end.diff(moment().format('YYYY-MM-DD'), 'days')
      console.log('date_diff = ', date_diff)

      if (0 < date_diff && date_diff < 62) {
        console.log('만료가 2달이 안 남았습니다')
        alert('만료기한이 2달 미만인 계약이 있습니다!')
        return true
      } else if (date_diff < 0) {
        console.log('이미 지난 계약입니다')
        return false
      } else {
        console.log('아직 만료기한이 남았습니다.')
        return false
      }
    },
    comma: function (str) {
      console.log('comma - str : ', str)
      str = String(str)
      return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,')
    },
    formatComma: function (price) {
      console.log('formatComma - price ', price)
      price.value = this.comma(price)
      console.log(price.value)
    }
  }
}
</script>

<style scoped>
.highlight {
  border: 1px #a7e4cc;
  color: orangered;
  font-weight: bold;
}
</style>

