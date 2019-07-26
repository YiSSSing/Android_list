package com.example.homework3;

public class DATA_LINE {

    String icon_url ;
    String name_string ;

    public DATA_LINE (String i , String n ) {
        this.icon_url = i ;
        this.name_string = n ;
    }

    public String getName() { return name_string ; }

    public void setName(String n ) { this.name_string = n ; }

    public String getUrl() { return icon_url ; }

    public void setUrl(String u) { this.icon_url = u ; }

}
