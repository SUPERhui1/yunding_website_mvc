
$(function(){

$(".word").hover(function(){
	 let hue1 = 'rgb('
 + (Math.floor(Math.random() * 255)) + ','
 + (Math.floor(Math.random() * 255)) + ','
 + (Math.floor(Math.random() * 255)) + ')';
    $(".word").css("background-color",hue1);
},function(){
		 let hue2 = 'rgb('
 + (Math.floor(Math.random() * 255)) + ','
 + (Math.floor(Math.random() * 255)) + ','
 + (Math.floor(Math.random() * 255)) + ')';
    $(".word").css("background-color",hue2);
});

$(".divp1").hover(function () {
	$('.p1').css('display','block')
},function(){
	$('.p1').css('display','none')
});

$(".divp2").hover(function () {
	$('.p2').css('display','block')
},function(){
	$('.p2').css('display','none')
});

});

function login() {
	let username=$("input[name='user']").val();
	let password= $("input[name='pass']").val();
	let url='/api/managerlogin/doLogin';
	$.ajax(url,{
		method:'POST',
		data:{
            'managerName':username,
            'managerPassword':password,
		},
		success:(data)=>{
			if(data.code===200) {
                window.location = '/H-ui.admin/index.html';
            }else{
				alert(data.message);
			}
		},
		false:(data)=>{
			alert("登录失败")
		}
	})
}