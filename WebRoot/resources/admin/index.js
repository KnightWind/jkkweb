$(function() {
	var html = '<li data-link="#LINK#"><strong><a href="#"><i class="new">&#xf0ad;</i>#NAME#</a></strong></li>';

	var EventManager = {
		clickTree : function() {
			$(".iframe-content").show();
			$(".default-content").hide();

			var width = $(".default-content").width();
			var link = $(this).attr("data-link");
			$("#conentFrame").width(width + 100).height(500).attr("src",
					ctx + link);
		},
		clickBanner : function() {
			var isActive = $("a", this).is(".cur");
			if (isActive) {
				return;
			}
			$("a.cur").removeClass("cur");
			$("a", this).addClass("cur");

			var contanier = $(".menu ul");
			contanier.html("");

			$.ajax({
				url : ctx + "/admin/menu/loadMenu.do?id="
						+ $(this).attr("data-banner"),
				success : function(res) {
					var datalist = eval("(" + res + ")");
					$.each(datalist, function() {
						var el = $(
								html.replace("#LINK#", this.link).replace(
										"#NAME#", this.name)).appendTo(
								contanier).click(EventManager.clickTree);
					});
				}
			})
		}
	};

	function initEvents() {
		$("li[data-link]").click(EventManager.clickTree);
		$("[data-banner]").click(EventManager.clickBanner);
	}

	initEvents();
});