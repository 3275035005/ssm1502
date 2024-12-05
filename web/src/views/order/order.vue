<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>订单信息</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="下单人姓名">
        <el-select v-model="formInline.uid" placeholder="请选择下单人姓名" clearable >
          <el-option v-for="item in userList" :label="item.name" :value="item.id" :key="item.id"/>
        </el-select>
      </el-form-item>

      <el-form-item label="购票景点">
        <el-select v-model="formInline.rid" placeholder="请选择购票景点" clearable >
          <el-option v-for="item in routeList" :label="item.rname" :value="item.id" :key="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="订单状态">
        <el-select v-model="formInline.status" clearable placeholder="请选择订单状态">
          <el-option label="购票成功" value="0" />
          <el-option label="退票成功" value="1"/>
          <el-option label="订单完成" value="2"/>
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
      <el-table-column align="center"  prop="name" label="下单人姓名" >
      </el-table-column>
      <el-table-column align="center"  prop="rname" label="购票景点" >
      </el-table-column>
      <el-table-column align="center"  prop="price" label="价格" >
      </el-table-column>
      <el-table-column align="center" min-width="120" label="订单状态" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status=='0'" type="success">购票成功</el-tag>
          <el-tag v-if="scope.row.status=='1'" type="warning">退票成功</el-tag>
          <el-tag v-if="scope.row.status=='2'" type="success">订单完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center"  prop="created" label="购票时间" >
      </el-table-column>
      <el-table-column label="操作" width="300">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">处理订单</el-button>
          <el-button size="mini" type="danger" @click="deleteUser(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>

    <!-- 编辑界面 -->
    <el-dialog
      :title="title"
      :visible.sync="editFormVisible"
      width="25%"
      @click='closeDialog("edit")'>
      <el-form label-width="120px" ref="editForm" :model="editForm" :rules="rules">
        <el-form-item label="下单人姓名" prop="name">
          <el-input size="small" v-model="editForm.name" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="购票景点" prop="rname">
          <el-input size="small" v-model="editForm.rname" :disabled="true" auto-complete="off" placeholder="请输入标题"></el-input>
        </el-form-item>
        <el-form-item label="订单状态">
          <el-select v-model="editForm.status" clearable placeholder="请选择订单状态">
            <el-option label="购票成功" value="0" />
            <el-option label="退票成功" value="1"/>
            <el-option label="订单完成" value="2"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click='closeDialog'>取消</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// 导入请求方法
import {orderList,orderDelete, orderUpdate} from '../../api/order'

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
      orderList(parameter).then(res => {
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
    //显示编辑界面
    handleEdit: function(row) {
      if(row){
        this.title = '处理订单';
        this.editForm = row;
      }
      this.editFormVisible = true;

    },
    // 编辑、添加提交方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          if(this.editForm.id){
            orderUpdate(this.editForm).then(res=>{
              this.$message({
                type: 'success',
                message: '处理订单成功!'
              })
              this.editFormVisible = false;
              this.getdata(this.formInline)
            })
          }

        } else {
          return false
        }
      })
    },
    // 关闭编辑、增加弹出框
    closeDialog() {
      this.editFormVisible = false
    },


    // 删除
    deleteUser(row) {
      this.$confirm('确定要删除吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        // 删除
        orderDelete(row.id).then(res => {
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
