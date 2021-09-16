package baemin;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Integer qty;
    private String productName;
    private String deliveryStatus;
    private Long deliveryId;

    @PostPersist
    public void onPostPersist(){

//        baemin.external.Product product = new baemin.external.Product();
        // mappings goes here
        baemin.external.Product product = OrderApplication.applicationContext.getBean(baemin.external.ProductService.class)
                .checkStock(this.productId);
        System.out.println("\n\n================================ ORDER CHECKSTOCK ================================\n\n");
        if(product.getStock()<this.qty)
            return;
        
        OrderPlaced orderPlaced = new OrderPlaced();
        BeanUtils.copyProperties(this, orderPlaced);
        orderPlaced.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.



    }


    @PreRemove
    public void onPreRemove(){
        OrderCancelled orderCancelled = new OrderCancelled();
        BeanUtils.copyProperties(this, orderCancelled);
        orderCancelled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        baemin.external.Delivery delivery = new baemin.external.Delivery();
        // mappings goes here
//        OrderApplication.applicationContext.getBean(baemin.external.DeliveryService.class)
//            .cancelDelivery(delivery);
        OrderApplication.applicationContext.getBean(baemin.external.DeliveryService.class)
                .cancelDelivery(this.deliveryId);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }




}