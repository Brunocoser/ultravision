package Interfaces;

import Enums.MediaFormats;

public interface ITitle {

    int getCode();
    void setCode(int code);
    int getYearRelease();
    void setYearRelease(int yearRelease);
    String getTitle();
    void setTitle(String title);
    String getGenre();
    void setGenre(String genre);
    String getDirectorOrBand();
    void setDirectorOrBand(String director);
    MediaFormats getFormatValue();
    void setFormatValue(MediaFormats formatValue);
}
