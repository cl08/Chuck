<template>
    <div class="result">
        <div class="selectPerson" v-if="data">
            <div v-for="(item, index) in data.cluster_list" :key="index" class="selectedImgDiv">
                <img :src="item.rep_image" :class="{ clicked: checkArr[index], nonClicked: checkArr[index] }" @clicked="this.checkArr[index] = !this.checkArr[index]">
            </div>
        </div>
        <div class="List" v-if="data">
            <div v-for="(item, index) in data.cluster_list[0].path_list" :key="index" class="resultImgDiv">
                <img class="pointer" :src="item" @click="clickedImg(index)">
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
            data: '',
            checkArr: '',
        }
    },
    mounted() {
        api.get('pictures/person_clustering?groupId='+this.getSelectedGroup.id)
        .then(({ data }) => {
            this.data = data
        })
    },
    computed: {
        ...mapGetters([
            'getSelectedGroup',
            'getPersonArray',
        ])
    },
    methods: {
        clickedImg(index) {
        //  해당 이미지가 있는 글로 이동!! 어떻게??
        console.log("해당 글로 이동!!!")
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