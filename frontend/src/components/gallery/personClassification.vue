<template>
  <div style="margin: 30px 30px 0px 0px">
    <div class="text-left ml-8">
      <img src="../../assets/title/gallery_tabtitle.svg" class="tabtitle">
    </div>
    <div class="selectPerson">
      <!-- 이미지는 서버에서 몇명인지 받아와서 for문으로 뿌려줌. -->
      <div v-for="(item, index) in items" :key="item.src" class="imgDiv" >
        <!-- <img :src="item.src" :class="{ clicked: checkArr[index], nonClicked: checkArr[index] }"  -->
        <a @click="clickedImg(index)"
          ><img
            src="@/assets/person/iu.jpg"
            :class="{ clicked: checkArr[index] }"
        /></a>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { mapMutations } from 'vuex'
export default {
  data() {
    return {
      items: [
        {
          // src: "../../../assets/person/iu.jpg",
        },
        {
          // src: "../../assets/person/suzy.jpg",
        },
        {
          // src: "../../assets/person/iu.jpg",
        },
        {
          // src: "../../assets/person/suzy.jpg",
        },
        {
          // src: "../../assets/person/iu.jpg",
        },
        {
          // src: "../../assets/person/suzy.jpg",
        },
      ],
      checkArr: [],
    };
  },
  mounted() {
    for (let index = 0; index < this.items.length; index++) {
      this.checkArr.push(false);
    }
    this.setPersonArray(this.checkArr);
  },
  computed: {},
  methods: {
    ...mapMutations([
      'setPersonArray',
    ]),
    clickedImg(imgNum) {
      if (this.checkArr[imgNum]) {
        this.$set(this.checkArr, imgNum, false);
      } else {
        this.$set(this.checkArr, imgNum, true);
      }
      // store에 저장
      this.setPersonArray(this.checkArr);
    },
  },
};
</script>

<style>
.imgDiv {
  width: 160px;
  display: inline-block;
}
.selectPerson img {
  width: 150px;
  height: 150px;
  border-radius: 50%;
}
.clicked {
  border: solid 0.2em;
  border-color: blueviolet;
  filter: opacity(0.5);
}
</style>