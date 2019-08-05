package hdli.condition;

public class FileMock {

    private String content[];
    private int index;

    public FileMock(int size, int length) {
        this.content = new String[size];
        for (int i = 0; i < 10; i++) {
            StringBuilder buffer = new StringBuilder(length);
            int indice = (int)Math.random()*255;
            buffer.append((char) indice);
            content[i] = buffer.toString();
        }
        index = 0;
    }

    public boolean hasMoreLines() {
        return index < content.length;
    }

    public String getLines() {
        if (this.hasMoreLines()) {
            System.out.println("Mock: " + (content.length - index));
            return content[index++];
        }
        return null;
    }

}
