package service;

import model.Card;

public class CardService {
   private Card[] cards=new Card[10];
   private int index=0;

   public CardService() {
   }
   public void addCard(Card card) {
       cards[index++] = card;
   }
   public void listCards() {
       for (Card card : cards) {
           if (card != null) {
               System.out.println(card);
           }

       }
   }
   public Card getCardByNumber(String cardNumber) {
       for (Card card : cards) {
           if (card!=null && card.getCardNumber().equals(cardNumber)) {
               return card;
           }
       }
       return null;
   }

}
