import java.util.*;
import java.util.function.*;

public class GenericsStory {
    public List mapRaw(List l, Function f, Object marker) {
        List ints = new ArrayList();
        for (Object s: l) {
            ints.add(f.apply(s));
        }

        ints.add(marker); 
        return ints;
    }

    public List<Integer> mapMono(List<String> l, Function<String, Integer> f, Integer marker) {
        List<Integer> ints = new ArrayList<>();
        for (String s: l) {
            ints.add(f.apply(s));
        }
        ints.add(marker); 
        return ints;        
    }

    public <T, R> List<R> mapPoly(List<T> l, Function<T, R> f, R marker) {
        List<R> xs = new ArrayList<>();
        for (T s: l) {
            xs.add(f.apply(s));
        }
        xs.add(marker); 
        return xs;        
    }

    public static void main(String[] args) {
        GenericsStory story = new GenericsStory();

        List l = story.mapRaw(Arrays.asList("a", "bb", "ccc"), s -> ((String) s).length(), "1000");

        List<Integer> ints = story.mapMono(Arrays.asList("a", "bb", "ccc"), s -> s.length(), 1000);

        // compiler error: incompatible types
        // story.mapMono(Arrays.asList("a", "bb", "ccc"), s -> s.length(), "1000"));

        // compiler error: incompatible types
        // story.mapMono(Arrays.asList("a", "bb", "ccc"), i -> i.toString(), 1000);
        
        // compiler error: incompatible types
        // story.mapMono(Arrays.asList(1, 2, 3), i -> i, 1000);

        List<Integer> genericInts = story.mapPoly(Arrays.asList("a", "bb", "ccc"), s -> s.length(), 1000);

        List<String> strings = story.mapPoly(Arrays.asList(1, 2, 3), i -> "'" + i + "'", "'1000'");

        // compiler error: incompatoble types
        // story.mapPoly(Arrays.asList(1, 2, 3), (String i) -> i);
    }
}