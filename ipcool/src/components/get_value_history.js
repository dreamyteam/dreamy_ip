export default class GetHistory {
    constructor(el, type) {
        this.el = el;
        this.type = type;
        this.trendEL = null; //趋势箭头
        this.historyDateEL = null; //历史最高日期元素
        this.historyValueEL = null; //历史最高日期值元素
        this.bookId = null;
        this.url = null;
        this.init();
    }
    init() {
        this.trendEL = this.el.find(".arrow");
        this.historyDateEL = this.el.find('.highest_history_date');
        this.historyValueEL = this.el.find('.highest_histroy_value');
        this.bookId = $('#bookId').val();
        (this.el.length > 0) && this.getRemoteData();
    }
    getRemoteData() {
        var self = this;
        this.url = '/index/historyTrend?bookId=' + this.bookId + '&type=' + this.type + '&t=' + new Date().getTime();
        $.ajax({
            url: this.url,
            type: 'GET',
            dataType: 'jsonp',
            jsonp: 'callback',
            success: function(result) {
                if (result.error_code == 0) {
                    self.renderUI(result.data);
                }
                //TODO 处理error_code
            }
        })
    }
    renderUI(data) {
        //处理箭头
        switch (data.current_index_trend) {
            case "0":
                this.trendEL.html('-');
                break;
            case "1":
                this.trendEL.html('↑');
                break;
            case "2":
                this.trendEL.html('↓');
                break;
            default:
                this.trendEL.html('-');
                break;
        }
        //处理日期
        this.historyDateEL.html(data.history_top_date.replace(/-/g,'/'));
        //处理数值
        this.historyValueEL.html(data.history_top_value);
    }
}
