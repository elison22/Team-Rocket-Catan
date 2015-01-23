package model.trade;

import model.cards.ResourceSet;

public interface ITradeOffer {


    /**
     * Sets the sender of the trade offer
     * @param sender person who is sending the trade
     */
    public void setSender(int sender);

    /**
     * Sets the receiver of the trade offer
     * @param reciever person who the trade is being sent to
     */
    public void setReciever(int reciever);

    /**
     * Sets the resources being traded in the offer
     * @param offer list of resource being given/received in trade
     */
    public void setOffer(ResourceSet offer);

    /**
     * Sets the type of trade being made
     * @param tradeType either domestic or Maritime
     */
    public void setTradeType(String tradeType);

}