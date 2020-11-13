<template>
    <div style="padding:30px 0px 0px 30px;">
        <font v-if="!getModify" size=6>{{ getSelectedDay }}</font>
        <font v-else size=6>{{ this.date }}</font>
        <input type="text" autocomplete="off" placeholder="오늘의 Chuck을 적어주세요." class="el-input__inner" v-model="title">
        <hr size="0.5" color="#E0E0E0" width="88%">
        <el-input type="textarea" :rows="19" placeholder="Chuck에 대한 설명을 적어주세요."  resize="none" v-model="content" style="margin-top:10px;">
        </el-input>
        <div class="solid pointer" @click="write">
            <font size=4 v-if="!getModify">작성 완료</font>
            <font size=4 v-else>수정 완료</font>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
import eventBus from '@/utils/EventBus';
import http from '../../utils/api.js';
import moment from 'moment';

export default {
    data() {
        return {
            title: '',
            content: '',
            date: '',
        }
    },
    created() {
        eventBus.$on('uploadeDone', () => {
            this.done();
        });
        eventBus.$on('clearWrite', () => {
            this.title = '';
            this.content = '';
        });
        eventBus.$on('modifyDiary', (data) => {
            this.title = data.title
            this.content = data.content
            this.date = data.date
        });
    },
    computed: {
        ...mapGetters([
            'getSelectedDay',
            'getId',
            'getSelectedDay',
            'getSelectedGroup',
            'getToken',
            'getModify',
        ])
    },
    methods: {
        write() {
            http.post('diaries', {
                title: this.title,
                content: this.content,
                writer: this.getId,
                date: this.getModify ? moment(this.date).tz('Asia/Seoul').format() : moment(this.getSelectedDay).tz('Asia/Seoul').format(),
                groupId: this.getSelectedGroup.id,
            },{
                headers: {
                    token: this.getToken
                }   
            })
            .then(({ data }) => {
                this.$store.dispatch('updateModify', false)
                eventBus.$emit('uploadImages', data.id)
            });
        },
        done() {
            this.title = ''
            this.content = ''
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
    font-size:20px;
    margin-top: 20px;
}
</style>