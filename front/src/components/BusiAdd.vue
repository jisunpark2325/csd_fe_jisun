<template>
  <div class="modal-dialog">
    <div id="addBusiForm" style="max-width: 100rem; align-items: center;" align="center">
      <b-card title="사업자 추가">
        <b-row class="my-1">
          <b-col sm="4" style="align-items: center">
            <label for='busi_name'>사업자 명 :</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='busi_name' placeholder='kt ds' v-model="business.busi_name" :type="text"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_num'>사업자 번호 :</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='busi_num' placeholder='00-000-00000' type='text'
                          v-model="business.busi_num"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_admin_name'>담당자 명 :</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='busi_admin_name' placeholder='Alice' type='text'
                          v-model="business.busi_admin_name"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_admin_email'>담당자 이메일 :</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='busi_admin_email' type='email' placeholder='abc@kt.com'
                          v-model="business.busi_admin_email"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_admin_phone'>담당자 연락처 :</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='busi_admin_phone' placeholder='010-1111-2222' type='text'
                          v-model="business.busi_admin_phone"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_admin_type'>담당자 타입 :</label>
          </b-col>
          <b-col sm="7">
            <b-form-select name='busi_admin_type' v-model="business.busi_admin_type"
                           :options="['운영담당자', '대표담당자']"></b-form-select>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_date'>등록일 :</label>
          </b-col>
          <b-col sm="7">
            <b-form-input name='busi_date' id='busi_date' placeholder='20200322' type='date'
                          v-model="business.busi_date"></b-form-input>
          </b-col>
        </b-row>
        <b-form-group style="margin-top: 10px;">
          <b-button id='btn_addBusiPop' class="btn btn-info" @click="enrollBusi" style="margin: 10px;">
            등록
          </b-button>
          <b-button id='btn_cancleBusiPop' class="btn btn-danger disabled" @click="$emit('close')" style="margin: 10px;">
            취소
          </b-button>
        </b-form-group>
      </b-card>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'BusiAdd',
  data() {
    return {
      title: 'Add a new Busieness',
      business: [
        this.busi_name === '',
        this.busi_num === '',
        this.busi_admin_name === '',
        this.busi_admin_email === '',
        this.busi_admin_type === '',
        this.busi_admin_phone === '',
        this.busi_date === ''
      ]
    }
  },
  methods: {
    async enrollBusi() {
      let busi = new FormData()
      busi.append('busi_name', this.business.busi_name)
      busi.append('busi_num', this.business.busi_num)
      busi.append('busi_admin_name', this.business.busi_admin_name)
      busi.append('busi_admin_type', this.business.busi_admin_type)
      busi.append('busi_admin_phone', this.business.busi_admin_phone)
      busi.append('busi_admin_email', this.business.busi_admin_email)
      busi.append('busi_date', this.business.busi_date)

      const postURI = 'http://csd-business-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr:8080/business?'
/*
      const postURI = 'http://localhost:8082/jisun/business?'
*/
      axios.post(`${postURI}`, busi).then((response) => {
        if (response.status.toString() === '200') {
          alert('사업자 정보가 추가되었습니다!')
          this.$emit('close')
        }
      })
        .catch(error => console.log(error))
    }
  }
}
</script>

<style scoped>

</style>
