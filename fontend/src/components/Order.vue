<template>
<!--        <h1>我和Cart一样是被暂时抛弃的界面</h1>-->
<!--        <el-row v-for="j in 3" :key="j">-->
<!--            <el-col :span="7" v-for="i in 3" :key="i">-->
<!--                <BookCard :id="3*(j-1)+i"></BookCard>-->
<!--            </el-col>-->
<!--&lt;!&ndash;            <BookCard :id="j"></BookCard>&ndash;&gt;-->
<!--        </el-row>-->
        <div >
        <el-row v-for="(page,index) of pages" :key="index">
            <el-col :span="11" v-for="(item,innerindex) of page" :key="item" :offset="innerindex" >
                <div id="bCard">
<!--                    <div style="float: left">-->
<!--                        <el-checkbox ></el-checkbox>-->
<!--                    </div>-->
                    <BookCard :id="item" :disable="true"></BookCard>
                </div>
            </el-col>
        </el-row>

    </div>

</template>

<script>
    //import axios from 'axios';
    import BookCard from "@/components/BookCard.vue";
    import axios from "axios";

    export default {
        name: "Order",
        components: {BookCard},
        data() {
            return{//我严重怀疑IDEA是智障，为什么{换个行就有bug
                 //items:[{id: 1}, {id: 2}, {id: 3}, {id: 4}, {id: 5}, {id: 6}, {id: 7}, {id: 8}, {id: 9}]
                "items":[],
                "u_id":"0",
                // checkList:[3],
                // checkAll: false,
                // isIndeterminate: true
            }
        },
        computed: {
            pages () {
                const pages = []
                this.items.forEach((item, index) => {
                    const page = Math.floor(index / 2) //2代表2条为一行
                    if (!pages[page]) {
                        pages[page] = []
                    }
                    pages[page].push(item)
                })
                return pages
            }
        },
        created(){
            const _this=this;
            _this.u_id=sessionStorage.getItem("u_id");
            console.log(_this.u_id);
            axios.get('http://localhost:8080/getOrders?u_id=' + _this.u_id.toString()).then(function (resp) {
                console.log(resp.data);
                resp.data.forEach((belong)=>{
                    _this.items.push(belong.b_id);
                })
            })
        }
    }
</script>

<style scoped>

</style>