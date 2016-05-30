

function PopUp(cfg) {
    this.cfg = cfg;
    this.ipName = null;
    this.project = null;
    this.type = null;
    this.boundingBox = null;
    this.title = null;
    this.placeholder = null;
    this.init();
}
PopUp.prototype = {
    init: function() {
        this.ipName = this.cfg.ipName || '';
        this.project = this.cfg.project || '';
        this.type = this.cfg.type || '';
        if(this.type == 'expext'){
        	this.title = '<h2 class="title">我期待[' + this.ipName + ']被开发成' + this.project + '</h2>';
        	this.placeholder = "说说你的期待吧!"
        }else if (this.type = 'wantDevelop') {
        	this.title = '<h2 class="title">愿意为[' + this.ipName + ']开发' + this.project + '贡献一份力</h2>';
        	this.placeholder = "说说你聪明绝顶的想法吧!"
        }

        this.boundingBox = $(
            '<div class="popup_box pop_up_vote clearfix">' +
            '<button class="close"></button>' +
             this.title+
            '<textarea class="vote_textarea" placeholder='+this.placeholder+'></textarea>' +
            '<button class="btn_vote">发布</button>' +
            "</div>"
        )
    },
    alert: function() {
        this.boundingBox.appendTo('body');
        this.boundingBox.show();
    },
    destory: function() {
    	this.boundingBox.off();
    	this.boundingBox.remove();
    }
}
module.exports = PopUp;
