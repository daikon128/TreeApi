export function authHeader() {
    // FIXME: localStorage依存の方法は辞めたい
    // Cookiの方が良いのかな...
    let user = JSON.parse(localStorage.getItem('user'));

    if (user) {
        return { 'Authorization': 'Bearer ' + user.token};
    } else {
        return {};
    }
}