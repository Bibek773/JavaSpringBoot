    package BasicAndOop.proggrams.classandobj;
    import java.util.*;
    class BankAccount{
        String AcNo, owner;
        double balance=0;
        void deposit(double amount){
            balance=balance+amount;
            System.out.println("Successfully deposited "+ amount);
        }
        void checkBlc(){
            System.out.println("Dear "+ this.owner +", Your Current balance on Account number "+ AcNo + " is " + balance);
        }

    }
    public class BankAcc {
        public static void main(String[] args){
            Scanner input = new Scanner(System.in);
            BankAccount acc = new BankAccount();
            System.out.println("Enter Account Number");
            acc.AcNo=input.nextLine();
            System.out.println("Enter Your Name:");
            acc.owner=input.nextLine();
            while(true){
                System.out.println("Enter 1~3\n 1. Check Balance \n 2. Deposit amount \n3. Exit");
                int a= input.nextInt();
                if (a==1){
                    acc.checkBlc();
                } else if (a==2) {
                    System.out.println("Enter amount");
                    double amt = input.nextInt();
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
