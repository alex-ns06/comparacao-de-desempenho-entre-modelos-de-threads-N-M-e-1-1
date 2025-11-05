Relatório de Desempenho – Modelos de Threads 1:1 e N:M

1. Introdução

O objetivo deste experimento foi comparar o desempenho entre dois modelos de mapeamento de threads: 
- O modelo 1:1 (uma thread de usuário para uma thread do sistema operacional);
- O modelo N:M (múltiplas threads de usuário mapeadas para um número menor de threads do sistema).
Essa análise permite compreender como o gerenciamento de threads afeta o desempenho de aplicações que realizam tarefas concorrentes em Java.

2. Tabela de Resultados

<img width="712" height="125" alt="image" src="https://github.com/user-attachments/assets/ea9ca871-3a46-4bd9-b4c7-77229ef3cf3c" />

3. Análise dos Resultados

Observando os resultados, é possível notar que para uma carga pequena (10 tarefas), ambos os modelos apresentaram tempos muito semelhantes. Isso ocorre porque o sistema ainda consegue gerenciar as threads com facilidade, e a sobrecarga de criação e agendamento é baixa. Mas, conforme o número de tarefas aumenta, o modelo 1:1 passa a se mostrar muito mais eficiente. Isso acontece porque cada thread de usuário é diretamente associada a uma thread do sistema, permitindo um melhor aproveitamento dos núcleos de processamento. Já no modelo N:M, há um limite fixo de 10 threads do sistema (número fixo de threads que eu defini), o que faz com que as tarefas precisem aguardar sua vez na fila para serem executadas. Assim, o tempo total cresce rapidamente à medida que o número de tarefas aumenta.

Em resumo, podemos dizer que:

O modelo N:M é vantajoso em sistemas que possuem muitas tarefas curtas e leves, pois o número reduzido de threads do sistema economiza recursos.

O modelo 1:1 se torna mais vantajoso a partir de cerca de 100 tarefas (de acordo com meu teste fixo de 10 threads do sistema), quando a sobrecarga de agendamento do modelo N:M começa a ser perceptível.

4. Gráfico Comparativo

<img width="584" height="306" alt="image" src="https://github.com/user-attachments/assets/48b23a34-9a58-4763-9d05-a1e9ca66a299" />

5. Conclusão

Com base nos experimentos realizados, conclui-se que o modelo 1:1 apresenta um desempenho significativamente superior em cenários com alto número de tarefas simultâneas. Isso se deve à execução paralela real proporcionada pelas threads do sistema. Por outro lado, o modelo N:M pode ser útil quando há restrição de recursos do sistema, já que limita o número de threads do sistema operacional, reduzindo o consumo de memória e overhead de agendamento. Em suma, o modelo 1:1 se torna mais vantajoso a partir de 100 threads, demonstrando menor tempo de execução e maior eficiência para cargas mais pesadas.

6. Considerações Finais

Este experimento demonstrou na prática a diferença entre os modelos de threads, reforçando a importância de compreender o funcionamento interno de mecanismos de concorrência e como o gerenciamento de threads pode impactar diretamente o desempenho de aplicações reais.

7. Anexos

Segue prints dos resultados obtidos no terminal do IntelliJ Ultimate de meu computador rodando os códigos-fonte:

Modelo N:M:

<img width="1213" height="412" alt="image" src="https://github.com/user-attachments/assets/2f848ed8-d408-4f85-9656-80520d90ce9a" />

Modelo 1:1:

<img width="1210" height="356" alt="image" src="https://github.com/user-attachments/assets/6ccaa407-ff4d-4327-855f-b267c122ac6c" />
