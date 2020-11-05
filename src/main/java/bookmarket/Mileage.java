package bookmarket;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Mileage_table")
public class Mileage {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long mileage;
    private String status;
    private Long customerId;

    @PostPersist
    public void onPostPersist(){
        MileageUsed mileageUsed = new MileageUsed();
        BeanUtils.copyProperties(this, mileageUsed);
        mileageUsed.publishAfterCommit();


        UseCanceled useCanceled = new UseCanceled();
        BeanUtils.copyProperties(this, useCanceled);
        useCanceled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }




}
