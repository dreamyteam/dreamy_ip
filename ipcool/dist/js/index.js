/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ({

/***/ 0:
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var _tab = __webpack_require__(5);

	var _tab2 = _interopRequireDefault(_tab);

	var _slide_tab = __webpack_require__(6);

	var _slide_tab2 = _interopRequireDefault(_slide_tab);

	var _pop_up = __webpack_require__(2);

	var _pop_up2 = _interopRequireDefault(_pop_up);

	var _LonginReg = __webpack_require__(1);

	var _LonginReg2 = _interopRequireDefault(_LonginReg);

	var _hover_delay = __webpack_require__(19);

	var _hover_delay2 = _interopRequireDefault(_hover_delay);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	$(function () {

	    var userMenu = new _hover_delay2.default({
	        el: "#currentUser",
	        target: "#user_menu"
	    });

	    setTimeout(function () {
	        $("#loading").hide();
	        $("#home_page").show();
	        setTimeout(function () {
	            $("#home_page").addClass("active");
	        }, 100);
	        $("#home_page").fullpage({
	            verticalCentered: false,
	            afterLoad: function afterLoad(anchorLink, index) {
	                $(".sec_" + index).addClass('active');
	            }
	        });
	    }, 800);

	    var sec2Tab = new _tab2.default({
	        el: "#sec_2_tab",
	        tabNav: ".tab_nav",
	        tabContents: ".tab_content",
	        trigger: "mouseover"
	    });
	    var sec3Tab = new _tab2.default({
	        el: "#sec_3_tab",
	        tabNav: ".sec_3_list",
	        tabContents: ".sec_3_list_summary",
	        trigger: "mouseover"
	    });
	    var sec5SlideTab = new _slide_tab2.default({
	        el: "#sec_5_tab_slide",
	        box: ".sec_5_tab_slide_nav",
	        li: ".single_logo",
	        tabContents: ".sec_5_tab_slide_content",
	        trigger: "click"
	    });
	    var sec6SlideTab = new _slide_tab2.default({
	        el: "#sec_6_product",
	        box: ".sec_6_product_list",
	        prev: ".prev",
	        next: ".next",
	        li: ".single_product"
	    });
	    $("#register").on('click', function () {
	        var popReg = new _pop_up2.default('#popup_sign');
	        popReg.alert();
	        new _LonginReg2.default({
	            el: '#popup_sign',
	            type: 0
	        });
	    });
	    $("#login").on('click', function () {
	        var popLogin = new _pop_up2.default("#popup_sign");
	        popLogin.alert();
	        new _LonginReg2.default({
	            el: "#popup_sign",
	            type: 1
	        });
	    });
	    $("#regBottom").on('click', function () {
	        var popRegBottom = new _pop_up2.default('#popup_sign');
	        popRegBottom.alert();
	        new _LonginReg2.default({
	            el: '#popup_sign',
	            type: 0
	        });
	    });
	});

/***/ },

/***/ 1:
/***/ function(module, exports) {

	"use strict";

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	var Sign = function () {
	    function Sign(cfg) {
	        _classCallCheck(this, Sign);

	        this.cfg = cfg;
	        this.type = null; // 0 reg 1 login
	        this.el = null;
	        this.boxReg = null;
	        this.boxLogin = null;
	        this.err_msg = null;
	        this.boxValidate = null;
	        this.canClickSendVCB = null;
	        this.init();
	    }

	    _createClass(Sign, [{
	        key: "init",
	        value: function init() {
	            this.el = $("#popup_sign");
	            this.boxReg = $("#register_form");
	            this.boxLogin = $("#login_form");
	            this.err_msg = this.el.find('.err_msg');
	            this.boxValidate = $("#register_form .btn_send_verify_code");
	            this.canClickSendVCB = true; //可以发送验证码
	            this.type = this.cfg.type;
	            this.boxReg.hide();
	            this.boxLogin.hide();
	            this.err_msg.hide();
	            this.checkType(this.type); //检测type
	            this.bindSwitchBtn();
	        }
	    }, {
	        key: "bindSwitchBtn",
	        value: function bindSwitchBtn() {
	            var self = this;
	            this.boxReg.find('.tips_bottom_btn').on('click', function () {
	                self.type = 1;
	                self.checkType(self.type);
	            });
	            this.boxLogin.find('.tips_bottom_btn').on('click', function () {
	                self.type = 0;
	                self.checkType(self.type);
	            });
	        }
	    }, {
	        key: "checkType",
	        value: function checkType(type) {
	            if (type == 0) {
	                // reg
	                this.renderReg();
	            } else if (type == 1) {
	                //login
	                this.renderLogin();
	            }
	        }
	    }, {
	        key: "renderReg",
	        value: function renderReg() {
	            this.boxLogin.hide();
	            this.boxReg.show();
	            this.validateBase(0);
	        }
	    }, {
	        key: "renderLogin",
	        value: function renderLogin() {
	            this.boxReg.hide();
	            this.boxLogin.show();
	            this.validateBase(1);
	        }
	    }, {
	        key: "validateBase",
	        value: function validateBase(type) {
	            // 0 跳入 reg判断 1 跳入 login 判断
	            var self = this;
	            var hasValueRegPhone = false,
	                hasValueRegPwd = false,
	                hasValueLoginPhone = false,
	                hasValueLoginPwd = false;

	            if (type == 0) {
	                //reg
	                this.boxReg.find('input').on("input propertychange", function () {
	                    if ($(this).is("input[name='phone_number']")) {
	                        $(this).val($(this).val().replace(/\D/g, '')); //只能输入数字
	                        if ($(this).val() !== '') {
	                            hasValueRegPhone = true;
	                        }
	                    }
	                    if ($(this).is("input[name='password']")) {
	                        if ($(this).val() !== '') {
	                            hasValueRegPwd = true;
	                        }
	                    }
	                    if (hasValueRegPhone && hasValueRegPwd) {
	                        self.bindBtnValidateReg();
	                    }
	                });
	            } else if (type == 1) {
	                //login
	                this.boxLogin.find('input').on("input propertychange", function () {
	                    if ($(this).is("input[name='phone_number']")) {
	                        $(this).val($(this).val().replace(/\D/g, '')); //只能输入数字
	                        if ($(this).val() !== '') {
	                            hasValueLoginPhone = true;
	                        }
	                    }
	                    if ($(this).is("input[name='password']")) {
	                        if ($(this).val() !== '') {
	                            hasValueLoginPwd = true;
	                        }
	                    }
	                    if (hasValueLoginPhone && hasValueLoginPwd) {
	                        self.loginSubmit();
	                    }
	                });
	            }
	        }
	    }, {
	        key: "bindBtnValidateReg",
	        value: function bindBtnValidateReg() {
	            var self = this;
	            if (this.canClickSendVCB) {
	                this.boxValidate.addClass('active');
	                this.boxValidate.removeAttr("disabled");
	            }
	            this.boxValidate.off('click');
	            this.boxValidate.on('click', function (e) {
	                var regPhone = /^0?1[3|4|5|8][0-9]\d{8}$/;
	                var regPwd = /^[a-zA-Z\d]{6,16}$/;
	                var inputPhone = self.boxReg.find("input[name='phone_number']");
	                var inputPwd = self.boxReg.find("input[name='password']");
	                if (!regPhone.test(inputPhone.val())) {
	                    self.err_msg.show().html("手机号码格式错误");
	                } else if (!regPwd.test(inputPwd.val())) {
	                    self.err_msg.show().html("密码必须为6-16位,字母或数字");
	                } else {
	                    self.err_msg.hide();
	                    self.checkValidate();
	                }
	                return false;
	            });
	        }
	    }, {
	        key: "checkValidate",
	        value: function checkValidate() {
	            var self = this;
	            $.ajax({
	                url: '/user/register/verificationCode',
	                type: 'POST',
	                data: {
	                    mobile: self.boxReg.find("input[name='phone_number']").val()
	                },
	                success: function success(result) {
	                    if (result.error_code == 0) {
	                        self.boxValidate.removeClass('active');
	                        self.boxValidate.attr("disabled", "disabled");
	                        self.canClickSendVCB = false;
	                        self.regSubmit();
	                        settime(self.boxValidate);
	                    } else if (result.error_code > 0) {
	                        self.err_msg.show().html(result.error_msg);
	                    }
	                }
	            });
	            var countdown = 60;

	            function settime(obj) {
	                if (countdown == 0) {
	                    obj.removeAttr("disabled");
	                    obj.addClass("active");
	                    obj.html("重新发送");
	                    countdown = 60;
	                    return;
	                } else {
	                    obj.attr("disabled", "disabled");
	                    obj.html("重新发送" + countdown + 's');
	                    countdown--;
	                }
	                setTimeout(function () {
	                    settime(obj);
	                }, 1000);
	            }
	        }
	    }, {
	        key: "regSubmit",
	        value: function regSubmit() {
	            var self = this;
	            var btnSubmit = this.boxReg.find("button.solid");
	            var lastInput = this.boxReg.find("input[name='verify_code']");
	            btnSubmit.addClass('active');
	            lastInput.off("keydown");
	            lastInput.on("keydown", function (e) {
	                var key = e.which;
	                if (key == 13) {
	                    e.preventDefault();
	                    self.regConfirm();
	                    return false;
	                }
	            });
	            btnSubmit.off('click');
	            btnSubmit.on('click', function () {
	                self.regConfirm();
	                return false;
	            });
	        }
	    }, {
	        key: "regConfirm",
	        value: function regConfirm() {
	            var self = this;
	            $.ajax({
	                url: '/user/register',
	                type: 'POST',
	                data: {
	                    mobile: self.boxReg.find("input[name='phone_number']").val(),
	                    password: self.boxReg.find("input[name='password']").val(),
	                    checkCode: self.boxReg.find("input[name='verify_code']").val()
	                },
	                success: function success(result) {
	                    console.log(result);
	                    if (result.error_code == 0) {
	                        location.reload();
	                    } else if (result.error_code > 0) {
	                        self.err_msg.show().html(result.error_msg);
	                    }
	                }
	            });
	        }
	    }, {
	        key: "loginSubmit",
	        value: function loginSubmit() {
	            var self = this;
	            var btnSubmit = this.boxLogin.find("button.solid");
	            var lastInput = this.boxLogin.find("input[name='password']");
	            btnSubmit.addClass('active');

	            lastInput.off("keydown");
	            lastInput.on("keydown", function (e) {
	                var key = e.which;
	                if (key == 13) {
	                    e.preventDefault();
	                    self.loginBeforeAjax();
	                    return false;
	                }
	            });
	            btnSubmit.off("click");
	            btnSubmit.on('click', function (e) {
	                e.preventDefault();
	                self.loginBeforeAjax();
	                return false;
	            });
	        }
	    }, {
	        key: "loginBeforeAjax",
	        value: function loginBeforeAjax() {
	            var self = this;
	            var regPhone = /^0?1[3|4|5|8][0-9]\d{8}$/;
	            var regPwd = /^[a-zA-Z\d]{6,16}$/;
	            var inputPhone = self.boxLogin.find("input[name='phone_number']");
	            var inputPwd = self.boxLogin.find("input[name='password']");
	            var inputRememberPwd = self.boxLogin.find("input[name='remember_pwd']");
	            var rememberPwd;
	            if (inputRememberPwd.is(":checked")) {
	                rememberPwd = 1;
	            } else {
	                rememberPwd = 0;
	            }
	            if (!regPhone.test(inputPhone.val())) {
	                self.err_msg.show().html("手机号码格式错误");
	            } else if (!regPwd.test(inputPwd.val())) {
	                self.err_msg.show().html("密码必须为6-16位,字母或数字");
	            } else {
	                self.err_msg.hide();
	                self.loginConfirm(rememberPwd);
	            }
	        }
	    }, {
	        key: "loginConfirm",
	        value: function loginConfirm(cheked) {
	            var self = this;
	            $.ajax({
	                url: '/user/login',
	                type: 'POST',
	                data: {
	                    mobile: self.boxLogin.find("input[name='phone_number']").val(),
	                    password: self.boxLogin.find("input[name='password']").val(),
	                    rememberPwd: cheked
	                },
	                success: function success(result) {
	                    console.log(result);
	                    if (result.error_code == 0) {
	                        location.reload();
	                    } else if (result.error_code > 0) {
	                        self.err_msg.show().html(result.error_msg);
	                    }
	                }
	            });
	        }
	    }]);

	    return Sign;
	}();

	exports.default = Sign;

/***/ },

/***/ 2:
/***/ function(module, exports) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	var Popup = function () {
	    function Popup(el) {
	        _classCallCheck(this, Popup);

	        this.el = $(el);
	        this.mask = null;
	        this.init();
	    }

	    _createClass(Popup, [{
	        key: 'init',
	        value: function init() {
	            if ($('#popup_mask').length > 0) {
	                this.mask = $('#popup_mask');
	            } else {
	                this.mask = $("<div class='popup_mask' id='popup_mask'></div>");
	            }
	            this.bindClose();
	        }
	    }, {
	        key: 'bindClose',
	        value: function bindClose() {
	            var self = this;
	            this.mask.on('click', function () {
	                self.destory();
	            });
	            if (this.el.find('button.close')) {
	                var btnClose = this.el.find('button.close');
	                btnClose.on('click', function () {
	                    self.destory();
	                });
	            }
	        }
	    }, {
	        key: 'destory',
	        value: function destory() {
	            this.mask.remove();
	            this.el.hide();
	        }
	    }, {
	        key: 'alert',
	        value: function alert() {
	            this.mask.appendTo("body");
	            this.el.show();
	            this.el.addClass("active");
	        }
	    }]);

	    return Popup;
	}();

	exports.default = Popup;

/***/ },

/***/ 5:
/***/ function(module, exports) {

	"use strict";

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	var Tab = function () {
	    function Tab(cfg) {
	        _classCallCheck(this, Tab);

	        this.cfg = cfg;
	        this.init();
	    }

	    _createClass(Tab, [{
	        key: "init",
	        value: function init() {
	            this.el = $(this.cfg.el);
	            this.tabNav = this.el.find(this.cfg.tabNav);
	            this.tabContents = this.el.find(this.cfg.tabContents);
	            this.tabNavList = this.tabNav.find("li");
	            this.contentList = this.tabContents.find("li");
	            this.trigger = this.cfg.trigger || "click";
	            this.checkTrigger();
	        }
	    }, {
	        key: "checkTrigger",
	        value: function checkTrigger() {
	            var self = this;
	            if (this.trigger == "mouseover") {
	                this.tabNavList.each(function () {
	                    $(this).on("mouseover", function () {
	                        var index = $(this).index();
	                        self.switchTabNav(index);
	                    });
	                });
	            } else if (this.trigger == "click") {
	                this.tabNavList.each(function () {
	                    $(this).on("click", function () {
	                        var index = $(this).index();
	                        self.switchTabNav(index);
	                    });
	                });
	            }
	        }
	    }, {
	        key: "switchTabNav",
	        value: function switchTabNav(index) {
	            this.tabNavList.each(function () {
	                $(this).removeClass('active');
	            });
	            this.tabNavList.eq(index).addClass('active');
	            this.switchContent(index, true);
	        }
	    }, {
	        key: "switchContent",
	        value: function switchContent(index, animate) {
	            this.contentList.each(function () {
	                $(this).removeClass('active');
	            });
	            this.contentList.eq(index).addClass('active');
	        }
	    }]);

	    return Tab;
	}();

	exports.default = Tab;

/***/ },

/***/ 6:
/***/ function(module, exports) {

	"use strict";

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	var SlideTab = function () {
	    function SlideTab(cfg) {
	        _classCallCheck(this, SlideTab);

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

	    _createClass(SlideTab, [{
	        key: "init",
	        value: function init() {
	            var self = this;
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
	    }, {
	        key: "resize",
	        value: function resize() {
	            // 重置容器宽度
	            this.liWidth = this.lis.eq(0).width();
	            var ulWidth = this.liWidth * this.lis.length;
	            this.box.css({ width: ulWidth + 'px' });

	            var currentOffsetLeft = this.currentIndex * this.liWidth;
	            this.box.css({ left: -currentOffsetLeft + "px" });

	            if (this.tabContents) {
	                this.switchTabContents(this.currentIndex + 2);
	            }
	        }
	    }, {
	        key: "bindUI",
	        value: function bindUI() {
	            var self = this;

	            if (this.trigger == "btn") {
	                this.prev.off("click");
	                this.prev.on("click", function () {
	                    self.checkProsition();
	                    self.move(-1);
	                });
	                this.next.off("click");
	                this.next.on("click", function () {
	                    self.checkProsition();
	                    self.move(1);
	                });
	            } else if (this.trigger == "click") {
	                this.lis.each(function () {
	                    $(this).on("click", function () {
	                        self.checkProsition();
	                        self.clickMove($(this).index());
	                    });
	                });
	            }
	            $(window).on("resize", function () {
	                self.resize();
	            });
	        }
	    }, {
	        key: "checkProsition",
	        value: function checkProsition() {
	            var offsetLeft = this.box.position().left; //ul 的 offsetLeft
	            var ulWidth = -this.box.width() / 2; //重要 宽度不可能是负数
	            if (offsetLeft < ulWidth) {
	                this.box.css("left", -this.liWidth + "px");
	            }
	            if (offsetLeft >= 0) {
	                console.log("重置了");
	                this.box.css("left", ulWidth + "px");
	            }
	        }
	    }, {
	        key: "move",
	        value: function move(direction) {
	            //-1是后退 1是前进
	            var self = this;
	            if (this.canClick) {
	                var boxLeft = this.box.position().left;
	                if (direction > 0) {
	                    //后退
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
	                this.box.animate({ left: left + "px" }, function () {
	                    self.canClick = true;
	                });
	            }
	        }
	    }, {
	        key: "clickMove",
	        value: function clickMove(index) {
	            var self = this;
	            var myIndex = index;
	            if (myIndex > 4) {
	                myIndex = myIndex - 5;
	            }
	            if (this.canClick) {
	                var left = void 0;
	                if (myIndex < 3) {
	                    left = -(myIndex + 3) * this.liWidth;
	                } else {
	                    left = -(myIndex - 2) * this.liWidth;
	                }

	                this.canClick = false;
	                this.box.animate({ left: left + "px" }, function () {
	                    self.canClick = true;
	                });
	            }
	            if (this.tabContents) {
	                this.switchTabContents(myIndex);
	            }
	        }
	    }, {
	        key: "switchTabContents",
	        value: function switchTabContents(index) {
	            var tabContentsList = this.tabContents.find("li");
	            tabContentsList.each(function () {
	                $(this).removeClass('active');
	            });
	            tabContentsList.eq(index).addClass('active');
	        }
	    }]);

	    return SlideTab;
	}();

	exports.default = SlideTab;

/***/ },

/***/ 19:
/***/ function(module, exports) {

	"use strict";

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	var HoverDelay = function () {
	    function HoverDelay(cfg) {
	        _classCallCheck(this, HoverDelay);

	        this.cfg = cfg;
	        this.el = null;
	        this.target = null;
	        this.init();
	    }

	    _createClass(HoverDelay, [{
	        key: "init",
	        value: function init() {
	            this.el = $(this.cfg.el);
	            this.target = $(this.cfg.target);
	            this.bindUI();
	        }
	    }, {
	        key: "bindUI",
	        value: function bindUI() {
	            var self = this;
	            var timer = null;
	            this.el.hover(function () {
	                self.targetShow();
	                clearInterval(timer); //关键
	            }, function () {
	                timer = setTimeout(function () {
	                    self.targetHide();
	                }, 1000);
	            });

	            this.target.hover(function () {
	                clearInterval(timer);
	            }, function () {
	                timer = setTimeout(function () {
	                    self.targetHide();
	                }, 1000);
	            });
	        }
	    }, {
	        key: "targetShow",
	        value: function targetShow() {
	            var self = this;
	            self.target.show();
	            setTimeout(function () {
	                self.target.addClass("active");
	            }, 1);
	        }
	    }, {
	        key: "targetHide",
	        value: function targetHide() {
	            var self = this;
	            self.target.removeClass("active");
	            setTimeout(function () {
	                self.target.hide();
	            }, 300);
	        }
	    }]);

	    return HoverDelay;
	}();

	exports.default = HoverDelay;

/***/ }

/******/ });