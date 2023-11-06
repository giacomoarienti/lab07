package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private final List<T> list;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] array, final Predicate<T> filter) {
        this.list = List.of(array);
        this.filter = filter;
    }

    public IterableWithPolicyImpl(final T[] array) {
        this(array, new Predicate<T>() {
            @Override
            public boolean test(T arg0) {
                return true;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.filter = filter;
    }
    
    private class IteratorImpl implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            // if index is in bound
            if(this.index < IterableWithPolicyImpl.this.list.size()) {
                // check if next element passes the filter
                final T value = IterableWithPolicyImpl.this.list.get(this.index);
                if(IterableWithPolicyImpl.this.filter.test(value)) {
                    return true;
                }
                // if not, skip the element and check the following
                this.index += 1;
                return this.hasNext();
            }
            return false;
        }

        @Override
        public T next() {
            if(!this.hasNext()) {
                throw new NoSuchElementException();
            }
            final T value = IterableWithPolicyImpl.this.list.get(this.index);
            this.index += 1;
            return value;
        }

    }
}
