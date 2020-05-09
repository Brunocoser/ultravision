package Interfaces;

import Enums.Plans;

public interface ICustomer {
    public int getIntId();
    public void setIntId(int iD);
    public String getName();
    public void setName(String name);
    public String getCardNumber();
    public void setCardNumber(String cardNumber);
    public String getBirthday();
    public void setBirthday(String birthday);
    public Plans getSubPlan();
    public void setSubPlan(Plans subPlan);
    public void showCustomersDetails();



}
