<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Momstouch Admin</title>
<link rel="stylesheet" href="/momstouch/resources/css/admin.css">
<script type="text/javascript">
function worker_check()
{
  if(document.frm.workId.value==""){
      alert("아이디를 입력하세요.");
      return false;
  }else if(document.frm.workPw.value==""){
     alert("비밀번호를 입력하세요.");
      return false;
    }
    return true;  
}
</script>
</head>

<body>
  <div id="wrap">
    <header>      
      <div id="logo">
        <a href="/momstouch/admin/admin_login_form"> 
        <img src="/momstouch/resources/image/bar_1.gif">
        </a>
      </div>      
    </header>
    <div class="clear"></div>
    <article>
      <div id="loginform">
      <form name="frm" method="post" 
action="/momstouch/admin/admin_login">
      <table>
        <tr>
          <td> 아 이 디 </td>
          <td> <input type="text" name="workerId" size="10" value=""></td> <!--  id:admin pw:admin -->
        </tr>
        <tr>
          <td> 비밀번호 </td>
          <td> 
            <input type="password" name="workerPwd" size="10" value="">
          </td>
        </tr>
        <tr align="center" >
          <td  colspan="2">          
            <input class="btn" type="submit" value="업무 로그인" 
onclick="return worker_check()"><br><br><!--  return을 사용하고 실제 스크립트에서 return을 불리언으로 반환함에따라 이벤트를 실행여부를 결정한다.  -->
            <h4 style="color:red">${message}</h4>
          </td>
        </tr>
      </table>
      </form>
      </div>
    </article>
  </div>
</body>
</html>