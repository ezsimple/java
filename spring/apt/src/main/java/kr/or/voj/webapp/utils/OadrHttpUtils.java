package kr.or.voj.webapp.utils;

import kr.or.voj.quartz.job.CBLRcvJob;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.http.HttpMethod;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//import net.ion.oadr2.store.Env;

public class OadrHttpUtils {
	private static final Logger logger = Logger.getLogger(CBLRcvJob.class);

	public static class HTTP {
		private static SSLContext sslcontext = null;
		private static Scheme sch = null;

		private static DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();

		private static TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
			public boolean isTusted(X509Certificate[] certificates, String authType) {
				for (X509Certificate x509Certificate : certificates) {

				}
				return false;
			}

			public boolean isTrusted(X509Certificate[] arg0, String arg1)
					throws CertificateException {
				// TODO Auto-generated method stub
				return false;
			}
		};

		public static HttpResponse Request(String uri, HttpMethod method, String body, Header[] headers, ResponseHandler responseHandler) throws Exception {
			if(method.compareTo(HttpMethod.POST) == 0) {
				return Post(uri, body, headers, responseHandler);
			}else if(method.compareTo(HttpMethod.PUT) == 0){
				return Put(uri, body, headers, responseHandler);
			}else if(method.compareTo(HttpMethod.POST) == 0){
				return Post(uri, body, headers, responseHandler);
			}else {
				return Get(uri, body, headers, responseHandler);
			}
		}
		
		public static HttpResponse Post(String uri, String body, ResponseHandler responseHandler) throws Exception {
			//return Post(uri, body, new Header[]{new BasicHeader("Host", Env.getProperty("http.header.host"))}, responseHandler);
			return Post(uri, body, new Header[]{new BasicHeader("Host", null)}, responseHandler);
		}
		
		public static HttpResponse Post(String uri, String body, Header[] headers, ResponseHandler responseHandler) throws Exception {
			HttpPost post = new HttpPost(uri);
			post.setEntity(new StringEntity(body, ContentType.create("application/xml", "UTF-8")));
			return send(post, body, headers, responseHandler);
		}
		
		public static HttpResponse Put(String uri, String body, Header[] headers, ResponseHandler responseHandler) throws Exception {
			HttpPut put = new HttpPut(uri);
			put.setEntity(new StringEntity(body, ContentType.create("application/xml", "UTF-8")));
			return send(put, body, headers, responseHandler);
		}
		
		public static HttpResponse Delete(String uri, String body, Header[] headers, ResponseHandler responseHandler) throws Exception {
			HttpDelete delete = new HttpDelete(uri);
			return send(delete, body, headers, responseHandler);
		}
		
		public static HttpResponse Get(String uri, String body, Header[] headers, ResponseHandler responseHandler) throws Exception {
			HttpGet get = new HttpGet(uri);
			return send(get, body, headers, responseHandler);
		}

		private static HttpResponse send(HttpRequestBase request, String body, Header[] headers, ResponseHandler responseHandler) throws Exception {
			
			URI _uri = request.getURI();
			
			URL u = new URL(_uri.toString());	// this would check for the protocol
			u.toURI();	// does the extra checking required for validation of URI 
			
			boolean ssl = false;
			int port = 80;
			if (_uri.getScheme().equalsIgnoreCase("https")) {
				ssl = true;
			}
			if (_uri.getPort() == -1) {
				if (ssl) {
					port = 443;
				} else {
					port = 80;
				}
			} else {
				port = _uri.getPort();
			}
/*
			String _tls_version = Env.getProperty("tls.version");
			String _ciphers = Env.getProperty("tls.ciphers");
			String keystore = Env.getProperty("http.keystore");
			String keystore_password = Env.getProperty("http.keystore.password");

			String trustkeystore = Env.getProperty("http.trustkeystore");
			String trustkeystore_password = Env.getProperty("http.trustkeystore.password");
			String keystore_type = Env.getProperty("http.keystore.type");
			boolean client_auth = Boolean.parseBoolean(Env.getProperty("http.needClientAuth"));

			Scheme scheme = null;
			SSLSocketFactory sf = null;
			if (ssl) {
				if (client_auth) {
					KeyStore trustkeyStore = KeyStore.getInstance(keystore_type);
					trustkeyStore.load(defaultResourceLoader.getResource(trustkeystore).getInputStream(), trustkeystore_password.toCharArray());

					KeyStore keyStore = KeyStore.getInstance(keystore_type);
					keyStore.load(defaultResourceLoader.getResource(keystore).getInputStream(), keystore_password.toCharArray());
					sf = new SSLSocketFactory(SSLSocketFactory.TLS, keyStore, keystore_password, trustkeyStore, null, acceptingTrustStrategy, new AllTrustX509HostnameVerifier(_tls_version, _ciphers));
				} else {
					sf = new SSLSocketFactory(acceptingTrustStrategy, new AllTrustX509HostnameVerifier(_tls_version, _ciphers));
				}
				scheme = new Scheme("https", port, sf);
			}
*/
			HttpClient client = new DefaultHttpClient();
			HttpParams params = client.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 15000);

			for (Header header : headers) {
				request.addHeader(header);
			}

			HttpResponse response = null;
			try {
//				if (scheme != null) {
//					client.getConnectionManager().getSchemeRegistry().register(scheme);
//				}
				response = client.execute(request);
				if (responseHandler != null) {
					responseHandler.handleResponse(response);
				}

			} catch (Exception e) {
				throw e;
			} finally {
				request.releaseConnection();
			}

			return response;
		}
	}

	private static class AllTrustX509HostnameVerifier implements X509HostnameVerifier {
		private String _tls_version;
		private String[] _ciphers;

		private AllTrustX509HostnameVerifier(String _tls_version, String _ciphers) {
			this._tls_version = _tls_version;
			this._ciphers = StringUtils.split(_ciphers, ",");
		}

		public void verify(String string, SSLSocket ssls) throws IOException {

			List<String> protocols = new ArrayList<String>();
			CollectionUtils.addAll(protocols, ssls.getEnabledProtocols());
			if (!protocols.contains(_tls_version)) {
				protocols.add(_tls_version);
			}
			ssls.setEnabledProtocols(protocols.toArray(new String[0]));

			List<String> ciphers = new ArrayList<String>();
			CollectionUtils.addAll(ciphers, ssls.getEnabledCipherSuites());
			for (String cipher : _ciphers) {
				if (!ciphers.contains(cipher)) {
					ciphers.add(cipher);
				}
			}

			ssls.setEnabledCipherSuites(ciphers.toArray(new String[0]));

		}

		public void verify(String string, X509Certificate xc) throws SSLException {

		}

		public void verify(String string, String[] strings, String[] strings1) throws SSLException {
		}

		public boolean verify(String string, SSLSession ssls) {
			return true;
		}
	};

	public static Object pull(String vtnTransportAddress, String reqXml, File file) throws Exception{
		
		
		final String xml = XmlUtil.format(reqXml);
		final File resFile = file;
		
		return HTTP.Post(vtnTransportAddress, xml, new ResponseHandler<Object>() {
			public Object handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {

				int status = httpResponse.getStatusLine().getStatusCode();
				if(status!=200){
					throw new IOException("http error code : " + status);
				}
				
				String xml = XmlUtil.format(IOUtils.toString(httpResponse.getEntity().getContent()));
				FileUtils.writeStringToFile(resFile, xml, "UTF-8");
				return null;
			}
		});

	}

	public static Object pull(String vtnTransportAddress, String reqXml) throws Exception{
		final String xml = XmlUtil.format(reqXml);
		return HTTP.Post(vtnTransportAddress, xml, new ResponseHandler<Object>() {
			public Object handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
				int status = httpResponse.getStatusLine().getStatusCode();
				if(status!=200){
					throw new IOException("http error code : " + status);
				}
				return XmlUtil.format(IOUtils.toString(httpResponse.getEntity().getContent()));
			}
		});
	}


	public static void main(String[] args)throws Throwable {
		String vtnTransportAddress = "http://125.140.110.206:8888/bspla/histories";
		//String xml = "<energyParamType venid=\"1223081447\" meterid=\"3048501223081447***\" dataType=\"smartmeter\" dateStr=\"20150115\"/>";
		String xml = "<energyParamType venid=\"1223081447\" meterid=\"3048501223081447***\" dataType=\"ismart\" dateStr=\"20150115\"/>";

		File file = new File("d:/temp/xxx.xml");
		OadrHttpUtils.pull(vtnTransportAddress, xml, file);
	
		vtnTransportAddress = "http://125.140.110.206:8888/bspla/cbl";
		xml = "<cblParamType venid=\"1223081447\" meterid=\"3048501223081447***\" dataType=\"ismart\" dateStr=\"20150115\"/>";

		file = new File("d:/temp/xxx_cbl.xml");
		OadrHttpUtils.pull(vtnTransportAddress, xml, file);

	}
	public static void main1(String[] args)throws Throwable {
		String vtnTransportAddress = "http://125.140.110.206:8888/bspla/OpenADR2/Simple/2.0b/OadrPoll";
		String xml = FileUtils.readFileToString(new File("d:/temp/template_poll.xml"));
		vtnTransportAddress = "http://125.140.110.206:8888/bspla/OpenADR2/Simple/2.0b/EiEvent";
		String reqXml = FileUtils.readFileToString(new File("d:/temp/template_request_event.xml"));
		SimpleDateFormat folderFormat = new SimpleDateFormat("yyyy/MM");
		SimpleDateFormat fileFormat = new SimpleDateFormat("dd_HHmmss");
		Date date = new Date();

		reqXml = StringUtils.replaceOnce(reqXml, "${requestID}", "req_" + UUID.randomUUID());
		reqXml = StringUtils.replaceOnce(reqXml,"${venID}", "1223081447");
		
		String folderName = "/oadr/" + folderFormat.format(date);
		String fileName = fileFormat.format(date) + "_" + UUID.randomUUID() + ".xml";
		File ffile = new File(folderName);
		if(!ffile.exists()){
			ffile.mkdirs();
		}
		File file = new File(folderName, fileName);
		Object xml1 = (String)OadrHttpUtils.pull(vtnTransportAddress, reqXml, file);
		xml = XmlUtil.format(xml);
		FileUtils.writeStringToFile(file, xml);
	}
	
}
