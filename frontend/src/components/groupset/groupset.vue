<template>
  <div class="groupset">
      <div class="information">
          <h1> 
              그룹정보
              <v-btn @click="InviteGroup" color="orange" text>그룹원 초대하기</v-btn>
          </h1>
      </div>
      <h1>그룹원 목록</h1>
      <div class="list">
          <div v-for="(member, index) in memberList" :key="index">
              {{member.name}}
              <v-btn @click="DeportGroup(member.id)" color="orange" text>추방</v-btn>
          </div>
      </div>
      <div class="createDate">
          <h1>그룹 생성일</h1>
          <p>{{this.GroupCreateDate}}</p>
      </div>
      <h1>그룹 로그</h1>
      <div class="log">
          <div v-for="(log, index) in logList" :key="index">
              {{log.content}}
          </div>
      </div>
  </div>
</template>

<script>
import api from '@/utils/api';
export default {
    data(){
        return{
            memberList:[
                // 임시데이터
                {
                    id : 1,
                    name : '김김김'
                },
                {
                    id : 2,
                    name : '이이이'
                },
                {
                    id : 3,
                    name : '박박박'
                },
                {
                    id : 4,
                    name : '나나나'
                },
                {
                    id : 5,
                    name : '최최최'
                },
                {
                    id : 6,
                    name : '금금금'
                },
                
            ],
            GroupCreateDate : "2020.10.15",
            logList:[
                // 임시데이터
                {
                    content:"아이린님이 그룹에 가입하셨습니다."
                },
                {
                    content:"오연서님이 새글을 작성하셨습니다."
                },
                {
                    content:"아이유님이 그룹에 가입하셨습니다."
                },
                {
                    content:"아이린님이 새글을 작성하셨습니다."
                },
                
            ]
        }
    },
    methods:{
        InviteGroup(){
            // axios 그룹 초대하기
        },
        DeportGroup(id){
            // axios 멤버 추방하기
        },
    },
    computed:{
        // axios memberlist, log, 그룹생성일 가져오기
    }
}
</script>

<style scope>
.groupset{
    margin: 20px;
    text-align: left;
}
.groupset > div{
    margin-bottom: 30px;
}
.information button{
    left: 300px;
}
.list{
    margin-top: 30px;
    height: 150px;
    overflow: scroll;
}
.log{
    margin-top: 30px;
    height: 150px;
    overflow: scroll;
}
.createDate h1{
    margin-bottom: 10px;
}
</style>