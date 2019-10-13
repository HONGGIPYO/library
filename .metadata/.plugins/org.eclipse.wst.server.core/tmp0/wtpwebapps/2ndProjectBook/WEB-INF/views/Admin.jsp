<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Admin</title>
			<style>
  table, th, td {
    border: 1px solid #bcbcbc;
     text-align: center;
  }
  table {
    width: 100%;
    height: 200px;
     text-align: center;
  }
  
input, button {
	border: 1px solid black;
	background-color: rgba(0, 0, 0, 0);
	color: black;
	padding: 5px;
	font-size: 18px;
}

input:hover {
	color: black;
	background-color: #D8D8D8;
}
</style>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
var lendnum = "";
var booknum = "";
var actiondate = "";
var status = "";
var flag = 0;
$(function(){
	
	borrowRequestList();
	
	$("#logoutBtn").click(function(){
		window.location="/libs/logout";
		
		
	});
	
	$("#insertNewBook").click(function(){
		window.location="/libs/moveSignup";
		
	});
	
	$("#borrowRequestList").click(function(){
		flag = 0;
		borrowRequestList();
	});
	
	$("#borrowList").click(function(){
		flag = 1;
		borrowRequestList();
	});
	
	$("#delayList").click(function(){
		flag = 2;
		borrowRequestList();
		
	});
	
	$(document).on("click","#insertBtn",function(){
		
		
		
	});
	
	
	
	$(document).on("click","#searchbtn",function(){

		$.ajax({
			url:"searchBorrowRequest",
			data:{
				selectSearch:$("#selectSearch").val(),
				searchText:$("#searchText").val(),
		
			},
			success:function(serverData){
				

				$("#printArea").html("");
				var str="";
		
				if(flag==0){

				str+="<tr>";
				str+="<th>책제목</th>";
				str+="<th>신청자</th>";
				str+="<th>액션</th>";
				str+="</tr>";
				for(var i = 0 ; i < serverData.length ; i++){
					if(serverData[i].status=='reserved'){
						
				str+="<tr>";
				str+="<td>"+serverData[i].title+"</td>";
				str+="<td>"+serverData[i].username+"</td>";
				str+="<td>";
				str+="<input id = '"+serverData[i].lendnum+"' type = 'number'>일간<br>";
				str+="<input id = 'agreebtn' booknum = '"+serverData[i].booknum+"' lendnum = '"+serverData[i].lendnum+"' type = 'button' value = '승인'>";
				str+="<input id = 'rejectbtn' lendnum = '"+serverData[i].lendnum+"' status = '"+serverData[i].status+"' booknum = '"+serverData[i].booknum+"' type = 'button' value = '반려'>";
				str+="</td>";
				str+="</tr>";
					}
				
				}
				}else if(flag==1){
					str+="<tr>";
					str+="<th>책제목</th>";
					str+="<th>대출자</th>";
					str+="<th>시작일</th>";
					str+="<th>종료일</th>";
					str+="<th>액션</th>";
					str+="</tr>";
					for(var i = 0 ; i < serverData.length ; i++){
						if(serverData[i].status=='lent'){
							
						
					str+="<tr>";
					str+="<td>"+serverData[i].title+"</td>";
					str+="<td>"+serverData[i].username+"</td>";
					str+="<td>"+serverData[i].startdate+"</td>";
					str+="<td>"+serverData[i].enddate+"</td>";
					str+="<td>";
					str+="<input id = 'returnbtn' type = 'button' value = '반납' booknum = '"+serverData[i].booknum+"' lendnum = '"+serverData[i].lendnum+"' status = '"+serverData[i].status+"'>";
					str+="</td>";
					str+="</tr>";
						}
				}
		
			}else if(flag==2){
				str+="<tr>";
				str+="<th>책제목</th>";
				str+="<th>대출자</th>";
				str+="<th>시작일</th>";
				str+="<th>종료일</th>";
				str+="<th>연체일수</th>";
				str+="<th>액션</th>";
				str+="</tr>";
				for(var i = 0 ; i < serverData.length ; i++){
					if(serverData[i].status=='delayed'){
						
					
				str+="<tr>";
				str+="<td>"+serverData[i].title+"</td>";
				str+="<td>"+serverData[i].username+"</td>";
				str+="<td>"+serverData[i].startdate+"</td>";
				str+="<td>"+serverData[i].enddate+"</td>";
				str+="<td>"+serverData[i].delay+"</td>";
				str+="<td>";
				str+="<input id = 'returnbtn' type = 'button' value = '반납' booknum = '"+serverData[i].booknum+"' lendnum = '"+serverData[i].lendnum+"' status = '"+serverData[i].status+"'>";
				str+="</td>";
				str+="</tr>";
					}
			}
				
			}
				
				$("#printArea").html(str);
	
				
			}
		});
	});
	

	
	$(document).on("click","#agreebtn",function(){
		lendnum = $(this).attr("lendnum");
		actiondate = $("#"+lendnum).val();
		booknum = $(this).attr("booknum");

		$.ajax({
			url:"updateLend",
			data:{
				actiondate:actiondate,
				lendnum:lendnum,
				booknum:booknum
			},
			type:"POST",
			success:function(serverData){
				if(serverData=="no"){
					alert("이미 다른 회원에게 대여된 책입니다.");
					
				}else{
					borrowRequestList();
					
				}
			}
		});
	});
	
	
	$(document).on("click","#rejectbtn",function(){
		
		lendnum = $(this).attr("lendnum");
		status = $(this).attr("status");
		booknum = $(this).attr("booknum");

		$.ajax({
			url:"updateStatus",
			data:{
				lendnum:lendnum,
				status:status,
				booknum:booknum
			},
			type:"POST",
			success:function(serverData){
				if(serverData=="no"){
					alert("대출 신청 반려에 실패하였습니다");
				}else{
					alert("반려되었습니다.");
					borrowRequestList();
				}
			}
		});
	});
	
	$(document).on("click","#returnbtn",function(){
		
		lendnum = $(this).attr("lendnum");
		status = $(this).attr("status");
		booknum = $(this).attr("booknum");
	
		
		$.ajax({
			url:"updateStatus",
			data:{
				lendnum:lendnum,
				status:status,
				booknum:booknum
				
			},
			type:"POST",
			success:function(serverData){
				if(serverData=="no"){
					alert("반납 처리에 실패하였습니다");
				}else{
					alert("반납되었습니다.");
					flag = 1;
					borrowRequestList();
				}
				
				
			}
			
			
			
			
		});
	});
	
	
	function borrowRequestList(){
		
		$.ajax({
			url:"selectAllLend",
			success:function(serverData){
				$("#printArea").html("");
				var str="";
		
				if(flag==0){

				str+="<tr>";
				str+="<th>책제목</th>";
				str+="<th>신청자</th>";
				str+="<th>액션</th>";
				str+="</tr>";
				for(var i = 0 ; i < serverData.length ; i++){
					if(serverData[i].status=='reserved'){
						
				str+="<tr>";
				str+="<td>"+serverData[i].title+"</td>";
				str+="<td>"+serverData[i].username+"</td>";
				str+="<td>";
				str+="<input id = '"+serverData[i].lendnum+"' type = 'number'>일간<br>";
				str+="<input id = 'agreebtn' lendnum = '"+serverData[i].lendnum+"' booknum = '"+serverData[i].booknum+"' type = 'button' value = '승인'>";
				str+="<input id = 'rejectbtn' lendnum = '"+serverData[i].lendnum+"' status = '"+serverData[i].status+"' booknum = '"+serverData[i].booknum+"' type = 'button' value = '반려'>";
				str+="</td>";
				str+="</tr>";
					}
				
				}
				}else if(flag==1){
					str+="<tr>";
					str+="<th>책제목</th>";
					str+="<th>대출자</th>";
					str+="<th>시작일</th>";
					str+="<th>종료일</th>";
					str+="<th>액션</th>";
					str+="</tr>";
					for(var i = 0 ; i < serverData.length ; i++){
						if(serverData[i].status=='lent'){
							
						
					str+="<tr>";
					str+="<td>"+serverData[i].title+"</td>";
					str+="<td>"+serverData[i].username+"</td>";
					str+="<td>"+serverData[i].startdate+"</td>";
					str+="<td>"+serverData[i].enddate+"</td>";
					str+="<td>";
					str+="<input id = 'returnbtn' type = 'button' value = '반납' booknum = '"+serverData[i].booknum+"' lendnum = '"+serverData[i].lendnum+"' status = '"+serverData[i].status+"'>";
					str+="</td>";
					str+="</tr>";
						}
				}
		
			}else if(flag==2){
				str+="<tr>";
				str+="<th>책제목</th>";
				str+="<th>대출자</th>";
				str+="<th>시작일</th>";
				str+="<th>종료일</th>";
				str+="<th>연체일수</th>";
				str+="<th>액션</th>";
				str+="</tr>";
				for(var i = 0 ; i < serverData.length ; i++){
					if(serverData[i].status=='delayed'){
						
					
				str+="<tr>";
				str+="<td>"+serverData[i].title+"</td>";
				str+="<td>"+serverData[i].username+"</td>";
				str+="<td>"+serverData[i].startdate+"</td>";
				str+="<td>"+serverData[i].enddate+"</td>";
				str+="<td>"+serverData[i].delay+"</td>";
				str+="<td>";
				str+="<input id = 'returnbtn' type = 'button' value = '반납' booknum = '"+serverData[i].booknum+"' lendnum = '"+serverData[i].lendnum+"' status = '"+serverData[i].status+"'>";
				str+="</td>";
				str+="</tr>";
					}
			}
				
			}
				
				
				
		
				$("#printArea").html(str);
	
	}
	
	});
	
}
	
	function insertNewBookForm(){
		var str = "";
		str+="<form id = 'insertBtn' action = 'insertNewBook' method = 'POST' enctype = 'multipart/form-data'>";
		str+="책 제목 : <input type = 'text' name = 'title'><br>";
		str+="출판사 : <input type = 'text' name = 'publisher'><br>";
		str+="저자 : <input type = 'text' name = 'author'><br>";
		str+="내용 : <textarea name = 'content' cols = '50' rows='5'></textarea><br>";
		str+="책의 이미지를 첨부하세요<br>";
		str+="<input type = 'file' id = 'image' name = 'uploadFile'";
		str+="<br>";
		str+="<br>";
		str+="<input id = 'insertBtn' type = 'button' value = '등록하기'>";
		
		str+="</form>";

		$("#searchArea").html("");
		$("#printArea").html(str);
		
	}

});
</script>
<body>
<h1>
	별마당 도서관 
</h1>



<table>
<tr>
<th>
<h3>
${sessionScope.loginId }님, <br>
</h3>
<h5>
관리자 로그인 중입니다.
</h5>
<input id = "logoutBtn" type = "button" value = "로그아웃">
</th>
<th>
<input	 id = "insertNewBook" type=button style="width:250pt;height:45pt;" value="새로운 도서 등록하기">
</th>
<th><input	 id = "borrowRequestList" type=button style="width:250pt;height:45pt;" value="대출 신청 목록">
</th>
<th>
<input id = "borrowList" type=button style="width:250pt;height:45pt;" value="대출목록">
</th>
<th>
<input id = "delayList" type=button style="width:250pt;height:45pt;" value="연체목록">
</th>
</tr>
<!-- <tr>
<td></td>
<td></td>
<td></td>
<td></td>
</tr> -->
</table>


	<br>
	<br>
	<div id = "searchArea">
	<form>
		<select id='selectSearch' style="width:125px; height:35px;">
			<option value='책 제목'>책 제목</option>
			<option value='신청자'>신청자</option>
		</select> <input id='searchText' type='text' name='value'> 
		<input	id='searchbtn' type='button' value='검색'>
	</form>
	</div>
	<table id = "printArea">
	
	</table>
	
	


	<!-- 	<div id="printArea"></div> -->
</body>
</html>
