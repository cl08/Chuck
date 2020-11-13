<template>
    <div style="padding:30px 0px 0px 30px;">
        <font v-if="!getModify" size=6>{{ getSelectedDay }}</font>
        <font v-else size=6>{{ this.date }}</font>
        <input type="text" autocomplete="off" placeholder="오늘의 Chuck을 적어주세요." class="el-input__inner" v-model="title">
        <hr size="0.5" color="#E0E0E0" width="88%">
        <el-input type="textarea" :rows="19" placeholder="Chuck에 대한 설명을 적어주세요."  resize="none" v-model="content" style="margin-top:10px;">
        </el-input>
        <div class="solid pointer" v-if="!getModify" @click="write">
            <font size=4>작성 완료</font>
        </div>
        <div class="solid pointer" v-else @click="modify">
            <font size=4>수정 완료</font>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
import eventBus from '@/utils/EventBus';
import api from '../../utils/api.js';
import moment from 'moment';

export default {
    data() {
        return {
            title: '',
            content: '',
            date: '',
            id: '',
        }
    },
    created() {
        eventBus.$on('uploadeDone', () => {
            this.done();
        });
        eventBus.$on('clearWrite', () => {
            this.title = '';
            this.content = '';
            this.date = '';
        });
        eventBus.$on('modifyDiary', (data) => {
            this.id = data.id
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
            'getChuckMap',
        ])
    },
    methods: {
        write() {
            api.post('diaries', {
                title: this.title,
                content: this.content,
                writer: this.getId,
                date: moment(this.getSelectedDay).tz('Asia/Seoul').format(),
                groupId: this.getSelectedGroup.id,
            },{
                headers: {
                    token: this.getToken
                }   
            })
            .then(({ data }) => {
                eventBus.$emit('uploadImages', data.id)
            });
        },
        done() {
            this.title = ''
            this.content = ''
            this.$store.commit('setVisibleWrite', false)
            this.$store.commit('setVisibleCalendar', true)
            this.$store.commit('setVisibleChoice', true)
            eventBus.$emit('updateList')
        },
        modify() {
            api.put(`diaries/${this.id}`, {
                    content: this.content,
                    title: this.title,
                    writerId: this.getChuckMap.get(this.id).writerId,
                    id: this.id,
            }, {
                headers: {
                    token: this.$store.getters.getToken,
                }
            }).then(() => {
                this.$store.state.chuckMap.get(this.id).content = this.content
                this.$store.state.chuckMap.get(this.id).title = this.title
                this.content = ''
                this.title = ''
                eventBus.$emit('uploadImages', this.id)
            })
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