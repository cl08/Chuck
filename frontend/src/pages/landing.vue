<template>
    <router-link to="/login">
        <div id="landing_page">
        </div>
    </router-link>
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
            $('#landing_page').height(h);
            $('#landing_page').width(w);
        },
    }
}
</script>

<style scoped>
#landing_page {
    background: url('../assets/intro_bg.png');
    background-position: bottom -500px center;
}
</style>