package DAO;


import beans.HitBean;
import entity.Shots;


import javax.annotation.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class MyDAO extends AbstractDAO<Shots> implements Serializable {
    public MyDAO() {
        super(Shots.class);
    }
}
