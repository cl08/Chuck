<template>
    <div style="margin:30px 0px 0px 30px;">
        <div style="margin-top:150px; cursor:pointer" @click="nextStep">
            <h1 style="margin-bottom:100px">영상 제작</h1>
            <img src='../../assets/video_make.svg'>
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
        nextStep() {
            this.setVisibleChoice(false)
            this.setVisibleAlbum(false)
            this.setVisibleVideo(true)
            this.setVisiblePreview(false)
        }
    },
    computed: {
        ...mapGetters([
            'getChuckList',
            'getSelectedDiary',
            'getVisibleCalendar',
        ]),
    }
}
</script>

<style>

</style>