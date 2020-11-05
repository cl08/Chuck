<template>
    <div style="margin: 30px 30px 0px 0px">
        <div class="text-left ml-8">
            <img src="../../assets/title/gallery_tabtitle.svg" class="tabtitle">
        <div class="underline" style="margin-top: -5px;"></div>
        </div>
        <p>{{faces.length}}명의 인물이 검색되었습니다.</p>
        <div class="selectPerson">
            <div >
                <span class="face pointer" @click="selectAll">ALL</span>
                <span class="face pointer" v-for="(face, index) in faces" :key="index" @click="select(index)" :style="'background-image:url(' + face + ')'">
                    <img :id="'albumFace' + index" class="albumFaceNoneDisplay" src="@/assets/check_circle.svg">
                </span>
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
            faces: [],
            checkArr: [],
        };
    },
    mounted() {
        api.get('pictures/person_clustering?groupId='+this.getSelectedGroup.id)
        .then(({ data }) => {
            for(let i=0; i<data.cluster_list.length; i++) {
                this.faces.push(data.cluster_list[i].rep_image)
                this.checkArr[i] = false
            }
            this.$store.commit('setFaceData', data);
        })
    },
    computed: {
        ...mapGetters([
            'getSelectedGroup',
        ])
    },
    methods: {
        ...mapMutations([
            'setPersonArray',
        ]),
        selectAll() {
            for (let i = 0; i < this.faces.length; i++) {
                let el = document.getElementById("albumFace" + i);
                el.setAttribute("class", "");
                this.$set(this.checkArr, i, true);
                this.setPersonArray(this.checkArr);
            }
        },
        select(index) {
            let el = document.getElementById("albumFace" + index);
            el.classList.toggle("albumFaceNoneDisplay");
            if (this.checkArr[index]) {
                this.$set(this.checkArr, index, false);
            } else {
                this.$set(this.checkArr, index, true);
            }
            // store에 저장
            this.setPersonArray(this.checkArr);
        },
    },
};
</script>

<style scoped>
.imgDiv {
    width: 160px;
    display: inline-block;
}
p {
    margin: 30px;
    text-align: left;
}
.face {
    height: 90px;
    width: 90px;
    line-height: 90px;
    margin: 7px;
    border-radius: 50%;
    display: inline-block;
    box-sizing: border-box;
    text-align: center;
    color: #fff;
    background: #C0C4CC;
    background-size: cover;
    font-size: 26px;
    overflow: hidden;
}
.selectPerson {
    width: 400px;
}
.face img {
    height: 100%;
}
.albumFaceNoneDisplay {
    display: none;
}
</style>