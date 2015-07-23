package by.epam.nba.transport.newmain.creator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCreator {
	
	private String path;
	private String content;
	
	public FileCreator(String path, String content) {
		this.path = path;
		this.content = content;
	}
	
	public void createFile() throws IOException {
		File file = new File(path);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		byte[] buf = new byte[1635413*1024];
		fileOutputStream.write(buf);
		fileOutputStream.flush();
		fileOutputStream.close();
	}

}
