<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="dash">
            <font size=4>사진 고르기</font>
        </div>
        <div class="dash" style="height:620px;">
            <span class="photo pointer" @click="selectAll">ALL</span>
            <span v-for="(person, i) in getPersonArrayFilm" :key="i">
                <span v-show="person === true">
                    <span class="photo pointer" v-for="(photo, j) in getFaceData.cluster_list[i].path_list" :key="j" @click="select(j)" :style="'background-image:url('+photo+')'">
                        <img :id="'videoPhoto'+j" class="videoPhotoNoneDisplay" src="../../assets/check_square.svg">
                    </span>
                </span>
            </span>
        </div>
        <div class="dash pointer" @click="nextStep">
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
            
        }
    },
    computed: {
        ...mapGetters([
            'getChuckList',
            'getFaceData',
            'getPersonArrayFilm',
        ])
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
            console.log(this.getPersonArrayFilm)
            for(let i=0; i<this.photos.length; i++) {
                let el = document.getElementById('videoPhoto'+i)
                el.setAttribute('class', '')
            }
        },
        select(index) {
            let el = document.getElementById('videoPhoto'+index)
            el.classList.toggle("videoPhotoNoneDisplay")
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