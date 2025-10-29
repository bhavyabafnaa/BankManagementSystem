package bank.domain;
import bank.domain.interest.*;

public class CurrentAccount extends BankAccount {


	private static final long serialVersionUID = 1L;
	String tradeLicenseNumber;
	//	String type;
	private String businessType;   // e.g., "Retail", "Services", "IT"
	private String gstNumber;      // optional GST number

	public CurrentAccount(String name, double balance, double min_balance, String businessType, String gstNumber) throws Exception {
		super(name, balance, 5000);
		this.tradeLicenseNumber = tradeLicenseNumber;
		this.businessType = businessType;
		this.gstNumber = gstNumber;
		if (balance > 100000) {
			throw new Exception("Initial balance for Current Account cannot exceed ₹1,00,000.");
		}

	}

	public CurrentAccount(String name, double balance, double min_balance) throws Exception {
		this(name, balance, min_balance, "N/A", "N/A");
	}

}
