<template>
    <div id="loglist">
        <div v-if="!isLog">
            username: <input type="text" class="qxs-ic_user qxs-icon" v-model="user.username">
            <br><br>
            password: <input type="password" class="qxs-ic_password qxs-icon" v-model="user.password"></div>
        <div v-if="isLog">
            <el-image :src="icon" style="border-radius: 50%;height: 160px;width: 160px"></el-image>
<!--            <ImgUp :type="1" style="margin-left: 20px"></ImgUp>-->
            <p>{{username}}</p>
        </div>
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
    // import ImgUp from "@/components/ImgUp";

    export default {
        name: "Login",
        // components: {ImgUp},
        props: ["isLog"],
        data() {
            return {
                user: {
                    "username": "",
                    "password": "",
                    "msg": "sign in",
                    "icon":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3987282417,3624059467&fm=26&gp=0.jpg",
                    "role": "0",
                    // "userIcon": {icon: null},
                    // "email":"",
                }
            }
        },
        methods: {
            judge() {
                if (!this.isLog) {
                    const _this = this;
                    if (_this.user.username == "") alert("请输入用户名！");
                    else if (_this.user.password == "") alert("请输入密码！");
                    else {
                        axios.post('http://localhost:8080/login', _this.user).then(function (resp) {
                            console.log(resp);
                            if (resp.data != "" && resp.data.role != -1) {
                                alert("登录成功");
                                _this.user.u_id=resp.data.u_id;
                                sessionStorage.setItem('u_id', resp.data.u_id);
                                sessionStorage.setItem('username', resp.data.username);
                                // sessionStorage.setItem('email', resp.data.email);
                                if (resp.data.userIcon)
                                    sessionStorage.setItem('icon', resp.data.userIcon.icon);
                                else {
                                    console.log(_this.user.icon);
                                    sessionStorage.setItem('icon', _this.user.icon);
                                }
                                sessionStorage.setItem('role', resp.data.role);
                                console.log(sessionStorage.getItem("role"));
                                location.href = "http://localhost:3000/home";
                            } else if (resp.data != "" && resp.data.role == -1)
                                alert("您的账户已经被禁用");
                            else
                                alert("登录失败！")
                        }).catch(error=>console.log(error));
                    }
                } else {
                    sessionStorage.removeItem('u_id');
                    sessionStorage.removeItem('username');
                    sessionStorage.removeItem('icon');
                    sessionStorage.setItem('role', 0);
                    location.href = "http://localhost:3000/home";
                }
            },
            // handleSave() {
            //     // const _this=this;
            //     var img = sessionStorage.getItem("newImg");
            //     console.log(img);
            //     if (img != null)
            //         user.userIcon.icon = img;
            //     console.log(index);
            //     axios.post('http://localhost:8080/updateUser', user).then(function (resp) {
            //         console.log(resp);
            //         sessionStorage.removeItem("newImg");
            //     });
            //     console.log(index, row)
            // },
        },
        created() {
            const _this = this;
            _this.u_id = sessionStorage.getItem("u_id");
            if (_this.u_id != null) _this.isLog = true;
            if (_this.isLog) {
                this.msg = "log out";
                _this.username = sessionStorage.getItem("username");
                _this.icon = sessionStorage.getItem("icon");
            } else {
                this.msg = "sign in";
            }
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