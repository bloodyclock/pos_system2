public class discount{
    public static int dis(int a,String b){
        int c =0;
        Sql_connect_promo co = new Sql_connect_promo();
        String data[] = co.connect(b);
        System.out.println(data[0]+data[1]+data[2]);
        if(data[2].equals("1")){
            c =a-Integer.valueOf(data[1]);
            System.out.println(c);
        }
        else if(data[2].equals("2")){
            c=a-a*Integer.valueOf(data[1])/100;
            System.out.println(c);
        }
        return c;
    }
}
