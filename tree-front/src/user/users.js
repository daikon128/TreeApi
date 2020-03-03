// import config from 'config'
import { router } from '../router/router'

const user = JSON.parse(localStorage.getItem('user'))
const state = user ? { status: { loggedIn: true }, user} : { status: {}, user: null}

function login(username, password) {
    const request = {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password})
    }

    // return fetch(`${config.apiUrl}/api/authenticate/`, request)
    return fetch(`http://localhost:8888/api/authenticate`, request)
        .then(handleResponse)
        .then(user => {
            console.log("fetch login")
            if (user) {
                localStorage.setItem('user', JSON.stringify(user))
                console.log("login")
            }

            return user
        })
}

const actions = {
    login({ dispatch, commit}, { username, password }){
        console.log("login start")
        commit('loginRequest', { username })
        login(username, password)
            .then(
                user => {
                    commit('loginSuccess', user)
                    router.push("/trees", () => {}, () => {})
                },
                error => {
                    console.log(error)
                    console.log("====")
                    console.log(dispatch)
                    commit('loginFailure', error)
                    dispatch('alert/error', error, { root: true})
                }
            )
        console.log("login finish")
        }
}

const mutations = {
    loginRequest(state, user) {
        state.status = { loggingIn: true}
        state.user = user
    },
    loginSuccess(state, user) {
        console.log(user)
        state.status = { loggingIn: true}
        state.user = user
    },
    loginFailure(state) {
        state.status = {}
        state.user = null
    }
}

function handleResponse(response) {
    return response.text().then(text => {
        if (!response.ok) {
            if (response.status == 401) {
                return
            }
            const error = (text && text.message) || response.statusText
            return Promise.reject(error)
        }
        var auth = response.headers.get("Authorization")
        if (auth == undefined || auth == "") {
            return Promise.reject("auth failure")
        }
        return JSON.parse(text)
    })
}

export const account = {
    namespaced: true,
    state,
    actions,
    mutations
}

