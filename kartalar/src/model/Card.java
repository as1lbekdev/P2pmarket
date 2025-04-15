package model;

public class Card {
    private String cardNumber;
    private double balance;
    private String ownerName;


    public Card(String cardNumber, double balance, String ownerName) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.ownerName = ownerName;

    }
    public Card(){

    }
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", balance=" + balance +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
