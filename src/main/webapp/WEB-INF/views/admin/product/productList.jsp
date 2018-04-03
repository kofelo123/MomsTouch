<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!-- paging을 위한 부트스트랩 적용(겹치는게 있었어서 부트스트랩의 페이징 부분을 뽑아서 기존의 css등에 넣었다  -->
<!-- <link rel="stylesheet" href="/momstouch/resources/bootstrap/css/bootstrap.css"> -->
<!-- <script type="text/javascript" src="/momstouch/resources/bootstrap/js/bootstrap.js"></script> -->

<%@ include file="../include/header.jsp"%>
<%@ include file="../include/sub_menu.jsp"%>




<article>
<h1>상품리스트</h1>	
<form name="frm" method="get">
<table>
  <tr>
  <td width="642">
      상품명 
     <input type="text" name="keyword">
     <input class="btn" type="button" name="btn_search" value="검색" onClick="go_search()">
     <input class="btn" type="button" name="btn_total" value="전체보기 " onClick="go_total()">
     <input class="btn" type="button" name="btn_write" value="상품등록" onClick="go_wrt()">
  </td>
  </tr>
</table>
<table id="productList">
    <tr>
        <th>번호</th><th>상품명</th><th>원가</th><th>판매가</th><th>등록일</th><th>사용유무</th>
    </tr>
    <c:choose>
    <c:when test="${productListSize<=0}">
    <tr>
      <td width="100%" colspan="7" align="center" height="23">
        등록된 상품이 없습니다.
      </td>      
    </tr>
    </c:when>
	<c:otherwise>
	<c:forEach items="${list}" var="productVO">
    <tr>
   
      <td height="23" align="center" >${productVO.pseq}</td>
      <td style="text-align: left; padding-left: 50px; padding-right: 0px;">   
        <a href="#" onClick="go_detail('${pageMaker.cri.page}', '${productVO.pseq}')">
    	 ${productVO.name}     
   		</a>
   	  </td>
      <td><fmt:formatNumber value="${productVO.price1}"/></td>
      <td><fmt:formatNumber value="${productVO.price2}"/></td>
      <td><fmt:formatDate value="${productVO.indate}"/></td>
      <td>
      	<c:choose>
   	 		<c:when test='${productVO.useyn=="1"}'>미사용</c:when>
   	 		<c:otherwise>사용</c:otherwise>   	 		
   	 	</c:choose>	 
   	  </td> 
    </tr>
    </c:forEach>
    <%-- <tr><td colspan="6" style="text-align: center;"> ${paging} </td></tr> --%>
   
	</c:otherwise>	
</c:choose>  
</table>
 <nav>
    	<ul class="pagination">
    	
    		<c:if test="${pageMaker.prev}">
    			<li><a href="${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li> <!-- 링크에 pageMaker.makeSearch로 gage정보들을 넣어준다.  -->
    		</c:if>
    		
    		<c:forEach begin="${pageMaker.startPage}"
    			end="${pageMaker.endPage }" var="idx">
				<li 
					<c:out value="${pageMaker.cri.page == idx?' class = active':''}"/>>
				    <a href="${pageMaker.makeSearch(idx) }">${idx}</a>			
	    	</li>
	    	</c:forEach>
	    	
	    	<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
	    		<li><a
	    			href="${pageMaker.makeSearch(pageMaker.endPage + 1) }">&raquo;</a></li>
	    	</c:if>
    	</ul>
    </nav>
</form> 
</article>
<%@ include file="../include/footer.jsp"%>
</body>
</html>

<script> 
var delmsg = "${delmsg}";
	if(delmsg == 'DELETESUCCESS'){
		alert("삭제가 완료되었습니다.");
	}
</script>