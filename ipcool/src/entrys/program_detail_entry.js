var VoteProto = require('../components/voteproto.js'); //投票

// import Tab from '../components/tab.js'
import GetHistory from '../components/get_value_history.js'
import FixTop from '../components/fix_top.js'
import Popup from '../components/pop_up.js'

import LineChart from '../charts/line.js' 
import RadarChart from '../charts/radar.js'
import PieChartMutiple from '../charts/pieMutiple.js'
import PieChartDouble from '../charts/pieDouble.js'
import BarChartVertical from '../charts/barVertical.js'
import BarCommit from '../charts/barComment.js'


$(function() {
    //列表切换
    // new Tab({ el: '.program_tab' });
    //导航置顶
    new FixTop();
    //找到ip名字id
    let ip_name = $('.program_info .content h1.name').html();
    let ip_Id = $('#getIpid').val();
    //异步趋势历史最高
    let compositeValues = new GetHistory($('#composite_values'), 5);
    let hotValues = new GetHistory($('#hot_values'), 1);
    let developValues = new GetHistory($('#develop_values'), 2);
    let propagateValues = new GetHistory($('#propagate_values'), 3);
    let reputationValues = new GetHistory($('#reputation_values'), 4);

    let voteproto = new VoteProto({
        el: '.vote_container .vote_content',
        ip_Id: ip_Id
    });
    //图表们
    //综合指数
    let comprehensiveValue = new LineChart({
        el: 'chart_comprehensive_value',
        name: ip_name,
    });
    //潜力模型
    let potentialModel = new RadarChart({
        el: 'chart_potential_model',
        name: ip_name
    });
    //热度趋势
    let heatTrend = new LineChart({
        el: 'chart_heat_trend',
        name: ip_name
    });
    //传播能力趋势
    let transmissionIndex = new LineChart({
        el: 'chart_transmission_index',
        name: ip_name
    });
    //新闻媒体平台
    let mediaPlatform = new PieChartMutiple({
        el: 'chart_media_platform',
        left: 'center',
        name: ip_name,
    });
    // 社交平台
    let socialPlatform = new PieChartDouble({
        el: 'chart_social_platform',
        type: 'social',
        left: 'center',
        name: ip_name,
    });
    // 用户活跃度趋势
    let userVitalty = new LineChart({
        el: 'chart_user_vitalty',
        name: ip_name
    });
    // 性别比例分布
    let sexDistribution = new PieChartDouble({
        el: 'chart_sex_distribution',
        type: 'sex',
        left: 'center',
        name: ip_name,
    });
    //年龄分布
    let ageDistribution = new BarChartVertical({
        el: 'chart_age_distribution',
        type: 'age',
        left: 'center',
        name: ip_name,
    });
    //点评图表
    let commentReviews = new BarCommit({
        el: 'chart_reviews',
        name: ip_name
    });
})
