public class Modelo1para1 {

    public static void main(String[] args) throws InterruptedException {
        // quantidade de threads que serão criadas em cada teste
        int[] quantidades = {10, 100, 500, 1000};

        System.out.println("=== Simulação Modelo 1:1 ===");
        System.out.println("Cada thread de usuário corresponde a uma thread real do sistema.\n");

        for (int n : quantidades) {
            // marca o tempo de início da execução
            long inicio = System.currentTimeMillis();

            // array para armazenar as threads
            Thread[] threads = new Thread[n];

            // cria e inicia cada thread
            for (int i = 0; i < n; i++) {
                threads[i] = new Thread(() -> {
                    try {
                        // cada thread simula trabalho dormindo por 50 milissegundos
                        // isso representa o tempo de execução de uma tarefa qualquer
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                threads[i].start(); // inicia a execução da thread
            }

            // espera todas as threads terminarem ( dá um join)
            for (Thread t : threads) {
                t.join();
            }

            // marca o fim da execução
            long fim = System.currentTimeMillis();
            long tempoTotal = fim - inicio;

            System.out.println("Threads: " + n + " | Tempo total (ms): " + tempoTotal);
        }

        System.out.println("\nSimulação finalizada.");
    }
}