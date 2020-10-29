<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="text-left ml-5">
            <font size=9>TimeLine</font>
        </div>
        <v-card class="mx-auto text-left mb-3" width="550" v-for="(item, i) in getChuckList" :key="i">
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
            <v-img class="white--text align-end" height="200px" :src="item.img[0]" style="clear:both;">
                <v-card-title>{{ item.title }}</v-card-title>
            </v-img>
            <v-card-text class="text--primary">
                <div v-html="item.content"></div>
            </v-card-text>
            <v-card-actions>
                <v-btn color="orange" text>더보기</v-btn>
            </v-card-actions>
        </v-card>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'
import { mapMutations } from 'vuex'
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