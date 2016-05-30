/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;
/******/
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/*!***************************!*\
  !*** ./src/entrys/all.js ***!
  \***************************/
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	var _LonginReg = __webpack_require__(/*! ../components/LonginReg.js */ 1);
	
	var _LonginReg2 = _interopRequireDefault(_LonginReg);
	
	var _pop_up = __webpack_require__(/*! ../components/pop_up.js */ 2);
	
	var _pop_up2 = _interopRequireDefault(_pop_up);
	
	var _back_top = __webpack_require__(/*! ../components/back_top.js */ 3);
	
	var _back_top2 = _interopRequireDefault(_back_top);
	
	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }
	
	$(function () {
	
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
	    new _back_top2.default(); //返回顶部
	});

/***/ },
/* 1 */
/*!*************************************!*\
  !*** ./src/components/LonginReg.js ***!
  \*************************************/
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
/* 2 */
/*!**********************************!*\
  !*** ./src/components/pop_up.js ***!
  \**********************************/
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
/* 3 */
/*!************************************!*\
  !*** ./src/components/back_top.js ***!
  \************************************/
/***/ function(module, exports) {

	'use strict';
	
	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	
	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();
	
	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }
	
	var BackTop = function () {
	    function BackTop(contrastElement) {
	        _classCallCheck(this, BackTop);
	
	        //参照元素
	        this.boundingBox = null;
	        this.contrastElement = $(contrastElement || '.container');
	        this.init();
	    }
	
	    _createClass(BackTop, [{
	        key: 'init',
	        value: function init() {
	            this.renderUI();
	            this.syncUI();
	            this.toTop();
	        }
	    }, {
	        key: 'renderUI',
	        value: function renderUI() {
	            if ($('#gotoTop').length > 0) {
	                this.boundingBox = $('#gotoTop');
	            } else {
	                this.boundingBox = $("<div id='gotoTop'><button class='back_to_top'></button><a class='feedback' href='#'></a></div>");
	                this.boundingBox.appendTo(document.body);
	            }
	            // 先消失
	            this.boundingBox.hide();
	            this.show();
	        }
	    }, {
	        key: 'show',
	        value: function show() {
	            var self = this;
	            $(window).scroll(function () {
	                var top = $(document).scrollTop();
	                if (top > 400) {
	                    self.boundingBox.fadeIn(200);
	                } else if (top < 400) {
	                    self.boundingBox.fadeOut(200);
	                }
	            });
	        }
	    }, {
	        key: 'syncUI',
	        value: function syncUI() {
	            var self = this;
	            var cLeft = this.contrastElement.offset().left;
	            var cWidth = this.contrastElement.width();
	            this.boundingBox.css({
	                left: cLeft + cWidth + 20 + 'px'
	            });
	            $(window).resize(function () {
	                var cLeft = self.contrastElement.offset().left;
	                var cWidth = self.contrastElement.width();
	
	                self.boundingBox.css({
	                    left: cLeft + cWidth + 20 + 'px'
	                });
	            });
	        }
	    }, {
	        key: 'toTop',
	        value: function toTop() {
	            var self = this;
	            this.boundingBox.find('button.back_to_top').on('click', function () {
	                $('html,body').animate({ scrollTop: 0 }, 500);
	            });
	        }
	    }]);
	
	    return BackTop;
	}();
	
	exports.default = BackTop;

/***/ }
/******/ ]);
//# sourceMappingURL=all.js.map