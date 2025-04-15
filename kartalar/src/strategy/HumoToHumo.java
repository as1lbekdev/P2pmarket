package strategy;

import model.Card;
import service.CardService;

public class HumoToHumo  extends P2pStrategy {

    private static final double percent=0.002;

    public HumoToHumo(CardService cardService) {
        super(cardService);
    }

    public void p2p(Card fromCard, Card toCard, double amount) {
        Card fromcard=super.cardService.getCardByNumber(fromCard.getCardNumber());
        Card tocard=super.cardService.getCardByNumber(toCard.getCardNumber());

        double amountafterpercent = amount+(amount*percent);
        fromcard.setBalance(fromcard.getBalance()-amountafterpercent);
        tocard.setBalance(tocard.getBalance()+amountafterpercent);

    }

}
