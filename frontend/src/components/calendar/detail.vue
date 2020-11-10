<template>
    <div style="padding:30px 30px 0px 0px;">
        <div style="padding-bottom:70px;">
            <span class="float-left ml-10">
                <img src="../../assets/logo.svg" class="logo">
            </span>
            <span class="float-right mr-10 mt-4">
                <font size=5>{{ this.getSelectedDay }}</font>
            </span>
        </div>
        <div v-if="getChuckList[getSelectedDiary]" style="margin: 10px 30px 10px 30px;">
            <v-carousel height="400" hide-delimiter-background show-arrows-on-hover>
                <v-carousel-item class="kpa" v-for="(item, i) in getChuckList[getSelectedDiary].image" :key="i" :src="item" target></v-carousel-item>
            </v-carousel>
        </div>
        <div class="mb-15">
            <span v-if="getChuckList[getSelectedDiary]" class="float-left ml-10" style="font-size:large">
                {{ getChuckList[getSelectedDiary].writer }}
            </span>
            <span class="float-right mr-10">
                <a :href="picture" @click="download" target="_blank" download style="text-decoration:none;">
                    <v-icon class="pointer">
                        mdi-download
                    </v-icon>
                </a>
                <v-menu
                    offset-y
                    v-model="menu"
                    v-if="this.getSelectedDiary !== '' && this.getId == this.getChuckList[this.getSelectedDiary].writerId"
                >
                    <template v-slot:activator="{ on, attrs }">
                        <v-icon v-bind="attrs" v-on="on">
                            mdi-dots-horizontal
                        </v-icon>
                    </template>
                    <v-list>
                        <v-list-item class="pointer" @click="edit">
                            <v-list-item-title>수정하기</v-list-item-title>
                        </v-list-item>
                        <v-dialog v-model="dialog" max-width="290">
                            <template v-slot:activator="{ on, attrs }">
                                <v-list-item
                                    class="pointer"
                                    v-bind="attrs"
                                    v-on="on"
                                >
                                    <v-list-item-title>삭제하기</v-list-item-title>
                                </v-list-item>
                            </template>
                            <v-card>
                                <v-card-title class="headline"></v-card-title>
                                <v-card-text><b>정말로 삭제하시겠습니까?</b></v-card-text>
                                <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="green darken-1" text @click="remove()">확인</v-btn>
                                <v-btn color="green darken-1" text @click="clear()">취소</v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-dialog>
                    </v-list>
                </v-menu>
            </span>
        </div>
        <div v-if="getChuckList[getSelectedDiary]" class="mb-5" style="font-size:30px; padding:0px 30px 0px 30px;">
            {{ getChuckList[getSelectedDiary].title }}
        </div>
        <div v-if="getChuckList[getSelectedDiary] && !changeContent" style="font-size:20px; padding:0px 30px 0px 30px;" v-html="getChuckList[getSelectedDiary].content">
        </div>
        <textarea
            v-if="changeContent"
            v-model="content"
            ref="content"
            style="font-size:20px; padding:0px 30px 0px 30px; height:90px; width:596px"
          />
        <v-dialog v-model="dialog" max-width="290" v-if="changeContent">
            <template v-slot:activator="{ on, attrs }">
                <v-btn
                dark
                @click.prevent="cancle"
                >
                취소
                </v-btn>
                <v-btn
                dark
                v-bind="attrs"
                v-on="on"
                >
                수정
                </v-btn>
            </template>
            <v-card>
                <v-card-title class="headline"></v-card-title>
                <v-card-text><b>정말로 수정하시겠습니까?</b></v-card-text>
                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="updateContent()">확인</v-btn>
                <v-btn color="green darken-1" text @click="dialog = false">취소</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import api from '@/utils/api'
import eventBus from '@/utils/EventBus'

export default {
    data () {
        return {
            picture: '',
            content: '',
            changeContent: false,
            dialog: false,
            menu: false,
        }
    },
    computed: {
        ...mapGetters([
            'getSelectedDay',
            'getChuckList',
            'getSelectedDiary',
            'getId',
        ])
    },
    methods: {
        edit() {
            this.content = this.getChuckList[this.getSelectedDiary].content
            this.changeContent = true 
            this.$nextTick(() => {
                this.$refs.content.focus();
            });
        },
        remove() {
            this.dialog = false
            this.menu = false
            api.delete(`diaries/${this.getChuckList[this.getSelectedDiary].id}`, {
                headers: {
                    token: this.$store.getters.getToken,
                }
            }).then(() => {
                this.$store.state.chuckList.splice(this.getSelectedDiary, 1)
                eventBus.$emit('back')
            })
        },
        download() {
            let num = $('.kpa').length
            let picture = ''
            for(let i=0; i<num; i++) {
                if($('.kpa').eq(i).css('display') == 'block') {
                    picture = $('.kpa').eq(i).children().children().eq(1).css('backgroundImage')
                }
            }
            picture = picture.slice(5, -2)
            this.picture = picture
        },
        cancle() {
            this.dialog = false
            this.changeContent = false
            this.content = ''
        },
        clear() {
            this.dialog = false
            this.menu = false
        },
        updateContent() {
            const prev_content = this.getChuckList[this.getSelectedDiary].content
             if(this.content === prev_content) {
                alert('변경된 내용이 없습니다.')
                this.dialog = false
                this.$nextTick(() => {
                    this.$refs.content.focus();
                });
            } else {
                api.put(`diaries/${this.getChuckList[this.getSelectedDiary].id}`, {
                    content: this.content,
                    writerId: this.getChuckList[this.getSelectedDiary].writerId,
                    id: this.getChuckList[this.getSelectedDiary].id,
                }, {
                    headers: {
                        token: this.$store.getters.getToken,
                    }
                }).then(() => {
                    this.$store.state.chuckList[this.getSelectedDiary].content = this.content
                    this.dialog = false
                    this.changeContent = false
                    this.content = ''
                })
            }
        }
    }
}
</script>

<style>

</style>