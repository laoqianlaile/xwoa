package com.centit.webservice.pojo;

public class CentitWSReturninfo {
   private String status;

   private String description;

public CentitWSReturninfo(String status, String description) {
    this.status=status;
    this.description=description;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}
