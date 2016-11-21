import java.util.*;
import java.util.function.Function;
import java.util.function.*;

interface Function2<T, U, R> {
    R apply(T t, U u);
}

class RoughPad<T> implements Identifiable<T> {    
    public <U> U apply(T t, Function<? super T, ? extends U> f) {
        return f.apply(t);
    }

    public <R, U> R apply(T t, U u, Function2<? super T, ? super U, ? extends R> f) {
        return f.apply(t, u);
    }

    public static void main(String[] args) {
        foo(); bar(); fruits();
    }

    static void foo() {
        try {

            String[] strings = new String[]{"aa", "bb"};
            Object[] objects = strings;
            objects[0] = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void bar() {
        try {
            List<Integer> ints= new ArrayList<>();
            ints.add(1);
            List objs = ints;
            objs.add("1");
            for (Integer i: ints) {
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void fruits() {
        List<Plant> plants = new ArrayList<>();
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple());
        //apples.add(new Orange());  // error
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Apple());
        fruits.add(new Orange());

        List<? extends Fruit> xFruits = new ArrayList<Apple>();

        // xFruits.add(new Apple());       // 1
        // xFruits.add(new Orange());    // 2
        // xFruits.add(new Fruit());    // 3
        // xFruits.add(new Plant());        // 4
        Fruit fruit = xFruits.get(0);    // 5

        List<? super Fruit> sFruits = new ArrayList<Plant>();
        sFruits.add(new Apple());       // 6
        sFruits.add(new Orange());    // 7
        sFruits.add(new Fruit());    // 8
        // sFruits.add(new Plant());     // 9
        // Fruit fruit = sFruits.get(0);    // 10

        countAgain(plants);
        count(apples);
        countAgain(fruits);
        count(fruits);
    }

    static int count(List<? extends Fruit> fruits) {
        return fruits.size();
    }

    static int countAgain(List<? super Fruit> fruits) {
        return fruits.size();
    }
}
