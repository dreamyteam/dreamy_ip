export default class FixTop {
    constructor(el) {
        this.el = el || $('#program_nav');
        this.init();
    }
    init() {
        let self = this;
        if (this.el.length > 0) {
            const oriOffset = this.el.offset().top;
            $(window).on('scroll', function() {
                if ($(this).scrollTop() > oriOffset) {
                    self.el.addClass('program_nav_scroll');
                } else {
                    self.el.removeClass('program_nav_scroll');
                }
            })
        }
    }
}

