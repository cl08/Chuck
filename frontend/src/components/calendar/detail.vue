<template>
    <div style="margin:30px 30px 0px 0px;">
        <div style="padding-bottom:70px;">
            <span class="float-left ml-10">
                <img src="../../assets/logo.svg" class="tabtitle">
            </span>
            <span class="float-right mr-10 mt-4">
                <font size=5>{{ this.getSelectedDay }}</font>
            </span>
        </div>
        <div v-if="getChuckList[getSelectedDiary]" style="margin: 10px 30px 10px 30px;">
            <v-carousel height="400" hide-delimiter-background show-arrows-on-hover>
                <v-carousel-item v-for="(item, i) in getChuckList[getSelectedDiary].img" :key="i" :src="item"></v-carousel-item>
            </v-carousel>
        </div>
        <div class="mb-15">
            <span v-if="getChuckList[getSelectedDiary]" class="float-left ml-10" style="font-size:large">
                {{ getChuckList[getSelectedDiary].writer }}
            </span>
            <span class="float-right mr-10">
                <v-icon >
                    mdi-chat-outline
                </v-icon>
                <v-icon >
                    mdi-download
                </v-icon>
                <v-menu offset-y>
                    <template v-slot:activator="{ on, attrs }">
                        <v-icon v-bind="attrs" v-on="on">
                            mdi-dots-horizontal
                        </v-icon>
                    </template>
                    <v-list>
                        <v-list-item>
                            <v-list-item-title>수정하기</v-list-item-title>
                        </v-list-item>
                        <v-list-item>
                            <v-list-item-title>삭제하기</v-list-item-title>
                        </v-list-item>
                    </v-list>
                </v-menu>
            </span>
        </div>
        <div v-if="getChuckList[getSelectedDiary]" class="mb-5" style="font-size:30px;">
            {{ getChuckList[getSelectedDiary].title }}
        </div>
        <div v-if="getChuckList[getSelectedDiary]" style="font-size:20px;" v-html="getChuckList[getSelectedDiary].content">
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    data () {
        return {
            items: [
                {
                    src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
                },
                {
                    src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
                },
                {
                    src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
                },
                {
                    src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
                },
            ],
        }
    },
    computed: {
        ...mapGetters([
            'getSelectedDay',
            'getChuckList',
            'getSelectedDiary',
        ])
    },
}
</script>

<style>

</style>