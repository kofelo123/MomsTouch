<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<%@ include file="include/sub_menu.jsp"%>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
<script type="text/javascript">
  function go_search()
  {
	  document.frm.method="get";
     document.frm.action="/momstouch/admin/admin_member_list";
     document.frm.submit();
  }
</script>

<article>
<h1>회원리스트</h1>  
<form name="frm" method="post" id="formObj">
<table style="float:right; ">
  <tr>
  <td> 
  회원 이름
  <input type="text" name="key">
  <input class="btn" type="button" value="검색" onclick="go_search()">
  </td>
  </tr>
</table>  
<br>
<table id="orderList">
  <tr>
    <th> 아이디(정지여부) </th>    <th> 이름 </th>
    <th> 이메일 </th>             <th> 우편번호 </th>  
    <th> 주소 </th>  <th> 전화 </th>  <th> 가입일 </th>
  </tr>
  <c:forEach items="${memberList}" var="memberVO">  
  <tr>
    <td>${memberVO.id} 
    <c:choose>
      <c:when test='${memberVO.useyn=="y"}'>
        <input type="checkbox" name="useyn" value="${ memberVO.id}">
      </c:when>
      <c:otherwise>
        <input type="checkbox" checked="checked" disabled="disabled">
      </c:otherwise>
    </c:choose>
    </td>
    <td> ${memberVO.name} </td>
    <td> ${memberVO.email} </td> 
    <td> ${memberVO.zip_Num} </td>
    <td> ${memberVO.address} </td>
    <td> ${memberVO.phone} </td> 
    <td> <fmt:formatDate value="${memberVO.indate}"/></td>
  </tr>
  </c:forEach>
</table>
<input type="button" class="btn" id="userBan" style="width: 200px"
      value="아이디 사용정지"> <!-- onClick="ban()" -->
</form>
</article>
<%@ include file="include/footer.jsp"%>
<script>
/*
function ban(){
	var count = 0;
	console.log(count);
	if(document.frm.useyn.length == undefined && document.frm.useyn.checked == true){
		count ++;
	}else{
		for( var i = 0; i < document.frm.useyn.length; i++){
			if(document.frm.useyn[i].checked == true){
				count ++;
			}
		}
		if(count == 0){
			alert("정지시킬 계정을 선택해주세요.")
		}else{
			document.frm.action = "/momstouch/admin/userBan";
			document.frm.submit();
		}
	}
} 
 */
/* */
 $(document).ready(function(){
	$("#userBan").on("click", function(){
		
		var count = 0;
		
		var frm = $("form[name='frm']");
		
		var checkbox = $('input[name="useyn"]');
		
		/* console.log(checkbox.length); */
		
		
		if(checkbox.length == undefined && checkbox.checked == true){
			count++;
			console.log(count);
		}else{
			for( var i = 0;  i< checkbox.length; i++){
				if(checkbox[i].checked == true){
					count ++;
				}
			}
			
			if(count == 0){
				alert("정지시킬 계정을 선택해 주세요.")
			}else{
				frm.attr("method","post");
				frm.attr("action","/momstouch/admin/userBan");
				frm.submit();
			}
		}
	
		
	});
});  

/* frm.html(length) */
</script>
</body>
</html>