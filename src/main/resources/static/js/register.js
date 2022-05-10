let manufacturer;
let phoneNum;
//傳送register表單
function send_register() {
	var url = "api/user/insertRegister";
	var bearer = "Bearer "+getToken();
	let county=$('#twCounty').val();
	let district=$('#twDistrict').val();
	params = {county: county, district: district, zipStr: $("#twDistrict").find("option:selected").text(),phoneNum: phoneNum, manufacturer: manufacturer, token: getToken()};
    console.log(params);
    $.ajax({
		type: "POST",
		url: url,
		data: params,
		beforeSend: function(request){
			request.setRequestHeader("Authorization", bearer);
		},
		success: function(respone) {
			alert("登記成功，將返回首頁");
			window.location.href="choice";
		},
		error: function(){
			alert("閒置過久，系統已自動登出，請重新登入");
			window.location.href="index";
		}
	});
}
function checkData(){
	manufacturer=$("input[name='vaccine[]']:checked").map(function() { return $(this).val(); }).get();
	phoneNum=$(".phone_num").val();
	if(phoneNum==""||phoneNum.length<10||phoneNum.substring(0,2)!=09){
		alert("手機號碼格式錯誤，請重新輸入");
		phoneNum=$(".phone_num").val("");
	}else if(manufacturer.length==0){
		alert("尚未選擇偏好廠牌，請至少選擇一項");
	}else{
		let manufacturer0=0;
		let manufacturer1=0;
		let manufacturer2=0;
		let manufacturer3=0;
		for(var i=0; i<manufacturer.length; i++){
			switch(manufacturer[i]){
				case "0":
					manufacturer0=1;
					break;
				case "1":
					manufacturer1=1;
					break;
				case "2":
					manufacturer2=1;
					break;
				case "3":
					manufacturer3=1;
					break;
			}
		}
		var url = "api/user/converterManufacturer";
   		var token=getToken();
   		var bearer = "Bearer "+token;
   		params = {manufacturer0: manufacturer0, manufacturer1: manufacturer1, manufacturer2: manufacturer2, manufacturer3: manufacturer3};
		$.ajax({
			type: "POST",
			url: url,
			data: params,
			beforeSend: function(request){
				request.setRequestHeader("Authorization", bearer);
			},
			success: function(respone) {
			    var saints = {
			    	0: ["電話"],
			    	1: ["意願登記廠牌"],
			    	2: ["偏好施打地區"]
			    };
				manufacturer=respone[1];
				respone=[phoneNum, respone[0], $("#twDistrict").find("option:selected").text()];
				add(respone, saints);
			},
			error: function(){
				alert("閒置過久，系統已自動登出，請重新登入");
				window.location.href="index";
			}
		});
		$("#log_background").hide();
		$("#check").show();  
	}
}
//手機號碼限制輸入數字
$(".phone_num").on("keypress keyup blur",function (event) {    
    $(this).val($(this).val().replace(/[^\d]/g,""));
    if ((event.which < 48 || event.which > 57)) {
        event.preventDefault();
    }
});
function getName(token){
	var url = "api/user/searchName";
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
			$("input[name='id']").attr({"value": respone[0]});
			$("input[name='name']").attr({"value": respone[1]});
		},
		error: function(){
			alert("閒置過久，系統已自動登出，請重新登入");
			window.location.href="index";
		}
	});
}