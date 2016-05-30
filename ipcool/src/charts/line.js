import Chart from './baseChart.js'

export default class Line extends Chart {
    renderChart() {
        this.chart = echarts.init(this.el);
        if (this.type == "more") {
            var datazom = [{
                type: 'inside',
                start: 70,
                end: 100
            }, {
                start: 70,
                end: 100
            }]
            var bottom = '8%';
        } else {
            var datazom = [];
            var bottom = '0%';
        }
        var optionBasic = {
            tooltip: {
                trigger: 'axis',
                position: 'top',
                padding: 10,
                axisPointer: {
                    type: 'line',
                    lineStyle: {
                        width: 0.5,
                        color: '#00A69D'
                    }
                }
            },
            dataZoom: datazom,
            grid: {
                top: '5%',
                left: '0%',
                right: '2%',
                bottom: bottom,
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                axisLine: {
                    lineStyle: {
                        color: '#EFEFEF',
                    }
                },
                axisLabel: {
                    show: false,
                },
                splitLine: {
                    interval: 2, //间隔x坐标轴线
                    lineStyle: {
                        color: '#EFEFEF',
                    }
                },
                axisTick: {
                    show: false,
                },
                boundaryGap: false,
                data: []
            }],
            yAxis: [{
                type: 'value',
                axisLine: {
                    lineStyle: {
                        color: '#EFEFEF',
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: '#EFEFEF',
                    }
                },
                axisTick: {
                    show: false,
                },
            }],
            series: [{
                name: this.name,
                type: 'line',
                symbolSize: 6,
                lineStyle: {
                    normal: {
                        color: '#00A69D',
                        width: 2,
                    },
                },
                areaStyle: {
                    normal: {
                        color: '#00A69D',
                        opacity: 0.2
                    }
                },
                data: []
            }],
            color: ['#00A69D'],
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
            xAxis: [{
                data: data.date
            }],
            series: [{
                data: data.data
            }]
        }
        this.chart.setOption(option);
    }
}