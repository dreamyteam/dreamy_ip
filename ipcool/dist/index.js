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
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var _tab = __webpack_require__(4);

	var _tab2 = _interopRequireDefault(_tab);

	var _slide_tab = __webpack_require__(5);

	var _slide_tab2 = _interopRequireDefault(_slide_tab);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	$(function () {
	    $("#home_page").fullpage({
	        verticalCentered: false,
	        afterLoad: function afterLoad(anchorLink, index) {
	            $(".sec_" + index).addClass('active');
	        }
	    });
	    var sec2Tab = new _tab2.default({
	        el: "#sec_2_tab",
	        tabNav: ".tab_nav",
	        tabContents: ".tab_content"
	    });
	    var sec6SlideTab = new _slide_tab2.default({
	        el: "#sec_6_product",
	        box: ".sec_6_product_list",
	        prev: ".prev",
	        next: ".next",
	        li: ".single_product"
	    });
	});

/***/ },
/* 1 */,
/* 2 */,
/* 3 */,
/* 4 */
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
	            this.bindTabNav();
	        }
	    }, {
	        key: "bindTabNav",
	        value: function bindTabNav() {
	            var self = this;
	            this.tabNavList.each(function () {
	                $(this).on("click", function () {
	                    var index = $(this).index();
	                    console.log(index);
	                    //除去标题的active类
	                    self.tabNavList.each(function () {
	                        $(this).removeClass('active');
	                    });
	                    $(this).addClass('active');
	                    self.switchContent(index, true);
	                });
	            });
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
/* 5 */
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
	        this.li = null; //单体
	        this.liWidth = null; //单个li 的宽度
	        this.init();
	    }

	    _createClass(SlideTab, [{
	        key: "init",
	        value: function init() {
	            this.el = $(this.cfg.el);
	            this.prev = this.el.find(this.cfg.prev) || null;
	            this.next = this.el.find(this.cfg.next) || null;
	            this.box = this.el.find(this.cfg.box) || null;
	            this.lis = this.box.find(this.cfg.li) || null;
	            this.liWidth = this.lis.eq(0).width();
	            this.canClick = true;
	            this.bindUI();
	        }
	    }, {
	        key: "bindUI",
	        value: function bindUI() {
	            var self = this;
	            // 重置容器宽度
	            this.box.html(this.box.html() + this.box.html());
	            var ulWidth = this.liWidth * this.lis.length * 2;
	            console.log(ulWidth);
	            this.box.css({ width: ulWidth + 'px' });

	            this.prev.on("click", function () {
	                self.checkProsition();
	                self.move(-1);
	            });
	            this.next.on("click", function () {
	                self.checkProsition();
	                self.move(1);
	            });
	        }
	    }, {
	        key: "checkProsition",
	        value: function checkProsition() {
	            var offsetLeft = this.box.position().left; //ul 的 offsetLeft
	            console.log(offsetLeft);
	            var ulWidth = -this.box.width() / 2; //重要 宽度不可能是负数
	            if (offsetLeft < ulWidth) {
	                this.box.css("left", -this.liWidth + "px");
	            }
	            if (offsetLeft >= 0) {
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
	                if (direction < 0) {
	                    //后退
	                    var left = boxLeft - this.liWidth;
	                } else if (direction > 0) {
	                    var left = boxLeft + this.liWidth;
	                }
	                this.canClick = false;
	                this.box.animate({ left: left + "px" }, function () {
	                    self.canClick = true;
	                });
	            }
	        }
	    }]);

	    return SlideTab;
	}();

	exports.default = SlideTab;

/***/ }
/******/ ]);