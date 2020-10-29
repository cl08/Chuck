<!--
    1. 레이아웃 범위 지정 (ex:form)
    2. 범위 선택 ?? 
    3. 기간 내 총 게시물
-->
<template>
    <div style="margin:30px 30px 0px 0px;">
        <font size=9>사진첩 만들기</font>
        <div class="dash">
            <h2>기간 선택</h2>
        </div>
        <div class="dash" style="height:210px; text-align:left; overflow:auto;">
            <div style="margin:10px">
                <!-- 일별 선택 :  -->
                날짜 선택 : 
                <el-date-picker type="dates" v-model="dates" change="test" placeholder="날짜 선택" align="right" value-format="yyyy-MM-dd">
                </el-date-picker>
            </div>
            <!-- <div style="margin:10px">
                범위 선택 : 
                <el-date-picker v-model="dates" type="daterange" change="test" start-placeholder="시작일" end-placeholder="종료일" value-format="yyyy-MM-dd">
                </el-date-picker>
            </div> -->
            <div style="margin:10px">
                <el-tag v-for="(tag, index) in dates" :key="index" closable @close="removeTag(tag)" type="info" style="margin:2px;">{{ tag }}</el-tag>
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
            dates: [],
        }
    },
    methods: {
        removeTag(tag) {
            this.dates.splice(this.dates.indexOf(tag), 1);
        }
    }
  }
</script>