import GetHistory from './get_value_history.js'

import LineChart from '../charts/line.js'
import RadarChart from '../charts/radar.js'
import PieChartMutiple from '../charts/pieMutiple.js'
import PieChartDouble from '../charts/pieDouble.js'
import BarChartVertical from '../charts/barVertical.js'
import BarCommit from '../charts/barComment.js'

export default class ScrollLoad {
    constructor(cfg) {
        this.cfg = cfg;
        this.els = null;
        this.isLoaded = [];
        this.history = null;
        this.chart = null;
        this.init();
    }
    init() {
        this.els = $(this.cfg.els);
        this.history = this.cfg.history;
        this.chart = this.cfg.chart;
        this.setDefaultLoaded();
        this.listenEvent();
    }
    setDefaultLoaded() { //重置所有isLoaded
        let elLength = this.els.length;
        for (let i = 0; i < elLength; i++) {
            this.isLoaded[i] = false;
        }
    }
    listenEvent() {
        let self = this;
        function onScroll() {
            let scrollTop = $(document).scrollTop(); //滚动条距离
            self.els.each(function() {
                let offsetTop = $(this).offset().top; //距离文档顶部距离
                let disTop = offsetTop - scrollTop;
                let curIndex = $(this).index(self.cfg.els);
                if (disTop < 800) {
                    if (!self.isLoaded[curIndex]) {
                        self.loadCharts($(this));
                        self.loadHistory($(this));
                        self.isLoaded[curIndex] = true;
                    }
                }
            })
        }
        $(window).on("scroll", function() { onScroll(); })
        $(window).on("load", function() { onScroll(); }) //初始化首屏
    }
    loadHistory(obj) {
        if (obj.find(this.history).length > 0) {
            let elememt = obj.find(this.history).eq(0);
            let type = elememt.data("type");
            new GetHistory(obj, type);
        }
    }
    loadCharts(obj) {
        let self = this;
        if (obj.find(this.chart).length > 0) {
            let elememts = obj.find(this.chart);
            elememts.each(function() {
                self.getChartType($(this));
            })
        }
    }
    getChartType(el) {
        let fetchData = el.data("fetchType");
        let idValue = el.attr("id");
        let ipName = fetchData.name;
        let type = fetchData.type;
        if (type == "line") {
            new LineChart({
                el: idValue,
                name: ipName
            })
        } else if (type == "radar") {
            new RadarChart({
                el: idValue,
                name: ipName
            })
        } else if (type == "PieChartMutiple") {
            new PieChartMutiple({
                el: idValue,
                name: ipName,
                left: fetchData.left
            })
        } else if (type == "PieChartDouble") {
            new PieChartDouble({
                el: idValue,
                name: ipName,
                type: fetchData.chartType,
                left: fetchData.left
            })
        } else if (type == "BarChartVertical") {
            new BarChartVertical({
                el: idValue,
                name: ipName,
                type: fetchData.chartType,
                left: fetchData.left
            })
        } else if(type == "BarCommit"){
        	new BarCommit({
        		el: idValue,
                name: ipName
        	})
        }
    }
}
