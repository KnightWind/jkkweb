$(function() {
	var TreeRootId = "AppTreeId";
	var iTree;
	var EventManager = {
		clickNode : function(event, treeId, treeNode) {
			if (treeNode.isRoot) {
				EventManager.bind({});
			} else {
				EventManager.bind(treeNode);
			}
		},
		bind : function(data) {
			$("#editForm input[name]").each(function() {
				var prop = $(this).attr("name");
				var value = data[prop];
				value = typeof value == "undefined" ? "" : value;
				$(this).val(value);
			});
		},
		initForm : function() {
			var nodes = iTree.getSelectedNodes();
			if (!nodes.length) {
				alert("请先选择菜单");
				return;
			}
			var node = nodes[0];
			var data = {};
			data.pid = node.isRoot ? 0 : node.id;
			data.parentName = node.isRoot ? "" : node.name;
			var children = node.children;
			if (children && children.length) {
				data.orderby = children[children.length - 1].orderby + 1;
			} else {
				data.orderby = 1;
			}
			data.icon = "";
			EventManager.bind(data);
		},
		save : function() {
			var data = $("#editForm").serializeObject();
			var isSave = !data.id;
			alert(JSON.stringify(data));
			$.ajax({
				url : ctx + "/admin/menu/save.do",
				type : "post",
				data : {
					data : JSON.stringify(data)
				},
				success : function(res) {
					res = eval("(" + res + ")");
					if (res.success) {
						EventManager.refreshNode(res.result, isSave);
						alert("保存成功");
					} else {
						alert(res.result);
					}
				}
			});
		},
		clear : function() {
			EventManager.bind({});
		},
		refreshNode : function(data, isSave) {
			var node = iTree.getSelectedNodes()[0];
			if (isSave) {
				data.isParent = false;
				data.parentName = node.name;
				iTree.addNodes(node, data);
			} else {
				node.name = data.name;
				node.link = data.link;
				node.icon = data.icon;
				iTree.updateNode(node);
			}
		},
		deleteNode : function() {
			var nodes = iTree.getSelectedNodes();
			if (!nodes.length) {
				alert("请先选择菜单");
				return;
			}
			var node = nodes[0];
			if (node.isRoot) {
				alert("根节点不能删除！");
				return;
			}
			if ((node.children && node.children.length) || node.isParent) {
				alert("请先删除子节点！");
				return;
			}
			if (!confirm("您确定要删除该菜单吗？")) {
				return;
			}
			$.ajax({
				url : ctx + "/admin/menu/remove.do",
				data : {
					id : node.id
				},
				success : function(res) {
					res = eval("(" + res + ")");
					if (res.success) {
						var parentNode = node.getParentNode();
						if (parentNode.children.length == 1) {
							parentNode.isParent = false;
							iTree.updateNode(parentNode);
						}
						iTree.removeNode(node);
						iTree.selectNode(parentNode, false);
					} else {
						alert(res.result);
					}
				}
			});
		}
	};

	function getRootNode() {
		return iTree.getNodeByParam("id", TreeRootId, null);
	}

	function onLoadTree(event, treeId, treeNode) {

	}

	function initTree() {
		var setting = {
			async : {
				enable : true,
				url : ctx + "/admin/menu/loadMenu.do",
				autoParam : [ "id" ]
			},
			view : {
				showIcon : false
			},
			callback : {
				onAsyncSuccess : onLoadTree,
				onClick : EventManager.clickNode
			}
		};
		iTree = $.fn.zTree.init($("#treeDemo"), setting, [ {
			id : TreeRootId,
			name : "菜单管理",
			isParent : true,
			isRoot : true
		} ]);

		var node = getRootNode();
		if (node) {
			iTree.reAsyncChildNodes(node);
		}
	}

	function initEvents() {
		$("#addBtn").click(EventManager.initForm);
		$("#delBtn").click(EventManager.deleteNode);
		$("#saveBtn").click(EventManager.save);
		$("#clearBtn").click(EventManager.clear);
	}

	initTree();
	initEvents();
});