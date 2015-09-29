$(function() {
	$('tr.sp').show();
	$("tr[name=main]").click(function() {
		var expand = $(this).attr("expand");
		if (expand == 1) {
			$("tr[pid=" + $(this).attr("key") + "]").hide();
			$(this).attr("expand", "0");
		} else {
			$("tr[pid=" + $(this).attr("key") + "]").show();
			$(this).attr("expand", "1");
		}
	});
	$("tr[name=main]").eq(0).click();
	$("span.addSub").click(function() {
		addMask();
		$('body').append($('#actionEdit').html());
		$('input[name="id"]').val("");
		$('input[name="name"]').val("");
		$('input[name="link"]').val("");
		center($('.actionEdit'));
	});

	$("span.edit").click(function() {
		var row = $(this).parent().parent();
		var id = row.attr("key");
		var name = row.find("td[name=name]").text();
		var link = row.find("td[name=link]").text();
		addMask();
		$('body').append($('#actionEdit').html());
		$('input[name="id"]').val(id);
		$('input[name="name"]').val(name);
		$('input[name="link"]').val(link);
		center($('.actionEdit'));
	});

	$("span.save").click(function() {
		var id = $('input[name="id"]').val();
		var name = $('input[name="name"]').val();
		var link = $('input[name="link"]').val();
		if ($.trim(name) == "") {
			justTip("操作名称不能为空");
			return false;
		}
		delMask();
		$('.ui-dialog').hide();

		$.post(ctx + "/admin/action/save.do", {
			id : id,
			name : name,
			link : link
		}, function(res) {
			if (res.success) {
				document.location.reload();
			} else {
				justTip("保存失败");
			}
		});
	});

	$("span.del").click(function() {
		var id = $(this).parent().parent().attr("key");
		$.post(ctx + "/admin/action/remove.do", {
			id : id
		}, function(res) {
			if (res.success) {
				document.location.reload();
			} else {
				justTip("保存失败");
			}
		});
	});
});