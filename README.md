# Aplicação:

>DELAY regulável em Main.delay (Atual 5000 milissegundos)

- Empresa possui um GERENTE que recebe tarefas.
- O gerente atribui a tarefa para o melhor PROGRAMADOR para a tarefa.
- PROGRAMADOR realiza a tarefa, escolhe o TESTADOR mais disponivel e envia para ele.
- TESTADOR valida a tarefa, a tarefa pode ser definida como realizada ou enviada de volta para o PROGRAMADOR para correção


## TAREFA:

	private int id;
	private int duracao;
	private int prioridade;
	private Nivel nivel;
	private Programador programador;
	private Testador testador;
	private TarefaStatus status;
	private int tempoGasto;
	private int tempoTeste;


### TAREFA STATUS

	PENDENTE, EM_DESENVOLVIMENTO, INDO_PARA_TESTE, EM_TESTE, CONCLUIDO

### NIVEL
    JUNIOR, PLENO, SENIOR;


## GERENTE:

	private List<Tarefa> tarefas;
	private Nivel nivelProgramador;
	private String nome;

Um por sistema

**Behaviour:**

1. Tick de 1 delay -> A cada ciclo ele busca se há novos programadores
2. Tick de 3 delay -> A cada ciclo ele cria uma nova tarefa com NIVEL, DURACAO e COMPLEXIDADE definidos aleatóriamente
 e envia para o PROGRAMADOR mais disponível


## PROGRAMADOR:

Inicialmente 2 no sistema, podendo ser adicionados mais durante a execução

**Setup:**
- Envia mensagem para o gerente ativo avisando que um novo programador entrou na empresa

**Behaviour:**

1. Tick de 1 delay -> Busca por novas tarefas vindas do GERENTE ou dos TESTADORES
2. Tick de 1 delay -> "Gasta" uma hora na tarefa atual, verifica se a tarefa foi finalizada, se sim, envia para o TESTADOR com menos tempo de tarefa pendente


## TESTADOR:

**Behaviour:**

1. Tick de 1 delay -> Busca por novas tarefas vindas dos PROGRAMADORES
2. Tick de 1 delay -> "Gasta" uma hora validando a tarefa atual, verifica se foi finalizado, 
