<template>
  <div>
    <div class="bookmark">
      <button
        v-on:click="movePage(0)"
        :style="{ left: this.buttonArray[0] + 'px' }"
        id="album"
      ></button>
      <button
        v-on:click="movePage(1)"
        :style="{ left: this.buttonArray[1] + 'px' }"
        id="timeline"
      ></button>
      <button
        v-on:click="movePage(2)"
        :style="{ left: this.buttonArray[2] + 'px' }"
        id="chuck"
      ></button>
      <button
        v-on:click="movePage(3)"
        :style="{ left: this.buttonArray[3] + 'px' }"
        id="calender"
      ></button>
    </div>
    <div class="cover">
      <fw-turn class="magazine" :options="options">
        <!-- blank page -->
        <div class="blank">blank</div>
        <!-- main -->
        <div class="left">메인페이지</div>
        <div class="right">메인페이지 본문</div>
        <!-- timeline -->
        <div class="left">타임라인</div>
        <div class="right">타임라인 본문</div>
        <!-- calender -->
        <div class="left">
          <Calendar v-show="this.getVisibleCalendar" />
				  <Detail v-show="this.getVisibleDetail" />
				  <Write1 v-show="this.getVisibleWrite" />
        </div>
        <div class="right">
          <List v-if="this.getVisibleCalendar" />
          <Comment v-show="this.getVisibleDetail" />
          <Write2 v-show="this.getVisibleWrite" />
        </div>
        <!-- album -->
        <div class="left">앨범</div>
        <div class="right">앨범 본문</div>
        <!-- chuck -->
        <div class="left">추억</div>
        <div class="right">추억 본문</div>
        <!-- blank page -->
        <div class="blank">blank</div>
      </fw-turn>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Calendar from "@/components/calendar.vue";
import List from "@/components/list.vue";
import Detail from "@/components/detail.vue";
import Comment from "@/components/comment.vue";
import Write1 from "@/components/write1.vue";
import Write2 from "@/components/write2.vue";
import $ from "jquery";
export default {
  components: {
    Calendar,
    List,
    Detail,
    Comment,
    Write1,
    Write2,
  },
  computed: {
    ...mapGetters([
      "getChuckList",
      "getSelectedDiary",
      "getVisibleCalendar",
      "getVisibleDetail",
      "getVisibleWrite",
    ]),
  },
  mounted() {},
  data() {
    return {
      options: {
        display: "double",
        acceleration: false,
        page: 2,
        elevation: 50,
        width: 1170,
        height: 717,
      },
      buttonArray: [1373, 1373, 1373, 1373],
    };
  },
  methods: {
    movePage(num) {
      $(".magazine").turn("page", 4 + 2 * num);
      for (let i = 0; i < 4; i++) {
        // 150px <-> 1373px
        if (i <= num) {
          this.$set(this.buttonArray, i, 150);
        } else {
          this.$set(this.buttonArray, i, 1373);
        }
      }
      console.log(this.buttonArray);
    },
  },
};
</script>

<style scoped>
.body {
  -ms-overflow-style: none;
  overflow: auto;
}
::-webkit-scrollbar {
  display: none;
}
::selection {
  background: #222;
  color: white;
}
::-moz-selection {
  background: #222;
  color: white;
}
/* book css */
.cover {
  height: 800px;
  width: 1420px;
  background: url("../assets/layout1.png");
  background-size: cover;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  perspective: 2500px;
}
.magazine {
  position: relative;
  top: 49px;
  left: 143px;
  pointer-events: none;
}
.magazine img {
  width: 100%;
  height: 100%;
}
.magazine .left {
  background-image: url("../assets/leftpage.png");
  background-size: cover;
}
.magazine .right {
  background-image: url("../assets/rightpage.png");
  background-size: cover;
}

/* bookmark css */
.bookmark button {
  width: 50px;
  height: 120px;
  background-size: cover;
  position: absolute;
  margin: auto;
  color: white;
  text-shadow: 0 -1px 0 #222;
  line-height: 50px;
  text-align: center;
  font-family: monospace;
  content: "";
  z-index: 5;
  border: 0;
  outline: 0;
}
.bookmark #album {
  background: url("../assets/1.png");
  top: 30px;
}
.bookmark #timeline {
  background: url("../assets/2.png");
  top: 150px;
}
.bookmark #chuck {
  background: url("../assets/3.png");
  top: 270px;
}
.bookmark #calender {
  background: url("../assets/4.png");
  top: 390px;
}
</style>