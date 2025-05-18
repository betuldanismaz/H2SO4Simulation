//BETÜL DANIŞMAZ 220601019 (BİLMÜH)
package com.H2SO4Simulation;
import java.util.concurrent.Semaphore;
public class H2SO4Simulator extends Thread {
    public static void main(String[] args) {
        int numberOfH2SO4ToProduce = 1000; //1000 adet molekül oluşturulacak

        System.out.println("Starting H2SO4 production simulation for " + numberOfH2SO4ToProduce + " molecules.");

        for (int i = 1; i <= numberOfH2SO4ToProduce; i++) {
            // Her bir H2SO4 molekülü üretimi için yeni semaphorlar oluşturur
            // Tüm semaforlar başlangıçta 0 (locked)
            Semaphore semS = new Semaphore(0);
            Semaphore semO2_1 = new Semaphore(0); // O2(1) için
            Semaphore semO2_2 = new Semaphore(0); // O2(2) için
            Semaphore semH2O = new Semaphore(0);
            Semaphore semSO2 = new Semaphore(0);
            Semaphore semSO3 = new Semaphore(0);
            Semaphore semH2SO4Done = new Semaphore(0); // H2SO4 completion flag semaphore

            Thread producerS = new ElementProducer("S", semS, i);
            Thread producerO2_1 = new ElementProducer("O2(1)", semO2_1, i);
            Thread producerO2_2 = new ElementProducer("O2(2)", semO2_2, i);
            Thread producerH2O = new ElementProducer("H2O", semH2O, i);

            // Ara ürün SO2
            Thread producerSO2 = new MoleculeProducer("SO2", new Semaphore[]{semS, semO2_1}, semSO2, i);

            // Ara ürün SO3
            Thread producerSO3 = new MoleculeProducer("SO3", new Semaphore[]{semSO2, semO2_2}, semSO3, i);

            // Son ürün H2SO4
            // semH2SO4Done, H2SO4 üretimi bittiğinde ana thread'e sinyal vermek için kullanılır.
            Thread producerH2SO4 = new MoleculeProducer("H2SO4", new Semaphore[]{semSO3, semH2O}, semH2SO4Done, i);

            // Tüm threadler başlar
            // Şimdilik sıralamalar önemli değil, semaphorelar sayesinde doğru sıra gerçekleşecek
            producerS.start();
            producerO2_1.start();
            producerO2_2.start();
            producerH2O.start();
            producerSO2.start();
            producerSO3.start();
            producerH2SO4.start();

            try {
                semH2SO4Done.acquire(); // Blocks the main thread until semH2SO4Done has an available permit (which is >0)
                // if a permit exists, acquire() method decrements it by 1 and allows the main thread to proceed

                if (i < numberOfH2SO4ToProduce) {
                     System.out.println("--- Next H2SO4 molecule ---");
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Main thread interrupted while waiting for H2SO4 #" + i + ": " + e.getMessage());
            }
        }
        System.out.println(numberOfH2SO4ToProduce + " H2SO4 molecules simulated successfully.");
    }
}