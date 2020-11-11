<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="dash">
            <font size=4>미리보기</font>
        </div>
        <div class="dash" style="height:620px;">
            <el-carousel indicator-position="none" arrow="always" height="580px">
                <div v-for="(data, i) in getFaceDataBook" :key="i">
                    <div v-if="getPersonArrayBook[i]">
                        <el-carousel-item v-for="(chuck, j) in data.content_list" :key="j">
                            <el-card shadow="none" style="border:0px;">
                                <img :src="chuck.image" style="object-fit:none; width:400px; height:300px; margin-top:60px;">
                                    <div style="padding:20px;">
                                        <font size=5>{{ chuck.title }}</font>
                                    </div>
                                    <div v-html="chuck.content"></div>
                            </el-card>
                        </el-carousel-item>
                    </div>
                </div>
            </el-carousel>
            {{ getFaceDataBook.length }}
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
            'getFaceDataBook',
            'getPersonArrayBook',
        ])
    }
}
</script>

<style scoped>

</style>