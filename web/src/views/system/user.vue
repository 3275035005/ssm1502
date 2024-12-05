<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户信息</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="用户账号">
        <el-input clearable size="small" v-model="formInline.username" placeholder="请输入用户账号"></el-input>
      </el-form-item>
      <el-form-item label="用户姓名">
        <el-input clearable size="small" v-model="formInline.name" placeholder="请输入用户姓名"></el-input>
      </el-form-item>
      <el-form-item label="用户状态">
        <el-select v-model="formInline.status" placeholder="请选择用户状态">
          <el-option  label="正常" value="1"/>
          <el-option  label="禁用" value="0"/>
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
      <el-table-column align="center" sortable prop="username" label="用户账号" min-width="120">
      </el-table-column>
      <el-table-column align="center" sortable prop="telephone" label="用户姓名" min-width="80">
      </el-table-column>
      <el-table-column align="center" sortable label="性别" min-width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.sex==0" type="success">女</el-tag>
          <el-tag v-if="scope.row.sex==1" type="warning">男</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" sortable prop="telephone" label="联系电话" min-width="120">
      </el-table-column>
      <el-table-column align="center" sortable prop="email" label="邮箱" min-width="120">
      </el-table-column>
      <el-table-column align="center" sortable prop="province" label="省份" min-width="80">
      </el-table-column>
      <el-table-column align="center" sortable prop="city" label="地市" min-width="80">
      </el-table-column>
      <el-table-column align="center" sortable label="状态" min-width="120">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status=='1'" type="success">正常</el-tag>
          <el-tag v-if="scope.row.status=='0'" type="warning">停用</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" sortable prop="created" label="注册时间" min-width="120">
      </el-table-column>

      <el-table-column align="center"  label="操作" width="180">
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
    width="25%"
    @click='closeDialog("edit")'>
      <el-form label-width="120px" ref="editForm" :model="editForm" :rules="rules">
        <el-form-item label="用户姓名" prop="name">
          <el-input size="small" v-model="editForm.name" auto-complete="off" placeholder="请输入用户姓名"></el-input>
        </el-form-item>
        <el-form-item label="用户账号" prop="username">
          <el-input :disabled="updateKey" size="small" v-model="editForm.username" auto-complete="off" placeholder="请输入用户账号"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" prop="password">
          <el-input size="small" v-model="editForm.password" type="password" auto-complete="off" placeholder="请输入用户密码"></el-input>
        </el-form-item>

        <el-form-item label="联系电话" prop="telephone">
          <el-input size="small" v-model="editForm.telephone" uto-complete="off"  placeholder="请输入联系电话"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input size="small" v-model="editForm.email" uto-complete="off"  placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="editForm.sex" placeholder="请选择性别">
            <el-option  label="男" value="1"/>
            <el-option  label="女" value="0"/>
          </el-select>
        </el-form-item>
        <el-form-item label="省份" prop="province">
          <el-input size="small" v-model="editForm.province" uto-complete="off"  placeholder="请输入省份"></el-input>
        </el-form-item>
        <el-form-item label="地市" prop="city">
          <el-input size="small" v-model="editForm.city" uto-complete="off"  placeholder="请输入地市"></el-input>
        </el-form-item>
        <el-form-item label="账号状态" prop="status">
          <el-select v-model="editForm.status" placeholder="请选择账号状态">
            <el-option  label="正常" value="1"/>
            <el-option  label="停用" value="0"/>
          </el-select>
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
import {userList,userSave,userDelete, userUpdate} from '../../api/user'
import Pagination from '../../components/Pagination'
export default {
  data() {
    return {
      loading: false, //是显示加载
      title: '',
      // 编辑与添加
      editForm: {},
      fileList:[],

      // rules表单验证
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '请选择状态', trigger: 'blur' },
        ],
        sex: [
          { required: true, message: '请输入性别', trigger: 'blur' },
        ],
        telephone: [
          { required: true, message: '请输入联系电话', trigger: 'blur' },
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
      userList(parameter).then(res => {
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
    //搜索事件
    search() {
      this.getdata(this.formInline)
    },
    //显示编辑界面
    handleEdit: function(row) {
      if(row){
        this.title = '修改用户';
        this.editForm = row;


      }else{
        this.title = '添加用户';
        this.editForm = {
          image:""
        }
        this.updateKey = false;
      }
      this.editFormVisible = true;

    },
    // 编辑、添加提交方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          if(this.editForm.id){
            userUpdate(this.editForm).then(res=>{
              this.$message({
                type: 'success',
                message: '修改成功!'
              })
              this.editFormVisible = false;
              this.getdata(this.formInline)
            })
          }else{
            userSave(this.editForm).then(res=>{
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
        userDelete(row.id).then(res => {
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
