<template>
    <div style="padding:30px 0px 0px 30px;">
        <font size=6>{{ getSelectedDay }}</font><br>
            <el-input type="textarea" :rows="1" placeholder="오늘의 Chuck을 적어주세요." resize="none" v-model="title">
            </el-input>
            <el-input type="textarea" :rows="20" placeholder="Chuck에 대한 설명을 적어주세요."  resize="none" v-model="content">
            </el-input>
        <div class="dash pointer" @click="write">
            <font size=4>작성 완료</font>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import http from '../../utils/api.js'
export default {
    data() {
        return {
            title: '',
            content: '',
        }
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
                console.log(data)
                // 이미지 배열 전송
            });
        }
    }
}
</script>