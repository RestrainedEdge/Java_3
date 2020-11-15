package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.utils.HashMap;
import edu.ktu.ds.lab3.utils.HashType;
import edu.ktu.ds.lab3.utils.Map;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(time = 1, timeUnit = TimeUnit.SECONDS)
public class Benchmark {

    @State(Scope.Benchmark)
    public static class FullMap {

        List<String> ids;
        List<String> ids_2;
        List<Car> cars;
        List<Car> cars_2;
        HashMap<String, Car> carsMap;
        HashMap<String, Car> carsPutMap;

        @Setup(Level.Iteration)
        public void generateIdsAndCars(BenchmarkParams params) {
            ids = Benchmark.generateIds(Integer.parseInt(params.getParam("elementCount")));
            cars = Benchmark.generateCars(Integer.parseInt(params.getParam("elementCount")));
            ids_2 = Benchmark.generateIds(Integer.parseInt(params.getParam("elementCount")));
            cars_2 = Benchmark.generateCars(Integer.parseInt(params.getParam("elementCount")));
        }

        @Setup(Level.Invocation)
        public void fillCarMap(BenchmarkParams params) {
            carsMap = new HashMap<>(HashType.DIVISION);
            putOne(ids, cars, carsMap);
            carsPutMap = new HashMap<>(HashType.DIVISION);
            putOne(ids_2, cars_2, carsPutMap);
        }
    }

    @Param({ "10000",  "20000",  "30000",  "40000",
             "50000",  "60000",  "70000",  "80000",
             "90000", "100000", "110000", "120000",
            "130000", "140000", "150000", "160000"})
    public int elementCount;

    List<String> ids;
    List<String> ids_2;
    List<Car> cars;
    List<Car> cars_2;
    HashMap<String, Car> carsPutMap;

    @Setup(Level.Iteration)
    public void generateIdsAndCars() {
        ids = generateIds(elementCount);
        ids_2 = generateIds(elementCount);
        cars = generateCars(elementCount);
        cars_2 = generateCars(elementCount);
        carsPutMap = new HashMap<>(HashType.DIVISION);
        putOne(ids_2, cars_2, carsPutMap);
    }

    static List<String> generateIds(int count) {
        return new ArrayList<>(CarsGenerator.generateShuffleIds(count));
    }

    static List<Car> generateCars(int count) {
        return new ArrayList<>(CarsGenerator.generateShuffleCars(count));
    }

    @org.openjdk.jmh.annotations.Benchmark
    public Map<String, Car> putOneInMap() {
        Map<String, Car> carsMap = new HashMap<>(HashType.DIVISION);
        putOne(ids, cars, carsMap);
        return carsMap;
    }
    @org.openjdk.jmh.annotations.Benchmark
    public Map<String, Car> putAllInMap() {
        Map<String, Car> carsMap = new HashMap<>(HashType.DIVISION);
        putAll(carsPutMap, carsMap);
        return carsMap;
    }

    public static void putOne(List<String> ids, List<Car> cars, Map<String, Car> carsMap) {
        for (int i = 0; i < cars.size(); i++) {
            carsMap.put(ids.get(i), cars.get(i));
        }
    }
    public static void putAll(Map<String, Car> putMap, Map<String, Car> carsMap) {
        carsMap.putAll(putMap);
    }
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Benchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
