export function authHeader() {
    // FIXME: localStorage依存の方法は辞めたい
    let user = JSON.parse(localStorage.getItem('user'));
    // let user = this.$store.state.account.user

    if (user) {
        return { 'Authorization': 'Bearer ' + user.token};
    } else {
        return {};
    }
}