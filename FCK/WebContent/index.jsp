<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="res/common/js/jquery.js"></script>
<script src="res/fckeditor/fckeditor.js"></script>
<script>
 $(function(){
	    var fck = new FCKeditor("fck");    //productdesc是textarea的ID
		fck.BasePath = "res/fckeditor/";   //基本路径webapp/res/fckeditor/.
		fck.Height = 400 ;
		fck.Config["ImageUploadURL"] = "/product/uploadFck.do";  //上传图片的链接动作
		fck.ToolbarSet = "myTool";    //自定义工具栏 在 fckconfig.js中
		fck.ReplaceTextarea();        //转成Fck
	 
 })
</script>
<title>FCK</title>
</head>
<body>
<textarea id="fck"></textarea>
</body>
</html>
