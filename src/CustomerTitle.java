import Enums.MediaFormats;
import Enums.Plans;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomerTitle extends Title {

    public int intIdCustomer;
    public Date dateRent;
    public Date dateReturn;

    public CustomerTitle(int code, String title, int yearRelease, String genre, String directorOrBand, MediaFormats format, Plans type, boolean rented) {
        super(code, title, yearRelease, genre, directorOrBand, format, type, rented);
    }

    public int getIntIdCustomer() {
        return intIdCustomer;
    }

    public void setIntIdCustomer(int iDCustomer) {
        intIdCustomer = iDCustomer;
    }

    public Date getDateRent() {
        return dateRent;
    }

    public String getStringDateRent() {

        return new SimpleDateFormat("dd/MM/yyyy").format(dateRent);
    }

    public void setDateRent(Date dateRent) {

        this.dateRent = dateRent;
    }

    public Date getDateReturn() {

        return dateReturn;
    }

    public String getStringDateReturn() {
        return new SimpleDateFormat("dd/MM/yyyy").format(dateReturn);
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public void rentTitle() {
        Date date = new Date();

        this.dateRent = date;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 72);
        Date currentDatePlusThree = calendar.getTime();

        this.dateReturn = currentDatePlusThree;

    }

    public void show() {
        System.out.println("Code: " + this.getCode() + "| Title: " + this.getTitle() + " | Year Release: " + this.getYearRelease() +
                " | Genre: " + this.getGenre() + " | Rented: " + this.getStringDateRent());
    }
}
