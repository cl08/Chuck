<!--
    1. content 길 경우 문제. 자르고 ... 표시
    2. 검색 기능
-->
<template>
    <div style="margin:30px 0px 0px 30px;">
        <v-row>
            <span class="col-3">
            </span>
            <span class="col-6">
                <font size=6>{{ this.getSelectedDay }}</font>
            </span>
            <span class="col-3 float-right">
                <el-button icon="el-icon-search" circle></el-button>
                <el-button icon="el-icon-edit" circle @click="write"></el-button>
            </span>
        </v-row>
        <v-container style="padding:0px 20px 0px 20px;">
            <v-row dense>
                <v-col v-for="(item, i) in selectedChuckList" :key="i" cols="12" @click="detail(selectedChuckList[i].id)" style="cursor:pointer">
                    <v-card>
                        <div class="d-flex flex-no-wrap">
                            <span :style="{backgroundColor:item.color, color:item.color}">dd</span>
                            <v-avatar size="125" tile>
                                <v-img :src="item.img[0]"></v-img>
                            </v-avatar>
                            <div style="width:400px;">
                                <v-card-title>{{ item.title }} </v-card-title>
                                <v-card-subtitle v-text="item.content.slice(0, 30)" style="text-align:left; paadingBottom:0px;"></v-card-subtitle>
                                <v-card-text>
                                    <v-row>
                                        <span class="col-6" style="text-align:left; padding: 0px 12px;">
                                        작성자 : {{ item.writer }}
                                        </span>
                                        <span class="col-6" style="text-align:right; padding: 0px 12px;">
                                        작성일 : {{ item.date }}
                                        </span>
                                    </v-row>
                                </v-card-text>
                            </div>
                        </div>
                    </v-card>
                </v-col>
            </v-row>
      </v-container>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { mapMutations } from 'vuex'
export default {
    data: () => ({
        selectedChuckList: [],
    }),
    mounted() {
        for(var i=0; i<this.getChuckList.length; i++) {
            if(this.getChuckList.date === this.getSelectedDay) {
                this.selectedChuckList.push(this.getChuckList[i])
            }
        }
    },
    computed: {
        ...mapGetters([
            'getChuckList',
            'getSelectedDay',
        ])
    },
    watch: {
        getSelectedDay: function() {
            this.selectedChuckList= []
            for(var i=0; i<this.getChuckList.length; i++) {
                if(this.getChuckList[i].date === this.getSelectedDay) {
                    this.selectedChuckList.push(this.getChuckList[i])
                }
            }
        }
    },
    methods: {
        ...mapMutations([
            'setSelectedDay',
            'setSelectedDiary',
            'setVisibleCalendar',
            'setVisibleDetail',
            'setVisibleWrite',
        ]),
        detail(id) {
            this.setSelectedDiary(id)
            this.setVisibleDetail(true)
            this.setVisibleCalendar(false)
            this.setVisibleWrite(false)
        },
        write() {
            this.setVisibleWrite(true)
            this.setVisibleDetail(false)
            this.setVisibleCalendar(false)
        }
    }
}
</script>

<style>

</style>