<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../include/header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.jsp" %>       
  <article>
    <h2> MENU </h2>     
    <c:forEach items="${productKindList }"  var="productVO">
      <div id="item">
        <a href="/product/product_detail?pseq=${productVO.pseq}"> 
          <img src="/resources/image/momstouch/menu_${productVO.kind}/${productVO.image}.jpg" />
          <h3>${productVO.name} </h3>        
          <p>${productVO.price2} </p>
        </a>  
      </div>
     <c:set var="kindpage" value="${productVO.kind}" /><!-- 동적 페이지로 만들다보니 문제가 생김으로인해, paginate에 다음페이지로 지정하기 위한 kind를 만들어내기 위해서 내가 임시로 만듬.  -->
    </c:forEach>    
    <div class="clear"></div>
    <hr>
  <!-- paginate 시작 -->
  <div class="paginate">
		<a href='#' class='page_first' onfocus='this.blur()'> 
		<img src="/resources/image/momstouch/paginate/btn_first.gif" alt='처음'></a>&nbsp;&nbsp;
		
		<a href='#' class='page_pre' onfocus='this.blur()'>
		<img src='/resources/image/momstouch/paginate/btn_pre.gif' alt='이전'></a>&nbsp;&nbsp;<strong>1</strong>&nbsp;
		
		<a href="/product/category2?kind=${kindpage}">2</a>&nbsp;
		<a href='/product/category3?kind=${kindpage}'>3</a>&nbsp;&nbsp;
		
		<a href="/product/category2?kind=${kindpage}" class='page_next' onfocus='this.blur()'>
		<img src='/resources/image/momstouch/paginate/btn_next.gif' alt='다음' /></a>&nbsp;
		
		<a href='/product/category3&kind=${kindpage}'	class='page_end' onfocus='this.blur()'>
		<img src='/resources/image/momstouch/paginate/btn_end.gif' alt='끝' /></a>
		
	</div>
	<!--paginate 끝-->
  </article>

<%@ include file="../include/footer.jsp" %>    