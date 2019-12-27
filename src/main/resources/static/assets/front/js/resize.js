         $(document).ready(function(){
				$(".big").click(function(){
					$(".main").css({"font-size":"20px","line-height":"38px","letter-spacing":"2px"});
					$(".source .red").removeClass("red");
					$(this).addClass("red");
				});
				$(".mid").click(function(){
					$(".main").css("font-size","18px");
					$(".source .red").removeClass("red");
					$(this).addClass("red");
				});
				$(".small").click(function(){
					$(".main").css("font-size","16px");
					$(".source .red").removeClass("red");
					$(this).addClass("red");
				});
			});
			/*打印功能*/
       function printPage(){
       	     var newstr = document.getElementById("print").innerHTML;//得到需要打印的元素HTML
             var oldstr = document.body.innerHTML;//保存当前页面的HTML
             document.body.innerHTML = newstr;
             window.print();
            document.body.innerHTML = oldstr;
       }
       /*收藏功能*/
      function AddFavorite (sURL,sTitle) {
      	   var sTitle='';
      	   var sURL=location.href;
      	   if(window.sidebar) return true;
      	   try{
      	   	   window.external.AddFavorite(sURL,sTitle);
      	   }catch(e){
      	   	   alert("加入收藏失败，请使用ctrl+D进行添加");
      	   }
      	   return false;
      }
       /*关闭功能*/
      function close_plan(){
      	   window.close();
      	   window.open("index.html");
      }