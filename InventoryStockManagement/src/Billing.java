import java.io.IOException;

import Handlers.CardHandler;
import Handlers.DatasetHandler;
import Handlers.InputHandler;

public class Billing {

	private static  CardHandler cardHandler ;
	public static void main(String[] args) throws IOException {

		if(args.length == 0){
			System.out.println("Please enter file path to Inventory Stock Dataset");
			System.exit(0);
		}

		datasetinit(args[0]);
		cardinit(args[1]);
		orderCreation(args[2]);

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
		InputHandler inputHandler = new InputHandler(filePath);

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

