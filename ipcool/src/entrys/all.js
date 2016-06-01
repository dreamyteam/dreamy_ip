import SignIn from '../components/LonginReg.js'
import Popup from '../components/pop_up.js'
import BackTop from '../components/back_top.js';
import HoverDelay from '../components/hover_delay.js';

$(function() {

    let userMenu = new HoverDelay({
        el:"#currentUser",
        target:"#user_menu"
    });
    $("#register").on('click', function() {
        var popReg = new Popup('#popup_sign');
        popReg.alert();
        new SignIn({
            el: '#popup_sign',
            type: 0
        })
    })
    $("#login").on('click', function() {
        var popLogin = new Popup("#popup_sign");
        popLogin.alert();
        new SignIn({
            el: "#popup_sign",
            type: 1
        })
    })
    new BackTop(); //返回顶部
})
