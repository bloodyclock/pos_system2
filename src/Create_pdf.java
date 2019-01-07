import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Phaser;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
public class Create_pdf {
    SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
    int count =0;
    Date date = new Date();
    String RESULT = "C:/Users/Bas/Desktop/Result/"+formatter.format(date)+".pdf";
    public void Cre_pdf(String Itemdata,String Sale_name,int sum,int dis_sum) throws FileNotFoundException, DocumentException {
       String listItemStr="";
        for(int i=0;i<70;i++){
            listItemStr+="-";
        }
        listItemStr+="\nID       Product                    Qty                Price\n";// 27 space
        for(int i=0;i<70;i++){
            listItemStr+="-";
        }
        listItemStr+="\n";
        Document document = new Document(PageSize.LETTER);
        Font f=new Font(Font.FontFamily.COURIER);
        String[] splitStr = Itemdata.split("\\s+");
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        Chunk blank = new Chunk("       ");
        document.open();
        int space;
        Phrase p = new Phrase("",f);
        p.add(listItemStr);
        for(int i =0;i<splitStr.length;i++) {
            System.out.println(splitStr[i]);//not in program;
            p.add(splitStr[i]);
            count++;
            if(count==2){
                space = 26-splitStr[i].length();//fix space
                for (int j=0;j<space;j++){
                    p.add(" ");
                }
            }
            else if (count==3){
                space = 18-splitStr[i].length();//fix space
                for (int j=0;j<space;j++){
                    p.add(" ");
                }
            }
            else{
            p.add(new Chunk(blank));
            }
            if(count==4){
                count=0;
            }
            if((i+1)%4==0)
                p.add("\n");
        }

        listItemStr="";
        for(int i=0;i<70;i++){
            listItemStr+="-";
        }
        p.add(listItemStr);
        p.add("\nSubTotal");
        listItemStr="";
        for(int i=0;i<47;i++){
            listItemStr+=" ";
        }
        p.add(listItemStr);
        p.add(String.valueOf(sum));
        p.add("\nDiscount");
        listItemStr="";
        for(int i=0;i<47;i++){
            listItemStr+=" ";
        }
        p.add(listItemStr);
        p.add(String.valueOf(dis_sum));
        listItemStr="";
        for(int i=0;i<70;i++){
            listItemStr+="-";
        }
        p.add("\n"+listItemStr);
        p.add("\nTotal");
        listItemStr="";
        for(int i=0;i<50;i++){
            listItemStr+=" ";
        }
        p.add(listItemStr);
        p.add(String.valueOf(sum-dis_sum));
        document.add(p);
        document.close();

    }
}
