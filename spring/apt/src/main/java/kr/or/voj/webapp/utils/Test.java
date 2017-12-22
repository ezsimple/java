package kr.or.voj.webapp.utils;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

public class Test {
	public static void main(String[] args) {
		SSLContext sslContext;
		try {
			sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(null, null, null);
			String[] protocols = sslContext.getSupportedSSLParameters().getProtocols();
			for (String protocol : protocols) {
				System.out.println("Context supported protocol: " + protocol);
			}

			SSLEngine engine = sslContext.createSSLEngine();
			String[] supportedProtocols = engine.getSupportedProtocols();
			for (String protocol : supportedProtocols) {
				System.out.println("Engine supported protocol: " + protocol);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
