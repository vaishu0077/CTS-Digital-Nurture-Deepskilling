class Order {
    private String orderId;
    private String customerName;
    private double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId +
               ", Customer: " + customerName +
               ", Total Price: " + totalPrice;
    }
}

class OrderSorter {

    public static void bubbleSort(Order[] orders) {
        int n = orders.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        Order pivot = orders[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot.getTotalPrice()) {
                i++;

                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    public static void displayOrders(Order[] orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}

public class SortCustomerOrders {
    public static void main(String[] args) {
        Order[] orders1 = {
            new Order("O101", "Asha", 850.50),
            new Order("O102", "Rahul", 120.00),
            new Order("O103", "Meena", 560.75),
            new Order("O104", "John", 299.99),
            new Order("O105", "Sara", 999.00)
        };

        Order[] orders2 = {
            new Order("O101", "Asha", 850.50),
            new Order("O102", "Rahul", 120.00),
            new Order("O103", "Meena", 560.75),
            new Order("O104", "John", 299.99),
            new Order("O105", "Sara", 999.00)
        };

        System.out.println("Orders before sorting:");
        OrderSorter.displayOrders(orders1);

        System.out.println("\nOrders after Bubble Sort:");
        OrderSorter.bubbleSort(orders1);
        OrderSorter.displayOrders(orders1);

        System.out.println("\nOrders after Quick Sort:");
        OrderSorter.quickSort(orders2, 0, orders2.length - 1);
        OrderSorter.displayOrders(orders2);
    }
}