function VoteProto(cfg) {
    this.cfg = cfg;
    this.el = null; //根元素
    this.ip_Id = null; //ipid
    this.allBtns = null; //所有投票按钮
    this.btnExpexts = null; // 期待投票按钮们
    this.btnWanted = null; // 想开发按钮们
    this.init();
}
VoteProto.prototype = {
    init: function() {
        this.el = $(this.cfg.el);
        this.ip_Id = this.cfg.ip_Id;
        if (this.el) {
            this.allBtns = this.el.find("button");
            this.btnExpexts = this.el.find("button.expext");
            this.btnWanted = this.el.find("button.wantDevelop");
            this.renderVoted();
            this.bindVoteBtns();
        }
    },
    renderVoted: function() { //初始化已投票按钮
        var self = this;
        var expectVoted, wantVoted;
        $.ajax({
            url: '/ip/vote/detail',
            type: 'POST',
            data: {
                ipId: self.ip_Id,
            },
            success: function(result) {
                if (result.error_code == 0) {
                    expectVoted = result.data["1"];
                    wantVoted = result.data["2"];
                    self.setVotedBtns(expectVoted, wantVoted);
                } else if (result.error_code > 0) {
                    //TODO 综合用户提示
                    alert(result.error_msg);
                }
            }
        })
    },
    setVotedBtns: function(expect, want) {
        for (var i = 0; i < expect.length; i++) {
            var eindex = expect[i] - 1;
            this.btnExpexts.eq(eindex).addClass('active').attr("disabled", true);
        };

        for (var j = 0; j < want.length; j++) {
            var windex = want[j] - 1;
            this.btnWanted.eq(windex).addClass('active').attr("disabled", true);
        }
    },
    bindVoteBtns: function() {
        var self = this;
        this.allBtns.each(function() {
            $(this).on("click", function() {
                $(this).addClass("active").attr("disabled", true);
                var parent = $(this).closest('.vote');
                var projectName = parent.find('h5').html();
                var type = parent.data("type");
                var choice;
                if ($(this).hasClass('expext')) {
                    choice = 1;
                } else if ($(this).hasClass('wantDevelop')) {
                    choice = 2;
                }
                $.ajax({
                        url: '/user/intention/vote',
                        type: 'POST',
                        data: {
                            ipId: self.ip_Id,
                            type: type,
                            choice: choice
                        },
                        success: function(result) {
                            console.log(result);
                            if (result.error_code == 0) {
                                //添加active
                            } else if (result.error_code > 0) {
                                alert(result.error_msg);
                            }
                        }
                    })
            })
        })
    }
}

module.exports = VoteProto
