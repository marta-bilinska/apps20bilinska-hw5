package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.Collection;

public interface IntStream {

    Double average();

    Integer max();

    Integer min();
    
    IntStream flatMap(IntToIntStreamFunction func);

    long count();

    IntStream filter(IntPredicate predicate);

    void forEach(IntConsumer action);

    IntStream map(IntUnaryOperator mapper);

    int reduce(int identity, IntBinaryOperator op);

    Integer sum();

    int[] toArray();

    Collection<? extends Integer> getValues();
}
