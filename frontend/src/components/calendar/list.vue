<!--
    1. content 길 경우 문제. 자르고 ... 표시
-->
<template>
    <div style="padding:30px 0px 0px 30px;">
        <v-row v-if="searchFlag">
            <span class="col-3">
            </span>
            <span class="col-6">
                <font size=6>{{ this.getSelectedDay }}</font>
            </span>
            <span class="col-3 float-right">
                <el-button icon="el-icon-search" circle @click="openSearchBar"></el-button>
                <el-button icon="el-icon-edit" circle @click="write"></el-button>
            </span>
        </v-row>

        <v-row v-else>
            <span class="col-12 float-right">
                <div class="el-input el-input--suffix" style="width: 80%; margin:0px 12px 0px 26px;">
                    <input type="text" autocomplete="off" class="el-input__inner" id="searchBar" v-model="keyword" @keydown.enter="search" style="text-align:left">
                    <span class="el-input__suffix">
                        <span class="el-input__suffix-inner">
                            <i class="el-input__icon el-icon-close pointer" @click="closeSearchBar"></i>
                        </span>
                    </span>
                </div>
                <el-button icon="el-icon-edit" circle @click="write"></el-button>
            </span>
        </v-row>

        <v-container v-if="selectedChuckList.length != 0" style="padding:0px 20px 0px 20px;">
            <div v-show="searchResult">'{{ searchResult }}' 검색 결과</div>
            <v-row dense>
                <v-col v-for="(item, i) in selectedChuckList" :key="i" cols="12" @click="detail(item)" style="cursor:pointer">
                    <v-card>
                        <div class="d-flex flex-no-wrap">
                            <span :style="{backgroundColor:item.color, color:item.color}">dd</span>
                            <v-avatar size="125" tile>
                                <v-img :src="item.image[0]"></v-img>
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

        <v-container v-else style="padding:0px 20px 0px 20px;">
            <font size=5>오늘의 새로운 Chuck을 작성해 보세요</font>
        </v-container>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { mapMutations } from 'vuex'
import eventBus from '@/utils/EventBus'
import store from '@/store'

export default {
    data: () => ({
        searchFlag: true,
        searchResult: '',
        keyword: '',
        color: [],
    }),
    mounted() {
        this.color = this.$store.getters.getColor;
    },
    created() {
        eventBus.$on('updateList', () => {
            store.dispatch('updateSelectedChuckList')
        });
        eventBus.$on('showDetail', (data) => {
            this.detail(data);
        });
    },
    computed: {
        ...mapGetters([
            'getChuckList',
            'getSelectedDay',
        ]),
        selectedChuckList: () => store.getters.getSelectedChuckList,
    },
    watch: {
        getSelectedDay() {
           store.dispatch('updateSelectedChuckList')
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
        detail(item) {
            store.dispatch('updateComments', item.id)            
            this.setSelectedDiary(item.index)
            this.setVisibleDetail(true)
            this.setVisibleCalendar(false)
            this.setVisibleWrite(false)
        },
        write() {
            this.setVisibleWrite(true)
            this.setVisibleDetail(false)
            this.setVisibleCalendar(false)
        },
        openSearchBar() {
            this.keyword = ''
            this.searchFlag = false
            setTimeout(function(){
                document.getElementById('searchBar').focus()
            }, 1);
        },
        closeSearchBar() {
            this.searchFlag = true
            this.searchResult = ''
        },
        search() {
            this.searchResult = this.keyword
            this.keyword = ''
            // 검색 구현
        }
    }
}
</script>

<style>

</style>