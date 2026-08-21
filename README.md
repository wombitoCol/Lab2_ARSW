# Lab2_ARSW
## Reporte de Laboratorio: Parte I — (Calentamiento) Wait / Notify en Java

## 1. Introducción y Objetivo Didáctico

El objetivo de esta parte del laboratorio es practicar la suspensión y reanudación de hilos concurrentes en Java mediante el uso de monitores (`synchronized`, `wait()`, `notifyAll()`) sobre un programa enfocado en la búsqueda de números primos (`PrimeFinder`).

La meta didáctica es lograr la pausa temporal de los hilos y la espera de interacción por consola sin incurrir en espera activa (*busy-waiting*).

---

## 2. Análisis del Diseño de Sincronización en el Código Existente

### 2.1 Identificación del Lock / Monitor
- **Monitor Utilizado**: Se utiliza la propia instancia de cada hilo trabajador (`this` dentro de `PrimeFinderThread`).
- **Funcionamiento**: Cada hilo trabajador actúa como su propio monitor intrínseco. En `PrimeFinderThread`, los métodos `setSuspended()`, `stopSuspended()` y el bloque dentro de `run()` sincronizan directamente sobre la instancia del hilo actual (`synchronized(this)`).

### 2.2 Condición de Sincronización y Suspensión
- **Variable de Estado**: `private boolean isSuspended = false;` en `PrimeFinderThread`.
- **Verificación de Pausa**: En cada iteración del bucle `for` en el método `run()`, el hilo consulta si la variable `isSuspended` es `true`:
  ```java
  while (isSuspended) {
      synchronized (this) {
          try {
              wait();
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
          }
      }
  }
## Segunda Parte Primera parte
https://github.com/wombitoCol/Lab2_1_2#segunda-parte-de-la-primera-laboratorio-arsw
