package bookmarket;

public class OrderCanceled extends AbstractEvent {

    private Long id;
    private Long bookId;
    private Long qty;
    private String status;
    private Long customerId;
    private String isMile;

    public String getIsMile() {
        return isMile;
    }

    public void setIsMile(String isMile) {
        this.isMile = isMile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
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