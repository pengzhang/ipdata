package com.fanaifan;

import java.io.*;
import java.util.regex.*;

public class Utils {
	private static Pattern pointPattern = Pattern.compile("[.]");

	/**
	 * 把类成员ip和beginIp比较，注意这个beginIp是big-endian的
	 * 
	 * @param ip 要查询的IP
	 * @param beginIp 和被查询IP相比较的IP
	 * @return 相等返回0，ip大于beginIp则返回1，小于返回-1。
	 */
	public static int compareIP(byte[] ip, byte[] beginIp)
	{
		for (int i = 0; i < 4; i++)
		{
			int r = Utils.compareByte(ip[i], beginIp[i]);
			if (r != 0)
			{
				return r;
			}
		}
		return 0;
	}

	// public static int compareIP(byte[] ip, byte[] beginIp)
	// {
	// for (int i = 0; i < 4; i++)
	// {
	// int r = Utils.compareByte(ip[i], beginIp[i]);
	// if (r != 0)
	// {
	// return r;
	// }
	// }
	// return 0;
	// }
	/**
	 * 把两个byte当作无符号数进行比较
	 * 
	 * @param b1
	 * @param b2
	 * @return 若b1大于b2则返回1，相等返回0，小于返回-1
	 */
	public static int compareByte(byte b1, byte b2)
	{
		if ((b1 & 0xFF) > (b2 & 0xFF))
		{
			return 1;
		}
		else if ((b1 ^ b2) == 0)
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}

	/**
	 * @param ipBytes
	 * @return
	 */
	public static long getLong(byte[] ipBytes)
	{
		long value = 0;
		value |= ((ipBytes[3] & 0xFF));
		value |= ((ipBytes[2] << 8) & 0xFF00);
		value |= ((ipBytes[1] << 16) & 0xFF0000);
		value |= ((ipBytes[0] << 24) & 0xFF000000);
		return value;
	}

	/**
	 * 从ip的字符串形式得到字节数组形式
	 * 
	 * @param ip 字符串形式的ip
	 * @return 字节数组形式的ip
	 */
	public static byte[] getIpByteArrayFromString(String ip)
	{
		byte[] ret = new byte[4];
		String[] ss = Utils.pointPattern.split(ip);
		try
		{
			for (int i = 0; i < ret.length; i++)
			{
				ret[i] = (byte) (Integer.parseInt(ss[i]) & 0xFF);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return ret;
	}

	public static void main(String args[])
	{
		long value = Utils.getLong(Utils
			.getIpByteArrayFromString("180.200.68.221"));
		value = Utils.getLong(Utils.getIpByteArrayFromString("71.111.128.221"));
		value = Utils.getLong(Utils.getIpByteArrayFromString("75.0.3.221"));
		value = Utils.getLong(Utils.getIpByteArrayFromString("91.91.153.221"));
		value = Utils.getLong(Utils.getIpByteArrayFromString("41.181.202.221"));
		value = Utils.getLong(Utils.getIpByteArrayFromString("162.174.92.221"));
		value = Utils.getLong(Utils.getIpByteArrayFromString("140.0.135.221"));
		 byte[] value1 = Utils.getIpByteArrayFromString("86.67.210.180");
		 byte[] value2 = Utils.getIpByteArrayFromString("112.63.162.152");
		 byte[] value3 = Utils.getIpByteArrayFromString("115.255.255.255");
		 int i = Utils.compareIP(value1, value2);
		 int i2 = Utils.compareIP(value1, value3);
		 int i3 = Utils.compareIP(value2, value3);
		 i = Utils.compareIP(value2, value1);
		 i2 = Utils.compareIP(value3, value1);
		 i3 = Utils.compareIP(value3, value2);
		 i3 = Utils.compareIP(value1, value1);
		 System.out.println(value1);
		 System.out.println(value2);
		 System.out.println(value3);
	}

	/**
	 * 对原始字符串进行编码转换，如果失败，返回原始的字符串
	 * 
	 * @param s 原始字符串
	 * @param srcEncoding 源编码方式
	 * @param destEncoding 目标编码方式
	 * @return 转换编码后的字符串，失败返回原始字符串
	 */
	public static String getString(String s, String srcEncoding,
		String destEncoding)
	{
		try
		{
			return new String(s.getBytes(srcEncoding), destEncoding);
		}
		catch (UnsupportedEncodingException e)
		{
			return s;
		}
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * 
	 * @param b 字节数组
	 * @param encoding 编码方式
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String getString(byte[] b, String encoding)
	{
		try
		{
			return new String(b, encoding);
		}
		catch (UnsupportedEncodingException e)
		{
			return new String(b);
		}
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * 
	 * @param b 字节数组
	 * @param offset 要转换的起始位置
	 * @param len 要转换的长度
	 * @param encoding 编码方式
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String getString(byte[] b, int offset, int len,
		String encoding)
	{
		try
		{
			return new String(b, offset, len, encoding);
		}
		catch (UnsupportedEncodingException e)
		{
			return new String(b, offset, len);
		}
	}

	//
	// public static long GetLong(byte[] ip)
	// {
	// long value = 0;
	// value |= ip[0] & 0x00FF;
	// value |= (ip[1] << 8) & 0x00FF00;
	// value |= (ip[2] << 16) & 0x00FF0000;
	// value |= (ip[3] << 24) & 0x00FF000000;
	// return value;
	// }
	/**
	 * @param ip ip的字节数组形式
	 * @return 字符串形式的ip
	 */
	public static String getIpStringFromBytes(byte[] ip)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(ip[0] & 0xFF);
		sb.append('.');
		sb.append(ip[1] & 0xFF);
		sb.append('.');
		sb.append(ip[2] & 0xFF);
		sb.append('.');
		sb.append(ip[3] & 0xFF);
		return sb.toString();
	}
}
