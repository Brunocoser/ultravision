package Interfaces;

import Enums.MediaFormats;

public interface ITitle {

    public int getCode();
    public void setCode(int code);
    public int getYearRelease();
    public void setYearRelease(int yearRelease);
    public String getTitle();
    public void setTitle(String title);
    public String getGenre();
    public void setGenre(String genre);
    public String getDirectorOrBand();
    public void setDirectorOrBand(String director);
    public MediaFormats getFormatValue();
    public void setFormatValue(MediaFormats formatValue);
}
