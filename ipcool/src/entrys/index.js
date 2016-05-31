import Tab from '../components/tab.js'
import SlideTab from '../components/slide_tab.js'
import Popup from '../components/pop_up.js'
import SignIn from '../components/LonginReg.js'

$(function() {

    setTimeout(function() {
        $("#loading").hide();
        $("#home_page").show();
        setTimeout(function(){
            $("#home_page").addClass("active");
        },100)
        $("#home_page").fullpage({
            verticalCentered: false,
            afterLoad: function(anchorLink, index) {
                $(".sec_" + index).addClass('active');
            },
        })
    },800)




    let sec2Tab = new Tab({
        el: "#sec_2_tab",
        tabNav: ".tab_nav",
        tabContents: ".tab_content",
        trigger: "mouseover"
    })
    let sec3Tab = new Tab({
        el: "#sec_3_tab",
        tabNav: ".sec_3_list",
        tabContents: ".sec_3_list_summary",
        trigger: "mouseover"
    })
    let sec5SlideTab = new SlideTab({
        el: "#sec_5_tab_slide",
        box: ".sec_5_tab_slide_nav",
        li: ".single_logo",
        tabContents: ".sec_5_tab_slide_content",
        trigger: "click"
    })
    let sec6SlideTab = new SlideTab({
        el: "#sec_6_product",
        box: ".sec_6_product_list",
        prev: ".prev",
        next: ".next",
        li: ".single_product",
    })
    $("#register").on('click', function() {
        let popReg = new Popup('#popup_sign');
        popReg.alert();
        new SignIn({
            el: '#popup_sign',
            type: 0
        })
    })
    $("#login").on('click', function() {
        let popLogin = new Popup("#popup_sign");
        popLogin.alert();
        new SignIn({
            el: "#popup_sign",
            type: 1
        })
    })
    $("#regBottom").on('click', function() {
        let popRegBottom = new Popup('#popup_sign');
        popRegBottom.alert();
        new SignIn({
            el: '#popup_sign',
            type: 0
        })
    })
})
