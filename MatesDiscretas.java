public class MatesDiscretas {
    private RegresionLinealSimple regresion;

    public double b1;
    public double b0;

    public double promX;
    public double promY;

    public MatesDiscretas(RegresionLinealSimple regresion) {
        this.regresion = regresion;
    }

    public double getB1() {
        return b1;
    }

    public double getB0() {
        return b0;
    }

    public void Operaciones() {


        double sumatoriaX = regresion.getSumX();
        double sumatoriaX2 = regresion.getSumX2();
        double sumatoriaXY = regresion.getSumXY();
        double sumatoriaY = regresion.getSumY();


        promX = sumatoriaX / regresion.n;
        promY = sumatoriaY / regresion.n;

        b1 = (sumatoriaXY - ((sumatoriaX * sumatoriaY) / regresion.n)) /
                (sumatoriaX2 - ((sumatoriaX * sumatoriaX) / regresion.n));
        b0 = promY - (b1 * promX);

        Modelo model = new Modelo(this);
        model.getEcuacion();
    }
}