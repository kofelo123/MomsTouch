<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>
<%@ include file="sub_img.html" %>
<%@ include file="sub_menu.jsp" %>
<article>
    <h1> Item </h1>
    <div id="itemdetail">
        <form method="post" name="formm">
            <fieldset>
                <legend> 메뉴 정보</legend>
                <a href="/momstouch/product/product_detail?pseq=${productVO.pseq}">
            <span style="float: left;">
              <img src="/momstouch/resources/image/momstouch${subfolder }/${productVO.image}"/>
            </span>
                    <h2> ${productVO.name} </h2>
                </a>
                <br>${productVO.content}<br><br>
                <label> 가 격 : </label>
                <p> ${productVO.price2} 원</p>
                <label> 수 량 : </label>
                <div id="plusminus">
                    <input type="text" name="quantity" size="2" value="1"> <img
                        src="/momstouch/resources/image/momstouch/plus.png" id="plus" /><img
                        src="/momstouch/resources/image/momstouch/minus.png" id="minus"/></div>
                <br>
                <input type="hidden" name="pseq" value="${productVO.pseq}"><br>
            </fieldset>


            <div class="clear"></div>
            <div id="buttons">
                <input type="button" value="장바구니에 담기" class="submit" id="go_cart">
                <input type="button" value="즉시 구매" class="submit" id="go_order_instant" >
                <input type="reset" value="취소" class="cancel" onclick="javascript:history.go(-1)">
            </div>
        </form>
    </div>
</article>
<%@ include file="../include/footer.jsp" %>
