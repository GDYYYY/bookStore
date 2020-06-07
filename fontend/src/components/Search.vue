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
                "ShowList":[],
                "target":null,
            }
        },
        computed: {
            pages() {
                const pages = [];
                this.ShowList.forEach((item, index) => {
                    const page = Math.floor(index / 3) ; //3代表3条为一行
                    if (!pages[page]) {
                        pages[page] = []
                    }
                    pages[page].push(item)
                });
                return pages
            },
        },
        watch:{
            ShowList(data){
                console.log("show:");
                console.log(data);
                this.pages();
            }
        },
        created(){
            const  _this = this;
            // console.log(1);
            var t=sessionStorage.getItem("target");
            if(t!=null)
            _this.target=t;
            console.log(_this.target);
            if(_this.target!=null)
            axios.get('http://localhost:8080/getTargets?target='+_this.target.toString()).then(function (resp) {
                console.log(resp.data);
                resp.data.forEach((item)=>{
                    if(item.onshelf==1)
                    _this.ShowList.push([item.b_id]);
                })
            });
            else
            axios.get('http://localhost:8080/getBooks').then(function (resp) {
                console.log(resp.data);
                resp.data.forEach((item)=>{
                    if(item.onshelf==1)
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