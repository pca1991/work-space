package priv.austin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SpringBootTest
class BusinessValidateApplicationTests {

    @Test
    void contextLoads() {

    }

    public static void main(String[] args){
        List<String> list = Arrays.asList("a","b","c");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Collections.sort(list,(a,b) -> b.compareTo(a));
        //
        //list.sort((a,b) -> a.compareTo(b));

        //Converter<String, Integer> converter = new Converter<String, Integer>() {
        //    @Override
        //    public Integer convert(String source) {
        //        return Integer.valueOf(source);
        //    }
        //};
        //
        //Converter<String, Integer> converter2 = a -> Integer.valueOf( a);
        //
        //Converter<String, Integer> converter3 = Integer::valueOf;

        //PersonFactory<Person> personFactory = Person::new;
        //Person person = personFactory.create("Peter", "Parker");


        //Predicate<String> predicate = s -> s.length() > 0;
        //System.out.println(predicate.test("foo"));
        //System.out.println(predicate.negate().test("foo"));


        //输出所有区域标识符
        ZoneId.getAvailableZoneIds().stream().filter(s -> s.startsWith("Asia/B")).forEach(System.out::println);
        ZoneId.of("");
    }

    class Person {
        String firstName;
        String lastName;

        Person() {}

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

}
