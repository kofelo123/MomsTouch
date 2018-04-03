<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../include/header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.jsp" %>       
  <article>
    <h2> MENU </h2>     
    
   
   
    
    <c:forEach items="${productKindList }"  var="productVO">
    
     <!-- 폴더이름와 kind를   일치시켜주면 아래와 같은 수고를 덜 수 있긴한데 폴더명을 한글로 해도 관례적으로 문제가 없는지..-->
     	<c:choose>
			<c:when test="${productVO.kind == '치킨 메뉴' }">
				<c:set var="subfolder" value="/menu_1"/>  
			</c:when>
			<c:when test="${productVO.kind == '씨푸드 메뉴' }">
				<c:set var="subfolder" value="/menu_2"/>  
			</c:when>
			<c:when test="${productVO.kind == '버거 메뉴' }">
				<c:set var="subfolder" value="/menu_3"/>  
			</c:when>
			<c:when test="${productVO.kind == '사이드 메뉴' }">
				<c:set var="subfolder" value="/menu_4"/>  
			</c:when>
			<c:when test="${productVO.kind == '음료류' }">
				<c:set var="subfolder" value="/menu_5"/>  
			</c:when>
    	</c:choose>
      <div id="item">
        <a href="/momstouch/product/product_detail?pseq=${productVO.pseq}"> 
          <img src="/momstouch/resources/image/momstouch${subfolder }/${productVO.image}" />
          <h3>${productVO.name} </h3>        
          <p>${productVO.price2} </p>
        </a>  
      </div>
     <c:set var="kindpage" value="${productVO.kind}" /><!-- 동적 페이지로 만들다보니 문제가 생김으로인해, paginate에 다음페이지로 지정하기 위한 kind를 만들어내기 위해서 내가 임시로 만듬.  -->
    </c:forEach>    
    <div class="clear"></div>
    <hr>
   
    <nav id="paginav">
    	<ul class="pagination">
    	
    		<c:if test="${pageMaker.prev }">
    			<li><a href="${pageMaker.makeQuery(pageMaker.startPage - 1) }&kind=${productKindList[0].kind}">&laquo;</a></li><!-- makeSearch와 makeQuery가 있는데 검색이 아니므로 makeQuery썼다.  -->
    		</c:if>
    		
    		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
    			<li <c:out value="${pageMaker.cri.page == idx?' class = active':''}"/>> <!-- <li class=active> or <li> 이런식으로된다  -->
    			 <a href="${pageMaker.makeQuery(idx) }&kind=${productKindList[0].kind}">${idx}</a>
    		</c:forEach>
    		
    		<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
    			<li><a href="${pageMaker.makeQuery(pageMaker.endPage + 1) }&kind=${productKindList[0].kind}">&raquo;</a></li>
    		</c:if>
    	</ul>
    </nav>
     </article>
    
<%--   <!-- paginate 시작 -->
  <div class="paginate">
		<a href='#' class='page_first' onfocus='this.blur()'> 
		<img src="/momstouch/resources/image/momstouch/paginate/btn_first.gif" alt='처음'></a>&nbsp;&nbsp;
		
		<a href='#' class='page_pre' onfocus='this.blur()'>
		<img src='/momstouch/resources/image/momstouch/paginate/btn_pre.gif' alt='이전'></a>&nbsp;&nbsp;<strong>1</strong>&nbsp;
		
		<a href="/product/category2?kind=${kindpage}">2</a>&nbsp;
		<a href='/product/category3?kind=${kindpage}'>3</a>&nbsp;&nbsp;
		
		<a href="/product/category2?kind=${kindpage}" class='page_next' onfocus='this.blur()'>
		<img src='/momstouch/resources/image/momstouch/paginate/btn_next.gif' alt='다음' /></a>&nbsp;
		
		<a href='/product/category3&kind=${kindpage}'	class='page_end' onfocus='this.blur()'>
		<img src='/momstouch/resources/image/momstouch/paginate/btn_end.gif' alt='끝' /></a>
		
	</div>
	<!--paginate 끝-->
	 --%>
  

<%@ include file="../include/footer.jsp" %>    