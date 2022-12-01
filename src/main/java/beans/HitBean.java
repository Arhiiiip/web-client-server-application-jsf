package beans;

//import DAO.MyDAO;
import DAO.MyDAO;
import back.CheckArea;
import back.Validation;
import entity.Shots;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@ManagedBean(name = "hitBean")
@SessionScoped
public class HitBean implements Serializable {
    private final Validation validation;
    private final CheckArea checkArea;

    @Inject
    MyDAO dataAO;


    public HitBean() {
        validation = new Validation();
        checkArea = new CheckArea();
    }

    private String x;
    private String y;
    private String r;

    public static Error errorX = new Error("");
    public static Error errorY = new Error("");
    public static Error errorR = new Error("");

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
        long start = System.nanoTime();

//        HitBean hitBean = new HitBean();

        System.out.println(this.x);
        System.out.println(this.y);
        System.out.println(this.r);
        double numericalX = Double.parseDouble(this.x);
        double numericalY = Double.parseDouble(this.y);
        double numericalR = Integer.parseInt(this.r);


        Shots shot = new Shots();
        shot.setX((float) numericalX);
        shot.setY((float) numericalY);
        shot.setR((float) numericalR);
        shot.setCurrentTime(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime()));
        shot.setDuration((System.nanoTime() - start) / 1000);
        shot.setResult(checkArea.hit(numericalX, numericalY, numericalR));
        dataAO.addShot(shot);

    }

    public void resetHistory() {
        dataAO.clear();
    }

    public List<Shots> getHistory() {
        return dataAO.getAll();
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
        System.out.println("r = " + this.r);
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
