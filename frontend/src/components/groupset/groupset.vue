<template>
    <div class="groupset">
        <div>
            <img src='../../assets/title/management_tabtitle.svg' class="tabtitle">
            <div class="underline"></div>
        </div>
        <h2>그룹명</h2>
        <div class="information">
            {{ name }}
        </div>
        <h2>그룹원 목록<v-btn @click="InviteGroup" color="orange" text>그룹원 초대하기</v-btn></h2>
        <div class="list">
            <div v-for="(member, index) in memberList" :key="index">
                {{member.name}}
                <v-btn @click="DeportGroup(member.id)" color="orange" text>추방</v-btn>
            </div>
        </div>
        <h2>그룹 생성일</h2>
        <div class="createDate">
            <p>{{this.GroupCreateDate}}</p>
        </div>
        <h2>그룹 로그</h2>
        <div class="log" style="margin-bottom:0px;">
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
            name: 'Team 쓰는척',
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
    margin: 30px 30px 0px 30px;
    text-align: left;
}
.groupset > div{
    margin-bottom: 24px;
}
.information button{
    left: 300px;
}
.list{
    height: 150px;
    overflow: scroll;
}
.log{
    height: 110px;
    overflow: scroll;
}
.createDate h2{
    margin-bottom: 10px;
}
</style>