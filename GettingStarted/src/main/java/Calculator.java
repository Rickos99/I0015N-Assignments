public class Calculator {
    public int add(int a, int b){
        return a + b;
    }

    public int subtract(int a, int b){
        return a - b;
    }

    public int multiply(int a, int b){
        return a * b;
    }

    public double divide(int a, int b){
        return a / (b * 1.0);
    }

    public boolean isPrime(int a){
        if (a < 2)
            return false;

        if (a == 2 || a == 3)
            return true;

        if (a % 2 == 0 || a % 3 == 0)
            return false;

        for (int i = 5; i * i <= a; i += 6)
        {
            if (a % i == 0 || a % (i + 2) == 0)
                return false;
        }

        return true;
    }
}
