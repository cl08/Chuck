<template>
    <div style="padding:30px 0px 0px 30px;">
        <div class="text-left ml-5">
            <img src="../../assets/title/timeline_tabtitle.svg" class="tabtitle">
            <div class="underline"></div>
        </div>
        <v-container v-if="chuckList.length != 0" style="padding:30px 20px 0px 0px;">
            <v-col v-for="(item, index) in chuckList" :key="index" cols="12" @click="detail(item)" style="padding:0px;">
                <!-- {{ chuckList[index].date}} -->
                <img v-if="index===0" src="../../assets/timeline01.png" class="line" style="height:80px;">
                <img v-else-if="chuckList[index-1].date === chuckList[index].date" src="../../assets/timeline03.png" class="line" style="height:80px;">
                <img v-else src="../../assets/timeline02.png" class="line" style="height:120px; width:80px;">
                <v-card class="pointer" style="box-shadow: 2px 2px 5px 1px #CED4DA; display:inline-block; bottom:12px;">
                    <div class="d-flex flex-no-wrap">
                        <div class="belt" :style="{backgroundColor:item.color}"></div>
                        <v-avatar size="60" tile style="margin:5px 0px;">
                            <v-img :src="item.image[0]"></v-img>
                        </v-avatar>
                        <div style="width:400px;">
                            <v-card-subtitle style="text-align:left; padding:10px 12px; color:#2F2F2F">{{ item.title }} </v-card-subtitle>
                            <v-card-text style="padding:0px; color:#8D6262; font-size:12px;">
                                <span class="col-6 float-left" style="text-align:left; padding: 0px 12px;">
                                    작성일 : {{ item.date }}
                                </span>
                                <span class="col-6 float-right" style="text-align:right; padding: 0px 12px;">
                                    작성자 : {{ item.writer }}
                                </span>
                            </v-card-text>
                        </div>
                    </div>
                </v-card>
            </v-col>
        </v-container>
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
            eventBus.$emit('movePage', {index: 1, item: item})
        },
    }
}
</script>

<style scoped>
.belt {
    display: inline-block;
    height: 60px;
    width: 6px;
    margin: 4px;
    border-radius: 5px;
}
.line {
    display:inline-block;
}
</style>