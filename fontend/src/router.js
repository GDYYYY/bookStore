import Vue from "vue";
import VueRouter from "vue-router";
// import VueTableDynamic from 'vue-table-dynamic'
import ElementUI from "element-ui";
import 'element-ui/lib/theme-chalk/index.css';
// 引入组件
import home from "./components/Home.vue";
import cart from "./components/Cart.vue";
import order from "./components/Order.vue";
import login from "./components/Login.vue";
import signup from "./components/SignUp.vue";
import administer from "./components/Administer.vue";
import admUser from "./components/AdmUser.vue";
import admOrder from "./components/AdmOrder.vue";
import details from "./components/Details.vue";
import all from "./components/All.vue";
import search from "./components/Search.vue";

// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter);
// Vue.use(VueTableDynamic);
Vue.use(ElementUI);
const routes = [

    {
        path:"/home",
        component: home,
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
    {
        path: "/admUser",
        component:admUser,
    },
    {
        path: "/admOrder",
        component:admOrder,
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