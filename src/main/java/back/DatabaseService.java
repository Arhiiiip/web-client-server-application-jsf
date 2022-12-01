//package back;
//
//import DAO.MyDAO;
//import entity.Shots;
//import org.hibernate.service.spi.ServiceException;
//
//import java.util.List;
//
//public class DatabaseService {
//    private final MyDAO dataAO = new MyDAO();
//
//    public List<Shots> getShots(){
//        try{
//            return dataAO.getAll();
//        } catch (ServiceException exception){
//            return null;
//        }
//    }
//
//    public void deleteShots(){
//        try {
//            dataAO.clear();
//        } catch (ServiceException exception){
//            exception.printStackTrace();
//        }
//    }
//
//    public void addShot(Shots shot){
//        try {
//            dataAO.addShot(shot);
//        } catch (ServiceException exception){
//            exception.printStackTrace();
//        }
//    }
//}
