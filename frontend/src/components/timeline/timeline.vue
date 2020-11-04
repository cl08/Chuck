<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="text-left ml-5">
            <img src="../../assets/title/timeline_tabtitle.svg" class="tabtitle">
        </div>
        <v-card class="mx-auto text-left mb-3" width="550" v-for="(item, i) in getChuckList" :key="i" flat="true">
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
            <v-img height="300px" :src="item.img[0]" style="clear:both;">
            </v-img>
                <v-card-title>{{ item.title }}</v-card-title>
            <v-card-text class="text--primary">
                <div v-if="item.content.length < 40">{{ item.content }}</div>
                <div v-else>
                    {{ item.content.slice(0, 40) }}
                    <v-btn color="#8D6262" text>...더보기</v-btn>
                </div>
            </v-card-text>
            <hr size="0.5" color="#E0E0E0">
        </v-card>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'
export default {
    computed: {
        ...mapGetters([
            'getChuckList',
        ])
    },
}
</script>

<style>

</style>