$(function() {
	// $('.opmenu').delegate('p','mouseenter',function(){
	// $(this).addClass('cur');
	// }).delegate('p','mouseleave',function(){
	// $(this).removeClass('cur');
	// });
	// // pop menuadd
	// $('.opmenu span.btn').bind('click',function(){
	// addMask();
	// $('body').append($('#menuEdit').html());
	// center($('.menuEdit'));
	//        
	// });
//	$('body').delegate('span.save', 'click', function() {
//		alert(111);
//		var text = $.trim($('input[name="name"]').val());
//		var id = $.trim($('input[name="id"]').val());
//		if (text == '') {
//			justTip('请填写角色名称！');
//			return false;
//		}
//		var text = $.trim($('input[name="name"]').val());
//		$('.ui-dialog').remove();
//		delMask();
//		$.ajax({
//			url : ctx + "/admin/role/save.do",
//			type : "post",
//			data : {
//				id : id,
//				name : text
//			},
//			success : function(res) {
//				justTip(res.msg);
//				if (res.ret == 1) {
//					setTimeout(function(){document.location.reload();}, 300)
//				}
//			}
//		});
//	});
	// menuadd
	$('.oprole').delegate('span.addSub', 'click', function() {
		addMask();
		$('body').append($('#roleEdit').html());
		$('input[name="name"]').val("");
		$('input[name="id"]').val("");
		center($('.roleEdit'));
	});
	// menu modify
	$('.oprole').delegate('span.modify', 'click', function() {
		var id = $(this).parent().parent().attr("menuid");
		var name = $(this).parent().prev().text();
		addMask();
		$('body').append($('#roleEdit').html());
		$('input[name="id"]').val(id);
		$('input[name="name"]').val(name);
		center($('.roleEdit'));
	});
	// menu del
	$('.oprole').delegate('span.del', 'click', function() {
		var menuid = $(this).parent().parent().attr("menuid");
		$.ajax({
			url : ctx + "/admin/role/remove.do",
			data : {
				id : menuid
			},
			success : function(res) {
				justTip(res.msg);
				if (res.ret == 1) {
					setTimeout(function(){document.location.reload();}, 300)
				}
			}
		});
	});
})