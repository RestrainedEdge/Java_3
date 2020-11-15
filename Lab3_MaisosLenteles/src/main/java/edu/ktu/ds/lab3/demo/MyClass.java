package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.utils.*;

public class MyClass {
    public static void main(String[] args) {
        ParsableMap<String, Car> carsMap
                = new ParsableHashMap<>(String::new, Car::new, HashType.DIVISION);
        String removeID = null;
        Car containsValue = null;
        for(int i = 0; i < 32; i++) {
            int id = ((i * 81) >>> 2) ^ ((2 << 12) - 1);
            String carID = "Car" + id;
            Car car = new Car("Car" + i, "ModelCar" + i, i%30 +1990, i +100, i +100);
            if(i == 16) {
                removeID = carID;
                containsValue = car;
            }
            carsMap.put(carID, car);
        }
//        RemoveTest(carsMap, removeID);
//        ContainsValueTest(carsMap, removeID, containsValue);
//        PutIfAbsentTest(carsMap);
//        NumberOfEmptiesTest(carsMap);
//        PutAllTest(carsMap);
//        ReplaceTest(carsMap, removeID, containsValue);
    }
    public static void RemoveTest(ParsableMap<String, Car> carsMap, String removeID) {
        carsMap.println("");
        Ks.out("Removing " + removeID + '\n');
        carsMap.remove(removeID);
        carsMap.println("");
    }
    public static void ContainsValueTest(ParsableMap<String, Car> carsMap, String removeID, Car value) {
        Ks.out("Contains - " + value + "-" + carsMap.containsValue(value) + '\n');
        Ks.out("Removing " + value + '\n');
        carsMap.remove(removeID);
        Ks.out("Contains - " + value + "-" + carsMap.containsValue(value));
    }
    public static void PutIfAbsentTest(ParsableMap<String, Car> carsMap) {
        String key = "CarTEST_TEST_TEST";
        Car car = new Car("TEST", "TEST" , 1990, 100, 100);
        Ks.out(carsMap.putIfAbsent(key, car) + "\n");
        Ks.out(carsMap.putIfAbsent(key, car));
    }
    public static void NumberOfEmptiesTest(ParsableMap<String, Car> carsMap) {
        carsMap.println("");
        Ks.out("Empties - " + carsMap.numberOfEmpties());
    }
    public static void PutAllTest(ParsableMap<String, Car> carsMap) {
        ParsableMap<String, Car> insertMap
                = new ParsableHashMap<>(String::new, Car::new, HashType.DIVISION);
        for(int i = 0; i < 8; i++) {
            int id = (((i+1) * 9) >>> 2) ^ ((2 << 2) - 1);
            String carID = "CarInsert" + id;
            Car car = new Car("Car" + (i<<10), "ModelCar" + (i<<10), i%30 +1990, i +100, i +100);
            insertMap.put(carID, car);
        }
        carsMap.println("=");
        Ks.out("Inserting \n");
        carsMap.putAll(insertMap);
        carsMap.println("=");
    }
    public static void ReplaceTest(ParsableMap<String, Car> carsMap, String replaceID, Car value) {
        Car car = new Car("TEST", "TEST" , 1990, 100, 100);
        Ks.out("Replace - " + carsMap.replace(replaceID, value, car) + "\n");
        Ks.out("Replace - " + carsMap.replace(replaceID, value, car));
    }
}
