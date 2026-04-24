package BasicAndOop.proggrams.AbstractionAndInterface;

abstract class Appliance{
    abstract void operate();
    void powerOn(){
        System.out.println(" Power is on Now");
    }
}
class WashingMachine extends Appliance {

    @Override
    void operate(){
        System.out.println("washing clothes");
    }
}
class Microwave extends Appliance {

    @Override
    void operate(){
        System.out.println("cooking food");
    }
}
public class example {
    public static void main(String[] args){
        System.out.println("hello");

        Appliance a1= new WashingMachine();
        Appliance a2 = new Microwave();

        a1.powerOn();
        a1.operate();
        a2.powerOn();
        a2.operate();

    }
}
