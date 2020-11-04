<template>
  <div>
    <div
      class="floating-button-menu"
      :class="{ 'menu-off': !clicked, 'menu-on': clicked }"
    >
      <div class="floating-button-menu-links">
        <h2>마이페이지</h2>
        <img src="" alt="" />
        <div style="text-align: left; margin: 200px 20px 20px 20px">
          닉네임
          <v-btn
            @click="focusNickname"
            color="orange"
            text
            v-show="!changeNicknameClicked"
            >바꾸기</v-btn
          >
          <v-btn
            @click="changeNickname"
            color="orange"
            text
            v-show="changeNicknameClicked"
            >수정완료</v-btn
          >
          <input
            v-model="username"
            ref="username"
            style="font-size: 24px; width: 240px"
            :disabled="!changeNicknameClicked"
          />
        </div>
        <div style="text-align: left; margin: 20px 20px 120px 20px">
          <router-link to="/group">그룹선택 화면으로</router-link>
          <button id="logout" @click="logout">로그아웃</button>
        </div>
          <button id="closeBtn" @click="close"><h1>x</h1></button>
      </div>
      <div @click="open" class="floating-button-menu-label">
        <img src="@/assets/setting_floatingbtn.svg" alt="" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "UserName",
      clicked: false,
      changeNicknameClicked: false,
    };
  },
  mounted() {},
  methods: {
    focusNickname() {
      this.changeNicknameClicked = true;
      this.$nextTick(() => {
        this.$refs.username.focus();
      });
    },
    changeNickname() {
      // axios
      this.changeNicknameClicked = false;
    },
    close() {
      this.clicked = false;
    },
    open() {
      this.clicked = true;
    },
    logout() {
      this.$store.dispatch("logout");
      console.log("logout");
      this.$router.push("/");
    },
  },
};
</script>

<style lang="scss" scoped>

.floating-button-menu {
  z-index: 5;
  position: fixed;
  bottom: 40px;
  right: 40px;
  cursor: pointer;
  border-radius: 50%;
  min-width: 70px;
  max-width: 0px;
  min-height: 70px;
  max-height: 0px;
  box-shadow: 2px 2px 2px 2px rgba(0, 0, 0, 0.3);
  transition: all ease-in-out 0.3s;
  .floating-button-menu-links {
    width: 0;
    height: 0;
    overflow: hidden;
    opacity: 0;
    // 메뉴 사라질때 
    transition: all 0.3s;
    cursor: auto;
    a {
      position: relative;
      color: #454545;
      text-transform: uppercase;
      text-decoration: none;
      line-height: 49px;
      display: block;
      display: block;
      width: 100%;
      height: 50px;
      transition: background ease-in-out 0.3s;
      background: rgba(0, 0, 0, 0);
      &:last-child {
        border-bottom: 0px solid #fff;
      }
    }
    // &.menu-on {
    //   background: #fff;
    //   width: 100px;
    //   height: 800px;
    //   border-radius: 10px;
    //   opacity: 1;
    //   transition: all ease-in-out 0.5s;
    // }
    #closeBtn {
      width: 30px;
      height: 30px;
      outline: none;
      position: absolute;
      left: 225px;
      bottom: 35px;
    }
    #logout{
      width: 240px;
      height: 50px;
      outline: none;
      text-align: left;
    }
    h2{
      margin: 50px;
      position: absolute;
      left: 30px;
    }
  }

  .floating-button-menu-label {
    text-align: center;
    line-height: 74px;
    font-size: 50px;
    opacity: 1;
    transition: opacity 0.3s;
    img {
      width: 50%;
      height: 50%;
    }
  }

  &.menu-on {
    background: #fff;
    max-width: 280px;
    max-height: 500px;
    border-radius: 10px;
    .floating-button-menu-links {
      width: 100%;
      height: 100%;
      opacity: 1;
    }
    .floating-button-menu-label {
      height: 0px;
      overflow: hidden;
    }
  }
}

</style>