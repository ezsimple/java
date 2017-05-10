package net.ion.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {

	public static String getString(JSONObject params, String key) {
        if(params == null || params.isEmpty())
        	return null;
      	JSONArray arr = params.getJSONArray(key);
      	return arr.getString(0);
	}

}
