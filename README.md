# H2SO4 (Sulfuric Acid) Production Simulator

This project is a Java-based console application that simulates the production of Sulfuric Acid (H2SO4) using multi-threading and semaphores to manage and synchronize the chemical reaction steps according to a given precedence graph. This was developed as an assignment for the BIL 302 - Operating Systems course.


## Project Overview

The simulation models the step-by-step creation of H2SO4 molecules. The production process involves creating base elements (S, O2(1), O2(2), H2O) and intermediate molecules (SO2, SO3) before the final H2SO4 molecule can be formed. Each step is represented by a thread, and semaphores are used to ensure that a step only proceeds after its prerequisite components are available. The simulator is configured to produce 1000 H2SO4 molecules.

## Features

-   Simulation of H2SO4 production based on a defined chemical reaction pathway.
-   Utilization of Java multi-threading for concurrent process simulation.
-   Synchronization of production steps using Java Semaphores.
-   Console-based output logging each creation event.
-   Simulation of 1000 H2SO4 molecules.
-   The main thread orchestrates the creation of all simulation worker threads.


The production follows these dependencies:

1.  **S + O2(1)  -> SO2**
2.  **SO2 + O2(2) -> SO3**
3.  **SO3 + H2O   -> H2SO4**

Where S, O2(1), O2(2), and H2O are initial reactants.
