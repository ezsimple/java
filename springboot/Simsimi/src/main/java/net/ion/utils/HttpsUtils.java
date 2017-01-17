package net.ion.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpsUtils {
	private final static Logger logger = Logger.getLogger(HttpsUtils.class);
	
	public static File getFile(String url, Map<String, Object> parameterMap, File f)throws Exception {
		return getFile(url, parameterMap, f, 10000);
	}

	public static File getFile(String url, Map<String, Object> parameterMap, File f, int timeout)throws Exception {
		FileOutputStream fos = new FileOutputStream(f);
		String data = getString(url,parameterMap,timeout);
		IOUtils.write(data, fos);
		return f;
	}

	public static String getString(String url, Map<String, Object> parameterMap) throws Exception {
		return getString(url,parameterMap,5000);
	}

	public static String getString(String url, Map<String, Object> parameterMap,final int timeout) throws Exception {
		StringBuffer out = null;
		String res = "";
		try {
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}
				public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}
				public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}
			} };

	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        HostnameVerifier allHostsValid = new HostnameVerifier() {
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
	        };
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			
			final URL _url = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection)_url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setReadTimeout(timeout);
			con.setConnectTimeout(timeout);
			con.setDoInput(true);
			con.setDoOutput(true);

			if(con!=null){

				OutputStream os = con.getOutputStream();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));	
				byte[] postDataBytes = getParamString(parameterMap).getBytes("UTF-8");
				os.write(postDataBytes);
				writer.flush();
				writer.close();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				out = new StringBuffer();

				String input;
				while ((input = br.readLine()) != null){
					out.append(input);
				}		
				br.close();
				res = out.toString();

			}
		} catch (Exception e) {
			throw e;
		}

		return res;
	}

	public static String getParamString(Map<String,Object> parameterMap) {
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		if (parameterMap != null && !parameterMap.isEmpty()) {
			for (String key : parameterMap.keySet()) {
				Object obj =  parameterMap.get(key);
				if (obj instanceof List) {
					List<String> vals = (List<String>) obj;
					for(String val : vals){
						builder.queryParam(key, val);
					}
				}else{
					builder.queryParam(key, obj.toString());
				}
			}
		}

		return StringUtils.replaceOnce(builder.build().encode().toString(),"?","");
	}

	public static void main(String[] arg) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();		
		param.put("gridx", 60);
		param.put("gridy", 127);
		String url = "https://www.kma.go.kr/wid/queryDFS.jsp";
		String res = getString(url,param);
		logger.debug(res);
	}

}
