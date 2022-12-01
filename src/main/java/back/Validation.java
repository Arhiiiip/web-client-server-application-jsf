package back;

import beans.HitBean;

import java.io.Serializable;

public class Validation implements IValidation, Serializable {
    @Override
    public Double validateX(String x) {
        Double doubleX = null;
        try {
            doubleX = Double.parseDouble(x);
            if (doubleX > 7 || doubleX < -7) {
                doubleX = null;
                HitBean.errorX = new Error("Поле X должно быть от -7 до 7.");
            }
        } catch (NumberFormatException exception) {
            HitBean.errorX = new Error("Поле X должно быть числом.");
        } catch (NullPointerException exception) {
            HitBean.errorX = new Error("Поле X не введено.");
        }
        if (doubleX != null) {
            HitBean.errorX = new Error("");
        }
        return doubleX;
    }

    @Override
    public Double validateY(String y) {
        Double doubleY = null;
        try {
            doubleY = Double.parseDouble(y);
            if (doubleY > 5 || doubleY < -5) {
                doubleY = null;
                HitBean.errorY = new Error("Поле Y должно быть от -5 до 5.");
            }
        } catch (NumberFormatException exception) {
            if (y.equals("")) {
                HitBean.errorY = new Error("Поле Y не введено.");
            } else {
                HitBean.errorY = new Error("Поле Y должно быть числом.");
            }
        } catch (NullPointerException ignored) {}
        if (doubleY != null) {
            HitBean.errorY = new Error("");
        }
        return doubleY;
    }

    @Override
    public Double validateR(String r) {
        Double dR = null;
        if (r == "") {
            HitBean.errorR = new Error("Поле R не введено.");
            return dR;
        }
        try {
            dR = Double.parseDouble(r);
            if (dR > 3 || dR < 1) {
                dR = null;
                HitBean.errorR = new Error("Поле R должно быть от 1 до 3.");
            }
        } catch (NumberFormatException exception) {
            HitBean.errorR = new Error("Поле R должно быть числом.");
        } catch (NullPointerException exception) {
            HitBean.errorR = new Error("Поле R не введено.");
        }
        if (dR != null) {
            HitBean.errorR = new Error("");
        }
        return dR;
    }
}
