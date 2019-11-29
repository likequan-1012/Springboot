<template>
  <el-main>


    <el-form :inline="true" :model="queryForm" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="queryForm.name" placeholder="请输入姓名或登录名"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="生日">
        <div class="block">
          <el-date-picker
            v-model="queryForm.birstart"
            value-format="yyyy-MM-dd"
            type="date"
            placeholder="选择开始日期">
          </el-date-picker>
        </div>
        <div class="block">
          <el-date-picker
            v-model="queryForm.birend"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择结束日期">
          </el-date-picker>
        </div>
      </el-form-item>
      <br>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>


    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        fixed
        prop="name"
        label="姓名"
        width="150">
      </el-table-column>
      <el-table-column
        prop="loginid"
        label="登录名"
        width="120">
      </el-table-column>
      <el-table-column
        prop="birthday"
        label="生日"
        width="120">
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱"
        width="120">
      </el-table-column>
      <el-table-column
        prop="telno"
        label="电话"
        width="300">
      </el-table-column>
      <el-table-column
        prop="province"
        label="省"
        width="120">
      </el-table-column>
      <el-table-column
        prop="city"
        label="市"
        width="120">
      </el-table-column>
      <el-table-column
        prop="county"
        label="县"
        width="120">
      </el-table-column>
      <el-table-column
        prop="bname"
        label="部门"
        width="120">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="dels(scope.row.id)" type="text" size="small">删除</el-button>
          <el-button type="text" size="small" @click="update(scope.row.id)" >编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      background
      layout="prev, pager, next"
      @current-change="gotoPage"
      :page-count="pageCount">
    </el-pagination>

    <el-row>
      <el-button type="primary" @click="add">添加</el-button>
      <el-button type="primary" @click="dels(null)">批量删除</el-button>
    </el-row>
  </el-main>
</template>

<script>
    const axios = require("axios");
    const basePath = "http://127.0.0.1/";
    export default {
        name: "User",
        data() {
            return {
                tableData: [],
                pageCount: 0,
                queryForm: {
                    pageNo: 1,
                    pageSize: 3
                },
                ids:null
            }
        },
        methods: {
            handleSelectionChange(row){
                let ids = null;
                row.forEach(res=>{
                    if (ids==null){
                        ids=res.id;
                    }else {
                        ids = ids+","+res.id;
                    }
                })
            this.ids = ids;
            },
            dels(id){
                if(id!=null){
                    this.ids = id;
                }
                if (this.ids==null){
                    this.$message("至少选中一条");
                    return
                }
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios({
                        url: basePath + "user/dels",
                        method: "get",
                        params: {ids: this.ids}
                    }).then(res => {
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        this.getList();
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            getList() {
                let self = this;
                axios({
                    url: basePath + "user/list",
                    method: "get",
                    params: this.queryForm
                }).then(res => {
                    self.tableData = res.data.list;
                    self.pageCount = res.data.pages;
                })
            },
            gotoPage(pageNo) {
                this.queryForm.pageNo = pageNo;
                this.getList();
            },
            onSubmit() {
                this.getList()
            },
            handleClose() {
                dialogVisible = false;
            },
            add() {
                this.$router.push("/save/"+null)
            },
            update(id){
                this.$router.push("/save/"+id)
            }
        },
        created() {
            this.getList()
        }
    }
</script>

<style scoped>

</style>
