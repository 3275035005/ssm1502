/**
* 左边菜单
*/
<template>
  <el-menu default-active="2" :collapse="collapsed" collapse-transition router :default-active="$route.path" unique-opened class="el-menu-vertical-demo" background-color="#334157" text-color="#fff" active-text-color="#ffd04b">
    <div class="logobox">
      <!-- <img class="logoimg" src="../assets/img/logo.png" alt="">-->
      旅游信息分享管理系统
    </div>
    <el-submenu v-for="menu in allmenu" :key="menu.menuid" :index="menu.menuname">
      <template slot="title">
        <i class="iconfont" :class="menu.icon"></i>
        <span>{{menu.menuname}}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item v-for="chmenu in menu.menus" :index="'/'+chmenu.url" :key="chmenu.menuid">
          <i class="iconfont" :class="chmenu.icon"></i>
          <span>{{chmenu.menuname}}</span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>
<script>
export default {
  name: 'leftnav',
  data() {
    return {
      collapsed: false,
      allmenu: []
    }
  },
  // 创建完毕状态(里面是操作)
  created() {
    this.allmenu = [
      {
        menuid: 2,
        icon: 'li-icon-shangchengxitongtubiaozitihuayuanwenjian91',
        menuname: '景点信息管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 3,
            icon: 'icon-provider-manage',
            menuname: '景点信息',
            hasThird: 'N',
            url: 'route/route',
            menus: null
          },
          {
            menuid: 101,
            icon: 'icon-provider-manage',
            menuname: '景点建议',
            hasThird: 'N',
            url: 'route/suggest',
            menus: null
          },
          {
            menuid: 102,
            icon: 'icon-provider-manage',
            menuname: '景点投诉',
            hasThird: 'N',
            url: 'route/complaint',
            menus: null
          }
        ]
      },
      {
        menuid: 200,
        icon: 'li-icon-shangchengxitongtubiaozitihuayuanwenjian91',
        menuname: '攻略管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 201,
            icon: 'icon-provider-manage',
            menuname: '攻略信息',
            hasThird: 'N',
            url: 'strategy/strategy',
            menus: null
          }
        ]
      },
      {
        menuid: 300,
        icon: 'li-icon-shangchengxitongtubiaozitihuayuanwenjian91',
        menuname: '订单管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 301,
            icon: 'icon-provider-manage',
            menuname: '订单信息',
            hasThird: 'N',
            url: 'order/order',
            menus: null
          }
        ]
      },
      {
        menuid: 5,
        icon: 'li-icon-xiangmuguanli',
        menuname: '收藏管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 6,
            icon: 'icon-cat-skuQuery',
            menuname: '收藏信息',
            hasThird: 'N',
            url: 'favorite/favorite',
            menus: null
          }
        ]
      },
      {
        menuid: 7,
        icon: 'li-icon-xiangmuguanli',
        menuname: '公告管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 8,
            icon: 'icon-cat-skuQuery',
            menuname: '公告信息',
            hasThird: 'N',
            url: 'announcement/announcement',
            menus: null
          }
        ]
      },
      {
        menuid: 9,
        icon: 'li-icon-xiangmuguanli',
        menuname: '留言管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 10,
            icon: 'icon-cat-skuQuery',
            menuname: '留言信息',
            hasThird: 'N',
            url: 'leave/leave',
            menus: null
          }
        ]
      },
      {
        menuid: 11,
        icon: 'li-icon-xiangmuguanli',
        menuname: '评论管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 12,
            icon: 'icon-cat-skuQuery',
            menuname: '评论信息',
            hasThird: 'N',
            url: 'evaluate/evaluate',
            menus: null
          }
        ]
      },
      {
        menuid: 60,
        icon: 'icon-news-manage',
        menuname: '系统管理',
        hasThird: null,
        url: null,
        menus: [

          {
            menuid: 61,
            icon: 'icon-news-manage',
            menuname: '管理员信息',
            hasThird: 'N',
            url: 'system/admin',
            menus: null
          },
          {
            menuid: 62,
            icon: 'icon-news-manage',
            menuname: '用户信息',
            hasThird: 'N',
            url: 'system/user',
            menus: null
          },
          {
            menuid: 63,
            icon: 'icon-provider-manage',
            menuname: '个人信息修改',
            hasThird: 'N',
            url: 'personage/personage',
            menus: null
          }
        ]
      }
    ]
    // 监听
    this.$root.Bus.$on('toggle', value => {
      this.collapsed = !value
    })
  }
}
</script>
<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 240px;
  min-height: 400px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  border: none;
  text-align: left;
}
.el-menu-item-group__title {
  padding: 0px;
}
.el-menu-bg {
  background-color: #1f2d3d !important;
}
.el-menu {
  border: none;
}
.logobox {
  height: 40px;
  font-weight: bold;
  line-height: 40px;
  color: #9d9d9d;
  font-size: 20px;
  text-align: center;
  padding: 20px 0px;
}
.logoimg {
  height: 40px;
}
</style>
