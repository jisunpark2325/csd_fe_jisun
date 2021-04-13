<template>
  <div style="height: auto">
    <div id="modiBusiForm" style="max-width: 100rem;" align="center">
      <b-card title="사업자 수정">
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_name'>사업자</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='busi_name' placeholder='kt ds' v-model="business.busi_name" :type="text"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_num'>사업자 번호</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='busi_num' placeholder='00-000-00000' type='text'
                          v-model="business.busi_num"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_admin_name'>담당자 명</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='busi_admin_name' placeholder='Alice' type='text'
                          v-model="business.busi_admin_name"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_admin_email'>담당자 이메일</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='busi_admin_email' type='email' placeholder='abc@kt.com'
                          v-model="business.busi_admin_email"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_admin_phone'>담당자 연락처</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='busi_admin_phone' placeholder='010-1111-2222' type='text'
                          v-model="business.busi_admin_phone"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_admin_type'>담당자 타입</label>
          </b-col>
          <b-col sm="7">
            <b-form-select name='busi_admin_type' v-model="business.busi_admin_type"
                           :options="['운영담당자', '대표담당자']"></b-form-select>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <label for='busi_date'>등록일</label>
          </b-col>
          <b-col sm="7">
            <b-form-input name='busi_date' id='busi_date' placeholder='20200322' type='date'
                          v-model="business.busi_date"></b-form-input>
          </b-col>
        </b-row>
        <b-form-group style="margin-top: 10px;">
          <b-button id='btn_addBusiPop' @click="modiBusi" class="btn btn-info"style="margin: 10px;">
            수정
          </b-button>
          <b-button id='btn_cancleBusiPop' class="btn btn-danger disabled" @click="$emit('close')" style="margin: 10px;">
            닫기
          </b-button>
          <button id='btn_delBusiPop' class="btn btn-outline-danger" @click="delBusi"
                    style="margin: 10px; color: #ff7851">
            삭제
          </button>
        </b-form-group>
      </b-card>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'BusiModify',
  data () {
    return {
      title: 'Modify the Busieness',
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

    const getURI = 'http://csd-business-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/business?busi_code=' + busiCode
/*    const getURI = 'http://localhost:8082/jisun/business?busi_code=' + busiCode*/
    axios.get(`${getURI}`)
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
    modiBusi: function () {
      let busi = new FormData()
      busi.append('busi_code', this.busiCode)
      busi.append('busi_name', this.business.busi_name)
      busi.append('busi_num', this.business.busi_num)
      busi.append('busi_admin_name', this.business.busi_admin_name)
      busi.append('busi_admin_type', this.business.busi_admin_type)
      busi.append('busi_admin_phone', this.business.busi_admin_phone)
      busi.append('busi_admin_email', this.business.busi_admin_email)
      busi.append('busi_date', this.business.busi_date)

      const putURI = 'http://csd-business-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/business?'
/*      const putURI = 'http://localhost:8082/jisun/business?'*/
      axios.put(`${putURI}`, busi).then((response) => {
        if (response.status.toString() === '200') {
          alert('사업자 정보가 수정되었습니다!')
          this.$emit('close')
        }
      })
        .catch(error => console.log(error))
    },
    delBusi: function () {
      console.log('=== BusiModify.vue / delBusi ===')
      console.log('delBusi - busi_name : ', this.business.busi_name)

      if (confirm('해당 사업자 정보를 삭제하시겠습니까?') === true) {
        const delURI = 'http://csd-business-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/business?busi_name=' + this.business.busi_name
/*        const delURI = 'http://localhost:8082/jisun/business?busi_name=' + this.business.busi_name*/

        axios.delete(`${delURI}`)
          .then((response) => {
            console.log(response)
            alert('삭제되었습니다!')
            this.$emit('close')
          })
          .catch(error => console.log(error))
      } else {
        window.close()
      }
    }
  }
}
</script>

<style scoped>

</style>
