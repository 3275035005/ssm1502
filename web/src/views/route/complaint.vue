<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>景点投诉信息</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="投诉用户">
        <el-select v-model="formInline.uid" placeholder="请选择投诉用户" clearable >
          <el-option v-for="item in userList" :label="item.name" :value="item.id" :key="item.id"/>
        </el-select>
      </el-form-item>

      <el-form-item label="投诉景点">
        <el-select v-model="formInline.rid" placeholder="请选择投诉景点" clearable >
          <el-option v-for="item in routeList" :label="item.rname" :value="item.id" :key="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
      </el-form-item>
    </el-form>


    <!--列表-->
    <el-table size="small" :data="userData" highlight-current-row v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
      <el-table-column label="序号" type="index" width="80" align="center">
        <template slot-scope="scope">
          {{(formInline.page - 1) * formInline.limit + scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column align="center"  prop="name" label="投诉人姓名" >
      </el-table-column>
      <el-table-column align="center"  prop="rname" label="投诉景点" >
      </el-table-column>
      <el-table-column align="center"  prop="content" label="投诉内容" >
      </el-table-column>
      <el-table-column align="center"  prop="created" label="投诉时间" >
      </el-table-column>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="deleteUser(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
  </div>
</template>

<script>
// 导入请求方法
import {suggestComplaintList,suggestComplaintDelete} from '../../api/suggestComplaint'

import Pagination from '../../components/Pagination'
import {getUserAll} from "../../api/user";
import {getRouteAll} from "../../api/route";
export default {
  data() {
    return {
      loading: false, //是显示加载
      title: '',
      // 编辑与添加
      editForm: {},
      // rules表单验证
      rules: {
      },
      editFormVisible: false,
      // 请求数据参数
      formInline: {
        page: 1,
        limit: 10,
      },
      userList:[],
      routeList:[],
      //用户数据
      userData: [],
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      },
      user:{},
      updateKey:true,
      typeKey:true
    }
  },
  // 注册组件
  components: {
    Pagination
  },

  /**
   * 数据发生改变
   */
  watch: {},

  /**
   * 创建完毕
   */
  created() {
    //获取信息
    this.getdata(this.formInline);
    this.init();
  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    init(){
      getUserAll().then(res=>{
        this.userList = res.data.rows
      })
      getRouteAll().then(res=>{
        this.routeList = res.data.rows
      })
    },
    // 获取数据方法
    getdata(parameter) {
      this.loading = true;
      parameter.type = '1'
      suggestComplaintList(parameter).then(res => {
        this.loading = false
        this.userData = res.data.rows
        // 分页赋值
        this.pageparm.currentPage = this.formInline.page
        this.pageparm.pageSize = this.formInline.limit
        this.pageparm.total = res.data.total
      })
    },
    // 分页插件事件
    callFather(parm) {
      this.formInline.page = parm.currentPage
      this.formInline.limit = parm.pageSize
      this.getdata(this.formInline)
    },
    //搜索事件
    search() {
      this.getdata(this.formInline)
    },

    // 删除
    deleteUser(row) {
      this.$confirm('确定要删除吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        // 删除
        suggestComplaintDelete(row.id).then(res => {
          if (res.success) {
            this.$message({
              type: 'success',
              message: '数据已删除!'
            })
            this.getdata(this.formInline)
          } else {
            this.$message({
              type: 'info',
              message: res.message
            })
          }
        }).catch(err => {
          this.loading = false
          this.$message.error('数据删除失败，请稍后再试！')
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除！'
        })
      })
    },
  }
}
</script>

<style scoped>
.user-search {
  margin-top: 20px;
}
</style>
