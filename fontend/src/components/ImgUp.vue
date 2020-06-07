<template>
    <div class="com-upload-img">
        <div class="img_group">
            <div class="img_box" v-if="allowAddImg">
                <label for="imgup" v-if="type==1"
                       style="background-color: #FFFFFF;padding: 20px;border-radius: 5px;border: 1px solid">
                    选择图片(jpg/png)</label>
                <label for="imgup" v-else
                       style="margin:10px;background-color: #FFFFFF;padding: 10px;border-radius: 5px;border: 1px solid">
                    更改</label>
                <input id="imgup" style="opacity: 0" type="file" accept="image/*" @change="changeImg($event)">
            </div>
            <div class="img_box" v-for="(item,index) in imgArr" :key="index">
                <div class="img_show_box">
                    <img :src="item" alt="">
                    <el-button class="img_delete" type="text" size="mini" @click="deleteImg(index)">删除</el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'ImgUp',
        props: ["type"],
        data() {
            return {
                imgData: '',
                imgArr: [],
                imgSrc: '',
                allowAddImg: true,
            }
        },
        watch: {
            imgArr(data) {
                console.log('imgArr');
                console.log(data);
            }
        },
        methods: {
            changeImg: function (e) {
                var _this = this;
                var imgLimit = 1024;
                // console.log('111');
                var files = e.target.files;
                var image = new Image();
                if (files.length > 0) {
                    var dd = 0;
                    var timer = setInterval(function () {
                        if (files.item(dd).type != 'image/png' && files.item(dd).type != 'image/jpeg' && files.item(dd).type != 'image/gif') {
                            return false;
                        }

                        if (files.item(dd).size > imgLimit * 102400) {
                            //to do sth
                        } else {
                            image.src = window.URL.createObjectURL(files.item(dd));
                            image.onload = function () {
                                // 默认按比例压缩
                                var w = image.width,
                                    h = image.height,
                                    scale = w / h;
                                w = 200;
                                h = w / scale;
                                // 默认图片质量为0.7，quality值越小，所绘制出的图像越模糊
                                var quality = 0.7;
                                //生成canvas
                                var canvas = document.createElement('canvas');
                                var ctx = canvas.getContext('2d');
                                // 创建属性节点
                                var anw = document.createAttribute("width");
                                anw.nodeValue = w;
                                var anh = document.createAttribute("height");
                                anh.nodeValue = h;
                                canvas.setAttributeNode(anw);
                                canvas.setAttributeNode(anh);
                                ctx.drawImage(image, 0, 0, w, h);
                                var ext = image.src.substring(image.src.lastIndexOf(".") + 1).toLowerCase();//图片格式
                                var base64 = canvas.toDataURL("image/" + ext, quality);
                                // 回调函数返回base64的值
                                if (_this.imgArr.length <= 0) {
                                    _this.imgArr.unshift('');
                                    _this.imgArr.splice(0, 1, base64);//替换数组数据的方法，此处不能使用：this.imgArr[index] = url;
                                    if (_this.imgArr.length >= 1) {
                                        _this.allowAddImg = false;
                                    }
                                }
                                // alert(base64);
                                sessionStorage.setItem('newImg', base64);
                                console.log(_this.imgArr);
                            };
                        }
                        if (dd < files.length - 1) {
                            dd++;
                        } else {
                            clearInterval(timer);
                        }
                    }, 1000)
                }
                // console.log('1');
                // alert(this.imgArr[0]);
                // if(this.imgArr!=null)
                // {sessionStorage.setItem('newImg', this.imgArr);
                //     console.log(this.imgArr);
                // }
            },
            deleteImg: function (index) {
                this.imgArr.splice(index, 1);
                if (this.imgArr.length < 1) {
                    this.allowAddImg = true;
                }
            },
        },
    }
</script>
