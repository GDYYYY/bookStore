<template>
    <div>
        <div style="float: left">
            <el-image style="width: 370px; height: 520px"
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
        <div><p style="float: right; color: #409EFF">￥{{price}}</p></div>
        <div style="float: bottom;padding-top: 60px">
            <el-input-number v-model="num" controls-position="right" size="mini" min="1"></el-input-number>

            <el-button id="add_book" type="text" class="button" icon="el-icon-shopping-cart-2" @click="addCart()">加入购物车
            </el-button>
            <el-button id="buy_book" type="text" class="button" icon="el-icon-goods" @click="addOrders()">立即购买
            </el-button>
        </div>
        <!--        <el-input-number v-model="num" controls-position="right" size="mini" ></el-input-number>-->
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "Details",
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
                //id:3,
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
                alert("购买成功！");
                axios.post('http://localhost:8080/addOrders?u_id=' + _this.u_id + '&num='+_this.num + '&b_id=' + _this.b_id).then();
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
                _this.url = resp.data.image;
                _this.price = resp.data.price;
                _this.details = resp.data.description;
            })
        },
    }
</script>

<style scoped>

</style>