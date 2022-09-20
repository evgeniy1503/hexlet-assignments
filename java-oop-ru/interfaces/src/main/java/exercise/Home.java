package exercise;

// BEGIN
public interface Home {

    double getArea();

    String toString();

    default int compareTo(Home another){

        if (this.getArea() > another.getArea()) {
            return 1;
        } else if (this.getArea() < another.getArea()) {
            return -1;
        } else {
            return 0;
        }

    }
}
// END
