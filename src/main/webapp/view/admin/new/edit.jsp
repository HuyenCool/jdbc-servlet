<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-new" />
<c:url var="NewURL" value="/admin-new" />
<html>
<head>
<title>Chỉnh sửa bài viết</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang chủ</a></li>
					<li class="active">Chỉnh sửa bài viết</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			
			<div class="page-content">
			
				<div class="row">
					<div class="col-xs-12">
						
						<c:if test="${not empty messageResponse}">
							<div class="alert alert-${arlet}">${messageResponse}</div>
						</c:if>
						<form id="formSubmit">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Thể
									loại</label>
								<div class="col-sm-9">
									<select class="form-control" id="categoryCode"
										name="categoryCode">
										<c:if test="${empty newsmodel.categoryCode}">
											<option value="">Chọn loại bài viết</option>
											<c:forEach var="item" items="${categories}">
												<option value="${item.code}">${item.name}</option>
											</c:forEach>
										</c:if>
										<c:if test="${not empty newsmodel.categoryCode}">
											<option value="">Chọn loại bài viết</option>
											<c:forEach var="item" items="${categories}">
												<option value="${item.code}"
													<c:if test="${item.code == newsmodel.categoryCode}">selected="selected"</c:if>>
													${item.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Tiêu
									đề</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="title" name="title"
										value="${newsmodel.title}" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Hình
									đại diện</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="thumbnail"
										name="thumbnail" value="${newsmodel.thumbnail}" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Mô
									tả ngắn</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="shortdescription"
										name="shortdescription" value="${newsmodel.shortdescription}" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Nội
									dung</label>
								<div class="col-sm-9">
									<textarea rows="" cols="" id="content" name="content"
										style="margin: 0px; width: 815px; height: 157px;">${newsmodel.content}</textarea>

								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<div class="col-sm-12">
									<c:if test="${not empty newsmodel.id}">
										<input type="button"
											class="btn btn-white btn-warning btn-bold"
											value="Cập nhật bài viết" id="btnAddOrUpdateNew" />
									</c:if>
									<c:if test="${empty newsmodel.id}">
										<input type="button"
											class="btn btn-white btn-warning btn-bold"
											value="Thêm bài viết" id="btnAddOrUpdateNew" />
									</c:if>
								</div>
							</div>
							<input type="hidden" value="${newsmodel.id}" id="id" name="id" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	
	<!-- Những gì trong hàm document thì sẽ đucợ thực thi đều tiên-->
	var ckeditor = ""
	$(document).ready(function(){
		ckeditor = CKEDITOR.replace("content"); <!-- bên trong là id--> 
		});
	
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault(); <!-- để tránh tình trạng submit nhầm URL vì cái mk dang cần hướng tới là /api-admin-new , sợ nó nhảy vào URL /admin-new-->
        var data = {};
        var formData = $('#formSubmit').serializeArray(); <!-- chứa 1 mảng gồm tên của ddtruong và value của dtuong nên cần phải cho tên của dtuong-->
        $.each(formData, function (i, v) {  <!--each(data muốn lặp, function(indext: vtri của mảng, value: gtri của vtri đó)))))-->
            
        	console.log(v); <!-- v này gồm name và value của name đó-->
        	data[""+v.name+""] = v.value;
        });
     	data["content"] = ckeditor.getData(); <!-- get dữ liệu từ Ckeditor vào data-->
     	
        var id = $('#id').val();
        if (id == "") {
            addNew(data);
        } else {
            updateNew(data);
        }
    });
    function addNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	
            	window.location.href = "${NewURL}?type=edit&message=insert_success";
            	
            },
            error: function (error) {
            	console.log(error)
            	
            	window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&sortName=title&sortBy=desc&message=erorr_system";
            	
            }
        });
    }
    function updateNew(data) {
        $.ajax({
            url: '${APIurl}',<!-- URl là cái mà API muốn call và cần thông qua 1 cáu c:url-->
            type: 'PUT', <!-- kiểu dữ liệu mapping vs thang API-->
            contentType: 'application/json', <!-- kiểu dlieu gửi từ client về server , ở đây gửi dạng json-->
            data: JSON.stringify(data), <!-- data : dlieu cần gửi lên; chuyển  từ JavaScrip Object sang Json-->
            dataType: 'json',
            success: function (result) {
            	
            	window.location.href = "${NewURL}?type=edit&id="+result+"&message=update_success";
            },
            error: function (error) {
            	
            	
            	window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&sortName=title&sortBy=desc&message=erorr_system";
            }
        });
    }
    
</script>
</body>
</html>
