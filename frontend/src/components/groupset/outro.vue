<template>
<div class="outro">
    <div class="left">
        
    </div>
    <div class="right">
        <img src="../../assets/logo_200150.svg">
        <!-- 오른쪽 버튼 그룹 -->
        <div class="BtnGroup">
            <v-btn @click="DeleteGroup" color="orange" text x-large><h2>그룹삭제</h2></v-btn>
            <v-btn @click="SecedeGroup" color="orange" text x-large><h2>그룹탈퇴</h2></v-btn>
        </div>
    </div>
</div>
</template>

<script>
export default {
    data() {
    return {
      
    };
  },
  computed: {
    
  },
  methods: {
    DeleteGroup(){
        console.log("그룹삭제")
        // axios or 물어보는 페이지
    },
    SecedeGroup(){
        console.log("그룹탈퇴")
        // axios or 물어보는 페이지
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
.right img{
    float: right;
    width: 300px;
    height: 150px;
}
.right .BtnGroup{
    width: 140px;
    position: absolute;
    bottom: 5px;
    left: 5px;
}
</style>