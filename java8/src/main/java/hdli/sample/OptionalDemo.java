package hdli.sample;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {
        Optional<String> name = Optional.of("lihuaidong");
        Optional empty = Optional.ofNullable(null);

        if(name.isPresent()) {
            System.out.println(name.get());
        }

        try {
            System.out.println(empty.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        name.ifPresent((value) -> System.out.println("The length of the value is: " + value.length()));

        System.out.println(empty.orElse("There is no value present"));
        System.out.println(name.orElse("There is some value"));

        System.out.println(empty.orElseGet(() -> "Default Value"));
        System.out.println(name.orElseGet(String::new));

        try {
            empty.orElseThrow(IllegalArgumentException::new);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Optional<String> upperName = name.map((value) -> value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));

        upperName = name.flatMap((value) -> Optional.of(value.toUpperCase()));
        System.out.println(upperName.get());

        List<String> names = Arrays.asList("LiHuaidong", "Konglina");
        for(String s : names) {
            Optional<String> nameLenLessThan9 = Optional.of(s).filter((value) -> value.length() < 9);
            System.out.println(nameLenLessThan9.orElse("The name is more than 6 characters"));
        }
    }
}
