<template>
  <el-menu :default-openeds="['1']" style="min-height: 100%; overflow-x:hidden" 
    background-color="rgb(48, 65, 86)"
    text-color="#fff"
    active-text-color="#ffd04b"
    :collapse-transition="false"
    :collapse="isCollapse"
    router>
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; margin-right: 5px;">
      <b style="color: white;" v-show="!isCollapse">后台管理系统</b>
    </div>
    <el-menu-item index="/">
      <i class="el-icon-house"></i>
      <template slot="title">
        <span>主页</span>
      </template>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span>系统管理</span>
      </template>
      <el-menu-item index="/user">
        <i class="el-icon-s-custom"></i>
        <template slot="title">
            <span>用户管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/file">
        <i class="el-icon-document"></i>
        <template slot="title">
          <span>文件管理</span>
        </template>
      </el-menu-item>
    </el-submenu>
    
  </el-menu>
</template>

<script>
  export default {
    name:"Aside",
    
    props:{
        isCollapse:Boolean
    }
  }
</script>

<style>

</style>