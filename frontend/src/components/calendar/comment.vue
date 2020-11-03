<!--
    1. 댓글 좌우 번갈아 가면서 나오기
    2. 크기고정 + 스크롤
-->
<template>
    <div style="margin:30px 0px 0px 30px; height:680px; overflow:scroll;">
        <div v-for="(item, index) in getComments" :key="index" class="post-it">
            <p class="note">
                <strong>{{ item.writer }}</strong><br>
                {{ item.comment }}<br>
            </p>
        </div>
        <div class="comment">      
            <div class="el-input el-input-group el-input-group--append">
                <input type="text" autocomplete="off" placeholder="댓글 입력" class="el-input__inner" v-model="input" style="text-align:left">
                <div class="el-input-group__append">
                    <i class="el-icon-edit"></i>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    data() {
        return{
            input:'',
        }
    },
    computed: {
        ...mapGetters([
            'getComments'
        ]),
    },
    methods: {
        comment() {
            alert("ㅋㅋ")
        }
    }
}
</script>

<style scoped>
.post-it {
    display: inline-block;
    padding: 20px;
}
.note { 
    -webkit-box-shadow: #DDD 0px 1px 2px;
    position: relative;
    background-color: #F4F39E;
    border-color: #DEE184;
    text-align: center;
    /* margin: 1.5em auto; */
    padding: 1.5em 1em;
    -webkit-box-shadow: 0px 1px 3px rgba(0,0,0,0.25);
    -moz-box-shadow: 0px 1px 3px rgba(0,0,0,0.25);
    box-shadow: 0px 1px 3px rgba(0,0,0,0.25);
    -webkit-transform: rotate(2deg);
    -moz-transform: rotate(2deg);
    -o-transform: rotate(2deg);
    -ms-transform: rotate(2deg);
    transform: rotate(2deg);
    width: 180px;
    height: 160px;
    overflow: scroll;
}
.note:after {
    display: block;
    content: "";
    position: absolute; 
    width: 110px;
    height: 30px;
    top: -21px;
    left: 30%;    
    border: 1px solid #fff;
    background: rgba(254, 254, 254, .7);
    -webkit-box-shadow: 0px 0 3px rgba(0,0,0,0.1);
    -moz-box-shadow: 0px 0 3px rgba(0,0,0,0.1);
    box-shadow: 0px 0 3px rgba(0,0,0,0.1);  
}
.comment { 
    position: absolute; 
    bottom: 30px;
    left: 80px;
    width:500px;
}
</style>