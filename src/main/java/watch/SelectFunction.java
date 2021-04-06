package watch;

import javax.naming.ldap.Control;

public class SelectFunction {
    private static int count;
    private static int index=0;
    private static int i=0;
    private static boolean[] functionList = new boolean[6];
    private static String[] functionName ={"TimeKeeping","Timer","Alarm","StopWatch","D+Day","Fitness"};
    InstManager instManager;

    public SelectFunction() {
        this.functionList[0]=true;
        this.functionList[1]=true;
        this.functionList[2]=false;
        this.functionList[3]=true;
        this.functionList[4]=false;
        this.functionList[5]=true;
        count=3;
    }


    public static String getfunctionName()
    {
        String name = functionName[i];
        i++;
        if(i==6){
            i=0;}
        return name;
    }


    public static int getfunctionList()
    {
        do {
            index++;
            if(index==6){
                index = 0;}
        }while(functionList[index]!=true);

        int nextFunc = index;
        return nextFunc;
    }

    //>해당인덱스의 기능이 선택된 상태인지 bool값을 반환해주는 함수.
    public boolean getFunctionListBool(int i){
        return functionList[i];
    }
    //<

    public boolean checkFirstDisplay(String status)
    {
        if(status.equals("TimeKeeping")){
            return true;}
        else{
            return false;}
    }
    public boolean checkDefaultDisplay()
    {
        instManager = InstManager.getInstance();
        boolean is_stop[] = {instManager.getTimer().getIs_stop(),instManager.getStopwatch().getIs_stop(), instManager.getFitness().getIs_stop()};

        if((is_stop[0]==true)&&(is_stop[1]==true)&&(is_stop[2]==true)){
            return true;}
        else{
            System.out.println("타이머 is_stop : "+ is_stop[0]);
            System.out.println("스탑워치 is_stop : "+ is_stop[1]);
            System.out.println("피트니스 is_stop : "+ is_stop[2]);
            return false;}
    }

    public static boolean setFunctionList(int index)
    {
        if(functionList[index])
        {
            functionList[index] = false;
            count--;
        }
        else
        {
            functionList[index] = true;
            count++;
        }
        return functionList[index];
    }

    public boolean check_four_fuction()
    {
        if(count == 3){
            return true;}
        else{
            return false;}
    }

}