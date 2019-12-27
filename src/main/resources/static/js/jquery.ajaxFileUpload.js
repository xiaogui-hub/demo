/*!
AjaxFileUpload 1.0 by pierce1218@gmail.com.
A jQuery plugin, support asynchronous file upload and
asynchronous response (based on HTTP 1.1 Chunked protocol)


jQuery异步文件上传插件（参考ajaxfileupload）。
实现功能：
 1) 实现前端异步文件上传（基于iframe）。
 2) 支持异步响应结果（基于Http 1.1 chunked协议），用于返回后端文件处理进度。
            主要用于数据导入应用场景（如：显示Excel文件导入进度）


$.ajaxFileUpload({
  url: '/upload.html',
  secureuri: false,
  fileElementId: 'fileElementId',
  dataType: 'json',
  isAsyncResponse: true,  // 使用异步响应结果
  success: function(data, status) {
    //show progress
  },
  complete:function(data, status) {
    //show finish
  }
});
*/

(function($) {
  var _UploadIndex = 0;
    
  //create frame
  function createUploadIframe(id, uri) {
    var frameId = id;
    if(window.ActiveXObject) {
    	//兼容ie9
    	if(jQuery.browser.version == "9.0") {
    		io = document.createElement('iframe');
    		io.id = frameId;
    		io.name = frameId;
    	} else if(jQuery.browser.version == "6.0" || jQuery.browser.version == "7.0" || jQuery.browser.version == "8.0"){  
    		var io = document.createElement('<iframe id="' + frameId + '" name="' + frameId + '" />');
    		if(typeof uri== 'boolean'){
    			io.src = 'javascript:false';
    		}
    		else if(typeof uri== 'string'){
    			io.src = uri;
    		}
    	}
    } else {
        var io = document.createElement('iframe');
        io.id = frameId;
        io.name = frameId;
    }
    io.style.position = 'absolute';
    io.style.top = '-1000px';
    io.style.left = '-1000px';

    document.body.appendChild(io);
    return $(io);
  };
  
  //create form 
  function createUploadForm(id, oldFileId) {
    var formId = id;
    var fileId = id + "_File";
    var form = $('<form action="" method="POST" name="' + formId + '" id="' + formId + '" enctype="multipart/form-data"></form>'); 
    var oldElement = $('#' + oldFileId);
    var newElement = $(oldElement).clone();
    oldElement.attr('id', fileId);
    oldElement.before(newElement);
    oldElement.appendTo(form);
    //set attributes
    form.css('position', 'absolute');
    form.css('top', '-1200px');
    form.css('left', '-1200px');
    form.appendTo('body');
    return form;
  };

  var AjaxFileUpload = (function() {
    
    function AjaxFileUpload(options) {
      _UploadIndex ++;
      var _this = this;
      // TODO introduce global settings, allowing the client to modify them for all requests, not only timeout  
      this.options = $.extend({}, $.ajaxSettings, options);
      
      this.iframeId = 'jUploadFrame' + this.options.fileElementId + '_' + _UploadIndex;
      this.formId = 'jUploadForm' + this.options.fileElementId + '_' + _UploadIndex;
      
      this.form = createUploadForm(this.formId, this.options.fileElementId);
      this.iframe = createUploadIframe(this.iframeId, options.secureuri);
      this.isAsyncResponse = this.options.isAsyncResponse || false;
      this.requestDone = false;
        
      
      if( this.options.global && ! $.active++ ) {
        // Watch for a new set of requests
        $.event.trigger( "ajaxStart" );
      }
    
      // Create the request object
      this.response = {};
      if( this.options.global ) {
        $.event.trigger("ajaxSend", [this.response, this.options]);
      }
        
      try {
        var form = $(this.form);
        
        form.attr('action', options.url);
        form.attr('method', 'POST');
        form.attr('target', this.iframeId);
        if(form.encoding) {
          form.encoding = 'multipart/form-data';    
        } else {
          form.enctype = 'multipart/form-data';
        }
        
        form.submit();
      } catch(e) {
        this.handleError(options, this.response, null, e);
      }
    
      if( this.isAsyncResponse ) {
        this.handleAsyncResponse();
      } else {
        this.handleResponse();
      }
      if( this.options.timeout > 0 ) {
        this.checkTimeout(this.options.timeout);
      }
    }
    
    AjaxFileUpload.prototype.checkTimeout = function(timeout) {
      var _this = this;
      
      if(!!this.funcCheckTimeout) {
        clearTimeout(this.funcCheckTimeout);
      }
      
      this.funcCheckTimeout = setTimeout(function() {
        if( !this.requestDone ) {
          _this.responseCallback( "timeout" );
        }
      }, timeout);
    }
    
    AjaxFileUpload.prototype.handleResponse = function() {
      var _this = this;
      
      if(window.attachEvent){
        $(this.iframe)[0].attachEvent('onload', function() {
          _this.responseCallback();
        });
      } else {
        $(this.iframe)[0].addEventListener('load', function() {
          _this.responseCallback();
        }, false);
      }   
    }
    
    AjaxFileUpload.prototype.handleAsyncResponse = function() {
      var _this = this;
      
      if(!!this.funcHandleAsyncResponse) {
        clearTimeout(this.funcHandleAsyncResponse);
      }
      if(!this.requestDone) {
        this.funcHandleAsyncResponse = setTimeout(function() {
          _this.responseCallback();
        }, 200);
      }
      
    }
    
    AjaxFileUpload.prototype.responseCallback = function(isTimeout) {  
      var old = {
        text: this.response.text || "",
        xml: this.response.xml || ""
      }
      
      var io = $(this.iframe)[0];
      try {
        if(io.contentWindow) {
          this.response.text = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML : null;
          this.response.xml = io.contentWindow.document.XMLDocument ? io.contentWindow.document.XMLDocument : io.contentWindow.document;
        } else if(io.contentDocument) {
          this.response.text = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML : null;
          this.response.xml = io.contentDocument.document.XMLDocument ? io.contentDocument.document.XMLDocument : io.contentDocument.document;
        }
      } catch(e) {
        this.handleError(this.options, this.response, null, e);
      }

      if(isTimeout == "timeout") {
        this.handleTimeout();
        if(this.isAsyncResponse) {
          this.handleAsyncResponse();
        }
      } else {
        if(this.isAsyncResponse) {
          this.handleAsyncSuccess(this.response, old);
          this.handleAsyncResponse();
        } else {
          this.handleSuccess(this.response);
        }
      }
    }
    
    AjaxFileUpload.prototype.handleError = function(s, xhr, status, e) {
      // If a local callback was specified, fire it
      if ( s.error ) {
	    s.error.call( s.context || s, xhr, status, e );
      }
      
      // Fire the global callback
      if ( s.global ) {
	    (s.context ? $(s.context) : $.event).trigger( "ajaxError", [xhr, s, e] );
      }
    }
    
    AjaxFileUpload.prototype.handleTimeout = function() {
      requestDone = true;
      this.handleError(this.options, this.response, "error");
      this.requestComplete("error");
    }
    
    AjaxFileUpload.prototype.handleSuccess = function(r) {
      this.requestDone = true;
      var status = "success";
      var type = this.options.dataType;
      var data = (type == "xml" || !type) ? r.xml : r.text;
      
      var ex;
      try {
        // process the data (runs the xml through httpData regardless of callback)
        if(type == "script") { // if the type is "script", eval it in global context
          $.globalEval( data );
        } else if( type == "json" ) { // to JavaScript object
          data = this.json2Object( data );
        } else if( type == "html" ) { // evaluate scripts within html  
          $("<div>").html(data).evalScripts();
        }
      } finally {
        if( this.options.success ) {
          this.options.success( data, status );
        };
        
        // Fire the global callback
        if( this.options.global ) {
          $.event.trigger( "ajaxSuccess", [this.response, this.options] );
        };
        this.requestComplete(status);
      }
    }
    
    AjaxFileUpload.prototype.handleAsyncSuccess = function(cur, old) {
      var oldText = old.text || "";
      var curText = cur.text || "";
      
      if(!$.browser.msie) {
        oldText = this.cleanTagPre(oldText);
        curText = this.cleanTagPre(curText);
      }
      
      var newText = curText.substring(oldText.length);
      if(newText != "") {
        if( this.options.timeout > 0 ) {
           this.checkTimeout(this.options.timeout);
        }
        var data = /(\s*##HEAD##){0,1}((\s*.*)##0##){0,}(##END##){0,1}/.exec(newText);
        var dataHead = data[1];
        var dataBody = data[3];
        var dataEnd = data[4];
        
        if(!!dataBody) {
          var lines = dataBody.split("##0##");
          for(i=0; i<lines.length; i++) {
            if( this.options.success ) {
              this.options.success(lines[i], "success");
            };
          }
        }
        
        if(dataEnd == '##END##') {
          this.requestDone = true;
          // Fire the global callback
          if( this.options.global ) {
            $.event.trigger( "ajaxSuccess", [this.response, this.options] );
          };
          this.requestComplete("success");
        } 
      }
    }
    
    AjaxFileUpload.prototype.requestComplete = function(status) {
      if( this.options.global ) {
        // The request was completed
        $.event.trigger( "ajaxComplete", [this.response, this.options] );
      };
          
      // Handle the global AJAX counter
      if(this.options.global && ! --$.active) {
        $.event.trigger("ajaxStop");
      };
          
      if(this.options.complete) {
        this.options.complete(this.response, status);
      };
      
      this.destroy();
    }
    
    // Convert JSON to the JavaScript object.
    AjaxFileUpload.prototype.json2Object = function(json) {
      if(json == null || json == "") {
        return "";
      }
    
      if($.browser.msie != "undefined" && $.browser.msie == true) { 
        return ($.browser.version == "7.0") ? eval('('+ json +')')
                                            : JSON.stringify( eval('('+ json +')') );
	  } else {
        return eval("(" + this.cleanTagPre(json) + ")");
	  }
    }
    
    // clean the tag of text '<pre>'    
    AjaxFileUpload.prototype.cleanTagPre = function(s) {
      // var jsonobj = eval('('+data.substring(5,data.length-6)+')');
      
      var start = s.indexOf(">");  
      if(start != -1) {
        var end = s.indexOf("<", start + 1);  
        if(end != -1) {
          s = s.substring(start + 1, end);  
        } 
      }
      return s;
    }
    
    AjaxFileUpload.prototype.destroy = function() {
      var _this = this;
      
      _this.iframe.unbind();
      setTimeout(function() {
        try {
          _this.iframe.remove();
          _this.form.remove(); 
        } catch(e) {
          this.handleError(_this.options, _this.response, null, e);
        }
      }, 100);
    }
    
    return AjaxFileUpload;
  })();  

  $.extend({
    ajaxFileUpload: function(options) {
      return {
        abort: function () {},
        uploader: new AjaxFileUpload(options)
      }; 
    }
  });

})(jQuery)
