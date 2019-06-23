package hdli.behavioural.chainOfResponsibility;

public class TownHandler extends Handler {

    @Override
    public boolean handleRequest(String value) {
        boolean result = false;
        if ("town".equals(value)) {
            System.out.println("TownHandler: handled~");
            result = true;
        } else {
            System.out.println("TownHandler: pass~");
            this.next.handleRequest(value);
        }
        return result;
    }

}
