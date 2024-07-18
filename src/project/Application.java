package project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.Contract;
import service.ContractService;



public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter fmt =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre os dados do contrato:");
		System.out.print("Número: ");
		int contractNumber = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Data (dd/MM/yyyy):");
		LocalDate contractDate = LocalDate.parse(scanner.nextLine(), fmt);
		System.out.print("Valor contrato: ");
		double contractValue = scanner.nextDouble();
		System.out.print("Entre com o número de parcelas: ");
		int contractInstallments = scanner.nextInt();
		Contract contract = new Contract(contractNumber,contractDate, contractValue);
		ContractService service = new ContractService();
		service.processContract(contract, contractInstallments);
		
		System.out.println("Parcelas: ");
		System.out.println(contract);
		
		scanner.close();
		
	}

}
