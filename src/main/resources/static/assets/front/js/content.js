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
            /*排序——降序*/
           	$(".descend").unbind('click').bind('click',function(){
           	     var ptr=$(this).parents("tr").html();
           	     var ntr=$(this).parents("tr").next().html();
           	     $(this).parents("tr").next().html(ptr);
           	     $(this).parents("tr").html(ntr);
             });
           /*排序——升序*/
            $(".rise").click(function(){
           	     var ptr=$(this).parents("tr").html();
           	     var ntr=$(this).parents("tr").prev().html();
           	     $(this).parents("tr").prev().html(ptr);
           	     $(this).parents("tr").html(ntr);
             });
           /*编辑功能*/
            $(".atc").click(function(){
                 $(".tip3").toggle();
            });
            /*删除功能*/
           $(".del").click(function(){
           	      $(this).parents("tr").empty();
           });
           /*批量删除*/
            $("#btn2").click(function(){
            	var oInput=document.getElementsByName("essay");
            	for(var i=0;i<oInput.length;i++){
            		var che=oInput[i].checked;
            		if(che=="checked"){
            		$("[checked=checked]").parents("tr").remove();
            	}else{
            		alert("请选择所要删除的项");
            	}
            	}
            })
		});