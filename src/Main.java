import model.Inventory;
import model.Product;
import model.SalesThread;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = Inventory.getInstance();

        // Adicionando produtos ao estoque
        inventory.addProduct(new Product(1, "Produto A", 10, 50.0));
        inventory.addProduct(new Product(2, "Produto B", 5, 30.0));
        inventory.addProduct(new Product(3, "Produto C", 20, 70.0));
        inventory.addProduct(new Product(4, "Produto D", 15, 100.0));

        Thread t1 = new Thread(new SalesThread(1, 3, "Thread 1"));
        Thread t2 = new Thread(new SalesThread(2, 2, "Thread 2"));
        Thread t3 = new Thread(new SalesThread(3, 5, "Thread 3"));
        Thread t4 = new Thread(new SalesThread(4, 8, "Thread 4"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Estoque final:");
        for (Product p : inventory.getStock()) {
            System.out.println("Produto: " + p.getName() + " | Quantidade: " + p.getQuantity());
        }
    }
}
