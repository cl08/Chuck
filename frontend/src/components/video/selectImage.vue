<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="dash">
            <font size=4>사진 고르기</font>
        </div>
        <div class="dash" style="height:620px; text-align:left">
            <span class="photo pointer" @click="selectAll">ALL</span>
            <span v-for="(data, i) in getFaceDataFilm" :key="i">
                <span v-show="getPersonArrayFilm[i]">
                    <span class="photo pointer" v-for="(chuck, j) in data.content_list" :key="j" @click="select(i, j)" :style="'background-image:url('+chuck.image+')'">
                        <img :id="'videoPhoto'+i+'arr'+j" class="videoPhotoNoneDisplay" src="../../assets/check_square.svg">
                    </span>
                </span>
            </span>
        </div>
        <div v-if="selectCount < 5" class="dash pointer">
            <font size=4 color="red">다섯장 이상의 사진을 선택해야 합니다. {{selectCount}} / {{maxCount}}</font>
        </div>
        <div v-else class="dash pointer" @click="nextStep">
            <font size=4>다음으로</font>
        </div>
        <v-dialog v-model="loading" hide-overlay persistent width="300">
            <v-card color="#8D6262" dark>
                <v-card-text>
                    Making Chuck Film...
                    <v-progress-linear indeterminate color="white" class="mb-0"></v-progress-linear>
                </v-card-text>
            </v-card>
        </v-dialog>
    </div>
    
</template>

<script>
import { mapGetters } from 'vuex'
import { mapMutations } from 'vuex'
import api from '@/utils/api'
export default {
    data () {
        return {
            selectCount: 0,
            maxCount : 0,
            imageArray: [],
            loading: false,
        }
    },
    computed: {
        ...mapGetters([
            'getId',
            'getChuckList',
            'getFaceDataFilm',
            'getPersonArrayFilm',
            'getVideoUrl'
        ]),
    },
    watch: {
        getPersonArrayFilm: function() {
            let count = 0
            for(let i=0; i<this.getPersonArrayFilm.length; i++) {
                if(this.getPersonArrayFilm[i])
                    count = count + this.getFaceDataFilm[i].content_list.length
            }
            this.maxCount = count
        },
        getFaceDataFilm: function() {
            for(let i=0; i<this.getFaceDataFilm.length; i++) {
                this.imageArray[i] = new Array()
            }
        },
    },
    methods: {
        ...mapMutations([
            'setVisibleChoice',
            'setVisibleAlbum',
            'setVisibleVideo',
            'setVisiblePreview',
            'setVideoSrc',
            'setVideoUrl',
        ]),
        nextStep() {
            this.loading = true
            let src = new Array()
            for(let i=0; i<this.getFaceDataFilm.length; i++) {
                for(let j=0; j<this.getFaceDataFilm[i].content_list.length; j++) {
                    if(this.imageArray[i][j]) {
                        src.push(this.getFaceDataFilm[i].content_list[j].image)
                    }
                }
            }
            api.post('pictures/mkVideo', {
                'music': "Fingertips.mp3",
                'userId': this.getId,
                'path_list': src,
            })
            .then(({ data }) => {
                data = data.replace('final', 'middle')
                this.setVideoUrl(data)
                this.setVideoSrc(src)
            
                // 동영상 생성
                let p = document.getElementById("media-video")
                while (p.hasChildNodes()) {
                    p.removeChild( p.firstChild )
                }
                
                let source = document.createElement("source")
                let url = this.getVideoUrl.replace('final', 'middle')
                source.setAttribute('src', url)
                p.appendChild(source)
            
                this.setVisibleChoice(false)
                this.setVisibleAlbum(false)
                this.setVisibleVideo(false)
                this.setVisiblePreview(true)
                this.loading = false
            })
        },
        selectAll() {
            if(this.selectCount == this.maxCount) {
                for(let i=0; i<this.getFaceDataFilm.length; i++) {
                    if(this.getPersonArrayFilm[i]) {
                        for(let j=0; j<this.getFaceDataFilm[i].content_list.length; j++) {
                            let el = document.getElementById('videoPhoto'+i+'arr'+j)
                            el.setAttribute('class', 'videoPhotoNoneDisplay')
                            this.imageArray[i][j] = false
                        }
                    }
                }
                this.selectCount = 0
            }
            else {
                for(let i=0; i<this.getFaceDataFilm.length; i++) {
                    if(this.getPersonArrayFilm[i]) {
                        for(let j=0; j<this.getFaceDataFilm[i].content_list.length; j++) {
                            let el = document.getElementById('videoPhoto'+i+'arr'+j)
                            el.setAttribute('class', '')
                            this.imageArray[i][j] = true
                        }
                    }
                }
                this.selectCount = this.maxCount
            }
        },
        select(i, j) {
            let el = document.getElementById('videoPhoto'+i+'arr'+j)
            el.classList.toggle("videoPhotoNoneDisplay")
            if(this.imageArray[i][j]) {
                this.imageArray[i][j] = false
                this.selectCount--
            }
            else {
                this.imageArray[i][j] = true
                this.selectCount++
            }
        }
    }
}
</script>

<style scoped>
.photo {
    height: 118px;
    width: 118px;
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
.photo img {
    height: 100%;
}
.videoPhotoNoneDisplay {
    display: none;
}
</style>