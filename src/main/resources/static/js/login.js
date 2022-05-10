//健保卡輸入自動跳下一格
var demo=document.getElementById('NHI_input');
input=demo.getElementsByTagName('input');
var iNow=0;
type   = !-[1,] ? 'onpropertychange' : 'oninput',
    limit  = 4;
    for(var i=0;i<input.length-1;i++){
        input[i].index=i;
        input[i][type]=function () {
        iNow=this.index;
        var that=this;
        setTimeout(function () {
        that.value.length>limit-1&&input[iNow+1].focus();
        },0)
    }
}   
//健保卡限制輸入數字
$(".NHI_id_input").on("keypress keyup blur",function (event) {    
    $(this).val($(this).val().replace(/[^\d]/g,""));
    if ((event.which < 48 || event.which > 57)) {
        event.preventDefault();
    }
});
//計算城市加權和
function calCity(id) {
	var checkHead = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
    var city = id.toString().substring(0,1);
    var ret = 0;
    t = checkHead.indexOf(city)+10;
    ret += Math.floor(t/10);
	ret += t%10*9;
    return ret;
}
//計算流水號加權和
function calMid(id) {
    var weighted = 8;
    var tal = 0;
    for (i = 1 ;i < 9 ;i++){
        tal += (id.toString().substring(i,i+1)) * weighted;
        weighted--;
    }
    return tal;
}
//輸出檢查碼
function inputCheckNum(id) {
    var checknum = id.toString().substring(9,10);
    return checknum;
}
//計算所有總和並取餘數
function calTal(id) {
    var ret = calCity(id)+calMid(id);
    ret = ret % 10;
    return ret;
}
//驗證身分證號
function check() {
    var id_input = document.getElementById("id_input").value;
    var ret = calTal(id_input);
    if (ret == 0 && inputCheckNum(id_input) == 0){
        sendForm();
    }else if ((10-ret)!=inputCheckNum(id_input)){
        alert("身分證驗證錯誤，請重新輸入");
        $("#id_input").val("");
        $(".NHI_id_input").val("");
    }else{
        sendForm();
    }
}