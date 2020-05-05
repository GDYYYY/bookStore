<template>
    <div>
        <div>
            <el-checkbox-group v-model="checkList" @change="handleCheckedItemsChange">
                <el-row v-for="(page,index) of pages" :key="index">
                    <el-col :span="80" v-for="(item,innerindex) of page" :key="item" :offset="innerindex">
                        <div id="bCard">
                            <div style="float: left;">
                                <el-checkbox :label="item" :key="item" style="vertical-align: middle" @change="spend()">
                                    <div @click="recheck()">
                                        <BookCard :id="item"></BookCard>
                                    </div>
                                </el-checkbox>
                                <!--                            {{ }}用于使label不显示-->
                            </div>
                            <!--                        <div>-->
                            <!--                    <BookCard :id="item"></BookCard></div>-->
                        </div>
                    </el-col>
                </el-row>
            </el-checkbox-group>
        </div>
        <div id="purchase" style="float: right">
            <p id="price" style="padding-right: 30px;margin-top: 10px">共计：￥{{total}}</p>
            <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange"
                         style="padding: 10px">全选
            </el-checkbox>
            <el-button type="plain" icon="el-icon-goods" @click="buyBook">购买</el-button>
        </div>
    </div>
</template>

<script>
    import BookCard from "@/components/BookCard";
    import axios from 'axios';

    export default {
        components: {BookCard},
        //props:["total"],
        data() {
            return {
                "items": [],
                "total": "0",
                "checkList": [],
                "u_id": 0,
                "flag": true,//用于判断点击的是checkbox还是
                "num": '',
                "numList": [],
                flag1: false,
                f: false,
                precheckList: [],
                checkAll: false,
                isIndeterminate: true
            }
        },
        computed: {
            pages() {
                const pages = []
                this.items.forEach((item, index) => {
                    const page = Math.floor(index / 2)  //2代表2条为一行
                    if (!pages[page]) {
                        pages[page] = []
                    }
                    pages[page].push(item)
                })
                return pages
            },
        },
        watch: {
            checkList(val) {
                console.log(val);
            },
            // numList(val) {
            //     console.log("num:"+val);
            // },
        },
        methods: {
            buyBook() {
                var c_id;
                var num = 1;
                const _this = this;
                alert("购买成功！");
                _this.checkList.forEach((item) => {
                    axios.get('http://localhost:8080/getNum?u_id=' + _this.u_id.toString() + '&b_id=' + item).then(function (resp) {
                        c_id = resp.data.id;
                        num = resp.data.num;
                        axios.post('http://localhost:8080/addOrders?u_id=' + _this.u_id + '&num=' + num + '&b_id=' + item).then();
                        axios.post('http://localhost:8080/delCart?id=' + c_id.toString()).then();//delete
                    });
                })
            },
            recheck() {
                const _this = this;
                _this.flag = false;
                _this.checkList = _this.precheckList;
            },
            spend() {
                var ans = 0;
                var num = 1;
                const _this = this;
                _this.total = ans;
                if (_this.flag) {
                    _this.precheckList = _this.checkList;
                } else {
                    _this.checkList = _this.precheckList;
                    _this.flag = true;
                }
                // _this.checkList.forEach((item) => {
                //     axios.get('http://localhost:8080/getNum?u_id=' + _this.u_id.toString() + '&b_id=' + item.toString()).then(async function (resp) {
                //         num = resp.data.num;
                //         _this.numList.push({b_id: item, num: num});
                //         console.log(resp.data.b_id + '+' + resp.data.num);//异步,axios是并行的
                //         console.log("numlist add");
                //         await axios.get('http://localhost:8080/getBook?b_id=' + item.toString()).then(function (resp) {
                //             console.log(resp.data);
                //             console.log(num);
                //             ans = (ans + resp.data.price * num)//.toFixed(2);//还要乘数量！
                //             console.log(ans);
                //             _this.total = ans.toFixed(2);
                //         })
                //
                //     });
                // });
                _this.checkList.forEach((item) => {
                    axios.get('http://localhost:8080/getPrice?u_id=' + _this.u_id.toString() + '&b_id=' + item.toString()).then(function (resp) {
                        num = resp.data;
                            ans = (ans + num);//.toFixed(2);//还要乘数量！
                            console.log(ans);
                            _this.total = ans.toFixed(2);
                        })
                    });
            },
            handleCheckAllChange(val) {
                const _this = this;
                _this.checkList = val ? _this.items : [];
                _this.isIndeterminate = false;
                // _this.$options.methods.spend(_this);
                var ans = 0;
                var num = 1;
                _this.total = ans;
                _this.checkList.forEach((item) => {
                    axios.get('http://localhost:8080/getPrice?u_id=' + _this.u_id.toString() + '&b_id=' + item.toString()).then(function (resp) {
                        num = resp.data;
                        ans = (ans + num);//.toFixed(2);//还要乘数量！
                        console.log(ans);
                        _this.total = ans.toFixed(2);
                    })
                });
            },
            handleCheckedItemsChange(value) {
                let checkedCount = value.length;
                this.checkAll = checkedCount === this.items.length;
                this.isIndeterminate = checkedCount > 0 && checkedCount < this.items.length;
            },

        },
        created() {
            const _this = this;
            _this.u_id = sessionStorage.getItem("u_id");
            console.log(_this.u_id);
            axios.get('http://localhost:8080/getCart?u_id=' + _this.u_id.toString()).then(function (resp) {
                console.log(resp.data);
                resp.data.forEach((want) => {
                    _this.items.push(want.b_id);
                })
            })
        }
    }
</script>
<style>
    @import "../assets/css2.css";
    @import "../assets/css1.css";
</style>