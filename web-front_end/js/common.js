function GetQueryString(name ){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");

    var r = window.location.search.substr(1).match(reg);

    if(r!=null)return  unescape(r[2]); return null;
}
function btn() {
    window.location.href = 'demo.html';
}
function my_btn() {
    window.location.href = 'UserHome.html';
}
function find_btn() {
    window.location.href = 'index.html';
}
function fllow_btn() {
    window.location.href = 'UserFllow.html';
}

