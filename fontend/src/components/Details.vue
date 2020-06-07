<template>
    <div>
        <div style="float: left">
            <el-image style="width: 370px; height: 520px;padding-right: 20px"
                      fit="cover"
                      :src="url">
            </el-image>
        </div>
        <div id="detail_block">
            <!--            <h1>{{$route.params.id}}</h1>-->
            <h1 id="book_name1">{{title}}</h1>
            <p id="author_name">{{author}}</p>
            <p id="details">{{details}}</p>
        </div>
        <div><p id="isbn">ISBN:{{isbn}}</p></div>
        <div style="margin-top: 30px"><p style="float: right; color: #409EFF">￥{{price}}</p></div>
        <div><p style="color: #B9D7EA;float:left;padding-left: 15%">库存:{{stock}}</p></div>
        <div style="float: bottom;padding-top: 60px">
            <el-input-number v-model="num" controls-position="right" size="mini" :min="1" :max="stock" :disabled="stock<=0"></el-input-number>

            <el-button id="add_book" type="text" class="button" icon="el-icon-shopping-cart-2" :disabled="stock<=0" @click="addCart()">加入购物车
            </el-button>
            <el-button id="buy_book" type="text" class="button" icon="el-icon-goods" :disabled="stock<=0" @click="addOrders()">立即购买
            </el-button>
        </div>
        <!--        <el-input-number v-model="num" controls-position="right" size="mini" ></el-input-number>-->
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "Details",
        inject: ["reload"],
        data() {
            return {
                // fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
                "url": 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                "title": "name",
                "author": "author",
                "details": "description",
                "price": "price",
                "num": "1",
                "u_id": "0",
                "b_id": "0",
                "stock":"",
                "isbn":"",
                "id": this.$route.params.id,
            }
        },
        methods: {
            addCart() {
                const _this = this;
                alert("添加成功！");
                axios.post('http://localhost:8080/addCart?u_id=' + _this.u_id + '&b_id=' + _this.b_id + '&num=' + _this.num).then(function (resp) {
                    _this.num = resp.data.num;
                })
            },
            addOrders() {
                const _this = this;
                var x=_this.num*_this.price;
                alert("购买成功！");
                axios.get('http://localhost:8080/newForm?u_id='+ _this.u_id.toString()+'&cost='+x.toString()).then(function (resp) {
                    var f_id=resp.data.f_id;
                axios.post('http://localhost:8080/addOrders?f_id=' + f_id + '&num='+_this.num + '&b_id=' + _this.b_id).then(function () {
                    _this.reload();
                }
                );})
            },
        },
        created() {
            const _this = this;
            _this.u_id = sessionStorage.getItem("u_id");
            _this.b_id = _this.id;
            axios.get('http://localhost:8080/getBook?b_id=' + _this.id.toString()).then(function (resp) {
                console.log(resp.data);
                _this.author = resp.data.author;
                _this.title = resp.data.name;
                _this.url = resp.data.bookInfo.image;
                _this.isbn = resp.data.isbn;
                _this.price = resp.data.price;
                _this.details = resp.data.description;
                _this.stock=resp.data.stock;
            })
        },
    }
</script>

<style scoped>

</style>