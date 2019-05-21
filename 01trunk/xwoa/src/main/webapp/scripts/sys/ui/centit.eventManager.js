/**
 * 事件管理器
 * 
 * @param name
 * @returns
 */
function EventManager(name) {
	this.name = name;
	
	// 事件对象池
	this._EVENT_POOL = [];
	
	// 前拦截器
	this._BEFORE_INTERCEPTOR = {};
	
	// 后拦截器
	this._AFTER_INTERCEPTOR = {};
}

EventManager.prototype._DEBUG = false;

EventManager.prototype.ASSEMBLY_LINE = {
	authenticate: {},
	prepare: {before:"authenticate"},
	cancel: {},
	process: {},
	validate: {},
	excute: {before: "validate"},
	callback: {}
};

EventManager.prototype.debug = function(debug) {
	this._DEBUG = debug;
};

EventManager.prototype.getAssembly = function(name) {
	return this.ASSEMBLY_LINE[name];
};

EventManager.prototype.getBeforeInterceptor = function(name) {
	return this._BEFORE_INTERCEPTOR[name];
};

EventManager.prototype.getAfterInterceptor = function(name) {
	return this._AFTER_INTERCEPTOR[name];
};

/**
 * 添加监听对象
 * 
 * @param name
 * @returns {EventObject}
 */
EventManager.prototype.addEvent = function(name) {
	var object = new EventObject(name, this);
	this._EVENT_POOL.push(object);
	
	return object;
};

/**
 * 设置全局属性，可以在过程函数中调用
 * @param name
 * @param value
 */
EventManager.prototype.setProperty = function(name, value) {
	this[name] = value;
	
	return this;
};

/**
 * 取全局属性，可以在过程函数中调用
 * @param name
 * @param value
 */
EventManager.prototype.getProperty = function(name) {
	return this[name];
};

/**
 * 初始化，加载事件监听
 */
EventManager.prototype.init = function() {
	var manager = this;
	// 事件池中读取事件
	for (var name in this._EVENT_POOL) {
		
		var eventObject = this._EVENT_POOL[name];
		var listens = eventObject.getEventListens();
		
		// 需要添加监听事件的ACTION
		for(var eventName in listens) {
			var listen = listens[eventName];
			
			// 多个监听事件可能对应同一个ACTION
			for(var i=0; i<listen.length; i++) {
				var listenObj = listen[i];
				
				// 闭包保存循环时变量的值
				(function(eventObject, listenObj, eventName) {
					var object = listenObj.object;
					var method = listenObj.method;
					var eventParams = listenObj.params;
					
					// String变成Jquery对象
					if (typeof object == "string") {
						object = $(object);
					}
					
					eventParams.push(function(event) {
						eventObject.excuteAction(eventName, event, this);
					});
					
					object[method].apply(object, eventParams);
					if (manager._DEBUG && window.console) window.console.log("【" + eventObject.eventName + "】", "添加事件：", eventName, "对象：", object, "方法：", method, "参数：", eventParams);
				})(eventObject, listenObj, eventName);
				
			}
		}
	}
};

EventManager.prototype.addBeforeInterceptor = function(name, interceptor) {
	this._BEFORE_INTERCEPTOR[name] = interceptor;
	return this;
};

EventManager.prototype.addAfterInterceptor = function(name, interceptor) {
	this._AFTER_INTERCEPTOR[name] = interceptor;
	return this;
};

/**
 * 事件对象
 * 
 * @param name
 * @returns
 */
function EventObject(name, manager) {
	this.eventName = name;
	this.manager = manager;
	
	this._EVENT_ACTION = {};
	
	this._EVENT_LISTEN = {};
	
	this.RESULT = {};
}

/**
 * 设置全局属性，可以在过程函数中调用
 * @param name
 * @param value
 */
EventObject.prototype.setProperty = function(name, value) {
	this.manager[name] = value;
	
	return this;
};

/**
 * 取全局属性，可以在过程函数中调用
 * @param name
 * @param value
 */
EventObject.prototype.getProperty = function(name) {
	return this.manager[name];
};

/**
 * 执行action
 * @param name
 * @param params
 */
EventObject.prototype.excuteAction = function() {
	var params = [];
	var name = arguments[0];
	
	// 重新获取参数
	for (var i=1; i<arguments.length; i++) {
		params.push(arguments[i]);
	}
	
	var assembly = this.manager.getAssembly(name);

	// 前缀方法、
	var before = assembly.before;
	if (before) {
		params.unshift(before);
		this.excuteAction.apply(this, params);
		if (this.RESULT[before] != undefined){
			params.push(this.RESULT[before]);
		}
		params.shift();
	}
	
	// 执行主方法
	var action=this.getEventAction(name);
	if (action) {
		// 前拦截
		var beforeInterceptor = this.manager.getBeforeInterceptor(name);
		if (beforeInterceptor) {
			if (this.manager._DEBUG && window.console) window.console.log("【"+this.eventName+"】", "执行前拦截：", name, "参数：", params); 
			var result = beforeInterceptor.apply(this, params);
			if (this.manager._DEBUG && window.console) window.console.log("【"+this.eventName+"】", "执行前拦截：", "返回：", result); 
			if (result != undefined) params.push(result);
		}
		
		if (this.manager._DEBUG && window.console) window.console.log("【"+this.eventName+"】", "执行：", name, "参数：", params); 
		this.RESULT[name] = action.apply(this, params);
		if (this.manager._DEBUG && window.console) window.console.log("【"+this.eventName+"】", "执行：", name, "返回：", this.RESULT[name]); 
		
		// 后拦截
		var afterInterceptor = this.manager.getAfterInterceptor(name);
		if (afterInterceptor) {
			if (this.manager._DEBUG && window.console) window.console.log("【"+this.eventName+"】", "执行后拦截：", name, "参数：", this.RESULT[name]); 
			var result = afterInterceptor.call(this, this.RESULT[name]);
			if (this.manager._DEBUG && window.console) window.console.log("【"+this.eventName+"】", "执行后拦截：", name, "返回：", result); 
		}
	}
	
	// 后缀方法
	var after = assembly.after;
	if (after) {
		this.excuteAction(before, this.RESULT[name]);
	}
	
};

EventObject.prototype.getEventListens = function(name) {
	if (!name) return this._EVENT_LISTEN;
	
	return this._EVENT_LISTEN[name];
};

EventObject.prototype.getEventAction = function(name) {
	if (!name) return this._EVENT_ACTION;
	
	return this._EVENT_ACTION[name];
};

/**
 * 添加事件监听
 * @param name
 * @param object
 * @param method
 * @param params
 * @returns {EventObject}
 */
EventObject.prototype.addEventLitsen = function(name, object, method, params) {
	if (params && !$.isArray(params)) params = [params];
	var listen = {
		object: object,
		method: method || "on",
		params: params || []
	};
	var value = this._EVENT_LISTEN[name];
	
	if (!value) {
		this._EVENT_LISTEN[name] = [listen];
	}
	else {
		this._EVENT_LISTEN[name].push(listen);
	}
	
	return this;
};

/**
 * 添加事件函数
 * @param name
 * @param action
 * @returns {EventObjcet}
 */
EventObject.prototype.setEventAction = function(name, action) {
	this._EVENT_ACTION[name] = action;
	
	return this;
};

