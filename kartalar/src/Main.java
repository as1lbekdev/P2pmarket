import model.Card;
import service.CardService;
import service.P2pService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);

        CardService cardService = new CardService();
        P2pService p2pService=new P2pService(cardService);

        int choice=44434344;
        while (choice != 0) {

            System.out.println("1.Add Card 2.list Card,3.p2p");
            choice = scannerInt.nextInt();
            switch (choice) {
                case 1->{
                    Card card = new Card();
                    System.out.println("Enter card number");
                    card.setCardNumber(scannerStr.nextLine());

                    System.out.println("enter owner name:");
                    card.setOwnerName(scannerStr.nextLine());

                    System.out.println("enter balance:");
                    card.setBalance(scannerStr.nextDouble());
                    scannerStr.nextLine();
                    cardService.addCard(card);
                }
                case 2->{
                 cardService.listCards();
                }
                case 3->{
                    System.out.println("enter from card number:");
                    String fromCardNumber = scannerStr.nextLine();

                    System.out.println("enter to card number:");
                    String toCardNumber = scannerStr.nextLine();

                    System.out.println("enter your amount:");
                    double amount = scannerStr.nextDouble();

                    System.out.println("1.Humo->Uzcard,2.Uzcard->Humo,3.Uzcard->Uzcard,4.Humo->humo");
                    int i= scannerInt.nextInt();

                    p2pService.p2p(i,toCardNumber,fromCardNumber,amount);

                }
            }
        }
    }
}