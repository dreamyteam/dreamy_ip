export default class Chart {
    constructor(cfg) {
        this.cfg = cfg;
        this.el = null; //表格元素
        this.title = null; //标题
        this.subTitle = null;
        this.type = null; //种类 不同图表不同规则
        this.name = null;
        this.left = null; //title 是否居中
        this.chart = null; //chart 实体
        this.url = null; //ajax地址
        this.init();
    }
    init() {
        this.el = document.getElementById(this.cfg.el);
        this.type = this.cfg.type || '';
        this.left = this.cfg.left || '';
        this.name = this.cfg.name;

        if (this.el && this.el.hasAttribute('data-fetch-url')) {
            this.url = this.el.getAttribute('data-fetch-url') + '&t=' + new Date().getTime();
            if (this.type) {
                this.checkType(this.type)
            } else {
                this.renderChart();
            }
        }
    }
    checkeType(type) {}
    renderChart() {}
    getRemoteData() {
        var self = this;
        self.chart.showLoading();
        $.ajax({
            url: self.url,
            type: 'GET',
            dataType: 'jsonp',
            jsonp: 'callback',
            success: function(result) {
                self.checkResult(result);
            }
        })
    }
    checkResult(result) {
        if (result.error_code == 0) {
            this.updateChart(result.data)
        } else {
            this.showError(result.error_msg)
        }
    }
    updateChart(data) {}
    showError(errMsg) {}
}
