<template>
    <div>
        <h2>Registration</h2>
        <div>
            <label for="username">Username</label>
            <validation-provider rules="required" v-slot="{ errors }">
                <input type="text"
                       v-model="username"
                       name="username"
                       id="username"
                       :class="{ 'is-invalid': submitted && !username }" />
                <div class="form-control-feedback">
                    <p class="alert alert-danger">{{ errors[0] }}</p>
                </div>
            </validation-provider>
        </div>
        <div>
            <label for="password">Password</label>
            <validation-provider rules="required" v-slot="{ errors }">
                <input type="password"
                       v-model="password"
                       name="password"
                       id="password"
                       :class="{ 'is-invalid': submitted && !password }" />
                <div class="form-control-feedback">
                    <p class="alert alert-danger">{{ errors[0] }}</p>
                </div>
            </validation-provider>
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