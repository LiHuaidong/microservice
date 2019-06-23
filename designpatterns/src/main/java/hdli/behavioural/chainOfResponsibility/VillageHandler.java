package hdli.behavioural.chainOfResponsibility;

public class VillageHandler extends Handler {

    @Override
    public boolean handleRequest(String value) {
        boolean result = false;
        if ("village".equals(value)) {
            System.out.println("VillageHandler: handled~");
            result = true;
        } else {
            System.out.println("VillageHandler: pass~");
            this.next.handleRequest(value);
        }
        return result;
    }

}
