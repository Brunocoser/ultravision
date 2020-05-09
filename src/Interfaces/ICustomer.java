package Interfaces;

import Enums.Plans;

public interface ICustomer {
    public int getIntId();
    public void setIntId(int iD);
    public String getStrName();
    public void setStrName(String strName);
    public String getStrCardNumber();
    public void setStrCardNumber(String strCardNumber);
    public String getStrBirthday();
    public void setStrBirthday(String strBirthday);
    public Plans getSubPlan();
    public void setSubPlan(Plans subPlan);
    public void showCustomersDetails();



}
