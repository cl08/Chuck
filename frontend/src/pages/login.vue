<template>
    <div id="login_page">
        <!-- 로그인 하면 select push 로 이동 -->
        <!-- <h1>로그인페이지</h1> -->
        <!-- develop에서만 보이기-->
        <div id="login_bg"></div>
        <div id="login_btn">
            <kakao-login
              api-key='d1baf2cad3354a9138989baea6e65995'
              :on-success=onSuccess
              :on-failure=onFailure
              image="kakao_account_login_btn_medium_narrow"
            />
        </div>

        <!-- <router-link to="/group"><img src="../assets/kakao_login_medium_narrow.png"></router-link> -->
    </div>
</template>

<script>
import KakaoLogin from 'vue-kakao-login';
import api from '@/utils/api';

export default {
    components: {
        KakaoLogin,
    },
    mounted() {
        var w = window.innerWidth
        var h = window.innerHeight
        $('#login_page').height(h);
        $('#login_page').width(w);
        window.addEventListener("resize", this.handleResize)
    },
    methods:{
        handleResize(e){
            var w = window.innerWidth
            var h = window.innerHeight
            $('#login_page').height(h);
            $('#login_page').width(w);
        },
        onSuccess(result) {
        api.post('/users', {
            access_token: result.access_token,
        }).then(({ data }) => {
            console.log(data);
            this.$router.push({name: 'group'});
        });
    },
    onFailure(result) {
      console.error(result);
    },

    }
}
</script>

<style scoped>
#login_bg {
    background: url('../assets/login_bg.png');
    background-size: cover;
    position:absolute;
    height: 810px;
    width: 1440px;
    bottom: 0px;
    left:50%;
    margin-left:-720px;
}
#login_btn {
    position:absolute;
    left: 50%;
    margin-left:-91px;
    bottom: 200px;
    cursor: pointer;
}
#login_page {
    background-color:#FEF9F5
}
</style>