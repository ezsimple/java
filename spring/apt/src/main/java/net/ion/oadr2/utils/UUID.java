/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package net.ion.oadr2.utils;

import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

public class UUID {
	final int _time;
	final int _machine;
	final int _inc;
	private static AtomicInteger _nextInc = new AtomicInteger( new java.util.Random().nextInt(Integer.MAX_VALUE));

	private static final int _genmachine;

	private UUID() {

		_time = (int) (System.currentTimeMillis() / 1000);
		_machine = _genmachine;
		_inc = _nextInc.getAndIncrement();
	}

	private byte[] toByteArray() {
		byte b[] = new byte[12];
		ByteBuffer bb = ByteBuffer.wrap(b);
		bb.putInt(_time);
		bb.putInt(_machine);
		bb.putInt(_inc);
		return b;
	}

	private String toHexString() {
		byte b[] = toByteArray();

		StringBuilder buf = new StringBuilder(24);

		for (int i = 0; i < b.length; i++) {
			int x = b[i] & 0xFF;
			String s = Integer.toHexString(x);
			if (s.length() == 1) {
				buf.append("0");
			}
			buf.append(s);
		}

		return buf.toString();
	}

	@Override
	public String toString() {
		return toHexString();
	}

	public static int machine(){
		return _genmachine;
	}

	public static int next(){
		return _nextInc.getAndIncrement();
	}

	public static String id(){
		return new UUID().toString();
	}

	static {

		try {
			final int machinePiece;
			{
				StringBuilder sb = new StringBuilder();
				Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
				while (e.hasMoreElements()) {
					NetworkInterface ni = e.nextElement();
					sb.append(ni.toString());
				}
				machinePiece = sb.toString().hashCode() << 16;
			}

			final int processPiece;
			{
				int processId = new java.util.Random().nextInt();
				try {
					processId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().hashCode();
				} catch (Throwable t) {
				}

				ClassLoader loader = UUID.class.getClassLoader();
				int loaderId = loader != null ? System.identityHashCode(loader) : 0;

				StringBuilder sb = new StringBuilder();
				sb.append(Integer.toHexString(processId));
				sb.append(Integer.toHexString(loaderId));
				processPiece = sb.toString().hashCode() & 0xFFFF;
			}

			_genmachine = machinePiece | processPiece;
		} catch (java.io.IOException ioe) {
			throw new RuntimeException(ioe);
		}

	}

	public static void main(String[] args) {

//		System.out.println(byteArray.length);
	}


}
