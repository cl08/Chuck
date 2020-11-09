<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="dash">
            <font size=4>사진 고르기</font>
        </div>
        <div class="dash" style="height:620px; text-align:left">
            <span class="photo pointer" @click="selectAll">ALL</span>
            <span v-for="(person, i) in getPersonArrayFilm" :key="i">
                <span v-show="person === true">
                    <span class="photo pointer" v-for="(photo, j) in getFaceData.cluster_list[i].path_list" :key="j" @click="select(i, j)" :style="'background-image:url('+photo+')'">
                        <img :id="'videoPhoto'+i+'arr'+j" class="videoPhotoNoneDisplay" src="../../assets/check_square.svg">
                    </span>
                </span>
            </span>
        </div>
        <div class="dash pointer" @click="nextStep">
            {{ selectCount }} / {{ maxCount }}
            <font size=4>다음으로</font>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { mapMutations } from 'vuex'
export default {
    data () {
        return {
            selectCount: 0,
            maxCount : '',
            imageArray: [],
        }
    },
    computed: {
        ...mapGetters([
            'getChuckList',
            'getFaceData',
            'getPersonArrayFilm',
        ]),
    },
    watch: {
        getPersonArrayFilm: function() {
            let count = 0
            for(let i=0; i<this.getPersonArrayFilm.length; i++){
                if(this.getPersonArrayFilm[i])
                    count = count + this.getFaceData.cluster_list[i].path_list.length
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
            this.setVisibleChoice(false)
            this.setVisibleAlbum(false)
            this.setVisibleVideo(false)
            this.setVisiblePreview(true)
        },
        selectAll() {
            let count = 0
            if(this.selectCount == this.maxCount) {
                for(let i=0; i<this.getFaceData.cluster_list.length; i++) {
                    if(this.getPersonArrayFilm[i]) {
                        for(let j=0; j<this.getFaceData.cluster_list[i].path_list.length; j++) {
                            let el = document.getElementById('videoPhoto'+i+'arr'+j)
                            el.setAttribute('class', 'videoPhotoNoneDisplay')
                            this.imageArray[i][j] = false
                        }
                    }
                }
                this.selectCount = 0
            }
            else {
                for(let i=0; i<this.getFaceData.cluster_list.length; i++) {
                    if(this.getPersonArrayFilm[i]) {
                        for(let j=0; j<this.getFaceData.cluster_list[i].path_list.length; j++) {
                            let el = document.getElementById('videoPhoto'+i+'arr'+j)
                            el.setAttribute('class', '')
                            this.imageArray[i][j] = true
                        }
                    }
                }
            }
        },
        select(i, j) {
            console.log(i+", "+j)
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