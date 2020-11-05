<template>
<div>
    <div class="left">
        <div class="text-left" style="padding:30px 0px 0px 30px;">
            <img src="../../assets/logo.svg" class="subtitle">
            <div class="groupInfo">
                <h2>그룹명</h2>
                <div class="information">
                    {{this.groupInfo.name}}
                </div>
                <h2>그룹 생성일</h2>
                <div class="createDate">
                    {{this.groupInfo.publishedDate | moment('YYYY.MM.DD')}}
                </div>
                <h2>그룹장</h2>
                <div>
                    {{this.groupInfo.ownerName}}
                </div>
                <h2>그룹소개</h2>
                    <div id="group_intro">
                    </div>
            </div>
        </div>
        <div class="right">
        </div>
    </div>
</div>
</template>

<script>
import store from '@/store';

export default {
    data() {
        return {
            intro: '',
        };
    },
    computed: {
        groupInfo: () => store.getters.getSelectedGroup,
    },
    mounted() {
        // 아직 그룹 입력시 br 태그를 못 받아서 임시로 함
        const len = this.groupInfo.intro.length / 20;
        for(var i=0; i<len; i++) {
            this.intro += this.groupInfo.intro.substring(20*i, 20*(i+1));
            this.intro += '<br/>';
        }
        $('#group_intro').html(this.intro);
    }

}
</script>

<style scoped>
.left {
    text-align: left;
    width: 300px;
    height: 767px;
    background-color: #FFF8F8;
    box-shadow: 2px 0px 10px 1px #E2E2E2;
}
.groupInfo {
    position: absolute;
    bottom: 0px;
}
.groupInfo > div{
    margin-bottom: 24px;
}
</style>