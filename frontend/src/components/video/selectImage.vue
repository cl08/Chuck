<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="dash">
            <font size=4>사진 고르기</font>
        </div>
        <div class="dash" style="height:620px;">
            <el-avatar v-for="(image, index) in images" :key="index" shape="square" :size=90 :src="image" style="margin:7px"></el-avatar>
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
            images:
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
        }
    }
}
</script>

<style>
</style>