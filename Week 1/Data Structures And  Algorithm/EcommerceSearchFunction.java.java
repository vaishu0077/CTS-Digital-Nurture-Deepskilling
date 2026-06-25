class Product {
    private String productId;
    private String productName;
    private String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String getProductId() { 
        return productId; 
    }
    
    public String getProductName() { 
        return productName; 
    }
    
    public String getCategory() { 
        return category; 
    }

    @Override
    public String toString() {
        return String.format("Product[ID: %s, Name: %s, Category: %s]", 
                           productId, productName, category);
    }
}

class EcommerceSearchSystem {
    
    private Product[] linearSearchArray;
    private int linearSearchCount;
    
    private Product[] binarySearchArray;
    private int binarySearchCount;
    
    private static final int MAX_CAPACITY = 100;

    public EcommerceSearchSystem() {
        linearSearchArray = new Product[MAX_CAPACITY];
        binarySearchArray = new Product[MAX_CAPACITY];
        linearSearchCount = 0;
        binarySearchCount = 0;
    }

    public void addProductForLinearSearch(Product product) {
        if (linearSearchCount < MAX_CAPACITY) {
            linearSearchArray[linearSearchCount] = product;
            linearSearchCount++;
            System.out.println("Added to linear search array: " + product.getProductName());
        } else {
            System.out.println("Error: Linear search array is full!");
        }
    }

    public void addProductForBinarySearch(Product product) {
        if (binarySearchCount < MAX_CAPACITY) {
            int position = binarySearchCount;
            
            for (int i = binarySearchCount - 1; i >= 0; i--) {
                if (binarySearchArray[i].getProductId().compareTo(product.getProductId()) > 0) {
                    position = i;
                }
            }
            
            for (int i = binarySearchCount; i > position; i--) {
                binarySearchArray[i] = binarySearchArray[i - 1];
            }
            
            binarySearchArray[position] = product;
            binarySearchCount++;
            
            System.out.println("Added to binary search array (sorted): " + product.getProductName());
        } else {
            System.out.println("Error: Binary search array is full!");
        }
    }

    public Product linearSearchByName(String searchName) {
        System.out.println("\nStarting LINEAR SEARCH for: \"" + searchName + "\"");
        System.out.println("─────────────────────────────────────────");
        
        int comparisons = 0;
        
        for (int i = 0; i < linearSearchCount; i++) {
            comparisons++;
            System.out.println("  Checking position " + i + ": " + linearSearchArray[i].getProductName());
            
            if (linearSearchArray[i].getProductName().toLowerCase()
                             .contains(searchName.toLowerCase())) {
                System.out.println("  FOUND! Made " + comparisons + " comparison(s)");
                return linearSearchArray[i];
            }
        }
        
        System.out.println("  NOT FOUND after " + comparisons + " comparison(s)");
        return null;
    }

    public Product linearSearchById(String productId) {
        for (int i = 0; i < linearSearchCount; i++) {
            if (linearSearchArray[i].getProductId().equals(productId)) {
                return linearSearchArray[i];
            }
        }
        return null;
    }

    public Product binarySearchById(String productId) {
        System.out.println("\nStarting BINARY SEARCH for ID: \"" + productId + "\"");
        System.out.println("─────────────────────────────────────────");
        
        int left = 0;
        int right = binarySearchCount - 1;
        int comparisons = 0;
        
        while (left <= right) {
            int middle = left + (right - left) / 2;
            comparisons++;
            
            String middleId = binarySearchArray[middle].getProductId();
            System.out.println("  Step " + comparisons + 
                             ": Checking middle position " + middle + 
                             " (ID: " + middleId + ")");
            
            if (middleId.equals(productId)) {
                System.out.println("  FOUND! Made " + comparisons + " comparison(s)");
                return binarySearchArray[middle];
            }
            
            if (productId.compareTo(middleId) < 0) {
                System.out.println("  Search left half (ID is smaller)");
                right = middle - 1;
            }
            else {
                System.out.println("  Search right half (ID is larger)");
                left = middle + 1;
            }
        }
        
        System.out.println("  NOT FOUND after " + comparisons + " comparison(s)");
        return null;
    }

    public Product binarySearchByName(String productName) {
        System.out.println("\nNote: Binary search is optimized for ID search.");
        System.out.println("    For name search, using linear search as fallback.");
        return linearSearchByName(productName);
    }

    public void displayLinearSearchArray() {
        System.out.println("\nLINEAR SEARCH ARRAY (Unsorted):");
        System.out.println("╔════════════════════════════════════════════╗");
        
        if (linearSearchCount == 0) {
            System.out.println("║  Array is empty                                          ║");
        } else {
            for (int i = 0; i < linearSearchCount; i++) {
                System.out.println("║  [" + i + "] " + linearSearchArray[i].toString() + " ║");
            }
        }
        System.out.println("╚════════════════════════════════════════════╝");
    }

    public void displayBinarySearchArray() {
        System.out.println("\nBINARY SEARCH ARRAY (Sorted by ID):");
        System.out.println("╔════════════════════════════════════════════╗");
        
        if (binarySearchCount == 0) {
            System.out.println("║  Array is empty                                          ║");
        } else {
            for (int i = 0; i < binarySearchCount; i++) {
                System.out.println("║  [" + i + "] " + binarySearchArray[i].toString() + " ║");
            }
        }
        System.out.println("╚════════════════════════════════════════════╝");
    }

    public int getLinearSearchCount() {
        return linearSearchCount;
    }

    public int getBinarySearchCount() {
        return binarySearchCount;
    }
}

public class EcommerceSearchFunction {
    public static void main(String[] args) {
        EcommerceSearchSystem searchSystem = new EcommerceSearchSystem();
        
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║  E-COMMERCE PLATFORM SEARCH FUNCTIONALITY   ║");
        System.out.println("║  Comparing Linear Search vs Binary Search   ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println();
        
        System.out.println("STEP 1: Adding Products to Both Search Arrays");
        System.out.println("─────────────────────────────────────────");
        
        searchSystem.addProductForLinearSearch(new Product("E005", "Wireless Headphones", "Electronics"));
        searchSystem.addProductForLinearSearch(new Product("E001", "Running Shoes", "Clothing"));
        searchSystem.addProductForLinearSearch(new Product("E003", "Coffee Maker", "Home & Kitchen"));
        searchSystem.addProductForLinearSearch(new Product("E002", "Smartphone", "Electronics"));
        searchSystem.addProductForLinearSearch(new Product("E004", "Yoga Mat", "Sports"));
        
        System.out.println("\nAdded 5 products to both arrays");
        
        System.out.println("\nSTEP 2: Displaying Both Arrays");
        System.out.println("─────────────────────────────────────────");
        
        searchSystem.displayLinearSearchArray();
        searchSystem.displayBinarySearchArray();
        
        System.out.println("\nKey Observation:");
        System.out.println("  - Linear array: Products in ADD order (unsorted)");
        System.out.println("  - Binary array: Products sorted by ID (E001, E002, E003...)");
        
        System.out.println("\n════════════════════════════════════════════");
        System.out.println("STEP 3: TESTING LINEAR SEARCH (O(n))");
        System.out.println("════════════════════════════════════════════");
        
        System.out.println("\nTEST 3A: Search for existing product \"Coffee Maker\"");
        Product found1 = searchSystem.linearSearchByName("Coffee Maker");
        if (found1 != null) {
            System.out.println("Result: " + found1);
        }
        
        System.out.println("\nTEST 3B: Search for existing product \"Running Shoes\" (first item)");
        Product found2 = searchSystem.linearSearchByName("Running Shoes");
        if (found2 != null) {
            System.out.println("Result: " + found2);
        }
        
        System.out.println("\nTEST 3C: Search for NON-EXISTING product \"Gaming Console\"");
        Product found3 = searchSystem.linearSearchByName("Gaming Console");
        if (found3 == null) {
            System.out.println("Result: Product not found (as expected)");
        }
        
        System.out.println("\n════════════════════════════════════════════");
        System.out.println("STEP 4: TESTING BINARY SEARCH (O(log n))");
        System.out.println("════════════════════════════════════════════");
        
        System.out.println("\nTEST 4A: Search for ID \"E003\" (middle item)");
        Product found4 = searchSystem.binarySearchById("E003");
        if (found4 != null) {
            System.out.println("Result: " + found4);
        }
        
        System.out.println("\nTEST 4B: Search for ID \"E001\" (first item)");
        Product found5 = searchSystem.binarySearchById("E001");
        if (found5 != null) {
            System.out.println("Result: " + found5);
        }
        
        System.out.println("\nTEST 4C: Search for ID \"E005\" (last item)");
        Product found6 = searchSystem.binarySearchById("E005");
        if (found6 != null) {
            System.out.println("Result: " + found6);
        }
        
        System.out.println("\nTEST 4D: Search for NON-EXISTING ID \"E009\"");
        Product found7 = searchSystem.binarySearchById("E009");
        if (found7 == null) {
            System.out.println("Result: Product not found (as expected)");
        }
        
        System.out.println("\n════════════════════════════════════════════");
        System.out.println("STEP 5: COMPARING BOTH ALGORITHMS");
        System.out.println("════════════════════════════════════════════");
        
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println("│  TIME COMPLEXITY COMPARISON                │");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.println("│  Linear Search:  O(n)                     │");
        System.out.println("│  Binary Search:  O(log n)  - MUCH FASTER! │");
        System.out.println("└──────────────────────────────────────────┘");
        
        System.out.println("\nPRACTICAL EXAMPLE (for 1,000,000 products):");
        System.out.println("  - Linear Search:  Up to 1,000,000 comparisons");
        System.out.println("  - Binary Search:  Max 20 comparisons");
        
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println("│  WHEN TO USE EACH ALGORITHM                │");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.println("│  Linear Search:                            │");
        System.out.println("│    - Small datasets (< 50 items)           │");
        System.out.println("│    - Unsorted data                         │");
        System.out.println("│    - One-time search                       │");
        System.out.println("│                                            │");
        System.out.println("│  Binary Search:                            │");
        System.out.println("│    - Large datasets (100+ items)           │");
        System.out.println("│    - Sorted data available                 │");
        System.out.println("│    - Multiple searches (worth sorting)     │");
        System.out.println("│    - Performance is critical               │");
        System.out.println("└──────────────────────────────────────────┘");
        
        System.out.println("\nRECOMMENDATION FOR E-COMMERCE PLATFORM:");
        System.out.println("─────────────────────────────────────────");
        System.out.println("Use BINARY SEARCH for the following reasons:");
        System.out.println("  1. E-commerce platforms have HUGE inventories (10,000+ products)");
        System.out.println("  2. Users expect INSTANT search results (< 1 second)");
        System.out.println("  3. Same data is searched THOUSANDS of times daily");
        System.out.println("  4. Sorting once gives FASTER searches forever");
        System.out.println("  5. Mobile users need optimal performance");
        
        System.out.println("\nOPTIMIZATION STRATEGY:");
        System.out.println("  - Store products in sorted arrays (by ID or name)");
        System.out.println("  - Use Binary Search for ID-based searches");
        System.out.println("  - For category searches, maintain sorted index by category");
        System.out.println("  - Consider HashMap for O(1) ID lookups (even faster!)");
        
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║  Search functionality analysis complete!     ║");
        System.out.println("╚════════════════════════════════════════════╝");
    }
}