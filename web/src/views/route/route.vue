<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>景点路线信息</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="景点名称">
        <el-input clearable size="small" v-model="formInline.rname" placeholder="请输入景点名称"></el-input>
      </el-form-item>
      <el-form-item label="发布状态">
        <el-select v-model="formInline.rflag" clearable placeholder="请选择发布状态">
          <el-option label="已发布" value="1" />
          <el-option label="未发布" value="0"/>
        </el-select>
      </el-form-item>
      <el-form-item label="推荐状态">
        <el-select v-model="formInline.isThemeTour" clearable placeholder="请选择推荐状态">
          <el-option label="已推荐" value="1" />
          <el-option label="未推荐" value="0"/>
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
      <el-table-column align="center" sortable prop="rname" label="景点名称" min-width="120">
      </el-table-column>
      <el-table-column prop="rimage" label="景点封面">
        <template slot-scope="scope">
          <el-image
            style="width: 50px; height: 50px"
            :src="scope.row.rimage">

          </el-image>
        </template>
      </el-table-column>
      <el-table-column align="center" sortable prop="routeIntroduce" label="景点介绍" min-width="120">
      </el-table-column>
      <el-table-column align="center" sortable prop="price" label="景点收费" min-width="120">
      </el-table-column>
      <el-table-column align="center" sortable prop="count" label="热度值" min-width="120">
      </el-table-column>
      <el-table-column align="center" sortable prop="phone" label="咨询电话" min-width="120">
      </el-table-column>
      <el-table-column align="center" sortable prop="address" label="景点地址" min-width="120">
      </el-table-column>
      <el-table-column align="center" sortable label="发布状态" min-width="120">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.rflag==1" type="success">已发布</el-tag>
          <el-tag v-if="scope.row.rflag==0" type="warning">未发布</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" sortable label="推荐状态" min-width="120">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isThemeTour==1" type="success">已推荐</el-tag>
          <el-tag v-if="scope.row.isThemeTour==0" type="warning">未推荐</el-tag>
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

        <el-form-item label="景点名称" prop="rname">
          <el-input type="textarea"
                    :rows="5" size="small" v-model="editForm.rname" auto-complete="off" placeholder="请输入景点名称"></el-input>
        </el-form-item>
        <el-form-item label="景点封面">
          <el-upload
            ref="upload"
            :file-list="fileList"
            action="http://localhost:5888/files/file"
            :on-success="fileHandleSuccess"
            accept="image/*"
            class="avatar-uploader"
          >
            <img  v-if="editForm.rimage" :src="editForm.rimage" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="景点介绍" prop="routeIntroduce">
          <el-input size="small" v-model="editForm.routeIntroduce" type="textarea"
                    :rows="5" auto-complete="off" placeholder="请输入景点介绍"></el-input>
        </el-form-item>
        <el-form-item label="景点收费" prop="price">
          <el-input size="small" v-model="editForm.price" type="number" auto-complete="off" placeholder="请输入景点收费"></el-input>
        </el-form-item>
        <el-form-item label="热度值" prop="count">
          <el-input size="small" v-model="editForm.count" type="number" auto-complete="off" placeholder="请输入热度值"></el-input>
        </el-form-item>
        <el-form-item label="咨询电话" prop="phone">
          <el-input size="small" v-model="editForm.phone" auto-complete="off" placeholder="请输入咨询电话"></el-input>
        </el-form-item>
        <el-form-item label="景点地市" prop="address">
          <el-input size="small" v-model="editForm.address" auto-complete="off" placeholder="请输入景点地市"></el-input>
        </el-form-item>
        <el-form-item label="发布状态" prop="rflag">
          <el-select v-model="editForm.rflag" placeholder="请选择发布状态">
            <el-option  label="已发布" value="1"/>
            <el-option  label="未发布" value="0"/>
          </el-select>
        </el-form-item>
        <el-form-item label="推荐状态" prop="isThemeTour">
          <el-select v-model="editForm.isThemeTour" placeholder="请选择推荐状态">
            <el-option  label="已推荐" value="1"/>
            <el-option  label="未推荐" value="0"/>
          </el-select>
        </el-form-item>
        <el-form-item label="景点须知" prop="content">
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
import {routeList, routeDelete, routeUpdate, routeSave} from '../../api/route'
import Pagination from '../../components/Pagination'
import Tinymce from '@/components/Tinymce'
export default {
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
        rname: [
          { required: true, message: '请输入景点名称', trigger: 'blur' }
        ],
        routeIntroduce: [
          { required: true, message: '请输入景点介绍', trigger: 'blur' }
        ],
        price: [
          { required: true, message: '请输入景点收费', trigger: 'blur' }
        ],
        count: [
          { required: true, message: '请输入热度值', trigger: 'blur' }
        ],
        rflag: [
          { required: true, message: '请选择发布状态', trigger: 'blur' }
        ],
        isThemeTour: [
          { required: true, message: '请选择推荐状态', trigger: 'blur' }
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
    }
  },
  // 注册组件
  components: {
    Pagination, Tinymce
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
  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    // 获取数据方法
    getdata(parameter) {
      this.loading = true
      // 获取用户列表
      routeList(parameter).then(res => {
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
      this.editForm.rimage= response.data.row;
      this.$refs.upload.clearFiles();
    },
    //搜索事件
    search() {
      this.getdata(this.formInline)
    },
    //显示编辑界面
    handleEdit: function(row) {
      if(row){
        this.title = '修改景点信息';
        this.editForm = row;
      }else{
        this.title = '添加景点信息';
        this.editForm = {
          rimage:''
        }
      }
      this.editFormVisible = true;

    },

    // 编辑、添加提交方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          if(this.editForm.id){
            routeUpdate(this.editForm).then(res=>{
              this.$message({
                type: 'success',
                message: '修改成功!'
              })
              this.editFormVisible = false;
              this.getdata(this.formInline)
            })
          }else{
            routeSave(this.editForm).then(res=>{
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
        routeDelete(row.id).then(res => {
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
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
