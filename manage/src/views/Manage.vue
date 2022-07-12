<template>
  <div>
    <el-container style="min-height: 100vh">
      <el-aside :width="sideWidth + 'px'" style="background-color: rgb(48, 65, 86); box-shadow: 2px 0 6px rgb(0 21 21 / 35%);">
        <Aside :isCollapse="isCollapse"/>
      </el-aside>
  
      <el-container>
        <el-header style="border-bottom: 1px solid #ccc; box-shadow: 0px 1px 3px #ccc; ">
          <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse" :user="user" />
        </el-header>
        
        <el-main>
          <router-view @refreshUser="getUser"/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import Aside from "@/components/Aside";
  import Header from "@/components/Header";
  export default {
    name: 'Manage',
    data() {
      return {
        collapseBtnClass:'el-icon-s-fold',
        isCollapse: false,
        sideWidth: 250,
        user: {},
      }
    },
    components:{
      Aside,
      Header
    },  
    created() {
      // 从后台获取最新的User数据
      this.getUser()
    },

    methods:{
      collapse(){
        this.isCollapse = !this.isCollapse
        if(this.isCollapse){
          this.sideWidth = 64
          this.collapseBtnClass = 'el-icon-s-unfold'
        }else{
          this.sideWidth = 250
          this.collapseBtnClass = 'el-icon-s-fold'
        }
      },
      getUser() {
        let username = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username : ""
        if (username) {
          // 从后台获取User数据
          this.request.get("/user/username/" + username).then(res => {
            // 重新赋值后台的最新User数据
            this.user = res.data
          })
        }
      },
    },
    
  }
</script>