export default class SlideTab {
    constructor(cfg) {
        this.cfg = cfg;
        this.el = null; //容器元素
        this.box = null; //container元素
        this.prev = null; //前一个 按钮
        this.next = null; //后一个 按钮
        this.autoPlay = null;
        this.canClick = null;
        this.lis = null; //单体
        this.liWidth = null; //单个li 的宽度
        this.currentIndex = null; //当前第一个index
        this.tabContents = null; //是否有底部
        this.trigger = null;
        this.init();
    }
    init() {
        let self = this;
        this.el = $(this.cfg.el);
        this.prev = this.el.find(this.cfg.prev) || null;
        this.next = this.el.find(this.cfg.next) || null;
        this.box = this.el.find(this.cfg.box) || null;
        this.box.html(this.box.html() + this.box.html());
        this.lis = this.box.find(this.cfg.li) || null;
        this.liWidth = this.lis.eq(0).width();
        this.tabContents = this.el.find(this.cfg.tabContents) || null;
        this.trigger = this.cfg.trigger || "btn";
        this.currentIndex = 0;
        this.canClick = true;

        this.resize();
        this.bindUI();
    }
    resize() {
        // 重置容器宽度
        this.liWidth = this.lis.eq(0).width();
        let ulWidth = this.liWidth * this.lis.length;
        this.box.css({ width: ulWidth + 'px' });

        let currentOffsetLeft = this.currentIndex * this.liWidth;
        this.box.css({ left: -currentOffsetLeft + "px" })

        if (this.tabContents) {
            this.switchTabContents(this.currentIndex + 2)
        }
    }
    bindUI() {
        let self = this;

        if (this.trigger == "btn") {
            this.prev.off("click");
            this.prev.on("click", function() {
                self.checkProsition();
                self.move(-1);
            });
            this.next.off("click");
            this.next.on("click", function() {
                self.checkProsition();
                self.move(1);
            })
        } else if (this.trigger == "click") {
            this.lis.each(function() {
                $(this).on("click", function() {
                    self.checkProsition();
                    self.clickMove($(this).index())

                })
            })
        }
        $(window).on("resize", function() {
            self.resize();
        })
    }
    checkProsition() {
        let offsetLeft = this.box.position().left; //ul 的 offsetLeft
        let ulWidth = -this.box.width() / 2; //重要 宽度不可能是负数
        if (offsetLeft < ulWidth) {
            this.box.css("left", -this.liWidth + "px");
        }
        if (offsetLeft >= 0) {
            console.log("重置了")
            this.box.css("left", ulWidth + "px");
        }
    }
    move(direction) { //-1是后退 1是前进
        let self = this;
        if (this.canClick) {
            let boxLeft = this.box.position().left;
            if (direction > 0) { //后退
                var left = boxLeft - this.liWidth;
                this.currentIndex++;
                if (this.currentIndex >= 5) {
                    this.currentIndex = 0;
                }
            } else if (direction < 0) {
                var left = boxLeft + this.liWidth;
                this.currentIndex--;
                if (this.currentIndex < 0) {
                    this.currentIndex = 4;
                }
            }
            this.canClick = false;
            this.box.animate({ left: left + "px" }, function() {
                self.canClick = true;
            });
        }
    }
    clickMove(index) {
        let self = this;
        let myIndex = index;
        if (myIndex > 4) {
            myIndex = myIndex - 5;
        }
        if (this.canClick) {
            let left;
            if (myIndex < 3) {
                left = -(myIndex + 3) * this.liWidth;
            } else {
                left = -(myIndex - 2) * this.liWidth;
            }

            this.canClick = false;
            this.box.animate({ left: left + "px" }, function() {
                self.canClick = true;
            })
        }
        if (this.tabContents) {
            this.switchTabContents(myIndex)
        }
    }
    switchTabContents(index) {
        let tabContentsList = this.tabContents.find("li");
        tabContentsList.each(function() { $(this).removeClass('active'); });
        tabContentsList.eq(index).addClass('active');
    }
}
