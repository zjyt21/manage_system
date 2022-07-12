<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 40px;">
      <el-col :span="6">
        <el-card style="color: #409EFF;">
          <div><i class="el-icon-user-solid"></i> 用户总数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold;">
            100
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #F56C6C;">
          <div ><i class="el-icon-money"></i>销售总量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold;">
            ￥100000
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #E6C23A;">
          <div ><i class="el-icon-bank-card"></i>收益总额</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold;">
            ￥30000
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #E6A23C;">
          <div ><i class="el-icon-s-shop"></i>门店总数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold;">
            20
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-card id="main" style="width: 500px; height: 450px;"></el-card>
      </el-col>
      <el-col :span="12">
        <el-card id="pie" style="width: 500px; height: 450px;"></el-card>
      </el-col>
    </el-row>
    
    
  </div>
</template>

<script>
  import * as echarts from 'echarts';
  export default {
    name:"Home",
    data() {
      return {

      }
    },
    mounted() {
      //绑定和初始化
      var chartDom = document.getElementById('main');
      var myChart = echarts.init(chartDom);

      var pieDom = document.getElementById('pie');
      var pieChart = echarts.init(pieDom);

      //折线图和柱状图
      var option = {
        title: {
          text: '各季度会员数量统计',
          subtext: '趋势图',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        xAxis: {
          type: 'category',
          data: ["第一季度", "第二季度", "第三季度", "第四季度"]
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name:"星巴克",
            data: [],
            type: 'line'
          },
          {
            name:"瑞辛咖啡",
            data: [],
            type: 'line'
          },
          {
            name:"星巴克",
            data: [],
            type: 'bar'
          },
          {
            name:"瑞辛咖啡",
            data: [],
            type: 'bar'
          },
        ]
      };
      
      //饼图
      var pieOption = {
        title: {
          text: '各季度会员数量统计',
          subtext: '比例图',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '星巴克',
            type: 'pie',
            radius: '50%',
            data: [],
            label:{            //饼图图形上的文本标签
              normal:{
                show:true,
                position:'inner', //标签的位置
                textStyle : {
                  fontWeight : 300 ,
                  fontSize : 14,    //文字的字体大小
                  color: "#fff"
                },
                formatter:'{d}%'
              }
            },
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      
      //填入数据
      this.request.get("/echarts/members").then(res => {
        option.series[0].data = res.data
        option.series[1].data = [5,6,7,8]
        option.series[2].data = res.data
        option.series[3].data = [5,6,7,8]
        myChart.setOption(option);

        pieOption.series[0].data = [
          {name: "第一季度", value: res.data[0]},
          {name: "第二季度", value: res.data[1]},
          {name: "第三季度", value: res.data[2]},
          {name: "第四季度", value: res.data[3]},
        ]
        pieChart.setOption(pieOption)
      })
      
    },
  }
</script>

<style>

</style>