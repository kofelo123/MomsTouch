function NumFormat(t) // 원 단위, 찍어주기
{
	s = t.value;
	s = s.replace(/\D/g, '');
	l = s.length - 3;
	while (l > 0) {
		s = s.substr(0, l) + ',' + s.substr(l);
		l -= 3;
	}
	t.value = s;
	return t;
}

function go_ab() // 판매가-원가=순매출을 replace해서 계산해 준다.
{
	var theForm = document.frm;
	var a = theForm.price2.value.replace(/,/g, '');
	var b = theForm.price1.value.replace(/,/g, '');
	var ab = parseInt(a) - parseInt(b);
	theForm.price3.value = ab;
}

// 폼에 입력이 올바른지 판단한다.
// productWrite.jsp에서 사용한다.
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
		/*theForm.method="post";*/
		theForm.action = "/momstouch/admin/product_write";
		theForm.submit();
	}
}

function removeComma(input) // ,을 빼고 값을 다시 넣어준다.
{
	return input.value.replace(/,/gi, "");
}
// productWrite.jsp에서 사용한다. 상품 리스트로 이동한다.
function go_mov()
{
	window.location.href="/momstouch/admin/admin_product_list";
}
//projectList.jsp
function go_search() {
	var theForm = document.frm;
	theForm.method = "get";
	theForm.action =  "/momstouch/admin/admin_order_list";
	theForm.submit();
}

function go_total() {
	var theForm = document.frm;
	theForm.keyword.value = "";
	theForm.action =  "/momstouch/admin/admin_product_list";
	theForm.submit();
}

function go_detail(page, pseq) {
//	var theForm = document.frm;
	console.log("page="+page);
	// 상품 상세 보기 페이지에서 다시 상품 리스트로 돌아왔을 경우 현재 페이지로
	// 돌아올 수 있도록 하기 위해서 현재 페이지 번호를 쿼리 스트링으로 넘겨준다.
	
	/*theForm.action =  "/momstouch/admin/admin_product_detail?page=" +
	                  page+"&pseq="+pseq;
	
	
	theForm.submit();*/
	//이거 내가 아래로 바꾼거다. form으로 action시키는게 아니라 location으로 했다. 기존에 파라미터가 안되어서..(detail?keyword= 이렇게되는데 왜그런지는..)
	//생각해보니 리스트에서 폼으로 전송한다는게 좀 이상해서 location.href하니까 잘된다.
	window.location.href= "/momstouch/admin/admin_product_detail?page=" +
    page+"&pseq="+pseq;
}

function go_wrt() {
	var theForm = document.frm;
	theForm.action = "/momstouch/admin/product_write_form";
	theForm.submit();
}
// **************** productDetail.jsp
function go_list(page) {
	var theForm = document.frm;
	//상품 리스트로 이동하되 현재 페이지를 쿼리 스트링으로 넘긴다.
		//머하러 이프로젝트는 죄다 페이지이동(목록->리스트 등)을 form action submit 으로 하는지 모르겠다. 이상하게 파라미터가 유지가 안되는등 잘안되서 그냥 href
	//검색해보다가 알게됬는데 form의 전송은 폼안에 속성으로 값이 확실히 있을때 서버로 전송이 된다는것 같기도 하다.
//	theForm.action = "/momstouch/admin/admin_product_list?page=" + tpage;
//	theForm.submit();
	
	window.location.href="/momstouch/admin/admin_product_list?page="+page;
	
}
// **************** productDetail.jsp
function go_mod(page, pseq) {
	var theForm = document.frm;
	//현재 페이지를 쿼리 스트링으로 넘긴다.
/*	theForm.action = "/momstouch/admin/admin_product_update_form?page=" + 
		              page+"&pseq="+pseq;
	theForm.submit();*/
	
	window.location.href="/momstouch/admin/admin_product_update_form?page=" + 
    page+"&pseq="+pseq;
}

//	function go_mod_save(tpage, pseq) {
function go_mod_save(pseq) {
	var theForm = document.frm;

	if (theForm.kind.value == '') {
		alert('상품분류를 선택하세요');
		theForm.kind.focus();
	} else if (theForm.name.value == '') {
		alert('상품명을 입력하세요');
		theForm.name.focus();
	} else if (theForm.price1.value == '') {
		alert('원가를 입력하세요');
		theForm.price1.focus();
	} else if (theForm.price2.value == '') {
		alert('판매가를 입력하세요');
		theForm.price2.focus();
	} else if (theForm.content.value == '') {
		alert('상품상세를 입력하세요');
		theForm.content.focus();
	} else {
		if (confirm('수정하시겠습니까?')) {
			// [1] 상품을 삭제하지 않는 대신 사용하지 않음을 products 테이블의 useyn 컬럼에 1을 채워 넣기 위해서
			// useyns hidden 태그에 1을 지정한다.
			
			if(document.getElementById("checkboxbest").checked){
				document.getElementById("uncheckedbest").disabled=true;
			}
			
			if(document.getElementById("checkboxuse").checked){
				document.getElementById("uncheckeduse").disabled=true;
			}
			
//			if (theForm.useyn == null) {
//				theForm.useyn.value = "n";
//			}
//			if(theForm.bestyn == null) {
//				theForm.bestyn.value = "n";
//			}
			theForm.encoding = "multipart/form-data";
			// theForm.seq.value=seq;
			theForm.price1.value = removeComma(theForm.price1);
			theForm.price2.value = removeComma(theForm.price2);
			theForm.price3.value = removeComma(theForm.price3);
			// [2] products 테이블의 상품 정보를 수정하는 처리를 하는 product_modsave.jsp 페이지로
			// 이동하되 상품 코드를 전달해준다. 상품코드로 폴더를 생성해서 그곳에 이미지 파일을 업로드하기 때문이다.			
			theForm.action = "/momstouch/admin/admin_product_update";
			theForm.submit();
		}
	}
}

function go_mod_mov(tpage, pseq) {
	var theForm = document.frm;
	theForm.action = 'NonageServlet?command=admin_product_detail&tpage=' + tpage + "&pseq=" + pseq;
	theForm.submit();
}
