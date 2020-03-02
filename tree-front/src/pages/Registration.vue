<template>
    <div>
        <h2>Registration</h2>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" v-model="username" name="username" class="form-control" :class="{ 'is-invalid': submitted && !username }" />
                <div v-show="submitted && !username" class="invalid-feedback">Username is required</div>
            </div>
            <div class="form-group">
                <label htmlFor="password">Password</label>
                <input type="password" v-model="password" name="password" class="form-control" :class="{ 'is-invalid': submitted && !password }" />
                <div v-show="submitted && !password" class="invalid-feedback">Password is required</div>
            </div>
            <div class="form-group">
                <button class="btn btn-primary" @click="send()">Registration</button>
            </div>
    </div>
</template>
<script>
    export default {
        name: "Registration",
        data () {
            return {
                username: '',
                password: '',
                submitted: false
            }
        },
        methods: {
            send() {
                this.submitted = true
                const params  = {
                    username: this.username,
                    password: this.password
                }
                const requestOptions = {
                    method: 'POST',
                    body: JSON.stringify(params)
                };
                fetch(`http://localhost:8888/registration`, requestOptions)
                    .then(() => this.$router.push("/login"))
                    .catch(e => {
                        console.log(e)
                        this.submitted = false
                    })
            }
        }
    };
</script>

<style scoped>

</style>