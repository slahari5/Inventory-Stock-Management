package Handlers;

import java.util.ArrayList;

import Database.InMemoryDB;
import Models.InventoryStock;
import Utils.HelperFile;

public class DatasetHandler {

	private InMemoryDB db = InMemoryDB.staticdatabase;

	private HelperFile helperfile;

	public DatasetHandler(String filePath) {

		this.helperfile = new HelperFile(filePath);
	}

	public void DatasetInit() {

		try{

			helperfile.readFile(true);
		}catch (Exception e){
			System.out.println("Please enter valid Dataset file path. Path not found. ");
			System.exit(0);
		}

		ArrayList<String> fileContent = helperfile.getfileContent();
		for(int i=0;i<fileContent.size();i++){
			String[] splitItem = fileContent.get(i).split(",");
			db.getInventoryStockList().put(splitItem[0],
					new InventoryStock(splitItem[1],splitItem[0],Integer.parseInt(splitItem[2]),Double.parseDouble(splitItem[3])));
		}

	}

}
