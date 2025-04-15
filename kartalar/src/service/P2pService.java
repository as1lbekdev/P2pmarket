package service;

import strategy.*;

public class P2pService {
    private P2pStrategy p2pStrategy;
    private CardService cardService;

    public P2pService( CardService cardService) {
        this.cardService = cardService;
    }
    public void p2p(int type, String fromCardNumber, String toCardNumber, double amount) {
        if (type == 1) {
            p2pStrategy = new HumoToUzcardStrategy(cardService);
        }
        else if (type == 2) {
            p2pStrategy = new UzcardToHumoStrategy(cardService);
        }
        else if (type == 3) {
            p2pStrategy = new UzcardToUzcard(cardService);
        }
        else if (type == 4) {
            p2pStrategy = new HumoToHumo(cardService);
        }

        p2pStrategy.p2p(fromCardNumber,toCardNumber,amount);
    }
}
