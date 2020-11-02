<template>
    <div id="login_page">
        <div id="login_kakao">
            <kakao-login
              api-key='d1baf2cad3354a9138989baea6e65995'
              :on-success=onSuccess
              :on-failure=onFailure
              image="kakao_account_login_btn_medium_narrow"
            />
        </div>

        <div id="login_test" @click="testLogin">
            테스트 계정으로 둘러보기
        </div>
    </div>
</template>

<script>
import KakaoLogin from 'vue-kakao-login';
import api from '@/utils/api';
import jwt from 'jwt-decode';
import store from '@/store';

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
    methods: {
        testLogin() {
            store.dispatch('updateRefreshToken', '1');
            api.post('/users', {
                accessToken: '1',
            }).then(({ data }) => {
                store.dispatch('updateToken', data);
                var decode = jwt(data);
                store.dispatch('updateId', decode.ID);
                store.dispatch('updateName', decode.NAME);
                this.$router.push({name: 'group'});
            });
        },
        handleResize(e){
            var w = window.innerWidth
            var h = window.innerHeight
            $('#login_page').height(h);
            $('#login_page').width(w);
        },
        onSuccess(result) {
            store.dispatch('updateRefreshToken', result.refresh_token);
            api.post('/users', {
                accessToken: result.access_token,
            }).then(({ data }) => {
                store.dispatch('updateToken', data);
                var decode = jwt(data);
                store.dispatch('updateId', decode.ID);
                store.dispatch('updateName', decode.NAME);
                this.$router.push({name: 'group'});
            });
        },
        onFailure(result) {
            console.error(result);
        },
        logout() {
            store.dispatch('logout');
        },
    }
}
</script>

<style scoped>
#login_page {
    background: url('../assets/login_bg.png');
    background-position: bottom center;
}
#login_kakao {
    position:absolute;
    left: 50%;
    margin-left: -110px;
    bottom: 220px;
    cursor: pointer;
}
#login_test {
    position: absolute;
    left: 50%;
    margin-left: -78px;
    bottom: 190px;
    cursor: pointer;
    color: white;
}
</style>