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
                    width="250"
                    align="center">
                <template slot-scope="scope">
                    <p> {{bookData[scope.row.b_id-1].name}}</p>
                </template>
            </el-table-column>
            <el-table-column
                    prop="author"
                    label="作者"
                    width="230"
                    align="center">
                <template slot-scope="scope">
                    <p> {{bookData[scope.row.b_id-1].author}}</p>
                </template>
            </el-table-column>
            <el-table-column
                    prop="sum"
                    label="数量"
                    width="150"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="单价"
                    width="170"
                    align="center">
                <template slot-scope="scope">
                    <p> {{bookData[scope.row.b_id-1].price}}</p>
                </template>
            </el-table-column>
            <el-table-column
                    prop="cost"
                    label="总价"
                    width="200"
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
                    return a.b_id - b.b_id;
                });
                this.mergeTable();
            },
            sortBysum() {
                this.tableData.sort(function (a, b) {
                    if (a.sum == b.sum) {
                        return a.b_id - b.b_id;
                    } else {
                        return b.sum - a.sum;
                    }
                });
            },
            mergeTable() {
                let b = 0, e = 0, l = this.tableData.length;
                let book = this.tableData[0].b_id;
                let n = this.tableData[0].num;
                for (let i = 1; i < l;) {
                    while (i < l
                    && this.tableData[i].b_id == book) {
                        n += this.tableData[i].num;
                        i++;
                    }
                    e = i - 1;
                    for (let j = b; j <= e; j++) {
                        this.tableData[j].sum = n;
                    }
                    n = 0;
                    b = i;
                    if(i<l) book = this.tableData[i].b_id;
                    else break;
                }
                this.sortBysum();
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
                _this.orderData.forEach((item) => {
                    _this.x = item;
                    _this.formData.forEach((i) => {
                        if (i.f_id == _this.x.f_id) {
                            i.time = i.time.substr(0, 10) + ' ' + i.time.substr(11, 8);
                            _this.x.form = i;
                            _this.tableData.push(_this.x);
                        }
                    })
                });
                _this.packBook();
            },
            objectSpanMethod({row, column, rowIndex, columnIndex}) {
                console.log(row, column);
                if (columnIndex === 0 || columnIndex === 1 || columnIndex === 2 || columnIndex === 3|| columnIndex === 4) {
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