$("document").ready(function () {
				var today=new Date();//获得当前时间
				var yy=today.getFullYear();
				var mm=today.getMonth()+1;
				var dd=today.getDate();
				var ww=today.getDay();
				var wd;
				switch(ww){
					case 0:
					      wd="日";
					      break;
					case 1:
					      wd="一";
					      break;
					case 2:
					      wd="二";
					      break;
				    case 3:
					      wd="三";
					      break;
				    case 4:
					      wd="四";
					      break;
					case 5:
					      wd="五";
					      break;
					case 6:
					      wd="六";
					      break;
				}
				$("#todayclock").html("<i>"+yy+"年"+mm+"月"+dd+"日&nbsp;&nbsp;&nbsp;&nbsp;星期"+wd+"</i>");
				/*导航栏特效*/
				$('.nev-menu li').hover(function(){
					$(this).parent().parent().parent().addClass("ahover");
				},function(){
					$(this).parent().parent().parent().removeClass("ahover");
				});
				$('.nev-ul>li').click(function(){
					$(this).addClass("ahover2").siblings().removeClass("ahover2");
					$(this).children(".nev-menu").show().parent().siblings().children(".nev-menu").hide();
				});
				$('.nev-ul>li').mousemove(function(){
					$(this).children(".nev-menu").show().parent().siblings().children(".nev-menu").hide();
				});
				/*选项卡切换功能*/
				$(".tab-head li").each(function(index) {
					$(this).click(function(){
						$(this).addClass("selected").siblings().removeClass("selected");
						$(".top-list .tab-content>div:eq("+index+")").show().siblings().hide();
					});
				});
				$(".hgt .bar li").each(function(index) {
					$(this).mousemove(function(){
						$(this).addClass("selected2").siblings().removeClass("selected2");
						$(".hgt .tab-content>div:eq("+index+")").show().siblings().hide();
					});
				});
				$(".hgt1 .bar li").each(function(index) {
					$(this).mousemove(function(){
						$(this).addClass("selected2").siblings().removeClass("selected2");
						$(".hgt1 .tab-content>div:eq("+index+")").show().siblings().hide();
					});
				});
				$(".hgt2 .bar li").each(function(index) {
					$(this).mousemove(function(){
						$(this).addClass("selected2").siblings().removeClass("selected2");
						$(".hgt2 .tab-content>div:eq("+index+")").show().siblings().hide();
					});
				});
				$(".hgt3 .bar li").each(function(index) {
					$(this).mousemove(function(){
						$(this).addClass("selected2").siblings().removeClass("selected2");
						$(".hgt3 .tab-content>div:eq("+index+")").show().siblings().hide();
					});
				});
				$(".tab li").each(function(index) {
					$(this).mousemove(function(){
						$(this).addClass("selected3").siblings().removeClass("selected3");
						$(" .content>div:eq("+index+")").show().siblings().hide();
					});
				});
				/*
				var loopTime = '';
				$.get("http://192.168.199.26:8080/peoplebank/front/loopTime", function(data){//只能ip获取,localhost和127.0.0.1均获取不到
					var dataTest = parseInt(data);
					loopTime = dataTest;
					//alert(loopTime);
					var timeId = setInterval(function () {//需回调,因ajax异步加载,get方法未执行完闭,此setInterval()方法已先执行,loopTime永远是初始化的时间,非最新更改的时间
                        showImg();
                       }, loopTime);   
			});
				
				
				顶部轮播图
				var $pic1=$(".pic1");
				var $uLi=$(".pic1>ul>li");
				var num=$uLi.length;
				var index=0;
				function showImg(){
			    		index++;
			    		if (index>num-1) {
			    			index=0;
			    		}
			    		$uLi.eq(index).fadeIn().siblings().fadeOut();
			    	}*/
			/*	var timeId = setInterval(function () {----------------^
                    showImg();
                   }, loopTime);   */
				//顶部轮播图
				//begin
				var loopTime = '2000';
				var $pic1=$(".pic1");
				var $uLi=$(".pic1>ul>li");
				var num=$uLi.length;
				var index=0;
				function showImg(){
			    		index++;
			    		if (index>num-1) {
			    			index=0;
			    		}
			    		$uLi.eq(index).fadeIn().siblings().fadeOut();
			    	}
				var timeId = setInterval(function () {
                    showImg();
                   }, loopTime);
				
				//end
				
				
				
			    	
                /*中支动态轮播图*/
                var $uLi2=$(".pic2>ul>li");
                var $arrowLeft = $('.arrow .left');
                var $arrowRight = $('.arrow .right');
				var leg=$uLi2.length;
				var $ol=$(".pic2>ol");
				var index2=0;
				var linum=0;
				while(linum<leg){
					$ol.append("<li></li>");
					linum++;
				}
				 var $oLi2 = $('.pic2>ol>li');
                  $oLi2.first().addClass('now');  // 默认给第一个小圆点添加now类

        // 鼠标经过小圆点时，显示出对应的图片
        $oLi2.mouseover(function () {
            $(this).addClass('now').siblings().removeClass('now');
            index2 = $(this).index();
            $uLi2.eq(index2).fadeIn().siblings().fadeOut();
        });
        // 1、点击右箭头：让当前图片的下一张图片淡入，其他图片淡出。
        $arrowRight.click(function () {
        	index2++;
            if (index2 > leg - 1) {
                index2 = 0;
            }

            // 点击右箭头修改呈现的图片时，对应的小圆点也跟着修改now类
            $oLi2.eq(index2).addClass('now').siblings().removeClass('now');
			$uLi2.eq(index2).fadeIn().siblings().fadeOut();
        });


        // 2、单击左箭头：让当前图片的上一张图片淡入，其他图片淡出。
        $arrowLeft.click(function () {
            index2--;
            if (index2 < 0) {
                index2 = leg - 1;
            }

            // 点击右箭头修改呈现的图片时，对应的小圆点也跟着修改now类
            $oLi2.eq(index2).addClass('now').siblings().removeClass('now');
			$uLi2.eq(index2).fadeIn().siblings().fadeOut();
        });
				function show(){
					index2++;
					if (index2>leg-1) {
						index2=0;
					}
					$oLi2.eq(index2).addClass('now').siblings().removeClass('now');
					$uLi2.eq(index2).fadeIn().siblings().fadeOut();
				}
				var timeId2 = setInterval(function () {show(); }, 2000);
				 var $pic2=$(".pic2");
        $pic2.hover(function(){
        	clearInterval(timeId2);
        },function(){
        	timeId2 = setInterval(function () {show(); }, 2000);
        });
        /*底部轮播图*/
                var $pic3=$(".pic3");
				var $uLi3=$(".pic3>ul>li");
				var num3=$uLi3.length;
				var index3=0;
				function showImg3(){
			    		index3++;
			    		if (index3>num3-1) {
			    			index3=0;
			    		}
			    		$uLi3.eq(index3).fadeIn().siblings().fadeOut();
			    	}
			    	var timeId3 = setInterval(function () {
                          showImg3();
                         }, 5000);    // 5秒自动切换
                /*飘窗*/
         var i = j = -1;  
       var e = $(".automv"); 
       /*  var e = $("");*/
        var win = $(window);  
  
        function intern() {  
            var width = e.width();  
            var height = e.height();  
            var left = parseInt(e.css("left"));  
            var top = parseInt(e.css("top"));  
            var windowWidth = win.width();  
            var windowHeight = win.height();  
            if (windowWidth - width < (left + i)) {  
                i = -i;  
            } else if ((left + i) < 0) {  
                i = -i;  
            }  
            if (windowHeight - height < (top + j)) {  
                j = -j;  
            } else if ((top + j) < 0) {  
                j = -j;  
            }  
            e.css({  
                left: left + i,  
                top: top + j  
            });  
        }  
        setInterval(intern, 20);
        /*5分钟后自动关闭飘窗*/
       /* setTimeout(function(){$('.automv').css("display","none")},60000);*/
       /* $('.automv').autoMove({angle:-Math.PI/4, speed:120});*/
                $('#closew').click(function(){
                	$('.automv').css("display","none");
                });
                /* setTimeout(function(){ $('#closew').click()},60000);*/
                 setTimeout("$('#closew').click()",60000*5);
			});