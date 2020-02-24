export function authHeader() {
    // FIXME: localStorage依存の方法は辞めたい
    let user = JSON.parse(localStorage.getItem('user'));

    if (user) {
        return { 'Authorization': 'Bearer ' + user};
    } else {
        return {};
    }
}