package vn.com.minhtuan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDauCham;
    private Button btnHaiSoKhong;
    private Button btnSoKhong;
    private Button btnSoMot;
    private Button btnSoHai;
    private Button btnSoBa;
    private Button btnSoBon;
    private Button btnSoNam;
    private Button btnSoSau;
    private Button btnSoBay;
    private Button btnSoTam;
    private Button btnSoChin;

    private Button btnDauBang;
    private Button btnPhepCong;
    private Button btnPhepTru;
    private Button btnPhepNhan;
    private Button btnPhepChia;
    private Button btnPhanTram;
    private Button btnClear;

    private ImageButton btnXoa;

    private TextView txtPhepTinh;
    private TextView txtKetQua;

    private String SoThu1="";
    private String SoThu2="";
    private String PhepTinh="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1.Load layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2.Read view objects
        btnDauCham =(Button) findViewById(R.id.btnDauCham);
        btnHaiSoKhong=(Button) findViewById(R.id.btnHaiSoKhong);
        btnSoKhong=(Button) findViewById(R.id.btnSoKhong);
        btnSoMot=(Button) findViewById(R.id.btnSoMot);
        btnSoHai=(Button) findViewById(R.id.btnSoHai);
        btnSoBa=(Button) findViewById(R.id.btnSoBa);
        btnSoBon=(Button) findViewById(R.id.btnSoBon);
        btnSoNam=(Button) findViewById(R.id.btnSoNam);
        btnSoSau=(Button) findViewById(R.id.btnSoSau);
        btnSoBay=(Button) findViewById(R.id.btnSoBay);
        btnSoTam=(Button) findViewById(R.id.btnSoTam);
        btnSoChin=(Button) findViewById(R.id.btnSoChin);

        btnDauBang=(Button) findViewById(R.id.btnDauBang);
        btnPhepCong=(Button) findViewById(R.id.btnPhepCong);
        btnPhepTru=(Button) findViewById(R.id.btnPhepTru);
        btnPhepNhan=(Button) findViewById(R.id.btnPhepNhan);
        btnPhepChia=(Button) findViewById(R.id.btnPhepChia);
        btnPhanTram=(Button) findViewById(R.id.btnPhanTram);
        btnClear=(Button) findViewById(R.id.btnClear);

        btnXoa=(ImageButton) findViewById(R.id.btnXoa);

        txtPhepTinh=(TextView) findViewById(R.id.txtPhepTinh);
        txtKetQua=(TextView) findViewById(R.id.txtKetQua);

        //3.Setup event handlers
        btnDauCham.setOnClickListener(this);
        btnHaiSoKhong.setOnClickListener(this);
        btnSoKhong.setOnClickListener(this);
        btnSoMot.setOnClickListener(this);
        btnSoHai.setOnClickListener(this);
        btnSoBa.setOnClickListener(this);
        btnSoBon.setOnClickListener(this);
        btnSoNam.setOnClickListener(this);
        btnSoSau.setOnClickListener(this);
        btnSoBay.setOnClickListener(this);
        btnSoTam.setOnClickListener(this);
        btnSoChin.setOnClickListener(this);

        btnDauBang.setOnClickListener(this);
        btnPhepCong.setOnClickListener(this);
        btnPhepTru.setOnClickListener(this);
        btnPhepNhan.setOnClickListener(this);
        btnPhepChia.setOnClickListener(this);
        btnPhanTram.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        btnXoa.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btnHaiSoKhong:
                ClickHaiSoKhong();
                break;
            case R.id.btnSoKhong:
                ClickSoKhong();
                break;
            case R.id.btnSoMot:
                ClickSoMot();
                break;
            case R.id.btnSoHai:
                ClickSoHai();
                break;
            case R.id.btnSoBa:
                ClickSoBa();
                break;
            case R.id.btnSoBon:
                ClickSoBon();
                break;
            case R.id.btnSoNam:
                ClickSoNam();
                break;
            case R.id.btnSoSau:
                ClickSoSau();
                break;
            case R.id.btnSoBay:
                ClickSoBay();
                break;
            case R.id.btnSoTam:
                ClickSoTam();
                break;
            case R.id.btnSoChin:
                ClickSoChin();
                break;
            case R.id.btnPhepCong:
                ClickPhepCong();
                break;
            case R.id.btnPhepTru:
                ClickPhepTru();
                break;
            case R.id.btnPhepNhan:
                ClickPhepNhan();
                break;
            case R.id.btnPhepChia:
                ClickPhepChia();
                break;
            case R.id.btnDauBang:
                ClickDauBang();
                break;
            case R.id.btnClear:
                ClickClear();
                break;
            case R.id.btnPhanTram:
                ClickPhanTram();
                break;
            case R.id.btnXoa:
                ClickXoa();
                break;
            default:
                break;
        }
    }

    public void ClickHaiSoKhong(){
        if(PhepTinh==""){
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu1=SoThu1+"00";
            String txt=format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        }
        else {
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu2=SoThu2+"00";
            String txt1=format.format(Double.parseDouble(SoThu1));
            String txt2=format.format(Double.parseDouble(SoThu2));
            txtPhepTinh.setText(txt1+PhepTinh+txt2);
        }
    }

    public void ClickSoKhong(){
        if(PhepTinh==""){
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu1=SoThu1+"0";
            String txt=format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        }
        else {
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu2=SoThu2+"0";
            String txt1=format.format(Double.parseDouble(SoThu1));
            String txt2=format.format(Double.parseDouble(SoThu2));
            txtPhepTinh.setText(txt1+PhepTinh+txt2);
        }
    }

    public void ClickSoMot(){
        if(PhepTinh==""){
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu1=SoThu1+"1";
            String txt=format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        }
        else {
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu2=SoThu2+"1";
            String txt1=format.format(Double.parseDouble(SoThu1));
            String txt2=format.format(Double.parseDouble(SoThu2));
            txtPhepTinh.setText(txt1+PhepTinh+txt2);
        }
    }

    public void ClickSoHai(){
        if(PhepTinh==""){
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu1=SoThu1+"2";
            String txt=format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        }
        else {
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu2=SoThu2+"2";
            String txt1=format.format(Double.parseDouble(SoThu1));
            String txt2=format.format(Double.parseDouble(SoThu2));
            txtPhepTinh.setText(txt1+PhepTinh+txt2);
        }
    }

    public void ClickSoBa(){
        if(PhepTinh==""){
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu1=SoThu1+"3";
            String txt=format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        }
        else {
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu2=SoThu2+"3";
            String txt1=format.format(Double.parseDouble(SoThu1));
            String txt2=format.format(Double.parseDouble(SoThu2));
            txtPhepTinh.setText(txt1+PhepTinh+txt2);
        }
    }

    public void ClickSoBon(){
        if(PhepTinh==""){
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu1=SoThu1+"4";
            String txt=format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        }
        else {
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu2=SoThu2+"4";
            String txt1=format.format(Double.parseDouble(SoThu1));
            String txt2=format.format(Double.parseDouble(SoThu2));
            txtPhepTinh.setText(txt1+PhepTinh+txt2);
        }
    }

    public void ClickSoNam(){
        if(PhepTinh==""){
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu1=SoThu1+"5";
            String txt=format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        }
        else {
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu2=SoThu2+"5";
            String txt1=format.format(Double.parseDouble(SoThu1));
            String txt2=format.format(Double.parseDouble(SoThu2));
            txtPhepTinh.setText(txt1+PhepTinh+txt2);
        }
    }

    public void ClickSoSau(){
        if(PhepTinh==""){
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu1=SoThu1+"6";
            String txt=format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        }
        else {
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu2=SoThu2+"6";
            String txt1=format.format(Double.parseDouble(SoThu1));
            String txt2=format.format(Double.parseDouble(SoThu2));
            txtPhepTinh.setText(txt1+PhepTinh+txt2);
        }
    }

    public void ClickSoBay(){
        if(PhepTinh==""){
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu1=SoThu1+"7";
            String txt=format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        }
        else {
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu2=SoThu2+"7";
            String txt1=format.format(Double.parseDouble(SoThu1));
            String txt2=format.format(Double.parseDouble(SoThu2));
            txtPhepTinh.setText(txt1+PhepTinh+txt2);
        }
    }

    public void ClickSoTam(){
        if(PhepTinh==""){
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu1=SoThu1+"8";
            String txt=format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        }
        else {
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu2=SoThu2+"8";
            String txt1=format.format(Double.parseDouble(SoThu1));
            String txt2=format.format(Double.parseDouble(SoThu2));
            txtPhepTinh.setText(txt1+PhepTinh+txt2);
        }
    }

    public void ClickSoChin(){
        if(PhepTinh==""){
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu1=SoThu1+"9";
            String txt=format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        }
        else {
            DecimalFormat format=new DecimalFormat("###,###,###,###,###");
            SoThu2=SoThu2+"9";
            String txt1=format.format(Double.parseDouble(SoThu1));
            String txt2=format.format(Double.parseDouble(SoThu2));
            txtPhepTinh.setText(txt1+PhepTinh+txt2);
        }
    }

    public void ClickPhepCong(){
        DecimalFormat format=new DecimalFormat("###,###,###,###,###");
        PhepTinh="+";
        if(SoThu1=="")
            SoThu1="0";
        String txt=format.format(Double.parseDouble(SoThu1));
        txtPhepTinh.setText(txt+PhepTinh);
    }

    public void ClickPhepTru(){
        DecimalFormat format=new DecimalFormat("###,###,###,###,###");
        PhepTinh="-";
        if(SoThu1=="")
            SoThu1="0";
        String txt=format.format(Double.parseDouble(SoThu1));
        txtPhepTinh.setText(txt+PhepTinh);
    }

    public void ClickPhepNhan(){
        DecimalFormat format=new DecimalFormat("###,###,###,###,###");
        PhepTinh="x";
        if(SoThu1=="")
            SoThu1="0";
        String txt=format.format(Double.parseDouble(SoThu1));
        txtPhepTinh.setText(txt+PhepTinh);
    }

    public void ClickPhepChia(){
        DecimalFormat format=new DecimalFormat("###,###,###,###,###");
        PhepTinh="÷";
        if(SoThu1=="")
            SoThu1="0";
        String txt=format.format(Double.parseDouble(SoThu1));
        txtPhepTinh.setText(txt+PhepTinh);
    }

    public void ClickDauBang(){
        DecimalFormat format=new DecimalFormat("###,###,###,###,###");
        Double KetQua;
        if(PhepTinh=="+"){
            Double So1=Double.parseDouble(SoThu1);
            Double So2=Double.parseDouble(SoThu2);
            KetQua=So1+So2;
            String txtKQ=format.format(KetQua);
            txtKetQua.setText(String.valueOf(txtKQ));
        }
        else if(PhepTinh=="-"){
            Double So1=Double.parseDouble(SoThu1);
            Double So2=Double.parseDouble(SoThu2);
            KetQua=So1-So2;
            String txtKQ=format.format(KetQua);
            txtKetQua.setText(String.valueOf(txtKQ));
        }
        else if(PhepTinh=="x"){
            Double So1=Double.parseDouble(SoThu1);
            Double So2=Double.parseDouble(SoThu2);
            KetQua=So1*So2;
            String txtKQ=format.format(KetQua);
            txtKetQua.setText(String.valueOf(txtKQ));
        }
        else if(PhepTinh=="÷"){
            Double So1=Double.parseDouble(SoThu1);
            Double So2=Double.parseDouble(SoThu2);
            KetQua=So1/So2;
            String txtKQ=format.format(KetQua);
            txtKetQua.setText(String.valueOf(txtKQ));
        }
    }

    public void ClickClear(){
        SoThu1=SoThu2=PhepTinh="";
        txtPhepTinh.setText("");
        txtKetQua.setText("0");
    }

    public void ClickPhanTram(){
        if(SoThu1!="" && SoThu2!=""){
            if(PhepTinh=="+"){
                Double So1=Double.parseDouble(SoThu1);
                Double So2=Double.parseDouble(SoThu2);
                Double KetQua=So1+So2;
                KetQua=KetQua/100;
                String txtKQ=String.valueOf(KetQua);
                txtKetQua.setText(txtKQ);
            }
            else if(PhepTinh=="-"){
                Double So1=Double.parseDouble(SoThu1);
                Double So2=Double.parseDouble(SoThu2);
                Double KetQua=So1-So2;
                KetQua=KetQua/100;
                String txtKQ=String.valueOf(KetQua);
                txtKetQua.setText(txtKQ);
            }
            else if(PhepTinh=="x"){
                Double So1=Double.parseDouble(SoThu1);
                Double So2=Double.parseDouble(SoThu2);
                Double KetQua=So1*So2;
                KetQua=KetQua/100;
                String txtKQ=String.valueOf(KetQua);
                txtKetQua.setText(txtKQ);
            }
            else if(PhepTinh=="÷"){
                Double So1=Double.parseDouble(SoThu1);
                Double So2=Double.parseDouble(SoThu2);
                Double KetQua=So1/So2;
                KetQua=KetQua/100;
                String txtKQ=String.valueOf(KetQua);
                txtKetQua.setText(txtKQ);
            }
        }
        else if(SoThu1!="" && SoThu2==""){
            String txtKQ=String.valueOf(Double.parseDouble(SoThu1) / 100);
            txtKetQua.setText(txtKQ);
        }
    }

    public void ClickXoa(){
        DecimalFormat format=new DecimalFormat("###,###,###,###,###");
        if(SoThu2!=""){
            if(SoThu2.length()>1) {
                SoThu2 = SoThu2.substring(0, SoThu2.length() - 1);
                String txt1 = format.format(Double.parseDouble(SoThu1));
                String txt2 = format.format(Double.parseDouble(SoThu2));
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
            }
            else {
                SoThu2="";
                String txt = format.format(Double.parseDouble(SoThu1));
                txtPhepTinh.setText(txt + PhepTinh);
            }
        }
        else if(SoThu2=="" && PhepTinh!=""){
            PhepTinh="";
            String txt=format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        }
        else {
            if(SoThu1.length()>1) {
                SoThu1 = SoThu1.substring(0, SoThu1.length() - 1);
                String txt = format.format(Double.parseDouble(SoThu1));
                txtPhepTinh.setText(txt);
            }
            else {
                SoThu1="";
                SoThu2="";
                PhepTinh="";
                txtPhepTinh.setText("");
            }
        }
    }
}
