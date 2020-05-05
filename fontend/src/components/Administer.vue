<template>
    <div style="width: 100%">
        <!--    <el-button class="aside-btns" size="mini" @click="toggleEdit">Toggle Edit</el-button>-->
        <el-input v-model="target" placeholder="请输入搜索内容" style="width: 70%" @input="search()"
                  ref="AdmInput" value={$target}></el-input>
        <el-button class="aside-btns" size="mini" @click="addRow">Add Row</el-button>
        <el-button class="aside-btns" size="mini" @click="delRow">Delete Row</el-button>
        <!--        <el-button class="aside-btns" size="mini" @click="toggleSelect">Select</el-button>-->
        <el-table
                ref="multipleTable"
                :data="params.tableData"
                :header-cell-style="{color:'#769FCD'}"
                :cell-style="{color:'#769FCD'}"
                height="700"
                stripe
                @select="handleChange"
                style="width: 100%">
            <el-table-column align="center" type="selection" width="50" fixed="left" v-model="multipleSelection"  >
<!--                <template slot-scope="scope">-->
<!--                    <el-checkbox @select="handleChange(scope.row)"></el-checkbox>-->
<!--                </template>-->
            </el-table-column>
            <el-table-column
                    prop="title"
                    label="书名"
                    width="180">
<!--                <template slot-scope="scope">-->
<!--                    <template v-if="scope.row.is_edit">-->
<!--                        <input v-model="scope.row.copy.name" />-->
<!--                        <el-button size="mini" @click="scope.row.save">save</el-button>-->
<!--                        <el-button size="mini" @click="scope.row.is_edit = false">cancel</el-button>-->
<!--                    </template>-->
<!--                    <template v-else>-->
<!--                        <span @click="scope.row.show_edit">{{ scope.row.origin.name }}</span>-->
<!--                    </template>-->
<!--                </template>-->
            </el-table-column>
            <el-table-column
                    prop="author"
                    label="作者"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="isbn"
                    label="ISBN">
            </el-table-column>

            <el-table-column
                    prop="price"
                    label="价格">
            </el-table-column>
            <el-table-column
                    prop="stock"
                    label="库存">
            </el-table-column>
            <el-table-column
                    prop="image"
                    label="图片"
                    width="350">
            </el-table-column>
            <el-table-column
                    prop="description"
                    label="简介"
                    width="350">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="140">
                <template slot-scope="scope">
                    <el-button @click="handleDetails(scope.row)" type="text" size="small">查看详情</el-button>
                    <el-button @click="handleEdit(scope.row)" size="small">{{params.msg}}</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        inject: ["reload"],
        name: 'Demo',
        data() {
            return {
                params: {
                    tableData: [],
                    target: '',
                    msg:"编辑"
                },
                multipleSelection: []
            }
        },
        created() {
            const _this = this;
            _this.target = sessionStorage.getItem("target");
            if (_this.target == null) {
                axios.get('http://localhost:8080/getBooks').then(function (resp) {
                    console.log(resp.data);
                    resp.data.forEach((item) => {
                        _this.params.tableData.push({
                            title: item.name,
                            author: item.author,
                            price: item.price,
                            isbn: item.isbn,
                            stock: item.stock,
                            description: item.description,
                            image:item.image,
                            b_id: item.b_id,
                        });
                    })
                })
            } else {
                axios.get('http://localhost:8080/getAdmTargets?target=' + _this.target).then(function (resp) {
                    _this.params.tableData = [];
                    console.log(resp.data);
                    resp.data.forEach((item) => {
                        _this.params.tableData.push({
                            title: item.name,
                            author: item.author,
                            price: item.price,
                            isbn: item.isbn,
                            stock: item.stock,
                            image:item.image,
                            description: item.description,
                            b_id: item.b_id,
                        });
                    })
                })
            }
        },
        // computed: {
        //     filterData: function () {
        //         var input = this.target && this.target.toLowerCase();
        //         var items = this.params.tableData;
        //         var items1;
        //         if (input) {
        //             items1 = items.filter(function (item) {
        //                 return Object.keys(item).some(function (key1) {
        //                     return String(item[key1])
        //                         .toLowerCase()
        //                         .match(input);
        //                 });
        //             });
        //         } else {
        //             items1 = items;
        //         }
        //         // this.$forceUpdate();
        //         return items1;
        //     }
        // },
        watch: {
            target(data) {
                console.log(data);
            },
            multipleSelection(data){
                // console.log("aaaa1");
                console.log(data);
            }
        },
        methods: {
            handleChange(selection,row){
                console.log(row);
                this.multipleSelection=selection;
            },
            search() {
                this.$forceUpdate();
                this.$nextTick(function () {
                    this.$refs.AdmInput.focus();
                });
                const _this = this;
                sessionStorage.setItem('target', this.target);
                _this.reload();
            },
            handleEdit(index, row) {

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
                _this.multipleSelection.forEach((book)=>{
                axios.get('http://localhost:8080/delBook?b_id='+book.b_id).then(function (resp) {
                    console.log(resp);
                    _this.reload();
                })})
            }

            // onCellChange(rowIndex, columnIndex, data) {
            //     console.log('onCellChange: ', rowIndex, columnIndex, data)
            //     console.log('table data: ', this.$refs.table.getData())
            //     this.params.data[rowIndex][columnIndex] = data
            // },
            // toggleSelect() {
            //     this.params.showCheck = !this.params.showCheck
            // },
            // onSelect(isChecked, index, data) {
            //     console.log('onSelect: ', isChecked, index, data)
            //     console.log('Checked Data:', this.$refs.table.getCheckedRowDatas(true))
            // },
            // onSelectionChange(checkedDatas, checkedIndexs, checkedNum) {
            //     console.log('onSelectionChange: ', checkedDatas, checkedIndexs, checkedNum)
            // },
            // addRow() {
            //     let rowNum = this.params.data.length
            //     let columnNum = this.params.data[0].length
            //
            //     if (rowNum >= 200) {
            //         return this.showMsg('warning', 'The number of rows cannot be more than 200.')
            //     }
            //
            //     let newRow = [rowNum]
            //     // newRow.push(this.rowIndex)
            //     for (let i = 1; i < columnNum; i++) {
            //         newRow.push(`new-Cell`)
            //     }
            //
            //     this.params.data.push(newRow)
            // },
            // getIndexByData(val) {
            //     for (var i = 0; i < this.params.data.length; i++) {
            //         if (this.params.data[i][0] == val[0]) {
            //             return i;
            //         }
            //     }
            //     return -1;
            // },
            // deleteRow() {
            //     for (let i = 0; i < this.$refs.table.getCheckedRowDatas().length; i++) {
            //         this.params.data.splice(this.getIndexByData(this.$refs.table.getCheckedRowDatas()[i]), 1)
            //     }
            //     // let rowNum = this.params.data.length
            //     // if (rowNum <= 1) {
            //     //     return this.showMsg('warning', 'The number of rows cannot be less than 1.')
            //     // }
            //     //
            //     // this.params.data.pop()
            // },

            // format (val) {
            //     if (val.indexOf(this.search) !== -1 && this.search !== '') {
            //         return val.replace(this.search, '<font color="red">' + this.search + '</font>')
            //     } else {
            //         return val
            //     }
            // }
        },
    }
</script>
<style>
    @import "../assets/css2.css";
    @import "../assets/css1.css";

    #content {
        margin-top: 0;
    }
</style>