/*
 * 회원가입 jquery 수정
 * @author 허정원  
 * @since 18.3.5 
 */


/****************************************************************
 * 
 * 파일명 : member.js
 * 설  명 : 회원가입 기능 사용 JavaScript
 * 
 *    수정일      수정자     Version        Function 명
 * ------------    ---------   -------------  ----------------------------
 * 
 * 2018.03.5    허정원                      전체(js -> jquery)
 * 2018.03.30   허정원					  전체(jquery 각 function onclick -> document.ready)	
 * 
 * 
 * **************************************************************/

$(document).ready(function(){
	
	var formObj = $("[name=formm]");
	
	$("#register").on("click", function(){
		
		if(formObj.find("[name=id]").val() == ""){
			alert("아이디를 입력하여 주세요.");
			formObj.find("[name=id]").focus();
		  } else if (formObj.find("[name=id]").val() != formObj.find("[name=reid]").val()){
		    alert("중복확인을 클릭하여 주세요.");
		    formObj.find("[name=id]").focus();
		  } else if (formObj.find("[name=pwd]").val()== "") {
		    alert("비밀번호를 입력해 주세요.");
		    formObj.find("[name=pwd]").focus();
		  } else if ((formObj.find("[name=pwd]").val() !=formObj.find("[name=pwdCheck]").val())) {
		    alert("비밀번호가 일치하지 않습니다.");
		    formObj.find("[name=pwdCheck]").focus();
		  } else if (formObj.find("[name=name]").val()== "") {
		    alert("이름을 입력해 주세요.");
		    formObj.find("[name=name]").focus();
		  } else if (formObj.find("[name=email]").val()== "") {
		    alert("이메일을 입력해 주세요.");
		    formObj.find("[name=email]").val().focus();
		  } else {
		    formObj.attr("action","/momstouch/member/join");
		    formObj.submit();
		  }
	});
	
	$("#idcheck").on("click",function(){
		if(formObj.find("[name=id]").val() ==""){
			alert('아이디를 입력하여 주십시오.');
			formObj.find("[name=id]").focus();
			return;
		}
		var url = "/momstouch/member/id_check_form?id=" 
			+ formObj.find("[name=id]").val();
		 window.open( url, "_blank_1",
		 "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=400, height=250, left=1100, top=340");
	});
	
	$("#postzip").on("click",function(){
		var url = "/momstouch/member/find_zip_num";
		  window.open( url, "_blank_1",
		"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=550, height=400, top=400, left=1100 ");
	});
	
	$("#goNext").on("click",function(){
		var checkbox= $("[name=okon1]");
		
		if(checkbox[0].checked == true){
			formObj.attr("action","/momstouch/member/join_form");
			formObj.submit();
		}else if(checkbox[1].checked == true){
			alert('약관에 동의하셔야만 합니다.');
		}
	});
	
	
	$("#mailcheck").on("click",function(){
		
		if(formObj.find("[name=company]").val() != "직접입력"){
			formObj.find("[name=email2]").val(formObj.find("[name=company]").val());
		}else if(formObj.find("[name=company]").val() == "직접입력"){
			formObj.find("[name=email2]").val("");
		}
	});
});



//1차 - js코드
/*
function go_save() 
{
	var theForm = document.frm;
	
	if (theForm.kind.value == '') {
		alert('상품분류를 선택하세요.');
		theForm.kind.focus();
	} else if (theForm.name.value == '') {
		alert('상품명을 입력하세요.');
		theForm.name.focus();
	} else if (theForm.price1.value == '') {
		alert('원가를 입력하세요.');
		theForm.price1.focus();
	} else if (theForm.price2.value == '') {
		alert('판매가를 입력하세요.');
		theForm.price2.focus();
	} else if (theForm.content.value == '') {
		alert('상품상세를 입력하세요.');
		theForm.content.focus();
	} else if (theForm.file.value == '') {
		alert('상품이미지들 입력하세요.');
		theForm.image.focus();
	} else {
		theForm.encoding = "multipart/form-data";
		theForm.price1.value = removeComma(theForm.price1);
		theForm.price2.value = removeComma(theForm.price2);
		theForm.price3.value = removeComma(theForm.price3);

		// productWrite.jsp 폼 페이지에서 입력받은 값을
		// 디비에 추가하기 위한 페이지인 product_save.jsp로 이동하되
		// 입력받은 상품 코드를 쿼리 스트링 형태로 전달한다.
		// 상품 코드로 폴더를 만들어 거기에 이미지 파일을 업로드한다.
		theForm.method="post";
		theForm.action = "/momstouch/admin/product_write";
		theForm.submit();
	}
}
*/
/*function go_save() {
if (document.formm.id.value == "") {
  alert("아이디를 입력하여 주세요.");
  document.formm.id.focus();
} else if (document.formm.id.value != document.formm.reid.value) {
  alert("중복확인을 클릭하여 주세요.");
  document.formm.id.focus();
} else if (document.formm.pwd.value == "") {
  alert("비밀번호를 입력해 주세요.");
  document.formm.pwd.focus();
} else if ((document.formm.pwd.value != document.formm.pwdCheck.value)) {
  alert("비밀번호가 일치하지 않습니다.");
  document.formm.pwd.focus();
} else if (document.formm.name.value == "") {
  alert("이름을 입력해 주세요.");
  document.formm.name.focus();
} else if (document.formm.email.value == "") {
  alert("이메일을 입력해 주세요.");
  document.formm.email.focus();
} else {
  document.formm.action = "/momstouch/member/join";
  document.formm.submit();
}
}*/

/*
function go_next() {
  if (document.formm.okon1[0].checked == true) {
    document.formm.action = "/momstouch/member/join_form";
    document.formm.submit();
  } else if (document.formm.okon1[1].checked == true) {
    alert('약관에 동의하셔야만 합니다.');
  }
}
*/

/* function idcheck() {
  if (document.formm.id.value == "") {
    alert('아이디를 입력하여 주십시오.');
    document.formm.id.focus();
    return;
  }
  var url = "/momstouch/member/id_check_form?id=" 
+ document.formm.id.value;
  window.open( url, "_blank_1",
"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=400, height=250, left=1100, top=340");
}
*/

/*
function mailcheck(){
	
	if(document.formm.company.value!="직접입력"){
		document.formm.email2.value=document.formm.company.value;
	}else if(document.formm.company.value=="직접입력"){
		document.formm.email2.value="";
	}
	
	
}*/

//2차 jquery - function


/*
function go_save(){
	var formObj = $("form[name='formm']");
	
	if(formObj.find("[name=id]").val() == ""){
		alert("아이디를 입력하여 주세요.");
		formObj.find("[name=id]").focus();
	  } else if (formObj.find("[name=id]").val() != formObj.find("[name=reid]").val()){
	    alert("중복확인을 클릭하여 주세요.");
	    formObj.find("[name=id]").focus();
	  } else if (formObj.find("[name=pwd]").val()== "") {
	    alert("비밀번호를 입력해 주세요.");
	    formObj.find("[name=pwd]").focus();
	  } else if ((formObj.find("[name=pwd]").val() !=formObj.find("[name=pwdCheck]").val())) {
	    alert("비밀번호가 일치하지 않습니다.");
	    formObj.find("[name=pwdCheck]").focus();
	  } else if (formObj.find("[name=name]").val()== "") {
	    alert("이름을 입력해 주세요.");
	    formObj.find("[name=name]").focus();
	  } else if (formObj.find("[name=email]").val()== "") {
	    alert("이메일을 입력해 주세요.");
	    formObj.find("[name=email]").val().focus();
	  } else {
	    formObj.attr("action","/momstouch/member/join");
	    formObj.submit();
	  }
}

*/


/*function idcheck(){
	var formObj = $("form[name=formm]");
	
	if(formObj.find("[name=id]").val() ==""){
		alert('아이디를 입력하여 주십시오.');
		formObj.find("[name=id]").focus();
		return;
	}
	var url = "/momstouch/member/id_check_form?id=" 
		+ formObj.find("[name=id]").val();
	 window.open( url, "_blank_1",
	 "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=400, height=250, left=1100, top=340");
}
*/

/*
function post_zip() {
  var url = "/momstouch/member/find_zip_num";
  window.open( url, "_blank_1",
"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=550, height=400, top=400, left=1100 ");
}
*/


/*function go_next(){
	var formObj = $("form[name=formm]");
	
	var checkbox= $("[name=okon1]");
	
//	if(checkbox[0].checked == true){
	
	if(checkbox[0].checked == true){
		formObj.attr("action","/momstouch/member/join_form");
		formObj.submit();
	}else if(checkbox[1].checked == true){
		alert('약관에 동의하셔야만 합니다.');
	}
}
*/

/*
function mailcheck(){
	var formObj = $("form[name=formm]");
	
	if(formObj.find("[name=company]").val() != "직접입력"){
		formObj.find("[name=email2]").val(formObj.find("[name=company]").val());
	}else if(formObj.find("[name=company]").val() == "직접입력"){
		formObj.find("[name=email2]").val("");
	}
}
*/