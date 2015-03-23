package model.strade;

import java.util.HashMap;

import serializer.json.JsonResourceList;
import serializer.json.JsonTradeOffer;
import shared.definitions.ResourceType;

public class ServerTradeOffer {

    private int sender;
    private int receiver;
    private HashMap<ResourceType, Integer> resources;

    public ServerTradeOffer(JsonTradeOffer trade) {
        sender = trade.getSender();
        receiver = trade.getReceiver();
        JsonResourceList list = trade.getOffer();

        resources = new HashMap<ResourceType, Integer>();
        resources.put(ResourceType.BRICK, list.getBrick());
        resources.put(ResourceType.ORE, list.getOre());
        resources.put(ResourceType.SHEEP, list.getSheep());
        resources.put(ResourceType.WHEAT, list.getWheat());
        resources.put(ResourceType.WOOD, list.getWood());
    }

    public ServerTradeOffer(int sender, int receiver, HashMap<ResourceType, Integer> resourceMap) {
        this.sender = sender;
        this.receiver = receiver;

        resources = new HashMap<ResourceType, Integer>();
        resources.put(ResourceType.BRICK, resourceMap.get(ResourceType.BRICK));
        resources.put(ResourceType.ORE, resourceMap.get(ResourceType.ORE));
        resources.put(ResourceType.SHEEP, resourceMap.get(ResourceType.SHEEP));
        resources.put(ResourceType.WHEAT, resourceMap.get(ResourceType.WHEAT));
        resources.put(ResourceType.WOOD, resourceMap.get(ResourceType.WOOD));
    }

    public int getSender() {
        return sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public HashMap<ResourceType, Integer> getResources() {
        return resources;
    }
}
