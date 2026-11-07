import java.util.*;

public class laba2_1
{
    static int N = 1000;
    short[] ARR;
    void main(String[] args)
    {
        ARR = GetArray(N, (short) 12030, (short) 32703);
        long t1 = System.nanoTime();
        HoareSort(ARR, 0, N - 1);
        long t2 = System.nanoTime();
        PrintTime((t2 - t1));
        ARR = GetArray(N, (short) 12030, (short) 32703);
        t1 = System.nanoTime();
        FastSort(ARR);
        t2 = System.nanoTime();
        PrintTime((t2 - t1));
        ARR = GetArray(N, (short) 12030, (short) 32703);
        t1 = System.nanoTime();
        SelectSort(ARR);
        t2 = System.nanoTime();
        PrintTime((t2 - t1));
    }

    private void PrintTime(long t)
    {
        IO.println((double)t / 1_000_00);
    }

    private void SelectSort(short[] arr)
    {
        for (int i = 0; i < arr.length; i++) {
            int pos = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[pos] > arr[j])
                {
                    pos = j;
                }
            }
            short t = arr[i];
            arr[i] = arr[pos];
            arr[pos] = t;
        }
    }

    private void FastSort(short[] arr)
    {
        if(arr.length <= 1) return;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        stack.push(arr.length - 1);

        while (!stack.isEmpty())
        {
            int high = stack.pop();
            int low = stack.pop();

            short pivot = arr[high];
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (arr[j] <= pivot)
                {
                    i++;
                    swap(arr, i, j);
                }
            }

            swap(arr, i + 1, high);
            if(i > low)
            {
                stack.push(low);
                stack.push(i);
            }
            if(i + 2 < high)
            {
                stack.push(i + 2);
                stack.push(high);
            }
        }
    }

    private void HoareSort(short[] arr, int low, int high)
    {
        if(low >= high){ return; }
        int pivot = arr[low + (high - low) / 2];

        int i = low;
        int j = high;

        while (i <= j)
        {
            while (arr[i] < pivot)
            {
                i++;
            }
            while (arr[j] > pivot)
            {
                j--;
            }
            if (i <= j)
            {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        if(low < j)
        {
            HoareSort(arr, low, j);
        }
        if(high > i)
        {
            HoareSort(arr, i, high);
        }
    }

    private void swap(short[] arr, int i, int j)
    {
        short temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private short[] GetArray(int n, short a, short b)
    {
        Random r = new Random();
        short[] t = new short[n];
        for(int i = 0; i < n; i++)
        {
            t[i] = (short)r.nextInt(a, b);
        }
        return t;
    }

    private boolean CheckArray(short[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++) {
            if(arr[i] > arr[i + 1]) return false;
        }
        return true;
    }
}
