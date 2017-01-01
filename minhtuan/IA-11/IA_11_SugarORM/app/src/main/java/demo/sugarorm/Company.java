package demo.sugarorm;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Lam Phong on 4/24/2016.
 */
@Table
public class Company extends SugarRecord {

    private String NameCompany;
    private String Phone;

    public Company(){}

    public Company(String nameCompany, String phone){
        this.NameCompany = nameCompany;
        this.Phone = phone;
    }

    public String getNameCompany(){
        return NameCompany;
    }

    public void setNameCompany(String nameCompany){
        this.NameCompany = nameCompany;
    }

    public String getPhone(){
        return Phone;
    }

    public void setPhone(String phone){
        this.Phone = phone;
    }

    @Override
    public String toString(){
        return "Name company= " + this.NameCompany + ", Phone=" + this.Phone;
    }
}
