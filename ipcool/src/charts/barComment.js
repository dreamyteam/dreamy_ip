import Chart from './baseChart.js'

export default class barCommit extends Chart {
    renderChart() {
        this.chart = echarts.init(this.el);
        let optionBasic = {
            grid: {
                left: 65,
                top: 27,
                right: '10%',
                bottom: 0,
                show: false,
            },
            yAxis: {
                type: 'category',
                data: [],
                splitLine: {
                    show: false,
                },
                axisTick: {
                    show: false,
                },
                axisLine: {
                    show: false,
                    onZero: true,
                },
                axisLabel: {
                    textStyle: {
                        color: '#979797',
                        fontSize: 14
                    }
                }
            },
            xAxis: {
                type: 'value',
                axisLine: {
                    show: false,
                },
                splitLine: {
                    show: false,
                },
                axisTick: {
                    show: false,
                }
            },
            series: [{
                name: this.name,
                type: 'bar',
                data: [],
                barWidth: 16,
                label: {
                    normal: {
                        show: true,
                        position: 'right',
                        textStyle: {
                            color: '#979797',
                            fontSize: 14
                        }
                    }
                }
            }],
            color: ['#84D2CD'],
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
        let option = {
            yAxis: {
                data: [data[0].name, data[1].name, data[2].name, data[3].name]
            },
            series: [{
                data: [data[0].value, data[1].value, data[2].value, data[3].value],
            }],
        }
        this.chart.setOption(option);
    }
}
