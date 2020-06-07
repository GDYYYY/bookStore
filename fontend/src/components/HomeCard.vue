<template>
    <el-card shadow="hover" style="margin: 5px;height: auto;width: 231.5px">
<!--        'http://localhost:3000/details/id'+this.id.toString()+'/'-->
        <img id="cover" :src="url" class="image" @click="loc1()">
        <div id="info" >
<!--            <p>{{'http://localhost:3000/details/'+id}}</p>-->
            <p id="book_name">{{title}}</p>
            <p id="author_name">{{author}}</p>
            <p id="isbn_c" v-if="!isHome">ISBN:{{isbn}}</p>
            <p id="stock" v-if="!isHome">stock:{{stock}}</p>
            <div class="bottom_clearfix">
                <p id="price">￥{{price}}</p>
                <el-button id="add_book" type="text" class="button" icon="el-icon-shopping-cart-2" @click="addCart()"></el-button>
            </div>
        </div>
    </el-card>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "HomeCard",
        props:["id","isHome"],
        data() {
            return {
                // fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
                url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                title: "name",
                author: "author",
                price: "price",
                tar:"http://localhost:3000/details/id",
                "u_id":"",
                "b_id":"",
                num:"",
                isbn:'',
                stock:''
                //id:this.$route.params.id,
            }
        },
        // mounted() {
        //     this.id = this.id1;
        // },
        methods:{
            loc1(){
                // console.log(this.tar);
                this.tar='http://localhost:3000/details/'+this.id.toString();
                console.log(this.tar);
                location.href=this.tar;
            },
            addCart(){
                const _this = this;
                alert("添加成功！");
                axios.post('http://localhost:8080/addCart?u_id='+_this.u_id+'&b_id='+_this.b_id+'&num=1').then(function (resp){
                    _this.num=resp.data.num;
                })
            }
        },
        created() {
            const _this = this;
            _this.u_id=sessionStorage.getItem("u_id");
            _this.b_id=_this.id;
            axios.get('http://localhost:8080/getBook?b_id=' + _this.id.toString()).then(function (resp) {
                console.log(resp.data);
                _this.author = resp.data.author;
                _this.title = resp.data.name;
                if(resp.data.bookInfo)
                _this.url = resp.data.bookInfo.image;
                _this.price = resp.data.price;
                _this.isbn=resp.data.isbn;
                _this.stock=resp.data.stock;
            })
        },
    }
</script>
<style scoped>

</style>