//
package com.H2SO4Simulation;
import java.util.concurrent.Semaphore;
public class MoleculeProducer extends Thread {
    private String moleculeName;
    private Semaphore[] requiredSemaphores; // Bu molekülü üretmek için gereken girdilerin semaforları
    private Semaphore producedSemaphore;    // Bu molekül üretildiğinde sinyal verecek semafor
    private int moleculeNumber;

    public MoleculeProducer(String moleculeName,
                            Semaphore[] requiredSemaphores,
                            Semaphore producedSemaphore,
                            int moleculeNumber) {
        this.moleculeName = moleculeName;
        this.requiredSemaphores = requiredSemaphores;
        this.producedSemaphore = producedSemaphore;
        this.moleculeNumber = moleculeNumber;
    }

    @Override
    public void run() {
        try {
            // Gerekli tüm girdilerin üretilmesini bekle
            for (Semaphore reqSem : requiredSemaphores) {
                reqSem.acquire();
            }
            // Simülasyon başlamadan önce biraz beklemek için
            Thread.sleep((long) (Math.random() * 100));
            System.out.println("NUM = " + moleculeNumber + " " + moleculeName + " Created");
            producedSemaphore.release(); // Moleküller üretilince signal verir

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error in " + moleculeName + " producer: " + e.getMessage());
        }
    }
}
