<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'Video.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery-1.4.2.js" type="text/javascript"></script>  
    <script src="js/jwplayer.js" type="text/javascript"></script> 
     <script type="text/javascript">jwplayer.key="W+FSSIJICMeqqi4MQCwmdLePq9iq8HQqiVT5dQ==";</script>   

  </head>
 
  <body>
    <div id="container"></div>  
    <input type="button" class="player-play" value="播放" />  
    <input type="button" class="player-stop" value="停止" />  
    <input type="button" class="player-status" value="取得状态" />  
    <input type="button" class="player-current" value="当前播放秒数" />  
    <input type="button" class="player-goto" value="转到第30秒播放" />  
    <input type="button" class="player-length" value="视频时长(秒)" />  
  </body>
      <script type="text/javascript">  
        var thePlayer;  //保存当前播放器以便操作  
        $(function() {  
            thePlayer = jwplayer('container').setup({  
                flashplayer:"plugin/jwplayer.flash.swf", 
                file: "plugin/hsgj.mp4",  
                width: 500,  
                height: 350,  
                dock: false  
            });  
              
            //播放 暂停  
            $('.player-play').click(function() {  
                if (thePlayer.getState() != 'PLAYING') {  
                    thePlayer.play(true);  
                    this.value = '暂停';  
                } else {  
                    thePlayer.play(false);  
                    this.value = '播放';  
                }  
            });  
              
            //停止  
            $('.player-stop').click(function() { thePlayer.stop(); });  
              
            //获取状态  
            $('.player-status').click(function() {  
                var state = thePlayer.getState();  
                var msg;  
                switch (state) {  
                    case 'BUFFERING':  
                        msg = '加载中';  
                        break;  
                    case 'PLAYING':  
                        msg = '正在播放';  
                        break;  
                    case 'PAUSED':  
                        msg = '暂停';  
                        break;  
                    case 'IDLE':  
                        msg = '停止';  
                        break;  
                }  
                alert(msg);  
            });  
              
            //获取播放进度  
            $('.player-current').click(function() { alert(thePlayer.getPosition()); });  
      
            //跳转到指定位置播放  
            $('.player-goto').click(function() {  
                if (thePlayer.getState() != 'PLAYING') {    //若当前未播放，先启动播放器  
                    thePlayer.play();  
                }  
                thePlayer.seek(30); //从指定位置开始播放(单位：秒)  
            });  
              
            //获取视频长度  
            $('.player-length').click(function() { alert(thePlayer.getDuration()); });  
        });  
    </script>  
</html>
