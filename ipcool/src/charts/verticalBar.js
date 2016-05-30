// var echarts = require('echarts');

function Chart(cfg) {
    this.cfg = cfg;
    this.el = null;
    this.titleText = null; // 标题 由type决定
    this.subTitle = null; //如果是sex图表 计算得出
    this.name = null; // ip名称 
    this.type = null; // 类型 'age' 'vote'
    this.left = null; //  标题是否剧中 'center' 'left'
    this.chart = null; // 图表实例
    this.url = null; //ajax 请求地址
    this.init();
}
Chart.prototype = {
    init: function() {
        this.el = document.getElementById(this.cfg.el);
        this.type = this.cfg.type;
        if (this.type == 'age') {
            this.titleText = '受众年龄分布';
        } else if (this.type == 'social') {
            this.titleText = '投票结果';
        }
        this.name = this.cfg.name;
        this.left = this.cfg.left || 'center';
        if (this.el && this.el.hasAttribute('data-fetch-url')) {
            this.url = this.el.getAttribute('data-fetch-url') + '&t=' + new Date().getTime();
            this.renderChart();
        }
        console.log(this.url)
    },
    renderChart: function() {
        this.chart = echarts.init(this.el);
        optionBasic = {
            title: {
                text: this.titleText,
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
                    var average = params[0];
                    var curIp = params[1];
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
        if (this.url) {
            this.update();
        }
    },
    getSubText: function(resArr) {
        var maxAge = Math.max.apply(null, resArr);
        var titleArr = ['19岁以下', '20-29', '30-39', '40-49', '49以上'];
        for (var i = 0; i < resArr.length; i++) {
            if (resArr[i] == maxAge) {
                var subscript = i;
            }
        }
        return '主要受众年龄段为[' + titleArr[subscript] + ']'
    },
    update: function() {
        this.chart.showLoading();
        var self = this;
        $.ajax({
            url: self.url,
            type: 'GET',
            dataType: 'jsonp',
            jsonp: 'callback',
            success: function(result) {
                if (result.error_code == 0) {
                    self.chart.hideLoading();
                    if (self.type == 'age') {
                        self.subTitle = self.getSubText(result.data[1].value);
                    }
                    var option = {
                        title: {
                            subtext: self.subTitle,
                        },
                        legend: {
                            data: ['平均分布', self.name]
                        },
                        series: [{
                            name: '平均分布',
                            type: 'bar',
                            data: result.data[0].value,
                            itemStyle: {
                                emphasis: {
                                    color: '#EEE'
                                }
                            }
                        }, {
                            name: self.name,
                            type: 'bar',
                            barGap: '-50%',
                            z: 3,
                            data: result.data[1].value
                        }]
                    }
                    self.chart.setOption(option);
                }
            },
            error: function(msg) {
                console.log(msg);
            }
        })
    }
}


module.exports = Chart;
