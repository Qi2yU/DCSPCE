<template>
    <div>
        <p>系统内存检测</p>
        <el-table :data="memData" style="width: 100%;">
            <el-table-column label="已使用" prop="memUse"></el-table-column>
            <el-table-column label="未使用" prop="nonmem"></el-table-column>
        </el-table>
    </div>
</template>

<script>
    var $this = {};
    export default {
        data() {
            return {
                memData: [],
            };
        },
        mounted() {
            this.fetchChartData();
        },
        methods: {
            fetchChartData() {
                this.$http({
                    url: '/government-pro/getSystemInfo',
                    method: 'get',
                    headers:
                    {
                        'Content-Type': 'application/json'
                    }
                }).then(response => {
                    this.memData = response.data;
                    // const values = response.data;
                    // // const values = [100, 200];
                    // console.log(values);
                    // this.chartData.forEach((item, index) => {
                    //     if(values[index]) {
                    //         item.value = Number(values[index]);
                    //     }
                    // });
                    // console.log(this.chartData);
                });
            },
        },
    };
</script>