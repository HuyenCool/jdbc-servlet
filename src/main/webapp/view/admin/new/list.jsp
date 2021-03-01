<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-new" />
<c:url var="NewURL" value="/admin-new" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách bài viết</title>
</head>
<body>

	<div class="main-content">
		<form action="<c:url value="/admin-new"/>" id="formSubmit"
			method="get">
			<div class="main-content-inner">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>

					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
								chủ</a></li>
								<li class="active">Chỉnh sửa bài viết</li>

					</ul>


				</div>

				<!-- /.breadcrumb -->
				<div class="page-content">
					<c:if test="${not empty messageResponse}">
						<div class="alert alert-${arlet}" >${messageResponse}</div>
					</c:if>

					<div class="widget-box table-filter">
						<div class="table-btn-controls">
							<div class="pull-right tableTools-container">
								<div class="dt-buttons btn-overlap btn-group">
									<a flag="info"
										class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Thêm bài viết'
										href='<c:url value="/admin-new?type=edit"/>'> <span>
											<i class="fa fa-plus-circle bigger-110 purple"></i>
									</span>
									</a>
									<button id="btnDelete" type="button"
										class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Xóa bài viết'>
										<span> <i class="fa fa-trash-o bigger-110 pink"></i>
										</span>
									</button>
								</div>
							</div>
						</div>
						<div class="col-xs-12">
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th><input type="checkbox" value="" id="checkAll">
											</th>
											<th>Tên bài viết</th>
											<th>Mô tả ngắn</th>
											<th>Thao tác</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${newsmodel.listResult}">
											<tr>
												<td><input type="checkbox" value="${item.id }"
													id="check_${item.id }"></td>
												<td>${item.title}</td>
												<td>${item.content}</td>
												<td>
													<!-- 
													<c:url var="editURL" value="/admin-new">
														<c:param name="type" value="edit"/>
														<c:param name="id" value="${item.id}"/>
													</c:url>
												 --> <a class="btn btn-sm btn-primary btn-edit"
													data-toggle="tooltip" title="Cập nhật bài viết"
													href='<c:url value="/admin-new?type=edit&id=${item.id}"/>'><i
														class="fa fa-pencil-square-o" aria-hidden="true"></i> </a>
												</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
								<ul class="pagination" id="pagination"></ul>
								<input type="hidden" value="" id="page" name="page" />
								<!-- name để mapp với thằng model khi request dlu 
								về controller => để tên trùng vs tên các thuộc tính trong model  -->
								<input type="hidden" value="" id="maxPageItem"
									name="maxPageItem" /> <input type="hidden" value=""
									id="sortName" name="sortName" /> <input type="hidden" value=""
									id="sortBy" name="sortBy" /> <input type="hidden" value=""
									id="type" name="type" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<!-- /.main-content -->
	<script type="text/javascript">

		var totalPages = ${newsmodel.totalPage};
		var currentPage = ${newsmodel.page};
		var limit = 2;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 2,  <!-- số nút hiện trên thanh ngang -->
				startPage : currentPage,
				onPageClick : function(event, page) {
					//console.info(page + ' (from options)');
					if(currentPage!= page){
						<!-- val  để truyển giá trị cho thằng value theo id ở trên-->
						$('#maxPageItem').val(limit); <!-- số Item trên 1 page -->
						$('#page').val(page); <!-- page vừa được clich -->
						$('#sortName').val('title');
						$('#sortBy').val('desc');
						$('#type').val('list');
						$('#formSubmit').submit();
					}
					
				}
			}).on('page', function(event, page) {
				console.info(page + ' (from event listening)');
			});
		});
		
		
		
		$("#btnDelete").click(function (e){
			var data = {};
			
			var ids = $('tbody input[type=checkbox]:checked').map(function () {
	            return $(this).val();
	            
	        }).get();
			
			data['ids'] = ids;
			
			deleteNew(data);
		});
		
		function deleteNew(data) {
	        $.ajax({
	            url: '${APIurl}',
	            type: 'DELETE',
	            contentType: 'application/json',                                                            
	            data: JSON.stringify(data),
	            success: function (result) {
	            	window.location.href = "${NewURL}?type=list&page=1&maxPageItem=2&sortName=title&sortBy=desc&message=delete_success";
	            },
	            error: function (error) {
	            	
	            	window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&sortName=title&sortBy=desc&message=erorr_system";
	            }
	        });
	    }
		
	</script>
</body>
</html>