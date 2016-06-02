import Chart from './baseChart.js'

export default class Radar extends Chart {
    renderChart() {
        this.chart = echarts.init(this.el);
        var option = {
            tooltip: {
                show: true,
            },
            radar: {
                nameGap: 10,
                name: {
                    textStyle: {
                        color: '#000',
                        fontSize: 14,
                    },
                },
                indicator: [
                    { name: '热度', max: 10000 },
                    { name: '开发空间', max: 10000 },
                    { name: '传播', max: 10000 },
                    { name: '口碑', max: 10000 },
                    { name: '消费能力', max: 10000 },
                ],
                splitArea: {
                    areaStyle: {
                        color: ['rgba(250,250,250,0)'],
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: '#d8d8d8',
                    }
                }
            },
            series: [{
                name: this.name,
                type: 'radar',
                symbol: 'circle',
                symbolSize: 8,
                lineStyle: {
                    normal: {
                        width: 3
                    }
                },
                areaStyle: {
                    normal: {
                        color: '#00A69D',
                        opacity: 0.2
                    }
                },
                data: [{
                    value: [],
                    name: this.name
                }]
            }],
            color: ['#00A69D'],
            animation: false,
            textStyle: {
                fontFamily: 'pingfang SC'
            }
        }
        this.chart.setOption(option);
        this.url && this.getRemoteData();
    }
    updateChart(data) {
    	this.chart.hideLoading();
        var option = {
            series: [{
                data: [{
                    value:data.value,
                    name:this.name
                }]
            }]
        }
        this.chart.setOption(option);
    }
}
