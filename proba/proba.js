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
		
		var drawHeight = elemTop + $window.height()*2/3;

		return ((docViewBottom >= elemTop) && (docViewBottom <= drawHeight));
	}

		
	$(window).scroll(function(){
		scrollh = $(window).scrollTop();		
		elemTop = $(".draw").offset().top;
		if(isScrolledIntoView(".draw")){
			var snaps = document.getElementsByClassName("prpr");
			var arr = [].slice.call(snaps);
			
			var elemTop = $(".draw").offset().top;
			var docViewBottom = $(window).scrollTop() + $(window).height();
			
			var scDraw = docViewBottom - elemTop;
			
			var del = $(window).height()*2/3;
			
			arr.forEach(function(i){
				
				var line=i.getAttribute("data-length");
				var data = line*scDraw/del;
				var style = "stroke-dasharray: "+data+"px, "+line+"px;"
				i.setAttribute("style",style);
			});
		}			
	});
	
});