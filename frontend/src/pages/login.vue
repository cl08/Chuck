<template>
    <div id="login_page">
        <!-- 로그인 하면 select push 로 이동 -->
        <!-- <h1>로그인페이지</h1> -->
        <!-- develop에서만 보이기-->
        <div id="login_bg"></div>
        <div id="login_btn"><router-link to="/group"><img src="../assets/kakao_login_medium_narrow.png"></router-link>
        </div>
        
    </div>
</template>

<script>
export default {
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