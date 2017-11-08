<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ include file="../include/header.jsp" %>   
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.html" %>   
  <article>
  <hr>
    <center><h2>Join Us</h2></center>
    <form id="join" action="NonageServlet?command=join" method="post" name="formm">
      <fieldset>
        <legend>기본정보</legend>
        <label>아이디</label>
        <input type="text"      name="id"        size="12"  >
        <input type="hidden"    name="reid">
        <input type="button"    value="중복 체크"  class="dup" onclick="idcheck()"><br>
        <label>비밀번호</label> 
        <input type="password"  name="pwd"><br> 
        <label>비밀번호 확인</label> 
        <input type="password"  name="pwdCheck"><br> 
        <label>이름</label>
        <input type="text"      name="name"><br> 
        <label>이메일</label>
        <input type="text"      name="email" size="12">&nbsp;@ <input type="text"      name="email2" size="12">
        	<select name="company" onclick="mailcheck()">
   				<option value="직접입력" selected="selected">직접입력</option>
    			<option value="naver.com">네이버</option>
    			<option value="daum.net" >다음</option>
    			<option value="nate.com" >네이트</option>
    			<option value="hotmail.com" >핫메일</option>
    			<option value="yahoo.com" >야후</option>
   				<option value="empas.com">엠파스</option>
   				<option value="dreamwiz.com">드림위즈</option>
   				<option value="gmail.com">지메일</option>
			</select>
        <br>
        
      </fieldset>
      <fieldset>
        <legend>추가정보</legend>
        <label>우편번호</label> 
        <input type="text"       name="zipNum"   size="10" >      
        <input type="button"     value="우편 번호" class="dup" onclick="post_zip()"><br>
        <label>주소</label> 
        <input type="text"        name="addr1"   size="50">
        <input type="text"        name="addr2"   size="35" style="margin-left:140px" > <br>
        <label>휴대전화</label> 
        <select name="phone1">
   				<option value="010" selected="selected">010</option>
    			<option value="011">011</option>
    			<option value="016" >016</option>
    			<option value="017" >017</option>
    			<option value="018" >018</option>
    			<option value="019" >019</option>
			</select>
        -&nbsp;<input  type="text"       name="phone2" size="6">&nbsp;-&nbsp;<input  type="text"  name="phone3" size="6"><br>
      </fieldset>
      <div class="clear"></div>
      <div id="buttons">
        <input type="button"    value="회원가입"   class="submit" onclick="go_save()"> 
        <input type="reset"      value="취소"     class="cancel">
      </div>
    </form>
  </article>
<%@ include file="../include/footer.jsp" %>
  