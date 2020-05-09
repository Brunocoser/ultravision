import Enums.MediaFormats;
import Enums.Plans;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomerTitle extends title {

    public int IDCustomer;
    public Date DateRent;
    public Date DateReturn;

    public CustomerTitle(int Code, String Title, int YearRelease, String Genre, String DirectorOrBand, MediaFormats format, Plans type, boolean rented) {
        super(Code, Title, YearRelease, Genre, DirectorOrBand, format, type, rented);
    }

    public int getIDCustomer(){

        return IDCustomer;
    }
    public void setIDCustomer(int iDCustomer){

        IDCustomer = iDCustomer;
    }
    public Date getDateRent(){

        return DateRent;
    }
    public String getStringDateRent(){

        return new SimpleDateFormat("dd/MM/yyyy").format(DateRent);
    }
    public void setDateRent(Date dateRent){

        DateRent = dateRent;
    }
    public Date getDateReturn(){

        return DateReturn;
    }

    public String getStringDateReturn(){
        return new SimpleDateFormat("dd/MM/yyyy").format(DateReturn);
    }

    public void setDateReturn(Date dateReturn){
        DateReturn = dateReturn;
    }
    public void rentTitle(){
        Date date = new Date();

        this.DateRent = date;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 72);
        Date currentDatePlusThree = calendar.getTime();

        this.DateReturn = currentDatePlusThree;

    }
    public void show (){
        System.out.println("Code: " + this.getCode() + "| Title: " + this.getTitle() + " | Year Release: " + this.getYearRelease() +
                " | Genre: " + this.getGenre() + " | Rented: " + this.getStringDateRent());
    }
}
