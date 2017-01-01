package demo.sugarorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMsg = (TextView) findViewById(R.id.txtMsg);

        try{
            insertSomeDbData(); // create-populate table Company
            useRawQueryShowAll(); // display all records
            useRawQuery1(); // fixed SQL with no arguments
            useRawQuery2(); // parameter substitution
            useRawQuery3(); // manual string concatenation
            useSimpleQuery1(); // simple (parametric) query
            useSimpleQuery2(); // nontrivial 'simple query'
            showTable("Company"); // retrieve all rows from a table
            updateDB(); // use execSQL to update
            useInsertMethod(); // use insert method
            useUpdateMethod(); // use update method
            useDeleteMethod(); // use delete method
            dropTable();  // if need drop table Company
            txtMsg.append("\nAll Done!");
        } catch(Exception e){
            txtMsg.append("\nError onCreate: " + e.getMessage());
            finish();
        }
    }

    private void insertSomeDbData(){
        try
        {
            Company cp1 = new Company("AAA", "555-1111");
            cp1.save();

            Company cp2 = new Company("BBB", "555-2222");
            cp2.save();

            Company cp3 = new Company("CCC", "555-3333");
            cp3.save();

            txtMsg.append("\n-insertSomeDbData - 3 rec. were inserted");
        } catch(Exception e){
            txtMsg.append("\nError insertSomeDbData: " + e.getMessage());
        }
    }

    private void useRawQueryShowAll(){
        try{
            List<Company> listCompany = Company.listAll(Company.class);
            txtMsg.append("\n-useRawQueryShowAll: ");
            for(int i = 0;i < listCompany.size();i++){
                txtMsg.append("\n ID= " + listCompany.get(i).getId() + ", " + listCompany.get(i).toString());
            }
        } catch(Exception e){
            txtMsg.append("\nError useRawQueryShowAll: " + e.getMessage());
        }
    }

    private void useRawQuery1(){
        try
        {
            List<Company> listCompany = Company.listAll(Company.class);
            if(listCompany.size() >  0){
                Long theRecID = listCompany.get(0).getId();
                Company cp = Company.findById(Company.class, theRecID);
                txtMsg.append("\n-useRawQuery1 - first ID " + theRecID);
                txtMsg.append("\n ID= " + theRecID + ", " +  cp.toString());
            } else{

            }
        } catch(Exception e){
            txtMsg.append("\nError useRawQuery1: " + e.getMessage());
        }
    }

    private void useRawQuery2(){
        try
        {
            List<Company> listCompany = Company.find(Company.class, "NAME_COMPANY = ?", "BBB");
            if(listCompany.size() >  0) {
                Company cp = listCompany.get(0);
                txtMsg.append("\n-useRawQuery2 Retrieved Name: " + cp.getNameCompany());
                txtMsg.append("\n-useRawQuery2 \n ID= " + cp.getId() + ", " + cp.toString());
            }
        } catch(Exception e){
            txtMsg.append("\nError useRawQuery2: " + e.getMessage());
        }
    }

    private void useRawQuery3(){
        try
        {
            String[] args = {"1", "BBB"};
            List<Company> listCompany = Company.findWithQuery(Company.class,
                    "Select * from Company where ID > "+ args[0] +" and NAME_COMPANY = '"+ args[1] +"'");
            if(listCompany.size() >  0) {
                Company cp = listCompany.get(0);
                txtMsg.append("\n-useRawQuery3 - Phone: " + cp.getPhone());
                txtMsg.append("\n-useRawQuery3 \n ID= " + cp.getId() + ", " + cp.toString());
            }
        } catch(Exception e){
            txtMsg.append("\nError useRawQuery3: " + e.getMessage());
        }
    }

    private void useSimpleQuery1(){
        try
        {
            List<Company> listCompany = Company.find(Company.class, "length(NAME_COMPANY) >= 3", null, null, null, null);
            if(listCompany.size() > 0){
                // get NameCompany from first data row
                Company cp = listCompany.get(0);
                txtMsg.append("\n-useSimpleQuery1 - Total rec " + cp.getNameCompany());
                txtMsg.append("\n-useSimpleQuery1 \n ID= " + cp.getId() + ", " + cp.toString());
            }
        } catch(Exception e){
            txtMsg.append("\nError useSimpleQuery1: " + e.getMessage());
        }
    }

    private void useSimpleQuery2(){
        try
        {
            String whereCondition = "ID >= ?";
            String[] whereConditionArgs = {"1"};
            String groupBy = "NAME_COMPANY";
            String having = "count(*) <= 4";
            List<Company> listCompany = Company.find(Company.class, whereCondition,
                    whereConditionArgs, groupBy, having, null);
            if(listCompany.size() > 0){
                txtMsg.append("\n-useSimpleQuery2 - Total rec: " + listCompany.size());
                txtMsg.append("\n-useSimpleQuery2");
            }
            for(int i = 0;i < listCompany.size();i++){
                txtMsg.append("\n ID= " + listCompany.get(i).getId() + ", " + listCompany.get(i).toString());
            }
        } catch(Exception e){
            txtMsg.append("\nError useSimpleQuery2: " + e.getMessage());
        }
    }

    private void showTable(String tableName){
        try
        {
            List<Company> listCompany = Company.findWithQuery(Company.class,
                    "Select * from "+ tableName +"");
            txtMsg.append("\n-showTable:");
            for(int i = 0;i < listCompany.size();i++){
                txtMsg.append("\n ID= " + listCompany.get(i).getId() + ", " + listCompany.get(i).toString());
            }
        } catch(Exception e){
            txtMsg.append("\nError showTable: " + e.getMessage());
        }
    }

    private void updateDB(){
        try
        {
            txtMsg.append("\n-updateDB");
            String thePhoneNo = "555-1111";
            Company.executeQuery("update Company set NAME_COMPANY = (NAME_COMPANY || 'XXX')"
                    + " where PHONE = '"+ thePhoneNo +"'");
            showTable("Company");
        } catch(Exception e){
            txtMsg.append("\nError updateDB: " + e.getMessage());
        }
    }

    private void useInsertMethod(){
        try
        {
            Company cp = new Company();
            cp.setNameCompany("ABC");
            cp.setPhone("555-4444");
            Long rowPosition = cp.save();
            txtMsg.append("\n-useInsertMethod rec added at: "+ rowPosition);
            showTable("Company");
        } catch(Exception e){
            txtMsg.append("\nError useInsertMethod: " + e.getMessage());
        }
    }

    private void useUpdateMethod(){
        try
        {
            String[] whereArgs = {"1"};
            List<Company> listCompany = Company.find(Company.class, "ID = ?", whereArgs);
            if(listCompany.size() > 0){
                Company cp = listCompany.get(0);
                cp.setNameCompany("Maria");
                Long recAffected = cp.save();
                txtMsg.append("\n-useUpdateMethod - Rec Affectec "+ recAffected);
                showTable("Company");
            }
        } catch(Exception e){
            txtMsg.append("\nError useUpdateMethod: " + e.getMessage());
        }
    }

    private void useDeleteMethod(){
        try
        {
            String[] whereArgs = {"2"};
            List<Company> listCompany = Company.find(Company.class, "ID = ?", whereArgs);
            if(listCompany.size() > 0){
                Company cp = listCompany.get(0);
                Long recAffected = cp.getId();
                cp.delete();
                txtMsg.append("\n-useDeleteMethod - Rec Affectec "+ recAffected);
                showTable("Company");
            }
        } catch(Exception e){
            txtMsg.append("\nError useDeleteMethod: " + e.getMessage());
        }
    }

    private void dropTable(){
        try
        {
            Company.executeQuery(" Drop table Company;");
            txtMsg.append("\n-dropTable - dropped!!");
        } catch(Exception e){
            txtMsg.append("\nError dropTable: " + e.getMessage());
            finish();
        }
    }
}
