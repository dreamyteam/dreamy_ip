export default class Paging {
    constructor(el) {
        this.el = $(el);
        this.url = null;
        this.pageAttach = null;
        this.totalNum = null;
        this.current = null;
        this.totalPage = null;
        this.content = null;
        this.ul = null;
        this.init();
    }
    init() {
        this.url = window.location.pathname;
        this.pageAttach = this.el.data("pageAttach");

        this.totalNum = this.pageAttach.totalNum; //总数据数 用于计算非显示
        this.current = this.pageAttach.currentPage; //当前页码
        this.pageSize = this.pageAttach.pageSize; //每页显示多少个
        this.totalPage = Math.ceil(this.totalNum / this.pageSize); //总页数 用于显示
        this.content = this.pageAttach.content; //查询参数用于拼接字符串

        //第一层判断 是否显示分页 如果pagesize <= totalNumber 则显示
        if (this.el.length > 0 && this.pageSize <= this.totalNum) {
            this.setPaging();
            // this.setWidget();
        }
    }
    setPaging() {
        this.ul = $("<ul class='pagging_container'></ul>");
        this.el.append(this.ul);
        this.setPrevBtn();
        this.setPageNumbers();
        this.setNextBtn();
        this.setWidget();
    }
    setPageNumbers() {
        // console.log("总页数" + this.totalPage);
        if (this.totalPage <= 6) {
            this.setDom(1, this.totalPage, true);
        } else { //totalPage >6 需要显示...
            if (this.current < 6) {
                this.setDom(1, 6, true);
                this.setDot();
            } else { //current > 6 显示12 ... current
                //先添加1，2...
                this.setDom(1, 2, false);
                this.setDot();
                let lastPage = this.current + 2;
                if (lastPage < this.totalPage) {
                    this.setDom(this.current - 2, lastPage, true);
                    this.setDot();
                } else { //用于计算 后面数字出现5个
                    let afterCurrent = this.totalPage - this.current;
                    console.log(afterCurrent);
                    if (afterCurrent <= 2) {
                        let beforeCurrent = 4 - afterCurrent;
                        this.setDom(this.current - beforeCurrent, this.totalPage, true);
                    }
                }
            }
        }
    }
    setDom(pageStart, pageEnd, hasCurrent) {
        for (let i = pageStart; i <= pageEnd; i++) {
            if (hasCurrent) {
                if (i == this.current) {
                    this.ul.append($("<li class='page active'><a href=" + this.url + '?content=' + this.content + '&currentPage=' + i + ">" + i + "</a></li>"))
                } else {
                    this.ul.append($("<li class='page'><a href=" + this.url + '?content=' + this.content + '&currentPage=' + i + ">" + i + "</a></li>"))
                }
            } else {
                this.ul.append($("<li class='page'><a href=" + this.url + '?content=' + this.content + '&currentPage=' + i + ">" + i + "</a></li>"))

            }
        }
    }
    setDot() {
        let dot = $("<li class='dot'>...</li>");
        dot.appendTo(this.ul);
    }
    setPrevBtn() {
        //先添加上一页按钮
        let prevBtn = $("<li class='page'><a href=" + this.url + '?content=' + this.content + '&currentPage=' + (this.current - 1) + "><</a></li>");
        prevBtn.appendTo(this.ul);
        //判断是否为第一页 如果是 则 上一页为disable状态
        if (this.current == 1) {
            prevBtn.addClass("disable");
            prevBtn.find("a").attr("href", "javascript:void(0)");
        }
    }
    setNextBtn() {
        let nextBtn = $("<li class='page'><a href=" + this.url + '?content=' + this.content + '&currentPage=' + (this.current + 1) + ">></a></li>");
        nextBtn.appendTo(this.ul);
        //判断是否为最后页 如果是 则 下一页为disable状态
        if (this.current == this.totalPage) {
            nextBtn.addClass("disable");
            nextBtn.find("a").attr("href", "javascript:void(0)")
        }
    }
    setWidget() {
        let self = this;
        let value = this.current == this.totalPage ? this.totalPage : this.current + 1;
        let widget = $(
            "<li class='widget'>" +
            "<span class='text'>共" + this.totalPage + "页，到第</span>" +
            "<input type='number' name='which_page' value=" + value + " min='1' max=" + this.totalPage + ">" +
            "<span class='text'>页</span>" +
            "<button class='go_selected_page'>确定</button>" +
            "</li>"
        )
        widget.appendTo(this.ul);
        let btn = widget.find(".go_selected_page");
        btn.on("click", function() {
            let whichPage = widget.find("input[name='which_page']").val();
            if (whichPage > 0 && whichPage <= self.totalPage) {
                window.location.href = self.url + "?content=" + self.content + "&currentPage=" + whichPage;
            }
        })
    }
}
