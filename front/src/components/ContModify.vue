<template>
  <div style="height: auto">
    <div id="modiContForm" style="max-width: 100rem; align-items: center;" align="center">
      <b-card title="계약 수정">
        <b-row class="my-1">
          <b-col sm="4" style="align-items: center">
            <label for='plat_name'>솔루션</label>
          </b-col>
          <b-col sm="7">
            <b-form-select name='plat_name' placeholder='솔루션선택' v-model="contract.plat_name">
              <option v-for="pname in platform" v-bind:value="pname.plat_name">{{ pname.plat_name }}</option>
            </b-form-select>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_name'>사업자</label>
          </b-col>
          <b-col sm="7">
            <b-form-select name='busi_name' placeholder='사업자선택' v-model="contract.busi_name">
              <option v-for="bname in business" v-bind:value="bname.busi_name">{{ bname.busi_name }}</option>
            </b-form-select>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='cont_start_date'>계약 시작일</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='cont_start_date' placeholder='2021-04-12' type='date'
                          v-model="contract.cont_start_date"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='cont_end_date'>계약 만료일</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='cont_end_date' type='date' placeholder='2021-04-12'
                          v-model="contract.cont_end_date"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='cont_type'>계약 타입</label>
          </b-col>
          <b-col sm="7">
            <b-form-select name='cont_type' v-model="contract.cont_type"
                           :options="['RS', 'core']" type="text"></b-form-select>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='cont_core'>계약 core 수</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='cont_core' type='number' placeholder='0'
                          v-model="contract.cont_core"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='cont_sale_price'>계약 판매가(원)</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='cont_sale_price' type='number' placeholder='0'
                          v-model="contract.cont_sale_price" value="0">{{ salePrice }}
            </b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='cont_discount'>계약 할인율(%)</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='cont_discount' type='number' placeholder='0'
                          v-model="contract.cont_discount" value="0">{{ discount }}
            </b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='cont_all_price'>계약 총 금액(원)</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='cont_all_price' type='number' placeholder='0'
                          v-model="contract.cont_all_price" value="0" @keyup="formatComma()"> {{ totalPrice }}
            </b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='cont_status'>상태</label>
          </b-col>
          <b-col sm="7">
            <b-form-select name='cont_status' v-model="contract.cont_status"
                           :options="['예약', '계약', '중단', '종료']"></b-form-select>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='cont_memo'>비고</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='cont_memo' type='text' placeholder='계약 관련 사항'
                          v-model="contract.cont_memo">{{memo}}</b-form-input>
          </b-col>
        </b-row>
        <b-form-group style="margin-top: 10px;">
          <b-button id='btn_addContPop' class="btn btn-info" @click="modiCont" style="margin: 10px;">
            수정
          </b-button>
          <b-button id='btn_cancleContPop' class="btn btn-danger disabled" @click="$emit('close')"
                    style="margin: 10px;">
            닫기
          </b-button>
        </b-form-group>
      </b-card>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ContModify",
  data() {
    return {
      title: 'Modify this Contract',
      salePrice: 0,
      discount: 0,
      memo: '',
      contract: []
    }
  },
  props: {
    contCode: Number,
    platform: Array,
    business: Array
  },
  mounted() {
    console.log('=== ContModify.vue ===')
    console.log('platform - ', this.contCode)
    console.log('type of contCode - ', typeof this.contCode)
    console.log('platform - ', this.platform)
    console.log('business - ', this.business)

    const contCode = this.contCode
        const getURI = 'http://csd-contract-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/contract?cont_code=' + contCode
/*    const getURI = 'http://localhost:8083/contract?cont_code=' + contCode*/
    axios.get(`${getURI}`)
      .then((response) => {
        console.log(response)
        this.contract = response.data

        for (let p of this.platform) {
          if (p.plat_code === this.contract.cont_plat_code)
            this.contract.plat_name = p.plat_name
        }

        for (let b of this.business) {
          if (b.busi_code === this.contract.cont_busi_code)
            this.contract.busi_name = b.busi_name
        }

        document.getElementById('plat_name').value = this.contract.plat_name
        document.getElementById('busi_name').value = this.contract.busi_name
        document.getElementById('cont_start_date').value = response.data[0].cont_start_date
        document.getElementById('cont_end_date').value = response.data[0].cont_end_date
        document.getElementById('cont_type').value = response.data[0].cont_type
        document.getElementById('cont_core').value = response.data[0].cont_core
        document.getElementById('cont_sale_price').value = response.data[0].cont_sale_price
        document.getElementById('cont_discount').value = response.data[0].cont_discount
        document.getElementById('cont_all_price').value = response.data[0].cont_all_price
        document.getElementById('cont_status').value = response.data[0].cont_status
        document.getElementById('cont_memo').value = response.data[0].cont_memo
      })
      .catch(error => console.log(error))
  },
  methods: {
    modiCont: function () {
      console.log('=== ContModify.vue / modiCont ===')
      console.log('modiCont - cont_code : ', this.contCode)
      console.log('modiCont - cont_memo : ', this.contract.cont_memo)

      for (let p of this.platform) {
        if (p.plat_name === this.contract.plat_name)
          this.contract.plat_code = p.plat_code
      }

      for (let b of this.business) {
        if (b.busi_name === this.contract.busi_name)
          this.contract.busi_code = b.busi_code
      }

      //비고란에 기본 공백 넣기
      if (this.contract.cont_memo === undefined)
        this.contract.cont_memo = ''

      //sale_price, discount의 default는 0
      if (this.contract.cont_sale_price > this.salePrice)
        this.salePrice = this.contract.cont_sale_price

      if (this.contract.cont_discount > this.discount)
        this.discount = this.contract.cont_discount

      //계약 총 금액이 0원이면 alert. 아닐때만 계약 추가
      if (this.contract.cont_all_price === '0')
        alert('계약 총 금액이 0원입니다. 확인해주세요')
      else {
        let cont = new FormData()
        cont.append('cont_code', this.contCode)
        cont.append('plat_code', this.contract.plat_code)
        cont.append('busi_code', this.contract.busi_code)
        cont.append('start_date', this.contract.cont_start_date)
        cont.append('end_date', this.contract.cont_end_date)
        cont.append('type', this.contract.cont_type)
        cont.append('core', this.contract.cont_core)
        cont.append('sale_price', this.salePrice)
        cont.append('discount', this.discount)
        cont.append('all_price', this.contract.cont_all_price)
        cont.append('status', this.contract.cont_status)
        cont.append('memo', this.contract.cont_memo)

              const putURI = 'http://csd-contract-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/contract?'
/*        const putURI = 'http://localhost:8083/contract?'*/
        axios.put(`${putURI}`, cont).then((response) => {
          if (response.status.toString() === '200') {
            console.log(response.data)
            alert('계약 정보가 수정되었습니다!')
            this.$emit('close')
          }
        })
          .catch(error => console.log(error))
      }
    }
  }
}
</script>

<style scoped>

</style>
/*
<!--<button id='btn_delContPop' class="btn btn-outline-danger" @click="delCont"
                    style="margin: 10px;">
            삭제
          </button>-->
delCont: function () {
console.log('=== ContModify.vue / delCont ===')
console.log('delCont - cont_code : ', this.contCode)

if (confirm('해당 계약 정보를 삭제하시겠습니까?') === true) {
const delURI = 'http://csd-contract-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/contract?cont_code=' + this.contCode
/!*
const delURI = 'http://localhost:8083/contract?cont_code=' + this.contCode
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
}*/
