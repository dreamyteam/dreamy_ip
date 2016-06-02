import Chart from './baseChart.js'

export default class pieDouble extends Chart {
    checkType(type) {
        if (type == 'sex') {
            this.title = '受众性别分布';
        } else if (type == 'social') {
            this.title = '社交媒体平台传播构成';
        }
        this.renderChart();
    }
    renderChart() {
        this.chart = echarts.init(this.el);
        let optionBasic = {
            title: {
                text: this.title,
                left: this.left,
                subtext: '',
                textStyle: {
                    color: '#4a4a4a',
                    fontSize: 16
                },
                subtextStyle: {
                    color: '#9b9b9b',
                    fontSize: 14
                }
            },
            legend: {
                left: 'center',
                top: 50,
                data: []
            },
            tooltip: {
                trigger: 'item',
                formatter:'{a}<br/>{b} : {d}%'
            },
            series: [{
                name: this.name,
                type: 'pie',
                startAngle: 240,
                minAngle: 50,
                radius: ['40%', '65%'],
                center: ['50%', '55%'],
                avoidLabelOverlap: true,
                label: {
                    normal: {
                        show: false,
                        position: 'center',
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: 24,
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: [
                    /*  { value: 0, name: '微信', itemStyle: { emphasis: { color: '#84d2cd' } } },
                      { value: 0, name: '微博', itemStyle: { emphasis: { color: '#EEEEEE' } } }*/
                ]
            }],
            color: ['#EEEEEE', '#84D2CD', '#84D2CD', '#EEEEEE', '#84D2CD'],
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
        if (this.type == 'sex') {
            this.subTitle = this.caculateSubTitle(data[0].value, data[1].value);
        }
        let option = {
            title: {
                subtext: this.subTitle,
            },
            legend: {
                data: [
                    { name: data[0].name, icon: 'rect' },
                    { name: data[1].name, icon: 'rect' }
                ]
            },
            series: [{
                data: [
                    { value: data[0].value, name: data[0].name, itemStyle: { emphasis: { color: '#EEEEEE' } } },
                    { value: data[1].value, name: data[1].name, itemStyle: { emphasis: { color: '#84d2cd' } } }
                ]
            }]
        }
        this.chart.setOption(option);
    }
    caculateSubTitle(male, female) {
        let total = male + female
        let malePer = (male / total) * 100;
        if (malePer > 65) {
            return '主要受众人群为男性'
        } else if (malePer > 55 && malePer <= 65) {
            return '受众用户偏向男性'
        } else if (malePer > 45 && malePer <= 55) {
            return '受众用户性别均衡'
        } else if (malePer > 35 && malePer <= 45) {
            return '受众用户偏向女性'
        } else {
            return '主要受众人群为女性'
        }
    }
}
