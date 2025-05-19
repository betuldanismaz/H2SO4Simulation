//
package com.H2SO4Simulation;
import java.util.concurrent.Semaphore;
public class ElementProducer extends Thread {
    private  String elementName;
    private Semaphore producedSemaphore;
    private int moleculeNumber;

    public ElementProducer (String elementName,
                            Semaphore producedSemaphore,
                            int moleculeNumber)
    {
        this.elementName = elementName;
        this.producedSemaphore = producedSemaphore ;
        this.moleculeNumber = moleculeNumber;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 100)) ; //simülasyon başlamadan önce bekleme süresi
            System.out.println ( "NUM = " + moleculeNumber + " " + elementName + "Created");
            producedSemaphore.release(); //üretim tamamlandıktan sonra signal verir
        }
        catch(Exception e) {
            Thread.currentThread().interrupt();
            System.err.println("Error in" + elementName + "producer:" + e.getMessage()) ;
        }
    }
}
