<template>
    <div style="margin:30px 30px 0px 0px;">
        <div class="text-left ml-8">
            <img src="../../assets/title/studio_tabtitle.svg" class="tabtitle">
        </div>
        <div style="margin-top:120px; cursor:pointer" @click="nextStep">
            <img src='../../assets/photo_album.svg'><br>
            <img src='../../assets/title/chuckBook_tabtitle.svg' class="tabtitle mt-10">
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
            this.setVisibleAlbum(true)
            this.setVisibleVideo(false)
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