package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntBinaryOperator;
import ua.edu.ucu.function.IntConsumer;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;
import java.util.LinkedList;

public class AsIntStream implements IntStream {
    private LinkedList<Integer> values = new LinkedList<>();
    private final int size;

    private AsIntStream() {
        this.size = 0;
    }

    private AsIntStream(LinkedList<Integer> list) {
        this.values = list;
        this.size = list.size();
    }

    private AsIntStream(int[] arr) {
        this.size = arr.length;
        for (int j : arr) {
            this.values.add(j);
        }
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    private void checkIfEmpty() {
        if (isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Double average() {
        checkIfEmpty();
        return (sum() / (double) this.size);
    }

    @Override
    public Integer max() {
        return extreme((int) Double.NEGATIVE_INFINITY, 1);
    }

    @Override
    public Integer min() {
        return extreme((int) Double.POSITIVE_INFINITY, -1);
    }

    private Integer extreme(int initialValue, int comparator) {
        checkIfEmpty();
        int extreme = initialValue;
        for (int i = 0; i < this.size; i++) {
            Integer value = this.values.get(i);
            if (Double.compare(value, extreme) == comparator) {
                extreme = value;
            }
        }
        return extreme;
    }

    public LinkedList<Integer> getValues() {
        return (LinkedList<Integer>) this.values.clone();
    }

    @Override
    public long count() {
        return this.size;
    }

    @Override
    public Integer sum() {
        checkIfEmpty();
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        LinkedList<Integer> resultList = new LinkedList<>();
        for (Integer value : this.values) {
            if (predicate.test(value)) {
                resultList.add(value);
            }
        }
        return new AsIntStream(resultList);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (Integer value : this.values) {
            action.accept(value);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        LinkedList<Integer> resultList = new LinkedList<>();
        for (Integer value : this.values) {
            resultList.add(mapper.apply(value));
        }
        return new AsIntStream(resultList);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        LinkedList<Integer> result = new LinkedList<>();
        for (int value : this.values) {
            result.addAll(func.applyAsIntStream(value).getValues());
        }
        return new AsIntStream(result);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (int value : this.values) {
            result = op.apply(result, value);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = this.values.get(i);
        }
        return result;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

}
