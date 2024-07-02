package CoreJava.q1;

// This class is not a pure function as it dependes on instance variable rate
// public class TaxUtil {
//     double rate = 0.15;

//     public double calculateTax(double amount) {
//         return amount * rate;
//     }
// }

// This class is a pure function because it depends only on its parameters
public class TaxUtil {

    public double calculateTax(double amount, double rate) {
        return amount * rate;
    }
}
