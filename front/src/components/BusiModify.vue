<template>
  <div class="modi_busi">
    <h1 id='header'>
      {{ title }}
    </h1>
    <div id="modiBusiForm">
      <div>
        <label> 계약사명 </label>
        <input id='busi_name' placeholder='kt ds' type='text' v-model="business.busi_name" value="">
      </div>
      <div>
        <label> 사업자번호 </label>
        <input id='busi_num' placeholder='00-000-00000' type='text' v-model="business.busi_num" value="">
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
        <select id='busi_admin_type' name='busi_admin_type' v-model="business.busi_admin_type">
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
        <button id='btn_modiBusiPop' @click="modiBusi">
          수정
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
  name: 'BusiModify',
  data () {
    return {
      title: 'Modify a new Busieness',
      business: [ ]
    }
  },
  props: {
    busiCode: Number,
    required: true,
    default: 0
  },
  mounted () {
    const busiCode = this.busiCode
    console.log('busi_code = ' + busiCode)
    const baseURI = 'http://localhost:8082/jisun/business?busi_code=' + busiCode
    axios.get(`${baseURI}`)
      .then((response) => {
        console.log(response)
        this.business = response.data[0]
        document.getElementById('busi_name').value = response.data[0].busi_name
        document.getElementById('busi_num').value = response.data[0].busi_num
        document.getElementById('busi_admin_name').value = response.data[0].busi_admin_name
        document.getElementById('busi_admin_type').value = response.data[0].busi_admin_type
        document.getElementById('busi_admin_phone').value = response.data[0].busi_admin_phone
        document.getElementById('busi_admin_email').value = response.data[0].busi_admin_email
        document.getElementById('busi_date').value = response.data[0].busi_date
      })
      .catch(error => console.log(error))
  },
  methods: {
    async modiBusi () {
      let busi = new FormData()
      busi.append('busi_code', this.busiCode)
      busi.append('busi_name', this.business.busi_name)
      busi.append('busi_num', this.business.busi_num)
      busi.append('busi_admin_name', this.business.busi_admin_name)
      busi.append('busi_admin_type', this.business.busi_admin_type)
      busi.append('busi_admin_phone', this.business.busi_admin_phone)
      busi.append('busi_admin_email', this.business.busi_admin_email)
      busi.append('busi_date', this.business.busi_date)

      const baseURI = 'http://localhost:8082/jisun/business?'
      axios.put(`${baseURI}`, busi).then((response) => {
        if (response.status.toString() === '200') {
          alert('사업자 정보가 수정되었습니다!')
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
