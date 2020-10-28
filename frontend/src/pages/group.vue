<template>
    <div>
        <h1>그룹 선택 페이지</h1>
        <a @click="logout">logout</a>
        <br>
        <router-link to="/diary">그룹 선택</router-link>
    </div>
</template>

<script>
export default {
    methods: {
        logout() {
                this.$store.dispatch('logout');
        },
    }
}
</script>

<style>

</style>