<template>
  <div class="register_busi">
    <h1 id='header'>
      {{ title }}
    </h1>
    <form @submit.prevent="enrollBusi">
      <div>
        <label> 계약사명 </label>
        <input id='busi_name' placeholder='kt ds' type='text' v-model="busi_name">
      </div>
      <div>
        <label> 사업자번호 </label>
        <input id='busi_num' placeholder='00-000-00000' type='text' v-model="busi_num">
      </div>
      <div>
        <label> 담당자명 </label>
        <input id='busi_admin_name' placeholder='Alice' type='text' v-model="busi_admin_name">
      </div>
      <div>
        <label> 담당자 이메일 </label>
        <input id='busi_admin_email' type='email' placeholder='abc@kt.com' v-model="busi_admin_email">
      </div>
      <div>
        <label> 담당자 연락처 </label>
        <input id='busi_admin_phone' placeholder='010-1111-2222' type='text' v-model="busi_admin_phone">
      </div>
      <div>
        <label> 담당자타입 </label>
        <select name='busi_admin_type' v-model="busi_admin_type">
          <option value="RS">RS</option>
          <option value="year">year</option>
          <option value="core">core</option>
        </select>
      </div>
      <div>
        <label> 등록일 </label>
        <input name='busi_date' id='busi_date' placeholder='20200322' type='date' v-model="busi_date">
      </div>
      <div>
        <button id='enroll_busi'>
          등록
        </button>
      </div>
    </form>
  </div>
</template>

<script>

export default {
  name: 'RegisterBusi',
  data () {
    return {
      title: 'Register new Business!',
      busi_name: '',
      busi_num: '',
      busi_admin_name: '',
      busi_admin_email: '',
      busi_admin_phone: '',
      busi_admin_type: '',
      busi_date: ''
    }
  },
  methods: {
    async enrollBusi () {
      const busiData = {
        busi_name: this.busi_name,
        busi_num: this.busi_num,
        busi_admin_name: this.busi_admin_name,
        busi_admin_email: this.busi_admin_email,
        busi_admin_phone: this.busi_admin_phone,
        busi_admin_type: this.busi_admin_type,
        busi_date: this.busi_date
      }
      const response = await registerBusi (busiData)
      if (response.status === 200) {
        console.log('success to register new business!')
        alert('등록했습니다!')
        this.$router.push('/contractMgmt')
      } else {
        alert(response.data)
      }
    }
  }
}
</script>

<style scoped>

</style>
