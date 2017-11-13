<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../include/header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.jsp" %>       
  <article>
    <h1> Item </h1>
    <div id="itemdetail" >
      <form  method="post" name="formm">    
        <fieldset>
          <legend> Item detail Info</legend>  
          <a href="/product/product_detail?pseq=${productVO.pseq}">         
            <span style="float: left;">
              <img src="/resources/image/momstouch/menu_${productVO.kind}/${productVO.image}.jpg"  />
            </span>              
            <h2> ${productVO.name} </h2>  
          </a>   
          <br>${productVO.content}<br><br>
          <label> 가 격 :  </label>  
          <p> ${productVO.price2} 원</p>  
          <label> 수 량 : </label> <div id="plusminus">
         <input  type="text"      name="quantity"  size="2"      value="1"> <img src="/resources/image/momstouch/plus.png" onclick="plus()" /><img src="images/momstouch/minus.png" onclick="minus()"/></div><br>
          <input  type="hidden"    name="pseq"       value="${productVO.pseq}"><br>
        </fieldset>
        
        
        <div class="clear"></div>
        <div id="buttons">
          <input type="button" value="장바구니에 담기"   class="submit"    onclick="go_cart()"> 
          <input type="button" value="즉시 구매"       class="submit"    onclick="go_order()"> 
          <input type="reset"  value="취소"           class="cancel">
        </div>
      </form>  
    </div>
  </article>
<%@ include file="../include/footer.jsp" %>    