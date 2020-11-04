<template>
<div class="outro">
    <div class="left">
        
    </div>
    <div class="right">
        <!-- 이미지로 변경 -->
        <div class="text-right" style="padding:30px 30px 0px 0px;">
            <img src="../../assets/logo.svg" class="tabtitle">
        </div>
        <!-- 오른쪽 버튼 그룹 -->
        <div class="BtnGroup">
            <v-btn @click="DeleteGroup" color="#8D6262" text x-large><h2>그룹삭제</h2></v-btn>
            <v-btn @click="SecedeGroup" color="#8D6262" text x-large><h2>그룹탈퇴</h2></v-btn>
        </div>
    </div>
</div>
</template>

<script>
import api from '@/utils/api';
import store from '@/store';

export default {
    data() {
    return {
      
    };
  },
  computed: {
      groupInfo: () => store.getters.getSelectedGroup,
  },
  methods: {
      DeleteGroup(){
        
      },
      SecedeGroup(){
          api.delete(`groups/${this.groupInfo.id}`, {
              headers: {
                  token: store.getters.getToken
              }
          })
      }
  },
}
</script>

<style scoped>
.left{
    width:326px;
    height:767px;
    float: left;
}
.right{
    width:300px;
    height:767px;
    float: left;
    background-color: #FEF4E5;
    position: relative;
}
.right h1{
    float: right;
    color: orange;
    margin: 10px;
}
.right .BtnGroup{
    width: 140px;
    position: absolute;
    bottom: 5px;
    left: 5px;
}
</style>