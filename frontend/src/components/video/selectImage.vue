<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="dash">
            <font size=4>사진 고르기</font>
        </div>
        <div class="dash" style="height:620px;">
            <span class="Photo pointer" @click="selectAll">ALL</span>
            <span class="Photo pointer" v-for="(Photo, index) in Photos" :key="index" @click="select(index)" :style="'background-image:url('+Photo+')'">
                <img :id="'videoPhoto'+index" class="videoPhotoNoneDisplay" src="../../assets/check_square.svg">
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
            Photos:
            [
                'https://pbs.twimg.com/profile_images/1306539284212539392/aJrYjxho.jpg',
                'https://post-phinf.pstatic.net/MjAxOTA1MTZfMTEg/MDAxNTU3OTg3NzEyMDM4.m3__BqbSluWgyBBVca8kkg6COBQHGYtYQzwQR_hJ3RUg.3DeOn797qHrvboiIBMSLvBxY5W4vGB2OLx1XoYAENJAg.JPEG/17.jpg?type=w1200',
                'https://yt3.ggpht.com/a/AATXAJzAGhJRXaxZihohn-Ydp7s0jmLkLT28ZOGloycVXg=s900-c-k-c0x00ffffff-no-rj',
                'https://pbs.twimg.com/profile_images/1306539284212539392/aJrYjxho.jpg',
                'https://post-phinf.pstatic.net/MjAxOTA1MTZfMTEg/MDAxNTU3OTg3NzEyMDM4.m3__BqbSluWgyBBVca8kkg6COBQHGYtYQzwQR_hJ3RUg.3DeOn797qHrvboiIBMSLvBxY5W4vGB2OLx1XoYAENJAg.JPEG/17.jpg?type=w1200',
                'https://yt3.ggpht.com/a/AATXAJzAGhJRXaxZihohn-Ydp7s0jmLkLT28ZOGloycVXg=s900-c-k-c0x00ffffff-no-rj',
                'https://pbs.twimg.com/profile_images/1306539284212539392/aJrYjxho.jpg',
                'https://post-phinf.pstatic.net/MjAxOTA1MTZfMTEg/MDAxNTU3OTg3NzEyMDM4.m3__BqbSluWgyBBVca8kkg6COBQHGYtYQzwQR_hJ3RUg.3DeOn797qHrvboiIBMSLvBxY5W4vGB2OLx1XoYAENJAg.JPEG/17.jpg?type=w1200',
                'https://pbs.twimg.com/profile_images/1306539284212539392/aJrYjxho.jpg',
                'https://post-phinf.pstatic.net/MjAxOTA1MTZfMTEg/MDAxNTU3OTg3NzEyMDM4.m3__BqbSluWgyBBVca8kkg6COBQHGYtYQzwQR_hJ3RUg.3DeOn797qHrvboiIBMSLvBxY5W4vGB2OLx1XoYAENJAg.JPEG/17.jpg?type=w1200',
                'https://yt3.ggpht.com/a/AATXAJzAGhJRXaxZihohn-Ydp7s0jmLkLT28ZOGloycVXg=s900-c-k-c0x00ffffff-no-rj',
                'https://pbs.twimg.com/profile_images/1306539284212539392/aJrYjxho.jpg',
                'https://post-phinf.pstatic.net/MjAxOTA1MTZfMTEg/MDAxNTU3OTg3NzEyMDM4.m3__BqbSluWgyBBVca8kkg6COBQHGYtYQzwQR_hJ3RUg.3DeOn797qHrvboiIBMSLvBxY5W4vGB2OLx1XoYAENJAg.JPEG/17.jpg?type=w1200',
                'https://yt3.ggpht.com/a/AATXAJzAGhJRXaxZihohn-Ydp7s0jmLkLT28ZOGloycVXg=s900-c-k-c0x00ffffff-no-rj',
                'https://pbs.twimg.com/profile_images/1306539284212539392/aJrYjxho.jpg',
                'https://post-phinf.pstatic.net/MjAxOTA1MTZfMTEg/MDAxNTU3OTg3NzEyMDM4.m3__BqbSluWgyBBVca8kkg6COBQHGYtYQzwQR_hJ3RUg.3DeOn797qHrvboiIBMSLvBxY5W4vGB2OLx1XoYAENJAg.JPEG/17.jpg?type=w1200',
            ]
        }
    },
    computed: {
        ...mapGetters([
            'getChuckList',
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
            for(let i=0; i<this.Photos.length; i++){
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
.Photo {
    height: 118px;
    width: 118px;
    line-height: 118px;
    margin: 7px;
    /* border-radius: 50%; */
    display: inline-block;
    box-sizing: border-box;
    text-align: center;
    color: #fff;
    background: #C0C4CC;
    background-size: cover;
    font-size: 26px;
    overflow: hidden;
}
.Photo img {
    height: 100%;
}
.videoPhotoNoneDisplay {
    display: none;
}
</style>