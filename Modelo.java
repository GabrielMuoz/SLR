public class Modelo {
    private MatesDiscretas matesDiscretas;

    public Modelo(MatesDiscretas matesDiscretas) {
        this.matesDiscretas = matesDiscretas;
    }


    public String getEcuacion() {
        double m = matesDiscretas.getB1();
        double b = matesDiscretas.getB0();

        return "La ecuación es: y = " + m + "x + " + b;
    }
}
