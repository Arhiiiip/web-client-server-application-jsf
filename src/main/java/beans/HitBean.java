package beans;

//import DAO.MyDAO;
import DAO.MyDAO;
import back.CheckArea;
import back.Validation;
import data.MyError;
import entity.Shots;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@ManagedBean(name = "hitBean")
@SessionScoped
public class HitBean implements Serializable {
    private final Validation validation;
    private final CheckArea checkArea;

    private String isR = "Not enter";


    @Inject
    MyDAO dataAO;


    public HitBean() {
        validation = new Validation();
        checkArea = new CheckArea();
    }

    private String x;
    private String y;
    private String r;

    public static MyError errorX = new MyError("");
    public static MyError errorY = new MyError("");
    public static MyError errorR = new MyError("");

    private boolean correctX = false;
    private boolean correctY = false;
    private boolean correctR = false;

    public void submit() {
        long start = System.nanoTime();

        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("r = " + r);

        Double numericalX = validation.validateX(x);
        Double numericalY = validation.validateY(y);
        Double numericalR = validation.validateR(r);

        correctX = (numericalX != null);
        correctY = (numericalY != null);
        correctR = (numericalR != null);

        if (correctX && correctY && correctR) {
            Shots shot = new Shots();
            shot.setX(numericalX.floatValue());
            shot.setY(numericalY.floatValue());
            shot.setR(numericalR.floatValue());
            shot.setCurrentTime(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime()));
            shot.setDuration((System.nanoTime() - start) / 1000);
            shot.setResult(checkArea.hit(numericalX, numericalY, numericalR));
            dataAO.addShot(shot);
        }
    }

    public void submitClick() {
        System.out.println("Start sC");

        long start = System.nanoTime();

        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("r = " + r);

        double numericalX = Double.parseDouble(x);
        double numericalY = Double.parseDouble(y);
        double numericalR = Double.parseDouble(r);

        Shots shot = new Shots();
        shot.setX((float) numericalX);
        shot.setY((float) numericalY);
        shot.setR((float) numericalR);
        shot.setCurrentTime(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime()));
        shot.setDuration((System.nanoTime() - start) / 1000);
        shot.setResult(checkArea.hit(numericalX, numericalY, numericalR));
        dataAO.addShot(shot);
        System.out.println("Fnsh sC");
    }

    public void resetHistory() {
        dataAO.clear();
    }

    public List<Shots> getHistory() {
        List<Shots> sL = dataAO.getAll();
        Collections.reverse(sL);
        return sL;
    }



    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
        isR = this.r;
        System.out.println("r = " + this.r);
    }

    public String getIsR() {
        return isR;
    }

    public void setIsR(String isR) {
        this.isR = isR;
    }

    public boolean getCorrectX() {
        return correctX;
    }

    public boolean getCorrectY() {
        return correctY;
    }

    public Object getErrorR() {
        return errorR;
    }

    public Object getErrorY() {
        return errorY;
    }

    public Object getErrorX() {
        return errorX;
    }
}
