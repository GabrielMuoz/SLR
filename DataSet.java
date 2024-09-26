import java.util.Arrays;

public class DataSet {

    public double[] X = {108, 115, 106, 97, 95, 91, 97, 83, 83, 78, 54, 67, 56, 53, 61, 115, 81, 78, 30, 45, 99, 32, 25, 28, 90, 89};
    public double[] Y = {95, 96, 95, 97, 93, 94, 95, 93, 92, 86, 73, 80, 65, 69, 77, 96, 87, 89, 60, 63, 95, 61, 55, 56, 94, 93};

    private RegresionLinealSimple mejorModelo;
    private double mejorR2 = -1;
    private CoeficienteError mejorCoeficienteError;

    public double[] getX() {
        return X;
    }

    public double[] getY() {
        return Y;
    }

    public void seleccionarMejorModelo() {
        System.out.println("Iniciando la selección del mejor modelo...\n");
        DataSetMetodo();
        DataSet7030();
        DataSet3070();


        if (mejorCoeficienteError != null) {
            System.out.println("\nMejor coeficiente de determinación (R²): " + mejorCoeficienteError.getR2());
            System.out.println("Coeficiente de correlación (r) del mejor modelo: " + mejorModelo.calcularCorrelacion());
        }

        realizarPredicciones();
    }

    public void DataSetMetodo() {
        System.out.println("----- Procesando conjunto de datos completo (100%) -----");
        RegresionLinealSimple regresion = new RegresionLinealSimple(getX(), getY());
        regresion.calcularSumatorias();

        MatesDiscretas matesDiscretas = new MatesDiscretas(regresion);
        matesDiscretas.Operaciones();

        Modelo modelo = new Modelo(matesDiscretas);
        System.out.println(modelo.getEcuacion());

        CoeficienteError coeficienteError = new CoeficienteError(Y, X, matesDiscretas.getB0(), matesDiscretas.getB1());
        coeficienteError.mostrarResultados();

        double correlacion = regresion.calcularCorrelacion();
        System.out.println("Coeficiente de correlación (r): " + correlacion);

        if (coeficienteError.getR2() > mejorR2) {
            mejorR2 = coeficienteError.getR2();
            mejorModelo = regresion;
            mejorCoeficienteError = coeficienteError;
        }
    }

    public void DataSet7030() {
        System.out.println("----- Procesando conjunto de datos 70%-30% -----");
        int n = X.length;
        int trainSize = (int) (n * 0.7);
        double[] X_train = Arrays.copyOfRange(X, 0, trainSize);
        double[] Y_train = Arrays.copyOfRange(Y, 0, trainSize);
        double[] X_test = Arrays.copyOfRange(X, trainSize, n);
        double[] Y_test = Arrays.copyOfRange(Y, trainSize, n);

        RegresionLinealSimple regresion = new RegresionLinealSimple(X_train, Y_train);
        regresion.calcularSumatorias();

        MatesDiscretas matesDiscretas = new MatesDiscretas(regresion);
        matesDiscretas.Operaciones();

        Modelo modelo = new Modelo(matesDiscretas);
        System.out.println(modelo.getEcuacion());

        CoeficienteError coeficienteError = new CoeficienteError(Y_test, X_test, matesDiscretas.getB0(), matesDiscretas.getB1());
        coeficienteError.mostrarResultados();

        double correlacion = regresion.calcularCorrelacion();
        System.out.println("Coeficiente de correlación (r): " + correlacion);

        if (coeficienteError.getR2() > mejorR2) {
            mejorR2 = coeficienteError.getR2();
            mejorModelo = regresion;
            mejorCoeficienteError = coeficienteError;
        }
    }

    public void DataSet3070() {
        System.out.println("----- Procesando conjunto de datos 30%-70% -----");
        int n = X.length;
        int trainSize = (int) (n * 0.3);
        double[] X_test = Arrays.copyOfRange(X, 0, trainSize);
        double[] Y_test = Arrays.copyOfRange(Y, 0, trainSize);
        double[] X_train = Arrays.copyOfRange(X, trainSize, n);
        double[] Y_train = Arrays.copyOfRange(Y, trainSize, n);

        RegresionLinealSimple regresion = new RegresionLinealSimple(X_train, Y_train);
        regresion.calcularSumatorias();

        MatesDiscretas matesDiscretas = new MatesDiscretas(regresion);
        matesDiscretas.Operaciones();

        Modelo modelo = new Modelo(matesDiscretas);
        System.out.println(modelo.getEcuacion());

        CoeficienteError coeficienteError = new CoeficienteError(Y_test, X_test, matesDiscretas.getB0(), matesDiscretas.getB1());
        coeficienteError.mostrarResultados();

        double correlacion = regresion.calcularCorrelacion();
        System.out.println("Coeficiente de correlación (r): " + correlacion);

        if (coeficienteError.getR2() > mejorR2) {
            mejorR2 = coeficienteError.getR2();
            mejorModelo = regresion;
            mejorCoeficienteError = coeficienteError;
        }
    }

    public void realizarPredicciones() {
        if (mejorModelo == null) {
            System.out.println("No se ha seleccionado ningún modelo para predicciones.");
            return;
        }

        double[] nuevosValoresX = {116, 136, 112, 123, 200};
        double[] predicciones = new double[nuevosValoresX.length];

        System.out.println("\nPredicciones con el mejor modelo (R² más alto):");
        for (int i = 0; i < nuevosValoresX.length; i++) {
            predicciones[i] = mejorModelo.calcularPrediccion(nuevosValoresX[i]);
            System.out.println("Predicción para X = " + nuevosValoresX[i] + ": " + predicciones[i]);
        }


        System.out.println("\nCoeficientes de error del mejor modelo:");
        mejorCoeficienteError.mostrarResultados();
    }
}
