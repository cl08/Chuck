<template>
<div id="flipbook">
    <div class="hard"> Turn.js </div> 
    <div class="hard"></div>
    <div> Page 1 </div>
    <div> Page 2 </div>
    <div> Page 3 </div>
    <div> Page 4 </div>
    <div class="hard"></div>
    <div class="hard"></div>
</div>
</template>

<script>

export default {
    mounted() {
        $("#flipbook").turn({
            width: 400,
            height: 300,
            autoCenter: true
        });
    }
}
</script>

<style>

</style>