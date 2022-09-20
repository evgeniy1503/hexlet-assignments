package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle circle) {
        try {
            double getSquare = circle.getSquare();
            int square = (int)Math.round(getSquare);
            System.out.println(square);
        } catch (NegativeRadiusException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
// END
