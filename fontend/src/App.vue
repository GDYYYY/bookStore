<template>
    <div id="app">
        <div id="header">
            <!--    <img alt="Vue logo" src="./assets/logo.png">-->
            <div id="logo"><img src="./assets/logo.png" alt="logo"></div>
            <div id="search">
                <!--                <form action="./unknown.asp" method="GET">-->
                <!--                    search <input type="text" name="target_book" size="35">-->
                <!--                    <el-button icon="el-icon-search" type="text"></el-button>-->
                <!--                </form>-->
                <router-link to="/search">search<input type="text" name="target_book" v-model="target"
                                                       @change="loc2()"
                                                       value={$target} size="35">
                    <el-button icon="el-icon-search" type="text"></el-button>
                </router-link>
            </div>
            <div id="log">
                <router-link to="/login" :isLog="isLog">{{message}}</router-link>
            </div>
        </div>
        <div>
            <div id="meun">
                <!-- router-link 定义点击后导航到哪个路径下 -->
                <router-link to="/home">Home</router-link>
                <br/>
                <router-link to="/cart">Cart</router-link>
                <br/>
                <router-link to="/order">Order</router-link>
                <br/>
                <router-link to="/all">All</router-link>
                <br/>
                <router-link to="/administer" v-if="isAdm>0">Administer</router-link>
                <br/>
                <!--  增加两个到user组件的导航，可以看到这里使用了不同的to属性 -->
                <!--           <router-link to="/user/123">User123</router-link>-->
                <!--           <br/>-->
                <!--           <router-link to="/user/456">User456</router-link>-->
            </div>
            <div id="view">
                <router-view v-if="isRouterAlive"></router-view>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'App',
        data() {
            return {
                "message": "login",
                "isLog": false,
                "isAdm": false,
                "target": "",
                isRouterAlive :true,
                tar: ''
            }
        },
        provide(){
            return {
                reload: this.reload
            }
        },
        created() {
            const _this = this;
            _this.message = sessionStorage.getItem("username");
            _this.target = sessionStorage.getItem("target");
            if (_this.message == null) _this.message = "login";
            else {
                _this.isLog = true;
                _this.isAdm = sessionStorage.getItem("role");
            }
            console.log(_this.message + " log in role:"+_this.isAdm);
        },
        watch: {
            target(data) {
                console.log(data);
                sessionStorage.setItem('target', this.target);
            },
            isAdm(data){
                console.log("role:"+data);
            }
        },
        methods: {
            loc2() {
                sessionStorage.setItem('target', this.target);
                this.tar = 'http://localhost:3000/search';
                console.log(this.tar);
                location.href = this.tar;
            },
            reload() {
                this.isRouterAlive = false
                this.$nextTick(function () {
                    this.isRouterAlive = true
                })
            }
        }
    }
</script>

<style>
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        width: 100%;
        height: 900px;
        /*margin-top: 60px;*/
    }

    div#view {
        float: left;
        margin: 20px;
        width: 70%;
        text-align: center;
    }

    @import "./assets/css2.css";
    @import "./assets/css1.css";

    a {
        text-decoration: none;
        color: #769FCD;
    }

    .el-input__inner {
        padding: 0;
    }
</style>
