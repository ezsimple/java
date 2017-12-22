package kr.or.voj.webapp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import kr.or.voj.webapp.processor.AttachProcessor;
import kr.or.voj.webapp.processor.ProcessorServiceFactory;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.io.FileUtils;

public class RepositoryUtils {
	public static String save(String groupId, String kind, File file, String ext)throws Exception {
		FileInputStream fis = null;
		String attachId = UUID.randomUUID().toString();
		String fileName = attachId + "." + ext;
		String fileId = "";
		
		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String,Object>>>();
		
		try {
			fis = new FileInputStream(file);
			AttachProcessor.saveFile(result, attachId, groupId==null ? attachId : groupId, kind, fis, fileName, file.length());
			CaseInsensitiveMap params = new CaseInsensitiveMap();
			List<Map<String,Object>> attchFileList = new ArrayList<Map<String,Object>>();
			
			for(String key : result.keySet()){
				attchFileList.addAll(result.get(key));
			}
			
			params.put("_atach_all", attchFileList);
			params.put("_atach", result);
			
			if(!params.containsKey("del_file_id_")){
				String[] delId = {"0"};
				params.put("del_file_id_", delId);
			}
			fileId = (String)((Map)((List)params.get("_atach_all")).get(0)).get("id");
			ProcessorServiceFactory.executeQuery("attach", "insert", params);
		} finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (Exception e) {;}
			}
		}
		return fileId;
		
	}
	public static String save(String kind, File file)throws Exception {
		return save(null, kind, file);
	}
	public static String save(String groupId, String kind, File file)throws Exception {
		return save(groupId, kind, file, "xml");
	}
	public static String save(String kind, File file, String ext)throws Exception {
		return save(null, kind, file, ext);
	}
}
