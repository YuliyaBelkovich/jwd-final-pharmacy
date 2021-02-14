package com.epam.jwd.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class responsible for managing the resource bundle object and changing the language on the JSP pages
 */
public class LanguageManager {
    private static LanguageManager instance = new LanguageManager();
    private  Locale current;
    private  ResourceBundle resourceBundle;

    private LanguageManager() {
        current = new Locale("en","US");
        resourceBundle = ResourceBundle.getBundle("text", current);
    }

    public static LanguageManager getInstance() {
        return instance;
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    public void setEnglish(){
        current = new Locale("en","US");
        resourceBundle = ResourceBundle.getBundle("text", current);
    }
    public void setRussian(){
        current = new Locale("ru","RU");
        resourceBundle = ResourceBundle.getBundle("text", current);
    }

    public ResourceBundle getBundle(){
        return resourceBundle;
    }


}
