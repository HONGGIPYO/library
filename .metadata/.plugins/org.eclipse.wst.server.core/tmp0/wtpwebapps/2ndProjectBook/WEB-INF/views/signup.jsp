<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>별마당 도서관</title>
	<style>
	
  table, th, td, fieldset {
    text-align: center;
     margin: auto;

  }
  table {
    width: 40%;
    margin: auto;
    height: 400px;
    text-align: center;
   
  }
  input, button {
	border: 1px solid black;
	background-color: rgba(0, 0, 0, 0);
	color: black;

	font-size: 13px;
}

input:hover {
	color: black;
	background-color: #D8D8D8;
}
</style>

</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
var flag = false;
var oldPwdFlag = false;
var pwdCheckFlag = false;
$(function(){
	
	
	$("#oldPwd").change(function(){
		oldPwdFlag = false;
	});

	$("#pwdCheck1").change(function(){
		pwdCheckFlag = false;
	});
	
	$("#pwdCheck2").change(function(){
		pwdCheckFlag = false;
	});
	
	
	$("#idcheck").click(function(){
		
		$.ajax({
			
			url:"selectOneUser",
			data:{
				userid:$("#userid").val()
			},
			success:function(serverData){
				if(serverData=="yes"){
					alert("동일한 아이디가 존재합니다");
				}else{
					alert("가입 가능한 아이디입니다");
					flag = true;
				}
			}
		});
	});
	
	$("#userid").change(function(){
		flag = false;
		
	});

	$("#submitbtn").click(function(){
		
		if($("#password").val()!=$("#pwdchecked").val()){
			alert("패스워드와 패스워드 확인이 일치하지 않습니다");
		}else if(flag==false){
			alert("아이디 중복확인을 해주세요");
		}else{
			$("#signupForm").submit();
		}
		
	});
	
	$("#moveAdmin").click(function(){
		window.location="/libs/moveAdmin";
	});
	
	$("#updateBtn").click(function(){

		var userid = "${sessionScope.loginId}";
		var password = $("#oldPwd").val();
		$.ajax({
			url:"selectOneUser",
			data:{
				userid:userid,
				password:password
			},
			type:"GET",
			success:function(serverData){
				if(serverData=="no"){
					alert("입력하신 기존 패스워드가 일치하지 않습니다.");
					
				}else{
					oldPwdFlag=true;
				}
			}
		});
		
		if($("#pwdCheck1").val()!=$("#pwdCheck2").val()){
			alert("새 패스워드와 새 패스워드 확인의 값이 일치하지 않습니다.");
		}else{
			pwdCheckFlag=true;
		}
		
		if(oldPwdFlag==true&&pwdCheckFlag==true){
			$("#updateUser").submit();
		}else{
			alert("올바른 값을 입력하세요.");
		}
		
	});
	
	$("#goHomeBtn").click(function(){
		window.location="/libs/";

	});
	
	 function readURL(input) {
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();
	            
	            reader.onload = function(e) {
	                $('#foo').attr('src', e.target.result);
	            }
	            reader.readAsDataURL(input.files[0]);
	        }
	    }

	    $("#image").change(function() {
	        readURL(this);
	    });
	    
	
});


</script>
<body>
<c:if test="${empty sessionScope.loginId }">
<fieldset style="width: 1500px">
<legend><h1>회원 가입</h1></legend>
<form id = "signupForm" action = "signUp" method = "POST" enctype = "multipart/form-data">
<table>
<tr>
<th>아이디</th>
<th><input id = "userid" type  = "text" name = "userid"></th>
<th><input id = "idcheck" type = "button" value = "ID 중복체크"></th>
</tr>
<tr>
<th>패스워드</th>
<th><input id = "password" type = "password" name = "password"></th>
</tr>
<tr>
<th>패스워드 확인</th>
<th><input id = "pwdchecked" type = "password"></th>
</tr>
<tr>
<th>이름</th>
<th><input type = "text" name = "username"></th>
</tr>
<tr>
<th>생년월일</th>
<th><input type = "date" name = "birthdate"></th>
</tr>
<tr>
<th>전화번호</th>
<th>
<select name = "tel1" style="width:60px; height:25px;">
<option value = "010">010</option>
<option value = "011">011</option>
<option value = "017">017</option>
<option value = "031">031</option>
<option value = "02">02</option>
</select>
<input type = "text" name = "tel2">
<input type = "text" name = "tel3">
</th>
</tr>
<tr>
<th>사진 첨부</th>
<th><input type = "file" id = "image" name = "uploadFile"></th>
</tr>
<tr>
<th></th>
<th> <br><img id="foo"src="#" width='150' height='150'/></th>
</tr>
<tr>
<th></th>
<th><input id = "submitbtn" type = "button" value = "가입하기">  
<input type = "reset" value = "초기화">  
<input id = "goHomeBtn" type = "button" value = "뒤로 가기">
</th>
</tr>
</table>
</form>
</fieldset>
</c:if>



<c:if test="${!empty sessionScope.loginId && sessionScope.loginId != 'admin' }">
<fieldset style="width: 1500px">
<legend><h1>회원 정보 수정</h1></legend>
<form id = "updateUser" action = "updateUser" method = "POST" enctype = "multipart/form-data">
<table>
<tr>
<th>회원 번호</th>
<th>${sessionScope.userNum }</th>
</tr>
<tr>
<th>아이디</th>
<th>${sessionScope.loginId }</th>
</tr>
<tr>
<th>기존 패스워드</th>
<th><input id = "oldPwd" type = "password" name = "oldPwd"></th>
</tr>
<tr>
<th>새 패스워드</th>
<th><input id = "pwdCheck1" type = "password" name = "password"></th>
</tr>
<tr>
<th>새 패스워드 확인</th>
<th><input id = "pwdCheck2" type = "password"></th>
</tr>
<tr>
<th>이름</th>
<th>${sessionScope.userName }</th>
</tr>
<tr>
<th>생년월일</th>
<th>${sessionScope.birthDate }</th>
</tr>
<tr>
<th>전화번호</th>
<th>
<select name = "tel1" style="width:60px; height:25px;">
<option value = "010">010</option>
<option value = "011">011</option>
<option value = "017">017</option>
<option value = "031">031</option>
<option value = "02">02</option>
</select>
<input type = "text" name = "tel2">
<input type = "text" name = "tel3">
</th>
</tr>
<tr>
<th>사진</th>
<th><input type = 'file' id = 'image' name = 'uploadFile'></th>
</tr>
<tr>
<th></th>
<th> <br><img id="foo"src="#" width='150' height='150'/></th>
</tr>
<tr>
<th><br><br><br>
<input type = "hidden" value = "${sessionScope.userNum }" name = "usernum">
</th>
<th>
<input id = 'updateBtn' type = 'button' value = '정보 수정하기'>
<input type = "reset" value = "초기화">  
<input id = "goHomeBtn" type = "button" value = "뒤로 가기">
</th>
</tr>
<tr>
</table>
</form>
</fieldset>
</c:if>



<c:if test="${sessionScope.loginId == 'admin' }">
<fieldset style="width: 1500px">
<legend><h1>새로운 도서 등록</h1></legend>
<form id = "insertForm" action = "insertNewBook" method = "POST" enctype = "multipart/form-data">
<table>
<tr>
<th>책 제목</th>
<th><input type = 'text' name = 'title'></th>
</tr>
<tr>
<th>출판사</th>
<th><input type = 'text' name = 'publisher'></th>
</tr>
<tr>
<th>저자</th>
<th><input type = 'text' name = 'author'></th>
</tr>
<tr>
<th>소개</th>
<th><textarea name = 'content' cols = '50' rows='5' style="resize: none;"></textarea></th>
</tr>
<tr>
<th>사진 첨부</th>
<th><input type = 'file' id = 'image' name = 'uploadFile'></th>
</tr>		
<tr>
<th></th>
<th> <br><img id="foo"src="#" width='150' height='150'/></th>
</tr>
<tr>
<th></th>
<th>
<input id = 'insertBtn' type = 'submit' value = '등록하기'>  
<input type = "reset" value = "초기화">  
<input id = "moveAdmin" type = "button" value = "관리자 화면으로 돌아가기">
</th>
</tr>	
</table>
</form>
</fieldset>
</c:if>

</body>
</html>