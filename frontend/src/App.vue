<template>
    <v-app id="app">
        <router-view />
    </v-app>
</template>

<script>

export default {
    name: 'App',
}
</script>

<style>
#app {
    /* background:  linear-gradient(rgba(255, 255, 255, 0.3), rgba(255, 255, 255, 0.3)), url('./assets/bg3.jpg'); */
    background-color: #FEF9F5;
    font-family: MaplestoryOTFLight, Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
}
@font-face {
    font-family: 'MaplestoryOTFLight';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/MaplestoryOTFLight.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>
