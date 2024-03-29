package net.ion.plugin.cstore.commUtil.webtail;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

/**
 * <pre>
 * 작 성 자 : Lee MinHo 
 * 작 성 일 : 2013. 2. 5.
 * 설    명 : I-ON Web Log Viewer & Download(Zip) Log
 * 수정이력 : 2013. 2. 5. Lee MinHo 최초작성
 * 저 작 권 : I-ON Communications Content Infra Dev Team
 * </pre>
 */
public class LogFile {

	private long lPos = 0;
	private File file;
	private long lFileSize = 0;
	
	private PropertyChangeSupport mPcs = new PropertyChangeSupport(this);
	
	public LogFile(File file) {
		this.file = file;
		this.setFileSize(this.file.length());
	}

	public long getFileSize() {
		return lFileSize;
	}

	private void setFileSize(long lFileSize) {
		mPcs.firePropertyChange("lFileSize", this.lFileSize, lFileSize);
		this.lFileSize = lFileSize;
	}
	
	public long getPos() {
		return lPos;
	}
	
	public void checkFileSize() {
		long lSize = file.length();
		if (lSize != lFileSize) {
			setFileSize(lSize);
		}
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
        mPcs.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mPcs.removePropertyChangeListener(listener);
    }
    
}
