package Utils;

import java.io.IOException;
import java.util.ArrayList;


public abstract class AbstractOutputFile {

	ArrayList<String> content;

	public AbstractOutputFile(ArrayList<String> content) {
		this.content = content;
	}

	abstract void saveToPath(String targetDirectory) throws IOException;

}
