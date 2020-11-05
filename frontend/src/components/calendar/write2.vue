<template>
    <div style="padding:30px 0px 0px 30px;">
        <font size=6>{{ getSelectedDay }}</font>
        <input type="text" autocomplete="off" placeholder="오늘의 Chuck을 적어주세요." class="el-input__inner" v-model="title">
        <hr size="0.5" color="#E0E0E0" width="88%">
        <el-input type="textarea" :rows="19" placeholder="Chuck에 대한 설명을 적어주세요."  resize="none" v-model="content" style="margin-top:10px;">
        </el-input>
        <div class="solid pointer" @click="write">
            <font size=4>작성 완료</font>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
import evnetBus from '@/utils/EventBus';
import http from '../../utils/api.js';

export default {
    data() {
        return {
            title: '',
            content: '',
        }
    },
    created() {
        evnetBus.$on('uploadeDone', () => {
            this.done();
        });
    },
    computed: {
        ...mapGetters([
            'getSelectedDay',
            'getId',
            'getSelectedDay',
            'getSelectedGroup',
            'getToken'
        ])
    },
    methods: {
        write() {
            http.post('diaries', {
                title: this.title,
                content: this.content,
                writer: this.getId,
                date: this.getSelectedDay,
                groupId: this.getSelectedGroup.id,
            },{
                headers: {
                    token: this.getToken
                }   
            })
            .then(({ data }) => {
                evnetBus.$emit('uploadImages', data.id);
            });
        },
        done() {
            this.title = '';
            this.content = '';
            this.$store.commit('setVisibleWrite', false);
            this.$store.commit('setVisibleCalendar', true);
            this.$store.commit('setVisibleChoice', true);
        }
    }
}
</script>
<style scoped>
.el-input__inner {
    text-align:left;
    width: 91%;
    border: none;
    font-size:25px;
    margin-top: 20px;
}
</style>