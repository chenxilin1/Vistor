//<![CDATA[
function zoomBox() {this.index.apply(this, arguments)}
zoomBox.prototype = {
    index: function(win,zoom) {
        var win=document.getElementById(win);
        var box=document.getElementById(zoom);
        var img=box.getElementsByTagName('IMG')[0];
        var zoom=img.width/win.getElementsByTagName('IMG')[0].width;
        var z=Math.round(box.offsetWidth/2);
        win.onmousemove=function (e){
            e = e || window.event;
            var x=e.clientX,y=e.clientY, ori=win.getBoundingClientRect();
            if (x>ori.right+20||y>ori.bottom+20||x<ori.left-20||y<ori.top-20)box.style.display='none';
            x-=ori.left;
            y-=ori.top;
            box.style.left=x-z+'px';
            box.style.top=y-z+'px';
            img.style.left=-x*zoom+z+'px';
            img.style.top=-y*zoom+z+'px';
        }
        win.onmouseover=function (){box.style.display=''}
    }
};
window.onload=function (){
    x=new zoomBox('zoomPan','zoom')
}
 //]]>