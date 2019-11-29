<template>
  <el-main>
    <el-form :inline="true" :model="addForm" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="addForm.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="登录名">
        <el-input v-model="addForm.loginid" placeholder="请输入登录名"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="邮箱">
        <el-input v-model="addForm.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="电话">
        <el-input v-model="addForm.telno" placeholder="请输入电话"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="省">
        <el-select @change="sheng" v-model="addForm.provincecode" placeholder="请选择省">
          <el-option
            v-for="item in provinceList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <br>
      <el-form-item label="市">
        <el-select @change="changecity" v-model="addForm.citycode" placeholder="请选择市">
          <el-option
            v-for="item in cityList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <br>
      <el-form-item label="区">
        <el-select v-model="addForm.countycode" placeholder="请选择区">
          <el-option
            v-for="item in countyList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="部门">
        <el-select v-model="addForm.org" placeholder="请选择部门">
          <el-option
            v-for="item in orgList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <br>
      <el-form-item label="生日">
        <div class="block">
          <el-date-picker
            v-model="addForm.birthday"
            value-format="yyyy-MM-dd"
            type="date"
            placeholder="选择开始日期">
          </el-date-picker>
        </div>
      </el-form-item>
      <br>
      <el-form-item>
        <el-button type="primary" @click="save">保存</el-button>
      </el-form-item>
    </el-form>
  </el-main>
</template>

<script>
    const axios = require("axios");
    const basePath = "http://127.0.0.1/";
    export default {
        name: "Save",
        data() {
            return {
                addForm: {},
                provinceList: [],
                cityList: [],
                countyList: [],
                orgList:[]
            }
        },
        methods: {
            save() {
                const self = this;
                if (!Number.isInteger(new Number(self.addForm.telno).valueOf())){
                    this.$message("电话号必须为数字")
                    return;
                }
                axios({
                    url: basePath + "user/save",
                    method: "post",
                    data: this.addForm
                }).then(res => {
                    this.$router.push("/")
                })
            },
            sheng() {

                axios({
                    url: basePath + "user/Address",
                    method: "get",
                    params: {code: this.addForm.provincecode}
                }).then(res => {
                    this.cityList=[];
                    this.addForm.countycode=""
                    this.countyList=[];
                    this.addForm.citycode=""
                    this.cityList = res.data
                })
            },
            changecity() {
                axios({
                    url: basePath + "user/Address",
                    method: "get",
                    params: {code: this.addForm.citycode}
                }).then(res => {
                    this.countyList = res.data
                })
            }
        },
        created() {
            let id = this.$route.params.id

            let self = this;

            this.addForm={provincecode:"",citycode:"",countycode:""};

            axios({
                url: basePath + "user/initData",
                method: "get"
            }).then(res => {
                this.provinceList = res.data.province
                this.orgList = res.data.org
            })

            if (id > 0) {
                axios({
                    url: basePath + "user/getById",
                    method: "get",
                    params: {id: id}
                }).then(res => {
                    self.addForm = res.data
                   /* self.cityList=res.data.*/
                    console.log(self.addForm)
                })
            }
        }
    }
</script>
<style scoped>
</style>
