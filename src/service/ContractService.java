package service;

import java.time.LocalDate;

import entities.Contract;
import entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, Integer months) {
	
		
		double baseInstallmentValue = contract.getTotalValue()  / months;
		
		for (int i = 1; i <= months; i++) {
			
			double interest = onlinePaymentService.interest(baseInstallmentValue, i);
			double paymentFee = onlinePaymentService.paymentFee(interest + baseInstallmentValue);
			double amount = baseInstallmentValue + interest + paymentFee;

			LocalDate dueDate = contract.getDate().plusMonths(i);

			Installment installment = new Installment(dueDate, amount); 
			contract.setInstallments(installment);
		}
	}
}