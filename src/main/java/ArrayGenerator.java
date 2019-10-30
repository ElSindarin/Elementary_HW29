import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayGenerator {
    public int size;
    Integer array[];
    //Integer sum = 0;
    Double trigSum = 0d;



    public static void main(String[] args) throws InterruptedException {
        int arr[] = initArray();
        ArraySumCalculator thread[] = new ArraySumCalculator[10];
        initThreadArrays(thread);

        long start = System.currentTimeMillis();
        Double total = operateThreads(thread);
        System.out.println("Общая сумма: " + total);
        System.out.println("Длительность суммирования потоками " + Duration.ofMillis(System.currentTimeMillis() - start));

        //обычное суммирование
        start = System.currentTimeMillis();
        Double regularSum = 0d;
        for (int i = 0; i < arr.length; i++) {
            //regularSum += arr[i];
            regularSum += (Math.sin(arr[i]) * Math.sin(arr[i]) + Math.cos(arr[i]) * Math.cos(arr[i]));
        }
        System.out.println("Общая сумма: " + regularSum);
        System.out.println("Длительность обычного суммирования: " + Duration.ofMillis(System.currentTimeMillis() - start));
    }

    public ArrayGenerator(int size) {
        this.size = size;
        array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = 0;
        }
    }

    public Double sumArray() {
        for (int i = 0; i < array.length; i++) {
            //sum += array[i];
            trigSum += (Math.sin(array[i]) * Math.sin(array[i]) + Math.cos(array[i]) * Math.cos(array[i]));
        }
        return trigSum;
    }

    public static void initThreadArrays (ArraySumCalculator thread []) {
        int [] arr = initArray();
        int size = 100_000;
        for (int i = 0; i < 10; i++) {
            thread[i] = new ArraySumCalculator(new ArrayGenerator(size));
            for (int j = 0; j < size; j++) {
                thread[i].arrayGenerator.array[j] = arr[j + i * 100_000];
            }
        }
    }

    public static int[] initArray () {
        int arr[] = new int[1_000_000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static Double operateThreads (ArraySumCalculator thread []) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
        for (int i = 0; i < 10; i++) {
            thread[i].join();
        }
        Double total = 0d;
        for (int i = 0; i < 10; i++) {
            total += thread[i].sum;
        }
        return total;
    }
}
