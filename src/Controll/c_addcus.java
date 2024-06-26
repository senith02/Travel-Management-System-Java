package Controll;
import Model.m_addcus;
public class c_addcus {
    public void addcus(String username,String id,String number,String name,String gender,
             String country,String address,String phone,String email){
        m_addcus ad1 = new m_addcus();
        ad1.addCustomer(username, id, number, name, gender, country, address, phone, email);
    }
}
