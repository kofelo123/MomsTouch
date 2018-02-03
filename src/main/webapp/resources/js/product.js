function go_cart() {
  if (document.formm.quantity.value == "") {
    alert("수량을 입력하여 주세요.");
    document.formm.quantity.focus();
  } else {
    document.formm.action = "/momstouch/product/cart_insert";
    document.formm.submit();
  }
}

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

function go_order_insert() {
  document.formm.action = "/momstouch/product/order_insert";
  document.formm.submit();
}

function go_order_instant() {
	document.formm.action = "/momstouch/product/order_instant";
	document.formm.submit();
}

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


/* 따로 추가했다 메뉴상세페이지에 + - 버튼*/
function plus(){
	
	if(document.formm.quantity.value<10){ //최대갯수는 10개정도로 제한을 둔다.
	document.formm.quantity.value++;
	}
}
function minus(){
	if(document.formm.quantity.value>0){ //-가 안되게..
		document.formm.quantity.value--;
		}
}