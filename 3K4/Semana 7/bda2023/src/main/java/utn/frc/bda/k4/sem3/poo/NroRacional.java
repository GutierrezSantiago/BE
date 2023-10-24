package utn.frc.bda.k4.sem3.poo;

public class NroRacional {

    private int num;
    private int den;

    public NroRacional() {
        super(); // opcional
    }

    public NroRacional(int num, int den) {
        this.num = num;
        setDen(den);
    }

    public NroRacional(int num) {
        this(num, 1);
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDen() {
        return den;
    }

    public void setDen(int den) {
        //if (den != 0) {
        //    this.den = den;
        //} else {
        //    den = 1;
        //}

        this.den = den != 0
                ? den
                : 1;
    }

    @Override
    public String toString() {
        return "[" + num + (den == 1 ? "" : "/" + den) + "]";
    }

}
