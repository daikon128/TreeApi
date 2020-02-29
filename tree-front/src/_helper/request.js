import {account} from "../user/users"
import {authHeader} from "../security/auth-header";

function treeFetch(url, option) {
    const requestBase = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    }

    var authMergedRequest = Object.assign(requestBase, {headers: authHeader()})
    var sendRequest = Object.assign(authMergedRequest, option)

    // return fetch(`${config.apiUrl}/api/authenticate/`, request)
    return fetch(`http://localhost:8888${url}`, sendRequest)
}

export {
    treeFetch
}
