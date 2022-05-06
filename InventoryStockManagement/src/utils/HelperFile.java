package utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HelperFile {

	Path path;
	private ArrayList<String> filecontent = new ArrayList<>();

	public HelperFile(String filePathInfo) {
		this.path = Paths.get(filePathInfo);
	}

	public void readFile(boolean fileinfo) throws Exception{

		if(Files.exists(path)){
			BufferedReader bufferReader = new BufferedReader((new FileReader(path.toFile())));
			String newline = "";

			while((newline = bufferReader.readLine())!= null){
				if(fileinfo){
					fileinfo = false;
					continue;
				}
				filecontent.add(newline);
			}
		}else{
			throw new Exception();
		}
	}

	public ArrayList<String> getfileContent() {
		return filecontent;
	}

	public void addNewLine(String newLine) throws Exception {

		if(Files.exists(path)){

			FileWriter fstream = new FileWriter(path.toFile(), true);
			BufferedWriter bufferedWriter = new BufferedWriter(fstream);

			bufferedWriter.newLine();
			bufferedWriter.write(newLine);
			bufferedWriter.close();
		}else{
			throw new Exception();
		}
	}


	public void generateOutput(ArrayList<String> outputContent, boolean isInvalid) throws IOException{
		AbstractOutputFile outputFile;
		String outputDirectory = this.path.getParent().toString();
		if(isInvalid){
			outputFile = new OutputErrorFile(outputContent);
			outputFile.saveToPath(outputDirectory);

		}else{
			outputFile = new OrderSuccessFile(outputContent);			
			outputFile.saveToPath(outputDirectory);
		}
	}


}