<!--
    1. 레이아웃 범위 지정 (ex:form 형식처럼)
    3. 날짜 선택
    4. 기간 내 총 게시물
-->
<template>
    <div style="margin:30px 30px 0px 0px;">
        <font size=9>사진첩 만들기</font>
        <div class="dash">
            <h2>기간 선택</h2>
        </div>
        <div class="dash" style="height:210px; text-align:left; overflow:auto;">
            <div style="margin:10px">
                일별 선택 : 
                <el-date-picker type="dates" v-model="dates" change="test" placeholder="날짜 선택" align="right">
                </el-date-picker>
            </div>
            <div style="margin:10px">
                범위 선택 : 
                <el-date-picker v-model="dates" type="daterange" change="test" start-placeholder="시작일" end-placeholder="종료일">
                </el-date-picker>
            </div>
            <div style="margin:10px">
                {{ dates }}
                <!-- <el-tag v-for="(tag, index) in tags" :key="index" closable @close="removeTag(tag)" type="info" style="margin:2px;">{{ tag }}</el-tag> -->
            </div>
            <div style="margin:10px">
                기간 내 총 게시물 : 52134개
            </div>
            <div style="margin:10px">
                
            </div>
        </div>
        <div class="dash">
            <h2>인물 선택</h2>
        </div>
        <div class="dash" style="height:210px">
            인물ㅋㅋ
        </div>
    </div>
</template>

<script>
export default {
    data () {
        return {
            dates: '',
            tags: ['2020.09.29', '2020.09.30', '2020.10.01', '2020.10.02']
        }
    },
    methods: {
        removeTag(tag) {
            this.tags.splice(this.tags.indexOf(tag), 1);
        }
    }
  }
</script>