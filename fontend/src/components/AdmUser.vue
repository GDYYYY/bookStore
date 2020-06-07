<template>
    <div style="width: 100%">
        <el-table
                ref="multipleTable"
                :data="params.tableData"
                :header-cell-style="{color:'#769FCD'}"
                :cell-style="{color:'#769FCD'}"
                stripe
                style="width: 100%">
            <el-table-column
                    prop="username"
                    label="用户名"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="password"
                    label="密码"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="role"
                    label="身份">
                <template slot-scope="scope">
                    <p> {{r[scope.row.role+1]}}</p>
                </template>
            </el-table-column>
            <el-table-column
                    prop="icon"
                    label="头像"
                    width="100">
            <template slot-scope="scope">
                <img :src="scope.row.icon"  min-width="70" height="70" />
            </template>
            </el-table-column>
            <el-table-column
                    prop="email"
                    label="邮箱">
            </el-table-column>
            <el-table-column
                    float="right"
                    align="center"
                    width="350">
                <template slot-scope="scope">
                    <el-button @click="handleDisabled(scope.row)" :disabled="(scope.row.role)==0?false:true" size="small">禁用</el-button>
                    <el-button @click="handleAble(scope.row)" :disabled="(scope.row.role)==-1?false:true" size="small">解禁</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: 'Demo',
        data() {
            return {
                params: {
                    tableData: [],
                },
                r:['禁用','用户','管理员']
            }
        },
        created() {
            const _this = this;
            var icon=null;
                axios.get('http://localhost:8080/getUsers').then(function (resp) {
                    console.log(resp.data);
                    resp.data.forEach((item) => {
                        if(item.userIcon)
                            icon=item.userIcon.icon;
                        _this.params.tableData.push({
                            username: item.username,
                            password: item.password,
                            role:item.role,
                            email:item.email,
                            icon:icon
                        });
                    })
                })
        },
        methods: {
            handleAble(index, row) {
                var user = index;
                user.role=0;
                axios.post('http://localhost:8080/updateUser',user).then(function (resp){
                    // index.role=resp.data.role;
                    console.log(index, row,resp.role)
                });
            },
            handleDisabled(index, row) {
                var user = index;
                user.role=-1;
                axios.post('http://localhost:8080/updateUser',user).then(function (resp){
                    // index.role=resp.data.role;
                    console.log(index, row,resp.role)
                })
            },
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