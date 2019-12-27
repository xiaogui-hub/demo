$(document).ready(function(){
			/*分页*/
		   $('.M-box5').pagination({
               pageCount: 50,
               jump: true,
               callback: function (api) {
               var data = {
                      page: api.getCurrent(),
                      name: 'mss',
                       say: 'oh'
                    };
                $.getJSON('https://www.easy-mock.com/mock/58fff7a5739ac1685205ad5d/example/pagination#!method=get', data, function (json) {
                     console.log(json);
                });
              }
            });
            /*添加功能*/
            $("#add").click(function(){
            	$(this).next().toggle();
            });
            /*全选、全部选功能*/
            $("#all").click(function(){
            	var oInput=document.getElementsByName("essay");
            	for(var i=0;i<oInput.length;i++){
            		oInput[i].checked=document.getElementById("all").checked;
            	}
            });
           /*编辑功能*/
            $(".atc").click(function(){
                 $(".tip3").toggle();
            });
            /*弹出框中的取消按钮*/
            $(".cancel").click(function(){
                 $(this).parents(".fdf").toggle();
             });
           /* 删除功能
           $(".del").click(function(){
           	      $(this).parents("tr").empty();
           });*/
           /*批量删除*/
          /*  $("#btn2").click(function(){
            	var oInput=document.getElementsByName("essay");
            	for(var i=0;i<oInput.length;i++){
            		var che=oInput[i].checked;
            		if(che=="checked"){
            		$("[checked=checked]").parents("tr").remove();
            	}else{
            		alert("请选择所要删除的项");
            	}
            });*/
            /*图片放大功能*/
            $(".top-img img").hover(function(){
				var img=$(this).parent().html();
				var largen=$(".largen").empty().append(img).show();
			},function(){
			    $(".largen").hide();
			});
			
			/*上移、下移图片颜色改变*/
			$(".sort img:even").click(function(){
				$(this).hide().next().show();
				//alert($(this).next().html());
			})
		});
		/*排序-向上移动一行*/
		/* function moveUp(_a){
               var _row = _a.parentNode.parentNode;
               //如果不是第一行，则与上一行交换顺序
               var _node = _row.previousSibling;
               while(_node && _node.nodeType != 1){
                    _node = _node.previousSibling;
                 }
               if(_node&&_node.previousSibling){
                   swapNode(_row,_node);
               }else{
               	alert("我已经到达最高点了，亲！");
               }
         }
		  排序——向下移动一行
          function moveDown(_a){
               var _row = _a.parentNode.parentNode;
               //如果不是最后一行，则与下一行交换顺序
               var _node = _row.nextSibling;
               while(_node && _node.nodeType != 1){
                     _node = _node.nextSibling;
                }
               if(_node){
                    swapNode(_row,_node);
                }else{
               	   alert("我不能再往下了，亲！");
               }
            }
        function swapNode(node1,node2){
                 //获取父结点
                 var _parent = node1.parentNode;
                 //获取两个结点的相对位置
                 var _t1 = node1.nextSibling;
                 var _t2 = node2.nextSibling;
                 //将node2插入到原来node1的位置
                 if(_t1)_parent.insertBefore(node2,_t1);
                 else _parent.appendChild(node2);
                 //将node1插入到原来node2的位置
                 if(_t2)_parent.insertBefore(node1,_t2);
                 else _parent.appendChild(node1);
         }*/