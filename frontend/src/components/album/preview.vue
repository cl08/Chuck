<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="dash">
            <font size=4>미리보기</font>
        </div>
        <div class="dash" style="height:620px;">
            <el-carousel indicator-position="none" arrow="always" height="605px">
                <el-carousel-item v-for="(chuck, index) in getChuckList" :key="index">
                     <el-card shadow="none" style="border:0px;">
                        <img :src="chuck.img[0]" style="object-fit:none; width:400px; height:300px; margin-top:60px;">
                            <div style="padding:20px;">
                                <font size=5>{{ chuck.title }}</font>
                            </div>
                            <div v-html="chuck.content"></div>
                    </el-card>
                </el-carousel-item>
            </el-carousel>
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

<style scoped>

</style>