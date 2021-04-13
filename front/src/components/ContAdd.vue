<template>
  <div style="height: auto" class="mymodal">
    <div id="addContForm" style="max-width: 100rem; align-items: center;" align="center">
      <b-card title="계약 추가">
        <b-row class="my-1">
          <b-col sm="4" style="align-items: center">
            <label for='plat_name'>솔루션</label>
          </b-col>
          <b-col sm="7">
            <b-form-select id='plat_name' placeholder='솔루션선택' v-model="contract.plat_name">
              <option v-for="pname in platform" v-bind:value="pname.plat_name">{{ pname.plat_name }}</option>
            </b-form-select>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_name'>사업자</label>
          </b-col>
          <b-col sm="7">
            <b-form-select id='busi_name' placeholder='사업자선택' v-model="contract.busi_name">
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
            <b-form-select id='cont_type' v-model="contract.cont_type"
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
                          v-model="contract.cont_all_price" value="0">
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
                          v-model="contract.cont_memo"></b-form-input>
          </b-col>
        </b-row>
        <b-form-group style="margin-top: 10px;">
          <b-button id='btn_addContPop' class="btn btn-info" @click="enrollCont" style="margin: 10px;">
            추가
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
import axios from 'axios'

export default {
  name: 'ContAdd',
  data() {
    return {
      title: 'Add a new Contract',
      salePrice: 0,
      discount: 0,
      contract: [
        this.plat_code === 0,
        this.plat_name === '',
        this.busi_code === 0,
        this.busi_name === '',
        this.cont_start_date === '',
        this.cont_end_date === '',
        this.cont_type === '',
        this.cont_core === 0,
        this.cont_sale_price === 0,
        this.cont_discount === 0,
        this.cont_all_price === 0,
        this.cont_status === '',
        this.cont_memo === ''
      ]
    }
  },
  props: {
    platform: Array,
    business: Array
  },
  created() {
    console.log('=== ContAdd.vue ===')
    console.log('platform - ', this.platform)
    console.log('business - ', this.business)
  },
  methods: {
    enrollCont: function () {
      console.log('=== ContAdd.vue / enrollCont ===')

      for (let p of this.platform) {
        if (p.plat_name === this.contract.plat_name)
          this.contract.plat_code = p.plat_code
      }

      for (let b of this.business) {
        if (b.busi_name === this.contract.busi_name)
          this.contract.busi_code = b.busi_code
      }
      //console.log(this.contract)

      //sale_price, discount의 default는 0
      if (this.contract.cont_sale_price > this.salePrice)
        this.salePrice = this.contract.cont_sale_price

      if (this.contract.cont_discount > this.discount)
        this.discount = this.contract.cont_discount

      console.log('enrollCont - totalPrice : ', this.contract.cont_all_price)
      //계약 총 금액이 0원이면 alert. 아닐때만 계약 추가
      if (this.contract.cont_all_price === undefined)
        alert('계약 총 금액이 0원입니다. 확인해주세요')
      else {
        let cont = new FormData()
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

              const postURI = 'http://csd-contract-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/contract?'
/*
        const postURI = 'http://localhost:8083/contract?'
*/

        axios.post(`${postURI}`, cont).then((response) => {
          if (response.status.toString() === '200') {
            console.log(response.data)
            alert('계약 정보가 추가되었습니다!')
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
