<template>
  <div id="app">
    <!-- <LandingPage /> -->
    <router-view />
  </div>
</template>

<script>

import LandingPage from '@/pages/landing.vue'
export default {
  name: 'App',
  components: {
    LandingPage
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
body {
    background:  linear-gradient(
      rgba(255, 255, 255, 0.3), 
      rgba(255, 255, 255, 0.3)), url('./assets/bg4.jpg');
  font-family: 'Stalemate', cursive;
}
</style>
