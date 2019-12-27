$("document").ready(function () {
	var $pic2=$(".pic2>ul>li");
				var leg=$pic2.length;
				var index=0;
				var $ol=$(".pic2>ol");
				var lino=0;
				while(lino<leg){
					$ol.append("<li></li>");
					lino++;
				}
				 var $oLi = $('.pic2>ol>li');
                  $oLi.first().addClass('now');  // 默认给第一个小圆点添加now类

        // 鼠标经过小圆点时，显示出对应的图片
        $oLi.mouseover(function () {
            $(this).addClass('now').siblings().removeClass('now');
            index = $(this).index();
            $pic2.eq(index).fadeIn().siblings().fadeOut();
        });
				function show(){
					index++;
					if (index>leg-1) {
						index=0;
					}
					$oLi.eq(index).addClass('now').siblings().removeClass('now');
					$pic2.eq(index).fadeIn().siblings().fadeOut();
				}
				var timeId2 = setInterval(function () {show(); }, 2000);
				 var $pic20=$(".pic2");
        $pic20.hover(function(){
        	clearInterval(timeId2);
        },function(){
        	timeId2 = setInterval(function () {show(); }, 2000);
        });
});