package hdli.behavioural.chainOfResponsibility;

public class CommonHandler extends Handler {

    @Override
    public boolean handleRequest(String value) {
        System.out.println("CommonHandler: handled~");
        return true;
    }

}
