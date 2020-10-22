import Vue from 'vue'
import Router from 'vue-router'
import landing from '@/pages/landing.vue'
import login from '@/pages/login.vue'
import group from '@/pages/group.vue'
import diary from '@/pages/diary.vue'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'landing',
            component: landing
        },
        {
            path: '/login',
            name: 'login',
            component: login
        },
        
        {
            path: '/group',
            name: 'group',
            component: group
        },
        {
            path: '/diary',
            name: 'diary',
            component: diary
        },
    ]
})