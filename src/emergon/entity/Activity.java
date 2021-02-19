package emergon.entity;
// Generated 17 ??? 2021 5:37:33 ?? by Hibernate Tools 4.3.1


import emergon.ui.Laborer;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Activity generated by hbm2java
 */
@Entity
@Table(name="activity"
    ,catalog="employees"
)
public class Activity  implements java.io.Serializable {


     private long activityid;
     private String title;
     private String itemcode;
     private BigDecimal totalcost;
     private Set<Laborer> laborers = new HashSet<Laborer>(0);

    public Activity() {
    }

	
    public Activity(long activityid) {
        this.activityid = activityid;
    }
    public Activity(long activityid, String title, String itemcode, BigDecimal totalcost, Set<Laborer> laborers) {
       this.activityid = activityid;
       this.title = title;
       this.itemcode = itemcode;
       this.totalcost = totalcost;
       this.laborers = laborers;
    }
   
     @Id 

    
    @Column(name="activityid", unique=true, nullable=false)
    public long getActivityid() {
        return this.activityid;
    }
    
    public void setActivityid(long activityid) {
        this.activityid = activityid;
    }

    
    @Column(name="title", length=100)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="itemcode", length=100)
    public String getItemcode() {
        return this.itemcode;
    }
    
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    
    @Column(name="totalcost", precision=10, scale=5)
    public BigDecimal getTotalcost() {
        return this.totalcost;
    }
    
    public void setTotalcost(BigDecimal totalcost) {
        this.totalcost = totalcost;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="activity")
    public Set<Laborer> getLaborers() {
        return this.laborers;
    }
    
    public void setLaborers(Set<Laborer> laborers) {
        this.laborers = laborers;
    }




}

