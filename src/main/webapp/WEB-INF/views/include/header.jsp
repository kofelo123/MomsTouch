<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>MomsTouch by JW</title>
  <link href="/momstouch/resources/css/shopping.css" rel="stylesheet">  
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
  <script type="text/javascript" src="/momstouch/resources/js/member.js"></script>
  <script type="text/javascript" src="/momstouch/resources/js/mypage.js"></script>
  <script type="text/javascript" src="/momstouch/resources/js/product.js"></script>
  
  <!-- 롤링 소스 시작  -->
  <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <title>Parallax Content Slider with CSS3 and jQuery</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Parallax Content Slider with CSS3 and jQuery" />
        <meta name="keywords" content="slider, animations, parallax, delayed, easing, jquery, css3, kendo UI" />
        <meta name="author" content="Codrops" />
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="/momstouch/resources/rolling/css/demo.css" />
        <link rel="stylesheet" type="text/css" href="/momstouch/resources/rolling/css/style2.css" />
		<script type="text/javascript" src="/momstouch/resources/rolling/js/modernizr.custom.28468.js"></script>
		<link href='http://fonts.googleapis.com/css?family=Economica:700,400italic' rel='stylesheet' type='text/css'>
		<noscript>
			<link rel="stylesheet" type="text/css" href="/momstouch/resources/rolling/css/nojs.css" />
		</noscript>
	  <!-- 롤링 소스  끝 -->
</head>

<body>
  <div id="backlogo">
	<!-- <img src="images/momstouch/container_Bg_02.png"  /> -->

<div id="wrap">
<!--헤더파일 들어가는 곳 시작 -->

  <header>

    <!--로고 들어가는 곳 시작--->  
    <div id="logo">
      <a href="/momstouch/">
        <img src="/momstouch/resources/image/momstouch/main_logo.png" width="280" height="100" alt="nonageshop">
      </a>  
    </div>
    <!--로고 들어가는 곳 끝-->     
    <nav id="catagory_menu">
     <ul>
       <c:choose>
       <c:when test="${empty sessionScope.loginUser}">
       <li>         
         <a href="/momstouch/member/login_form" style="width:110px;">LOGIN</a> 
       </li>  
	     
	  		       
       <li>/</li>
       <li><a href="/momstouch/member/contract">JOIN</a></li>
       <li>/</li>
       <li><a href="/momstouch/admin/admin_login_form" style="width:100px;"> ADMIN</a></li>
     
       </c:when>
       <c:otherwise>
       <li style="color:orange">
         ${sessionScope.loginUser.name}(${sessionScope.loginUser.id})
       </li>
       <li><a href="/momstouch/member/logout" style="margin-right:100px;">LOGOUT</a></li>
    	
      
       </c:otherwise>       
       </c:choose>
     
       
     </ul>
    </nav>

    <nav id="top_menu">
      <ul>
        <li>
          <a href="/momstouch/product/category?kind=치킨 메뉴">MENU</a>
        </li>  
        <li>
          <a href="/momstouch/product/cart_list">CART</a>
        </li>
       
       <li>
         <a href="/momstouch/product/mypage">ORDER LIST</a>
       </li>
       <li>
         <a href="/momstouch/member/qna_list">Q&amp;A(1:1)</a>
       </li>  
     
        
      </ul>
    </nav>
    <div class="clear"></div>
    <hr>
  </header>
  <!--헤더파일 들어가는 곳 끝 -->