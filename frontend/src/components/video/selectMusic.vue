<template>
    <div style="margin:30px 30px 0px 0px;">
        <img src='../../assets/title/chuckFilm_tabtitle.svg' class="subtitle">
        <div class="dash">
            <font size=4>노래 선택하기</font>
        </div>
        <div class="dash" style="height:540px;">
            <span class="wrap pointer" v-for="(music, index) in musics" :key="index" @mouseover="mouseover(index)" @mouseout="mouseout(index)" @click="selectMusic(index)">
                <div v-if="index > 0">
                    <img :id="'videoMusic'+index" class="videoMusicNoneDisplay" src="../../assets/music_play.svg" style="position: absolute; width: 150px; transform: translateX(-75px); z-index:10">
                    <div class="music" :style="'background-image:url('+music.img+')'" style="position:absolute">
                        <MusicBar :id="'musicbar'+index" style="display:none;"></MusicBar>
                    </div>
                    <div style="padding:5px; position: relative; padding: 5px; top: 150px;">{{ music.title }}</div>
                </div>
                <div v-else>
                    <img :id="'videoMusic'+index" class="videoMusicNoneDisplay" src="../../assets/music_play.svg" style="position: absolute; width: 150px; transform: translateX(-75px); z-index:10">
                    <div class="music noMusic">
                        <MusicBar :id="'musicbar'+index" style="display:none;"></MusicBar>
                    </div>
                    <div style="padding:5px; position: relative; padding: 5px; top: 150px;">{{ music.title }}</div>
                </div>
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
                    img: '',
                    src: '',
                    value: 'middle'
                },
                {
                    title: 'Beside Me - Patrick Patrikios',
                    img: "https://cdnimg.melon.co.kr/cm2/album/images/104/44/179/10444179_20200612165324_500.jpg?c11ac4207c13df80c4b52223fe0bd3ae/melon/resize/282/quality/80/optimize",
                    src: '../../assets/music/Beside Me - Patrick Patrikios.mp3',
                    value: 'Fingertips.mp3'
                },
                {
                    title: 'Nocturne - Asher Fulero',
                    img: 'https://cdnimg.melon.co.kr/cm2/album/images/104/86/979/10486979_20200907171548_500.jpg?35f390d020fc7c5e75d699329fcb166f/melon/resize/282/quality/80/optimize',
                    src: '../../assets/music/Nocturne - Asher Fulero.mp3',
                    value: 'Fingertips.mp3'
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
            'setVideoMusic',
        ]),
        previousStep() {
            let el
            for(let i=0; i<this.musics.length; i++) {
                el = document.getElementById('audio'+i)
                el.load()
                el = document.getElementById('musicbar'+i)
                el.style.display = "none"
            }
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
                el.load()
                el = document.getElementById('musicbar'+i)
                el.style.display = "none"
            }
            if(index > 0) {
                el = document.getElementById('musicbar'+index)
                el.style.display = "block"
            }
            el = document.getElementById('audio'+index)
            el.play()
            
            this.setVideoMusic(this.musics[index].value)

			let mediaPlayer = document.getElementById('media-video')
			mediaPlayer.load()
			mediaPlayer.play()
        }
    }
}
</script>

<style>
.wrap {
    height: 180px;
    width: 150px;
    margin: 7px;
    display: inline-block;
    box-sizing: border-box;
    overflow: hidden;
}
.music {
    height: 150px;
    width: 150px;
    background-size: cover;
}
.music img {
    height: 100%;
}
.videoMusicNoneDisplay {
    display: none;
}
.noMusic {
    background-image:url('../../assets/no_music.svg');
    position: absolute;
}
</style>