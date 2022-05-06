package Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import Models.FinalOrder;
import Models.InventoryStock;

public class InMemoryDB {

	public static InMemoryDB staticdatabase = new InMemoryDB();

	private HashMap<String,InventoryStock> inventoryStockList = new HashMap<>();

	private HashSet<String> creditCardList = new HashSet<>();

	private ArrayList<FinalOrder> finalOderList = new ArrayList<>();

	public HashMap<String, InventoryStock> getInventoryStockList() {
		return inventoryStockList;
	}

	public void setInventoryStockList(HashMap<String, InventoryStock> inventoryStockList) {
		this.inventoryStockList = inventoryStockList;
	}

	public HashSet<String> getCreditCardList() {
		return creditCardList;
	}

	public void setCreditCardList(HashSet<String> creditCardList) {
		this.creditCardList = creditCardList;
	}

	public ArrayList<FinalOrder> getFinalOderList() {
		return finalOderList;
	}

	public void setFinalOderList(ArrayList<FinalOrder> finalOderList) {
		this.finalOderList = finalOderList;
	}





}
