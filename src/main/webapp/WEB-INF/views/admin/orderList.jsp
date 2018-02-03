<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<%@ include file="include/sub_menu.jsp"%>
 
<script type="text/javascript">
  function go_order_save() {
    var count = 0;
    if (document.frm.result.length == undefined) {//
      if (document.frm.result.checked == true) {
        count++;
      }
    } else {
      for ( var i = 0; i < document.frm.result.length; i++) {
        if (document.frm.result[i].checked == true) {
          count++;
        }
      }
    }
    if (count == 0) {
      alert("주문처리할 항목을 선택해 주세요.");
    } else {
      document.frm.action = "/momstouch/admin/admin_order_save";
      document.frm.method = "post";
      document.frm.submit();
    }
  }
</script>
<article>
<h1>주문리스트</h1>
<form name="frm">
  <table style="float: right;">
    <tr>
      <td>주문자 이름 <input type="text" name="key"> 
      <input class="btn" type="button" value="검색" onclick="go_search()"><!-- 따로 구현안되있는것으로 보인다.그러나 그냥 기존에껄로 쉽게 구현가능해보임  -->
      </td>
    </tr>
  </table>
  <br>
  <table id="orderList">
  <tr>
    <th style="width:100px;">주문번호(처리여부)</th><th style="width:40px;">주문자</th><th>상품명</th><th style="width:40px;">수량</th>
    <th style="width:60px;">우편번호</th><th style="width:260px;">배송지</th><th>전화</th><th>주문일</th>
  </tr>
  <c:forEach items="${orderList}" var="orderVO">
  <tr>
    <td>
      <c:choose>
        <c:when test='${orderVO.result=="1"}'>
        <span style="font-weight: bold; color: blue">${orderVO.odseq}</span>
        (<input type="checkbox" name="result" value="${orderVO.odseq}"> 미처리)
        </c:when>
        <c:otherwise>
          <span style="font-weight: bold; color: red">${orderVO.odseq}</span>
          (<input type="checkbox" checked="checked" disabled="disabled">처리완료)
        </c:otherwise>
      </c:choose>
    </td>
    <td>${orderVO.mname}</td> <td>${orderVO.pname}</td>
    <td>${orderVO.quantity}</td> <td>${orderVO.zip_Num}</td>
    <td>${orderVO.address}</td>  <td>${orderVO.phone}</td>
    <td>
    <%--시간관련 계산로직 --%>
      <jsp:useBean id="curtime" class="java.util.Date" />
    	<fmt:formatDate value="${curtime}" pattern="yyyy-MM-dd-HH" var="current" />
    	<fmt:parseDate value="${current}" pattern="yyyy-MM-dd-HH" var="current2" />
		<fmt:parseNumber value="${current2.time / (1000*60*60) }" integerOnly="true" var="curcal"/>

    	 <fmt:formatDate value="${orderVO.indate}" pattern="yyyy-MM-dd-HH" var="orderdate" />
    	 <fmt:parseDate value="${orderdate}"    pattern="yyyy-MM-dd-HH" var="orderdate2"/>
		 <fmt:parseNumber value="${orderdate2.time /(1000*60*60) }" integerOnly="true" var="ordercal"/> 
    	
    	<c:set var="timecal" value="${curcal-ordercal }"/>
    		
    	<c:if test='${timecal < 24}'>
    		${timecal}시간 전 
    	</c:if>
    	<c:if test='${timecal >= 24}'>
    		 <fmt:parseNumber value="${timecal/24}" integerOnly="true"/>일 전 
    	</c:if>
     <%--시간관련 계산로직 --%>
	</td>
    <%-- <td><fmt:formatDate value="${orderVO.indate}" pattern="yy.MM.dd"/></td> --%>
  </tr>
  </c:forEach>
  </table>
  <input type="button" class="btn" style="width: 200px"
      value="확인처리(배달완료)" onClick="go_order_save()">
</form>
</article>
<%@ include file="include/footer.jsp"%>
</body>
</html>