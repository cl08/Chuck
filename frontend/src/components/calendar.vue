<!--
1. 날짜 연산 : https://dororongju.tistory.com/116
2. 아이콘
3. 년도/월 표현식
4. 년/월 이동
-->
<template>
    <v-row class="fill-height" style="margin:0px 30px 0px 0px;">
        <v-col>
            <v-sheet height="64">
                <v-toolbar flat>
                    <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">
                        Today
                    </v-btn>
                    <v-spacer></v-spacer>
                    <v-btn fab text small color="grey darken-2" @click="prev">
                        <font-awesome-icon :icon="faAngleLeft" />
                    </v-btn>
                    <v-toolbar-title v-if="$refs.calendar">
                        {{ calendarTitle }}
                    </v-toolbar-title>
                    <v-btn fab text small color="grey darken-2" @click="next">
                        <font-awesome-icon :icon="faAngleRight" />
                    </v-btn>
                </v-toolbar>
            </v-sheet>
            <v-sheet height="600">
                <v-calendar ref="calendar"
                v-model="focus"
                color="primary"
                :events="events"
                :event-color="getEventColor"
                :type="type"
                @click:event="showEvent"
                @click:more="viewDay"
                @click:date="viewDay"
                @change="updateRange"
                ></v-calendar>
            </v-sheet>
        </v-col>
    </v-row>
</template>

<script>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faAngleLeft } from '@fortawesome/free-solid-svg-icons'
import { faAngleRight } from '@fortawesome/free-solid-svg-icons'
import { faUser } from '@fortawesome/free-regular-svg-icons'
import { faHeart } from '@fortawesome/free-regular-svg-icons'
import { faShoppingBag } from '@fortawesome/free-solid-svg-icons'
export default {
    data: () => ({
        faAngleLeft,
        faAngleRight,
        focus: '',
        type: 'month',
        
        events: [],
    }),
    mounted () {
          this.$refs.calendar.checkChange()
    },
    computed: {
        calendarTitle: function() {
            if(this.$refs.calendar.title.length == 7){
                return this.$refs.calendar.title.substring(3,7)+"년 "+this.$refs.calendar.title.substring(0,2)
            }
            else{
                return this.$refs.calendar.title.substring(4,8)+"년 "+this.$refs.calendar.title.substring(0,3)
            }
        }
    },
    methods: {
        viewDay ({ date }) {
            alert("리스트가 나와야 함")
            this.focus = date
        },
        getEventColor (event) {
            return event.color
        },
        setToday () {
            this.focus = ''
        },
        prev () {
            this.$refs.calendar.prev()
        },
        next () {
            this.$refs.calendar.next()
        },
        showEvent ({ nativeEvent, event }) {
            alert("디테일 페이지가 나와야함")
        },
        updateRange ({ start, end }) {
            const events = []
            const today = new Date()
            const yesterday = new Date()
            yesterday.setDate(today.getDate()-1)
            events.push({
                name: '추억1',
                start: today,
                color: 'yellow',
            })
            events.push({
                name: '추억2',
                start: today,
                color: 'green',
            })
            events.push({
                name: '추억3',
                start: today,
                color: 'green',
            })
            events.push({
                name: '추억4',
                start: today,
                color: 'green',
            })
            events.push({
                name: '추억3',
                start: yesterday,
                color: 'red',
            })
            this.events = events
        },
        rnd (a, b) {
            return Math.floor((b - a + 1) * Math.random()) + a
        },
    },
}
</script>
<style>

</style>