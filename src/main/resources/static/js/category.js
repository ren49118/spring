$(document).ready(function(){
	showcategory();
	$('#btn-add').click(function(evt){
		evt.preventDefault();
		$('.title-cat').html("Create");
		$('#cat-id').val(0);
		$('#addcat').modal('show');
	});
	$(document).on('click','.edit-btn',function(){
		let id = $(this).data("id");
		$.ajax({
			url : '/api/category/find/'+id,
			type : 'GET',
			success : function(result){
				if(result.data != null){
					let cat = result.data[0];
					
					$('#cat-id').val(cat.id);
					$('.title-cat').html("Edit");
					$('#name').val(cat.name);
					$('#addcat').modal('show');
				}
			}
		});
		
	});
	$(document).on('click','.delete-btn',function(){
		let id = $(this).data("id");
		if(confirm("are u sure")){
			$.ajax({
				url : 'api/category/delete/'+id,
				type : 'DELETE',
				success : function(result){
					if(result.responeMsg == 'success'){
						showcategory();
					}
				}
				
			})
		}
		
	});
	
	$('#save-cat').submit(function(evt){
		evt.preventDefault();
		let  catid = $('#cat-id').val();
		let urlVal = (catid == 0) ? '/api/category/create' : '/api/category/edit/'+catid;
		let typeVal = (catid == 0)? 'POST' : 'PUT';
		let formData= {
			name : $('#name').val()
		}
		
		$.ajax({ 
			url : urlVal,
			type : typeVal,
			contentType : 'application/json',
			dataType : 'json',
			data : JSON.stringify(formData),
			success : function(result){	
				if(result.responeMsg == 'duplicate'){
					
					$('#message').html("category already exists");
					$('#message').removeClass('d-none');
					$('#message').removeClass('text-success');
					$('#message').addClass('text-danger');
				}
				else if (result.responseMsg == 'success'){
					$('#message').html("success");
					$('#message').removeClass('d-none');
					$('#message').removeClass('text-danger');
					$('#message').addClass('text-success');
				}
				showcategory();
				
			},
			error :function(err){
				alert("Error");
				console.log("error");
			}
		})
	});
	function showcategory(){
		$.ajax({
			
			url : 'api/category/all',
			type : 'GET',
			success : function(result){
				let html;
				let j = 1;
				if(result != null){
					$.each(result.data,function(i,cat){
						
						 html +=`<tr>
									<td>${j++}</td>
									<td>${cat.name}</td>
									<td>
										<a data-id="${cat.id}" class="btn btn-outline-success edit-btn">Edit</a>
										<a data-id="${cat.id}" class="btn btn-outline-danger delete-btn">Delete</a>
									</td>
								</tr>`;
					});
					$('.table-body').html(html);
				}
			},
			error : function(err){
				
			}
		});
	}
	
});
