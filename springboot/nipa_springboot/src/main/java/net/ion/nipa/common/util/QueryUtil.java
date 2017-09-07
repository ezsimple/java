package net.ion.nipa.common.util;

import java.util.List;
import java.util.Map;

import net.ion.util.ListUtil;
import net.ion.util.ResultUtil;

public class QueryUtil {
	public static Map<String, Object> getResult(List<Map<String, Object>> list) throws Exception {
		Map<String, Object> resultMap = ResultUtil.newResult();
		ResultUtil.setSuccess(resultMap, false);
		if (list != null) {
			resultMap.put("list", list);
			resultMap.put("total", list.size());
			ResultUtil.setSuccess(resultMap, true);
		}
		return resultMap;
	}

	// (중요) result parameter의 값은 getResult의 결과값이다.
	public static List<Object> getList(Map<String, Object> result) {
		if (result == null)
			return ListUtil.EMPTY;
		return (List) result.get("list");
	}

	// (중요) result parameter의 값은 getResult의 결과값이다.
	public static List<Object> find(Map<String, Object> result, String fieldName) {
		if (result == null)
			return ListUtil.EMPTY;
		List list = ListUtil.newList();
		for (Object obj : (List) result.get("list")) {
			if (obj == null)
				continue;
			list.add(((Map) obj).get(fieldName));
		}
		return list;
	}

}
