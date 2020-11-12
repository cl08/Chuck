<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="dash">
            <font size=4>미리보기</font>
        </div>
        <div class="dash" style="height:620px;">
            <el-carousel indicator-position="none" arrow="always" height="580px">
                <el-carousel-item v-for="(chuck, i) in selectedChucks" :key="i">
                    <el-card shadow="none" style="border:0px;">
                        <img :src="chuck.image" style="object-fit:none; width:400px; height:300px; margin-top:60px;">
                            <div style="padding:20px;">
                                <font size=5>{{ chuck.title }}</font>
                            </div>
                            <div v-html="chuck.content"></div>
                    </el-card>
                </el-carousel-item>
            </el-carousel>
        </div>
        <div class="dash pointer">
            <font size=4>PDF로 내려받기</font>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    data() {
        return {
            checkArr: [],
            selectedMap: new Map(),
            selectedChucks: [],
        }
    },
    computed: {
        ...mapGetters([
            'getFaceDataBook',
            'getPersonArrayBook',
        ]),
    },
    watch: {
        getPersonArrayBook: function(data) {
            let change = []
            if(this.checkArr.length == 0) {
                this.checkArr = data.slice()
                for(let i = 0; i<data.length; i++) change.push(i)
            } else {
                for(let i = 0; i<data.length; i++) {
                    if(this.checkArr[i] != data[i]) change.push(i)
                }
                this.checkArr = data.slice()
            }
            if(change.length != 0) {
                change.forEach(index => {
                    const flag = this.checkArr[index]
                    const num = this.getFaceDataBook[index].content_list.length
                    for(let i = 0; i<num; i++) {
                        const path = this.getFaceDataBook[index].content_list[i].image
                        const chuck = this.getFaceDataBook[index].content_list[i]
                        if(this.selectedMap.has(path)) {
                            if(!flag) this.selectedMap.delete(path)
                        } else {
                            if(flag) this.selectedMap.set(path, chuck)
                        }
                    } 
                });
            }
            this.selectedChucks = Array.from(this.selectedMap.values())
        }
    },
}
</script>

<style scoped>

</style>