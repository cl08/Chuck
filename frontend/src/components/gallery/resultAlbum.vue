<template>
    <div class="result">
        <div class="List">
            <span v-for="(item, index) in temp" :key="index" class="resultImgDiv">
                <img class="pointer" :src="item" @click="clickedImg(index)">
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
        //  해당 이미지가 있는 글로 이동!! 어떻게??
            const id = this.imageList.get(this.temp[index]).diaryId
            eventBus.$emit('movePage', {index: 1, item: this.getChuckMap.get(id), state: 3})
        },
    },
};
</script>

<style scoped>
.List {
    margin-top: 50px;
    margin-left: 50px;
}
.resultImgDiv {
    width: 130px;
    display: inline-block;
    overflow: hidden;
}
.List img {
    width: 110px;
    height: 110px;
}
.result .selectPerson {
    margin-top: 50px;
    margin-left: 30px;
}
.selectedImgDiv {
    margin-left: 20px;
    width: 100px;
}
.selectedImgDiv img {
    border: solid 0.1em;
    width: 30px;
    height: 30px;
    filter: opacity(0.5);
}
</style>