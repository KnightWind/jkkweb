$(function() {
	$('span.btn').bind('click', function() {
		var row = $(this).parent().parent();
		var id = row.attr("key");
		var isopen = $(this).attr("isopen");
		$.post(ctx + "/admin/area/operate.do", {
			id : id,
			isopen : isopen
		}, function(data) {
			if (data.ret == 1) {
				document.location.reload();
			} else {
				justTip(data.msg);
			}
		}, "json")
	});
})