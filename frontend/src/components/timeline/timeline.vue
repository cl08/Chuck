<template>
    <div style="padding:30px 0px 0px 30px;">
        <div class="text-left ml-5">
            <img src="../../assets/title/timeline_tabtitle.svg" class="tabtitle">
            <div class="underline"></div>
        </div>
        <v-card class="mx-auto text-left mb-3" width="550" v-for="(item, i) in chuckList" :key="i" :flat=true>
            <v-card-subtitle class="pt-0">
                <div class="float-left pt-2">
                    {{ item.writer }}
                </div>
                <div class="float-right">
                    <v-menu offset-y>
                        <template v-slot:activator="{ on, attrs }">
                            <v-icon large v-bind="attrs" v-on="on">
                                mdi-dots-horizontal
                            </v-icon>
                        </template>
                        <v-list>
                            <v-list-item>
                                <v-list-item-title>여기에는</v-list-item-title>
                            </v-list-item>
                            <v-list-item>
                                <v-list-item-title>무엇이</v-list-item-title>
                            </v-list-item>
                            <v-list-item>
                                <v-list-item-title>들어갈까?</v-list-item-title>
                            </v-list-item>
                        </v-list>
                    </v-menu>
                </div>
            </v-card-subtitle>
            <v-img height="300px" :src="item.image[0]" style="clear:both; cursor: pointer;" @click="detail(item)">
            </v-img>
                <v-card-title>{{ item.title }}</v-card-title>
            <v-card-text class="text--primary">
                <div v-if="item.content.length < 40">{{ item.content }}</div>
                <div v-else>
                    {{ item.content.slice(0, 40) }}
                    <v-btn color="#3D91FF" text @click="detail(item)">...더보기</v-btn>
                </div>
            </v-card-text>
            <hr size="0.5" color="#E0E0E0">
        </v-card>
    </div>
</template>
<script>
import store from '@/store'
import { mapMutations } from 'vuex'
import eventBus from '@/utils/EventBus'

export default {
    computed: {
        groupInfo: () => store.getters.getSelectedGroup,
        chuckList: () => store.getters.getChuckList,
    },
    methods: {
        ...mapMutations([
            'setSelectedDiary',
            'setVisibleCalendar',
            'setVisibleDetail',
            'setVisibleWrite',
        ]),
        detail(item) {
            // store.dispatch('updateComments', item.id)            
            // this.setSelectedDiary(item.index)
            // this.setVisibleDetail(true)
            // this.setVisibleCalendar(false)
            // this.setVisibleWrite(false)
            eventBus.$emit('movePage', {index: 1, item: item})
        },
    }
}
</script>

<style>

</style>