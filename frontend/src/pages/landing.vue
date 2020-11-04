<template>
    <div id="landing_page">
        <router-link to="/login">
            <img id="landing_book" src="../assets/intro_book.png">
        </router-link>
    </div>
</template>

<script>
export default {

    mounted() {
        var w = window.innerWidth
        var h = window.innerHeight
        $('#landing_page').height(h);
        $('#landing_page').width(w);
        window.addEventListener("resize", this.handleResize)
    },
    methods: {
        handleResize(e){
            var w = window.innerWidth
            var h = window.innerHeight
            $('#landing_page').width(w);
            $('#landing_book').css('top', '50%')
        },
    }
}
</script>

<style scoped>
#landing_page {
    background: url('../assets/intro_bg.png');
    background-position: bottom center;
}
#landing_book {
    position: relative;
    top:50%;
}
</style>