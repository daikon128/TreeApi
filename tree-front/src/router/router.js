import Vue from 'vue';
import Router from 'vue-router';

import Login from '@/pages/Login'
import Trees from '@/pages/Trees'
import Registration from "@/pages/Registration";

Vue.use(Router);

export const router = new Router({
    routes: [
        { path: '/', component: Login },
        { path: '/registration', component: Registration },
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