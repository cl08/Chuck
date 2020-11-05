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
        selectedGroup: JSON.parse(sessionStorage.getItem('selectedGroup')),
        selectedDay: new Date().getFullYear()+'-'+(new Date().getMonth()+1)+'-'+new Date().getDate(),
        selectedDiary: '',
        visibleCalendar: true,
        visibleDetail: false,
        visibleWrite: false,
        visibleChoice: true,
        visibleAlbum: false,
        visibleVideo: false,
        visiblePreview: false,
        chuckList: [],
        comments: [],
        personArray : [],
    },
    getters: {
        getSelectedGroup(state) {
            return state.selectedGroup
        },
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
        },
        getComments(state) {
            return state.comments
        },
        getPersonArray(state) {
            return state.personArray
        },
        getPersonClassificationResult(state){
            for(let i = 0; i < state.personArray.length; i++){
                if(state.personArray[i]){
                    return true
                }
            }
            return false
        }
    },
    mutations: {
        setSelectedGroup(state, payload) {
            state.selectedGroup = payload
            sessionStorage.setItem('selectedGroup', JSON.stringify(payload))
        },
        setRefreshToken(state, payload) {
            state.refreshToken = payload
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
        },
        setPersonArray(state, payload){
            state.personArray = payload
        }
    },
    actions: {
        updateSelectedGroup({commit}, items) {
            commit('setSelectedGroup', items)
        },
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
        updateChuckList({commit}) {
            api.get(`diaries/group/${this.state.selectedGroup.id}`, {
                headers: {
                    token: sessionStorage.getItem('token')
                },
            }).then(({ data }) => {
                data.forEach(element => {
                    const image = element.image.split(';');
                    element.image = image;
                });
                commit('setChuckList', data);
            })
        },
        logout({commit}) {
            api.post(`users/logout`, {
                refreshToken: sessionStorage.getItem('refreshToken')
            }, {
                headers: {
                    token: sessionStorage.getItem('token')
                },
            }).then(() => {
                commit('deleteUser');
                sessionStorage.clear();
            })
        }
    }
})