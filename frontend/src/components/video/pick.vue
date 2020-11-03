<template>
    <div style="margin:30px 0px 0px 30px;">
        <div style="margin-top:220px; cursor:pointer" @click="nextStep">
            <img src='../../assets/video_make.svg'><br>
            <img src='../../assets/title/chuckFilm_tabtitle.svg' class="tabtitle mt-10">
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