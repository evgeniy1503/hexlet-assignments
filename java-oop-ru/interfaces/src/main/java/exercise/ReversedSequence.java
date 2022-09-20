package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {

    String reversString;

    public ReversedSequence(String str) {
        this.reversString = new StringBuilder(str).reverse().toString();
    }

    @Override
    public int length() {
        return this.reversString.length();
    }

    @Override
    public char charAt(int i) {
        return this.reversString.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return this.reversString.subSequence(i, i1);
    }

    public String toString() {
        return this.reversString;
    }
}
// END
