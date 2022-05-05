package Handlers;

import java.util.ArrayList;

import Database.Data_Database;
import Helper.HelperFile;
import Models.InventoryStock;

public class DatasetHandler {

	private Data_Database db = Data_Database.staticdatabase;
	
	private HelperFile helperfile;

	public DatasetHandler(String filePath) {
		
		this.helperfile = new HelperFile(filePath);
		System.out.println("filePath----> "+filePath);
	
	}
	
	public void DatasetInit() {
		
		 try{
			 
			 helperfile.readFile(true);
			 System.out.println("Hellooo ---->");
	        }catch (Exception e){
	            System.out.println("Please enter valid file path. Path not found. ");
	            System.exit(0);
	        }
	
		 ArrayList<String> fileContent = helperfile.getfileContent();
		 for(int i=0;i<fileContent.size();i++){
	            String[] splitItem = fileContent.get(i).split(",");
	            db.getInventoryStockList().put(splitItem[0], new InventoryStock(splitItem[1],splitItem[0],Integer.parseInt(splitItem[2]),Double.parseDouble(splitItem[3])));
	        }
		
	}
	
	
}
