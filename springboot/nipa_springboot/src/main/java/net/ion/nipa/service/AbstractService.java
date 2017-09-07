package net.ion.nipa.service;

import java.util.List;
import java.util.Map;

import net.ion.nipa.common.util.QueryUtil;

public abstract class AbstractService implements NipaService {
	protected Map<String, Object> getResult(List<Map<String, Object>> list) throws Exception {
		return QueryUtil.getResult(list);
	}
}
