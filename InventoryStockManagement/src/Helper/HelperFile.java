package Helper;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	    
	    
	    public void generateOutput(ArrayList<String> msg, boolean isFault) throws IOException{
	     
	    	 FileWriter fileOutput;
	    	
	    	ArrayList<String> content = msg;
	    	
	    	
	    	if(isFault){
	        
	    		fileOutput = new FileWriter(path.getParent().toString()+"/ErrorLog_"+new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".txt");
	    		System.out.println(path.getParent().toString());
	    	
	    	}else{
	       	
	    		fileOutput = new FileWriter(path.getParent().toString()+"/OrderSuccessfulFile_"+new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".csv");
	        	  
	        	
	        }
	    	for(String line:content)
	    		
	    		fileOutput.write(line+"\n");
	    		fileOutput.close();  
	    }


}