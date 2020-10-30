<template>
    <div style="margin:30px 30px 0px 0px;">
        <font size=9>미리보기</font>
        <div class="dash">
            <font size=4>노래 선택하기</font>
        </div>
        <div class="dash" style="height:545px;">
            <span class="music pointer" :style="'background-image:url('+music.img+')'"
            v-for="(music, index) in musics" :key="index"
            @mouseover="mouseover(index)" @mouseout="mouseout(index)" @click="selectMusic(index)">
                <img :id="'videoMusic'+index" class="videoMusicNoneDisplay" src="../../assets/check_square.svg">
                <MusicBar :id="'musicbar'+index" style="display:none"></MusicBar>
            </span>
            <audio id="audio0" loop></audio>
            <audio id="audio1" src="../../assets/music/Beside Me - Patrick Patrikios.mp3" loop></audio>
            <audio id="audio2" src="../../assets/music/Nocturne - Asher Fulero.mp3" loop></audio>
        </div>
        <div class="dash pointer" @click="previousStep">
            <font size=4>이전 단계로</font>
        </div>
    </div>
</template>

<script>
import { mapMutations } from 'vuex'
import MusicBar from './musicbar.vue'
export default {
    components: {
        MusicBar,
    },
    data () {
        return {
            musics:
            [
                {
                    title: '음악 없음',
                    img: "https://previews.123rf.com/images/leventegyori/leventegyori1510/leventegyori151000012/47713326-%EA%B7%B8%EB%A0%A4%EC%A7%84-%EB%90%9C-x-%EB%A7%88%ED%81%AC-%ED%99%94%EC%9D%B4%ED%8A%B8-%EC%A0%88%EC%97%B0.jpg",
                    src: '',
                },
                {
                    title: 'Beside Me - Patrick Patrikios',
                    img: "https://cdnimg.melon.co.kr/cm2/album/images/104/44/179/10444179_20200612165324_500.jpg?c11ac4207c13df80c4b52223fe0bd3ae/melon/resize/282/quality/80/optimize",
                    src: '../../assets/music/Beside Me - Patrick Patrikios.mp3',
                },
                {
                    title: 'Nocturne - Asher Fulero',
                    img: 'https://cdnimg.melon.co.kr/cm2/album/images/104/86/979/10486979_20200907171548_500.jpg?35f390d020fc7c5e75d699329fcb166f/melon/resize/282/quality/80/optimize',
                    src: '../../assets/music/Nocturne - Asher Fulero.mp3',
                },
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
        mouseover(index) {
            let el = document.getElementById('videoMusic'+index)
            el.setAttribute('class', '')
        },
        mouseout(index) {
            let el = document.getElementById('videoMusic'+index)
            el.setAttribute('class', 'videoMusicNoneDisplay')
        },
        selectMusic(index) {
            let el
            for(let i=0; i<this.musics.length; i++) {
                el = document.getElementById('audio'+i)
                el.pause()
                el = document.getElementById('musicbar'+i)
                el.style.display = "none"
            }
            if(index > 0) {
                el = document.getElementById('musicbar'+index)
                el.style.display = "block"
            }
            el = document.getElementById('audio'+index)
            el.play()
        }
    }
}
</script>

<style>
.warp {
    height: 200px;
    width: 150px;
    line-height: 118px;
    margin: 7px;
    display: inline-block;
    box-sizing: border-box;
    text-align: center;
    background-size: cover;
    font-size: 26px;
    overflow: hidden;
}
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