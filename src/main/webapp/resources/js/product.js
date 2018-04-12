/****************************************************************
 *
 * 파일명 : product.js
 * 설  명 : 구매관련 기능 사용 JavaScript
 *
 *    수정일      수정자     Version        Function 명
 * ------------    ---------   -------------  ----------------------------
 *
 * 2018.03.6    허정원                      전체(js -> jquery)
 * 2018.04.12   허정원					  전체(jquery 각 function onclick -> document.ready)
 *
 *
 * **************************************************************/

$(document).ready(function(){

    var formObj = $("[name=formm]");

    /** 카트담기 */
    $("#go_cart").on("click",function(){
        if($("[name=quantity]").val()==""){
            alert("수량을 입력해 주세요.");
            $("[name=quantity]").focus();
        }else{
            formObj.attr("action","/momstouch/product/cart_insert");
            formObj.submit();
        }
    });

    /**
     * 카트에 담겨있는 리스트중 체크박스된것 삭제
     *(체크가 된것을 뽑아야하는데 그 체크된 체크박스가 하나일때는 undefined가 된다.)
     */
    $("#go_cart_delete").on("click",function(){

        var count = 0;

        var cseq = $("[name=cseq]");

        if(cseq.length == undefined && cseq.checked == true){
            count ++;
        }else {
            for (var i = 0; i < cseq.length; i++) {
                if (cseq[i].checked == true) {
                    count++;
                }
            }
        }
        if(count == 0){
            alert("삭제할 항목을 선택해 주세요");
        }else{
            formObj.attr("action","/momstouch/product/cart_delete");
            formObj.submit();
        }
    });

    /**
     * 주문하기1  (카트담아서->주문 )
     */
    $("#go_order_insert").on("click",function(){
        formObj.attr("action","/momstouch/product/order_insert");
        formObj.submit();
    });

    /**
     *  주문하기2  (메뉴에서 바로주문)
     */
    $("#go_order_instant").on("click",function(){
        formObj.attr("action","/momstouch/product/order_instant");
        formObj.submit();
    });

    /** 따로 추가함. 메뉴 구매시 수량 +,- 버튼 */
    $("#plus").on("click",function(){
        var quantityVal = $("[name=quantity]").val();

        if(quantityVal < 10){
            quantityVal++;
            $("[name=quantity]").val(quantityVal);
        }
    });

    $("#minus").on("click",function(){

        var quantityVal = $("[name=quantity]").val();

        if(quantityVal > 1){
            quantityVal--;
            $("[name=quantity]").val(quantityVal);
        }
    });

});


/*function go_cart() {
  if (document.formm.quantity.value == "" ) {
    alert("수량을 입력하여 주세요.");
    document.formm.quantity.focus();
  } else {
	  
    document.formm.action = "/momstouch/product/cart_insert";
    document.formm.submit();
  }
}*/


/*

function go_cart(){
	var formObj = $("form[name=formm]");
	
	if(formObj.find("[name=quantity]").val()==""){
		alert("수량을 입력해 주세요.");
		formObj.find("[name=quantity]").focus();
	}else{
		formObj.attr("action","/momstouch/product/cart_insert");
		formObj.submit();
	}
}*/


/*
function go_cart_delete() {
  var count = 0;
  if(document.formm.cseq.length == undefined && document.formm.cseq.checked == true){
	  	count++;
  }else{
  for ( var i = 0; i < document.formm.cseq.length; i++) {
    if (document.formm.cseq[i].checked == true) {
      count++;
    }
  }
  }
  console.log("test3:"+count);
  if (count == 0) {
    alert("삭제할 항목을 선택해 주세요.");

  } else {
    document.formm.action = "/momstouch/product/cart_delete";
    document.formm.submit();
  }
}
*/


/*
 * 카트에 담겨있는 리스트중 체크박스된것 삭제
 *(체크가 된것을 뽑아야하는데 그 체크된 체크박스가 하나일때는 undefined가 된다.) 
 */
/*
function go_cart_delete(){
	var count = 0;
	
	var formObj = $("[name=formm]");
	
	var cseq = formObj.find("input[name=cseq]");
	
	if(cseq.length == undefined && cesq.checked == true){
		count++;
	}else{
		for( var i =0; i < cseq.length; i++){
			if(cseq[i].checked == true){
				count++;
			}
		}
	}
	if(count == 0){
		alert("삭제할 항목을 선택해 주세요.");
	}else{
		formObj.attr("action","/momstouch/product/cart_delete");
		formObj.submit();
	}
}
*/


/*function go_order_insert() {
  document.formm.action = "/momstouch/product/order_insert";
  document.formm.submit();
}*/

/*
 * 주문하기1  (카트담아서->주문 )
 */
/*function go_order_insert(){
	var formObj = $("[name=formm]");
	
	formObj.attr("action","/momstouch/product/order_insert");
	formObj.submit();
}*/
/*
function go_order_instant() {
	document.formm.action = "/momstouch/product/order_instant";
	document.formm.submit();
}*/
/*
 * 주문하기2  (메뉴에서 바로주문)
 */
/*
function go_order_instant(){
	var formObj= $("[name=formm]");
	
	formObj.attr("action","/momstouch/product/order_instant");
	formObj.submit();
}*/



/*
안쓰는 메소드
function go_order_delete() {
  var count = 0;
  for ( var i = 0; i < document.formm.oseq.length; i++) {
    if (document.formm.oseq[i].checked == true) {
      count++;
    }
  }
  if (count == 0) {
    alert("삭제할 항목을 선택해 주세요.");

  } else {
    document.formm.action = "/momstouch/product/order_delete";
    document.formm.submit();
  }
}

function go_order() {
  document.formm.action = "/momstouch/product/mypage";
  document.formm.submit();
}
*/

/* 따로 추가했다 메뉴상세페이지에 + - 버튼*/
/*function plus(){
	
	if(document.formm.quantity.value<10){ //최대갯수는 10개정도로 제한을 둔다.
	document.formm.quantity.value++;
	}
}
function minus(){
	if(document.formm.quantity.value>0){ //-가 안되게..
		document.formm.quantity.value--;
		}
}*/

/* 따로 추가했다 메뉴상세페이지에 + - 버튼*/
/*
function plus(){
	
	var formObj = $("[name=formm]");
	
	var quantity = formObj.find("[name=quantity]");
	
	var quantityVal = quantity.val();
	if(quantity.val() < 10){
		quantityVal++;
		quantity.val(quantityVal);
	}
				
}

function minus(){
	if(quantity.val() > 0){
		quantity.val(quantity.val()-1);
	}
}*/
