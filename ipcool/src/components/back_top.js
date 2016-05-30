export default class BackTop {
    constructor(contrastElement) { //参照元素
        this.boundingBox = null;
        this.contrastElement = $(contrastElement || '.container');
        this.init();
    }
    init() {
        this.renderUI();
        this.syncUI();
        this.toTop();
    }
    renderUI() {
        if ($('#gotoTop').length > 0) {
            this.boundingBox = $('#gotoTop');
        } else {
            this.boundingBox = $(
                "<div id='gotoTop'><button class='back_to_top'></button><a class='feedback' href='#'></a></div>"
            )
            this.boundingBox.appendTo(document.body);
        }
        // 先消失
        this.boundingBox.hide();
        this.show();
    }
    show() {
        let self = this;
        $(window).scroll(function() {
            let top = $(document).scrollTop();
            if (top > 400) {
                self.boundingBox.fadeIn(200);
            } else if (top < 400) {
                self.boundingBox.fadeOut(200);
            }
        })
    }
    syncUI() {
        let self = this;
        let cLeft = this.contrastElement.offset().left;
        let cWidth = this.contrastElement.width();
        this.boundingBox.css({
            left: cLeft + cWidth + 20 + 'px'
        })
        $(window).resize(function() {
            let cLeft = self.contrastElement.offset().left;
            let cWidth = self.contrastElement.width();

            self.boundingBox.css({
                left: cLeft + cWidth + 20 + 'px'
            })
        })
    }
    toTop() {
        let self = this;
        this.boundingBox.find('button.back_to_top').on('click', function() {
            $('html,body').animate({scrollTop: 0}, 500)
        })
    }
}
