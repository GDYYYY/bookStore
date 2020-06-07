<template>
    <div id="form">
        <div>
            <el-row v-for="(page,index) of pages" :key="index">
                <el-col :span="11" v-for="(item,innerindex) of page" :key="item" :offset="innerindex">
                    <div id="bCard">
                        <BookCard :id="item" :disable="true" :f_id="f_id"></BookCard>
                    </div>
                </el-col>
            </el-row>
        </div>
        <div id="about_form">
            <p>cost:￥{{cost}}</p> <p>time:{{time}}</p>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    import BookCard from "@/components/BookCard";

    export default {
        name: "OrderForm",
        components: {BookCard},
        props: ["f_id","cost","time"],
        data() {
            return {//我严重怀疑IDEA是智障，为什么{换个行就有bug
                //items:[{id: 1}, {id: 2}, {id: 3}, {id: 4}, {id: 5}, {id: 6}, {id: 7}, {id: 8}, {id: 9}]
                "items": [],
                "u_id": "0",
                // "cost": "",
                // "time": "",
            }
        },
        computed: {
            pages() {
                const pages = [];
                this.items.forEach((item, index) => {
                    const page = Math.floor(index / 2);//2代表2条为一行
                    if (!pages[page]) {
                        pages[page] = []
                    }
                    pages[page].push(item)
                });
                return pages
            },
        },
        created() {
            const _this = this;
            console.log(_this.f_id);
            axios.get('http://localhost:8080/getOrders?f_id=' + _this.f_id.toString()).then(function (resp) {
                resp.data.forEach((belong) => {
                    _this.items.push(belong.b_id);
                });
                console.log("ff");
                console.log(_this.items);
            });
            // let t=_this.time;
            // t=t.substr(0,10);//+' '+t.substr(11,8);
            // _this.time=t;
        }
    }
</script>

<style scoped>

</style>