<template>
  <div class="modal-dialog">
    <div id="addPlatForm" style="max-width: 100rem;" align="center">
      <b-card title="플랫폼 추가">
        <b-row class="my-1">
          <b-col sm="4">
            <label for='plat_name'>플랫폼 명 :</label>
          </b-col>
          <b-col sm="7">
            <b-form-input id='plat_name' placeholder='FlyingCube' v-model="platform.plat_name" :type="text"></b-form-input>
          </b-col>
        </b-row>
        <b-form-group style="margin-top: 10px;">
          <b-button id='btn_addPlatPop' class="btn btn-info" @click="enrollPlat" style="margin: 10px;">
            등록
          </b-button>
          <b-button id='btn_canclePlatPop' class="btn btn-danger disabled" @click="$emit('close')" style="margin: 10px;">
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
  name: "PlatAdd",
  data () {
    return {
      title: 'Add a new Platform',
      platform: [
        this.plat_name === ''
      ]
    }
  },
  methods: {
    async enrollPlat () {
      let plat = new FormData()
      plat.append('plat_name', this.platform.plat_name)

      const postURI = 'http://csd-platform-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr:8080/platform?'
/*
      const postURI = 'http://localhost:8081/jisun/platform?'
*/
      axios.post(`${postURI}`, plat).then((response) => {
        if (response.status.toString() === '200') {
          alert('플랫폼 정보가 추가되었습니다!')
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
