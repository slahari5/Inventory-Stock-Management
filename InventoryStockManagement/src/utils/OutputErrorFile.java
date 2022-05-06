package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OutputErrorFile extends AbstractOutputFile
	{

	public OutputErrorFile(ArrayList<String> content) {
		super(content);
	}

	@Override
	void saveToPath(String targetDirectory) throws IOException {
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = "Error_"+ df.format(new Date()) + ".txt";
		String outputFilePath = targetDirectory + "/" + fileName ;

		FileWriter errorFile = new FileWriter(outputFilePath);
		for(String line:this.content) {
			errorFile.write(line+"\n");			
		}
		errorFile.close();
		System.out.println("Output Error File stored at "+ outputFilePath);
	}

}
