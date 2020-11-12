<template>
    <div style="padding:30px 0px 0px 30px;">
        <div class="bg" style="text-align:center">
            <img v-if="currentImage" class="currentImg" :src="currentImage">
        </div>

        <div style="text-align:left; padding:20px;">
            <span v-for="(item, index) in temp" :key="index" class="picture">
                <img class="pointer picture" :src="item" @click="clickedImg(index)" style="object-fit:cover">
            </span>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex"
import { mapMutations } from "vuex"
import api from '@/utils/api.js'
import eventBus from '@/utils/EventBus'

export default {
    data() {
        return {
            checkArr: [],
            imageList: new Map(),
            temp: [],
            currentImage: '',
        }
    },
    computed: {
        ...mapGetters([
            'getSelectedGroup',
            'getPersonArrayGallery',
            'getFaceDataGallery',
            'getChuckMap',
        ]),
    },
    watch: {
        getPersonArrayGallery: function(data) {
            let change = []
            if(this.checkArr.length == 0) {
                this.checkArr = data.slice()
                for(let i = 0; i<data.length; i++) change.push(i)
            } else {
                for(let i = 0; i<data.length; i++) {
                    if(this.checkArr[i] != data[i]) change.push(i)
                }
                this.checkArr = data.slice()
            }
            if(change.length != 0) {
                change.forEach(index => {
                    const flag = this.checkArr[index]
                    const num = this.getFaceDataGallery.gallery_list[index].content_list.length
                    for(let i = 0; i<num; i++) {
                        const diary_id = this.getFaceDataGallery.gallery_list[index].content_list[i].diary_id
                        const path = this.getFaceDataGallery.gallery_list[index].content_list[i].path
                        if(this.imageList.has(path)) {
                            const cnt = this.imageList.get(path).cnt
                            if(flag) this.imageList.set(path, {cnt: cnt + 1, diaryId: diary_id})
                            else {
                                if(cnt == 1) this.imageList.delete(path)
                                else this.imageList.set(path, {cnt: cnt - 1, diaryId: diary_id})
                            }
                        } else {
                            if(flag) this.imageList.set(path, {cnt: 1, diaryId: diary_id})
                        }
                    } 
                });
            }
            this.temp = Array.from(this.imageList.keys())
        }
    },
    methods: {
        clickedImg(index) {
            // const id = this.imageList.get(this.temp[index]).diaryId
            // eventBus.$emit('movePage', {index: 1, item: this.getChuckMap.get(id), state: 3})
            this.currentImage = this.temp[index]
        },
    },
};
</script>

<style scoped>
.picture {
    width: 120px;
    height: 120px;
    margin: 4px;
}
.bg {
    background: url('../../assets/gallery/background.jpg');
    background-size: cover;
    width: 550px;
    height: 350px;
    margin: 20px 20px 0px 20px;
}
.currentImg {
    width: 420px;
    height: 250px;
    margin-top: 50px;
    object-fit: cover;
}
</style>