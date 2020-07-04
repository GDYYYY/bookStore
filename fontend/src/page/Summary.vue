<template>
    <div>
        <div style="margin-bottom: 20px">
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
        <div>
            <p style="float: right">总书数:{{this.totalnum}}</p>
            <p style="float: right;margin-right: 30px">总耗资:{{this.totalcost}}</p></div>
        <el-table
                :data="tableData"
                border
                stripe
                height="600"
                style="width: 100%;"
                :span-method="objectSpanMethod"
                :header-cell-style="{color:'#769FCD'}"
                :cell-style="{color:'#769FCD'}">
            <el-table-column
                    prop="b_id"
                    label="书籍"
                    width="300"
                    align="center">
                <template slot-scope="scope">
                    <p> {{bookData[scope.row.b_id-1].name}}</p>
                </template>
            </el-table-column>
            <el-table-column
                    prop="sum"
                    label="数量"
                    width="200"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="单价"
                    width="200"
                    align="center">
                <template slot-scope="scope">
                    <p> {{bookData[scope.row.b_id-1].price}}</p>
                </template>
            </el-table-column>
            <el-table-column
                    prop="cost"
                    label="花费"
                    width="270"
                    align="center">
                <template slot-scope="scope">
                    <p> {{(bookData[scope.row.b_id-1].price*scope.row.sum).toFixed(2)}}</p>
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
                u_id: '',
                totalcost: 0,
                totalnum: 0,
                tableData: [],
                bookData: [],
                userData: [],
                orderData: [],
                formData: [],
                filterBook: [],
                target: '',
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
        watch: {
            tableData(value) {
                console.log(this.rangetime);
                console.log(value);
            }
        },
        created() {
            const _this = this;
            _this.u_id = sessionStorage.getItem("u_id");
            axios.get('http://localhost:8080/getAllOrderForm?u_id=' + _this.u_id.toString()).then(function (resp) {
                // console.log(resp.data);
                _this.formData = resp.data;
                axios.get('http://localhost:8080/getAllOrders').then(function (resp) {
                    // console.log(resp.data);
                    // _this.orderData = resp.data;
                    resp.data.forEach(i=>{
                        if(i.u_id==_this.u_id)
                            _this.orderData.push(i);
                    });
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
                                    i.time = i.time.substr(0, 10) + ' ' + i.time.substr(11, 8);
                                    _this.x.form = i;
                                    _this.x.sum = _this.x.num;
                                    _this.tableData.push(_this.x);
                                    // console.log(_this.x);
                                }
                            })
                        });
                        _this.sortTable();
                    })
                })

            })
        },
        methods: {
            sortTable() {
                this.tableData.sort(function (a, b) {
                    if (a.form.u_id == b.form.u_id) {
                        return a.b_id - b.b_id;
                        // return new Date(b.form.time.replace(/-/g, "/")) - new Date(a.form.time.replace(/-/g, "/"))
                    } else {
                        return a.form.u_id - b.form.u_id;
                    }
                });
                this.mergeTable();
            },
            total() {
                // alert('aa');
                let l = this.tableData.length;
                let table = this.tableData;
                let price = this.bookData;
                let u = this.u_id;
                let n = 0;
                let cost = 0;
                // cost = parseFloat(cost);
                let e = 0;
                for (e; e < l; e++) {
                    if (table[e].form.u_id == u) break;
                }
                // alert(e);
                while (e < l && table[e].form.u_id == u) {
                    let num = table[e].num;
                    let bid = table[e].b_id;
                    n += num;
                    // if(bid==1) alert(n);
                    cost += Number((num * price[bid - 1].price).toFixed(2));
                    e++;
                }
                this.totalnum = n;
                this.totalcost = cost;
            },
            mergeTable() {
                let b = 0, e = 0, l = this.tableData.length;
                let u = this.u_id;
                let n = 0;
                let book = 0;
                let i = 0;
                for (i; i < l; i++) {
                    if (this.tableData[i].form.u_id == u) break;
                }
                b=i;
                book=this.tableData[i].b_id;
                this.total();
                while (i < l && this.tableData[i].form.u_id == u) {
                    while (i < l &&this.tableData[i].form.u_id == u&&this.tableData[i].b_id == book) {
                        n += this.tableData[i].num;
                        // if(book==1) alert(n);
                        i++;
                    }
                    e = i - 1;
                    for (let j = b; j <= e; j++) {
                        this.tableData[j].sum = n;
                    }
                    n = 0;
                    b = i;
                    book = this.tableData[i].b_id;
                    // alert( this.tableData[i].form.u_id);
                }
            },
            packBook() {
                var newTable = [];
                const _this = this;
                this.tableData.forEach(it => {
                    if (_this.bookData[it.b_id-1].flag == 1) {
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
                            i.time = i.time.substr(0, 10) + ' ' + i.time.substr(11, 8);
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
                if (columnIndex === 0 || columnIndex === 1 || columnIndex === 2 || columnIndex === 3) {
                    const _row = this.setTable(this.tableData).one[rowIndex];
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
                    concatOne = 0;
                tableData.forEach((item, index) => {
                        if (index === 0) {
                            spanOneArr.push(1);
                            spanTwoArr.push(1);
                        } else {
                            if (item.b_id === tableData[index - 1].b_id) {
                                spanOneArr[concatOne] += 1;
                                spanOneArr.push(0);
                            } else {
                                spanOneArr.push(1);
                                concatOne = index;
                            }
                        }
                    }
                );
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