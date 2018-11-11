alert("111")

/*初始化字体大小*/
function initFontSize() {
	document.querySelector('html').style.fontSize = window.innerWidth / 750 * 100 + 'px';
}

/*图片点击事件*/
function imageClick() {
    var imgArr = document.getElementsByTagName("img");
    for (var i = 0; i < imgArr.length; i++) {
        imgArr[i].pos = i;
        imgArr[i].onclick = function () {
            alter(this.src)
            window.androidEvent.openImage(this.src, this.pos)
        }
    }
}

/*用于测试android调用js方法*/
function callJS() {
    alert("Android调用了JS的callJS方法");
}

/*页面加载完毕后执行*/
window.onload = function {
    window.androidEvent.getHtmlContent(document.documentElement.outerHTML))
    initFontSize();
    imageClick();
}

