import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ModeloNparaM {

    public static void main(String[] args) throws InterruptedException {
        // quantidade de threads de usuário que serão simuladas
        int[] quantidades = {10, 100, 500, 1000};

        // pool
        // aqui estamos simulando o número real de threads que o sistema pode executar ao mesmo tempo
        int M = 10;

        System.out.println("=== Simulação Modelo N:M ===");
        System.out.println("Cada tarefa representa uma 'thread de usuário'.");
        System.out.println("As tarefas são executadas por um pool fixo de " + M + " threads do sistema.\n");

        for (int n : quantidades) {
            // cria um pool fixo de M threads do sistema
            ExecutorService pool = Executors.newFixedThreadPool(M);

            // marca o início da execução para medir o tempo total
            long inicio = System.currentTimeMillis();

            // envia N tarefas para o pool
            for (int i = 0; i < n; i++) {
                pool.execute(() -> {
                    try {
                        // cada tarefa simula trabalho dormindo por 50 milissegundos
                        // isso representa o tempo que ela levaria para processar algo
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

            // impede o envio de novas tarefas e aguarda o término das existentes
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.HOURS);

            // marca o fim da execução e calcula o tempo total
            long fim = System.currentTimeMillis();
            long tempoTotal = fim - inicio;

            System.out.println("Tarefas: " + n + " | Pool: " + M + " threads | Tempo total (ms): " + tempoTotal);
        }

        System.out.println("\nSimulação finalizada.");
    }
}