<script type="text/template" id="TEMPLATE_TREE">
	<ul id="{{treeId}}"></ul>
</script>

<script type="text/template" id="TEMPLATE_IFRAME">
	<iframe id="menu_iframe_{{id}}" class="menu-iframe" scrolling="yes" frameborder="no" src="${pageContext.request.contextPath}{{url}}" ></iframe>
</script>

<script type="text/template" id="TEMPLATE_LINK">
	{{#children.length}}
	{{text}}
	{{/children.length}}
	{{^children.length}}
	<a href="${pageContext.request.contextPath}{{url}}" rel="{{id}}" class="menu-tab-link" onclick="return false;">{{text}}</a>
	{{/children.length}}
</script>