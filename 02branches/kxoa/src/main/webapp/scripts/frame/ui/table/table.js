define(['jquery', 'Core', 'ui/table/editTable', 'ui/table/dynamicTable'], function($, Core, EditTable, DynamicTable) {
	
	var T = {
			
			init: function(container, options) {
				
				if (EditTable) {
					EditTable.init(container, options);
				}
				
				if (DynamicTable) {
					DynamicTable.init(container, options);
				}

			}

	};
	
	return T;
});