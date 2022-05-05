package Handlers;

import java.util.ArrayList;
import Database.Data_Database;
import Helper.HelperFile;

public class CardHandler {

	
	private Data_Database db = Data_Database.staticdatabase;
	
	private HelperFile helperFile;

	public CardHandler(String filePath) {
		
		this.helperFile = new HelperFile(filePath);
	
	}
	
	public void addNewLine(String cardNum) {
			
		try {
			System.out.println("addNewLine ...? "+cardNum);
			helperFile.addNewLine(cardNum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void CardListInit() {
		
		 try{
			 helperFile.readFile(true);
	        }catch (Exception e){
	            System.out.println("Please enter valid creditcard file path. ");
	            System.exit(0);
	        }
		 
		 ArrayList<String> fileContent = helperFile.getfileContent();
		 for(int i=0;i<fileContent.size();i++){
	            db.getCreditCardList().add(fileContent.get(i));
	        }
		
	}
	
	
	
	

	
	
	
}
