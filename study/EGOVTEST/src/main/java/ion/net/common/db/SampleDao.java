package ion.net.common.db;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleDao extends DynamicDao {
	
	SqlObject<List<Sample>> retrieveBy;

	public String getPackageName() {
		return "sample_pkg";
	}
	
	public List<Sample> retrieveBy(Object ... params) {
		log.debug("call : "+retrieveBy);
		List<Sample> list = null;
		if(retrieveBy!=null)
			list = retrieveBy.execute(params);
		return list;
	}
	
}
