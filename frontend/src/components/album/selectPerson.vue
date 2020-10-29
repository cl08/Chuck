<!--
    1. 레이아웃 범위 지정 (ex:form)
-->
<template>
    <div style="margin:30px 30px 0px 0px;">
        <font size=9>사진첩 만들기</font>
        <div class="dash">
            <font size=4>기간 선택</font>
        </div>
        <div class="dash" style="height:240px; text-align:left; overflow:auto;">
            <div style="margin:10px">
                날짜 선택
                <el-date-picker type="dates" v-model="dates" placeholder="날짜 선택" value-format="yyyy-MM-dd" size="small">
                </el-date-picker>
            </div>
            <div style="margin:10px">
                범위 선택
                <el-date-picker type="daterange" v-model="dates" start-placeholder="시작일" end-placeholder="종료일" value-format="yyyy-MM-dd" size="small">
                </el-date-picker>
            </div>
            <div style="margin:10px">
                선택된 기간
                <!--
                    기간을 선택해주세요
                -->
                <el-tag v-for="(tag, index) in dates" :key="index" closable @close="removeTag(tag)" type="info" style="margin:2px;">{{ tag }}</el-tag>
            </div>
        </div>
        <div class="dash">
            <font size="4">인물 선택</font>
        </div>
        <div class="dash" style="height:300px; text-align:left; overflow:auto;">
                <el-avatar :size="86" bordered style="margin:5px 10px 5px 10px;" color="black">ALL</el-avatar>
            <span v-for="(face, index) in faces" :key="index">
                <el-avatar :size="86" :src="face" bordered style="margin:5px 10px 5px 10px;"></el-avatar>
            </span>
        </div>
    </div>
</template>

<script>
export default {
    data () {
        return {
            dates: [],
            faces: [
                'https://pbs.twimg.com/profile_images/1306539284212539392/aJrYjxho.jpg',
                'https://post-phinf.pstatic.net/MjAxOTA1MTZfMTEg/MDAxNTU3OTg3NzEyMDM4.m3__BqbSluWgyBBVca8kkg6COBQHGYtYQzwQR_hJ3RUg.3DeOn797qHrvboiIBMSLvBxY5W4vGB2OLx1XoYAENJAg.JPEG/17.jpg?type=w1200',
                'https://yt3.ggpht.com/a/AATXAJzAGhJRXaxZihohn-Ydp7s0jmLkLT28ZOGloycVXg=s900-c-k-c0x00ffffff-no-rj',
                'https://pbs.twimg.com/profile_images/1306539284212539392/aJrYjxho.jpg',
                'https://post-phinf.pstatic.net/MjAxOTA1MTZfMTEg/MDAxNTU3OTg3NzEyMDM4.m3__BqbSluWgyBBVca8kkg6COBQHGYtYQzwQR_hJ3RUg.3DeOn797qHrvboiIBMSLvBxY5W4vGB2OLx1XoYAENJAg.JPEG/17.jpg?type=w1200',
                'https://yt3.ggpht.com/a/AATXAJzAGhJRXaxZihohn-Ydp7s0jmLkLT28ZOGloycVXg=s900-c-k-c0x00ffffff-no-rj',
                'https://pbs.twimg.com/profile_images/1306539284212539392/aJrYjxho.jpg',
                'https://post-phinf.pstatic.net/MjAxOTA1MTZfMTEg/MDAxNTU3OTg3NzEyMDM4.m3__BqbSluWgyBBVca8kkg6COBQHGYtYQzwQR_hJ3RUg.3DeOn797qHrvboiIBMSLvBxY5W4vGB2OLx1XoYAENJAg.JPEG/17.jpg?type=w1200',
                'https://pbs.twimg.com/profile_images/1306539284212539392/aJrYjxho.jpg',
                'https://post-phinf.pstatic.net/MjAxOTA1MTZfMTEg/MDAxNTU3OTg3NzEyMDM4.m3__BqbSluWgyBBVca8kkg6COBQHGYtYQzwQR_hJ3RUg.3DeOn797qHrvboiIBMSLvBxY5W4vGB2OLx1XoYAENJAg.JPEG/17.jpg?type=w1200',
                'https://yt3.ggpht.com/a/AATXAJzAGhJRXaxZihohn-Ydp7s0jmLkLT28ZOGloycVXg=s900-c-k-c0x00ffffff-no-rj',
                'https://pbs.twimg.com/profile_images/1306539284212539392/aJrYjxho.jpg',
                'https://post-phinf.pstatic.net/MjAxOTA1MTZfMTEg/MDAxNTU3OTg3NzEyMDM4.m3__BqbSluWgyBBVca8kkg6COBQHGYtYQzwQR_hJ3RUg.3DeOn797qHrvboiIBMSLvBxY5W4vGB2OLx1XoYAENJAg.JPEG/17.jpg?type=w1200',
                'https://yt3.ggpht.com/a/AATXAJzAGhJRXaxZihohn-Ydp7s0jmLkLT28ZOGloycVXg=s900-c-k-c0x00ffffff-no-rj',
                'https://pbs.twimg.com/profile_images/1306539284212539392/aJrYjxho.jpg',
                'https://post-phinf.pstatic.net/MjAxOTA1MTZfMTEg/MDAxNTU3OTg3NzEyMDM4.m3__BqbSluWgyBBVca8kkg6COBQHGYtYQzwQR_hJ3RUg.3DeOn797qHrvboiIBMSLvBxY5W4vGB2OLx1XoYAENJAg.JPEG/17.jpg?type=w1200',
            ]
        }
    },
    methods: {
        removeTag(tag) {
            this.dates.splice(this.dates.indexOf(tag), 1);
        },
        test() {
            alert("tt")
        }
    }
  }
</script>
<style scoped>
.content {
    height: 220px;
    text-align: left;
    overflow: auto;
    font-size: 3;
}
</style>