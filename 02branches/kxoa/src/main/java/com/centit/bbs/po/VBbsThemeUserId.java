package com.centit.bbs.po;

public class VBbsThemeUserId implements java.io.Serializable {
    private static final long serialVersionUID =  1L;

    private String themeno;

    private String manager;
    
    public String getThemeno() {
        return themeno;
    }
    public void setThemeno(String themeno) {
        this.themeno = themeno;
    }
    public String getManager() {
        return manager;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }
    
    // Constructors
    /** default constructor */
    public VBbsThemeUserId() {
    }
    /** full constructor */
    public VBbsThemeUserId(String themeno, String manager) {

        this.themeno = themeno;
        this.manager = manager;   
    }
    
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof VBbsThemeUserId))
            return false;
        
        VBbsThemeUserId castOther = (VBbsThemeUserId) other;
        boolean ret = true;
  
        ret = ret && ( this.getThemeno() == castOther.getThemeno() ||
                       (this.getThemeno() != null && castOther.getThemeno() != null
                               && this.getThemeno().equals(castOther.getThemeno())));
  
        ret = ret && ( this.getManager() == castOther.getManager() ||
                       (this.getManager() != null && castOther.getManager() != null
                               && this.getManager().equals(castOther.getManager())));

        return ret;
    }
    
    public int hashCode() {
        int result = 17;
  
        result = 37 * result +
            (this.getThemeno() == null ? 0 :this.getThemeno().hashCode());
  
        result = 37 * result +
            (this.getManager() == null ? 0 :this.getManager().hashCode());

        return result;
    }
    
}