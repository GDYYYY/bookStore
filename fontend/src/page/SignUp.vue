<template>
    <div id="loglist">
        <br><br>
        *username: <input type="text" class="qxs-ic_user qxs-icon" v-model="user.username">
        <br><br>
        *password: <input type="password" class="qxs-ic_password qxs-icon" v-model="user.password">
        <!--<button class="login_btn el-button el-button&#45;&#45;primary is-round" type="primary" round>登录</button>-->
        <br><br>
        *confirm password: <input type="password" class="qxs-ic_password qxs-icon" v-model="user.confirmPassword">
        <br><br>
        *E-mail: <input type="text" v-model="user.email">
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
                    "confirmPassword": "",
                    "u_id": 0,
                    "role": 0,
                    "icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591200548265&di=c65d5d7023bb5aed326810d3b42f8617&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201704%2F27%2F20170427155254_Kctx8.jpeg",
                    "email": "",
                }
            }
        },
        methods: {
            checkEmail(){
                var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
                if(myReg.test(this.user.email))
                    return true;
                else
                    return false;
            },
            signup() {
                const _this = this;
                if (_this.user.username == "") alert("请输入用户名！");
                else if (_this.user.password == "") alert("请输入密码！");
                else if (_this.user.confirmPassword == "") alert("请确认密码！");
                else if (_this.user.email == "") alert("请输入邮箱！");
                else if (!_this.checkEmail()) alert("请输入邮箱正确格式！");
                else if (_this.user.password != _this.user.confirmPassword) {
                    alert("两次输入的密码不一致！");
                } else {
                    axios.post('http://localhost:8080/signup', _this.user).then(function (resp) {
                        if (resp.data) {
                            alert("注册成功！");
                            console.log(resp.data);
                            sessionStorage.setItem('u_id', resp.data.u_id);
                            sessionStorage.setItem('username', resp.data.username);
                            if(resp.data.userIcon)
                                sessionStorage.setItem('icon', resp.data.userIcon.icon);
                            else {
                                // console.log(_this.user.icon);
                                sessionStorage.setItem('icon',_this.user.icon);}
                            sessionStorage.setItem('role', resp.data.role);
                            location.href = "http://localhost:3000/home";
                        } else alert("用户名已存在！");
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