<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>별마당도서관에 방문하신 것을 환영합니다!</title>
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

#searchBox, #leftRightArea {
	width: 25%;
	margin: auto;
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
var booknum = "";
var startNum = 0;
var sLength = 5;
var numberTh;
$(function(){
	
	$("#printbtn").click(function(){
		window.location="/libs/";
	});
	
	$(document).on("click","#bookDetailBtn",function(){
		booknum = $(this).attr("booknum");
		boardDetail();
	});
	
	$("#selectPaging").change(function(){
		var selectPaging = $("#selectPaging").val();
		$.ajax({
			url:"selectPaging",
			data:{
				selectPaging:selectPaging
			},
			success:function(serverData){
				if(serverData=="yes"){
					window.location="/libs/selectBook";
				}
			}
		});
	});
	
	$(document).on("click","#toLeftBtn",function(){
		numberTh-=5;
		printBorrowList(numberTh);
	});
	
	$(document).on("click","#toRightBtn",function(){
		numberTh+=5;
		printBorrowList(numberTh);
	});
	
	$(document).on("click","#borrowList",function(){
		numberTh=4;
		printBorrowList(numberTh);
	});
	
	$(document).ready(function(){
		$(".top").click(function(){
		return $("html, body").animate({scrollTop:0},300),!1})});


	function printBorrowList(numberTh){
		$("#printArea").html("");
		
		$.ajax({
			url:"selectLend",
			data:{
				numberTh:numberTh
			},
			success:function(serverData){
				if(serverData.length==0){
					alert("출력할 대출 이력이 존재하지 않습니다");
					window.location="/libs/";
				}
				var str="";
				var status = "";
				var startdate = "";
				var endReturnDate = "";
				var delay="";
			
				str+="<br>";
				str+="<div id = 'leftRightArea'>";
				str+="<input id = 'toLeftBtn' type = 'button' value = '&lt;' style='width:100pt;height:25pt;'>";
				str+="<input id = 'toRightBtn' type = 'button' value = '&gt;' style='width:100pt;height:25pt;'>";
				str+="</div>";
				str+="<br>";
				str+="<table>";
				str+="<tr>";
				str+="<th>대출일</th>";
				str+="<th>반납일</th>";
				str+="<th>이미지</th>";
				str+="<th>제목</th>";
				str+="<th>상태</th>";
				str+="</tr>";
				for(var i = 0 ; i < serverData.length ; i++){
	
					status = serverData[i].status;
					startdate = serverData[i].startdate;
					delay = serverData[i].delay;
					
					switch(status){
					case "reserved":
						status="대출신청중";
						startdate="";
						endReturnDate="";
						break;
					case "lent":
						status="대출 중";
						if(delay>-4){
							status+="<br><input id = 'pleaseReturnBtn' delay = '"+delay+"' type='button' value = '반납임박'>";
						}
						endReturnDate=serverData[i].enddate;
						break;
					case "returned":
						status="반납 완료";
						endReturnDate=serverData[i].returndate;
						break;
					case "rejected":
						status="대출 거절";
						startdate="";
						endReturnDate="";
						break;
					case "delayed":
						status="<font color=red>연체</font>";
						endReturnDate=serverData[i].enddate;
						break;
					case "delay_returned":
						status="연체되어 반납 됨";
						endReturnDate=serverData[i].returndate;
						break;
					}
				
				str+="<tr>";
				str+="<td><h2>"+startdate+"</h2></td>";
				str+="<td><h2>"+endReturnDate+"</h2></td>";
				str+="<td><img src='resources/saveimg/"+serverData[i].imageurl+"' width='150' height='150'></td>";
				str+="<td><h2>"+serverData[i].title+"</h2></td>";
				str+="<td><h2>"+status+"</h2></td>";
				str+="</tr>";
				
				}
				str+="</table>";
				$("#printArea").html("");
				$("#printArea").append(str);
			}
		});
	}


	
	$("#moveSignup").click(function(){
		window.location="/libs/moveSignup";
	});
	
	$(document).on("click","#pleaseReturnBtn",function(){
		alert("반납일까지 "+$(this).attr("delay")+" 일 남았습니다");
	});
	
	$(document).on("click","#borrowbtn",function(){
		booknum = $(this).attr("booknum");
		title = $(this).attr("title");
		$.ajax({
			url:"insertLend",
			data:{
				booknum:booknum,
				title:title
			},
			type:"POST",
			success:function(serverData){
				if(serverData=="no"){
					alert("대출 신청에 실패하였습니다");
					
				}else{
					
					alert("대출 신청이 완료되었습니다");
					$("#borrowbtnArea").html("★ 신청 완료 ★");
					
				}
			}
		});
	});
	
 function boardDetail(){
	 $.ajax({
		 
		 url:"selectOneBook",
		 data:{
			 booknum:booknum
		 },
		 success:function(serverData){
	
		 	 var str = "";
		 	 str+="<br><br><br>";
			 str+="<table>";
			 str+="<tr>";
			 str+="<td colspan='2'>";
			 str+="<img src='resources/saveimg/"+serverData.imageurl+"' width='400' height='400'>";
			 str+="</td>";
			 str+="<td>";
			 str+="</td>";
			 str+="</tr>";
			 str+="<tr>";
			 str+="<th><h1>제목</h1></th>";
			 str+="<th><h1>"+serverData.title+"</h1></th>";
			 str+="</tr>";
			 str+="<tr>";
			 str+="<th><h1>출판사</h1></th>";
			 str+="<th><h1>"+serverData.publisher+"</h1></th>";
			 str+="	</tr>";
			 str+="<tr>";
			 str+="<th><h1>저자</h1></th>";
			 str+="<th><h1>"+serverData.author+"</h1></th>";
			 str+="</tr>";
			 str+="<tr>";
			 str+="<th><h1>대출가능</h1></th>";
			 str+="<th><h1>"+serverData.status+"</h1>";
			 str+="<c:if test='${!empty sessionScope.loginId }'>";
		
			 
			 if(serverData.status=='Y'){
			
				 str+="<div id = 'borrowbtnArea'>";
				 str+="<input id = 'borrowbtn' title = '"+serverData.title+"' booknum = '"+serverData.booknum+"' type = 'button' value = '대출신청'>";
				 str+="</div>";
				 
			 }
			 
			 str+=" </c:if>";
			 str+="<br></th>";
			 str+="</tr>";
			 str+="<tr>"; 
			 str+="<th><h1>내용</h1></th>";
			 str+="<th><h1>"+serverData.content+"</h1></th>";
			 str+="</tr>";
			 str+="</table>";
			$("#printArea").html("");
			$("#printArea").append(str);
			 
		 }
	 });
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
<c:if test="${empty sessionScope.loginId }">
<form action = "login" method = "POST">
<input id = "userid" type = "text" name = "userid"><br>
<input id = "password" type = "password" name = "password"><br>
<input id = "moveSignup" type = "button" value = "회원가입">
<input id = "loginbtn" type = "submit" value = "로그인">
</form>
</c:if>
<c:if test="${!empty sessionScope.loginId }"><br>
${sessionScope.loginId }님, 로그인 하셨습니다.<br><br>
 <img src="resources/saveimg/${sessionScope.loginImage}" width="150" height="150">
<br>
회원번호<br>
${sessionScope.userNum }<br><br>
<input id = "moveSignup" type = "button" value = "회원정보수정">
<form action = "logout" method = "GET">
<input id = "logoutbtn" type ="submit" value = "로그아웃">
</form>
</c:if>
</th>
<th><input id = "printbtn" type=button style="width:300pt;height:45pt;" value="도서목록">
</th>
<th>
<c:if test="${!empty sessionScope.loginId }">
<input id = "borrowList" type=button style="width:300pt;height:45pt;" value="대출이력">
</c:if>
</th>

</table>

<div id = "printArea">
<h2>
<a href="selectBook?page=1&searchWord=${searchWord}&searchTopic=${searchTopic}">&lt;&lt;</a>
<a href="selectBook?page=${navi.startPageGroup-1}&searchWord=${searchWord}&searchTopic=${searchTopic}">&lt;</a>

<c:forEach var = "counter" begin = "${navi.startPageGroup}" end="${navi.endPageGroup}">
<a href="selectBook?page=${counter }&searchWord=${searchWord}&searchTopic=${searchTopic}">${counter }&nbsp;</a>
</c:forEach>

<a href="selectBook?page=${navi.endPageGroup+1}&searchWord=${searchWord}&searchTopic=${searchTopic}">&gt;</a>
<a href="selectBook?page=${navi.totalRecordsCount}&searchWord=${searchWord}&searchTopic=${searchTopic}">&gt;&gt;</a>

<select id = "selectPaging" style="width:200px; height:40px;">
<option value = "선택">선택</option>
<option value = "5">5</option>
<option value = "10">10</option>
<option value = "20">20</option>
</select>
개씩 표시
</h2>

<div id = "searchBox">
<form action = "selectBook" method = "get">
<select name = "searchTopic" style="width:200px; height:40px;">
<option value = '제목'>제목</option>
<option value = '출판사'>출판사</option>
<option value = '저자'>저자</option>
<option value = '대출가능'>대출가능</option>
</select>
<input type ='text' name = "searchWord" value = "${searchWord}" style="width:200px; height:40px;">
<input type = "hidden" value = "${navi.currentPage}">
<input type = 'submit' value = '검색' style="width:100px; height:40px;">
</form>
</div>
				
<table>
<tr>
<th>이미지</th>
<th>제목</th>
<th>출판사</th>
<th>저자</th>
<th>대출가능</th>
</tr>

<c:forEach items="${bList }" var = "book">
<tr>
<td>
<img id="bookDetailBtn" src="resources/saveimg/${book.imageurl }" booknum = "${book.booknum}" width="150" height="150">
</td>
<td>
<h2><a href="#" id = "bookDetailBtn" booknum = "${book.booknum}">${book.title }</a></h2>
</td>
<td>
<h2>${book.publisher}</h2>
</td>
<td>
<h2>${book.author}</h2>
</td>
<td>
<h2>${book.status }</h2>
</td>
</tr>
</c:forEach>
</table>

</div>
<br>
<button type='button' class = 'top'>맨 위로 ↑</button>
<br>
</body>
</html>
