$(function() {
	function AjaxRequest(opts) {
		this.type = opts.type || "post";
		this.url = opts.url;
		this.param = opts.data || {};
		this.isShowLoader = opts.isShowLoader || true;
		this.dataType = opts.dataType || "json";
		this.success = opts.success;
		this.contentType = opts.contentType || "application/x-www-form-urlencoded";
		this.showLoader = function() {
			if (this.isShowLoader) {
				var index = layer.msg('<b>&nbsp;&nbsp;加载中···</b>', {
					  icon: 16 ,shade: 0.1, time:0
				});
				
				this.layerIndex = index;
			}
		},
		this.hideLoader = function() {
			if (this.isShowLoader) {
				layer.close(this.layerIndex);
			}
		},
		this.sendRequest = function() {
			var self = this;
			$.ajax({
				type : this.type,
				url : this.url,
				data : this.param,
				dataType : this.dataType,
				contentType:this.contentType,
				beforeSend : this.showLoader(),
				success : function(res) {
					self.hideLoader();
					if (res != null && res != "") {
						if (self.success) {
							 //Object.prototype.toString.call方法--精确判断对象的类型
							if (Object.prototype.toString.call(self.success) === "[object Function]") {
								self.success(res);
							} else {
								console.log("callBack is not a function");
							}
						}
					}
				}
			});
		}
	}
	$.ax = function(opts) {
		new AjaxRequest(opts).sendRequest();
	};
});

Date.prototype.Format = function (fmt) {
	var o = {
		"M+": this.getMonth() + 1, //月份
		"d+": this.getDate(), //日
		"H+": this.getHours(), //小时
		"m+": this.getMinutes(), //分
		"s+": this.getSeconds(), //秒
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度
		"S": this.getMilliseconds() //毫秒
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}