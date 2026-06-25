import java.util.HashMap;
import java.util.Map;
class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

        public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

       public String getProductId() { 
        return productId; 
    }
    
    public String getProductName() { 
        return productName; 
    }
    
    public int getQuantity() { 
        return quantity; 
    }
    
    public double getPrice() { 
        return price; 
    }

        public void setProductName(String productName) { 
        this.productName = productName; 
    }
    
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
    }
    
    public void setPrice(double price) { 
        this.price = price; 
    }

       @Override
    public String toString() {
        return String.format("Product[ID: %s, Name: %s, Qty: %d, Price: $%.2f]", 
                           productId, productName, quantity, price);
    }
}

class InventoryManager {
    
    private Map<String, Product> inventory; 
    
      public InventoryManager() {
        this.inventory = new HashMap<>();
    }

       public boolean addProduct(Product product) {
        // Check if product with this ID already exists
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Error: Product with ID '" + product.getProductId() 
                             + "' already exists in inventory.");
            return false;
        }
        
               inventory.put(product.getProductId(), product);
        System.out.println("✓ Successfully added: " + product.getProductName());
        return true;
    }

   
    public boolean updateProduct(String productId, String productName, 
                                int quantity, double price) {
                if (!inventory.containsKey(productId)) {
            System.out.println("Error: Product with ID '" + productId + "' not found.");
            return false;
        }
        
                Product product = inventory.get(productId);
        product.setProductName(productName);
        product.setQuantity(quantity);
        product.setPrice(price);
        
        System.out.println("Successfully updated: " + productName);
        return true;
    }

        public boolean deleteProduct(String productId) {
               if (!inventory.containsKey(productId)) {
            System.out.println(" Error: Product with ID '" + productId + "' not found.");
            return false;
        }
      
        String productName = inventory.get(productId).getProductName();
        inventory.remove(productId);
        
        System.out.println("Successfully deleted: " + productName);
        return true;
    }
    public Product getProduct(String productId) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println(" Product with ID '" + productId + "' not found.");
        }
        return product;
    }

       public void displayAllProducts() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty. No products to display.");
            return;
        }
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println(" CURRENT INVENTORY (" + inventory.size() + " products)");
        System.out.println("═".repeat(60));
        
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
        
        System.out.println("═".repeat(60) + "\n");
    }

        public int getTotalProducts() {
        return inventory.size();
    }
}
public class InventoryManagementSystem {
    public static void main(String[] args) {
               InventoryManager inventory = new InventoryManager();
        
        System.out.println("Welcome to the Warehouse Inventory Management System!");
        System.out.println();
        
        
        System.out.println("STEP 1: Adding Products to Inventory");
        System.out.println("─────────────────────────────────────────");
        
        inventory.addProduct(new Product("P001", "Laptop", 50, 999.99));
        inventory.addProduct(new Product("P002", "Mouse", 200, 29.99));
        inventory.addProduct(new Product("P003", "Keyboard", 150, 79.99));
        
        // Try adding a duplicate (should fail)
        inventory.addProduct(new Product("P001", "Duplicate Laptop", 10, 899.99));
        
        inventory.displayAllProducts();
        
               System.out.println("STEP 2: Updating Product Information");
        System.out.println("─────────────────────────────────────────");
        
        System.out.println("Before update:");
        System.out.println(inventory.getProduct("P001"));
        System.out.println();
        
               inventory.updateProduct("P001", "Laptop Pro", 45, 1099.99);
        
        System.out.println("After update:");
        System.out.println(inventory.getProduct("P001"));
        System.out.println();
        
               inventory.updateProduct("P999", "Fake Product", 10, 99.99);
        

        System.out.println("  STEP 3: Removing Products from Inventory");
        System.out.println("─────────────────────────────────────────");
        
        inventory.deleteProduct("P002");
        
        // Try deleting a non-existent product (should fail)
        inventory.deleteProduct("P999");
        
        inventory.displayAllProducts();
        
       
        System.out.println("FINAL SUMMARY");
        System.out.println("─────────────────────────────────────────");
        System.out.println("Total unique products in inventory: " 
                         + inventory.getTotalProducts());
        System.out.println();
        System.out.println(" Inventory management complete! Thank you for using the system.");
    }
}