var VoteProto = require('../components/voteproto.js'); //投票
// import Tab from '../components/tab.js'
import FixTop from '../components/fix_top.js'
import Popup from '../components/pop_up.js'
import ScrollLoad from '../components/scroll_load.js'

$(function() {
    //列表切换
    // new Tab({ el: '.program_tab' });
    //导航置顶
    new FixTop();
    //找到ip名字id
    let ip_name = $('.program_info .content h1.name').html();
    let ip_Id = $('#getIpid').val();

    let scrollLoad = new ScrollLoad({
        els:".scroll_load",
        history:".scroll_load_history",
        chart:".scroll_load_chart"
    });

    let voteproto = new VoteProto({
        el: '.vote_container .vote_content',
        ip_Id: ip_Id
    });
})
