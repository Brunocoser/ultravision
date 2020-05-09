import Enums.Plans;
import Interfaces.ICustomer;

import java.util.ArrayList;

public class Customer implements ICustomer {

    private int intId;
    private String strName;
    private String strCardNumber;
    private String strBirthday;
    private Plans subPlan;
    private int points;
    private boolean freeRentAllowed;
    private ArrayList<CustomerTitle> TitlesRented; //MAKE IT FINAL?

    public Customer (int intId, String strName, String strCardNumber, String strBirthday, Plans SubscriptionPlan, int Points){
        this.intId = intId;
        this.strName = strName;
        this.strCardNumber = strCardNumber;
        this.strBirthday = strBirthday;
        this.subPlan = SubscriptionPlan;
        this.TitlesRented = new ArrayList<CustomerTitle>();
    }

    public int getIntId() {
        return intId;
    }

    public void setIntId(int iD) {
        intId = iD;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrCardNumber() {
        return strCardNumber;
    }

    public void setStrCardNumber(String strCardNumber) {
        this.strCardNumber = strCardNumber;
    }

    public String getStrBirthday() {
        return strBirthday;
    }

    public void setStrBirthday(String strBirthday) {
        this.strBirthday = strBirthday;
    }

    public Plans getSubPlan() {
        return subPlan;
    }

    public void setSubPlan(Plans subPlan) {
        this.subPlan = subPlan;
    }

    public ArrayList<CustomerTitle> getArrayTitlesRented(){
        return TitlesRented;
    }

    public void addArrayTitlesRented(CustomerTitle title){
        TitlesRented.add(title);
    }

    public void addPoints(int points){
        this.points += points;
        setRentAllowed();
    }

    public boolean availFreeRent(){
        if(this.isFreeRentAllowed()){
            this.points -= 100;
            setRentAllowed();
            return true;
        }else {
            return false;
        }
    }

    private void setRentAllowed(){
        if (this.points >= 100){
            this.freeRentAllowed = true;
        }else {
            this.freeRentAllowed = false;
        }
    }

    public int getPoints(){
        return points;
    }

    public boolean isFreeRentAllowed(){
        return freeRentAllowed;
    }

    public void showCustomersDetails(){
        System.out.println("ID: " + this.intId + " | Name: " + this.strName + " | Date of Birthday: " + this.strBirthday + "| Subs Plan: " + this.subPlan + " Points: " + this.getPoints());

    }

}
