<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Simple SWFUpload for JAVA</title>
<link href='<c:url value="resources/bootstrap/css/bootstrap.css"/>' rel="stylesheet" type="text/css" />
<link href='<c:url value="resources/css/default.css"/>' rel="stylesheet" type="text/css" />
<script type="text/javascript" src='<c:url value="resources/js/jquery-1.3.2.js"/>'></script>
<script type="text/javascript" src='<c:url value="resources/js/swfupload/swfupload.js"/>'></script>
<script type="text/javascript" src='<c:url value="resources/js/swfupload/swfupload.swfobject.js"/>'></script>
<script type="text/javascript" src='<c:url value="resources/js/swfupload/swfupload.queue.js"/>'></script>
<script type="text/javascript" src='<c:url value="resources/js/swfupload/handlers.js"/>'></script>
<script type="text/javascript">
var swfu;
SWFUpload.onload = function () {
	    var settings = {
				flash_url : '<c:url value="resources/js/swfupload/swfupload.swf"/>',
				upload_url: "Upload",
              post_params : {'JSESSIONID':'<%=session.getId()%>'
			},
			file_size_limit : "1 GB",
			file_types : "*.*",
			file_types_description : "All Files",
			file_upload_limit : 0,
			file_queue_limit : 0,
			custom_settings : {
				progressTarget : "fsUploadProgress",
				cancelButtonId : "btnCancel",
				statusTarget : "statusDiv",
				progressContainer : "fsUploadProgress",
				queuedTotal : "queued_total",
				loadedTotal : "num_loaded",
				progressBar : "progressBar"
			},
			debug : false,

			// Button settings
			button_placeholder_id : "spanButtonPlaceHolder",
			// button_image_url : '<c:url value="resources/images/wdp_buttons_upload_114x29.png"/>',
			button_width : 114,
			button_height : 29,
			button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
			button_cursor : SWFUpload.CURSOR.HAND,

			// The event handler functions are defined in handlers.js
			file_dialog_complete_handler : fileDialogComplete,
			upload_start_handler : uploadStart,
			file_queue_error_handler : fileQueueError,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,
			queue_complete_handler : queueComplete, // Queue plugin event
			
	       // SWFObject settings
	       minimum_flash_version : "9.0.28"
		};
		swfu = new SWFUpload(settings);
	};
</script>
</head>
<body>
	<div class="container">
		<h2>Simple SWFUpload for JAVA</h2>

		<!--  The div that holds the total number of queued file and a counter of uploaded files   -->
		<div id="counterDiv">
			<span id="num_loaded">0</span> of <span id="queued_total">0</span>
			Files Uploaded
		</div>

		<div id="fsUploadProgress" class="progress">
  			<div id="progressBar" class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" style="width: 0%"></div>
		</div>

		<div id="statusDiv">&nbsp;</div>
		<div id="buttonDiv">
			<span id="spanButtonPlaceHolder"></span>
			<input id="btnUpload" type="button" class="btn btn-default btn-circle btn-success" value="Select Files">
			<input id="btnCancel" type="button" class="btn btn-default btn-circle btn-danger" value="Cancel All" onclick="swfu.cancelQueue();" disabled="disabled" />
		</div>
    </div>
<script>
</script>
</body>

</html>
