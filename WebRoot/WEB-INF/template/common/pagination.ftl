<div class="ui-paging" style="[#if (pagination.pages <= 1)]display:none;[/#if]">
	<a href="#" class="ui-paging-prev" [#if !pagination.hasPreviousPage]style="cursor: text;"[/#if]><i class="new"></i> 上一页</a>
	[#list pagination.navigatePageNumbers as item]
	<a href="#" class="ui-paging-item [#if pagination.pageNumber == item]ui-paging-current[/#if]" rel="${item}">${item}</a>
	[/#list]
	<a href="#" class="ui-paging-next" [#if !pagination.hasNextPage]style="cursor: text;"[/#if]>下一页 <i class="new"></i></a>
	<span class="ui-paging-info">
		<span class="ui-paging-bold">${pagination.pageNumber}/${pagination.pages}</span>页，共
		<span class="ui-paging-bold">${pagination.total}</span>条，第 </span>
		<span class="ui-paging-which">
			<input name="some_name" type="text">
		</span>
		<span class="ui-paging-bold">页</span>
		<a class="ui-paging-info ui-paging-goto" href="#" max_page="${pagination.pages}">跳转</a>
</div>