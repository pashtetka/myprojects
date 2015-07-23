window.onload = function() {
	var example = document.getElementById("example"), ctx = example
			.getContext('2d'), img = document.getElementById("image"), bod = document
			.getElementById("body");
	ctx.strokeStyle = "green";
	img.style.opacity = 0;
	bod.style.opacity = 0
	bod.style.marginLeft = "-110px";
	requestAnimationFrame(function func() {
		animate(function(i) {
			img.style.opacity = (1 + i * 99 / 89) / 100;
			bod.style.opacity = (1 + i * 99 / 89) / 100;
				l = -110 + (i-1)*13/9;
				bod.style.marginLeft = l + "px";
			img.height = 20;
			img.width = 20;
			ctx.beginPath();
			ctx.clearRect(0, 0, 640, 480);
			ctx.arc(60, 60, 56, Math.PI, (45 + i) / 45 * Math.PI, false);
			ctx.stroke();
			ctx.closePath();
		}, 90);
	});

	function animate(draw, duration) {
		i = 0;
		requestAnimationFrame(function animate(time) {
			draw(i);
			if (i < duration) {
				i++;
				requestAnimationFrame(animate);
			}

		});
	}
}