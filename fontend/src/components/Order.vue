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
        <div>
            <el-row v-for="(it,index) of filterData" :key="index" :offset="index">
                <div v-if="hackReset == true">
                    <OrderForm :f_id="it.f_id" :time="it.time" :cost="it.cost"></OrderForm>
                </div>
            </el-row>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    import OrderForm from "@/components/OrderForm";

    export default {
        name: "Order",
        components: {OrderForm},
        inject: ["reload"],
        data() {
            return {//我严重怀疑IDEA是智障，为什么{换个行就有bug
                formData: [],
                filterData: [],
                filterBook: [],
                u_id: 0,
                target: '',
                isFilter: false,
                isSearch: false,
                hackReset: true,
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
            }
        },
        created() {
            const _this = this;
            _this.u_id = sessionStorage.getItem("u_id");
            console.log(_this.u_id);
            axios.get('http://localhost:8080/getAllOrderForm?u_id=' + _this.u_id.toString()).then(function (resp) {
                console.log(resp.data);
                _this.formData = resp.data;
                _this.formData.forEach((i) => {
                    i.time=i.time.substr(0,10);
                    axios.get('http://localhost:8080/getOrders?f_id=' + i.f_id.toString()).then(function (resp) {
                        i.books = resp.data;
                    });
                });
                _this.filterData = _this.formData;
                // _this.formData = resp.data.reverse();
            })
        },
        methods: {
            search() {
                const _this = this;
                _this.isSearch = true;
                if (_this.target == null || _this.target == '') {
                    _this.isSearch = false;
                    _this.filterBook = [];
                    if (_this.isFilter) _this.filterForm();
                    else
                        _this.filterData = _this.formData.reverse();
                } else {
                    axios.get('http://localhost:8080/getAdmTargets?target=' + _this.target).then(function (resp) {
                        _this.filterBook = resp.data;
                        console.log(resp.data);
                        var newData = [];
                        for (var f = 0; f < _this.filterData.length; f++) {
                            var flag = false;
                            console.log(_this.filterData[f]);
                            for (var b = 0; b < _this.filterData[f].books.length; b++) {
                                for (var i = 0; i < _this.filterBook.length; i++) {
                                    if (_this.filterBook[i].b_id == _this.filterData[f].books[b].b_id) {
                                        newData.push(_this.filterData[f]);
                                        flag = true;
                                        break;
                                    }
                                }
                                if (flag) break;
                            }
                        }
                        _this.filterData = newData;

                        console.log("newdata");
                        console.log(newData);
                        if (_this.isFilter) _this.filterForm();
                        else _this.forceUpdate();
                    })
                }

            },
            forceUpdate() {
                this.hackReset = false;
                // $nextTick 是在下次 DOM 更新完成后，在执行里面的函数，类似于回调
                this.$nextTick(() => {
                    this.hackReset = true;
                });
            },
            filterForm() {
                const _this = this;
                _this.isFilter = true;
                if (!_this.isSearch) _this.filterData = _this.formData.reverse();
                var newData = [];
                axios.get('http://localhost:8080/getByTime?t1=' + _this.rangetime[0] + '&t2=' + _this.rangetime[1]).then(function (resp) {
                    console.log(resp.data);
                    console.log(_this.u_id);
                    for (var f = 0; f < _this.filterData.length; f++) {
                        for (var i = 0; i < resp.data.length; i++)
                            if (_this.filterData[f].f_id == resp.data[i].f_id)
                                newData.push(_this.filterData[f]);
                    }
                    _this.filterData = newData;
                    _this.forceUpdate();
                });
            },
            filterCancel() {
                const _this = this;
                _this.isFilter = false;
                _this.rangetime = [];
                _this.filterData = _this.formData.reverse();
                if (!_this.isSearch)
                    _this.forceUpdate();
                else _this.search();
            }
        }
    }
</script>

<style scoped>

</style>