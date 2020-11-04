<template>
    <div style="padding:30px 0px 0px 30px;">
        <font size=6>2020.01.01</font><br>
            <el-input type="textarea" :rows="1" placeholder="오늘의 Chuck을 적어주세요." resize="none" v-model="title">
            </el-input>
            <el-input type="textarea" :rows="20" placeholder="Chuck에 대한 설명을 적어주세요."  resize="none" v-model="content">
            </el-input>
        <div class="dash pointer">
            <font size=4>작성 완료</font>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            title: '',
            content: '',
        }
    }
}
</script>