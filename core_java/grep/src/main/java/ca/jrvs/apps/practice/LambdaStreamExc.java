package ca.jrvs.apps.practice;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface LambdaStreamExc {

    /**
     * Create a String from array
     * @param strings
     * @return
     */
    Stream<String> createStream(String ...strings);

    /**
     * Convert all strings to uppercase
     * @param strings
     * @return
     */
    Stream<String> toUpperCase(String ...strings);

    /**
     * Filter strings that contains the pattern
     * @param stringStream
     * @param pattern
     * @return
     */
    Stream<String> filter(Stream<String> stringStream, String pattern);

    /**
     * Create a intStream from an array
     * @param array
     * @return
     */
    IntStream createIntStream(int[] array);

    /**
     * Convert a stream to a list
     * @param stream
     * @return
     * @param <E>
     */
    <E> List<E> toList(Stream<E> stream);

    /**
     * Convert a intStream to a list
     * @param intStream
     * @return
     */
    List<Integer> toList(IntStream intStream);

    /**
     * Create a IntStream range from start to end inclusive
     * @param start
     * @param end
     * @return
     */
    IntStream createStream(int start, int end);

    /**
     * Convert a intStream to a doubleStream
     * and compute square root of each element
     * @param intStream
     * @return
     */
    DoubleStream squareRootIntStream(IntStream intStream);

    /**
     * Filter all even number and return odd numbers from a intStream
     * @param intStream
     * @return
     */
    IntStream getOdd(IntStream intStream);

    /**
     * Return a lambda function that print a message with a prefix and suffix
     * This lambda can be useful to format logs
     * @param prefix
     * @param suffix
     * @return
     */
    Consumer<String> getLambdaPrinter(String prefix, String suffix);

    /**
     * Print each message with a given printer
     * Use `getLambdaPrinter` method
     * @param messages
     * @param printer
     */
    void printMessage(String[] messages, Consumer<String> printer);

    /**
     * Print all odd number from a intStream
     * Use `createStream` and `getLambdaPrinter` methods
     * @param intStream
     * @param printer
     */
    void printOdd(IntStream intStream, Consumer<String> printer);

    /**
     * Square each number from the input
     * Write two solutions and compare difference
     *  - Using flatMap
     * @param ints
     * @return
     */
    Stream<Integer> flatNestedInt(Stream<List<Integer>> ints);
}
