package hw19;

import java.util.Arrays;

public class ValueCalculator {
    private float[] array;

    public float[] getArray () {
        return array;
    }
    public int getArraySize() {
        return array.length;
    }
    public int getHalfArraySize() {
        return array.length / 2;
    }
    public void calculateValues() {
        long start = System.currentTimeMillis();
        Arrays.fill(array, 1.0f);
        int half = array.length / 2;
        float[] a1 = new float[half];
        float[] a2 = new float[half];
        System.arraycopy (array, 0, a1, 0, half);
        System.arraycopy (array, half, a2, 0, half);
        Thread thread1 = new Thread (() -> {
            for (int i = 0; i < half; i++) {
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread thread2 = new Thread (() -> {
            for (int i = 0; i < half; i++) {
                a2[i] = (float) (a2[i] * Math.sin(0.2f + (i + half) / 5) * Math.cos(0.2f + (i + half) / 5) *
                        Math.cos(0.4f + (i + half) / 2));
            }
        });
        thread1.start ();
        thread2.start ();
        try {
            thread1.join ();
            thread2.join ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        System.arraycopy (a1, 0, array, 0, half);
        System.arraycopy (a2, 0, array, half, half);
        long end = System.currentTimeMillis ();
        long elapsedTime = end - start;
        System.out.println ("Time elapsed: " + elapsedTime + " milliseconds");
    }

    public void setArray (float[] array) {
        this.array = array;
    }
}


