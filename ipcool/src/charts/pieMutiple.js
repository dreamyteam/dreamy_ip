import Chart from './baseChart.js'

export default class pieMutiple extends Chart {
    renderChart() {
        this.chart = echarts.init(this.el);
        var optionBasic = {
            title: {
                text: '在新闻媒体平台的传播构成',
                left: this.left,
                textStyle: {
                    color: '#4a4a4a',
                    fontSize: 16
                },
                subtextStyle: {
                    color: '#9b9b9b',
                    fontSize: 14
                }
            },
            tooltip: {
                trigger: 'item',
            },
            series: [{
                name: this.name,
                type: 'pie',
                startAngle: 140,
                radius: ['40%', '65%'],
                center: ['50%', '55%'],
                avoidLabelOverlap: true,
                label: {
                    normal: {
                        show: true,
                        textStyle: {
                            color: '#4a4a4a',
                            fontSize: 16,
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                itemStyle: {
                    emphasis: {
                        color: '#00a69d',
                        opacity: 0.8
                    }
                },
                data: [
                    // { value: 12, name: '凤凰' },
                    // { value: 32, name: '网易' },
                    // { value: 28, name: '虎嗅' },
                    // { value: 18, name: '腾讯' },
                    // { value: 9, name: '起点' }
                ]
            }],
            color: ['#00A69D', '#84D2CD', '#EEEEEE', '#84D2CD', '#EEEEEE'],
            animation: false,
            textStyle: {
                fontFamily: 'pingfang SC'
            }
        }
        this.chart.setOption(optionBasic);
        this.url && this.getRemoteData();
    }
    updateChart(data) {
        this.chart.hideLoading();
        var option = {
            series: [{
                data: data
            }]
        }
        this.chart.setOption(option);
    }
}
