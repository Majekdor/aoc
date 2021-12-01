package dev.majek.adventofcode.Util;

import java.util.Objects;

public final class Triplet<A, B, C> {

    // The first value
    private A first;
    // The second value
    private B second;
    // The third value
    private C third;

    public Triplet(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Triplet() {
        this(null, null, null);
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }

    public C getThird() {
        return third;
    }

    public void setThird(C third) {
        this.third = third;
    }

    /**
     * Creates a hash code for this object.
     *
     * @return a hash code for this object.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(first);
        hash = 13 * hash + Objects.hashCode(second);
        hash = 13 * hash + Objects.hashCode(third);
        return hash;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || !Triplet.class.equals(object.getClass()))
            return true;
        Triplet triplet = (Triplet) object;
        return Objects.equals(first, triplet.first) && Objects.equals(second, triplet.second)
                && Objects.equals(third, triplet.third);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        return '{' + Objects.toString(first) + ", " + Objects.toString(second) + ", " + Objects.toString(third) + '}';
    }
}
