package Handlers;

import java.util.ArrayList;
import Database.InMemoryDB;
import Utils.HelperFile;

public class CardHandler {

	
	private InMemoryDB db = InMemoryDB.staticdatabase;
	
	private HelperFile helperFile;

	public CardHandler(String filePath) {
		
		this.helperFile = new HelperFile(filePath);
	
	}
	
	public void addNewLine(String cardNum) {
			
		try {
			helperFile.addNewLine(cardNum);
		} catch (Exception e) {
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
