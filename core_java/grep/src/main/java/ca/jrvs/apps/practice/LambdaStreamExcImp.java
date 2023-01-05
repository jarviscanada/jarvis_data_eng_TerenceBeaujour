package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc {

    public static void main(String[] args) {
        LambdaStreamExcImp lambda = new LambdaStreamExcImp();
        String[] arrayString = new String[]{"Terence", "Fanny", "Socrate"};
        Stream<String> stringStream = lambda.createStream(arrayString);
        stringStream.forEach(System.out::println);
        Stream<String> stringStreamUpper = lambda.toUpperCase(arrayString);
        stringStreamUpper.forEach(string -> System.out.println(string));
    }
    /**
     * Create a String from array
     *
     * @param strings
     * @return
     */
    @Override
    public Stream<String> createStream(String... strings) {
        return Arrays.stream(strings);
    }

    /**
     * Convert all strings to uppercase
     *
     * @param strings
     * @return
     */
    @Override
    public Stream<String> toUpperCase(String... strings) {
        Stream<String> stream = Arrays.stream(strings);
        return stream.map(string -> string.toUpperCase()) ;
    }

    /**
     * Filter strings that contains the pattern
     *
     * @param stringStream
     * @param pattern
     * @return
     */
    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(string -> string.matches(pattern));
    }

    /**
     * Create a intStream from an array
     *
     * @param array
     * @return
     */
    @Override
    public IntStream createIntStream(int[] array) {
        return Arrays.stream(array).map(element -> (int) element);
    }

    /**
     * Convert a stream to a list
     *
     * @param stream
     * @return
     */
    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    /**
     * Convert a intStream to a list
     *
     * @param intStream
     * @return
     */
    @Override
    public List<Integer> toList(IntStream intStream) {
        return intStream.boxed().collect(Collectors.toList());
    }

    /**
     * Create a IntStream range from start to end inclusive
     *
     * @param start
     * @param end
     * @return
     */
    @Override
    public IntStream createStream(int start, int end) {
        return IntStream.range(start, end + 1 );
    }

    /**
     * Convert a intStream to a doubleStream
     * and compute square root of each element
     *
     * @param intStream
     * @return
     */
    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream
                .asDoubleStream()
                .map(Math::sqrt);
    }

    /**
     * Filter all even number and return odd numbers from a intStream
     *
     * @param intStream
     * @return
     */
    @Override
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(x -> x % 2 != 0);
    }

    /**
     * Return a lambda function that print a message with a prefix and suffix
     * This lambda can be useful to format logs
     *
     * @param prefix
     * @param suffix
     * @return
     */
    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        Consumer<String> function = (String message) -> System.out.println(prefix + " " + message + " " + suffix);
        return function;
    }

    /**
     * Print each message with a given printer
     * Use `getLambdaPrinter` method
     *
     * @param messages
     * @param printer
     */
    @Override
    public void printMessage(String[] messages, Consumer<String> printer) {
        for (String string : messages) {
            printer.accept(string);
        }
    }

    /**
     * Print all odd number from a intStream
     * Use `createStream` and `getLambdaPrinter` methods
     *
     * @param intStream
     * @param printer
     */
    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {
        intStream
                .filter(x -> x%2 != 0)
                .forEach(x -> printer.accept(String.valueOf(x)));
    }

    /**
     * Square each number from the input
     * Write two solutions and compare difference
     * - Using flatMap
     *
     * @param ints
     * @return
     */
    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        return null;
    }
}
