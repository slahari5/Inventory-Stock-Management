import java.io.IOException;

import Handlers.CardHandler;
import Handlers.DatasetHandler;
import Handlers.IOFileHandler;

public class Billing {

	private static  CardHandler cardHandler ;
	private final static String cardFilePath = "./resources/Cards.csv";
	private final static String inventoryDataSetFilePath = "./resources/InventoryStock.csv";
			
	public static void main(String[] args) throws IOException {
		
		System.out.println("Please enter the input file path to order items:");

		if(args.length == 0){
			System.out.println("No valid path provided");
			System.exit(0);
		}
	    
		datasetinit(inventoryDataSetFilePath);
		cardinit(cardFilePath);
		orderCreation(args[0]);

	}

	private static void datasetinit(String args) {
		DatasetHandler datasetHandler = new DatasetHandler(args);
		datasetHandler.DatasetInit();

	}

	private static void cardinit(String args) {
		cardHandler = new CardHandler(args);
		cardHandler.CardListInit();

	}

	private static void orderCreation(String filePath) {
		IOFileHandler inputHandler = new IOFileHandler(filePath);

		if(inputHandler.initOrder()){
			if(inputHandler.validateOrder()){

				inputHandler.countTotalAmount();
				if(inputHandler.obtainTotalAmount()>0){

					if(inputHandler.isCreditCardExist != "") {

						cardHandler.addNewLine(inputHandler.isCreditCardExist);

					}

					inputHandler.publishOrder();
				}
			}else {
				System.out.println("Please look into the error log file created during creation of the order.");

				inputHandler.produceOutput();
			}
		}
		else {
			System.out.println("please provide valid input file order");
		}
	}


}

