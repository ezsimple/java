/**
 * 
 */
package com.linkwithweb.demos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 * @author Ashwin Kumar
 * 
 */
public class CommonsFileUploadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2178959757508467067L;
	private static final String TMP_DIR_PATH = "/tmp/upload";
	private static final String DESTINATION_DIR_PATH = "/tmp/upload";
	private File tmpDir;
	private File destinationDir;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		tmpDir = new File(TMP_DIR_PATH);
		if (!tmpDir.isDirectory()) {
			throw new ServletException(TMP_DIR_PATH + " is not a directory");
		}
		// String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
		destinationDir = new File(DESTINATION_DIR_PATH);
		if (!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH
					+ " is not a directory");
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		/*
		 * Set the size threshold, above which content will be stored on disk.
		 */
		fileItemFactory.setSizeThreshold(1 * 1024 * 1024); // 1 MB
		/*
		 * Set the temporary directory to store the uploaded files of size above
		 * threshold.
		 */
		fileItemFactory.setRepository(tmpDir);
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);

		PrintWriter out = response.getWriter();

		try {
			response.setContentType("text/plain");

			/*
			 * Parse the request
			 */
			List<?> items = uploadHandler.parseRequest(request);
			Iterator<?> itr = items.iterator();

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();

				if (item.isFormField()) {
					; /* Handle Form Fields. */
				} else {
					/*
					 * Write file to the ultimate location.
					 */
					File file = new File(destinationDir, item.getName());
					item.write(file);
					String msg = "Complete - "+item.getName()+" ,Size : "+FileUtils.byteCountToDisplaySize(item.getSize());
					log(msg);
					out.println(msg);
				}
			}
		} catch (FileUploadException ex) {
			log("Error encountered while parsing the request", ex);
		} catch (Exception ex) {
			log("Error encountered while uploading file", ex);
		} finally {
			out.close();
		}
	}
}
