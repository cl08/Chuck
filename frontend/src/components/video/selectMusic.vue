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
            <span class="music pointer" @click="selectAll">X</span>
            음악 선택 안함
            <span class="music pointer" v-for="(music, index) in musics" :key="index" @click="select(index)" :style="'background-image:url('+music+')'">
            <img :id="'videoMusic'+index" class="videoMusicNoneDisplay" src="../../assets/check_square.svg">
            </span>
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
    data () {
        return {
            musics:
            [
                'https://cdnimg.melon.co.kr/cm2/album/images/104/79/150/10479150_20200918102847_500.jpg?45d847d7dc536eecebd648786c2ea37c/melon/quality/80/optimize',
                'https://cdnimg.melon.co.kr/cm2/album/images/105/11/156/10511156_20201029161043_500.jpg?c6cdf6d317efcb4d53d07c2dc7eeb2ee/melon/quality/80/optimize',
                'https://cdnimg.melon.co.kr/cm2/album/images/105/11/269/10511269_20201029170936_500.jpg?c5bc5ccd9c5d21cdf0a43e953bd4569c/melon/quality/80/optimize',
                'https://cdnimg.melon.co.kr/cm2/album/images/105/10/895/10510895_20201029142102_500.jpg?75a1b88cc2033e4b57536a13293da6b7/melon/quality/80/optimize',
            ]
        }
    },
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
        },
        select(index) {
            let el = document.getElementById('videoMusic'+index)
            el.classList.toggle("videoMusicNoneDisplay")
        }
    }
}
</script>

<style>
.music {
    height: 150px;
    width: 150px;
    line-height: 118px;
    margin: 7px;
    display: inline-block;
    box-sizing: border-box;
    text-align: center;
    color: #fff;
    background: #C0C4CC;
    background-size: cover;
    font-size: 26px;
    overflow: hidden;
}
.music img {
    height: 100%;
}
.videoMusicNoneDisplay {
    display: none;
}
</style>