///////////////////////////////////////////////////////////////////////// 
//      getCookie();  setCookie();  deleteCookie();                   
/////////////////// ////////////////////////////////////////////////////
function getCookie (name) {
	var start = document.cookie.indexOf(name + "="), len, end;
	
	len = start + name.length + 1;
	
	if ((!start) && ( name !== document.cookie.substring(0, name.length))) {
		return null;
	}
	if (start === -1) {
	    return null;
	}
	
	end = document.cookie.indexOf(';', len);
	
	if (end === -1) {
	    end = document.cookie.length;
	}
	
	return unescape(document.cookie.substring(len, end));
}

function deleteCookie(name, path, domain) {
	if (getCookie(name)) { 
	    document.cookie = name + "=" + ((path) ? ";path=" + path : "") + ((domain) ? ";domain=" + domain : "") + ";expires=Thu, 01-Jan-1970 00:00:01 GMT";
	}
}

function setCookie(name, value, expires, path, domain, secure) {
	var today = new Date(), expires_date;
	today.setTime( today.getTime() );
	
	if (expires) {
		expires = expires * 1000 * 60 * 60 * 24;
	}
	
	expires_date = new Date( today.getTime() + (expires) );
	
	document.cookie = name+'='+escape(value) +
	( ( expires ) ? ';expires='+expires_date.toGMTString() : '') + //expires.toGMTString()
	( ( path ) ? ';path=' + path : '' ) +
	( ( domain ) ? ';domain=' + domain : '' ) +
	( ( secure ) ? ';secure' : '' );
}

/* 
****************************************************************************************************************
   Q's notes
   Event Handlers
   
   This is the object that keeps tracks of the Upload process. 
   The problem with SWFUpload object is that it resets the file count everytime we open the file dialog box
****************************************************************************************************************
*/
var FILEObject = {

	uploadedFiles: 0,
	
	queuedFiles: 0,

    printStatus: function (target, status) {
        document.getElementById(target).innerHTML = status;
    },
	
	//
    setTotalFiles: function (num) {
        if (this.queuedFiles) {
		    this.queuedFiles += num;
		}
		else {
		    this.queuedFiles = num;
		}
    },
	
    setUploadedFiles: function (num) {
        if (this.uploadedFiles) {
		    this.uploadedFiles += num;
		}
		else {
		    this.uploadedFiles = num;
		}
    }
};


/*
  This function is called when the upload dialog box is closed, i.e, when the user has finished selecting files.
*/
function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
      //  쿠키를 사용하지 않는다.
	  //  var mycookie = getCookie("filecookie");
	  //	
	  //  if (mycookie !== null && !FILEObject.queuedFiles) {
      //      mycookie = parseInt(mycookie, 10);	
	  //	    FILEObject.setTotalFiles(mycookie);
	  //		FILEObject.setUploadedFiles(mycookie);
	  //  }
      //
	    
		if (numFilesSelected > 0) {
            FILEObject.setTotalFiles(numFilesQueued);
			FILEObject.printStatus(this.customSettings.queuedTotal, FILEObject.queuedFiles);
			document.getElementById(this.customSettings.cancelButtonId).disabled = false;
		    document.getElementById(this.customSettings.progressBar).style.width = "0%";
		}
		
		/* I want auto start the upload and I can do that here */
		this.startUpload();
	} catch (ex)  {
        this.debug(ex);
	}
}


/*
  This function is called when there is a problem with a selected file 
*/
function fileQueueError(file, errorCode, message) {
	try {
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			alert("You have attempted to queue too many files.\n Upload limit exceeded.");
			return;
		}

		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			FILEObject.printStatus(this.customSettings.statusTarget, "File is too big.");
			alert("Please compress files before uploading them");
			this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			FILEObject.printStatus(this.customSettings.statusTarget, "Cannot upload Zero Byte files.");
			this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			FILEObject.printStatus(this.customSettings.statusTarget, "Invalid File Type.");
			this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		default:
			if (file !== null) {
				FILEObject.printStatus(this.customSettings.statusTarget, "Unhandled Error");
			}
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}


/*
  This function is called when there is a problem with a selected file 
*/
function uploadError(file, errorCode, message) {
	try {
		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			FILEObject.printStatus(this.customSettings.statusTarget, "Upload Error: " + message);
			this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			FILEObject.printStatus(this.customSettings.statusTarget, "Upload Failed.");
			this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			FILEObject.printStatus(this.customSettings.statusTarget, "Server (IO) Error");
			this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			FILEObject.printStatus(this.customSettings.statusTarget, "Security Error");
			this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			FILEObject.printStatus(this.customSettings.statusTarget, "Upload limit exceeded.");
			this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			FILEObject.printStatus(this.customSettings.statusTarget, "Failed Validation.  Upload skipped.");
			this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			// If there aren't any files left (they were all cancelled) disable the cancel button
			if (this.getStats().files_queued === 0) {
				document.getElementById(this.customSettings.cancelButtonId).disabled = true;
			}
			FILEObject.printStatus(this.customSettings.statusTarget, "Cancelled");
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			FILEObject.printStatus(this.customSettings.statusTarget, "Stopped");
			break;
		default:
			FILEObject.printStatus(this.customSettings.statusTarget, "Unhandled Error: " + errorCode);
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}


/*
  This function is called when a file starts to upload
*/
function uploadStart(file) {
	try {
		FILEObject.printStatus(this.customSettings.statusTarget, file.name + ' loading...');
	}
	catch (ex) {}
	
	return true;
}

/*
  This function is called when a file is loading
*/
function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
	    var stats = this.getStats(), currentfileprogress, allfilesprogress, totalprogress; 
		
		currentfileprogress = Math.floor(((1 / FILEObject.queuedFiles) * (bytesLoaded / bytesTotal)) * 100);    
		allfilesprogress = Math.ceil((stats.successful_uploads / FILEObject.queuedFiles) * 100);
			
		totalprogress = allfilesprogress + currentfileprogress;
		
		FILEObject.printStatus(this.customSettings.statusTarget, totalprogress + "% " + file.name + " loading...");
	} catch (ex) {
		this.debug(ex);
	}
}


/*
  This function is called when a file has been successifully uploaded
*/
function uploadSuccess(file, serverData) {
	try {
		FILEObject.printStatus(this.customSettings.statusTarget, "");
	} catch (ex) {
		this.debug(ex);
	}
}


/*
  This function is called when a file has been successifully uploaded
*/
function uploadComplete(file) {
	try {
	    var stats = this.getStats(), progress;
		
		FILEObject.uploadedFiles += 1;
		
		progress = Math.ceil((FILEObject.uploadedFiles / FILEObject.queuedFiles) * 100);
		
		FILEObject.printStatus(this.customSettings.loadedTotal, FILEObject.uploadedFiles);		
		document.getElementById(this.customSettings.progressBar).style.width = progress + "%";
		
		if (stats.files_queued === 0) {
		    document.getElementById(this.customSettings.cancelButtonId).disabled = true;
		}
	} catch (ex) {
		this.debug(ex);
	}
}


// This event comes from the Queue Plugin
function queueComplete(numFilesUploaded) {
    //
    //  쿠키를 사용하지 않는다.
    // var total_uploads = parseInt(numFilesUploaded, 10), mycookie = getCookie("filecookie");
	//
	// if (mycookie !== null) {	
	//    total_uploads += parseInt(mycookie, 10);
	// }
    //	
	// setCookie("filecookie",total_uploads);
	
	document.getElementById(this.customSettings.statusTarget).innerHTML = "File upload complete.";
}
