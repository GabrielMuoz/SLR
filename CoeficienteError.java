public class CoeficienteError {
    private double[] Y;
    private double[] X;
    private double b0;
    private double b1;
    private double r2;

    public CoeficienteError(double[] Y, double[] X, double b0, double b1) {
        this.Y = Y;
        this.X = X;
        this.b0 = b0;
        this.b1 = b1;
        calcularR2();
    }

    private void calcularR2() {
        double ssTotal = 0;
        double ssResiduos = 0;
        double mediaY = calcularMedia(Y);

        for (int i = 0; i < Y.length; i++) {
            double prediccion = b0 + b1 * X[i];
            ssTotal += Math.pow(Y[i] - mediaY, 2);
            ssResiduos += Math.pow(Y[i] - prediccion, 2);
        }

        r2 = 1 - (ssResiduos / ssTotal);
    }

    private double calcularMedia(double[] datos) {
        double suma = 0;
        for (double dato : datos) {
            suma += dato;
        }
        return suma / datos.length;
    }

    public void mostrarResultados() {
        double mse = calcularMSE();
        double rmse = Math.sqrt(mse);
        System.out.println("MSE: " + mse);
        System.out.println("RMSE: " + rmse);
        System.out.println("R²: " + r2);
    }

    private double calcularMSE() {
        double sumaErrorCuadrado = 0;
        for (int i = 0; i < Y.length; i++) {
            double prediccion = b0 + b1 * X[i];
            sumaErrorCuadrado += Math.pow(Y[i] - prediccion, 2);
        }
        return sumaErrorCuadrado / Y.length;
    }

    public double getR2() {
        return r2;
    }
}
