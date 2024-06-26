package Controll;
import Model.m_bookpackage;
public class c_bookpackage {
    public void bookpackage(String username, String package_name, String no_persons, String id,
                            String number, String phone, double cost){
        m_bookpackage bh = new m_bookpackage();
        bh.bookpackage(username, package_name, no_persons, id, number, phone, cost);
        
    }
}
