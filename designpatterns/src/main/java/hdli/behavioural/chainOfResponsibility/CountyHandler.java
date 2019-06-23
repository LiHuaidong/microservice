package hdli.behavioural.chainOfResponsibility;

public class CountyHandler extends Handler {

    @Override
    public boolean handleRequest(String value) {
        boolean result = false;
        if ("county".equals(value)) {
            System.out.println("CountyHandler: handled~");
            result = true;
        } else {
            System.out.println("CountyHandler: pass~");
            this.next.handleRequest(value);
        }
        return result;
    }

}
