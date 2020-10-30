import Vue from 'vue'
import Vuex from 'vuex'
import api from '@/utils/api'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: sessionStorage.getItem('token'),
        ID: sessionStorage.getItem('ID'),
        NAME: sessionStorage.getItem('NAME'),
        refreshToken: sessionStorage.getItem('refreshToken'),
        selectedDay: new Date().getFullYear()+'-'+(new Date().getMonth()+1)+'-'+new Date().getDate(),
        selectedDiary: '',
        visibleCalendar: true,
        visibleDetail: false,
        visibleWrite: false,
        visibleChoice: true,
        visibleAlbum: false,
        visibleVideo: false,
        visiblePreview: false,
        chuckList: [
            {
                id: '0',
                color: '#EEAFAF',
                title: '오프라인 모임',
                content: '모두 모여서 국밥을 먹었따. 팀장님은 자느라 지각함😒',
                img: [
                    'https://img.insight.co.kr/static/2019/08/12/700/y8jzfe6100x3yvgq39el.jpg',
                    'https://i.ytimg.com/vi/iFNARGg-zwM/hqdefault.jpg'
                ],
                writer: '김김현현대대',
                group: 'chuck',
                date: '2020-10-13',
            },
            {
                id: '1',
                color: '#BAE7AF',
                title: '저녁은 족발',
                content: '여기 족발 존맛인듯👍',
                img: [
                    'https://image.auction.co.kr/itemimage/19/15/8d/19158d8796.jpg',
                    'https://img.hani.co.kr/imgdb/resize/2018/1230/00501800_20181230.JPG',
                    'https://sikdorak365.co.kr/web/upload/NNEditor/20180607/jok01.jpg'

                ],
                writer: '김김현현대대',
                group: 'chuck',
                date: '2020-10-13',
            },
            {
                id: '2',
                color: '#FCFFB0',
                title: '두번째 모임',
                content: '역삼역에 노브랜드버거 생김🍔🍔',
                img: [
                    'https://cdn.clien.net/web/api/file/F01/8864280/32415ba78e5af9.jpg',
                    'https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile6.uf.tistory.com%2Fimage%2F9973C13359F03E0F01DFFB'
                ],
                writer: '김김현현대대',
                group: 'chuck',
                date: '2020-10-13',
            },
            {
                id: '3',
                color: '#AFC4E7',
                title: '기획중',
                content: '기획은 역시 오프라인으로~~<br>태그 테스트',
                img: [
                    'https://d32gkk464bsqbe.cloudfront.net/8n5zJRAS_Zn37bM2TsV8AgKmiWg=/1000x600/contents/o/c380a0a7c63bcd4616e9e1488b4510769fed1a91.jpeg'
                ],
                writer: '김김현현대대',
                group: 'chuck',
                date: '2020-10-14',
            },
        ],
        comments: [
            {
                writer: '메뚜기',
                comment: '오오오오 그렇구나',
                diary_id: '',
                date: '',
            },
            {
                writer: '꼴뚜기',
                comment: '아하 그렇구나',
                diary_id: '',
                date: '',
            },
            {
                writer: '망둥어',
                comment: '내용이 길면 어떻게 될까?? 내용이 길면 어떻게 될까?? 내용이 길면 어떻게 될까?? 내용이 길면 어떻게 될까?? 내용이 길면 어떻게 될까?? 내용이 길면 어떻게 될까?? ',
                diary_id: '',
                date: '',
            },
            {
                writer: '피콜로',
                comment: 'ㅋㅋ',
                diary_id: '',
                date: '',
            },
        ]
    },
    getters: {
        getRefreshToken(state) {
            return state.refreshToken
        },
        getToken(state) {
            return state.token
        },
        getId(state) {
            return state.ID
        },
        getName(state) {
            return state.NAME
        },
        getChuckList(state) {
            return state.chuckList
        },
        getSelectedDay(state) {
            return state.selectedDay
        },
        getSelectedDiary(state) {
            return state.selectedDiary
        },
        getVisibleCalendar(state) {
            return state.visibleCalendar
        },
        getVisibleDetail(state) {
            return state.visibleDetail
        },
        getComments(state) {
            return state.comments
        },
        getVisibleWrite(state) {
            return state.visibleWrite
        },
        getVisibleChoice(state) {
            return state.visibleChoice
        },
        getVisibleAlbum(state) {
            return state.visibleAlbum
        },
        getVisibleVideo(state) {
            return state.visibleVideo
        },
        getVisiblePreview(state) {
            return state.visiblePreview
        }
    },
    mutations: {
        setRefreshToken(state, payload) {
            state.refreshToken = payload;
            sessionStorage.setItem('refreshToken', payload)
        },
        setToken(state, payload) {
            state.token = payload
            sessionStorage.setItem('token', payload)
        },
        setId(state, payload) {
            state.ID = payload
            sessionStorage.setItem('ID', payload)
        },
        setName(state, payload) {
            state.NAME = payload
            sessionStorage.setItem('NAME', payload)
        },
        setChuckList(state, payload) {
            state.chuckList = payload
        },
        setSelectedDay(state, payload) {
            state.selectedDay = payload
        },
        setSelectedDiary(state, payload) {
            state.selectedDiary = payload
        },
        setVisibleCalendar(state, payload) {
            state.visibleCalendar = payload
        },
        setVisibleDetail(state, payload) {
            state.visibleDetail = payload
        },
        setComments(state, payload) {
            state.comments = payload
        },
        setVisibleWrite(state, payload) {
            state.visibleWrite = payload
        },
        setComments(state, payload) {
            state.comments = payload
        },
        deleteUser(state) {
            state.NAME = ''
            state.token = ''
            state.ID = ''
            sessionStorage.clear
        },
        setVisibleChoice(state, payload) {
            state.visibleChoice = payload
        },
        setVisibleAlbum(state, payload) {
            state.visibleAlbum = payload
        },
        setVisibleVideo(state, payload) {
            state.visibleVideo = payload
        },
        setVisiblePreview(state, payload) {
            state.visiblePreview = payload
        }
    },
    actions: {
        updateRefreshToken({commit}, item) {
            commit('setRefreshToken', item)
        },
        updateToken({commit}, item) {
            commit('setToken', item)
        },
        updateId({commit}, item) {
            commit('setId', item)
        },
        updateName({commit}, item) {
            commit('setName', item)
        },
        logout({commit}) {
            api.post(`/users/logout`, {
                refreshToken: sessionStorage.getItem('refreshToken')
            }, {
                headers: {
                    token: sessionStorage.getItem('token')
                },
            }).then(() => {
                commit('deleteUser');
            })
        }
    }
})