<template>
    <div>
        <div style="margin-bottom: 20px">
            <input v-model="target" placeholder="search.."
                   style="width: 30%;height:35px;margin-left:2%;border: 1px #B9D7EA solid;border-radius: 5px;float: left"
                   @keyup.enter="search()"
                   ref="AdmInput" value={$target}>
            <el-date-picker
                    v-model="rangetime"
                    type="datetimerange"
                    format="yyyy-MM-dd HH:mm:ss"
                    value-format="yyyy-MM-dd HH:mm:ss"
                    :picker-options="pickerOptions"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    align="right"
            >
            </el-date-picker>
            <el-button style="margin-left: 10px" @click="filterForm">过滤</el-button>
            <el-button @click="filterCancel">取消</el-button>
        </div>
        <el-table
                :data="tableData"
                border
                stripe
                height="700"
                style="width: 100%;"
                :span-method="objectSpanMethod"
                :header-cell-style="{color:'#769FCD'}"
                :cell-style="{color:'#769FCD'}">
            <el-table-column
                    prop="form.u_id"
                    label="用户"
                    width="180"
                    align="center">
                <template slot-scope="scope">
                    <p> {{userData[scope.row.form.u_id-1].username}}</p>
                </template>
            </el-table-column>
            <el-table-column
                    prop="form.f_id"
                    label="订单号"
                    width="100"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="form.cost"
                    label="花费"
                    width="150"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="form.time"
                    label="时间"
                    width="100"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="b_id"
                    label="书籍"
                    width="221"
                    align="center">
                <template slot-scope="scope">
                    <p> {{bookData[scope.row.b_id-1].name}}</p>
                </template>
            </el-table-column>
            <el-table-column
                    prop="num"
                    label="数量"
                    width="100"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="单价"
                    width="150"
                    align="center">
                <template slot-scope="scope">
                    <p> {{bookData[scope.row.b_id-1].price}}</p>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        inject: ["reload"],
        data() {
            return {
                tableData: [],
                bookData: [],
                userData: [],
                orderData: [],
                formData: [],
                filterBook: [],
                target:'',
                x: {},
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
                rangetime: ''
            };
        },
        watch:{
            tableData(value){
                console.log(this.rangetime);
                console.log(value);
            }
        },
        created() {
            const _this = this;
            axios.get('http://localhost:8080/getUsers').then(function (resp) {
                // console.log(resp.data);
                _this.userData = resp.data;
                axios.get('http://localhost:8080/getAllForms').then(function (resp) {
                    // console.log(resp.data);
                    _this.formData = resp.data;
                    axios.get('http://localhost:8080/getAllOrders').then(function (resp) {
                        // console.log(resp.data);
                        _this.orderData = resp.data;
                        console.log(_this.orderData);
                        axios.get('http://localhost:8080/getBooks').then(function (resp) {
                            // console.log(resp.data);
                            _this.bookData = resp.data;
                            _this.bookData.forEach(b => {
                                b.flag = 1;
                            });
                            _this.orderData.forEach((item) => {
                                _this.x = item;
                                _this.formData.forEach((i) => {
                                    if (i.f_id === _this.x.f_id) {
                                        i.time = i.time.substr(0, 10)+' '+i.time.substr(11,8);
                                        _this.x.form = i;
                                        _this.tableData.push(_this.x);
                                        // console.log(_this.x);
                                    }
                                })
                            });
                            _this.sortTable();
                        })
                    })

                })
            })
        },
        methods: {
            sortTable(){
                this.tableData.sort(function (a, b) {
                    if (a.form.u_id === b.form.u_id) {
                        return a.form.f_id-b.form.f_id;
                        // return new Date(b.form.time.replace(/-/g, "/")) - new Date(a.form.time.replace(/-/g, "/"))
                    } else {
                        return a.form.u_id - b.form.u_id;
                    }
                });
            },
            search() {
                const _this = this;
                if (_this.target == null || _this.target == '') {
                    _this.filterBook = [];
                    _this.bookData.forEach(b => {
                        b.flag = 1;
                    });
                    _this.packTable();
                } else {
                    axios.get('http://localhost:8080/getAdmTargets?target=' + _this.target).then(function (resp) {
                        _this.filterBook = resp.data;
                        console.log('sss');
                        console.log(resp.data);
                        _this.bookData.forEach(b => {
                            b.flag = 0;
                        });
                        _this.filterBook.forEach(b => {
                            _this.bookData[b.b_id].flag = 1;
                        });
                        _this.packTable();
                    })
                }
            },
            packBook() {
                var newTable = [];
                const _this = this;
                this.tableData.forEach(it => {
                    if (_this.bookData[it.b_id].flag == 1) {
                        newTable.push(it);
                    }
                });
                this.tableData = newTable;
                this.sortTable();
            },
            filterForm() {
                const _this = this;
                axios.get('http://localhost:8080/getByTime?t1=' + _this.rangetime[0] + '&t2=' + _this.rangetime[1]).then(function (resp) {
                    _this.formData = resp.data;
                    console.log('a');
                    console.log(resp.data);
                    _this.packTable();
                })
            },
            filterCancel() {
                const _this = this;
                _this.rangetime = [];
                axios.get('http://localhost:8080/getAllForms').then(function (resp) {
                    _this.formData = resp.data;
                    _this.packTable();
                })
            },
            packTable() {
                const _this = this;
                _this.tableData = [];
                // console.log('0');
                // console.log(_this.formData);
                _this.orderData.forEach((item) => {
                    _this.x = item;
                    _this.formData.forEach((i) => {
                        if (i.f_id == _this.x.f_id) {
                            i.time = i.time.substr(0, 10)+' '+i.time.substr(11, 8);
                            _this.x.form = i;
                            _this.tableData.push(_this.x);
                            // console.log(_this.tableData);
                        }
                    })
                });
                _this.packBook();
            },
            objectSpanMethod({row, column, rowIndex, columnIndex}) {
                console.log(row, column);
                if (columnIndex === 0) {
                    const _row = this.setTable(this.tableData).one[rowIndex];
                    const _col = _row > 0 ? 1 : 0;
                    return {
                        rowspan: _row,
                        colspan: _col
                    };
                }
                if (columnIndex === 1 || columnIndex === 2 || columnIndex === 3) {
                    const _row = this.setTable(this.tableData).two[rowIndex];
                    const _col = _row > 0 ? 1 : 0;
                    return {
                        rowspan: _row,
                        colspan: _col
                    };
                }
            },
            setTable(tableData) {
                let spanOneArr = [],
                    spanTwoArr = [],
                    concatOne = 0,
                    concatTwo = 0;
                tableData.forEach((item, index) => {
                    if (index === 0) {
                        spanOneArr.push(1);
                        spanTwoArr.push(1);
                    } else {
                        if (item.form.u_id === tableData[index - 1].form.u_id) {
                            spanOneArr[concatOne] += 1;
                            spanOneArr.push(0);
                        } else {
                            spanOneArr.push(1);
                            concatOne = index;
                        }
                        if (item.f_id === tableData[index - 1].f_id) {
                            spanTwoArr[concatTwo] += 1;
                            spanTwoArr.push(0);
                        } else {
                            spanTwoArr.push(1);
                            concatTwo = index;
                        }
                    }
                });
                return {
                    one: spanOneArr,
                    two: spanTwoArr
                };
            },
        },
    }
</script>

<style>
    @import "../assets/css2.css";
    @import "../assets/css1.css";

</style>