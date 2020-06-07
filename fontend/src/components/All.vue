<template>
    <div class="homepage">

        <div style="padding-left: 50px">
            <el-row v-for="(page,index) of pages" :key="index">
                <el-col :span="7" v-for="(item,innerindex) of page" :key="item" :offset="innerindex">
                <HomeCard  :id="item" :isHome="false" ></HomeCard>
            </el-col>
        </el-row></div>
        <br>
<!--        <router-link to="/administer">see more..</router-link>-->
    </div>
</template>

<script  >
    import HomeCard from "@/components/HomeCard";
    import axios from "axios";
    export default {
        name:'all',
        components: {HomeCard},
        // props:["target"],
        data () {
            return {
                "List":[],
                // "ShowList":[1,2],
                // "target":this.$route.params.id,
            }
        },
        computed: {
            pages() {
                const pages = [];
                this.List.forEach((item, index) => {
                    const page = Math.floor(index / 3)  //2代表2条为一行
                    if (!pages[page]) {
                        pages[page] = []
                    }
                    pages[page].push(item)
                });
                return pages
            },
        },
        watch:{
            List(data){
                console.log(data);
            },
        },
        created(){
            const  _this = this;
            console.log(1);
            axios.get('http://localhost:8080/getBooks').then(function (resp) {
                console.log(resp.data);
                resp.data.forEach((item)=>{
                    if(item.onshelf==1)
                    _this.List.push([item.b_id]);
                })
            });
            console.log(_this.List);
        },
    }
</script>

<style>
    @import "../assets/css2.css";
    @import "../assets/css1.css";

</style>