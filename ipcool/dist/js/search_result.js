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

	var _paging = __webpack_require__(16);

	var _paging2 = _interopRequireDefault(_paging);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	$(function () {
	    //分页
	    var paging = new _paging2.default('#paging');
	});

/***/ },

/***/ 16:
/***/ function(module, exports) {

	"use strict";

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	var Paging = function () {
	    function Paging(el) {
	        _classCallCheck(this, Paging);

	        this.el = $(el);
	        this.url = null;
	        this.pageAttach = null;
	        this.init();
	    }

	    _createClass(Paging, [{
	        key: "init",
	        value: function init() {
	            this.url = window.location.pathname;
	            this.pageAttach = this.el.data("pageAttach");
	            if (this.el.length > 0) {
	                this.setPaging();
	            }
	        }
	    }, {
	        key: "setPaging",
	        value: function setPaging() {
	            var totalNum = this.pageAttach.totalNum;
	            var current = this.pageAttach.currentPage;
	            var pageSize = this.pageAttach.pageSize;
	            var total = Math.ceil(totalNum / pageSize);
	            var content = this.pageAttach.content;

	            if (pageSize <= totalNum) {
	                var ul = $('<ul></ul>');
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
	                            ul.append($("<li class='active'><a href=" + this.url + '?content=' + content + '&currentPage=' + i + ">" + i + "</a></li>"));
	                        } else {
	                            ul.append($("<li><a href=" + this.url + '?content=' + content + '&currentPage=' + i + ">" + i + "</a></li>"));
	                        }
	                    }
	                } else {
	                    if (current <= 4) {
	                        for (var i = 1, len = 7; i <= len; i++) {
	                            if (i == current) {
	                                ul.append($("<li class='active'><a href=" + this.url + '?content=' + content + '&currentPage=' + i + ">" + i + "</a></li>"));
	                            } else {
	                                ul.append($("<li><a href=" + this.url + '?content=' + content + '&currentPage=' + i + ">" + i + "</a></li>"));
	                            }
	                        }
	                    } else {
	                        var pageStart = current - 3;
	                        // console.log(pageStart);
	                        var pageEnd = current + 3 > total ? total : current + 3;
	                        // console.log(pageEnd);
	                        for (var i = pageStart; i <= pageEnd; i++) {
	                            if (i == current) {
	                                ul.append($("<li class='active'><a href=" + this.url + '?content=' + content + '&currentPage=' + i + ">" + i + "</a></li>"));
	                            } else {
	                                ul.append($("<li><a href=" + this.url + '?content=' + content + '&currentPage=' + i + ">" + i + "</a></li>"));
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
	    }]);

	    return Paging;
	}();

	exports.default = Paging;

/***/ }

/******/ });