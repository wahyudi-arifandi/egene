package edu.ntu.eee.csn.ism.egene.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;

import edu.ntu.eee.csn.ism.egene.exception.CodecException;

public class Codec {

	public static String decodeUrl(String safeUrl) {
		if (null == safeUrl)
			return null;

		String res = null;
		try {
			URLCodec codec = new URLCodec();
			res = codec.decode(safeUrl);
		} catch (DecoderException e) {
			throw new CodecException(e.getMessage(), e);
		}

		return res;
	}
	
	public static String intArrayToString(int[] arr) {
		if (null == arr)
			return null;
		
		StringBuffer sb = new StringBuffer("[");
		for (int i = 0; i < arr.length; i ++) {
			sb.append(arr[i]).append(";");
		}
		sb.append("]");
		return sb.toString();
	}

}
