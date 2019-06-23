package hdli.behavioural.chainOfResponsibility;

public abstract class Handler {

    protected Handler next;

    public abstract boolean handleRequest(String value);

    public Handler next() {
        return this.next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

}
