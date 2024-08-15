package model;

public class SalesThread implements Runnable {
    private final long productId;
    private final int quantity;
    private final String threadName;

    public SalesThread(long productId, int quantity, String threadName) {
        this.productId = productId;
        this.quantity = quantity;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        Inventory inventory = Inventory.getInstance();
        if (inventory.updateProductQuantity(productId, quantity)) {
            System.out.println(threadName + " removed " + quantity + " units from Product ID: " + productId +
                    ". Remaining: " + inventory.getProductById(productId).getQuantity());
        } else {
            System.out.println(threadName + " tried to remove " + quantity + " units from Product ID: " + productId +
                    ", but there is not enough stock.");
        }
    }
}
