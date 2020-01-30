package hdli;

import java.io.*;

public class ReadCountMain {

    public static void main(String[] args) {
        File file = new File("E:\\research\\microservice\\io\\src\\main\\resources\\count");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String num = null;
            Long printNum = 0L;
            while((num = reader.readLine()) != null ) {
                if(!num.trim().equals("")) {
                    try {
                        printNum = Long.parseLong(num);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        continue;
                    }
                    System.out.println("printNum = [" + printNum + "]");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
