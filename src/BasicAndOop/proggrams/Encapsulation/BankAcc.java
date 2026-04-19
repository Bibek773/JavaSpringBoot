package BasicAndOop.proggrams.Encapsulation;

import java.util.Scanner;

class BankAccount{
    private String AcNo;
    private String owner;
    private double balance=0;
    //Getter:
    public String getAcNo(){
        return AcNo;
    }
    public String getOwner(){
        return owner;
    }
    public double getBlc(){
        return balance;
    }

    //Setter
    public void setAcNo(String AcNo){
        this.AcNo = AcNo;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }
    public void deposit(double blc){
        if(blc<=0){
            System.out.println("Please Enter valid Amount.....\nAmount Can't be Negative");
            return;
        }
        else {
            this.balance=this.balance+blc;
            System.out.println("Successfully Deposited "+ blc+ ".");

        }
    }


}
public class BankAcc {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        BankAccount acc = new BankAccount();
        System.out.println("Enter Account Number");
        String AcNo=input.nextLine();
        acc.setAcNo(AcNo);
        System.out.println("Enter Your Name:");
        String owner=input.nextLine();
        acc.setOwner(owner);
        while(true){
            System.out.println("Enter 1~3\n 1. Check Balance \n 2. Deposit amount \n3. Exit");
            int a= input.nextInt();
            if (a==1){
                String name = acc.getOwner();
                String Acc = acc.getAcNo();
                double blc= acc.getBlc();
                System.out.println("Dear "+ name+", Your Balance for Account Number "+ Acc+ " is RS"+ blc+".");
            } else if (a==2) {
                System.out.println("Enter amount");
                double amt = input.nextDouble();
                acc.deposit(amt);
            } else if (a==3) {
                break;
            }
            else {
                System.out.println("Enter 1~3");
            }
        }



    }
}
