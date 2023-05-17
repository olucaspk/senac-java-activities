import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        double investment = readDoubleInput("Investimento:");
        double averageEnergyConsumption = readDoubleInput("Consumo médio de energia:");
        double projectedEnergyGeneration = readDoubleInput("Geração de energia prevista:");
        double TUSDRate = readDoubleInput("Valor da tarifa TUSD:");
        double TERate = readDoubleInput("Valor da tarifa TE:");
        double currentEnergyCost = readDoubleInput("Custo atual de energia:");
        double returnPercentage = readDoubleInput("Se pago à vista, qual o percentual de retorno do valor investido?");
        double interestRate = readDoubleInput("Se financiado, qual o percentual de juros?");
        int financingTerm = readIntInput("Prazo do financiamento (em meses)");
        double monthlyPayment = readDoubleInput("Valor da prestação mensal:");
        double totalAmountPaid = readDoubleInput("Valor total pago:");
        double paymentDifference = readDoubleInput("Diferença entre a prestação mensal e o valor atual da conta:");

        double energySavings = (averageEnergyConsumption - projectedEnergyGeneration) * (TUSDRate + TERate);
        double annualSavings = energySavings * 12;

        double payback;

        if (investment > 0) {
            if (returnPercentage > 0) {
                double investedAmount = investment + investment * (returnPercentage / 100);
                payback = investedAmount / annualSavings;
            } else if (interestRate > 0) {
                double financedAmount = totalAmountPaid - investment;
                double totalInterest = financedAmount * (interestRate / 100);
                double amountPaidWithInterest = financedAmount + totalInterest;
                double remainingAmount = amountPaidWithInterest - paymentDifference;
                double remainingAmountAnnual = remainingAmount * 12;
                payback = amountPaidWithInterest / (annualSavings - remainingAmountAnnual);
            } else {
                payback = investment / annualSavings;
            }

            System.out.println("O período de payback do investimento é de aproximadamente " + payback + " anos.");
        } else {
            System.out.println("O valor do investimento deve ser maior que zero.");
        }

        scanner.close();
    }

    private static double readDoubleInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextDouble();
    }

    private static int readIntInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextInt();
    }
}
