<template>
  <div class="modal-dialog">
    <div id="modiPlatForm" style="max-width: 100rem;" align="center">
      <b-card title="플랫폼 수정">
        <b-row class="my-1">
          <b-col sm="4">
            <label for='plat_name'>플랫폼 명 :</label>
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
          <b-button v-on:click="delPopup(platform.plat_code)" id='btn_delPlat' class="btn btn-danger" type="button"
                    variant="secondary"
                    style="margin-left: 10px; margin-right: 10px"> 삭제
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
    const platCode = this.platCode
    console.log('plat_code = ' + platCode)

    /*
        const getURI = 'http://csd-platform-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr:8080/platform?plat_code=' + platCode
    */
    const getURI = 'http://localhost:8081/jisun/platform?plat_code=' + platCode
    axios.get(`${getURI}`).then((response) => {
      console.log(response)
      this.platform = response.data[0]
      document.getElementById('plat_name').value = response.data[0].plat_name
    })
      .catch(error => console.log(error))
  },
  methods: {
    async modiPlat() {
      console.log(this.platform.plat_name)
      let plat = new FormData()
      plat.append('plat_code', this.platCode)
      plat.append('plat_name', this.platform.plat_name)

      /*
            const putURI = 'http://csd-platform-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr:8080/platform?'
      */
      const putURI = 'http://localhost:8081/jisun/platform?'
      axios.put(`${putURI}`, plat).then((response) => {
        console.log(response)
        if (response.status.toString() === '200') {
          alert('플랫폼 정보가 수정되었습니다!')
          this.$emit('close')
        }
      }).catch(error => console.log(error))
    },
    delPopup: function (platCode) {
      console.log(platCode)
      const platName = document.getElementById('plat_name').value
      console.log(platName)
      /*
          const delURI = 'http://csd-platform-licensemgmt.c01-okd.cz-tb.paas.kt.co.kr:8080/platform?plat_name=' + platName
      */
      const delURI = 'http://localhost:8081/jisun/platform?plat_name=' + platName
      axios.delete(`${delURI}`)
        .then((response) => {
          console.log(response)
          alert('삭제되었습니다!')
        })
        .catch(error => console.log(error))
      /*      } else {
              window.close()
            }*/
    }
  }
}
</script>

<style scoped>

</style>
