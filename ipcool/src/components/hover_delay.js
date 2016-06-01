export default class HoverDelay {
    constructor(cfg) {
        this.cfg = cfg;
        this.el = null;
        this.target = null;
        this.init();
    }
    init() {
        this.el = $(this.cfg.el);
        this.target = $(this.cfg.target);
        this.bindUI();
    }
    bindUI() {
        let self = this;
        var timer = null;
        this.el.hover(function() {
        	self.targetShow();
            clearInterval(timer); //关键
        }, function() {
            timer = setTimeout(function() {
                self.targetHide();
            }, 1000)
        });

        this.target.hover(function() {
            clearInterval(timer);
        }, function() {
            timer = setTimeout(function() {
                self.targetHide();
            }, 1000)
        });
    }
    targetShow() {
    	let self = this;
        self.target.show();
        setTimeout(function() {
            self.target.addClass("active");
        }, 1)
    }
    targetHide(){
    	let self = this;
    	self.target.removeClass("active");
    	setTimeout(function(){
    		self.target.hide();
    	},300)
    }
}
