package vn.com.minhtuan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonDauCham;
    private Button mButtonHaiSoKhong;
    private Button mButtonSoKhong;
    private Button mButtonSoMot;
    private Button mButtonSoHai;
    private Button mButtonSoBa;
    private Button mButtonSoBon;
    private Button mButtonSoNam;
    private Button mButtonSoSau;
    private Button mButtonSoBay;
    private Button mButtonSoTam;
    private Button mButtonSoChin;

    private Button mButtonDauBang;
    private Button mButtonPhepCong;
    private Button mButtonPhepTru;
    private Button mButtonPhepNhan;
    private Button mButtonPhepChia;
    private Button mButtonPhanTram;
    private Button mButtonClear;

    private ImageButton mButtonXoa;

    private TextView mTextViewPhepTinh;
    private TextView mTextKetQua;

    private String mSoThu1 = "";
    private String mSoThu2 = "";
    private String mPhepTinh = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1.Load layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2.Read view objects
        mButtonDauCham = (Button) findViewById(R.id.btnDauCham);
        mButtonHaiSoKhong = (Button) findViewById(R.id.btnHaiSoKhong);
        mButtonSoKhong = (Button) findViewById(R.id.btnSoKhong);
        mButtonSoMot = (Button) findViewById(R.id.btnSoMot);
        mButtonSoHai = (Button) findViewById(R.id.btnSoHai);
        mButtonSoBa = (Button) findViewById(R.id.btnSoBa);
        mButtonSoBon = (Button) findViewById(R.id.btnSoBon);
        mButtonSoNam = (Button) findViewById(R.id.btnSoNam);
        mButtonSoSau = (Button) findViewById(R.id.btnSoSau);
        mButtonSoBay = (Button) findViewById(R.id.btnSoBay);
        mButtonSoTam = (Button) findViewById(R.id.btnSoTam);
        mButtonSoChin = (Button) findViewById(R.id.btnSoChin);

        mButtonDauBang = (Button) findViewById(R.id.btnDauBang);
        mButtonPhepCong = (Button) findViewById(R.id.btnPhepCong);
        mButtonPhepTru = (Button) findViewById(R.id.btnPhepTru);
        mButtonPhepNhan = (Button) findViewById(R.id.btnPhepNhan);
        mButtonPhepChia = (Button) findViewById(R.id.btnPhepChia);
        mButtonPhanTram = (Button) findViewById(R.id.btnPhanTram);
        mButtonClear = (Button) findViewById(R.id.btnClear);

        mButtonXoa = (ImageButton) findViewById(R.id.btnXoa);

        mTextViewPhepTinh = (TextView) findViewById(R.id.txtPhepTinh);
        mTextKetQua = (TextView) findViewById(R.id.txtKetQua);

        //3.Setup event handlers
        mButtonHaiSoKhong.setOnClickListener(this);
        mButtonSoKhong.setOnClickListener(this);
        mButtonSoMot.setOnClickListener(this);
        mButtonSoHai.setOnClickListener(this);
        mButtonSoBa.setOnClickListener(this);
        mButtonSoBon.setOnClickListener(this);
        mButtonSoNam.setOnClickListener(this);
        mButtonSoSau.setOnClickListener(this);
        mButtonSoBay.setOnClickListener(this);
        mButtonSoTam.setOnClickListener(this);
        mButtonSoChin.setOnClickListener(this);

        //add event calculator
        mButtonPhepCong.setOnClickListener(this);
        mButtonPhepTru.setOnClickListener(this);
        mButtonPhepNhan.setOnClickListener(this);
        mButtonPhepChia.setOnClickListener(this);

        //handler calculator
        mButtonDauBang.setOnClickListener(this);
        mButtonClear.setOnClickListener(this);
        mButtonPhanTram.setOnClickListener(this);
        mButtonXoa.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Double ketQua;
        DecimalFormat format;
        switch (v.getId()) {
            case R.id.btnHaiSoKhong:
                handleAddNumber("00");
                break;
            case R.id.btnSoKhong:
                handleAddNumber("0");
                break;
            case R.id.btnSoMot:
                handleAddNumber("1");
                break;
            case R.id.btnSoHai:
                handleAddNumber("2");
                break;
            case R.id.btnSoBa:
                handleAddNumber("3");
                break;
            case R.id.btnSoBon:
                handleAddNumber("4");
                break;
            case R.id.btnSoNam:
                handleAddNumber("5");
                break;
            case R.id.btnSoSau:
                handleAddNumber("6");
                break;
            case R.id.btnSoBay:
                handleAddNumber("7");
                break;
            case R.id.btnSoTam:
                handleAddNumber("8");
                break;
            case R.id.btnSoChin:
                handleAddNumber("9");
                break;
            case R.id.btnPhepCong:
                handlerAddCalculator("+");
                break;
            case R.id.btnPhepTru:
                handlerAddCalculator("-");
                break;
            case R.id.btnPhepNhan:
                handlerAddCalculator("x");
                break;
            case R.id.btnPhepChia:
                handlerAddCalculator(":");
                break;
            case R.id.btnDauBang:
                format = new DecimalFormat("###,###,###,###,###");
                if (mSoThu2 == null || mSoThu2.isEmpty() || mSoThu2.equalsIgnoreCase("")) {
                    return;
                }
                if (mPhepTinh == "+") {
                    Double So1 = Double.parseDouble(mSoThu1);
                    Double So2 = Double.parseDouble(mSoThu2);
                    ketQua = So1 + So2;
                    String txtKQ = format.format(ketQua);
                    mTextKetQua.setText(String.valueOf(txtKQ));
                } else if (mPhepTinh == "-") {
                    Double So1 = Double.parseDouble(mSoThu1);
                    Double So2 = Double.parseDouble(mSoThu2);
                    ketQua = So1 - So2;
                    String txtKQ = format.format(ketQua);
                    mTextKetQua.setText(String.valueOf(txtKQ));
                } else if (mPhepTinh == "x") {
                    Double So1 = Double.parseDouble(mSoThu1);
                    Double So2 = Double.parseDouble(mSoThu2);
                    ketQua = So1 * So2;
                    String txtKQ = format.format(ketQua);
                    mTextKetQua.setText(String.valueOf(txtKQ));
                } else if (mPhepTinh == "÷") {
                    Double So1 = Double.parseDouble(mSoThu1);
                    Double So2 = Double.parseDouble(mSoThu2);
                    ketQua = So1 / So2;
                    String txtKQ = format.format(ketQua);
                    mTextKetQua.setText(String.valueOf(txtKQ));
                }
                break;
            case R.id.btnClear:
                mSoThu1 = mSoThu2 = mPhepTinh = "";
                mTextViewPhepTinh.setText("");
                mTextKetQua.setText("0");
                break;
            case R.id.btnPhanTram:
                if (mSoThu1 != "" && mSoThu2 != "") {
                    if (mPhepTinh == "+") {
                        Double So1 = Double.parseDouble(mSoThu1);
                        Double So2 = Double.parseDouble(mSoThu2);
                        ketQua = So1 + So2;
                        ketQua = ketQua / 100;
                        String txtKQ = String.valueOf(ketQua);
                        mTextKetQua.setText(txtKQ);
                    } else if (mPhepTinh == "-") {
                        Double So1 = Double.parseDouble(mSoThu1);
                        Double So2 = Double.parseDouble(mSoThu2);
                        ketQua = So1 - So2;
                        ketQua = ketQua / 100;
                        String txtKQ = String.valueOf(ketQua);
                        mTextKetQua.setText(txtKQ);
                    } else if (mPhepTinh == "x") {
                        Double So1 = Double.parseDouble(mSoThu1);
                        Double So2 = Double.parseDouble(mSoThu2);
                        Double KetQua = So1 * So2;
                        KetQua = KetQua / 100;
                        String txtKQ = String.valueOf(KetQua);
                        mTextKetQua.setText(txtKQ);
                    } else if (mPhepTinh == "÷") {
                        Double So1 = Double.parseDouble(mSoThu1);
                        Double So2 = Double.parseDouble(mSoThu2);
                        Double KetQua = So1 / So2;
                        KetQua = KetQua / 100;
                        String txtKQ = String.valueOf(KetQua);
                        mTextKetQua.setText(txtKQ);
                    }
                } else if (mSoThu1 != "" && mSoThu2 == "") {
                    String txtKQ = String.valueOf(Double.parseDouble(mSoThu1) / 100);
                    mTextKetQua.setText(txtKQ);
                }
                break;
            case R.id.btnXoa:
                format = new DecimalFormat("###,###,###,###,###");
                if (mSoThu2 != "") {
                    if (mSoThu2.length() > 1) {
                        mSoThu2 = mSoThu2.substring(0, mSoThu2.length() - 1);
                        String txt1 = format.format(Double.parseDouble(mSoThu1));
                        String txt2 = format.format(Double.parseDouble(mSoThu2));
                        mTextViewPhepTinh.setText(txt1 + mPhepTinh + txt2);
                    } else {
                        mSoThu2 = "";
                        String txt = format.format(Double.parseDouble(mSoThu1));
                        mTextViewPhepTinh.setText(txt + mPhepTinh);
                    }
                } else if (mSoThu2 == "" && mPhepTinh != "") {
                    mPhepTinh = "";
                    String txt = format.format(Double.parseDouble(mSoThu1));
                    mTextViewPhepTinh.setText(txt);
                } else {
                    if (mSoThu1.length() > 1) {
                        mSoThu1 = mSoThu1.substring(0, mSoThu1.length() - 1);
                        String txt = format.format(Double.parseDouble(mSoThu1));
                        mTextViewPhepTinh.setText(txt);
                    } else {
                        mSoThu1 = "";
                        mSoThu2 = "";
                        mPhepTinh = "";
                        mTextViewPhepTinh.setText("");
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * click button input number to view
     *
     * @param number
     */
    private void handleAddNumber(String number) {
        if (mPhepTinh == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###");
            mSoThu1 = mSoThu1 + number;
            String txt = format.format(Double.parseDouble(mSoThu1));
            mTextViewPhepTinh.setText(txt);
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###");
            mSoThu2 = mSoThu2 + number;
            String txt1 = format.format(Double.parseDouble(mSoThu1));
            String txt2 = format.format(Double.parseDouble(mSoThu2));
            mTextViewPhepTinh.setText(txt1 + mPhepTinh + txt2);
        }
    }

    private void handlerAddCalculator(String event) {
        DecimalFormat format = new DecimalFormat("###,###,###,###,###");
        mPhepTinh = event;
        if (mSoThu1 == "")
            mSoThu1 = "0";
        String txt = format.format(Double.parseDouble(mSoThu1));
        mTextViewPhepTinh.setText(txt + mPhepTinh);
    }
}
