<template>
    <div style="height: 170px">
        <!--        id="bCard"-->
        <!--        <router-link to="/details/1">User123</router-link>-->
        <!--        <div style="float: left">-->
        <!--            <el-checkbox ></el-checkbox>-->
        <!--        </div>-->
        <div style="float: left">
            <div style="float: left; padding: 20px;" @click="loc1()">
                <el-image style="width: 120px; height: 120px"
                          fit="cover"
                          :src="url"
                ></el-image>
            </div>
            <div style="float: left; padding-top: 10px;height: 140px;width: 150px;">
                <p id="book_name">{{title}}</p>
                <p id="author_name">{{author}}</p>
                <p id="price" style="margin: 0">￥{{price}}</p>
                <el-input-number v-model="num" controls-position="right" size="mini" min="1" @change="sendNum()"
                                 :disabled="disable" style="width: 80px;padding: 0;float: bottom"></el-input-number>
            </div>
        </div>
        <div style="float: bottom">

        </div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "BookCard",
        props: ["id", "disable"],
        data() {
            return {
                // fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
                "url": 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                "title": "aaaaa",
                "author": "zuzu",
                "price": "36",
                "num": "1",
                "tar": "http://localhost:3000/details/id",
                "u_id":"0",
                "b_id":"0"
            }
        },
        methods: {
            loc1() {
                // console.log(this.tar);
                this.tar = 'http://localhost:3000/details/' + this.id.toString();
                console.log(this.tar);
                location.href = this.tar;
            },
            sendNum(){
                const _this = this;
                axios.post('http://localhost:8080/updateCart?u_id='+_this.u_id+'&b_id='+_this.b_id+'&num='+_this.num).then(function (resp){
                    _this.num=resp.data.num;
                })
            }
        },
        created() {
            const _this = this;
            _this.b_id=_this.id;
            _this.u_id=sessionStorage.getItem("u_id");
            axios.get('http://localhost:8080/getBook?b_id=' + _this.id.toString()).then(function (resp) {
                console.log(resp.data);
                _this.author = resp.data.author;
                _this.title = resp.data.name;
                _this.url = resp.data.image;
                _this.price = resp.data.price;
                // _this.num=
                // _this.details=resp.data.description;
            });
            if(!_this.disable)
            {axios.get('http://localhost:8080/getNum?u_id='+_this.u_id+'&b_id='+_this.id).then(function (resp){
                _this.num=resp.data.num;
                // console.log(resp.data);
                // console.log(_this.num);
            })}
            else{
                axios.get('http://localhost:8080/getONum?u_id='+_this.u_id+'&b_id='+_this.id).then(function (resp){
                    _this.num=resp.data.num;
                    // console.log(resp.data);
                    // console.log(_this.num);
                })
            }

        },
    }
</script>

<style scoped>

</style>