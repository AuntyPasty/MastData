package daveho.co.auntypasty.mastdata.models;

/**
 * A model for a tenant with mast count item
 */
public class TenantMast {

    private String tenantName;
    private int mastCount;

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public int getMastCount() {
        return mastCount;
    }

    public void setMastCount(int mastCount) {
        this.mastCount = mastCount;
    }
}
