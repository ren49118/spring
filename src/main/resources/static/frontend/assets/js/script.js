$(document).ready(function(){
	show();
	let count1 = 0;
	function show(){
		$.ajax({
			url : 'api/products/all',
			type: 'GET',
			success : function(result){
				let html;
				if(result!=null){
					$.each(result.data,function(i,v){
						html+=`<div class="col-12 col-md-6 col-lg-4 mb-5">
					                <div class="card bg-primary border-light shadow-soft">
					                    <div class="card-header p-3">
					                        <img  src="${v.photoPath}" class="card-img-top rounded" alt="Designer desk">
					                    </div>
					                    <div class="card-body pt-2">
					                        <div class="media d-flex align-items-center justify-content-between">
					                            <div class="post-group">
					                                <span>$ ${v.price}</span>
					                            </div>
					                            <div class="d-flex align-items-center">
					                                <span class="small"><span class="far fa-calendar-alt mr-2"></span>15 March 2020</span>
					                            </div>
					                        </div> 
					                        <h3 class="h5 card-title mt-4">${v.name}</h3>
					                        <p class="card-text">${v.description}</p>
					                        <div class="">
					                            <a  class="btn btn-primary text-info float-right ml-2 atc" data-id="${v.id}" data-name="${v.name}" data-price="${v.price}" data-qty="1" data-photo="${v.photoPath}" data-category="${v.category}" data-brand="${v.brand}">Add To Cart</a>
					                            <a href="product-detail?id=${v.id}"class="btn btn-primary text-success float-right edit-pro" data-id="${v.id}">Detail</a>
					                        </div>
					                    </div>
					                  </div>
					            </div>`;
							
					});
					html = html.replace('undefined','');
					$('#product-content').html(html);
				}	
			},
			error : function(err){
				
			}	
		})
	}
	function initLocal(){
		let items = localStorage.getItem("items");
		
		if(!items){
			items = [];
			localStorage.setItem("items",JSON.stringify(items));
			
		}
	}
	function count(){
		count1 = 0;
		let items = localStorage.getItem("items");
		itemArr = JSON.parse(items);
		console.log(typeof(itemArr));
		$.each(itemArr,function(i,v){
			count1++;
		})
		if(count1 == 0){
        	$(".checkout").removeAttr("href");
			$(".checkout").addClass("text-danger");
		}else{
			$(".checkout").addClass("text-success");
		}
	}
	count();
	initLocal();
	showTable();
	function showTable(){
		let html1;
		let html;
		let total = 0;
		let items = localStorage.getItem("items");
		if(items == null){
			itemArr = [];
		}else{
			itemArr = JSON.parse(items);
		}
		$.each(itemArr,function(i,v){
			html+=`<tr>
						<td>
							<a class="btn btn-primary text-danger del-btn" data-id="${i}"><i class="far fa-trash-alt"></i></a>
						</td>
						<td class="text-info">${v.name}</td>
						<td>$ ${v.price}</td>
						<td class="d-flex">
							<button data-id="${i}" class="btn btn-primary mr-2 text-info inc">+</button>
							<input type="text" value="${v.qty}" class="form-control text-center" style="width:100px;">
							<button data-id="${i}" class="btn btn-primary ml-2 text-danger dec">-</button>
						</td>
						<td>$ ${v.qty*v.price}</td>
					</tr>`;
					total +=v.price*v.qty;
			html1+=`<tr>
						<td class="text-info">${v.name}</td>
						<td>$${v.price}</td>
						<td class="text-center">${v.qty}</td>
						<td>$${v.qty*v.price}</td>
					</tr>`;
		})
		$('#checkin-body').html(html);
		$('#total').html("$"+total);
		$('#checkout-body').html(html1);
	}
	$(document).on('click','.inc',function(){
		let id = $(this).data("id");
		let items = localStorage.getItem("items");
		let itemArr = JSON.parse(items);
		$.each(itemArr,function(i,v){
			if(i == id){
				v.qty++;
			}
		})
		localStorage.setItem('items',JSON.stringify(itemArr));
		showTable();
	})
	$(document).on('click','.dec',function(){
		let id = $(this).data("id");
		let items = localStorage.getItem("items");
		let itemArr = JSON.parse(items);
		$.each(itemArr,function(i,v){
			if(i== id){
				v.qty--;
				if(v.qty == 0){
					if(confirm("are u sure?")){
						itemArr.splice(id,1);
					}else{
						v.qty++;
					}
				}
			}
		})
		localStorage.setItem('items',JSON.stringify(itemArr));
		showTable();
	})
	$(document).on('click','.del-btn',function(){
		let id = $(this).data("id");
		let items = localStorage.getItem("items");
		let itemArr = JSON.parse(items);
		$.each(itemArr,function(i,v){
			if(i == id){
				if(confirm("are u sure?")){
					itemArr.splice(id,1);
				}
			}
		})
		localStorage.setItem('items',JSON.stringify(itemArr));
		showTable();
	})
	$(document).on('click','.atc',function(){
		let id = $(this).data("id");
		let name = $(this).data("name");
		let price = $(this).data("price");
		let photo = $(this).data("photo");
		let qty = $(this).data("qty");
		
		let itemArr;
		let bool = false;
		let items = localStorage.getItem("items");
		if(items == null){
			itemArr = [];
		}else{
			itemArr = JSON.parse(items);
		}
		let item = {
			id : id,
			name : name,
			price : price,
			photo : photo,
			qty : qty
		}
		$.each(itemArr,function(i,v){
			if(id == v.id){
				v.qty++;
				bool = true;
			}
		})
		if(!bool){
			itemArr.push(item);
		}
		localStorage.setItem("items",JSON.stringify(itemArr));	
	})
	$('#checkout-form').submit(function(){
		let receiverInfo ={
			name : $('#recname').val(),
			phone : $('#recphone').val(),
			address : $('#recaddress').val()
		}
		let items = localStorage.getItem("items");
		let itemArr = JSON.parse(items);
		
		let orderInfo = {
			receiver : receiverInfo,
			orderItems : itemArr
		}
		$.ajax({
			url : '/api/checkout',
			type : 'POST',
			contentType : 'application/json',
			dataType :'json',
			data : JSON.stringify(orderInfo),
			success : function(result){
				alert(result)
			},
			error : function(result){
				alert("error")
			}
		})
		
	})
})