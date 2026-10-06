package com.budiluhur.catalog;

import com.budiluhur.catalog.model.Product;
import com.budiluhur.catalog.repository.ProductRepository;
import com.budiluhur.catalog.exception.ProductNotFoundException;

public class Application {
    public static void main(String[] args) {
        ProductRepository repo = new ProductRepository();

        repo.addProduct(new Product("PRD-01", "Keyboard Mechanical", 450000.0));
        repo.addProduct(new Product("PRD-02", "Mouse Wireless", 175000.0));

        System.out.println("=== DAFTAR SELURUH PRODUK ===");
        repo.findAll().forEach(System.out::println);

        System.out.println("\n=== PENCARIAN PRODUK ===");
        try {
            Product p = repo.findById("PRD-99");
            System.out.println("Ditemukan: " + p);
        } catch (ProductNotFoundException e) {
            System.err.println("Error Terjadi: " + e.getMessage());
        }

        System.out.println("\n=== HAPUS PRODUK (Mini Challenge) ===");
        try {
            repo.deleteById("PRD-01");
            System.out.println("PRD-01 berhasil dihapus.");
            repo.deleteById("PRD-77");
        } catch (ProductNotFoundException e) {
            System.err.println("Error Terjadi: " + e.getMessage());
        }

        System.out.println("\n=== SISA PRODUK ===");
        repo.findAll().forEach(System.out::println);
    }
}