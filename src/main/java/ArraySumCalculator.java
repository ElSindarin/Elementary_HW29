public class ArraySumCalculator extends Thread {
    ArrayGenerator arrayGenerator;
    Double sum;

    @Override
    public void run() {
        sum = arrayGenerator.sumArray();
    }

    public ArraySumCalculator(ArrayGenerator arrayGenerator) {
        this.arrayGenerator = arrayGenerator;
    }
}
