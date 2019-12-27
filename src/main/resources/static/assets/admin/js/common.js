/**
 * YDUI 可伸缩布局方案
 * rem计算方式：设计图尺寸px / 100 = 实际rem  例: 100px = 1rem
 */
!function (window) {

	/* 设计图文档宽度 */
	var docWidth = 1920;

	var doc = window.document,
		docEl = doc.documentElement,
		resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize';

	var recalc = (function refreshRem() {
		var clientWidth = docEl.getBoundingClientRect().width;

		/* 8.55：小于320px不再缩小，11.2：大于420px不再放大 */
		docEl.style.fontSize = Math.max(Math.min(20 * (clientWidth / docWidth), 20), 10.66) * 5 + 'px';

		return refreshRem;
	})();
	if (!doc.addEventListener) return;
	window.addEventListener(resizeEvt, recalc, false);
	doc.addEventListener('DOMContentLoaded', recalc, false);

}(window);