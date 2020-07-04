<template>
    <div style="width: 100%">
        <div style="margin-bottom: 20px">
            <input v-model="target" placeholder="search.."
                   style="width: 70%;height: 35px;border: 1px #B9D7EA solid;border-radius: 5px" @keyup.enter="search()"
                   ref="AdmInput" value={$target}>
            <el-button class="aside-btns" @click="addRow">Add Book</el-button>
            <el-button class="aside-btns" @click="delRow">Delete Book</el-button>
        </div>
        <el-table
                ref="multipleTable"
                :data="params.tableData"
                :header-cell-style="{color:'#769FCD'}"
                :cell-style="{color:'#769FCD'}"
                height="700"
                stripe
                @select="handleChange"
                style="width: 100%;">
            <el-table-column align="center" type="selection" width="25" fixed="right" v-model="multipleSelection">
            </el-table-column>
            <el-table-column
                    prop="onshelf"
                    label="状态"
                    width="120"
                    align="center">
                <template slot-scope="scope">
                    <template v-if="scope.row.is_edit">
                        <el-select v-model="scope.row.onshelf">
                            <el-option
                                    v-for="item in options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </template>
                    <template v-else>
                        <span>{{ shelf[scope.row.onshelf]}}</span>
                    </template>
                </template>
            </el-table-column>
            <el-table-column
                    prop="title"
                    label="书名"
                    width="160">
                <template slot-scope="scope">
                    <template v-if="scope.row.is_edit">
                        <input v-model="scope.row.name" style="width: 90%"/>
                        <!--                        <el-button size="mini" @click="scope.row.is_edit = false">cancel</el-button>-->
                    </template>
                    <template v-else>
                        <span>{{ scope.row.name }}</span>
                    </template>
                </template>
            </el-table-column>
            <el-table-column
                    prop="author"
                    label="作者"
                    width="120">
                <template slot-scope="scope">
                    <template v-if="scope.row.is_edit">
                        <input v-model="scope.row.author" style="width: 80%"/>
                        <!--                        <el-button size="mini" @click="scope.row.is_edit = false">cancel</el-button>-->
                    </template>
                    <template v-else>
                        <span>{{ scope.row.author }}</span>
                    </template>
                </template>
            </el-table-column>
            <el-table-column
                    prop="isbn"
                    label="ISBN"
                    width="160">
                <template slot-scope="scope">
                    <template v-if="scope.row.is_edit">
                        <input v-model="scope.row.isbn" style="width: 90%"/>
                    </template>
                    <template v-else>
                        <span>{{ scope.row.isbn }}</span>
                    </template>
                </template>
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="价格"
                    width="100">
                <template slot-scope="scope">
                    <template v-if="scope.row.is_edit">
                        <input v-model="scope.row.price" style="width: 80%"/>
                    </template>
                    <template v-else>
                        <span>{{ scope.row.price}}</span>
                    </template>
                </template>
            </el-table-column>
            <el-table-column
                    prop="stock"
                    label="库存"
                    width="100">
                <template slot-scope="scope">
                    <template v-if="scope.row.is_edit">
                        <input v-model="scope.row.stock" style="width: 80%"/>
                    </template>
                    <template v-else>
                        <span>{{ scope.row.stock }}</span>
                    </template>
                </template>
            </el-table-column>
            <el-table-column
                    prop="image"
                    label="封面"
                    width="100">
                <template slot-scope="scope">
                    <template v-if="scope.row.is_edit">
                        <ImgUp :tpye="0" v-model="scope.row.bookInfo.image"></ImgUp>
                    </template>
                    <template v-else>
                        <img :src="scope.row.bookInfo.image" min-width="70" height="70">
                    </template>
                </template>
            </el-table-column>
            <el-table-column
                    prop="description"
                    label="简介"
                    width="350">
                <template slot-scope="scope">
                    <template v-if="scope.row.is_edit">
                        <textarea v-model="scope.row.description" style="width: 90%;height: 100px;border:1px #D6E6F2 solid;border-radius:5px;color: #769FCD;"/>
                    </template>
                    <template v-else>
                        <span>{{ scope.row.description }}</span>
                    </template>
                </template>
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    align="center"
                    width="180">
                <template slot-scope="scope">
                    <el-button @click="handleDetails(scope.row)" v-if="!scope.row.is_edit" type="text" size="small">
                        查看详情
                    </el-button>
                    <el-button @click="handleCancel(scope.row)" v-else size="small">取消</el-button>
                    <el-button @click="handleSave(scope.row)" v-if="scope.row.is_edit" size="small">保存</el-button>
                    <el-button @click="handleEdit(scope.row)" v-else size="small">编辑</el-button>

                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import axios from "axios";
    import ImgUp from "@/components/ImgUp";

    export default {
        components: {ImgUp},
        inject: ["reload"],
        name: 'Demo',
        data() {
            return {
                params: {
                    tableData: [],
                },
                target: null,
                multipleSelection: [],
                options: [{
                    value: '0',
                    label: '未上架'
                }, {
                    value: '1',
                    label: '已上架'
                }],
                shelf: ['未上架', '已上架']
            }
        },
        created() {
            const _this = this;
            _this.target = sessionStorage.getItem("target1");
            if (_this.target == null) {
                axios.get('http://localhost:8080/getBooks').then(function (resp) {
                    console.log(resp.data);
                    resp.data.forEach((item) => {
                        var img = null;
                        if (item.bookInfo)
                            img = item.bookInfo.image;
                        _this.params.tableData.push({
                            name: item.name,
                            onshelf: item.onshelf,
                            author: item.author,
                            price: item.price,
                            isbn: item.isbn,
                            stock: item.stock,
                            description: item.description,
                            bookInfo: {image: img},
                            b_id: item.b_id,
                            is_edit: false
                        });
                    })
                })
            } else {
                axios.get('http://localhost:8080/getAdmTargets?target=' + _this.target).then(function (resp) {
                    _this.params.tableData = [];
                    console.log(resp.data);
                    resp.data.forEach((item) => {
                        var img = null;
                        if (item.bookInfo)
                            img = item.bookInfo.image;
                        _this.params.tableData.push({
                            name: item.name,
                            author: item.author,
                            price: item.price,
                            isbn: item.isbn,
                            onshelf: item.onshelf,
                            stock: item.stock,
                            bookInfo: {image: img},
                            description: item.description,
                            b_id: item.b_id,
                            is_edit: false
                        });
                    })
                })
            }
        },
        watch: {
            target(data) {
                console.log(data);
            },
            multipleSelection(data) {
                console.log(data);
            },
            $nextTick(){
                this.$refs.multipleTable.doLayout()
            }
        },
        methods: {
            handleChange(selection, row) {
                console.log(row);
                this.multipleSelection = selection;
            },
            search() {
                this.$forceUpdate();
                this.$nextTick(function () {
                    this.$refs.AdmInput.focus();
                });
                const _this = this;
                sessionStorage.setItem('target1', this.target);
                _this.reload();
            },
            handleEdit(index, row) {
                index.is_edit = true;
                // this.reload();
                console.log(index, row);
            },
            handleCancel(index, row) {
                index.is_edit = false;
                this.reload();
                console.log(index, row)
            },
            handleSave(index, row) {
                // const _this=this;
                index.is_edit = false;
                var img = sessionStorage.getItem("newImg");
                console.log(img);
                if (img != null)
                    index.bookInfo.image = img;
                console.log(index);
                axios.post('http://localhost:8080/updateBook', index).then(function (resp) {
                    console.log(resp);
                    sessionStorage.removeItem("newImg");
                });
                console.log(index, row)
            },
            handleDetails(index, row) {
                var id = index.b_id;
                console.log(index, row);
                location.href = 'http://localhost:3000/details/' + id.toString();
            },
            addRow() {
                const _this = this;
                axios.get('http://localhost:8080/addBook').then(function (resp) {
                    console.log(resp);
                    _this.reload();
                })
            },
            delRow() {
                const _this = this;
                console.log("start");
                _this.multipleSelection.forEach((book) => {
                    axios.get('http://localhost:8080/delBook?b_id=' + book.b_id).then(function (resp) {
                        console.log(resp);
                        _this.reload();
                    })
                })
            },
        },
    }
</script>
<style>
    @import "../assets/css2.css";
    @import "../assets/css1.css";

    input {
        font-style: normal;
    }
    body .el-table th.gutter{
        display: table-cell!important;
    }
    #content {
        margin-top: 0;
    }
</style>