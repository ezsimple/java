package net.ion.plugin.cstore.commUtil.webtail;

import java.io.File;
import java.io.FilenameFilter;

public class ionLogFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		return name.contains(".out");
	}

}
