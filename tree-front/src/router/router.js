import Vue from 'vue';
import Router from 'vue-router';

import Login from '@/pages/Login'
import Trees from '@/pages/Trees'

Vue.use(Router);

export const router = new Router({
    mode: 'history',
    routes: [
        { path: '/', component: Login },
        { path: '/trees', component: Trees },

        // otherwise redirect to home
        { path: '*', redirect: '/' }
    ]
});

router.beforeEach((to, from, next) => {
    // redirect to login page if not logged in and trying to access a restricted page
    const publicPages = ['/login', '/'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
        return next('/login');
    }

    next();
})