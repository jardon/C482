package Model;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    static ObservableList<Part> allParts = FXCollections.observableArrayList();
    static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part) { allParts.add(part); }

    public static void addProduct(Product product) { allProducts.add(product); }

    public static Part lookupPart(int partId) {
        Iterator<Part> itr = allParts.listIterator();
        while(itr.hasNext()) {
            Part part = itr.next();
            if(part.getId() == partId)
                return part;
        }
        throw new NoSuchElementException("Part not found");
    }

    public static ObservableList<Part> lookupPart(String partName) {
        Iterator<Part> itr = allParts.listIterator();
        ObservableList<Part> subList = FXCollections.observableArrayList();
        while(itr.hasNext()) {
            Part part = itr.next();
            if(part.getName() == partName) {
                subList.add(part);
                return subList;
            }
        }
        throw new NoSuchElementException("Part not found");
    }

    public static Product lookupProduct(int productId) {
        Iterator<Product> itr = allProducts.listIterator();
        while(itr.hasNext()) {
            Product prod = itr.next();
            if(prod.getId() == productId)
                return prod;
        }
        throw new NoSuchElementException("Product not found");
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        Iterator<Product> itr = allProducts.listIterator();
        ObservableList<Product> subList = FXCollections.observableArrayList();
        while(itr.hasNext()) {
            Product prod = itr.next();
            if(prod.getName() == productName) {
                subList.add(prod);
                return subList;
            }
        }
        throw new NoSuchElementException("Product not found");
    }

    public static void updatePart(int index, Part part) { allParts.set(index, part); }

    public static void updateProduct(int index, Product product) { allProducts.set(index, product); }

    public static void deletePart(Part part) { allParts.remove(part); }

    public static void deleteProduct(Product product) { allProducts.remove(product); }

    public static ObservableList<Part> getAllParts() { return allParts; }

    public static ObservableList<Product> getAllProducts() { return allProducts; }
}