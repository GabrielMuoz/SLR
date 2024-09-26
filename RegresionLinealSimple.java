public class RegresionLinealSimple {
    private double[] X;
    private double[] Y;

    public double sumXY = 0;
    public double sumX = 0;
    public double sumY = 0;
    public double sumX2 = 0;

    public int n;

    public RegresionLinealSimple(double[] X, double[] Y) {
        this.X = X;
        this.Y = Y;
        this.n = X.length;
    }

    public void calcularSumatorias() {

        for (int i = 0; i < n; i++) {
            sumXY += X[i] * Y[i];
            sumX += X[i];
            sumY += Y[i];
            sumX2 += X[i] * X[i];
        }
    }
    public double calcularCorrelacion() {
        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;
        double sumY2 = 0;
        int n = X.length;

        for (int i = 0; i < n; i++) {
            sumX += X[i];
            sumY += Y[i];
            sumXY += X[i] * Y[i];
            sumX2 += X[i] * X[i];
            sumY2 += Y[i] * Y[i];
        }

        double numerador = n * sumXY - sumX * sumY;
        double denominador = Math.sqrt((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY));

        return numerador / denominador;
    }
    public double calcularPrediccion(double x) {
        MatesDiscretas matesDiscretas = new MatesDiscretas(this);
        matesDiscretas.Operaciones();
        double b0 = matesDiscretas.getB0();
        double b1 = matesDiscretas.getB1();
        return b1 * x + b0;
    }


    public double getSumXY() {
        return sumXY;
    }

    public double getSumX() {
        return sumX;
    }

    public double getSumY() {
        return sumY;
    }

    public double getSumX2() {
        return sumX2;
    }
}
