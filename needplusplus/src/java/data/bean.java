/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.*;

/**
 *
 * @author Merve
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@ApplicationScoped

/**
 * Class to keep the user information
 */
public class bean {
    String name;
    String surname;
    String birthday;
    String email;
    String userName;
    String password;
    String country;
    String city;
    String district;
    String titleOfNeed;
    int numberOfNeed;
    String definition;
    String address;
    private Connection conn;
    String dburl="jdbc:derby://localhost:1527/Need";
    String user="ari";
    String pass="need";
    public List<NeedPostInformations> needPostList;
    int sizeOfActiveNeeds;

    /**
     * No parameter constructor 
     * @return 
     */
    public bean()
    {}
    
    /**
     * Constructor for testing
     * Initializing the below elements
     * @param name
     * @param surname
     * @param birthday
     * @param email
     * @param userName
     * @param password
     * @param country
     * @param city
     * @param district
     * @param address 
     * @param titleOfNeed 
     * @param numberOfNeed 
     * @param definition 
     * @param sizeOfActiveNeed 
     */
    public bean(String name,String surname, String birthday,String email,String userName,String password,String country,
            String city,String district,String address, String titleOfNeed, int numberOfNeed, String definition,
            int sizeOfActiveNeed)
    {
        this.address=address;
        this.birthday= birthday;
        this.city=city;
        this.country=country;
        this.district=district;
        this.email=email;
        this.name=name;
        this.password=password;
        this.surname= surname;
        this.userName=userName;
        this.titleOfNeed= titleOfNeed;
        this.numberOfNeed=numberOfNeed;
        this.definition=definition;
        this.sizeOfActiveNeeds=sizeOfActiveNeed;
        
    }
    
    /* Getter and Setter Methods */
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
    
    public int getSizeOfActiveNeeds() {
        return sizeOfActiveNeeds;
    }

    public void setSizeOfActiveNeeds(int sizeOfActiveNeeds) {
        this.sizeOfActiveNeeds = sizeOfActiveNeeds;
    }
    
     public String getTitleOfNeed() {
        return titleOfNeed;
    }
    
    public void setTitleOfNeed(String titleOfNeed) {
        this.titleOfNeed = titleOfNeed;
    }
     
    public List<NeedPostInformations> getNeedPostList() {
        return needPostList;
    }

    public void setNeedPostList(List<NeedPostInformations> needPostList) {
        this.needPostList = needPostList;
    }

    public int getNumberOfNeed() {
        return numberOfNeed;
    }

    public void setNumberOfNeed(int numberOfNeed) {
        this.numberOfNeed = numberOfNeed;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public Connection Connect(){
      System.out.println("Connection test...");
        try{
        
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        System.out.println("Connection is successful.");
        conn=DriverManager.getConnection(dburl,user,pass);
        
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
            System.out.println("Connection failed.");
        }
        return conn;
            
    }
    
    public String save(){
         
        PreparedStatement ps=null;
        Connection con=null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/Need","ari","need");
            ps=con.prepareStatement("INSERT INTO userinformation(NAME, SURNAME, BIRTHDATE, EMAIL,USERNAME,PASSWORD,COUNTRY,CITY,DISTRICT) VALUES(?,?,?,?,?,?,?,?,?)");
            
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, birthday);
            ps.setString(4, email);
            ps.setString(5, userName);
            ps.setString(6, password);
            ps.setString(7, country);
            ps.setString(8, city);
            ps.setString(9, district);

            ps.executeUpdate();  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally 
        {
            try{
            con.close();
            ps.close();
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
        }
        return "homepage?faces-redirect=true";
    }
     
    /**
     * Method to check the username and password pair
     * @param kullaniciAdi
     * @param sifre
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean userControl(String kullaniciAdi ,String sifre) throws ClassNotFoundException, SQLException{
         Connection conn=null;
         if(conn==null)
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn=DriverManager.getConnection("jdbc:derby://localhost:1527/Need","ari","need");
        }
        try {
            System.out.println("Conditions...");
            if(conn!=null){ 
                Statement stmt=conn.createStatement();

                System.out.println("Username\t Password\t Name\t Telephone");

                ResultSet rs=stmt.executeQuery("select password from userinformation where username='"+kullaniciAdi+"'");
                rs.next(); 
                return sifre.equals(rs.getString("password"));
            }
        }
        catch(SQLException e)
        {}
        return false;  
    }
    
    /**
     * Method to check if the password is correct or not
     * If correct, directs user to homepage
     * if not, asks again 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public String passwordCheck() throws ClassNotFoundException, SQLException{
        
        if(this.userControl(userName, password) ==true)
        {
            return "homepage?faces-redirect=true";
        }
        else
        {
            return  "wrongPassword?faces-redirect=true";
        }        
    }
    
    /**
     * Method to add Need 
     * @return homepage if the adding action is successful
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public String AddNeed() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        
        PreparedStatement ps=null;
        Connection con=null;

        try{

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/Need","ari","need");

            Statement stmt = con.createStatement();
            ResultSet rs;
            System.out.println("add need metodundayım");
            System.out.println(getName());
            rs = stmt.executeQuery("SELECT username FROM USERINFORMATION WHERE username='"+getUserName()+"' AND password='"+getPassword()+"'");
            int type = rs.getType();
            System.out.print(type);
              
    
            String idkisix = null;
            while ( rs.next() ) {
                System.out.print("dplsdcğspl");
                idkisix = rs.getString("username");
                 System.out.print(idkisix);

            }

            ps=con.prepareStatement("INSERT INTO NEEDPOSTINFORMATION(TITLEOFNEED,NUMBEROFNEED,DEFINITION,ADDRESS,USERNAME) VALUES (?,?,?,?,?)");

            ps.setString(1, titleOfNeed);
            ps.setInt(2, numberOfNeed);
            ps.setString(3, definition);
            ps.setString(4, address);
            System.out.print(idkisix);
            ps.setString(5, idkisix);


            ps.executeUpdate();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }

        return "homepage?faces-redirect=true";
    }
    
    
    /**
     * This method returns a list structure which is type of NeedPostInformations object
     * Takes values from database table for need posts, and assigns them into a list
     * @return NeedPostInformation list
     */
    public List<NeedPostInformations> getNeedTable() {
        PreparedStatement ps=null;
        
        try {
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");//Hangi türde bir veri tabanını kullanacağını bildiriyoruz.
            conn=DriverManager.getConnection("jdbc:derby://localhost:1527/Need","ari","need");//Bağlanacağı veri tabanını ve kullanacağı kullanıcı adı-parolayı bildiriyoruz.
            ps = conn.prepareStatement("SELECT * FROM NEEDPOSTINFORMATION");//need tablosundaki herşeyi çek diyoruz.
            ResultSet rs = ps.executeQuery();//SQL Sorgusundan dönecek sonuç rs sonuç kümesi içinde tutulacak.
            List<NeedPostInformations> liste=new ArrayList<>();//AdiAlani sınıfı tipinde liste tanımladık çünkü SQL Sorgusundan dönecek sonuç içindeki Adi Alani kısmına bu tiple ulaşacaz.
            while(rs.next())//Kayıt olduğu sürece her işlem sonunda 1 satır atla.
            {
                NeedPostInformations aa=new NeedPostInformations();//SQL Sorgusundan sütunları çekip bu değişkenin içinde Adı veya Alani kısmına atıyacağız.
                aa.setNeedTitle(rs.getString("titleofneed")); //ResultSet içinden o anki indisdeki "Adi" anahtar kelimesine karşı gelen değer alınıyor.
                aa.setNeedNumber(rs.getInt("numberofneed"));
                aa.setNeedDefinition(rs.getString("definition")); //ResultSet içinden o anki indisdeki "Alani" anahtar kelimesine karşı gelen değer alınıyor.
                aa.setNeedAddress(rs.getString("address"));
                
                liste.add(aa);// Add every value from table to the liste.
                
            }
            setNeedPostList(liste);
            this.sizeOfActiveNeeds=liste.size();
            System.out.println("fonsksiyon icindeki"+sizeOfActiveNeeds);
            setSizeOfActiveNeeds(liste.size());
            return liste;//Return liste.
        } 
        catch (ClassNotFoundException | SQLException exception) {
            System.out.println("An error occured! :"+exception);
            return null;
        }
        finally{ //try'a da düşse catch'e de bu bloktaki kod çalıştırılacak.
            try {
                if(conn!=null){ //Connection nesnesi belki yukarıda null kalmış olabilir. Kontrol etmekte fayda var.
                    conn.close();
                }
                if(ps!=null){ //PreparedStatement nesnesi yukarıda null kalmış olabilir. Kontrol etmekte fayda var.
                    ps.close();
                }
            } catch (SQLException sqlException) {
                System.out.println("An error occured! :"+sqlException);
            }
        }
    }
           
    /**
     * Method for editing profile
     * checks for username
     * @return 
     */
    public String edit(){
         
        PreparedStatement ps=null;
        Connection con=null;
                
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/Need","ari","need");
            
            //ps=con.prepareStatement("INSERT INTO userinformation(NAME, SURNAME, BIRTHDATE, EMAIL, USERNAME, PASSWORD, COUNTRY,CITY,DISTRICT) VALUES(?,?,?,?,?,?,?,?,?)");
            ps= con.prepareStatement("update userinformation set NAME=?, SURNAME=?, BIRTHDATE=?, EMAIL=?, PASSWORD=?, COUNTRY=?, CITY=?, DISTRICT=? where USERNAME='" +getUserName()+"'");
            
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, birthday);
            ps.setString(4, email);            
            ps.setString(5, password);
            ps.setString(6, country);
            ps.setString(7, city);
            ps.setString(8, district);            
            //ps.setString(9, oldUserName);            
            
            ps.executeUpdate();  
        }
        
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally 
        {
            try{
            con.close();
            ps.close();
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
        }
        //System.out.print("HEEEYYY");       
        return "homepage?faces-redirect=true";
    }
    
    
    public static void main(String arg[]) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        bean dbka=new bean("elif", "keles", "01.01.1901", "elif@hotmail.com", "elif1", "pass123", "tr", "kocaeli", "gebze", "simpleAddress", "Needtitle", 5, "SimpleDefinition", 0);
        dbka.Connect();
        dbka.getNeedTable();
        System.out.print("Active need number: ");
        System.out.print(dbka.getSizeOfActiveNeeds() + "\n" ); 
        // A lil change for testing.. ok, Now push this commit, 
        // dbka.setUserName("lol");
        //dbka.setName("evrem");
        //dbka.setPassword("dur");
        dbka.AddNeed();
        //dbka.save();
        //int i=dbka.getNeedTable().size();
        //System.out.println(dbka.getNeedPostList().get(1).getNeedTitle());
        //System.out.println(i);
            
        
        
        //dbka.kullaniciListesi();
        //dbka.sifreKontrol();      
        
        // System.out.println(dbka.sifreKontrol());
      
    }
        
}
