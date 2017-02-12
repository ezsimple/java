package net.ion.plugin.cstore.commUtil.shell;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.mozilla.universalchardet.UniversalDetector;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import net.ion.webapp.utils.CookieUtils;
import net.ion.webapp.utils.NetUtils;

/*
======================================================================
    주 시스템     : CSTORE
    서브시스템    : 
    프로그램 ID   : 
    프로그램 설명 : 셀러 파일 브라우저
    작성자        : Lee Min Ho
    작성일        : 2013. 1. 7.
    버전          : 1.2
----------------------------------------------------------------------
    수정이력      : 
          2013. 1. 3. 최초작성 - UTF-8 환경지원 & 한글지원
          2013. 1. 7. UTF-8 텍스트타입 파일 읽기/다운로드 기능 추가 - 수정시엔 인코팅타입 깨짐
          2013. 1. 8. UTF-8 텍스트타입 파일 수정(저장) 기능 추가 
          2013. 1. 8. UTF-8/EUC-KR 자동검출 기증 추가 .(일부 인코딩 타입만 지원됨:UniversalDetector)
          2013. 2.18. 파일 다운로드/View 시 MimeType 자동 검출기능 추가 (using Tika)
          2013. 2.18. 서블릿(자바클래스) 코드로 구현
          2013. 2.18. Spring 파일업로드 구현
          2013. 2.18. WebLogic 파일다운로드 오류 수정
          2013. 2.19. Direct Image Viewer 기능 추가
          2013. 2.19. Application Mimetype file Notiy 추가
          2013. 2.19. IE7,8 SSL file download error 보완
          2013. 2.20. 선택된 파일들의 ZIP 다운로드 기능 추가
          2014. 2.19. 업로드된 파일 날짜 자동변경 기능 추가
          2014. 2.20. 파일다운로드 방식 개선 : octet-stream 방식으로 바이너리 다운로드 구현함.
    기타사항      : 기본적으로 파일은 UTF-8인코딩 타입으로 처리함
----------------------------------------------------------------------
Copyrights I-ON Communications ECM Business Team. All right reserved.
======================================================================
 ionShell Browser 1.2
 Copyright (C) 2003-2006 Boris von Loesch
 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the
 Free Software Foundation; either version 2 of the License, or (at your option)
 any later version.
 This program is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 You should have received a copy of the GNU General Public License along with
 this program; if not, write to the
 Free Software Foundation, Inc.,
 59 Temple Place, Suite 330,
 Boston, MA 02111-1307 USA
 - Description: ionShell v1.2 -- This JSP program allows remote web-based
             file access and manipulation.  You can copy, create, move and delete files.
             Text files can be edited and groups of files and folders can be downloaded
             as a single zip file that's created on the fly.
 - Credits: Taylor Bastien, David Levine, David Cowan, Lieven Govaerts
 */
public final class ionShell extends HttpServlet {
	private final static long serialVersionUID = 1L;
	//FEATURES
	private final boolean NATIVE_COMMANDS = true;
	// If true, all operations (besides upload and native commands) 
	// which change something on the file system are permitted
	private final boolean READ_ONLY = false;
	//If true, uploads are allowed even if READ_ONLY = true
	private final boolean ALLOW_UPLOAD = true;
	//Allow browsing and file manipulation only in certain directories
	private final boolean RESTRICT_BROWSING = false;
	//If true, the user is allowed to browse only in RESTRICT_PATH,
	//if false, the user is allowed to browse all directories besides RESTRICT_PATH
	private final boolean RESTRICT_WHITELIST = false;
	//Paths, sperated by semicolon
	//private final String RESTRICT_PATH = "C:\\CODE;E:\\"; //Win32: Case important!!
	private final String RESTRICT_PATH = "/etc;/var";
	//The refresh time in seconds of the upload monitor window
	private final int UPLOAD_MONITOR_REFRESH = 2;
	//The number of colums for the edit field
	private final int EDITFIELD_COLS = 85;
	//The number of rows for the edit field
	private final int EDITFIELD_ROWS = 30;
	//Open a new window to view a file
	private final boolean USE_POPUP = true;
	// If USE_DIR_PREVIEW = true, then for every directory a tooltip will be
	// created (hold the mouse over the link) with the first DIR_PREVIEW_NUMBER entries.
	// This can yield to performance issues. Turn it off, if the directory loads to slow.
	private final boolean USE_DIR_PREVIEW = false;
	private final int DIR_PREVIEW_NUMBER = 10;
	// The name of an optional CSS Stylesheet file
	private final String CSS_NAME = "../css/ionShell.css";
	// The compression level for zip file creation (0-9)
	// 0 = No compression
	// 1 = Standard compression (Very fast)
	// ...
	// 9 = Best compression (Very slow)
	// private final int COMPRESSION_LEVEL = 1;
	// The FORBIDDEN_DRIVES are not displayed on the list. This can be usefull, if the
	// server runs on a windows platform, to avoid a message box, if you try to access
	// an empty removable drive (See KNOWN BUGS in Readme.txt).
	private final String[] FORBIDDEN_DRIVES = { "a:\\" };
	// Command of the shell interpreter and the parameter to run a programm
	// private final String[] COMMAND_INTERPRETER = { "cmd", "/C" }; // Dos,Windows
	private final String[] COMMAND_INTERPRETER = { "/bin/sh", "-c" }; // Unix
	// Max time in ms a process is allowed to run, before it will be terminated
	private final long MAX_PROCESS_RUNNING_TIME = 30 * 1000; //30 seconds
	//Button names
	private final String SAVE_AS_ZIP = "Download selected files as (z)ip";
	private final String RENAME_FILE = "(R)ename File";
	private String DELETE_FILES = "(Del)ete selected files";
	private final String CREATE_DIR = "Create (D)ir";
	private final String CREATE_FILE = "(C)reate File";
	private final String MOVE_FILES = "(M)ove Files";
	private final String COPY_FILES = "Cop(y) Files";
	private final String LAUNCH_COMMAND = "(L)aunch external program";
	private final String UPLOAD_FILES = "Upload";
	private String tempdir = ".";
	private String VERSION_NR = "1.2";
	private DateFormat dateFormat = DateFormat.getDateTimeInstance();
	private UploadMonitor uploadMonitor = new UploadMonitor();
	private String SERVER_SCHEME = "";
	private String SERVER_NAME = "";
	private final String PASSWD = "system";

	public String detectFileEncType(File f) {
		// 1. Set Default Type
		String encoding = null; 
		try {
			byte[] buf = new byte[4096];
			FileInputStream fis = new FileInputStream(f);
			// 2. Construct an instance of org.mozilla.universalchardet.UniversalDetector.
			UniversalDetector detector = new UniversalDetector(null);
			// 3. Feed some data (typically several thousands bytes) to the detector 
			//    by calling UniversalDetector.handleData().
			int nread;
			while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
				detector.handleData(buf, 0, nread);
			}
			// 4. Notify the detector of the end of data by calling UniversalDetector.dataEnd().
			detector.dataEnd();
			// 5. Get the detected encoding name 
			//    by calling UniversalDetector.getDetectedCharset().
			//    encoding is UTF-8/EUC-KR/.../NULL
			encoding = detector.getDetectedCharset();
			// 6. Don't forget to call UniversalDetector.reset() 
			//    before you reuse the detector instance.
			detector.reset();
		} catch (Exception e) { }
		return encoding;
	}
	public String toHangul(String _str) {
		String strRet = null;
		try {
			strRet = java.net.URLEncoder.encode(_str, "UTF-8"); // , "euc-kr"
			strRet = new String(strRet.getBytes("8859_1"), "UTF-8"); // KSC5601
		} 
        catch (Exception ue) // UnsupportedEncodingException
		{
			System.out.println("[EXCEPTION] ionShell.jsp toHangul()" + ue.toString());
		}
		return strRet;
	}
	public String fromHangul(String _str) {
		String strRet = null;
		try {
			strRet = java.net.URLDecoder.decode(_str, "UTF-8"); // , "euc-kr"
			strRet = new String(strRet.getBytes("8859_1"), "UTF-8"); // KSC5601
		}
		catch (Exception ue) {
			System.out.println("[EXCEPTION] ionShell.jsp fromHangul()" + ue.toString());
		}
		return strRet;
	}
	public class UplInfo {
		public long totalSize;
		public long currSize;
		public long starttime;
		public boolean aborted;
		public UplInfo() {
			totalSize = 0l;
			currSize = 0l;
			starttime = System.currentTimeMillis();
			aborted = false;
		}
		public UplInfo(int size) {
			totalSize = size;
			currSize = 0;
			starttime = System.currentTimeMillis();
			aborted = false;
		}
		public String getUprate() {
			long time = System.currentTimeMillis() - starttime;
			if (time != 0) {
				long uprate = currSize * 1000 / time;
				return convertFileSize(uprate) + "/s";
			} else
				return "n/a";
		}
		public int getPercent() {
			if (totalSize == 0)
				return 0;
			else
				return (int) (currSize * 100 / totalSize);
		}
		public String getTimeElapsed() {
			long time = (System.currentTimeMillis() - starttime) / 1000l;
			if (time - 60l >= 0) {
				if (time % 60 >= 10)
					return time / 60 + ":" + (time % 60) + "m";
				else
					return time / 60 + ":0" + (time % 60) + "m";
			} else
				return time < 10 ? "0" + time + "s" : time + "s";
		}
		public String getTimeEstimated() {
			if (currSize == 0)
				return "n/a";
			long time = System.currentTimeMillis() - starttime;
			time = totalSize * time / currSize;
			time /= 1000l;
			if (time - 60l >= 0) {
				if (time % 60 >= 10)
					return time / 60 + ":" + (time % 60) + "m";
				else
					return time / 60 + ":0" + (time % 60) + "m";
			} else
				return time < 10 ? "0" + time + "s" : time + "s";
		}
	}
	public class FileInfo {
		public String name = null, clientFileName = null, fileContentType = null;
		private byte[] fileContents = null;
		public File file = null;
		public StringBuffer sb = new StringBuffer(100);
		public void setFileContents(byte[] aByteArray) {
			fileContents = new byte[aByteArray.length];
			System.arraycopy(aByteArray, 0, fileContents, 0, aByteArray.length);
		}
	}
	public class UploadMonitor {
		@SuppressWarnings("rawtypes")
		Hashtable uploadTable = new Hashtable();
		@SuppressWarnings("unchecked")
		void set(String fName, UplInfo info) {
			uploadTable.put(fName, info);
		}
		void remove(String fName) {
			uploadTable.remove(fName);
		}
		UplInfo getInfo(String fName) {
			UplInfo info = (UplInfo) uploadTable.get(fName);
			return info;
		}
	}
	// A Class with methods used to process a ServletInputStream
	public class HttpMultiPartParser {
		//private final String lineSeparator = System.getProperty("line.separator", "\n");
		private final int ONE_MB = 1024 * 1;
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Hashtable processData(ServletInputStream is, String boundary, 
				String saveInDir, int clength) throws IllegalArgumentException,
				IOException {
			if (is == null)
				throw new IllegalArgumentException("InputStream");
			if (boundary == null || boundary.trim().length() < 1)
				throw new IllegalArgumentException("\"" + boundary
						+ "\" is an illegal boundary indicator");
			boundary = "--" + boundary;
			StringTokenizer stLine = null, stFields = null;
			FileInfo fileInfo = null;
			Hashtable dataTable = new Hashtable(5);
			String line = null, field = null, paramName = null;
			boolean saveFiles = (saveInDir != null && saveInDir.trim().length() > 0);
			boolean isFile = false;
			if (saveFiles) { // Create the required directory (including parent dirs)
				File f = new File(saveInDir);
				f.mkdirs();
			}
			line = getLine(is);
			if (line == null || !line.startsWith(boundary))
				throw new IOException("Boundary not found; boundary = "
						+ boundary + ", line(ServletInputStream) = " + line);
			while (line != null) {
				if (line == null || !line.startsWith(boundary))
					return dataTable;
				line = getLine(is);
				if (line == null)
					return dataTable;
				stLine = new StringTokenizer(line, ";\r\n");
				if (stLine.countTokens() < 2)
					throw new IllegalArgumentException("Bad data in second line");
				line = stLine.nextToken().toLowerCase();
				if (line.indexOf("form-data") < 0) 
					throw new IllegalArgumentException("Bad data in second line");
				stFields = new StringTokenizer(stLine.nextToken(), "=\"");
				if (stFields.countTokens() < 2)
					throw new IllegalArgumentException("Bad data in second line");
				fileInfo = new FileInfo();
				stFields.nextToken();
				paramName = stFields.nextToken();
				isFile = false;
				if (stLine.hasMoreTokens()) {
					field = stLine.nextToken();
					stFields = new StringTokenizer(field, "=\"");
					if (stFields.countTokens() > 1) {
						if (stFields.nextToken().trim().equalsIgnoreCase("filename")) {
							fileInfo.name = paramName;
							String value = stFields.nextToken();
							if (value != null && value.trim().length() > 0) {
								fileInfo.clientFileName = value;
								isFile = true;
							} else {
								line = getLine(is); // Skip "Content-Type:" line
								line = getLine(is); // Skip blank line
								line = getLine(is); // Skip blank line
								line = getLine(is); // Position to boundary line
								continue;
							}
						}
					} else if (field.toLowerCase().indexOf("filename") >= 0) {
						line = getLine(is); // Skip "Content-Type:" line
						line = getLine(is); // Skip blank line
						line = getLine(is); // Skip blank line
						line = getLine(is); // Position to boundary line
						continue;
					}
				}
				boolean skipBlankLine = true;
				if (isFile) {
					line = getLine(is);
					if (line == null)
						return dataTable;
					if (line.trim().length() < 1)
						skipBlankLine = false;
					else {
						stLine = new StringTokenizer(line, ": ");
						if (stLine.countTokens() < 2)
							throw new IllegalArgumentException("Bad data in third line");
						stLine.nextToken(); // Content-Type
						fileInfo.fileContentType = stLine.nextToken();
					}
				}
				if (skipBlankLine) {
					line = getLine(is);
					if (line == null)
						return dataTable;
				}
				if (!isFile) {
					line = getLine(is);
					if (line == null)
						return dataTable;
					dataTable.put(paramName, line);
					// If parameter is dir, change saveInDir to dir
					if (paramName.equals("dir"))
						saveInDir = line;
					line = getLine(is);
					continue;
				}
				try {
					UplInfo uplInfo = new UplInfo(clength);
					uploadMonitor.set(fileInfo.clientFileName, uplInfo);
					OutputStream os = null;
					String path = null;
					if (saveFiles)
						os = new FileOutputStream(path = getFileName(saveInDir, fileInfo.clientFileName));
					else
						os = new ByteArrayOutputStream(ONE_MB);
					boolean readingContent = true;
					byte previousLine[] = new byte[2 * ONE_MB];
					byte temp[] = null;
					byte currentLine[] = new byte[2 * ONE_MB];
					int read, read3;
					if ((read = is.readLine(previousLine, 0, previousLine.length)) == -1) {
						line = null;
						break;
					}
					while (readingContent) {
						if ((read3 = is.readLine(currentLine, 0, currentLine.length)) == -1) {
							line = null;
							uplInfo.aborted = true;
							break;
						}
						if (compareBoundary(boundary, currentLine)) {
							os.write(previousLine, 0, read - 2);
							line = new String(currentLine, 0, read3);
							break;
						} else {
							os.write(previousLine, 0, read);
							uplInfo.currSize += read;
							temp = currentLine;
							currentLine = previousLine;
							previousLine = temp;
							read = read3;
						}//end else
					}//end while
					os.flush();
					os.close();
					if (!saveFiles) {
						ByteArrayOutputStream baos = (ByteArrayOutputStream) os;
						fileInfo.setFileContents(baos.toByteArray());
					} else
						fileInfo.file = new File(path);
					dataTable.put(paramName, fileInfo);
					uplInfo.currSize = uplInfo.totalSize;
				}//end try
				catch (IOException e) {
					throw e;
				}
			}
			return dataTable;
		}
		/**
		 * Compares boundary string to byte array
		 */
		private boolean compareBoundary(String boundary, byte ba[]) {
			if (boundary == null || ba == null)
				return false;
			for (int i = 0; i < boundary.length(); i++)
				if ((byte) boundary.charAt(i) != ba[i])
					return false;
			return true;
		}
		/** Convenience method to read HTTP header lines */
		private synchronized String getLine(ServletInputStream sis)
				throws IOException {
			byte b[] = new byte[1024];
			int read = sis.readLine(b, 0, b.length), index;
			String line = null;
			if (read != -1) {
				line = new String(b, 0, read);
				if ((index = line.indexOf('\n')) >= 0)
					line = line.substring(0, index - 1);
			}
			return line;
		}
		public String getFileName(String dir, String fileName) throws IllegalArgumentException {
			String path = null;
			if (dir == null || fileName == null)
				throw new IllegalArgumentException("dir or fileName is null");
			int index = fileName.lastIndexOf('/');
			String name = null;
			if (index >= 0)
				name = fileName.substring(index + 1);
			else
				name = fileName;
			index = name.lastIndexOf('\\');
			if (index >= 0)
				fileName = name.substring(index + 1);
			path = dir + File.separator + fileName;
			if (File.separatorChar == '/')
				return path.replace('\\', File.separatorChar);
			else
				return path.replace('/', File.separatorChar);
		}
	} //End of class HttpMultiPartParser
	/**
	 * This class is a comparator to sort the filenames and dirs
	 */
	@SuppressWarnings("rawtypes")
	class FileComp implements Comparator {
		int mode;
		int sign;
		FileComp() {
			this.mode = 1;
			this.sign = 1;
		}
		/**
		 * @param mode sort by 1=Filename, 2=Size, 3=Date, 4=Type
		 * The default sorting method is by Name
		 * Negative mode means descending sort
		 */
		FileComp(int mode) {
			if (mode < 0) {
				this.mode = -mode;
				sign = -1;
			} else {
				this.mode = mode;
				this.sign = 1;
			}
		}
		public int compare(Object o1, Object o2) {
			File f1 = (File) o1;
			File f2 = (File) o2;
			if (f1.isDirectory()) {
				if (f2.isDirectory()) {
					switch (mode) {
					//Filename or Type
					case 1:
					case 4:
						return sign
								* f1.getAbsolutePath()
								.toUpperCase()
								.compareTo(
										f2.getAbsolutePath()
										.toUpperCase());
						//Filesize
					case 2:
						return sign
								* (new Long(f1.length()).compareTo(new Long(f2
										.length())));
						//Date
					case 3:
						return sign
								* (new Long(f1.lastModified())
								.compareTo(new Long(f2.lastModified())));
					default:
						return 1;
					}
				} else
					return -1;
			} else if (f2.isDirectory())
				return 1;
			else {
				switch (mode) {
				case 1:
					return sign
							* f1.getAbsolutePath()
							.toUpperCase()
							.compareTo(
									f2.getAbsolutePath().toUpperCase());
				case 2:
					return sign
							* (new Long(f1.length()).compareTo(new Long(f2
									.length())));
				case 3:
					return sign
							* (new Long(f1.lastModified()).compareTo(new Long(
									f2.lastModified())));
				case 4: { // Sort by extension
					int tempIndexf1 = f1.getAbsolutePath().lastIndexOf('.');
					int tempIndexf2 = f2.getAbsolutePath().lastIndexOf('.');
					if ((tempIndexf1 == -1) && (tempIndexf2 == -1)) { // Neither have an extension
						return sign
								* f1.getAbsolutePath()
								.toUpperCase()
								.compareTo(
										f2.getAbsolutePath()
										.toUpperCase());
					}
					// f1 has no extension
					else if (tempIndexf1 == -1)
						return -sign;
					// f2 has no extension
					else if (tempIndexf2 == -1)
						return sign;
					// Both have an extension
					else {
						String tempEndf1 = f1.getAbsolutePath().toUpperCase()
								.substring(tempIndexf1);
						String tempEndf2 = f2.getAbsolutePath().toUpperCase()
								.substring(tempIndexf2);
						return sign * tempEndf1.compareTo(tempEndf2);
					}
				}
				default:
					return 1;
				}
			}
		}
	}
	/**
	 * Wrapperclass to wrap an OutputStream around a Writer
	 */
	class Writer2Stream extends OutputStream {
		Writer out;
		Writer2Stream(Writer w) {
			super();
			out = w;
		}
		public void write(int i) throws IOException {
			out.write(i);
		}
		public void write(byte[] b) throws IOException {
			for (int i = 0; i < b.length; i++) {
				int n = b[i];
				//Convert byte to ubyte
				n = ((n >>> 4) & 0xF) * 16 + (n & 0xF);
				out.write(n);
			}
		}
		public void write(byte[] b, int off, int len) throws IOException {
			for (int i = off; i < off + len; i++) {
				int n = b[i];
				n = ((n >>> 4) & 0xF) * 16 + (n & 0xF);
				out.write(n);
			}
		}
	} //End of class Writer2Stream
	@SuppressWarnings({ "rawtypes", "unchecked" })
	Vector expandFileList(String[] files, boolean inclDirs) {
		Vector v = new Vector();
		if (files == null)
			return v;
		for (int i = 0; i < files.length; i++)
			v.add(new File(fromHangul(files[i])));
		for (int i = 0; i < v.size(); i++) {
			File f = (File) v.get(i);
			if (f.isDirectory()) {
				File[] fs = f.listFiles();
				for (int n = 0; n < fs.length; n++)
					v.add(fs[n]);
				if (!inclDirs) {
					v.remove(i);
					i--;
				}
			}
		}
		return v;
	}
	/**
	 * Method to build an absolute path
	 * @param dir the root dir
	 * @param name the name of the new directory
	 * @return if name is an absolute directory, returns name, else returns dir+name
	 */
	String getDir(String dir, String name) {
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File mv = new File(name);
		String new_dir = null;
		if (!mv.isAbsolute()) {
			new_dir = dir + name;
		} else
			new_dir = name;
		return new_dir;
	}
	/**
	 * This Method converts a byte size in a kbytes or Mbytes size, depending on the size
	 *     @param size The size in bytes
	 *     @return String with size and unit
	 */
	String convertFileSize(long size) {
		int divisor = 1;
		String unit = "bytes";
		if (size >= 1024 * 1024) {
			divisor = 1024 * 1024;
			unit = "MB";
		} else if (size >= 1024) {
			divisor = 1024;
			unit = "KB";
		}
		if (divisor == 1)
			return size / divisor + " " + unit;
		String aftercomma = "" + 100 * (size % divisor) / divisor;
		if (aftercomma.length() == 1)
			aftercomma = "0" + aftercomma;
		return size / divisor + "." + aftercomma + " " + unit;
	}
	/**
	 * Copies all data from in to out
	 * 	@param in the input stream
	 *	@param out the output stream
	 *	@param buffer copy buffer
	 */
	void copyStreams(InputStream in, OutputStream out, byte[] buffer)
			throws IOException {
		copyStreamsWithoutClose(in, out, buffer);
		in.close();
		out.close();
	}
	/**
	 * Copies all data from in to out
	 * 	@param in the input stream
	 *	@param out the output stream
	 *	@param buffer copy buffer
	 */
	void copyStreamsWithoutClose(InputStream in, OutputStream out,
			byte[] buffer) throws IOException {
		int b;
		while ((b = in.read(buffer)) != -1)
			out.write(buffer, 0, b);
	}
	
	private boolean isDirectViewMimeType(final String MimeType) {
		if (MimeType.contains("image") || MimeType.contains("audio") || MimeType.contains("video"))
			return true;
		return false;
	}
	
	private boolean isPlainTextType(final String MimeType,File f) {
		String fname = f.getName().toLowerCase();
		if(MimeType.contains("text"))
			return true;
		
		if(fname.endsWith("jsp")
				||fname.endsWith("js")
				||fname.endsWith("html")
				||fname.endsWith("htm")
				||fname.endsWith("txt")
				||fname.endsWith("xml")
				||fname.endsWith("tld")
				||fname.endsWith("properties")
				||fname.endsWith("css")
				||fname.endsWith("less")
				) 
			return true;
		
		return false;
	}
	
	public String getMimeType(File f) {
		String MimeType = null;
		FileInputStream is = null;
		try {
			is = new FileInputStream(f);
			BodyContentHandler contenthandler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());
			Parser parser = new AutoDetectParser();
			parser.parse(is, contenthandler, metadata, null);	
			MimeType = metadata.get(Metadata.CONTENT_TYPE);
			System.out.println("MimeType : "+MimeType+",FileName : "+f.getName());
		} catch (Throwable e) { }
		finally {
			if (is != null) {
				try { is.close(); }
				catch (IOException e) {}
				finally {}
			}
		}
		if(MimeType == null)
			MimeType = "application/octet-stream";
		return MimeType;
	}
	/**
	 * Converts some important chars (int) to the corresponding html string
	 */
	String conv2Html(int i) {
		if (i == '&')
			return "&amp;";
		else if (i == '<')
			return "&lt;";
		else if (i == '>')
			return "&gt;";
		else if (i == '"')
			return "&quot;";
		else
			return "" + (char) i;
	}
	/**
	 * Converts a normal string to a html conform string
	 */
	String conv2Html(String st) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < st.length(); i++) {
			buf.append(conv2Html(st.charAt(i)));
		}
		return buf.toString();
	}
	/**
	 * Starts a native process on the server
	 * 	@param command the command to start the process
	 *	@param dir the dir in which the process starts
	 */
	String startProcess(String command, String dir) throws IOException {
		StringBuffer ret = new StringBuffer();
		String[] comm = new String[3];
		comm[0] = COMMAND_INTERPRETER[0];
		comm[1] = COMMAND_INTERPRETER[1];
		comm[2] = command;
		long start = System.currentTimeMillis();
		try {
			//Start process
			Process ls_proc = Runtime.getRuntime().exec(comm, null,
					new File(dir));
			//Get input and error streams
			BufferedInputStream ls_in = new BufferedInputStream(
					ls_proc.getInputStream());
			BufferedInputStream ls_err = new BufferedInputStream(
					ls_proc.getErrorStream());
			boolean end = false;
			while (!end) {
				int c = 0;
				while ((ls_err.available() > 0) && (++c <= 1000)) {
					ret.append(conv2Html(ls_err.read()));
				}
				c = 0;
				while ((ls_in.available() > 0) && (++c <= 1000)) {
					ret.append(conv2Html(ls_in.read()));
				}
				try {
					ls_proc.exitValue();
					//if the process has not finished, an exception is thrown
					//else
					while (ls_err.available() > 0)
						ret.append(conv2Html(ls_err.read()));
					while (ls_in.available() > 0)
						ret.append(conv2Html(ls_in.read()));
					end = true;
				} catch (IllegalThreadStateException ex) {
					//Process is running
				}
				//The process is not allowed to run longer than given time.
				if (System.currentTimeMillis() - start > MAX_PROCESS_RUNNING_TIME) {
					ls_proc.destroy();
					end = true;
					ret.append("!!!! Process has timed out, destroyed !!!!!");
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException ie) {
				}
			}
		} catch (IOException e) {
			ret.append("Error: " + e);
		}
		return ret.toString();
	}
	/**
	 * Converts a dir string to a linked dir string
	 * 	@param dir the directory string (e.g. /usr/local/httpd)
	 *	@param browserLink web-path to ionShell.jsp
	 */
	String dir2linkdir(String dir, String browserLink, int sortMode) {
		File f = new File(dir);
		StringBuffer buf = new StringBuffer();
		while (f.getParentFile() != null) {
			if (f.canRead()) {
				String encPath = toHangul(f.getAbsolutePath());
				buf.insert(0, "<a href=\"" + browserLink + "?sort=" + sortMode
						+ "&amp;dir=" + encPath + "\">"
						+ conv2Html(f.getName()) + File.separator + "</a>");
			} else
				buf.insert(0, conv2Html(f.getName()) + File.separator);
			f = f.getParentFile();
		}
		if (f.canRead()) {
			String encPath = toHangul(f.getAbsolutePath());
			buf.insert(
					0,
					"<a href=\"" + browserLink + "?sort=" + sortMode
					+ "&amp;dir=" + encPath + "\">"
					+ conv2Html(f.getAbsolutePath()) + "</a>");
		} else
			buf.insert(0, f.getAbsolutePath());
		return buf.toString();
	}
	/**
	 *	Returns true if the given filename tends towards a packed file
	 */
	boolean isPacked(String name, boolean gz) {
		return (name.toLowerCase().endsWith(".zip")
				|| name.toLowerCase().endsWith(".jar")
				|| (gz && name.toLowerCase().endsWith(".gz")) || name
				.toLowerCase().endsWith(".war"));
	}
	/**
	 *	If RESTRICT_BROWSING = true this method checks, whether the path is allowed or not
	 */
	@SuppressWarnings("unused")
	boolean isAllowed(File path, boolean write) throws IOException {
		if (READ_ONLY && write)
			return false;
		if (RESTRICT_BROWSING) {
			StringTokenizer stk = new StringTokenizer(RESTRICT_PATH, ";");
			while (stk.hasMoreTokens()) {
				if (path != null
						&& path.getCanonicalPath().startsWith(stk.nextToken()))
					return RESTRICT_WHITELIST;
			}
			return !RESTRICT_WHITELIST;
		} else
			return true;
	}
	private void changeModificationFile(File file, long time) {
	    // round the value down to the nearest second
	    file.setLastModified((time / 1000) * 1000);
	}
	//---------------------------------------------------------------------------------------------------------------
	private final JspFactory jspFactory = JspFactory.getDefaultFactory();
	@SuppressWarnings({ "unchecked", "unused" })
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PageContext pageContext = null;
		HttpSession session = null;
		ServletContext application = null;
		JspWriter out = null;
		// Object page = this;
		JspWriter jspOut = null;
		PageContext jspPageContext = null;
		
		SERVER_SCHEME = request.getScheme();
		SERVER_NAME = SERVER_SCHEME+"://"+request.getServerName();

		try {
			pageContext = jspFactory.getPageContext(this, request, response, null, true, 8192, true);
			jspPageContext = pageContext;
			application = pageContext.getServletContext();
			// config = pageContext.getServletConfig();
			session = pageContext.getSession();
			String pw = (String) session.getAttribute("pw");
			if(pw==null || !PASSWD.equals(pw)) {
				pw=request.getParameter("pw")==null?"":request.getParameter("pw");
				session.setAttribute("pw", pw);
			}
			if(!PASSWD.equals(pw)) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			final String cookie = StringUtils.trim(CookieUtils.getCookie(request, "dev"));
			final String userId = StringUtils.trim(CookieUtils.getCookie(request, "PSSO_UserID"));
			if(!NetUtils.isLocal())
			if(StringUtils.isEmpty(cookie) || StringUtils.isEmpty(userId)){
				if(!NetUtils.isLocal()) { // 로컬호스트의 경우 PSSO 로그인을 사용하지 않음.
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
			}
			out = pageContext.getOut();
			jspOut = out;
			response.setContentType("text/html;charset=UTF-8");
			//Get the current browsing directory
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("dir", request.getParameter("dir"));
			// The browser_name variable is used to keep track of the URI
			// of the jsp file itself.  It is used in all link-backs.
			final String browser_name = request.getRequestURI();
			final String FOL_IMG = "";
			boolean nohtml = false;
			boolean dir_view = true;
			//Get Javascript
			if (request.getParameter("Javascript") != null) {
				dir_view = false;
				nohtml = true;
				//Tell the browser that it should cache the javascript
				response.setHeader("Cache-Control", "public");
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"EEE, d MMM yyyy HH:mm:ss z", Locale.US);
				response.setHeader(
						"Expires",
						sdf.format(new Date(now.getTime() + 1000 * 60 * 60 * 24
								* 2)));
				response.setHeader("Content-Type", "text/javascript");
				out.write("\n");
				out.write("");
				// This section contains the Javascript used for interface elements
				out.write("\n");
				out.write("var check = false;\n");
				out.write("");
				// Disables the checkbox feature
				out.write("\n");
				out.write("function dis(){check = true;}\n");
				out.write("\n");
				out.write("var DOM = 0, MS = 0, OP = 0, b = 0;\n");
				out.write("");
				// Determine the browser type
				out.write("\n");
				out.write("function CheckBrowser(){\n");
				out.write("if (b == 0){\n");
				out.write("if (window.opera) OP = 1;\n");
				out.write("// Moz or Netscape\n");
				out.write("if(document.getElementById) DOM = 1;\n");
				out.write("// Micro$oft\n");
				out.write("if(document.all && !OP) MS = 1;\n");
				out.write("b = 1;\n");
				out.write("}\n");
				out.write("}\n");
				out.write("");
				// Allows the whole row to be selected
				out.write("\n");
				out.write("function selrow (element, i){\n");
				out.write("var erst;\n");
				out.write("CheckBrowser();\n");
				out.write("if ((OP==1)||(MS==1)) erst = element.firstChild.firstChild;\n");
				out.write("else if (DOM==1) erst = element.firstChild.nextSibling.firstChild;\n");
				out.write("");
				// MouseIn
				out.write("\n");
				out.write("if (i==0){\n");
				out.write("if (erst.checked == true) element.className='mousechecked';\n");
				out.write("else element.className='mousein';\n");
				out.write("}\n");
				out.write("");
				// MouseOut
				out.write("\n");
				out.write("else if (i==1){\n");
				out.write("if (erst.checked == true) element.className='checked';\n");
				out.write("else element.className='mouseout';\n");
				out.write("}\n");
				out.write("");
				// MouseClick
				out.write("\n");
				out.write("else if ((i==2)&&(!check)){\n");
				out.write("if (erst.checked==true) element.className='mousein';\n");
				out.write("else element.className='mousechecked';\n");
				out.write("erst.click();\n");
				out.write("}\n");
				out.write("else check=false;\n");
				out.write("}\n");
				out.write("");
				// Filter files and dirs in FileList
				out.write("\n");
				out.write("function filter (begriff){\n");
				out.write("var suche = begriff.value.toLowerCase();\n");
				out.write("var table = document.getElementById(\"filetable\");\n");
				out.write("var ele;\n");
				out.write("for (var r = 1; r < table.rows.length; r++){\n");
				out.write("ele = table.rows[r].cells[1].innerHTML.replace(/<[^>]+>/g,\"\");\n");
				out.write("if (ele.toLowerCase().indexOf(suche)>=0 )\n");
				out.write("table.rows[r].style.display = '';\n");
				out.write("else table.rows[r].style.display = 'none';\n");
				out.write("      }\n");
				out.write("}\n");
				out.write("");
				//(De)select all checkboxes
				out.write("\n");
				out.write("function AllFiles(){\n");
				out.write("for(var x=0;x < document.FileList.elements.length;x++){\n");
				out.write("var y = document.FileList.elements[x];\n");
				out.write("var ytr = y.parentNode.parentNode;\n");
				out.write("var check = document.FileList.selall.checked;\n");
				out.write("if(y.name == 'selfile' && ytr.style.display != 'none'){\n");
				out.write("if (y.disabled != true){\n");
				out.write("y.checked = check;\n");
				out.write("if (y.checked == true) ytr.className = 'checked';\n");
				out.write("else ytr.className = 'mouseout';\n");
				out.write("}\n");
				out.write("}\n");
				out.write("}\n");
				out.write("}\n");
				out.write("\n");
				out.write("function shortKeyHandler(_event){\n");
				out.write("if (!_event) _event = window.event;\n");
				out.write("if (_event.which) {\n");
				out.write("keycode = _event.which;\n");
				out.write("} else if (_event.keyCode) {\n");
				out.write("keycode = _event.keyCode;\n");
				out.write("}\n");
				out.write("var t = document.getElementById(\"text_Dir\");\n");
				out.write("//z\n");
				out.write("if (keycode == 122){\n");
				out.write("document.getElementById(\"but_Zip\").click();\n");
				out.write("}\n");
				out.write("//r, F2\n");
				out.write("else if (keycode == 113 || keycode == 114){\n");
				out.write("var path = prompt(\"Please enter new filename\", \"\");\n");
				out.write("if (path == null) return;\n");
				out.write("t.value = path;\n");
				out.write("document.getElementById(\"but_Ren\").click();\n");
				out.write("}\n");
				out.write("//c\n");
				out.write("else if (keycode == 99){\n");
				out.write("var path = prompt(\"Please enter filename\", \"\");\n");
				out.write("if (path == null) return;\n");
				out.write("t.value = path;\n");
				out.write("document.getElementById(\"but_NFi\").click();\n");
				out.write("}\n");
				out.write("//d\n");
				out.write("else if (keycode == 100){\n");
				out.write("var path = prompt(\"Please enter directory name\", \"\");\n");
				out.write("if (path == null) return;\n");
				out.write("t.value = path;\n");
				out.write("document.getElementById(\"but_NDi\").click();\n");
				out.write("}\n");
				out.write("//m\n");
				out.write("else if (keycode == 109){\n");
				out.write("var path = prompt(\"Please enter move destination\", \"\");\n");
				out.write("if (path == null) return;\n");
				out.write("t.value = path;\n");
				out.write("document.getElementById(\"but_Mov\").click();\n");
				out.write("}\n");
				out.write("//y\n");
				out.write("else if (keycode == 121){\n");
				out.write("var path = prompt(\"Please enter copy destination\", \"\");\n");
				out.write("if (path == null) return;\n");
				out.write("t.value = path;\n");
				out.write("document.getElementById(\"but_Cop\").click();\n");
				out.write("}\n");
				out.write("//l\n");
				out.write("else if (keycode == 108){\n");
				out.write("document.getElementById(\"but_Lau\").click();\n");
				out.write("}\n");
				out.write("//Del\n");
				out.write("else if (keycode == 46){\n");
				out.write("document.getElementById(\"but_Del\").click();\n");
				out.write("}\n");
				out.write("}\n");
				out.write("\n");
				out.write("function popUp(URL){\n");
				out.write("fname = document.getElementsByName(\"myFile\")[0].value;\n");
				out.write("if (fname != \"\")\n");
				out.write("window.open(URL+\"?first&uplMonitor=\"+fname,\"\",\"width=400,height=150,resizable=yes,depend=yes\")\n");
				out.write("//window.open(URL+\"?first&uplMonitor=\"+encodeURIComponent(fname),\"\",\"width=400,height=150,resizable=yes,depend=yes\")\n");
				out.write("}\n");
				out.write("\n");
				out.write("document.onkeypress = shortKeyHandler;\n");
			}
			// View file
			else if (request.getParameter("file") != null) {
				String param = request.getParameter("file");
				param = fromHangul(param);
				File f = new File(param);
				if (!isAllowed(f, false)) {
					request.setAttribute("dir", f.getParent());
					request.setAttribute(
							"error",
							"You are not allowed to access "
									+ f.getAbsolutePath());
				} else if (f.exists() && f.canRead()) {
					if (isPacked(f.getName(), false)) {
						//If zipFile, do nothing here
					} else {
						String mimeType = getMimeType(f);
						if(isDirectViewMimeType(mimeType)) {
							response.setContentType(mimeType);
							response.setContentLength((int) f.length());
							response.resetBuffer(); 
							BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
							BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
							byte buffer[] = new byte[8 * 1024];
							int read = 0;
							while ((read = fileInput.read(buffer)) != -1) {
								outs.write(buffer,0,read);
							}//while
							fileInput.close();
							outs.close();
						} else if (!isPlainTextType(mimeType,f)) { // for prevent confusing
							out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
							out.write("<html>\r\n");
							out.write("<head>\r\n");
							out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
							out.write("<title>알려드립니다</title>\r\n");
							out.write("<link rel=\"shortcut icon\" href=\"/seller_res/images/favicon.ico\">\r\n");
							out.write("</head>\r\n");
							out.write("<body>\r\n");
							out.write("<br><br><br><br>\r\n");
							out.write("<center>\r\n");
							out.write("<table>\r\n");
							out.write("<tr><td>");
							out.print(f.getName());
							out.write(" 파일은</td></tr>\r\n");
							out.write("<tr><td>화면에 출력하지 못하는 형식의 파일입니다</td></tr>\r\n");
							out.write("<tr><td>다운로드 버튼을 이용해 주세요.</td></tr>\r\n");
							out.write("</table>\r\n");
							out.write("</center>\r\n");
							out.write("</body>\r\n");
							out.write("</html>");
						} else { // text/plain type etc...
							response.setContentType("text/plain"); // force setting mimeType
							response.setHeader("Content-Disposition",
									"inline;filename=\"" + f.getName() + "\"");
							String encoding = detectFileEncType(f);
							if (encoding == null)
								encoding = "UTF-8";
							BufferedReader in = new BufferedReader(
									new InputStreamReader(new FileInputStream(f),
											encoding));
							String str;
							while ((str = in.readLine()) != null) {
								out.write(str + "\n");
							}
							in.close();
						}
					}
					nohtml = true;
					dir_view = false;
				} else {
					request.setAttribute("dir", f.getParent());
					request.setAttribute(
							"error",
							"File "
									+ f.getAbsolutePath()
									+ " does not exist or is not readable on the server");
				}
			}
			// Download selected files as zip file
			else if ((request.getParameter("Submit") != null)
					&& (request.getParameter("Submit").equals(SAVE_AS_ZIP))) {
				@SuppressWarnings("rawtypes")
				Vector v = expandFileList(
						request.getParameterValues("selfile"), false);
				//Check if all files in vector are allowed
				String notAllowedFile = null;
				for (int i = 0; i < v.size(); i++) {
					File f = (File) v.get(i);
					if (!isAllowed(f, false)) {
						notAllowedFile = f.getAbsolutePath();
						break;
					}
				}
				if (notAllowedFile != null) {
					request.setAttribute("error",
							"You are not allowed to access " + notAllowedFile);
				} else if (v.size() == 0) {
					request.setAttribute("error", "No files selected");
				} else {
					File dir_file = new File("" + request.getAttribute("dir"));
					int dir_l = dir_file.getAbsolutePath().length();
					
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition","attachment;filename=\"selectedFiles.zip\"");
					ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
					for (int i = 0; i < v.size(); i++) {
						File f = (File) v.get(i);
						if (!f.canRead()) continue;
						ZipEntry entry = new ZipEntry(f.getAbsolutePath().substring(dir_l + 1));
						zos.putNextEntry(entry);
						FileInputStream fis = new FileInputStream(f);
						byte data[] = new byte[8 * 1024];
						int j = 0;
						while ((j = fis.read(data)) != -1) {
							zos.write(data, 0, j);
						}
						fis.close();
					}
					zos.closeEntry();
					zos.close();					
					
					nohtml = true;
					dir_view = false;
				}
			}
			// Download file
			else if (request.getParameter("downfile") != null) {
				String filePath = request.getParameter("downfile");
				// String filePathEng = null;
				// filePathEng = filePath;
				filePath = fromHangul(filePath);
				File f = new File(filePath);
				// File f2 = new File(filePathEng);
				if (!isAllowed(f, false)) {
					request.setAttribute("dir", f.getParent());
					request.setAttribute(
							"error",
							"You are not allowed to access "
									+ f.getAbsoluteFile());
				} else if (f.exists() && f.canRead()) {
					// String contentType = getMimeType(f);
					// response.setContentType(contentType);
					// response.setHeader("Content-Disposition",
					// 		"attachment;filename=\"" + f.getName() + "\"");
					// -------------------------------------------------------------------------------------
					// 파일다운로드 방식 개선 : octet-stream 방식으로 바이너리 다운로드 구현함. (2014.02.20)
					// -------------------------------------------------------------------------------------
					response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(f.getName(),"UTF-8"));
					response.setContentType("application/octet-stream;charset=utf-8");
					response.setContentLength((int) f.length());
					response.resetBuffer(); 
					BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
					BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
					IOUtils.copy(fileInput, outs);
					// byte buffer[] = new byte[8 * 1024];
					// int read = 0;
					// while ((read = fileInput.read(buffer)) != -1) {
					//	outs.write(buffer,0,read);
					// }//while
					fileInput.close();
					outs.close();
					nohtml = true;
					dir_view = false;
				} else {
					request.setAttribute("dir", f.getParent());
					request.setAttribute(
							"error",
							"File "
									+ f.getAbsolutePath()
									+ " does not exist or is not readable on the server");
				}
			}
			if (nohtml)
				return;
			//else
			// If no parameter is submitted, it will take the path from jsp file browser
			if (request.getAttribute("dir") == null) {
				String path = null;
				if (application.getRealPath(request.getRequestURI()) != null) {
					File f = new File(application.getRealPath(request
							.getRequestURI())).getParentFile();
					//This is a hack needed for tomcat
					while (f != null && !f.exists())
						f = f.getParentFile();
					if (f != null)
						path = f.getAbsolutePath();
				}
				if (path == null) { // handle the case where we are not in a directory (ex: war file)
					path = new File(".").getAbsolutePath();
				}
				//Check path
				if (!isAllowed(new File(path), false)) {
					if (RESTRICT_PATH.indexOf(";") < 0)
						path = RESTRICT_PATH;
					else
						path = RESTRICT_PATH.substring(0,
								RESTRICT_PATH.indexOf(";"));
				}
				request.setAttribute("dir", path);
			}
			out.write("\n");
			out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n");
			out.write("\"http://www.w3.org/TR/html4/loose.dtd\">\n");
			out.write("<html>\n");
			out.write("<head>\n");
			out.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n");
			out.write("<meta name=\"robots\" content=\"noindex\">\n");
			out.write("<meta http-equiv=\"expires\" content=\"0\">\n");
			if(!SERVER_SCHEME.equals("https")) // for prevent IE7,8 download error in SSL env
				out.write("<meta http-equiv=\"pragma\" content=\"no-cache\">\n");
			//If a cssfile exists, it will take it
			String cssPath = null;
			if (application.getRealPath(request.getRequestURI()) != null)
				cssPath = new File(application.getRealPath(request
						.getRequestURI())).getParent()
						+ File.separator
						+ CSS_NAME;
			if (cssPath == null)
				cssPath = application.getResource(CSS_NAME).toString();
			if (new File(cssPath).exists()) {
				out.write("\n");
				out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
				out.print(CSS_NAME);
				out.write("\">\n");
				out.write("      ");
			} else if (request.getParameter("uplMonitor") == null) {
				out.write("\n");
				out.write("<style type=\"text/css\">\n");
				out.write("input.button {background-color: #c0c0c0; color: #666666;\n");
				out.write("border: 1px solid #999999; margin: 5px 1px 5px 1px;}\n");
				out.write("input.textfield {margin: 5px 1px 5px 1px;}\n");
				out.write("input.button:Hover { color: #444444 }\n");
				out.write("table.filelist {background-color:#666666; width:100%; border:0px none #ffffff}\n");
				out.write(".formular {margin: 1px; background-color:#ffffff; padding: 1em; border:1px solid #000000;}\n");
				out.write(".formular2 {margin: 1px;}\n");
				out.write("th { background-color:#c0c0c0 }\n");
				out.write("tr.mouseout { background-color:#ffffff; }\n");
				out.write("tr.mousein  { background-color:#eeeeee; }\n");
				out.write("tr.checked  { background-color:#cccccc }\n");
				out.write("tr.mousechecked { background-color:#c0c0c0 }\n");
				out.write("td { font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 8pt; color: #666666;}\n");
				out.write("td.message { background-color: #FFFF00; color: #000000; text-align:center; font-weight:bold}\n");
				out.write("td.error { background-color: #FF0000; color: #000000; text-align:center; font-weight:bold}\n");
				out.write("A { text-decoration: none; }\n");
				out.write("A:Hover { color : Red; text-decoration : underline; }\n");
				out.write("BODY { font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 8pt; color: #666666;}\n");
				out.write("input#hostname {border:0;}\n");
				out.write("</style>\n");
				out.write("");
			}
			out.write("<link rel=\"shortcut icon\" href=\"/seller_res/images/favicon.ico\">\n");
			//Check path
			if (!isAllowed(new File((String) request.getAttribute("dir")),
					false)) {
				request.setAttribute("error", "You are not allowed to access "
						+ request.getAttribute("dir"));
			}
			//Upload monitor
			else if (request.getParameter("uplMonitor") != null) {
				out.write("\n");
				out.write("<style type=\"text/css\">\n");
				out.write("BODY { font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 8pt; color: #666666;}\n");
				out.write("</style>");
				request.setCharacterEncoding("UTF-8");
				String fname = request.getParameter("uplMonitor");
				fname = fromHangul(fname);
				//First opening
				boolean first = false;
				if (request.getParameter("first") != null)
					first = true;
				UplInfo info = new UplInfo();
				if (!first) {
					info = uploadMonitor.getInfo(fname);
					if (info == null) {
						//Windows
						int posi = fname.lastIndexOf("\\");
						if (posi != -1)
							info = uploadMonitor.getInfo(fname
									.substring(posi + 1));
					}
					if (info == null) {
						//Unix
						int posi = fname.lastIndexOf("/");
						if (posi != -1)
							info = uploadMonitor.getInfo(fname
									.substring(posi + 1));
					}
				}
				dir_view = false;
				request.setAttribute("dir", null);
				if (info != null && info.aborted) {
					uploadMonitor.remove(fname);
					out.write("\n");
					out.write("</head>\n");
					out.write("<body>\n");
					out.write("<b>Upload of[1] ");
					out.print(fname);
					out.write("</b><br><br>\n");
					out.write("Upload aborted.</body>\n");
					out.write("</html>");
				} else if (info != null
						&& (info.totalSize != info.currSize || info.currSize == 0)) {
					out.write("\n");
					out.write("<META HTTP-EQUIV=\"Refresh\" CONTENT=\"");
					out.print(UPLOAD_MONITOR_REFRESH);
					out.write(";URL=");
					out.print(browser_name);
					out.write("?uplMonitor=");
					out.print(toHangul(fname));
					out.write("\">\n");
					out.write("</head>\n");
					out.write("<body>\n");
					out.write("<b>Upload of[2] ");
					out.print(fname);
					out.write("</b><br><br>\n");
					out.write("<center>\n");
					out.write("<table height=\"20px\" width=\"90%\" bgcolor=\"#eeeeee\" style=\"border:1px solid #cccccc\"><tr>\n");
					out.write("<td bgcolor=\"blue\" width=\"");
					out.print(info.getPercent());
					out.write("%\"></td><td width=\"");
					out.print(100 - info.getPercent());
					out.write("%\"></td>\n");
					out.write("</tr></table></center>\n");
					out.print(convertFileSize(info.currSize));
					out.write(" from ");
					out.print(convertFileSize(info.totalSize));
					out.write("\n");
					out.write("(");
					out.print(info.getPercent());
					out.write(" %) uploaded (Speed: ");
					out.print(info.getUprate());
					out.write(").<br>\n");
					out.write("Time: ");
					out.print(info.getTimeElapsed());
					out.write(" from ");
					out.print(info.getTimeEstimated());
					out.write("\n");
					out.write("</body>\n");
					out.write("</html>");
				} else {
					uploadMonitor.remove(fname);
					out.write("\n");
					out.write("</head>\n");
					out.write("<body onload=\"javascript:window.close()\">\n");
					out.write("<b>Upload of[3] ");
					out.print(fname);
					out.write("</b><br><br>\n");
					out.write("Upload finished.\n");
					out.write("</body>\n");
					out.write("</html>");
				}
			}
			//Comandwindow
			else if (request.getParameter("command") != null) {
				if (!NATIVE_COMMANDS) {
					request.setAttribute("error","Execution of native commands is not allowed!");
				} else if (!"Cancel".equalsIgnoreCase(request.getParameter("Submit"))) {
					out.write("\n");
					out.write("<title>Launch commands in ");
					out.print(request.getAttribute("dir"));
					out.write("</title>\n");
					out.write("</head>\n");
					out.write("<body><center>\n");
					out.write("<h2>");
					out.print(LAUNCH_COMMAND);
					out.write("</h2><br />\n");
					out.println("<form action=\"" + browser_name
							+ "\" method=\"Post\">\n"
							+ "<textarea name=\"text\" wrap=\"off\" cols=\""
							+ EDITFIELD_COLS + "\" rows=\"" + EDITFIELD_ROWS
							+ "\" readonly>");
					String ret = "";
					if (!request.getParameter("command").equalsIgnoreCase(""))
						ret = startProcess(request.getParameter("command"),
								(String) request.getAttribute("dir"));
					ret = fromHangul(ret);
					out.println(ret);
					out.write("</textarea>\n");
					out.write("<input type=\"hidden\" name=\"dir\" value=\"");
					out.print(request.getAttribute("dir"));
					out.write("\">\n");
					out.write("<br /><br />\n");
					out.write("<table class=\"formular\">\n");
					out.write("<tr><td title=\"Enter your command\">\n");
					out.write("Command: <input size=\"");
					out.print(EDITFIELD_COLS - 5);
					out.write("\" type=\"text\" name=\"command\" value=\"\">\n");
					out.write("</td></tr>\n");
					out.write("<tr><td><input class=\"button\" type=\"Submit\" name=\"Submit\" value=\"Launch\">\n");
					out.write("<input type=\"hidden\" name=\"sort\" value=\"");
					out.print(request.getParameter("sort"));
					out.write("\">\n");
					out.write("<input type=\"Submit\" class=\"button\" name=\"Submit\" value=\"Cancel\"></td></tr>\n");
					out.write("</table>\n");
					out.write("</form>\n");
					out.write("<br />\n");
					out.write("<hr>\n");
					out.write("<center>\n");
					out.write("<small>ionShell version ");
					out.print(VERSION_NR);
					out.write(" by <a href=\"");
					out.print(SERVER_NAME);
					out.write("\">");
					out.print(SERVER_NAME);
					out.write("</a></small>\n");
					out.write("</center>\n");
					out.write("</center>\n");
					out.write("</body>\n");
					out.write("</html>\n");
					dir_view = false;
					request.setAttribute("dir", null);
				}
			}
			//Click on a filename, special viewer (zip+jar file)
			else if (request.getParameter("file") != null) {
				File f = new File(request.getParameter("file"));
				if (!isAllowed(f, false)) {
					request.setAttribute(
							"error",
							"You are not allowed to access "
									+ f.getAbsolutePath());
				} else if (isPacked(f.getName(), false)) {
					//ZipFile
					try {
						ZipFile zf = new ZipFile(f);
						@SuppressWarnings("rawtypes")
						Enumeration entries = zf.entries();
						out.write("\n");
						out.write("<title>");
						out.print(f.getAbsolutePath());
						out.write("</title>\n");
						out.write("</head>\n");
						out.write("<body>\n");
						out.write("<h2>Content of ");
						out.print(conv2Html(f.getName()));
						out.write("</h2><br />\n");
						out.write("<table class=\"filelist\" cellspacing=\"1px\" cellpadding=\"0px\">\n");
						out.write("<th>Name</th><th>Uncompressed size</th><th>Compressed size</th><th>Compr. ratio</th><th>Date</th>\n");
						long size = 0;
						int fileCount = 0;
						while (entries.hasMoreElements()) {
							ZipEntry entry = (ZipEntry) entries.nextElement();
							if (!entry.isDirectory()) {
								fileCount++;
								size += entry.getSize();
								long ratio = 0;
								if (entry.getSize() != 0)
									ratio = (entry.getCompressedSize() * 100)
									/ entry.getSize();
								out.println("<tr class=\"mouseout\"><td>"
										+ conv2Html(entry.getName())
										+ "</td><td>"
										+ convertFileSize(entry.getSize())
										+ "</td><td>"
										+ convertFileSize(entry
												.getCompressedSize())
												+ "</td><td>"
												+ ratio
												+ "%"
												+ "</td><td>"
												+ dateFormat.format(new Date(entry
														.getTime())) + "</td></tr>");
							}
						}
						zf.close();
						//No directory view
						dir_view = false;
						request.setAttribute("dir", null);
						out.write("\n");
						out.write("</table>\n");
						out.write("<p align=center>\n");
						out.write("<b>");
						out.print(convertFileSize(size));
						out.write(" in ");
						out.print(fileCount);
						out.write(" files in ");
						out.print(f.getName());
						out.write(". Compression ratio: ");
						out.print((f.length() * 100) / size);
						out.write("%\n");
						out.write("</b></p>\n");
						out.write("</body></html>\n");
					} catch (ZipException ex) {
						request.setAttribute("error",
								"Cannot read " + f.getName()
								+ ", no valid zip file");
					} catch (IOException ex) {
						request.setAttribute("error",
								"Reading of " + f.getName()
								+ " aborted. Error: " + ex);
					}
				}
			}
			// Upload
			else if ((request.getContentType() != null)
					&& (request.getContentType().toLowerCase().startsWith("multipart"))) {
				if (!ALLOW_UPLOAD){
					request.setAttribute("error", "Upload is forbidden!");
				}
				response.setContentType("text/html");
				HttpMultiPartParser parser = new HttpMultiPartParser();
				boolean error = false;
				try {
					int bstart = request.getContentType().lastIndexOf("oundary=");
					String bound = request.getContentType().substring(bstart + 8);
					int clength = request.getContentLength();
					@SuppressWarnings("rawtypes")
					Hashtable ht = parser
					.processData(request.getInputStream(), bound, tempdir, clength);
					if (!isAllowed(new File((String)ht.get("dir")), false)){
						//This is a hack, cos we are writing to this directory
						request.setAttribute("error", "You are not allowed to access " + ht.get("dir"));
						error = true;
					}
					else if (ht.get("myFile") != null) {
						FileInfo fi = (FileInfo) ht.get("myFile");
						File f = fi.file;
						UplInfo info = uploadMonitor.getInfo(fi.clientFileName);
						if (info != null && info.aborted) {
							f.delete();
							request.setAttribute("error", "Upload aborted");
						}
						else {
							// Move file from temp to the right dir
							String path = (String) ht.get("dir");
							if (!path.endsWith(File.separator)) path = path + File.separator;
							if (!f.renameTo(new File(path + f.getName()))) {
								request.setAttribute("error", "Cannot upload file.");
								error = true;
								f.delete();
							}
							if(f.exists()) { // 업로드된 파일의 날짜를, 가장 마지막 등록된 날짜에 맞춤
								// 초단위까지 제어가능할 때까지, 아래의 기능은 보류한다.
								// File folder = new File(path);
								// File[] filelist = folder.listFiles();
								// Arrays.sort(filelist, new FileComp(-3));
								// long date = filelist[0].lastModified() + 1L;
								// changeModificationFile(f,date);
								// f.setLastModified(date);
							}
						}
					}
					else {
						request.setAttribute("error", "No file selected for upload");
						error = true;
					}
					request.setAttribute("dir", (String) ht.get("dir"));
				}
				catch (Exception e) {
					request.setAttribute("error", "Error " + e + ". Upload aborted");
					error = true;
				}
				if (!error) request.setAttribute("message", "File upload correctly finished.");
			}
			// The form to edit a text file
			else if (request.getParameter("editfile") != null) {
				File ef = new File(request.getParameter("editfile"));
				if (!isAllowed(ef, true)) {
					request.setAttribute(
							"error",
							"You are not allowed to access "
									+ ef.getAbsolutePath());
				} else {
					out.write("\n");
					out.write("<title>Edit ");
					out.print(conv2Html(request.getParameter("editfile")));
					out.write("</title>\n");
					out.write("</head>\n");
					out.write("<body>\n");
					out.write("<center>\n");
					out.write("<h2>Edit ");
					out.print(conv2Html(request.getParameter("editfile")));
					out.write("</h2><br />\n");
					String encoding = detectFileEncType(ef);
					if (encoding == null)
						encoding = "UTF-8";
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(new FileInputStream(ef),
									encoding));
					// BufferedReader reader = new BufferedReader(new FileReader(ef));
					String disable = "";
					if (!ef.canWrite())
						disable = " readonly";
					out.println("<form action=\"" + browser_name
							+ "\" method=\"Post\">\n"
							+ "<textarea name=\"text\" wrap=\"off\" cols=\""
							+ EDITFIELD_COLS + "\" rows=\"" + EDITFIELD_ROWS
							+ "\"" + disable + ">");
					// Write out the file and check if it is a win or unix file
					int i;
					boolean dos = false;
					boolean cr = false;
					while ((i = reader.read()) >= 0) {
						out.print(conv2Html(i));
						if (i == '\r')
							cr = true;
						else if (cr && (i == '\n'))
							dos = true;
						else
							cr = false;
					}
					reader.close();
					//No File directory is shown
					request.setAttribute("dir", null);
					dir_view = false;
					out.write("</textarea><br /><br />\n");
					out.write("<table class=\"formular\">\n");
					out.write("<input type=\"hidden\" name=\"nfile\" value=\"");
					out.print(request.getParameter("editfile"));
					out.write("\">\n");
					out.write("<input type=\"hidden\" name=\"sort\" value=\"");
					out.print(request.getParameter("sort"));
					out.write("\">\n");
					out.write("<tr><td colspan=\"2\"><input type=\"radio\" name=\"lineformat\" value=\"dos\" ");
					out.print(dos ? "checked" : "");
					out.write(">Ms-Dos/Windows\n");
					out.write("<input type=\"radio\" name=\"lineformat\" value=\"unix\" ");
					out.print(dos ? "" : "checked");
					out.write(">Unix\n");
					out.write("<input type=\"checkbox\" name=\"Backup\" checked>Write backup</td></tr>\n");
					out.write("<tr><td title=\"Enter the new filename\"><input type=\"text\" name=\"new_name\" value=\"");
					out.print(ef.getName());
					out.write("\">\n");
					out.write("<input type=\"Submit\" name=\"Submit\" value=\"Save\"></td>\n");
					out.write("</form>\n");
					out.write("<form action=\"");
					out.print(browser_name);
					out.write("\" method=\"Post\">\n");
					out.write("<td align=\"left\">\n");
					out.write("<input type=\"Submit\" name=\"Submit\" value=\"Cancel\">\n");
					out.write("<input type=\"hidden\" name=\"nfile\" value=\"");
					out.print(request.getParameter("editfile"));
					out.write("\">\n");
					out.write("<input type=\"hidden\" name=\"sort\" value=\"");
					out.print(request.getParameter("sort"));
					out.write("\">\n");
					out.write("</td>\n");
					out.write("</form>\n");
					out.write("</tr>\n");
					out.write("</table>\n");
					out.write("</center>\n");
					out.write("<br />\n");
					out.write("<hr>\n");
					out.write("<center>\n");
					out.write("<small>ionShell version ");
					out.print(VERSION_NR);
					out.write(" by <a href=\"");
					out.print(SERVER_NAME);
					out.write("\">");
					out.print(SERVER_NAME);
					out.write("</a></small>\n");
					out.write("</center>\n");
					out.write("</body>\n");
					out.write("</html>\n");
				}
			}
			// Save or cancel the edited file
			else if (request.getParameter("nfile") != null) {
				File f = new File(request.getParameter("nfile"));
				if (request.getParameter("Submit").equals("Save")) {
					File new_f = new File(getDir(f.getParent(),
							request.getParameter("new_name")));
					if (!isAllowed(new_f, true)) {
						request.setAttribute(
								"error",
								"You are not allowed to access "
										+ new_f.getAbsolutePath());
					}
					if (new_f.exists() && new_f.canWrite()
							&& request.getParameter("Backup") != null) {
						File bak = new File(new_f.getAbsolutePath() + ".bak");
						bak.delete();
						new_f.renameTo(bak);
					}
					if (new_f.exists() && !new_f.canWrite())
						request.setAttribute("error", "Cannot write to "
								+ new_f.getName()
								+ ", file is write protected.");
					else {
						// BufferedWriter outs = new BufferedWriter(
						//		new FileWriter(new_f));
						String encoding = detectFileEncType(f);
						if (encoding == null)
							encoding = "UTF-8";
						BufferedWriter outs = new BufferedWriter(
								new OutputStreamWriter(new FileOutputStream(
										new_f), encoding));
						StringReader text = new StringReader(
								request.getParameter("text"));
						int i;
						boolean cr = false;
						String lineend = "\n";
						if (request.getParameter("lineformat").equals("dos"))
							lineend = "\r\n";
						while ((i = text.read()) >= 0) {
							if (i == '\r')
								cr = true;
							else if (i == '\n') {
								outs.write(lineend);
								cr = false;
							} else if (cr) {
								outs.write(lineend);
								cr = false;
							} else {
								outs.write(i);
								cr = false;
							}
						}
						outs.flush();
						outs.close();
					}
				}
				request.setAttribute("dir", f.getParent());
			}
			//Unpack file to the current directory without overwriting
			else if (request.getParameter("unpackfile") != null) {
				File f = new File(request.getParameter("unpackfile"));
				String root = f.getParent();
				request.setAttribute("dir", root);
				if (!isAllowed(new File(root), true)) {
					request.setAttribute("error",
							"You are not allowed to access " + root);
				}
				//Check if file exists
				else if (!f.exists()) {
					request.setAttribute("error",
							"Cannot unpack " + f.getName()
							+ ", file does not exist");
				}
				//Check if directory is readonly
				else if (!f.getParentFile().canWrite()) {
					request.setAttribute("error",
							"Cannot unpack " + f.getName()
							+ ", directory is write protected.");
				}
				//GZip
				else if (f.getName().toLowerCase().endsWith(".gz")) {
					//New name is old Name without .gz
					String newName = f.getAbsolutePath().substring(0,
							f.getAbsolutePath().length() - 3);
					try {
						byte buffer[] = new byte[0xffff];
						copyStreams(
								new GZIPInputStream(new FileInputStream(f)),
								new FileOutputStream(newName), buffer);
					} catch (IOException ex) {
						request.setAttribute("error",
								"Unpacking of " + f.getName()
								+ " aborted. Error: " + ex);
					}
				}
				//Else try Zip
				else {
					try {
						ZipFile zf = new ZipFile(f);
						@SuppressWarnings("rawtypes")
						Enumeration entries = zf.entries();
						//First check whether a file already exist
						boolean error = false;
						while (entries.hasMoreElements()) {
							ZipEntry entry = (ZipEntry) entries.nextElement();
							if (!entry.isDirectory()
									&& new File(root + File.separator
											+ entry.getName()).exists()) {
								request.setAttribute("error",
										"Cannot unpack " + f.getName()
										+ ", File " + entry.getName()
										+ " already exists.");
								error = true;
								break;
							}
						}
						if (!error) {
							//Unpack File
							entries = zf.entries();
							byte buffer[] = new byte[0xffff];
							while (entries.hasMoreElements()) {
								ZipEntry entry = (ZipEntry) entries
										.nextElement();
								File n = new File(root + File.separator
										+ entry.getName());
								if (entry.isDirectory())
									n.mkdirs();
								else {
									n.getParentFile().mkdirs();
									n.createNewFile();
									copyStreams(zf.getInputStream(entry),
											new FileOutputStream(n), buffer);
								}
							}
							zf.close();
							request.setAttribute("message",
									"Unpack of " + f.getName()
									+ " was successful.");
						}
					} catch (ZipException ex) {
						request.setAttribute("error",
								"Cannot unpack " + f.getName()
								+ ", no valid zip file");
					} catch (IOException ex) {
						request.setAttribute("error",
								"Unpacking of " + f.getName()
								+ " aborted. Error: " + ex);
					}
				}
			}
			// Delete Files
			else if ((request.getParameter("Submit") != null)
					&& (request.getParameter("Submit").equals(DELETE_FILES))) {
				@SuppressWarnings("rawtypes")
				Vector v = expandFileList(
						request.getParameterValues("selfile"), true);
				boolean error = false;
				//delete backwards
				for (int i = v.size() - 1; i >= 0; i--) {
					File f = (File) v.get(i);
					if (!isAllowed(f, true)) {
						request.setAttribute(
								"error",
								"You are not allowed to access "
										+ f.getAbsolutePath());
						error = true;
						break;
					}
					if (!f.canWrite() || !f.delete()) {
						request.setAttribute("error",
								"Cannot delete " + f.getAbsolutePath()
								+ ". Deletion aborted");
						error = true;
						break;
					}
				}
				if ((!error) && (v.size() > 1))
					request.setAttribute("message", "All files deleted");
				else if ((!error) && (v.size() > 0))
					request.setAttribute("message", "File deleted");
				else if (!error)
					request.setAttribute("error", "No files selected");
			}
			// Create Directory
			else if ((request.getParameter("Submit") != null)
					&& (request.getParameter("Submit").equals(CREATE_DIR))) {
				String dir = "" + request.getAttribute("dir");
				String dir_name = request.getParameter("cr_dir");
				String new_dir = getDir(dir, dir_name);
				if (!isAllowed(new File(new_dir), true)) {
					request.setAttribute("error",
							"You are not allowed to access " + new_dir);
				} else if (new File(new_dir).mkdirs()) {
					request.setAttribute("message", "Directory created");
				} else
					request.setAttribute("error", "Creation of directory "
							+ new_dir + " failed");
			}
			// Create a new empty file
			else if ((request.getParameter("Submit") != null)
					&& (request.getParameter("Submit").equals(CREATE_FILE))) {
				String dir = "" + request.getAttribute("dir");
				String file_name = request.getParameter("cr_dir");
				String new_file = getDir(dir, file_name);
				if (!isAllowed(new File(new_file), true)) {
					request.setAttribute("error",
							"You are not allowed to access " + new_file);
				}
				// Test, if file_name is empty
				else if (!"".equals(file_name.trim())
						&& !file_name.endsWith(File.separator)) {
					if (new File(new_file).createNewFile())
						request.setAttribute("message", "File created");
					else
						request.setAttribute("error", "Creation of file "
								+ new_file + " failed");
				} else
					request.setAttribute("error", "Error: " + file_name
							+ " is not a valid filename");
			}
			// Rename a file
			else if ((request.getParameter("Submit") != null)
					&& (request.getParameter("Submit").equals(RENAME_FILE))) {
				@SuppressWarnings("rawtypes")
				Vector v = expandFileList(
						request.getParameterValues("selfile"), true);
				String dir = "" + request.getAttribute("dir");
				String new_file_name = request.getParameter("cr_dir");
				String new_file = getDir(dir, new_file_name);
				if (!isAllowed(new File(new_file), true)) {
					request.setAttribute("error",
							"You are not allowed to access " + new_file);
				}
				// The error conditions:
				// 1) Zero Files selected
				else if (v.size() <= 0)
					request.setAttribute("error",
							"Select exactly one file or folder. Rename failed");
				// 2a) Multiple files selected and the first isn't a dir
				//     Here we assume that expandFileList builds v from top-bottom, starting with the dirs
				else if ((v.size() > 1) && !(((File) v.get(0)).isDirectory()))
					request.setAttribute("error",
							"Select exactly one file or folder. Rename failed");
				// 2b) If there are multiple files from the same directory, rename fails
				else if ((v.size() > 1)
						&& ((File) v.get(0)).isDirectory()
						&& !(((File) v.get(0)).getPath().equals(((File) v
								.get(1)).getParent()))) {
					request.setAttribute("error",
							"Select exactly one file or folder. Rename failed");
				} else {
					File f = (File) v.get(0);
					if (!isAllowed(f, true)) {
						request.setAttribute(
								"error",
								"You are not allowed to access "
										+ f.getAbsolutePath());
					}
					// Test, if file_name is empty
					else if ((new_file.trim() != "")
							&& !new_file.endsWith(File.separator)) {
						if (!f.canWrite()
								|| !f.renameTo(new File(new_file.trim()))) {
							request.setAttribute("error", "Creation of file "
									+ new_file + " failed");
						} else
							request.setAttribute("message", "Renamed file "
									+ ((File) v.get(0)).getName() + " to "
									+ new_file);
					} else
						request.setAttribute("error", "Error: \""
								+ new_file_name + "\" is not a valid filename");
				}
			}
			// Move selected file(s)
			else if ((request.getParameter("Submit") != null)
					&& (request.getParameter("Submit").equals(MOVE_FILES))) {
				@SuppressWarnings("rawtypes")
				Vector v = expandFileList(
						request.getParameterValues("selfile"), true);
				String dir = "" + request.getAttribute("dir");
				String dir_name = request.getParameter("cr_dir");
				String new_dir = getDir(dir, dir_name);
				if (!isAllowed(new File(new_dir), false)) {
					request.setAttribute("error",
							"You are not allowed to access " + new_dir);
				} else {
					boolean error = false;
					// This ensures that new_dir is a directory
					if (!new_dir.endsWith(File.separator))
						new_dir += File.separator;
					for (int i = v.size() - 1; i >= 0; i--) {
						File f = (File) v.get(i);
						if (!isAllowed(f, true)) {
							request.setAttribute(
									"error",
									"You are not allowed to access "
											+ f.getAbsolutePath());
							error = true;
							break;
						} else if (!f.canWrite()
								|| !f.renameTo(new File(new_dir
										+ f.getAbsolutePath().substring(
												dir.length())))) {
							request.setAttribute("error",
									"Cannot move " + f.getAbsolutePath()
									+ ". Move aborted");
							error = true;
							break;
						}
					}
					if ((!error) && (v.size() > 1))
						request.setAttribute("message", "All files moved");
					else if ((!error) && (v.size() > 0))
						request.setAttribute("message", "File moved");
					else if (!error)
						request.setAttribute("error", "No files selected");
				}
			}
			// Copy Files
			else if ((request.getParameter("Submit") != null)
					&& (request.getParameter("Submit").equals(COPY_FILES))) {
				@SuppressWarnings("rawtypes")
				Vector v = expandFileList(
						request.getParameterValues("selfile"), true);
				String dir = (String) request.getAttribute("dir");
				if (!dir.endsWith(File.separator))
					dir += File.separator;
				String dir_name = request.getParameter("cr_dir");
				String new_dir = getDir(dir, dir_name);
				if (!isAllowed(new File(new_dir), true)) {
					request.setAttribute("error",
							"You are not allowed to access " + new_dir);
				} else {
					boolean error = false;
					if (!new_dir.endsWith(File.separator))
						new_dir += File.separator;
					try {
						byte buffer[] = new byte[0xffff];
						for (int i = 0; i < v.size(); i++) {
							File f_old = (File) v.get(i);
							File f_new = new File(new_dir
									+ f_old.getAbsolutePath().substring(
											dir.length()));
							if (!isAllowed(f_old, false)
									|| !isAllowed(f_new, true)) {
								request.setAttribute("error",
										"You are not allowed to access "
												+ f_new.getAbsolutePath());
								error = true;
							} else if (f_old.isDirectory())
								f_new.mkdirs();
							// Overwriting is forbidden
							else if (!f_new.exists()) {
								copyStreams(new FileInputStream(f_old),
										new FileOutputStream(f_new), buffer);
							} else {
								// File exists
								request.setAttribute(
										"error",
										"Cannot copy "
												+ f_old.getAbsolutePath()
												+ ", file already exists. Copying aborted");
								error = true;
								break;
							}
						}
					} catch (IOException e) {
						request.setAttribute("error", "Error " + e
								+ ". Copying aborted");
						error = true;
					}
					if ((!error) && (v.size() > 1))
						request.setAttribute("message", "All files copied");
					else if ((!error) && (v.size() > 0))
						request.setAttribute("message", "File copied");
					else if (!error)
						request.setAttribute("error", "No files selected");
				}
			}
			// Directory viewer
			if (dir_view && request.getAttribute("dir") != null) {
				File f = new File(""
						+ fromHangul((String) request.getAttribute("dir")));
				//Check, whether the dir exists
				if (!f.exists() || !isAllowed(f, false)) {
					if (!f.exists()) {
						request.setAttribute("error",
								"Directory " + f.getAbsolutePath()
								+ " does not exist.");
					} else {
						request.setAttribute(
								"error",
								"You are not allowed to access "
										+ f.getAbsolutePath());
					}
					//if attribute olddir exists, it will change to olddir
					if (request.getAttribute("olddir") != null
							&& isAllowed(
									new File(
											(String) request
											.getAttribute("olddir")),
											false)) {
						f = new File("" + request.getAttribute("olddir"));
					}
					//try to go to the parent dir
					else {
						if (f.getParent() != null && isAllowed(f, false))
							f = new File(f.getParent());
					}
					//If this dir also do also not exist, go back to ionShell.jsp root path
					if (!f.exists()) {
						String path = null;
						if (application.getRealPath(request.getRequestURI()) != null)
							path = new File(application.getRealPath(request
									.getRequestURI())).getParent();
						if (path == null) // handle the case were we are not in a directory (ex: war file)
							path = new File(".").getAbsolutePath();
						f = new File(path);
					}
					if (isAllowed(f, false))
						request.setAttribute("dir", f.getAbsolutePath());
					else
						request.setAttribute("dir", null);
				}
				out.write("\n");
				out.write("<script type=\"text/javascript\" src=\"");
				out.print(browser_name);
				out.write("?Javascript=ion\">\n");
				out.write("</script>\n");
				out.write("<title>");
				out.print(request.getAttribute("dir"));
				out.write("</title>\n");
				out.write("</head>\n");
				out.write("<body>\n");
				//Output message
				if (request.getAttribute("message") != null) {
					out.println("<table border=\"0\" width=\"100%\"><tr><td class=\"message\">");
					out.println(request.getAttribute("message"));
					out.println("</td></tr></table>");
				}
				//Output error
				if (request.getAttribute("error") != null) {
					out.println("<table border=\"0\" width=\"100%\"><tr><td class=\"error\">");
					out.println(request.getAttribute("error"));
					out.println("</td></tr></table>");
				}
				if (request.getAttribute("dir") != null) {
					out.write("\n");
					out.write("\n");
					out.write("<form class=\"formular\" action=\"");
					out.print(browser_name);
					out.write("\" method=\"Post\" name=\"FileList\">\n");
					
					String hostname = "서버명 : ";
					try {hostname += InetAddress.getLocalHost().getHostName();} catch(Exception e) {};					
					out.write("<input type=text name=\"hostname\" id=\"hostname\" size=\"14\" value=\"");
					out.print(hostname);
					out.write("\" readonly />\n");	
					
					out.write("    Filename filter: <input name=\"filt\" onKeypress=\"event.cancelBubble=true;\" onkeyup=\"filter(this)\" type=\"text\">\n");
					out.write("    <br /><br />\n");
					out.write("<table id=\"filetable\" class=\"filelist\" cellspacing=\"1px\" cellpadding=\"0px\">\n");
					// Output the table, starting with the headers.
					String dir = toHangul((String) request.getAttribute("dir"));
					String cmd = browser_name + "?dir=" + dir;
					int sortMode = 1;
					if (request.getParameter("sort") != null)
						sortMode = Integer.parseInt(request
								.getParameter("sort"));
					int[] sort = new int[] { 1, 2, 3, 4 };
					for (int i = 0; i < sort.length; i++)
						if (sort[i] == sortMode)
							sort[i] = -sort[i];
					out.print("<tr><th><input type=\"checkbox\" name=\"selall\" onClick=\"AllFiles(this.form)\"></th><th title=\"Sort files by name\" align=left><a href=\""
							+ cmd
							+ "&amp;sort="
							+ sort[0]
									+ "\">Name</a></th>"
									+ "<th title=\"Sort files by size\" align=\"right\"><a href=\""
									+ cmd
									+ "&amp;sort="
									+ sort[1]
											+ "\">Size</a></th>"
											+ "<th title=\"Sort files by type\" align=\"center\"><a href=\""
											+ cmd
											+ "&amp;sort="
											+ sort[3]
													+ "\">Type</a></th>"
													+ "<th title=\"Sort files by date\" align=\"left\"><a href=\""
													+ cmd
													+ "&amp;sort="
													+ sort[2]
															+ "\">Date</a></th>"
															+ "<th>&nbsp;</th>");
					if (!READ_ONLY)
						out.print("<th>&nbsp;</th>");
					out.println("</tr>");
					// char trenner = File.separatorChar;
					// Output the Root-Dirs, without FORBIDDEN_DRIVES
					File[] entry = File.listRoots();
					for (int i = 0; i < entry.length; i++) {
						boolean forbidden = false;
						for (int i2 = 0; i2 < FORBIDDEN_DRIVES.length; i2++) {
							if (entry[i].getAbsolutePath().toLowerCase()
									.equals(FORBIDDEN_DRIVES[i2]))
								forbidden = true;
						}
						if (!forbidden) {
							out.println("<tr class=\"mouseout\" onmouseover=\"this.className='mousein'\""
									+ "onmouseout=\"this.className='mouseout'\">");
							out.println("<td>&nbsp;</td><td align=left >");
							String name = toHangul(entry[i].getAbsolutePath());
							String buf = entry[i].getAbsolutePath();
							out.println(" &nbsp;<a href=\"" + browser_name
									+ "?sort=" + sortMode + "&amp;dir=" + name
									+ "\">[" + buf + "]</a>");
							out.print("</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td></td></tr>");
						}
					}
					// Output the parent directory link ".."
					if (f.getParent() != null) {
						out.println("<tr class=\"mouseout\" onmouseover=\"this.className='mousein'\""
								+ "onmouseout=\"this.className='mouseout'\">");
						out.println("<td></td><td align=left>");
						out.println(" &nbsp;<a href=\"" + browser_name
								+ "?sort=" + sortMode + "&amp;dir="
								+ toHangul(f.getParent()) + "\">" + FOL_IMG
								+ "[..]</a>");
						out.print("</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td></td></tr>");
					}
					// Output all files and dirs and calculate the number of files and total size
					entry = f.listFiles();
					if (entry == null)
						entry = new File[] {};
					long totalSize = 0; // The total size of the files in the current directory
					long fileCount = 0; // The count of files in the current working directory
					if (entry != null && entry.length > 0) {
						Arrays.sort(entry, new FileComp(sortMode));
						for (int i = 0; i < entry.length; i++) {
							String name = toHangul(entry[i].getAbsolutePath());
							String type = "File"; // This String will tell the extension of the file
							if (entry[i].isDirectory())
								type = "DIR"; // It's a DIR
							else {
								String tempName = entry[i].getName().replace(
										' ', '_');
								if (tempName.lastIndexOf('.') != -1)
									type = tempName.substring(
											tempName.lastIndexOf('.'))
											.toLowerCase();
							}
							String ahref = "<a onmousedown=\"dis()\" href=\""
									+ browser_name + "?sort=" + sortMode
									+ "&amp;";
							String dlink = "&nbsp;"; // The "Download" link
							String elink = "&nbsp;"; // The "Edit" link
							String buf = conv2Html(entry[i].getName());
							if (!entry[i].canWrite())
								buf = "<i>" + buf + "</i>";
							String link = buf; // The standard view link, uses Mime-type
							if (entry[i].isDirectory()) {
								if (entry[i].canRead() && USE_DIR_PREVIEW) {
									//Show the first DIR_PREVIEW_NUMBER directory entries in a tooltip
									File[] fs = entry[i].listFiles();
									if (fs == null)
										fs = new File[] {};
									Arrays.sort(fs, new FileComp());
									StringBuffer filenames = new StringBuffer();
									for (int i2 = 0; (i2 < fs.length)
											&& (i2 < 10); i2++) {
										String fname = conv2Html(fs[i2]
												.getName());
										if (fs[i2].isDirectory())
											filenames
											.append("[" + fname + "];");
										else
											filenames.append(fname + ";");
									}
									if (fs.length > DIR_PREVIEW_NUMBER)
										filenames.append("...");
									else if (filenames.length() > 0)
										filenames
										.setLength(filenames.length() - 1);
									link = ahref + "dir=" + name
											+ "\" title=\"" + filenames + "\">"
											+ FOL_IMG + "[" + buf + "]</a>";
								} else if (entry[i].canRead()) {
									link = ahref + "dir=" + name + "\">"
											+ FOL_IMG + "[" + buf + "]</a>";
								} else
									link = FOL_IMG + "[" + buf + "]";
							} else if (entry[i].isFile()) { //Entry is file
								totalSize = totalSize + entry[i].length();
								fileCount = fileCount + 1;
								if (entry[i].canRead()) {
									dlink = ahref + "downfile=" + name
											+ "\">Download</a>";
									//If you click at the filename
									if (USE_POPUP)
										link = ahref + "file=" + name
										+ "\" target=\"_blank\">" + buf
										+ "</a>";
									else
										link = ahref + "file=" + name + "\">"
												+ buf + "</a>";
									if (entry[i].canWrite()) { // The file can be edited
										//If it is a zip or jar File you can unpack it
										if (isPacked(name, true))
											elink = ahref + "unpackfile="
													+ name + "\">Unpack</a>";
										else
											elink = ahref + "editfile=" + name
											+ "\">Edit</a>";
									} else { // If the file cannot be edited
										//If it is a zip or jar File you can unpack it
										if (isPacked(name, true))
											elink = ahref + "unpackfile="
													+ name + "\">Unpack</a>";
										else
											elink = ahref + "editfile=" + name
											+ "\">View</a>";
									}
								} else {
									link = buf;
								}
							}
							String date = dateFormat.format(new Date(entry[i]
									.lastModified()));
							out.println("<tr class=\"mouseout\" onmouseup=\"selrow(this, 2)\" "
									+ "onmouseover=\"selrow(this, 0);\" onmouseout=\"selrow(this, 1)\">");
							if (entry[i].canRead()) {
								out.println("<td align=center><input type=\"checkbox\" name=\"selfile\" value=\""
										+ name
										+ "\" onmousedown=\"dis()\"></td>");
							} else {
								out.println("<td align=center><input type=\"checkbox\" name=\"selfile\" disabled></td>");
							}
							out.print("<td align=left> &nbsp;" + link + "</td>");
							if (entry[i].isDirectory())
								out.print("<td>&nbsp;</td>");
							else {
								out.print("<td align=right title=\""
										+ entry[i].length() + " bytes\">"
										+ convertFileSize(entry[i].length())
										+ "</td>");
							}
							out.println("<td align=\"center\">" + type
									+ "</td><td align=left> &nbsp;" + // The file type (extension)
									date + "</td><td>" + // The date the file was created
									dlink + "</td>"); // The download link
							if (!READ_ONLY)
								out.print("<td>" + elink + "</td>"); // The edit link (or view, depending)
							out.println("</tr>");
						}
					}
					out.write("\n");
					out.write("</table>\n");
					out.write("<p align=center>\n");
					out.write("<b title=\"");
					out.print(totalSize);
					out.write(" bytes\">\n");
					out.write("");
					out.print(convertFileSize(totalSize));
					out.write("</b><b> in ");
					out.print(fileCount);
					out.write(" files in ");
					out.print(dir2linkdir(
							fromHangul((String) request.getAttribute("dir")),
							browser_name, sortMode));
					out.write("\n");
					out.write("</b>\n");
					out.write("</p>\n");
					out.write("");
					//	out.println("[" + fromHangul((String) request.getAttribute("dir")) + "]");
					out.write("\n");
					out.write("<input type=\"hidden\" name=\"dir\" value=\"");
					out.print(request.getAttribute("dir"));
					out.write("\">\n");
					out.write("<input type=\"hidden\" name=\"sort\" value=\"");
					out.print(sortMode);
					out.write("\">\n");
					out.write("<input title=\"Download selected files and directories as one zip file\" class=\"button\" id=\"but_Zip\" type=\"Submit\" name=\"Submit\" value=\"");
					out.print(SAVE_AS_ZIP);
					out.write("\">\n");
					out.write("");
					if (!READ_ONLY) {
						out.write("\n");
						out.write("<input title=\"Delete all selected files and directories incl. subdirs\" class=\"button\"  id=\"but_Del\" type=\"Submit\" name=\"Submit\" value=\"");
						out.print(DELETE_FILES);
						out.write("\"\n");
						out.write("onclick=\"return confirm('Do you really want to delete the entries?')\">\n");
						out.write("");
					}
					out.write("\n");
					out.write("");
					if (!READ_ONLY) {
						out.write("\n");
						out.write("<br />\n");
						out.write("<input title=\"Enter new dir or filename or the relative or absolute path\" class=\"textfield\" type=\"text\" onKeypress=\"event.cancelBubble=true;\" id=\"text_Dir\" name=\"cr_dir\">\n");
						out.write("<input title=\"Create a new directory with the given name\" class=\"button\" id=\"but_NDi\" type=\"Submit\" name=\"Submit\" value=\"");
						out.print(CREATE_DIR);
						out.write("\">\n");
						out.write("<input title=\"Create a new empty file with the given name\" class=\"button\" id=\"but_NFi\" type=\"Submit\" name=\"Submit\" value=\"");
						out.print(CREATE_FILE);
						out.write("\">\n");
						out.write("<input title=\"Move selected files and directories to the entered path\" id=\"but_Mov\" class=\"button\" type=\"Submit\" name=\"Submit\" value=\"");
						out.print(MOVE_FILES);
						out.write("\">\n");
						out.write("<input title=\"Copy selected files and directories to the entered path\" id=\"but_Cop\" class=\"button\" type=\"Submit\" name=\"Submit\" value=\"");
						out.print(COPY_FILES);
						out.write("\">\n");
						out.write("<input title=\"Rename selected file or directory to the entered name\" id=\"but_Ren\" class=\"button\" type=\"Submit\" name=\"Submit\" value=\"");
						out.print(RENAME_FILE);
						out.write("\">\n");
						out.write("");
					}
					out.write("\n");
					out.write("</form>\n");
					out.write("<br />\n");
					out.write("<div class=\"formular\">\n");
					out.write("");
					if (ALLOW_UPLOAD) {
						out.write("\n");
						out.write("<form class=\"formular2\" action=\"");
						out.print(browser_name);
						out.write("\" enctype=\"multipart/form-data\" method=\"POST\">\n");
						out.write("<input type=\"hidden\" name=\"dir\" value=\"");
						out.print(request.getAttribute("dir"));
						out.write("\">\n");
						out.write("<input type=\"hidden\" name=\"sort\" value=\"");
						out.print(sortMode);
						out.write("\">\n");
						out.write("<input type=\"file\" class=\"textfield\" onKeypress=\"event.cancelBubble=true;\" name=\"myFile\">\n");
						out.write("");
						out.write("\n");
						out.write("<input title=\"Upload selected file to the current working directory\" type=\"Submit\" class=\"button\" name=\"Submit\" value=\"");
						out.print(UPLOAD_FILES);
						out.write("\"\n");
						out.write("onClick=\"javascript:popUp('");
						out.print(browser_name);
						out.write("')\">\n");
						out.write("</form>\n");
						out.write("");
					}
					out.write("\n");
					if (NATIVE_COMMANDS) {
						out.write("\n");
						out.write("    <form class=\"formular2\" action=\"");
						out.print(browser_name);
						out.write("\" method=\"POST\">\n");
						out.write("<input type=\"hidden\" name=\"dir\" value=\"");
						out.print(request.getAttribute("dir"));
						out.write("\">\n");
						out.write("<input type=\"hidden\" name=\"sort\" value=\"");
						out.print(sortMode);
						out.write("\">\n");
						out.write("<input type=\"hidden\" name=\"command\" value=\"\">\n");
						out.write("<input title=\"Launch command in current directory\" type=\"Submit\" class=\"button\" id=\"but_Lau\" name=\"Submit\" value=\"");
						out.print(LAUNCH_COMMAND);
						out.write("\">\n");
						out.write("</form>");
					}
					out.write("\n");
					out.write("    </div>\n");
					out.write("    ");
				}
				out.write("\n");
				out.write("<hr>\n");
				out.write("<center>\n");
				out.write("<small>ionShell version ");
				out.print(VERSION_NR);
				out.write(" by <a href=\"");
				out.print(SERVER_NAME);
				out.write("\">");
				out.print(SERVER_NAME);
				out.write("</a></small>\n");
				out.write("</center>\n");
				out.write("</body>\n");
				out.write("</html>");
			}
			out.write('\r');
			out.write('\r');
			out.write('\n');
		} catch (Throwable t) {
			if (!(t instanceof SkipPageException)){
				out = jspOut;
				if (out != null && out.getBufferSize() != 0)
					try { out.clearBuffer(); } catch (IOException e) {}
				if (jspPageContext != null) jspPageContext.handlePageException(t);
			}
		} finally {
			jspFactory.releasePageContext(jspPageContext);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
