<template>
    <div class="result">
        <div class="List" v-if="getFaceDataGallery">
            <div v-for="(person, i) in getPersonArrayGallery" :key="i">
                <div v-if="person === true">
                    <div v-for="(item, index) in getFaceDataGallery.gallery_list[i].content_list" :key="index" class="resultImgDiv">
                        <img class="pointer" :src="item.path" @click="clickedImg(index)">
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex"
import { mapMutations } from "vuex"
import api from '@/utils/api.js'

export default {
    data() {
        return {
            checkArr: '',
        }
    },
    computed: {
        ...mapGetters([
            'getSelectedGroup',
            'getPersonArrayGallery',
            'getFaceDataGallery'
        ]),
    },
    methods: {
        clickedImg(index) {
        //  해당 이미지가 있는 글로 이동!! 어떻게??
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