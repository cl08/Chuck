<!--
    마우스 오버시 재생버튼
    클릭시 뮤직바 + 테두라
-->
<template>
    <div style="margin:30px 30px 0px 0px;">
        <font size=9>미리보기</font>
        <div class="dash">
            <font size=4>노래 선택하기</font>
        </div>
        <div class="dash" style="height:542px;">
            <!-- <el-avatar v-for="(image, index) in images" :key="index" shape="square" :size=90 :src="image" style="margin:7px"></el-avatar> -->
        </div>
        <div class="dash pointer" @click="previousStep">
            <font size=4>이전 단계로</font>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { mapMutations } from 'vuex'
export default {
    methods: {
        ...mapMutations([
            'setVisibleChoice',
            'setVisibleAlbum',
            'setVisibleVideo',
            'setVisiblePreview',
        ]),
        previousStep() {
            this.setVisibleChoice(false)
            this.setVisibleAlbum(false)
            this.setVisibleVideo(true)
            this.setVisiblePreview(false)
        }
    }
}
</script>

<style>

</style>