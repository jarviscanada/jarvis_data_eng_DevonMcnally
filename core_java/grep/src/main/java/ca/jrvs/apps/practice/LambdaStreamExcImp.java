package ca.jrvs.apps.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc{
    @Override
    public Stream<String> createStrStream(String... strings) {

        Stream<String> stream = Arrays.stream(strings);

        return stream;

    }

    @Override
    public Stream<String> toUpperCase(String... strings) {

       Stream<String> stream = Arrays.stream(strings)
               .map(string -> string.toUpperCase());


        return stream;

    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {

        Stream<String> stream = stringStream.filter(s -> s.contains(pattern));

        return stream;
    }

    @Override
    public IntStream createIntStream(int[] arr) {

        IntStream intStream = Arrays.stream(arr);

        return intStream;
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {


        List<E> newList = new ArrayList<>();

        stream.forEach(newList::add);
        return newList;
    }

    @Override
    public List<Integer> toList(IntStream intStream) {

        List<Integer> newList = new ArrayList<>();

        intStream.forEach(newList::add);

        return newList;

    }

    @Override
    public IntStream createIntStream(int start, int end) {


        IntStream intStream = IntStream.range(start, end);

        return intStream;

    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {

        DoubleStream stream = intStream.mapToDouble(x -> (double) x);

        stream.map(x -> Math.sqrt(x));

        return stream;

    }

    @Override
    public IntStream getOdd(IntStream intStream) {


        intStream = intStream.filter(x -> x % 2 !=0);

        return intStream;
    }

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {

        Consumer<String> printer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(prefix+s+suffix);
            }
        };


        return printer;
    }

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {

        getLambdaPrinter("Start>", "<End").accept(Arrays.toString(messages));
    }

    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {


        List<String> messageList = new ArrayList<>();
        intStream.filter(x -> x % 2 !=0)
                .forEach(x -> messageList.add((String.valueOf(x))));

        String[] messages = new String[messageList.size()];

        messageList.toArray(messages);
        printMessages(messages, getLambdaPrinter("", ""));
    }

    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {


        
        Stream<Integer> stream;


        ints.flatMap(new Function<List<Integer>, Stream<?>>() {
            @Override
            public Stream<?> apply(List<Integer> integers) {
                return integers.stream().map(x -> x *x);
            }
        });

        return null;
    }
}
