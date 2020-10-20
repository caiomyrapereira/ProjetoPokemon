package Rpg;

import java.util.Scanner;

import java.util.List;

public class Main {

	static int rand(int i, int j) {
		return (int) Math.ceil(Math.random() * (i - j + 1)) - 1 + j;
	}

	public static void main(String args[]) {
		String nome;
		int opcao, partida = 1, inimigo;
		Scanner leia = new Scanner(System.in);

		// pok�mons
		Charmander charmander = new Charmander("");
		Sandshrew sandshrew = new Sandshrew("");
		Caterpie caterpie = new Caterpie("");
		Charmander charmander2 = new Charmander("");
		Sandshrew sandshrew2 = new Sandshrew("");
		Caterpie caterpie2 = new Caterpie("");
		Diglett diglett = new Diglett("");
		Eternatus eternatus = new Eternatus("");
		Abra abra = new Abra("");
		Squirtle squirtle = new Squirtle("");

		List<Pokemon> pokemons = List.of(charmander, sandshrew, caterpie);

		// List<Pokemon> inimigos =
		// List.of(sandshrew,diglett,eternatus,abra,squirtle,charmander,caterpie);

		System.out.print("Digite o seu nome:");
		nome = leia.nextLine();

		do {
			System.out.println(nome
					+ ", voc� est� preparado para a sua batalha? \n Para come�ar, escolha o seu Pokemon \n 1- Charmander \n 2- Sandshrew \n 3- Caterpie");
			System.out.print("Escolhe uma op��o:");
			opcao = leia.nextInt();

			if (opcao != 1 && opcao != 2 && opcao != 3)
				System.out.println("\nOp��o inv�lida!\n");
		} while (opcao != 1 && opcao != 2 && opcao != 3);

		List<Pokemon> jogadores = List.of(pokemons.get(opcao - 1), sandshrew2, diglett, eternatus, abra, squirtle,
				charmander2, caterpie2);
		inimigo = rand(1, 8);

		do {
			System.out.println("\n" + nome + ", desejar visualizar as informa��es do Pokemon ou batalhar? "
					+ "\n (1) para visualizar seu pok�mon \n (2) para visualizar seu inimigo \n (3) escolher outro inimigo  \n (4) para duelar \n (5) sair do jogo ");
			System.out.print("Escolhe uma op��o:");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				jogadores.get(0).imprimirInfo();
				break;
			case 2:
				jogadores.get(inimigo).imprimirInfo();
				break;
			case 3:
				do {
					inimigo = rand(1, 8);
					if (jogadores.get(inimigo).getVida() > 0)
						break;
				} while (true);
				break;
			case 4:

				// batalha
				do {
					partida++;

					if (partida % 2 * inimigo == 0) {
						do {

							System.out.print("\nO jogador: " + nome + "\n ( 1 ) Ataca \n ( 2 ) Recuar ");
							System.out.print("\nEscolhe uma op��o:");
							opcao = leia.nextInt();
							System.out.print("\n");

							if (opcao != 1 && opcao != 2)
								System.out.println("\nOp��o inv�lida!\n");

						} while (opcao != 1 && opcao != 2);
					}

					if (opcao == 1) {
						jogadores.get((partida - 1) % 2 * inimigo).recebeDano(jogadores.get((partida) % 2 * inimigo)
								.ataque(jogadores.get(partida % 2 * inimigo).getTipo()));
						System.out.println(jogadores.get(partida % 2 * inimigo).getNome() + " : "
								+ jogadores.get(partida % 2 * inimigo).getVida() + "Hp ");
					}

				} while ((jogadores.get(partida % 2 * inimigo).getVida() > 0) && (opcao == 1));

				if (jogadores.get(partida % 2 * inimigo).getVida() == 0) {
					if (partida % 2 == 0)
						System.out.println("O seu pok�mon '" + jogadores.get(partida % 2).getNome() + "' desmaiou!");
					else
						System.out.println(
								"O pok�mon inimigo '" + jogadores.get(partida % 2 * inimigo).getNome() + "' desmaiou!");
				}

				break;
			}

			if (jogadores.get(inimigo).getVida() == 0) { // lop infinito.
				jogadores.get(0).recebeXp(100); // involuir o pok�mon do jogador
				System.out.println("Parab�nz " + nome + ", o seu pok�mon evoluir para " + jogadores.get(0).getNome());
				do {
					inimigo = rand(1, 8);
					if (jogadores.get(inimigo).getVida() > 0)
						break;
				} while (true);
			}

		} while (opcao != 5 && !(jogadores.get(0).getVida() == 0)
				&& !(jogadores.get(0).ultimaForma() == jogadores.get(0).getNome()));

	}
}
