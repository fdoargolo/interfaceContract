package service;

import java.time.LocalDate;

import entities.Contract;
import entities.Installment;

public class ContractService {
	public void processContract(Contract contract, Integer months) {
		OnlinePaymentService service = new PaypalService();
		
		for (int i = 1; i <= months; i++) {
			double baseInstallmentValue = contract.getTotalValue()  / months;
			double interest = service.interest(baseInstallmentValue, i);
			double paymentFee = service.paymentFee(interest + baseInstallmentValue);
			double amount = baseInstallmentValue + interest + paymentFee;

			LocalDate dueDate = contract.getDate().plusMonths(i);

			Installment installment = new Installment(dueDate, amount); 
			contract.setInstallments(installment);
		}
	}
}