<template>
  <div class="add_busi">
    <h1 id='header'>
      {{ title }}
    </h1>
    <div id="addBusiForm">
      <div>
        <label> 계약사명 </label>
        <input id='busi_name' placeholder='kt ds' type='text' v-model="business.busi_name">
      </div>
      <div>
        <label> 사업자번호 </label>
        <input id='busi_num' placeholder='00-000-00000' type='text' v-model="business.busi_num">
      </div>
      <div>
        <label> 담당자명 </label>
        <input id='busi_admin_name' placeholder='Alice' type='text' v-model="business.busi_admin_name">
      </div>
      <div>
        <label> 담당자 이메일 </label>
        <input id='busi_admin_email' type='email' placeholder='abc@kt.com' v-model="business.busi_admin_email">
      </div>
      <div>
        <label> 담당자 연락처 </label>
        <input id='busi_admin_phone' placeholder='010-1111-2222' type='text' v-model="business.busi_admin_phone">
      </div>
      <div>
        <label> 담당자타입 </label>
        <select name='busi_admin_type' v-model="business.busi_admin_type">
          <option value="RS">RS</option>
          <option value="year">year</option>
          <option value="core">core</option>
        </select>
      </div>
      <div>
        <label> 등록일 </label>
        <input name='busi_date' id='busi_date' placeholder='20200322' type='date' v-model="business.busi_date">
      </div>
      <div>
        <button id='btn_addBusiPop' @click="enrollBusi">
          등록
        </button>
        <button id='btn_cancleBusiPop' @click="$emit('close')">
          취소
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'BusiAdd',
  data () {
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
    async enrollBusi () {
      let busi = new FormData()
      busi.append('busi_name', this.business.busi_name)
      busi.append('busi_num', this.business.busi_num)
      busi.append('busi_admin_name', this.business.busi_admin_name)
      busi.append('busi_admin_type', this.business.busi_admin_type)
      busi.append('busi_admin_phone', this.business.busi_admin_phone)
      busi.append('busi_admin_email', this.business.busi_admin_email)
      busi.append('busi_date', this.business.busi_date)

      const baseURI = 'http://localhost:8082/jisun/business?'
      axios.post(`${baseURI}`, busi).then((response) => {
        if (response.status.toString() === '200') {
          alert('사업자 정보가 추가되었습니다!')
        }
        this.$emit('close')
      })
        .catch(error => console.log(error))
    }
  }
}
</script>

<style scoped>

</style>
