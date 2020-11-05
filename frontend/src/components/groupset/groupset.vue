<template>
    <div class="groupset">
        <div>
            <img src='../../assets/title/management_tabtitle.svg' class="tabtitle">
            <div class="underline"></div>
        </div>
        <font size=5>그룹명</font>
        <div class="information">
            {{ groupInfo.name }}
        </div>
        <font size=5>그룹원 목록
            <v-btn @click="InviteGroup" color="#3D91FF" text>그룹원 초대하기</v-btn>
        </font>
        <div class="list">
            <div v-for="(member, index) in memberList" :key="index">
                {{member.name}}
                <v-btn @click="DeportGroup(member.id)" color="#FFB6B6" text>추방</v-btn>
            </div>
        </div>
        <font size=5>그룹 생성일</font>
        <div class="createDate">
            <p>{{this.groupInfo.publishedDate | moment('YYYY.MM.DD')}}</p>
        </div>
        <font size=5>그룹 로그</font>
        <div class="log" style="margin-bottom:0px;">
            <div v-for="(log, index) in logList" :key="index">
                {{log.content}}
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import api from '@/utils/api';
import store from '@/store';

export default {
    data(){
        return{
            memberList:[],
            logList:[]
        }
    },
    created() {
        api.get(`groups/${this.groupInfo.id}/members`, {
            headers: {
                token: sessionStorage.getItem('token'),
            }
        }).then((res) => {
            this.memberList = res.data;
        });
        
        api.get(`logs/${this.groupInfo.id}`).then((res) => {
            this.logList = res.data;
        })
    },
    mounted() {
         if (!window.Kakao && sessionStorage.getItem('ID') !== '1') {
            const script = document.createElement('script');
              script.onload = () => window.Kakao.init('d1baf2cad3354a9138989baea6e65995');
            script.src = '/js/kakao.min.js';
            document.head.appendChild(script);
        }
    },
    methods:{
        InviteGroup() {
            if(sessionStorage.getItem('ID') === '1') {
                alert('테스트 아이디는 카카오 초대가 불가능합니다.')
            } else {
                window.Kakao.Link.sendCustom({
                    templateId: 39047,
                    templateArgs: {
                        key: this.groupInfo.token,
                        group: this.groupInfo.name, // this.groupName,
                        user: store.getters.getName,
                    },
                });
            }
        },
        DeportGroup(id){
            // axios 멤버 추방하기
        },
    },
    computed:{
        groupInfo: () => store.getters.getSelectedGroup,
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
    color: #2d2d2d;
}
.information button{
    left: 300px;
}
.list{
    height: 160px;
    overflow: scroll;
}
.log{
    height: 200px;
    overflow: scroll;
}
.createDate font{
    margin-bottom: 10px;
}
</style>