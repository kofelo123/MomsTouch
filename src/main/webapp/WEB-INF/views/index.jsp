<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>  

  <!--메인 이미지 들어가는 곳 시작 --->
  <!-- <div class="clear"></div>
  <div id="main_img">
    <img src="images/momstouch/banner_02_img_01.png" >    
  </div> -->
  <!--메인 이미지 들어가는 곳 끝--->
  
  <!-- 롤링 시작   -->
 <div class="container">
			<!-- Codrops top bar -->
            
			<div id="da-slider" class="da-slider">
				<div class="da-slide">
					<h2>뿌려먹는 치킨 "뿌치"</h2>
					<p>치킨위에  고소한 어니언 치즈맛,매콤한 핫칠리맛, 향긋한 커리맛을 입맛에 맞게 뿌려 다양하게 맛볼 수 있는 뿌려먹는 치킨 "뿌치" </p>
					<a href="NonageServlet?command=catagory&kind=1" class="da-link">메뉴보기</a><!-- 이거나중에 해당 메뉴로 연결되게끔 수정하면 좋을것같다, 이미지바꾸고  -->
					<div class="da-img"><img src="rolling/images/1.png" alt="image01" /></div>
				</div>
				<div class="da-slide">
					<h2>케이준 망고 통살버거</h2>
					<p>아삭한 파프리카,풍성한 야채와 향긋한  허브숙성 닭고기 통다리살의  패티와 달콤하고 진한 망고 후레쉬 소스의  프리미엄 수제버거 케이준 망고 통살버거.</p>
					<a href="NonageServlet?command=product_detail&pseq=33" class="da-link">메뉴보기</a>
					<div class="da-img"><img src="rolling/images/2.png" alt="image02" /></div>
				</div>
				<div class="da-slide">
					<h2>데리야끼 윙</h2>
					<p>신선한 닭날개에 매운맛과 순한맛의 데리야끼 소스를 가미하여 양념이 손에 묻지 않고 ,편하게 드실 수 있는 고급 윙메뉴</p>
					<a href="NonageServlet?command=product_detail&pseq=15" class="da-link">메뉴보기</a>
					<div class="da-img"><img src="rolling/images/3.png" alt="image03" /></div>
				</div>
				<div class="da-slide">
					<h2>Quality Control</h2>
					<p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.</p>
					<a href="#" class="da-link">Read more</a>
					<div class="da-img"><img src="rolling/images/4.png" alt="image04" /></div>
				</div>
				<nav class="da-arrows">
					<span class="da-arrows-prev"></span>
					<span class="da-arrows-next"></span>
				</nav>
			</div>
        </div>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script type="text/javascript" src="rolling/js/jquery.cslider.js"></script>
		<script type="text/javascript">
			$(function() {
				
				$('#da-slider').cslider({
					autoplay	: true,
					bgincrement	: 450
				});
			});
		</script>	
<!-- 롤링 끝	 -->

  <div class="clear"></div>   

  <div id="front">   
    <h2> NEW MENU</h2>     
    <div id="bestProduct">         
      <c:forEach items="${newProductList }"  var="productVO">
        <div id="item">
          <a href=
"NonageServlet?command=product_detail&pseq=${productVO.pseq}">
            <img src="images/momstouch/menu_${productVO.kind}/${productVO.image}.jpg" />
            <h3> ${productVO.name} </h3>    
            <p>${productVO.price2} </p>
          </a>    
        </div>
      </c:forEach>      
    </div>
   <div class="clear"></div>
     
    <h2> HOT MENU </h2>     
      <div id="bestProduct">         
        <c:forEach items="${bestProductList}"  var="productVO">
          <div id="item">
           <a href=
"NonageServlet?command=product_detail&pseq=${productVO.pseq}">
             <img src="images/momstouch/menu_${productVO.kind}/${productVO.image}.jpg" />
           <h3> ${productVO.name} </h3>    
           <p>${productVO.price2} </p>
        </a>  
      </div>
    </c:forEach>      
  </div>
  <div class="clear"></div>
  </div>
    
<%@ include file="include/footer.jsp" %>    