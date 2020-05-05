<template>
    <div id="loglist">
        <div v-if="!isLog">
        username: <input type="text" class="qxs-ic_user qxs-icon" v-model="user.username"  >
        <br><br>
        password: <input type="password" class="qxs-ic_password qxs-icon" v-model="user.password" ></div>
        <!--<button class="login_btn el-button el-button&#45;&#45;primary is-round" type="primary" round>登录</button>-->
        <br><br>
        <el-button class="login_btn" :loading="isBtnLoading" size="mini">
            <router-link to="/signup">sign up</router-link>
        </el-button>
        <el-button class="login_btn" @click="judge()" :loading="isBtnLoading" size="mini"> {{msg}}</el-button>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "Login",
        props: ["isLog"],
        data() {
            return {
                user: {
                    "username": "",
                    "password": "",
                    "msg": "sign in",
                    // u_id:0,
                    "role":"0",
                    // email:""
                }
            }
        },
        methods: {
            judge() {
                if(!this.isLog) {const _this = this;
                axios.post('http://localhost:8080/login', _this.user).then(function (resp) {
                    console.log(resp);
                    if (resp.data != "") {
                        alert("登录成功");
                        sessionStorage.setItem('u_id', resp.data.u_id);
                        sessionStorage.setItem('username', resp.data.username);
                        sessionStorage.setItem('role', resp.data.role);
                        // _this.$router.push('/home')
                        console.log(sessionStorage.getItem("role"));
                        location.href = "http://localhost:3000/home";
                    } else
                        alert("登录失败");
                })}
                else{
                    sessionStorage.removeItem('u_id');
                    sessionStorage.removeItem('username');
                    sessionStorage.setItem('role',0);
                    location.href = "http://localhost:3000/home";
                }

            }
        },
        created(){
            const _this = this;
            _this.u_id=sessionStorage.getItem("u_id");
            if(_this.u_id!=null) _this.isLog=true;
            if(_this.isLog){
                this.msg="log out";
            }
            else {this.msg="sign in";}
        }
    }
</script>

<style scoped>
    @import "../assets/css2.css";
    @import "../assets/css1.css";

    a {
        color: #606266
    }
</style>