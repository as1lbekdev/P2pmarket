package strategy;

import model.Card;
import service.CardService;

public class HumoToUzcardStrategy extends P2pStrategy {
    private static final double percent=0.005;

    public HumoToUzcardStrategy(CardService cardService) {
        super(cardService);

    }
    public void p2p(String fromCardNumber, String toCardNumber,double amount) {
        Card fromCard=super.cardService.getCardByNumber(fromCardNumber);
        Card toCard=super.cardService.getCardByNumber(toCardNumber);

         double amountafterpercent = amount+(amount*percent);
        fromCard.setBalance(fromCard.getBalance()-amountafterpercent);
        toCard.setBalance(toCard.getBalance()+amountafterpercent);
    }
}
