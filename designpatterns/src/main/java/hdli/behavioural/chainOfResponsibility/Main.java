package hdli.behavioural.chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String cityRequest = "city";
        String countyRequest = "county";
        String townRequest = "town";
        String villageRequest = "village";

        Handler villageHandler = new VillageHandler();
        Handler townHandler = new TownHandler();
        Handler countyHandler = new CountyHandler();
        Handler commonHandler = new CommonHandler();

        villageHandler.setNext(townHandler);
        townHandler.setNext(countyHandler);
        countyHandler.setNext(commonHandler);

        List<Handler> handlerList = new ArrayList<>();
        handlerList.add(villageHandler);
        handlerList.add(townHandler);
        handlerList.add(countyHandler);
        handlerList.add(commonHandler);

//        villageHandler.handleRequest(villageRequest);
        villageHandler.handleRequest(townRequest);
//        villageHandler.handleRequest(countyRequest);
//        villageHandler.handleRequest(cityRequest);
    }

    public static void handleRequest(String request, List<Handler> handlerList) {
        if(handlerList != null && handlerList.size() > 0) {
            for(Handler handler : handlerList) {
                if(handler.handleRequest(request)) {
                    break;
                }
            }
        }
    }

}
