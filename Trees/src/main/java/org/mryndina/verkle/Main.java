package org.mryndina.verkle;

import org.junit.jupiter.api.Assertions;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class Main {
    public static void main(String[] args) {
        System.out.println("Verkle Tree ");
        VerkleTree.first(args);
       System.out.println("Test1");
        test1();
        System.out.println("Test2");
        test2();
       System.out.println("Test3");
        test3();
         System.out.println("Test4");
        test4();
    }



        public static void  test1() {
            VerkleTree verkleTree = new VerkleTree();

            // Вставка значений
            verkleTree.insert("key1", "value1");
            verkleTree.insert("key2", "value2");
            verkleTree.insert("key3", "value3");

            // Проверка отсутствия значения для несуществующего ключа
            String value = verkleTree.get("nonexistentKey");
            if (value == null) {
                System.out.println("Test Passed: Value for nonexistent key is null.");
            } else {
                System.out.println("Test Failed: Expected null value for nonexistent key.");
            }

            // Проверка отсутствия значения для ключа с другим хешем
            byte[] keyHash = verkleTree.computeHash("key4");
            value = verkleTree.get(Arrays.toString(keyHash));
            if (value == null) {
                System.out.println("Test Passed: Value for key with different hash is null.");
            } else {
                System.out.println("Test Failed: Expected null value for key with different hash.");
            }
        }

    public static void test2() {
        VerkleTree verkleTree = new VerkleTree();
        verkleTree.insert("key1", "value1");

        // Получение и проверка исходного значения
        String initialValue = verkleTree.get("key1");
        assertEquals("value1", initialValue);

        // Обновление значения
        verkleTree.insert("key1", "newvalue1");

        // Получение и проверка обновленного значения
        String updatedValue = verkleTree.get("key1");
        assertEquals("newvalue1", updatedValue);

        // Подтверждение успешного прохождения теста
        System.out.println("Тест testValueUpdate успешно пройден.");
    }

    public static void test3() {
        VerkleTree verkleTree = new VerkleTree();

        // Вставка значения
        verkleTree.insert("key1", "value1");

        // Удаление значения
        verkleTree.remove("key1");

        // Получение значения после удаления
        String value = verkleTree.get("key1");

        // Убедитесь, что значение равно null
        System.out.println("Value: " + value);  // Вывод: Value: null
    }



    public static void test4() {
        VerkleTree verkleTree = new VerkleTree();

        // Generate a large number of unique keys and values
        int numEntries = 100000;
        Random random = new Random();
        for (int i = 0; i < numEntries; i++) {
            String key = "key" + i;
            String value = "value" + i;
            verkleTree.insert(key, value);
        }

        // Verify that all values were inserted correctly
        for (int i = 0; i < numEntries; i++) {
            String key = "key" + i;
            String expectedValue = "value" + i;
            String retrievedValue = verkleTree.get(key);
            if (!expectedValue.equals(retrievedValue)) {
                System.out.println("Error: Value mismatch for key " + key);
            }
        }

        // Measure the time taken to insert and access values
        long startTime = System.currentTimeMillis();

        // Insert a large number of additional entries
        for (int i = numEntries; i < numEntries + 10000; i++) {
            String key = "key" + i;
            String value = "value" + i;
            verkleTree.insert(key, value);
        }

        // Access a random subset of keys and measure the retrieval time
        int numRandomAccesses = 1000;
        for (int i = 0; i < numRandomAccesses; i++) {
            String key = "key" + random.nextInt(numEntries);
            String retrievedValue = verkleTree.get(key);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time taken: " + totalTime + " ms");

        // Check memory usage
        long memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory usage: " + memoryUsage + " bytes");
    }

    }
    /*  private static Map<String, String> generateKeyValuePairs(int numPairs) {
        Map<String, String> keyValuePairs = new HashMap<>();
        for (int i = 0; i < numPairs; i++) {
            String key = "key_" + i;
            String value = "value_" + i;
            keyValuePairs.put(key, value);
        }
        return keyValuePairs;
    }*/
