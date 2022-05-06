package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderSuccessFile extends AbstractOutputFile {

	public OrderSuccessFile(ArrayList<String> content) {
		super(content);
	}

	@Override
	void saveToPath(String targetDirectory) throws IOException {
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = "OrderOutput_"+ df.format(new Date()) + ".csv";
		String outputFilePath = targetDirectory + "/" + fileName ;

		FileWriter errorFile = new FileWriter(outputFilePath);
		for(String line:this.content) {
			errorFile.write(line+"\n");			
		}
		errorFile.close();
		System.out.println("Order details file stored at "+ outputFilePath);		
	}


}
