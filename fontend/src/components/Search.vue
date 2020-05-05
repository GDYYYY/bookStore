<template>
    <div class="homepage">

        <div style="padding-left: 50px">
            <el-row v-for="(page,index) of pages" :key="index">
                <el-col :span="7" v-for="(item,innerindex) of page" :key="item" :offset="innerindex">
                <HomeCard  :id="item" ></HomeCard>
            </el-col>
        </el-row></div>
        <br>
    </div>
</template>

<script  >
    import HomeCard from "@/components/HomeCard";
    import axios from "axios";
    export default {
        name:'search',
        components: {HomeCard},
        // props:["target"],
        data () {
            return {
                "List":[],
                "ShowList":[],
                "target":' ',
            }
        },
        computed: {
            pages() {
                const pages = []
                this.ShowList.forEach((item, index) => {
                    const page = Math.floor(index / 3)  //2代表2条为一行
                    if (!pages[page]) {
                        pages[page] = []
                    }
                    pages[page].push(item)
                })
                return pages
            },
        },
        watch:{
            List(data){
                console.log("list:");
                console.log(data);
            },
            // target(item){
            //     if(this.target==null||this.target=='') return;
            //     this.ShowList=[];
            //     this.ShowList.push(item);
            // },
            ShowList(data){
                console.log("show:");
                console.log(data);
                this.pages();
            }
        },
        created(){
            const  _this = this;
            // console.log(1);
            _this.target=sessionStorage.getItem("target");
            // console.log("t0:");
            console.log(_this.target);
            axios.get('http://localhost:8080/getTargets?target='+_this.target.toString()).then(function (resp) {
                console.log(resp.data);
                resp.data.forEach((item)=>{
                    _this.ShowList.push([item.b_id]);
                })
            });
            // console.log(_this.List);
            // _this.ShowList=_this.List;
            // console.log(_this.ShowList);
            // _this.ShowList=[];
            // _this.ShowList.push(_this.target);
        },
    }
</script>

<style>
    @import "../assets/css2.css";
    @import "../assets/css1.css";

</style>