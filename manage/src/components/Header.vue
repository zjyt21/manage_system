<template>
  <div style="font-size: 12px;line-height: 60px; display: flex;">
    <div style="flex: 1; font-size: 18px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>

      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px;">
        <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ this.$route.meta.name }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <el-dropdown style="width: 100px; cursor: pointer">
      <div>
        <img src="../assets/logo.png" alt=""
             style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px">
        <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      </div>
      
      <el-dropdown-menu slot="dropdown" style="widows: 100px; text-align: center;">
        <el-dropdown-item style="font-size: 14px; padding: 5px 0;" class="el-icon-user-solid">个人信息</el-dropdown-item>
        <br>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0;" class="el-icon-right">
          <router-link to="/login" style="text-decoration: none;">退出</router-link>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
  export default {
    name:"Header",

    props:{
      collapseBtnClass:String,
      collapse:Function
    },
  }
</script>

<style>

</style>