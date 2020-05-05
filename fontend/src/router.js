import Vue from "vue";
import VueRouter from "vue-router";
import VueTableDynamic from 'vue-table-dynamic'
import ElementUI from "element-ui";
import 'element-ui/lib/theme-chalk/index.css';
// 引入组件
import home from "./components/Home.vue";
import cart from "./components/Cart.vue";
import user from "./components/User.vue";
import order from "./components/Order.vue";
import login from "./components/Login.vue";
import signup from "./components/SignUp.vue";
import administer from "./components/Administer.vue";
import details from "./components/Details.vue";
import all from "./components/All.vue";
import search from "./components/Search.vue";

// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter);
Vue.use(VueTableDynamic);
Vue.use(ElementUI);
const routes = [

    {
        path:"/home",
        // 下面这个属性也不少，因为，我们是先进入home页面，才能进入子路由
        component: home,
        // 子路由
    },
    {
        path: "/cart",
        component: cart,
    },
    {
        path: "/all",
        component: all,
    },
    {
        path: "/search",
        component: search,
    },
    {
        path: "/details/:id",
        component: details,
    },
    {
        path: "/signup",
        component: signup,
    },
    // {
    //     path: "/navmenu",
    //     component: navmenu,
    // },
    // {
    //     path: "/vuebutton",
    //     component: vuebutton,
    // },

    {
        path: "/login",
        component: login,
    },
    {
        path:"/order",
        component:order,
    },
    {
        path: "/administer",
        component:administer,
    },
    /*新增user路径，配置了动态的id*/
    {
        path: "/user/:id",
        component: user
    },
    // 重定向
    {
        path: '/',
        redirect: '/home'
    }
]

var router =  new VueRouter({
    routes,
    mode:"history"
})
 export default router;
// export default new Router({
//     mode:"history",
//     routes: []
// })