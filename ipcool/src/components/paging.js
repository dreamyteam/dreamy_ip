export default class Paging {
    constructor(el) {
        this.el = $(el);
        this.url = null;
        this.pageAttach = null;
        this.init();
    }
    init() {
        this.url = window.location.pathname;
        this.pageAttach = this.el.data("pageAttach");
        if (this.el.length > 0) {
            this.setPaging();
        }
    }
    setPaging() {
        let totalNum = this.pageAttach.totalNum;
        let current = this.pageAttach.currentPage;
        let pageSize = this.pageAttach.pageSize;
        let total = Math.ceil(totalNum / pageSize);
        let content = this.pageAttach.content;

        if (pageSize <= totalNum) {
            let ul = $('<ul></ul>');
            this.el.append(ul);
            //是否显示prev
            if (current != 1) {
                var prevBtn = $("<li><a href=" + this.url + '?content=' + content + '&currentPage=' + (current - 1) + "><</a></li>");
                prevBtn.appendTo(ul);
            }

            //插入中间页
            if (total <= 7) {
                for (var i = 1, len = total + 1; i < len; i++) {
                    if (i == current) {
                        ul.append($("<li class='active'><a href=" + this.url + '?content=' + content + '&currentPage=' + i + ">" + i + "</a></li>"))
                    } else {
                        ul.append($("<li><a href=" + this.url + '?content=' + content + '&currentPage=' + i + ">" + i + "</a></li>"))
                    }
                }
            } else {
                if (current <= 4) {
                    for (var i = 1, len = 7; i <= len; i++) {
                        if (i == current) {
                            ul.append($("<li class='active'><a href=" + this.url + '?content=' + content + '&currentPage=' + i + ">" + i + "</a></li>"))
                        } else {
                            ul.append($("<li><a href=" + this.url + '?content=' + content + '&currentPage=' + i + ">" + i + "</a></li>"))
                        }
                    }
                } else {
                    var pageStart = current - 3;
                    // console.log(pageStart);
                    var pageEnd = (current + 3) > total ? total : (current + 3);
                    // console.log(pageEnd);
                    for (var i = pageStart; i <= pageEnd; i++) {
                        if (i == current) {
                            ul.append($("<li class='active'><a href=" + this.url + '?content=' + content + '&currentPage=' + i + ">" + i + "</a></li>"))
                        } else {
                            ul.append($("<li><a href=" + this.url + '?content=' + content + '&currentPage=' + i + ">" + i + "</a></li>"))
                        }
                    }
                }
            }

            //是否显示next
            if (current != total) {
                var nextBtn = $("<li><a href=" + this.url + '?content=' + content + '&currentPage=' + (current + 1) + ">></a></li>");
                nextBtn.appendTo(ul);
            }
        }
    }
}