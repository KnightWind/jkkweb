<h2 class="hd mb10">
	[#list navList as item]
		[#if item_index = 0]
			<strong><a href="#">${item.name}</a></strong> >
		[#elseif (item_index+1<navList?size)]
			<a href="#" id="sel${item_index}" data-role="${item.name}">${item.name}</a> >
		[#else]
			<span id="sel${item_index}" data-role="${item.name}">${item.name}</span>
		[/#if]
	[/#list]
</h2>