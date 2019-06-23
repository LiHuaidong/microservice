package hdli.behavioural.Template;

public class TemplatePatternDemoMain {

    public static void main(String[] args) {
        Game cricketGame = new Cricket();
        cricketGame.play();

        System.out.println("---------------------");

        Game footballGame = new Football();
        footballGame.play();
    }
}
