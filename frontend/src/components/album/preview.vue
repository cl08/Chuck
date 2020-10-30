<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="dash">
            <font size=4>미리보기</font>
        </div>
        <div class="dash" style="height:620px;">
            <!-- <el-carousel indicator-position="none">
                <el-carousel-item v-for="(chuck, index) in getChuckList" :key="index">
                    <img :src="chuck.img[0]">
                </el-carousel-item>
            </el-carousel> -->
        </div>
        <div class="dash pointer">
            <font size=4>PDF로 내려받기</font>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    computed: {
        ...mapGetters([
            'getChuckList',
        ])
    },
}
</script>

<style>
</style>