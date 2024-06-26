package Controll;
import Model.m_bookhotel;
import java.sql.Date;

public class c_bookhotel {
    public void bookhotel(String username, String hotelname, String no_persons, Date checkindate, Date checkoutdate, String AC,
                            String number, String phone, double cost){
        m_bookhotel bh = new m_bookhotel();
        bh.bookhotel(username, hotelname, no_persons, checkindate, checkoutdate, AC, number, phone, cost);
        
    }
}
