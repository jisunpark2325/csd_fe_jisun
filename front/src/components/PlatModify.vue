<template>
  <div style="height: auto">
    <div id="modiPlatForm" style="max-width: 100rem;" align="center">
      <b-card title="솔루션 수정">
        <b-row class="my-1">
          <b-col sm="4">
            <label for='plat_name'>솔루션</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='plat_name' placeholder='FlyingCube' v-model="platform.plat_name"
                          :type="text"></b-form-input>
          </b-col>
        </b-row>
        <b-form-group style="margin-top: 10px;">
          <b-button id='btn_addPlatPop' class="btn btn-info" @click="modiPlat" style="margin: 10px;">
            수정
          </b-button>
          <b-button id='btn_canclePlatPop' class="btn btn-danger disabled" @click="$emit('close')"
                    style="margin: 10px;">
            닫기
          </b-button>
          <b-button @click="delPlat()" id='btn_delPlat' class="btn btn-danger" type="button"
                    style="margin-left: 10px; margin-right: 10px">
            삭제
          </b-button>
        </b-form-group>
      </b-card>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "PlatModify",
  data() {
    return {
      title: 'Modify the Platform',
      platform: []
    }
  },
  props: {
    platCode: Number,
    required: true,
    default: 0
  },
  mounted() {
    console.log('=== PlatModify.vue ===')
    console.log('platform - ', this.platCode)

    const platCode = this.platCode

        const getURI = 'http://csd-platform-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/platform?plat_code=' + platCode
/*
    const getURI = 'http://localhost:8081/jisun/platform?plat_code=' + platCode
*/
    axios.get(`${getURI}`).then((response) => {
      console.log(response)
      this.platform = response.data[0]
      document.getElementById('plat_name').value = response.data[0].plat_name
    })
      .catch(error => console.log(error))
  },
  methods: {
    modiPlat: function () {
      console.log(this.platform.plat_name)

      if (this.platform.plat_name === undefined)
        alert('플랫폼 이름 입력해주세요')
      else {
        let plat = new FormData()
        plat.append('plat_code', this.platCode)
        plat.append('plat_name', this.platform.plat_name)

            const putURI = 'http://csd-platform-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/platform?'
/*        const putURI = 'http://localhost:8081/jisun/platform?'*/
        axios.put(`${putURI}`, plat).then((response) => {
          console.log(response)
          if (response.status.toString() === '200') {
            alert('플랫폼 정보가 수정되었습니다!')
            this.$emit('close')
          }
        }).catch(error => console.log(error))
      }
    },
    delPlat: function () {
      console.log('=== PlatModify.vue / delPlat ===')

      console.log(this.platCode)
      const platName = document.getElementById('plat_name').value
      console.log(platName)

      if (confirm('해당 계약 정보를 삭제하시겠습니까?') === true) {

            const delURI = 'http://csd-platform-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr/platform?plat_name=' + platName
/*
        const delURI = 'http://localhost:8081/jisun/platform?plat_name=' + platName
*/
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
