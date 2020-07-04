<template>
    <div class="homepage">

        <div id="roll">
            <el-carousel trigger="click" height="300px">
                <el-carousel-item v-for="item in rolls" :key="item">
                    <img id="rollin" :src="item"/>
                </el-carousel-item>
            </el-carousel>
        </div>

        <div style="padding-left: 50px">
        <el-row v-for="j in 3" :key="j">
            <el-col :span="7" v-for="i in 3" :key="i">
                <HomeCard  :id="3*(j-1)+i" :isHome="true"></HomeCard>
            </el-col>
        </el-row></div>
        <br>
        <router-link to="/all">see more..</router-link>
    </div>
</template>

<script type="text/javascript" >
    import HomeCard from "@/components/HomeCard";
    export default {
        name:'homepage',
        components: {HomeCard},
        data () {
            return {
                rolls:[
                    require("../assets/垫底辣妹.jpg"),
                    require("../assets/独立日2.jpg"),
                    require("../assets/控制.jpg"),
                ]
            }
        }
    }
</script>

<style>
    @import "../assets/css2.css";
    @import "../assets/css1.css";

</style>