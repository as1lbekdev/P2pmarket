package strategy;

import service.CardService;

public class P2pStrategy {

    protected CardService cardService;
    public P2pStrategy(CardService cardService) {
        this.cardService = cardService;
    }
    public void p2p(String fromCardNumber, String toCardNumber, double amount) {

    }
}
