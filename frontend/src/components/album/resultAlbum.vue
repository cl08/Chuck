/* 
1. axios
2. 사진 클릭했을 때 사진 상세페이지 보여주기
 */
<template>
  <div>
    <div class="selectPerson">
      <!-- 선택한 인원들을 상단에서 보여줌 -->
      <!-- <div v-for="(item, index) in items" :key="item.src" class="selectedImgDiv">
        <img
          :src="item.src"
          :class="{ clicked: checkArr[index], nonClicked: checkArr[index] }"
          @clicked="this.checkArr[index] = !this.checkArr[index]"
        />
      </div> -->

      <!-- 임시 데이터 -->
      <div class="selectedImgDiv">
        <img src="@/assets/person/iu.jpg" />
      </div>
    </div>
    <div class="List">
      <!-- 이미지는 서버에서 받아와서 for문으로 뿌려줌. -->
      <div v-for="(item,index) in items" :key="item.src" class="resultImgDiv">
        <!-- <img :src="item.src" /> -->
        <a @click="clickedImg(index)"
          ><img
            src="@/assets/person/iu.jpg"
        /></a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
        //   src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
           // src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
      ],
    };
  },
  mounted() {},
  computed: {},
  methods: {
    clickedImg(index) {
      //  해당 이미지가 있는 글로 이동!!
      console.log("해당 글로 이동!!!")
    },
  },
};
</script>

<style>
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
.selectPerson {
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