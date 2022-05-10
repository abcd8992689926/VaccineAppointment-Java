//傳送表單
function sendForm() {
    var id = $("#id_input").val();
    var NHI_1 = $("*[name='NHI_1']").val();
    var NHI_2 = $("*[name='NHI_2']").val();
    var NHI_3 = $("*[name='NHI_3']").val();
    var NHI = NHI_1+NHI_2+NHI_3;
    var url = "api/user/login";
    params = {id: id, NHI: NHI};
    $.post(url, params, function(respone){
        if (respone=="0001"||respone=="0002"||respone=="9999"){
            alert("輸入的資料有誤，請重新輸入");
            $('#id_input').val('');
            $('.NHI_id_input').val(''); 
        }else{
            window.sessionStorage.setItem('jwt', respone);
            window.location.href="choice";
        } 
    }).fail(function(){
        alert("輸入的資料有誤，請重新輸入");
        $('#id_input').val('');
        $('.NHI_id_input').val('');
    });
}
//取得register數值
function getRegistered(token){
	var url = "api/user/getRegistered";
	var bearer = "Bearer "+token;
	params = {token: token};
    $.ajax({
		type: "POST",
		url: url,
		data: params,
		beforeSend: function(request){
			request.setRequestHeader("Authorization", bearer);
		},
		success: function(respone) {
			verifyChoice(respone);
		}
	});
}
//登出系統
function logout() {
	sessionStorage.removeItem("jwt");
    window.location.href="index";
}
//轉址至register
/*function register() {
    window.location.href = "register.php";
}*/
//轉址至appointment
function appointment() {
    var url = "qualification_check.php";
    $.post( url, function ( data ) {
        if (data=="verify"){
            window.location.href='appointment.php';
        }
        else{
            alert("您不符合本輪預約資格");
        }
    });
}
//判定為意願登記或預約
function verifyChoice(register){
    if (!register){
        $("#left_btn_a").show();
    }
}
//確認是否登入
function verifyLogin(token){
	var url = "jwt/verify";
	var bearer = "Bearer "+token;
    $.ajax({
		type: "POST",
		url: url,
		beforeSend: function(request){
			request.setRequestHeader("Authorization", bearer);
		},
		error: function(){
			window.location.href="index";
		}
	});
}
//取得Token
function getToken(){
	return window.sessionStorage.getItem('jwt');
}
//確認是否登入
function Logined(token){
	var url = "jwt/verify";
	var bearer = "Bearer "+token;
    $.ajax({
		type: "POST",
		url: url,
		beforeSend: function(request){
			request.setRequestHeader("Authorization", bearer);
		},
		success: function() {
            window.location.href="choice";
		}
	});
}