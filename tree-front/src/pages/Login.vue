<template>
    <div>
        <h2>Login</h2>
        <form @submit.prevent="handleSubmit">
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
            <div>
                <button :disabled="status.loggingIn">Login</button>
                <router-link to="/register" >Register</router-link>
            </div>
        </form>
    </div>
</template>

<script>
    import { mapState, mapActions } from 'vuex'
    import { ValidationProvider } from 'vee-validate/dist/vee-validate.full'

    export default {
        data () {
            return {
                username: '',
                password: '',
                submitted: false
            }
        },
        components: {
            ValidationProvider
        },
        computed: {
            ...mapState('account', ['status'])
        },
        methods: {
            ...mapActions('account', ['login']),
            handleSubmit (e) {
                this.submitted = true;
                const { username, password } = this;
                if (username && password) {
                    this.login({ username, password })
                }
            }
        }
    }
</script>