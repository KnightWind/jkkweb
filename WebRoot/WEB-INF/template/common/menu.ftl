<div class="menu white bradius fl">
	<ul>
		[#list menuTree as item]
		<li class="[#if item.children??]nums[/#if]" data-role="${item.name}">
			<strong>
				[#if item.isOpen]
				<span><i class="new close">&#xf0da;</i><i class="new open">&#xf0d7;</i></span>
				[/#if]
				[#if item.link?? && item.link != '']
					[#if item.menuType = 2]
					<a href="${item.link}?mid=${item.id}&pid=${item.pid}"><i class="new">[#if item.icon?? && item.icon!='']&#${item.icon}[#else]&#xf0ad;[/#if]</i>${item.name}</a>
					[#else]
					<a href="${base}${item.link}?mid=${item.id}&pid=${item.pid}"><i class="new">[#if item.icon?? && item.icon!='']&#${item.icon}[#else]&#xf0ad;[/#if]</i>${item.name}</a>
					[/#if]
				[#else]
				<a href="javascript:;"><i class="new">[#if item.icon?? && item.icon!='']&#${item.icon}[#else]&#xf0ad;[/#if]</i>${item.name}</a>
				[/#if]
			</strong>
			
			[#if (item.children?size>0)]
			<ul class="sub">
				[#list item.children as item2]
					[#if item2.link??]
						[#if item.menuType = 2]
							[#if item2.link?index_of("?") > -1]
								<li><a href="${item2.link}&mid=${item2.id}&pid=${item.pid}" title="">${item2.name}</a></li>
							[#else]
								<li><a href="${item2.link}?mid=${item2.id}&pid=${item.pid}" title="">${item2.name}</a></li>
							[/#if]
						[#else]
							[#if item2.link?index_of("?") > -1]
								<li><a href="${base}${item2.link}&mid=${item2.id}&pid=${item.pid}" title="">${item2.name}</a></li>
							[#else]
								<li><a href="${base}${item2.link}?mid=${item2.id}&pid=${item.pid}" title="">${item2.name}</a></li>
							[/#if]
						[/#if]
					[#else]
					<li><a href="javascript:;" title="">${item2.name}</a></li>
					[/#if]
				[/#list]
			</ul>
			[/#if]
		</li>
	    [/#list]
	</ul>
</div>