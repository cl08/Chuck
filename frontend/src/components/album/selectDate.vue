<template>
    <div style="margin:30px 30px 0px 0px;">
        <div style="margin: 100px 0px 100px 0px;"><h1>추억 간직하기</h1></div>
            <div>
                <span style="margin:50px;">일 단위 선택</span>
                <el-date-picker
                v-model="value1"
                type="date"
                placeholder="Pick a day">
                </el-date-picker>
            </div>
            <div style="margin-top:50px;">
                <span style="margin:50px;" class="demonstration">시작/끝 선택</span>
                <el-date-picker
                v-model="value1"
                type="daterange"
                range-separator="To"
                start-placeholder="Start date"
                end-placeholder="End date"
                unlink-panels=true>
                </el-date-picker>
            </div>
    </div>
</template>

<script>
export default {
    data()  {
        return {
            value1: '',
        }
    }
}
</script>

<style>

</style>