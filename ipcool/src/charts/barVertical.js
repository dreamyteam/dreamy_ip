import Chart from './baseChart.js'

export default class barVertical extends Chart {
    checkType(type) {
        if (type == 'age') {
            this.title = '受众年龄分布';
        } else if (type == '') {
            this.title = '';
        }
        this.renderChart();
    }
    renderChart() {
        this.chart = echarts.init(this.el);
        let optionBasic = {
            title: {
                text: this.title,
                subtext: '',
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
                trigger: 'axis',
                formatter: function(params) {
                    let average = params[0];
                    let curIp = params[1];
                    return average.seriesName + ' : ' + Math.floor(average.value * 100) + '%<br/>' + curIp.seriesName + ' : ' + Math.floor(curIp.value * 100) + '%';
                },
                axisPointer: {
                    type: 'shadow',
                    shadowStyle: {
                        opacity: 0.3
                    }
                }
            },
            legend: {
                top: 50,
                data: []
            },
            grid: {
                top: 70,
                show: false,
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                    show: false,
                },
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
            },
            xAxis: {
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: '#d8d8d8',
                        width: 1,
                    }
                },
                splitLine: {
                    show: false,
                },
                axisTick: {
                    show: false,
                },
                type: 'category',
                data: ['19岁以下', '20-29', '30-39', '40-49', '49以上']
            },
            series: [],
            color: ['#EEEEEE', '#84D2CD'],
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
        if (this.type == 'age') {
            this.subTitle = this.caculateSubTitle(data[0].value);
        }
        let option = {
            title: {
                subtext: this.subTitle,
            },
            legend: {
                data: ['平均分布', this.name]
            },
            series: [{
                name: '平均分布',
                type: 'bar',
                data: data[1].value,
                itemStyle: {
                    emphasis: {
                        color: '#EEE'
                    }
                }
            }, {
                name: this.name,
                type: 'bar',
                barGap: '-50%',
                z: 3,
                data: data[0].value
            }]
        }
        this.chart.setOption(option);
    }
    caculateSubTitle(ageArr) {
        let maxAge = Math.max.apply(null, ageArr);
        let titleArr = ['19岁以下', '20-29', '30-39', '40-49', '49以上'];
        for (let i = 0; i < ageArr.length; i++) {
            if (ageArr[i] == maxAge) {
                var subscript = i;
            }
        }
        return '主要受众年龄段为[' + titleArr[subscript] + ']'
    }
}
