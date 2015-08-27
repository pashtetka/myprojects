$(document).ready(function(){
	var scrollh = 0;
	var elemTop;
	var snaps = document.getElementsByClassName("prpr");
	var arr = [].slice.call(snaps);
	arr.forEach(function(i){
		var l=i.getAttribute("data-length");
			if(l==null){
				l=i.getTotalLength();
				i.setAttribute("data-length",l);
			}
	});
	
	function isScrolledIntoView(elem){
		var $elem = $(elem);
		var $window = $(window);

		var docViewTop = $window.scrollTop();
		var docViewBottom = docViewTop + $window.height();

		var elemTop = $elem.offset().top;
		var elemBottom = elemTop + $elem.height();

		return ((elemBottom <= docViewBottom) && (elemTop >= docViewTop));
	}

		
	$(window).scroll(function(){
		scrollh = $(window).scrollTop()+$(window).height();		
		elemTop = $(".draw").offset().top;
		if(isScrolledIntoView(".draw")){
			var snaps = document.getElementsByClassName("prpr");
			var arr = [].slice.call(snaps);
			var drawheight = $(window).scrollTop()+$(window).height()/4;
			var del = $(window).scrollTop()+$(window).height() - drawheight;
			arr.forEach(function(i){
				
				var line=i.getAttribute("data-length");
				var data = line*(scrollh-drawheight)/del;
				var style = "stroke-dasharray: "+data+"px, "+line+"px;"
				i.setAttribute("style",style);
			});
		}			
	});
	
});