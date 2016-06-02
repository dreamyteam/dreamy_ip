//TODO 组织提交
export default class Validate {
    constructor(cfg) {
        this.cfg = cfg;
        this.el = null;
        this.inputBoxs = null; //input 容器 用于查找 input 和 errmsg
        this.btnSubmit = null;
        this.init();
    }
    init() {
        this.el = $(this.cfg.el);
        this.inputBoxs = this.el.find(this.cfg.inputBoxs);
        this.btnSubmit = this.el.find(this.cfg.btnSubmit);
        this.errMsg = ".err_msg";
        this.validateBlur();
        this.checkSubmit();
    }
    checkRequired(obj, parent, canSubmit) {
        let self = this;
        let errMsg = parent.find(this.errMsg);
        if (obj.val() == '') {
            let errText = obj.data("required") ? obj.data("required") : "必填";
            errMsg.show().html(errText);
            if (canSubmit) {
                self.canSubmit = false;
            }
        } else {
            errMsg.hide();
            if (canSubmit) {
                self.canSubmit = true;
            }
        }
    }
    checkMail(obj, parent, canSubmit) {
        let self = this;
        let errMsg = parent.find(this.errMsg);
        let regMail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if (!regMail.test(obj.val())) {
            errMsg.show().html("请输入正确的邮箱格式");
            if (canSubmit) {
                self.canSubmit = false;
            }
        } else {
            errmsg.hide();
            if (canSubmit) {
                self.canSubmit = true;
            }
        }
    }
    checkSelected(obj, parent, canSubmit) {
        let self = this;
        let errMsg = parent.find(this.errMsg);
        if (obj.val() == 0) {
            let errText = obj.data("required") ? obj.data("required") : "您需要选择类型";
            errMsg.show().html(errText);
            if (canSubmit) {
                self.canSubmit = false;
            }
        } else {
            errMsg.hide();
            if (canSubmit) {
                self.canSubmit = true;
            }
        }
    }
    validateBlur() {
        let self = this;
        this.inputBoxs.each(function() {
            let curBox = $(this);
            let curInput = $(this).find("input");
            let errMsg = curBox.find(self.errMsg);
            curInput.on("blur", function() {
                if (curInput.attr("required")) { //检测是否为空
                    self.checkRequired(curInput, curBox, false);
                }
                if (curInput.attr("type") == "email") {
                    self.checkMail(curInput, curBox, true);
                }
            });
            curInput.on("focus", function() {
                console.log(errMsg);
                errMsg.hide().html("");
            });
            // select框
            let curSelect = $(this).find("select");
            curSelect.on("blur", function() {
                if (curSelect.attr("required")) {
                    self.checkSelected(curSelect, curBox, true);
                }
            });
            curSelect.on("focus", function() {
                errMsg.hide();
            })
        })
    }
    validateSubmit() {
        let self = this;
        this.inputBoxs.each(function() {
            let curBox = $(this);
            let curInput = $(this).find("input");
            if (curInput.attr("required")) { //检测是否为空
                self.checkRequired(curInput, curBox, true);
            }
            if (curInput.attr("type") == "email") {
                self.checkMail(curInput, curBox, true);
            }
            let curSelect = $(this).find("select");
            if (curSelect.attr("required")) {
                self.checkSelected(curSelect, curBox, true);
            }
        })
    }
    checkSubmit() {
        let self = this;
        this.btnSubmit.on("click", function() {
            self.validateSubmit();
            if (!self.canSubmit) {
                return false;
            }
        })
    }
}
