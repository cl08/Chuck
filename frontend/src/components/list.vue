<!--
    1. 색
    2. content 길 경우 문제. grid 지정
    3. 스크롤
-->
<template>
    <div style="margin:30px 0px 0px 30px;">
        <font size=6>{{ this.getSelectedDay }}</font>
        <v-container>
            <v-row dense>
                <!-- <v-col cols="12">
                    <v-card color="#385F73" dark>
                        <v-card-title class="headline">
                            Unlimited music now
                        </v-card-title>
                        <v-card-subtitle>Listen to your favorite artists and albums whenever and wherever, online and offline.</v-card-subtitle> 
                        <v-card-actions>
                            <v-btn text>Listen Now</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-col> -->
                <v-col v-for="(item, i) in selectedChuckList" :key="i" cols="12" @click="detail" style="cursor:pointer">
                    <v-card :color="item.color">
                        <div class="d-flex flex-no-wrap justify-space-between">
                            <div>
                                <v-card-title class="headline" v-text="item.title"></v-card-title>
                                <!-- <v-card-subtitle v-html="item.content" style="text-align:left;"></v-card-subtitle>
                                <v-card-actions>
                                    <v-btn class="ml-2 mt-5" rounded small>view</v-btn>
                                </v-card-actions> -->
                            </div>
                            <!-- <v-avatar class="ma-3" size="125" tile>
                                <v-img :src="item.img[0]"></v-img>
                            </v-avatar> -->
                        </div>
                    </v-card>
                </v-col>
                <v-col cols="12">
                    <v-card class="mx-auto" outlined @click="write" style="border:dashed 2px gray">
                        <v-avatar size="62" tile style="cursor:pointer">
                        <!-- <v-avatar class="ma-3" size="125" tile style="cursor:pointer"> -->
                            <img src='../assets/add.png'>
                        </v-avatar>
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
        detail() {
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