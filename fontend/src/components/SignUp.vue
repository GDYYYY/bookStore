<template>
    <div id="loglist">
        username: <input type="text" class="qxs-ic_user qxs-icon" v-model="user.username">
        <br><br>
        password: <input type="text" class="qxs-ic_password qxs-icon" v-model="user.password">
        <!--<button class="login_btn el-button el-button&#45;&#45;primary is-round" type="primary" round>登录</button>-->
        <br><br>
        confirm password: <input type="text" class="qxs-ic_password qxs-icon" v-model="user.confirmPassword">
        <br><br>
        E-mail: <input type="text" class="qxs-ic_password qxs-icon" v-model="user.email">
        <br><br>
        <el-button class="login_btn" @click="signup()" :loading="isBtnLoading" size="mini">sign up</el-button>
        <el-button class="login_btn" :loading="isBtnLoading" size="mini">
            <router-link to="/login"> sign in</router-link>
        </el-button>
    </div>
</template>

<script>
    import axios from "axios";
    export default {
        name: "Login",
        data() {
            return {
                user: {
                    "username": "",
                    "password": "",
                    "confirmPassword":"",
                    "u_id":0,
                    "role":0,
                    "email":""
                }
            }
        },
        methods:{
            signup(){
            const _this = this;
            if(_this.password!=_this.confirmPassword){
                alert("两次输入的密码不一致！");
            }
            else{
                axios.post('http://localhost:8080/signup',_this.user).then(function (resp){
                    alert("注册成功！");
                    console.log(resp.data);
                    // sessionStorage.setItem('u_id', resp.data.u_id);
                })
            }
        }
        }
    }
</script>

<style scoped>
    @import "../assets/css2.css";
    @import "../assets/css1.css";

    a {
        color: #606266;
    }
</style>