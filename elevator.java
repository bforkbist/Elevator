import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Elevator{
    static HashMap<Integer,Integer> floorMap = new HashMap<>();
    static List<Integer> floorlist = new ArrayList<>();
    static HashMap<Integer,String> passengerMap =new  HashMap<>();
    static Scanner in = new Scanner(System.in);
    public static String go;
    public static int current = 0;
    public static int myfloor = 0;
    public static int maxfloor = 9;
//call the function or code
    public static void main(String[] args) throws InterruptedException {
        floor();
        passenger();
    }
//define the floors
    public static void floor() {
        for ( int i = 0; i <= maxfloor; i++) {
            floorlist.add(i);
            floorMap.put(i,0);
        }
    }
//fuction called when lift opens
    public static void Open(int j,char t) throws InterruptedException{
        Thread.sleep(1000);
        System.out.println("doors are opening for "+j+" make space");
        Thread.sleep(2000);
        addPassenger(j,t);
    }
//add passenger in between
    public static void addPassenger(int myfloor,char t) {
        System.out.println(floorMap);
        System.out.println("enter the passenger boarded");
        int n= in.nextInt();
        current=myfloor;
        System.out.println(current);
        // System.out.println("call by typing D or U");
        // go=in.next().toLowerCase();
        for(int i = 0 ; i < n ; i++ ){
            System.out.println("enter the floor you want to go passenger "+(i+1));
            myfloor=in.nextInt();
            if(current==myfloor){
                System.out.println("we are on the same floor /n try filling details again");
                // passenger();
            }else if(myfloor>current && t=='d'){
                System.out.println("the lift is going down");
            }else if(myfloor>current && t=='u'){
                floorMap.put(myfloor,floorMap.get(myfloor)+1);
            }else if(myfloor<current && t=='u'){
                System.out.println("the lift is going up");
            }else if(myfloor>current && t=='d'){
                floorMap.put(myfloor,floorMap.get(myfloor)+1);
            }
                
        }
    }
//initial passenger
    public static void passenger() throws InterruptedException{
        System.out.println("lift is on "+current+" floor");
        System.out.println("enter your floor");
        myfloor=in.nextInt();
        if(current > myfloor)
            System.out.println("lift is coming down");
        else if(current<myfloor) 
            System.out.println("lift is coming up");
        else 
            System.out.println("doors are opening");
        Thread.sleep(myfloor*1000);
        char t = 'u';
        addPassenger(myfloor,t);
        System.out.println(floorMap);
        mechanism();
    }
//check whether up or down 
    public static String check(int m) throws InterruptedException{
        int flag1=0;
        int flag2=0;
        for(int i=0;i<m;i++){
            if (floorMap.get(i)>0){
                flag1=1;
            }
        }
        for(int i=m;i<=maxfloor;i++){
            if (floorMap.get(i)>0){
                flag2=1;
            }
        }
        if(flag1==1)
            return "down";
        else if(flag2==1)
            return "up";
        else return "";
    }
//when lift is going up
    public static void goingDown() throws InterruptedException{
        System.out.println("hey going down");
        char t ="d";
        for(int i=current;i>=0 ; i--){
            Thread.sleep(2000);
            if(floorMap.get(i)!=0){
                floorMap.put(i, 0);
                Open(i,t);
            }
        }
    }
// when lift is going down
    public static void goingUp()throws InterruptedException {
        System.out.println("hey going up");
        char t ='u';
        for(int i=current;i<=maxfloor ; i++){
            Thread.sleep(2000);
            if(floorMap.get(i)!=0){
                floorMap.put(i, 0);
                Open(i,t);
            }
        }
    }
//mechanism to decide the user data
    public static void mechanism() throws InterruptedException {
        int n = current;
        String s = check(n);
        if(s.length()==4){
            System.out.println("the lift is going down");
            goingDown();
        }else if (s.length()==2){
            System.out.println("the lift is going up");
            goingUp();
        }else{
            System.out.println("we are on the same floor as pressed");
            System.out.println("where do you want to go u or d");
            char r=in.next().toLowerCase().charAt(0);
            if(r=='u'){
                goingUp();
            }else if(r=='d'){
                goingDown();
            }else {
                System.out.println("invalid input");
                mechanism();
            }
        }

    }
}
