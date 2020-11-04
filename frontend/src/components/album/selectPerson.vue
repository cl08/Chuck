<template>
    <div style="margin:30px 30px 0px 0px;">
            <img src="../../assets/title/chuckBook_tabtitle.svg" class="subtitle">
        <div class="dash">
            <font size=4>기간 선택</font>
        </div>
        <div class="dash" style="height:210px; text-align:left; overflow:auto;">
            <div style="margin:10px">
                <span style="display:inline-block; width:100px;">날짜 선택</span>
                <el-date-picker type="dates" v-model="value1" placeholder="날짜 선택" value-format="yyyy-MM-dd" size="small" @change="pickType1">
                </el-date-picker>
            </div>
            <div style="margin:10px">
                <span style="display:inline-block; width:100px;">범위 선택</span>
                <el-date-picker type="daterange" v-model="value2" start-placeholder="시작일" end-placeholder="종료일" value-format="yyyy-MM-dd" size="small" @change="pickType2">
                </el-date-picker>
            </div>
            <div style="margin:10px">
                <span style="display:inline-block; width:100px;">선택된 기간</span>
                <span v-if="value1">
                    <el-tag v-for="(tag, index) in value1" :key="index" closable @close="removeTag(tag)" type="info" style="margin:2px;">{{ tag }}</el-tag>
                </span>
                <span v-else-if="value2">
                    <el-tag type="info" style="margin:2px;">{{ value2[0] }}</el-tag>
                    ~
                    <el-tag type="info" style="margin:2px;">{{ value2[1] }}</el-tag>
                </span>
                <span v-else>
                    <font color="DCDFE6">기간을 선택해 주세요</font>
                </span>
            </div>
        </div>
        <div class="dash">
            <font size="4">인물 선택</font>
        </div>
        <div class="dash" style="height:325px;">
            <span class="face pointer" @click="selectAll">ALL</span>
            <span class="face pointer" v-for="(face, index) in faces" :key="index" @click="select(index)" :style="'background-image:url('+face+')'">
                <img :id="'albumFace'+index" class="albumFaceNoneDisplay" src="../../assets/check_circle.svg">
            </span>
        </div>
    </div>
</template>

<script>
export default {
    data () {
        return {
            value1: '',
            value2: '',
            dates: [],
            faces:
            [
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
            this.value1.splice(this.value1.indexOf(tag), 1);
        },
        pickType1(){
            this.value2 = ''
        },
        pickType2(){
            this.value1 = ''
        },
        selectAll() {
            for(let i=0; i<this.faces.length; i++){
                let el = document.getElementById('albumFace'+i)
                el.setAttribute('class', '')
            }
        },
        select(index) {
            let el = document.getElementById('albumFace'+index)
            el.classList.toggle("albumFaceNoneDisplay")
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
.face {
    height: 90px;
    width: 90px;
    line-height: 90px;
    margin: 7px;
    border-radius: 50%;
    display: inline-block;
    box-sizing: border-box;
    text-align: center;
    color: #fff;
    background: #C0C4CC;
    background-size: cover;
    font-size: 26px;
    overflow: hidden;
}
.face img {
    height: 100%;
}
.albumFaceNoneDisplay {
    display: none;
}
</style>