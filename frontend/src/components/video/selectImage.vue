<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="dash">
            <font size=4>사진 고르기</font>
        </div>
        <div class="dash" style="height:620px; text-align:left">
            <span class="photo pointer" @click="selectAll">ALL</span>
            <span v-for="(person, i) in getPersonArrayFilm" :key="i">
                <span v-show="person === true">
                    <span class="photo pointer" v-for="(photo, j) in getFaceDataStudio.studio_list[i].content_list" :key="j" @click="select(i, j)" :style="'background-image:url('+photo+')'">
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
        }
    },
    mounted() {
        let num = this.getFaceDataStudio.studio_list.length
        for(let i=0; i<num; i++) {
            this.imageArray[i] = new Array()
        }
    },
    computed: {
        ...mapGetters([
            'getId',
            'getChuckList',
            'getFaceDataStudio',
            'getPersonArrayFilm',
        ]),
    },
    watch: {
        getPersonArrayFilm: function() {
            let count = 0
            for(let i=0; i<this.getPersonArrayFilm.length; i++){
                if(this.getPersonArrayFilm[i])
                    count = count + this.getFaceDataStudio.studio_list[i].content_list.length
            }
            this.maxCount = count
        }
    },
    methods: {
        ...mapMutations([
            'setVisibleChoice',
            'setVisibleAlbum',
            'setVisibleVideo',
            'setVisiblePreview',
        ]),
        nextStep() {
            let content_list = new Array()
            for(let i=0; i<this.getFaceDataStudio.studio_list.length; i++) {
                for(let j=0; j<this.getFaceDataStudio.studio_list[i].content_list.length; j++) {
                    if(this.imageArray[i][j]) {
                        content_list.push(this.getFaceDataStudio.studio_list[i].content_list[j])
                    }
                }
            }
            console.log(content_list)
            api.get('picktures/mkVideo', {
                'userId': this.getId,
                'content_list': content_list,
            })
            .then(({ data }) => {
                console.log(data)
            })
            this.setVisibleChoice(false)
            this.setVisibleAlbum(false)
            this.setVisibleVideo(false)
            this.setVisiblePreview(true)
        },
        selectAll() {
            if(this.selectCount == this.maxCount) {
                for(let i=0; i<this.getFaceDataStudio.studio_list.length; i++) {
                    if(this.getPersonArrayFilm[i]) {
                        for(let j=0; j<this.getFaceDataStudio.studio_list[i].content_list.length; j++) {
                            let el = document.getElementById('videoPhoto'+i+'arr'+j)
                            el.setAttribute('class', 'videoPhotoNoneDisplay')
                            this.imageArray[i][j] = false
                        }
                    }
                }
                this.selectCount = 0
            }
            else {
                for(let i=0; i<this.getFaceDataStudio.studio_list.length; i++) {
                    if(this.getPersonArrayFilm[i]) {
                        for(let j=0; j<this.getFaceDataStudio.studio_list[i].content_list.length; j++) {
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