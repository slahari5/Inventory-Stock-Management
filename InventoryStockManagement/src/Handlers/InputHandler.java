package Handlers;


import Database.Data_Database;
import Helper.HelperFile;
import Models.InventoryStock;
import Models.FinalOrder;
import Models.CartOrder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class InputHandler {

	private Data_Database db = Data_Database.staticdatabase;
	private FinalOrder finalOrder = new FinalOrder();
	private HelperFile helperFile;
	private ArrayList<String> data_output = new ArrayList<>();
	private ArrayList<CartOrder> cartOrder = new ArrayList<>();
	private HashSet<String> cardDetails = db.getCreditCardList();
	private double totalAmount = 0;
	public String isCreditCardExist = "";
	
	public InputHandler() {}

	public InputHandler(String path){
		helperFile = new HelperFile(path);
	}

	public void outputAppend(String sample){
		data_output.add(sample);
	}

	public boolean initOrder() {
		try{
			helperFile.readFile(true);
		
		}catch (Exception e){
			return false;
		}
		obtainOrderItems(helperFile.getfileContent());
		return true;
	}
	
	public boolean validateOrder() {
		validateStockItem();
		return data_output.size() == 0;
	}

	public void countTotalAmount() {
		for(CartOrder cartOrderitem: cartOrder){
			totalAmount = totalAmount + cartOrderitem.getQuantity()
			* db.getInventoryStockList().get(cartOrderitem.getItemName()).getPrice();
		}
		finalOrder.setTotalAmount(totalAmount);
	}

	public double obtainTotalAmount() {
		return finalOrder.getTotalAmount();
	}

	public void publishOrder() {
		for(CartOrder cartOrderitem: cartOrder){
			InventoryStock inventoryStock = db.getInventoryStockList().get(cartOrderitem.getItemName());
			inventoryStock.setQuantity(inventoryStock.getQuantity()-cartOrderitem.getQuantity());
		}
		for(String card:cardDetails){
			if(!db.getCreditCardList().contains(card)){
				db.getCreditCardList().add(card);
			}
		}
		produceOutput();
	}

	public void DisplayMessage() {
		for(String data: data_output){
			System.out.println(data);
		}
	}

	public void obtainOrderItems(ArrayList<String> content) {

		for(String data: content){
			String[] orderItem = data.split(",");

			if(orderItem.length > 2) {
				System.out.println("dddd-> "+orderItem.toString());
				if(db.getInventoryStockList().containsKey(orderItem[0])){
					cartOrder.add(new CartOrder(orderItem[0],
							Integer.parseInt(orderItem[1]),orderItem[2].
							replaceAll("\\s+","")));

					if(!validateCardExist(orderItem[2])) {

						isCreditCardExist = orderItem[2];

					}
				}
				else{
					data_output.add(orderItem[0]+" named item is not found");
				}
			}
			else
			{
				if(db.getInventoryStockList().containsKey(orderItem[0])){
					cartOrder.add(new CartOrder(orderItem[0],
							Integer.parseInt(orderItem[1]), " "));
				}else{
					data_output.add(orderItem[0]+" named item is not found");
				}
			}
		}
		if(!data_output.isEmpty()){
			cartOrder.clear();
		}

	}

	public boolean validateCardExist(String creditCardNum) {
		   HashSet<String> creditCardList = db.getCreditCardList();
		   return creditCardList.contains(creditCardNum);
	}
	
	
	public boolean validateStockItem() {
		StringBuilder stringBuilder = new StringBuilder();
		db.getFinalOderList().add(finalOrder);

		if(!stockAvailabilityValidate()){
			data_output.add("Stock don't have one of the items requested.");
		}else if(!stockQuantityValidate()){
			data_output.add("Kindly provide the accurate quantity details for one of the items requested.");
		}else if(!stockCategoryCapacityValidate()){
			data_output.add("Restriction on the cap for categories occured. Current order is exceeding the limit. ");
		}

		for(CartOrder cartOrderItems: cartOrder){
			InventoryStock inventoryStockItem = db.getInventoryStockList().
					get(cartOrderItems.getItemName());
			if(inventoryStockItem.getQuantity()<cartOrderItems.getQuantity()){
				if(stringBuilder.length() > 0)
					stringBuilder.append(",");
				stringBuilder.append(cartOrderItems.getItemName()+"("+inventoryStockItem.getQuantity()+")");
			}else{
				if(!cardDetails.contains(cartOrderItems.getCreditCardNum()))
					cardDetails.add(cartOrderItems.getCreditCardNum());
			}
		}

		if(stringBuilder.length() > 0){
			data_output.add("Kindly provide accurate quantities");
			data_output.add(stringBuilder.toString());
		}
		return stringBuilder.length()==0;
	}

	private boolean stockAvailabilityValidate() {

		for(CartOrder CartOrderitem: cartOrder){
			if(!db.getInventoryStockList().containsKey(CartOrderitem.getItemName())){
				return false;
			}
		}
		return true;
	}

	private boolean stockQuantityValidate() {
		for(CartOrder cartOrderitem: cartOrder){
			if(db.getInventoryStockList().get(cartOrderitem.getItemName()).getQuantity()
					<cartOrderitem.getQuantity()){
				return false;
			}
		}

		return true;
	}
	
	//This method is used for checking the output file depending on the inputs given.
	
	private boolean stockCategoryCapacityValidate() {


		HashMap<String,Integer> map = new HashMap<>();
		for(CartOrder cartOrderitem: cartOrder){
			map.put(db.getInventoryStockList().get(cartOrderitem.getItemName()).getCategoryName()
					,map.getOrDefault(db.getInventoryStockList()
							.get(cartOrderitem.getItemName()).getCategoryName(),0)
					+ cartOrderitem.getQuantity());
		}
		if(map.getOrDefault("Essential",0) > 10){
			return false;
		}else if(map.getOrDefault("Luxury",0) > 10){
			return false;
		}else if(map.getOrDefault("Misc",0) > 10){
			return false;
		}

		return true;

	}    

	//This method is defined for generating the output file depending on the inputs given.
	public void produceOutput(){
		if(data_output.size()==0){
			boolean itemIndex = false;

			data_output.add("Item, Quantity, Price, Total Paid Amount");

			for(CartOrder CartOrderitem: cartOrder){
				if( !itemIndex ) {
					itemIndex = true;
					
					data_output.add(CartOrderitem.getItemName()+","+CartOrderitem.getQuantity()+"," + 
							db.getInventoryStockList().get(CartOrderitem.getItemName()).getPrice() * CartOrderitem.getQuantity()
								+","+Double.toString((finalOrder.getTotalAmount())));

				}
				else
					data_output.add(CartOrderitem.getItemName()+","+CartOrderitem.getQuantity()+
							","+db.getInventoryStockList().get(CartOrderitem.getItemName()).getPrice() * CartOrderitem.getQuantity());
				
			}
		try{
				helperFile.generateOutput(data_output,false);
			}catch (IOException e){
				e.printStackTrace();
				
			}
		}else{
			try{
				helperFile.generateOutput(data_output,true);
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}

}
