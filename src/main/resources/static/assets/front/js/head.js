$(document).ready(function(){
	$(".nev li").each(function(index){
					$(this).hover(function(){
					    $("li:eq("+index+") .nev-menu").show();
				    },function(){
					    $("li:eq("+index+") .nev-menu").hide();
				     });
				});
});