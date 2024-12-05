<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>旅游攻略路线信息</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="攻略标题">
        <el-input clearable size="small" v-model="formInline.title" placeholder="请输入攻略标题"></el-input>
      </el-form-item>
      <el-form-item label="推荐路线">
        <el-select v-model="formInline.isThemeTour" clearable placeholder="请选择是否为推荐路线">
          <el-option label="是" value="1" />
          <el-option label="否" value="0"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
          <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()">添加</el-button>
      </el-form-item>
    </el-form>


    <!--列表-->
    <el-table size="small" :data="userData" highlight-current-row v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
      <el-table-column label="序号" type="index" width="80" align="center">
        <template slot-scope="scope">
            {{(formInline.page - 1) * formInline.limit + scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column align="center" sortable prop="titles" label="攻略标题" min-width="120">
      </el-table-column>
      <el-table-column prop="image" label="封面">
        <template slot-scope="scope">
          <el-image
            style="width: 50px; height: 50px"
            :src="scope.row.image">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column align="center" sortable prop="strategyIntroduce" label="攻略介绍" min-width="120">
      </el-table-column>
      <el-table-column align="center" sortable label="推荐路线" min-width="120">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isThemeTour=='1'" type="success">是</el-tag>
          <el-tag v-if="scope.row.isThemeTour=='0'" type="warning">否</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" sortable prop="created" label="发布时间" min-width="120">
      </el-table-column>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
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
    width="50%"
    @click='closeDialog("edit")'>
      <el-form label-width="120px" ref="editForm" :model="editForm" :rules="rules">

        <el-form-item label="攻略标题" prop="title">
          <el-input type="textarea"
                    :rows="5" size="small" v-model="editForm.title" auto-complete="off" placeholder="请输入攻略标题"></el-input>
        </el-form-item>
        <el-form-item label="攻略景点路线">
          <el-select v-model="editForm.routeIdList" placeholder="请选择攻略景点路线" multiple clearable >
            <el-option v-for="item in routeList" :label="item.rname" :value="item.id" :key="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="封面">
          <el-upload
            ref="upload"
            :file-list="fileList"
            action="http://localhost:5888/files/file"
            :on-success="fileHandleSuccess"
            accept="image/*"
            class="avatar-uploader"
          >
            <img v-if="editForm.image" :src="editForm.image" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="攻略介绍" prop="strategyIntroduce">
          <el-input size="small" v-model="editForm.strategyIntroduce" type="textarea"
                    :rows="8" auto-complete="off" placeholder="请输入攻略介绍"></el-input>
        </el-form-item>
        <el-form-item label="推荐路线" prop="isThemeTour">
          <el-select v-model="editForm.isThemeTour" placeholder="请选择推荐路线">
            <el-option  label="是" value="1"/>
            <el-option  label="否" value="0"/>
          </el-select>
        </el-form-item>
        <el-form-item label="攻略须知" prop="content">
          <tinymce v-if="editFormVisible" :height="300" v-model="editForm.content"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click='closeDialog'>取消</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// 导入请求方法
import {strategyList, strategyDelete, strategyUpdate, strategySave} from '../../api/strategy'
import Pagination from '../../components/Pagination.vue'
import Tinymce from '@/components/Tinymce'
import {getRouteAll} from "../../api/route";
export default {
  // 注册组件
  components: {
    Pagination, Tinymce
  },
  data() {
    return {
      loading: false, //是显示加载
      title: '',
      // 编辑与添加
      editForm: {},
      userList:[],
      fileList:[],
      // rules表单验证
      rules: {
        name: [
          { required: true, message: '请输入攻略名称', trigger: 'blur' }
        ],
        isThemeTour: [
          { required: true, message: '请选择推荐路线', trigger: 'blur' }
        ]
      },
      editFormVisible: false,
      // 请求数据参数
      formInline: {
        page: 1,
        limit: 10,
      },
      //用户数据
      userData: [],
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      },
      user:{},
      routeList:[],
    }
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
   this.getdata(this.formInline)
    this.getRouteList();
  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    getRouteList(){
      getRouteAll().then(res=>{
        this.routeList = res.data.rows
      })
    },
    // 获取数据方法
    getdata(parameter) {
      this.loading = true
      // 获取用户列表
      strategyList(parameter).then(res => {
        console.log(res);
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
    fileHandleSuccess(response) {
      this.editForm.image= response.data.row;
    },
    //搜索事件
    search() {
      this.getdata(this.formInline)
    },
    //显示编辑界面
    handleEdit: function(row) {
      if(row){
        this.title = '修改攻略信息';
        this.editForm = row;
      }else{
        this.title = '添加攻略信息';
        this.editForm = {
          image:''
        }
      }
      this.editFormVisible = true;

    },

    // 编辑、添加提交方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          if(this.editForm.id){
            strategyUpdate(this.editForm).then(res=>{
              this.$message({
                type: 'success',
                message: '修改成功!'
              })
              this.editFormVisible = false;
              this.getdata(this.formInline)
            })
          }else{
            strategySave(this.editForm).then(res=>{
              this.$message({
                type: 'success',
                message: '添加成功!'
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
        strategyDelete(row.id).then(res => {
          if (res.success) {
            this.$message({
              type: 'success',
              message: '数据已删除!'
            })
            this.getdata(this.formInline)
          } else {
            this.$message({
              type: 'info',
              message: res.errMsg
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

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 148px;
  height: 148px;
  line-height: 148px;
  text-align: center;
}
.avatar {
  width: 148px;
  height: 148px;
  display: block;
}
</style>
