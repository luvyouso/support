package minhtuan.com.vn;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

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

    private Button btnMC;
    private Button btnMR;
    private Button btnMCong;
    private Button btnMTru;

    private ImageButton btnXoa;

    private TextView txtPhepTinh;
    private TextView txtKetQua;

    private String SoThu1 = "";
    private String SoThu1Cham = "";
    private int flag1 = 0;
    private String SoThu2 = "";
    private String SoThu2Cham = "";
    private int flag2 = 0;
    private String PhepTinh = "";

    private Double KetQua;

    public static final String NUMBER_KEY = "LamPhong";
    private String KeyFormat = "DauCham";
    private String KeyBackground = "BLACK";

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1.Load layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //2.Read view objects
        LoadSuViews();

        //3.Setup event handlers
        setUpEventHandlers();

        //4.Setting
        Settings();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnSetting:
                gotoSettingScreen();
                return true;
            case R.id.btnAbout:
                gotoAboutScreen();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void gotoSettingScreen() {
        Intent intentS = new Intent(this, SettingActivity.class);
        intentS.putExtra(NUMBER_KEY, KeyBackground + "," + KeyFormat);
        startActivity(intentS);
    }

    private void gotoAboutScreen() {
        Intent intentA = new Intent(this, AboutActivity.class);
        startActivity(intentA);
    }

    private void LoadSuViews() {
        btnDauCham = (Button) findViewById(R.id.btnDauCham);
        btnHaiSoKhong = (Button) findViewById(R.id.btnHaiSoKhong);
        btnSoKhong = (Button) findViewById(R.id.btnSoKhong);
        btnSoMot = (Button) findViewById(R.id.btnSoMot);
        btnSoHai = (Button) findViewById(R.id.btnSoHai);
        btnSoBa = (Button) findViewById(R.id.btnSoBa);
        btnSoBon = (Button) findViewById(R.id.btnSoBon);
        btnSoNam = (Button) findViewById(R.id.btnSoNam);
        btnSoSau = (Button) findViewById(R.id.btnSoSau);
        btnSoBay = (Button) findViewById(R.id.btnSoBay);
        btnSoTam = (Button) findViewById(R.id.btnSoTam);
        btnSoChin = (Button) findViewById(R.id.btnSoChin);

        btnDauBang = (Button) findViewById(R.id.btnDauBang);
        btnPhepCong = (Button) findViewById(R.id.btnPhepCong);
        btnPhepTru = (Button) findViewById(R.id.btnPhepTru);
        btnPhepNhan = (Button) findViewById(R.id.btnPhepNhan);
        btnPhepChia = (Button) findViewById(R.id.btnPhepChia);
        btnPhanTram = (Button) findViewById(R.id.btnPhanTram);
        btnClear = (Button) findViewById(R.id.btnClear);

        btnMR = (Button) findViewById(R.id.btnMR);
        btnMC = (Button) findViewById(R.id.btnMC);
        btnMCong = (Button) findViewById(R.id.btnMCong);
        btnMTru = (Button) findViewById(R.id.btnMTru);

        btnXoa = (ImageButton) findViewById(R.id.btnXoa);

        txtPhepTinh = (TextView) findViewById(R.id.txtPhepTinh);
        txtKetQua = (TextView) findViewById(R.id.txtKetQua);

        layout = (LinearLayout) findViewById(R.id.layout);
    }

    private void setUpEventHandlers() {
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

        btnMR.setOnClickListener(this);
        btnMC.setOnClickListener(this);
        btnMCong.setOnClickListener(this);
        btnMTru.setOnClickListener(this);

        btnXoa.setOnClickListener(this);
    }

    private void Settings() {
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String text = extra.getString(NUMBER_KEY, "0");
            String[] txt = text.split(",");
            if (txt[0].equals("BLACK")) {
                layout.setBackgroundColor(Color.BLACK);
                KeyBackground = "BLACK";
                if (txt[1].equals("DauCham"))
                    KeyFormat = "DauCham";
                else
                    KeyFormat = "DauPhay";
            } else {
                layout.setBackgroundColor(Color.WHITE);
                btnMR.setTextColor(Color.WHITE);
                btnMC.setTextColor(Color.WHITE);
                btnMCong.setTextColor(Color.WHITE);
                btnMTru.setTextColor(Color.WHITE);
                btnClear.setTextColor(Color.WHITE);
                btnPhanTram.setTextColor(Color.WHITE);
                btnPhepCong.setTextColor(Color.WHITE);
                btnPhepTru.setTextColor(Color.WHITE);
                btnPhepNhan.setTextColor(Color.WHITE);
                btnPhepChia.setTextColor(Color.WHITE);
                btnDauBang.setTextColor(Color.WHITE);
                btnHaiSoKhong.setTextColor(Color.WHITE);
                btnSoKhong.setTextColor(Color.WHITE);
                btnDauCham.setTextColor(Color.WHITE);
                btnSoMot.setTextColor(Color.WHITE);
                btnSoHai.setTextColor(Color.WHITE);
                btnSoBa.setTextColor(Color.WHITE);
                btnSoBon.setTextColor(Color.WHITE);
                btnSoNam.setTextColor(Color.WHITE);
                btnSoSau.setTextColor(Color.WHITE);
                btnSoBay.setTextColor(Color.WHITE);
                btnSoTam.setTextColor(Color.WHITE);
                btnSoChin.setTextColor(Color.WHITE);
                KeyBackground = "WHITE";
                if (txt[1].equals("DauCham"))
                    KeyFormat = "DauCham";
                else
                    KeyFormat = "DauPhay";
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnDauCham:
                ClickDauCham();
                break;
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

    private void ClickDauCham() {
        if (PhepTinh == "" && SoThu1Cham == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt + ",");
            } else
                txtPhepTinh.setText(txt + ".");
            SoThu1Cham = ".";
        } else if (PhepTinh != "" && SoThu2Cham == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            String txt1 = format.format(Double.parseDouble(SoThu1));
            String txt2 = format.format(Double.parseDouble(SoThu2));
            if (KeyFormat.equals("DauPhay")) {
                txt1 = txt1.replace(",", "/");
                txt1 = txt1.replace(".", ",");
                txt1 = txt1.replace("/", ".");
                txt2 = txt2.replace(",", "/");
                txt2 = txt2.replace(".", ",");
                txt2 = txt2.replace("/", ".");
                txtPhepTinh.setText(txt1 + PhepTinh + txt2 + ",");
            } else
                txtPhepTinh.setText(txt1 + PhepTinh + txt2 + ".");
            SoThu2Cham = ".";
        }
    }

    private void ClickHaiSoKhong() {
        if (PhepTinh == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu1Cham == "") {
                SoThu1 = SoThu1 + "00";
                String txt = format.format(Double.parseDouble(SoThu1));
                if (KeyFormat.equals("DauPhay")) {
                    txt = txt.replace(",", "/");
                    txt = txt.replace(".", ",");
                    txt = txt.replace("/", ".");
                    txtPhepTinh.setText(txt);
                } else
                    txtPhepTinh.setText(txt);
            } else {
                if (flag1 == 0) {
                    SoThu1 = SoThu1 + ".00";
                    flag1 = 1;
                    String txt = format.format(Double.parseDouble(SoThu1));
                    if (KeyFormat.equals("DauPhay")) {
                        txt = txt.replace(",", "/");
                        txt = txt.replace(".", ",");
                        txt = txt.replace("/", ".");
                        txtPhepTinh.setText(txt + ",00");
                    } else
                        txtPhepTinh.setText(txt + ".00");
                } else {
                    SoThu1 = SoThu1 + "00";
                    String txt = format.format(Double.parseDouble(SoThu1 + "1"));
                    if (KeyFormat.equals("DauPhay")) {
                        txt = txt.replace(",", "/");
                        txt = txt.replace(".", ",");
                        txt = txt.replace("/", ".");
                        txtPhepTinh.setText(txt.substring(0, txt.length() - 1));
                    } else
                        txtPhepTinh.setText(txt.substring(0, txt.length() - 1));
                }
            }
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu2Cham == "") {
                SoThu2 = SoThu2 + "00";
                String txt1 = format.format(Double.parseDouble(SoThu1));
                String txt2 = format.format(Double.parseDouble(SoThu2));
                if (KeyFormat.equals("DauPhay")) {
                    txt1 = txt1.replace(",", "/");
                    txt1 = txt1.replace(".", ",");
                    txt1 = txt1.replace("/", ".");
                    txt2 = txt2.replace(",", "/");
                    txt2 = txt2.replace(".", ",");
                    txt2 = txt2.replace("/", ".");
                    txtPhepTinh.setText(txt1 + PhepTinh + txt2);
                } else
                    txtPhepTinh.setText(txt1 + PhepTinh + txt2);
            } else {
                if (flag2 == 0) {
                    SoThu2 = SoThu2 + ".00";
                    flag2 = 1;
                    String txt1 = format.format(Double.parseDouble(SoThu1));
                    String txt2 = format.format(Double.parseDouble(SoThu2));
                    if (KeyFormat.equals("DauPhay")) {
                        txt1 = txt1.replace(",", "/");
                        txt1 = txt1.replace(".", ",");
                        txt1 = txt1.replace("/", ".");
                        txt2 = txt2.replace(",", "/");
                        txt2 = txt2.replace(".", ",");
                        txt2 = txt2.replace("/", ".");
                        txtPhepTinh.setText(txt1 + PhepTinh + txt2 + ",00");
                    } else
                        txtPhepTinh.setText(txt1 + PhepTinh + txt2 + ".00");
                } else {
                    SoThu2 = SoThu2 + "00";
                    String txt1 = format.format(Double.parseDouble(SoThu1));
                    String txt2 = format.format(Double.parseDouble(SoThu2 + "1"));
                    if (KeyFormat.equals("DauPhay")) {
                        txt1 = txt1.replace(",", "/");
                        txt1 = txt1.replace(".", ",");
                        txt1 = txt1.replace("/", ".");
                        txt2 = txt2.replace(",", "/");
                        txt2 = txt2.replace(".", ",");
                        txt2 = txt2.replace("/", ".");
                        txtPhepTinh.setText(txt1 + PhepTinh + txt2.substring(0, txt2.length() - 1));
                    } else
                        txtPhepTinh.setText(txt1 + PhepTinh + txt2.substring(0, txt2.length() - 1));
                }
            }
        }
    }

    private void ClickSoKhong() {
        if (PhepTinh == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu1Cham == "") {
                SoThu1 = SoThu1 + "0";
                String txt = format.format(Double.parseDouble(SoThu1));
                if (KeyFormat.equals("DauPhay")) {
                    txt = txt.replace(",", "/");
                    txt = txt.replace(".", ",");
                    txt = txt.replace("/", ".");
                    txtPhepTinh.setText(txt);
                } else
                    txtPhepTinh.setText(txt);
            } else {
                if (flag1 == 0) {
                    SoThu1 = SoThu1 + ".0";
                    flag1 = 1;
                    String txt = format.format(Double.parseDouble(SoThu1));
                    if (KeyFormat.equals("DauPhay")) {
                        txt = txt.replace(",", "/");
                        txt = txt.replace(".", ",");
                        txt = txt.replace("/", ".");
                        txtPhepTinh.setText(txt + ",0");
                    } else
                        txtPhepTinh.setText(txt + ".0");
                } else {
                    SoThu1 = SoThu1 + "0";
                    String txt = format.format(Double.parseDouble(SoThu1 + "1"));
                    if (KeyFormat.equals("DauPhay")) {
                        txt = txt.replace(",", "/");
                        txt = txt.replace(".", ",");
                        txt = txt.replace("/", ".");
                        txtPhepTinh.setText(txt.substring(0, txt.length() - 1));
                    } else
                        txtPhepTinh.setText(txt.substring(0, txt.length() - 1));
                }
            }
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu2Cham == "") {
                SoThu2 = SoThu2 + "0";
                String txt1 = format.format(Double.parseDouble(SoThu1));
                String txt2 = format.format(Double.parseDouble(SoThu2));
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
            } else {
                if (flag2 == 0) {
                    SoThu2 = SoThu2 + ".0";
                    flag2 = 1;
                    String txt1 = format.format(Double.parseDouble(SoThu1));
                    String txt2 = format.format(Double.parseDouble(SoThu2));
                    txtPhepTinh.setText(txt1 + PhepTinh + txt2 + ".0");
                } else {
                    SoThu2 = SoThu2 + "0";
                    String txt1 = format.format(Double.parseDouble(SoThu1));
                    String txt2 = format.format(Double.parseDouble(SoThu2 + "1"));
                    txtPhepTinh.setText(txt1 + PhepTinh + txt2.substring(0, txt2.length() - 1));
                }
            }
        }
    }

    private void ClickSoMot() {
        if (PhepTinh == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu1Cham == "")
                SoThu1 = SoThu1 + "1";
            else {
                if (flag1 == 0) {
                    SoThu1 = SoThu1 + ".1";
                    flag1 = 1;
                } else
                    SoThu1 = SoThu1 + "1";
            }
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt);
            } else
                txtPhepTinh.setText(txt);
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu2Cham == "")
                SoThu2 = SoThu2 + "1";
            else {
                if (flag2 == 0) {
                    SoThu2 = SoThu2 + ".1";
                    flag2 = 1;
                } else
                    SoThu2 = SoThu2 + "1";
            }
            String txt1 = format.format(Double.parseDouble(SoThu1));
            String txt2 = format.format(Double.parseDouble(SoThu2));
            if (KeyFormat.equals("DauPhay")) {
                txt1 = txt1.replace(",", "/");
                txt1 = txt1.replace(".", ",");
                txt1 = txt1.replace("/", ".");
                txt2 = txt2.replace(",", "/");
                txt2 = txt2.replace(".", ",");
                txt2 = txt2.replace("/", ".");
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
            } else
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
        }
    }

    private void ClickSoHai() {
        if (PhepTinh == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu1Cham == "")
                SoThu1 = SoThu1 + "2";
            else {
                if (flag1 == 0) {
                    SoThu1 = SoThu1 + ".2";
                    flag1 = 1;
                } else
                    SoThu1 = SoThu1 + "2";
            }
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt);
            } else
                txtPhepTinh.setText(txt);
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu2Cham == "")
                SoThu2 = SoThu2 + "2";
            else {
                if (flag2 == 0) {
                    SoThu2 = SoThu2 + ".2";
                    flag2 = 1;
                } else
                    SoThu2 = SoThu2 + "2";
            }
            String txt1 = format.format(Double.parseDouble(SoThu1));
            String txt2 = format.format(Double.parseDouble(SoThu2));
            if (KeyFormat.equals("DauPhay")) {
                txt1 = txt1.replace(",", "/");
                txt1 = txt1.replace(".", ",");
                txt1 = txt1.replace("/", ".");
                txt2 = txt2.replace(",", "/");
                txt2 = txt2.replace(".", ",");
                txt2 = txt2.replace("/", ".");
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
            } else
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
        }
    }

    private void ClickSoBa() {
        if (PhepTinh == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu1Cham == "")
                SoThu1 = SoThu1 + "3";
            else {
                if (flag1 == 0) {
                    SoThu1 = SoThu1 + ".3";
                    flag1 = 1;
                } else
                    SoThu1 = SoThu1 + "3";
            }
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt);
            } else
                txtPhepTinh.setText(txt);
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu2Cham == "")
                SoThu2 = SoThu2 + "3";
            else {
                if (flag2 == 0) {
                    SoThu2 = SoThu2 + ".3";
                    flag2 = 1;
                } else
                    SoThu2 = SoThu2 + "3";
            }
            String txt1 = format.format(Double.parseDouble(SoThu1));
            String txt2 = format.format(Double.parseDouble(SoThu2));
            if (KeyFormat.equals("DauPhay")) {
                txt1 = txt1.replace(",", "/");
                txt1 = txt1.replace(".", ",");
                txt1 = txt1.replace("/", ".");
                txt2 = txt2.replace(",", "/");
                txt2 = txt2.replace(".", ",");
                txt2 = txt2.replace("/", ".");
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
            } else
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
        }
    }

    private void ClickSoBon() {
        if (PhepTinh == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu1Cham == "")
                SoThu1 = SoThu1 + "4";
            else {
                if (flag1 == 0) {
                    SoThu1 = SoThu1 + ".4";
                    flag1 = 1;
                } else
                    SoThu1 = SoThu1 + "4";
            }
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt);
            } else
                txtPhepTinh.setText(txt);
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu2Cham == "")
                SoThu2 = SoThu2 + "4";
            else {
                if (flag2 == 0) {
                    SoThu2 = SoThu2 + ".4";
                    flag2 = 1;
                } else
                    SoThu2 = SoThu2 + "4";
            }
            String txt1 = format.format(Double.parseDouble(SoThu1));
            String txt2 = format.format(Double.parseDouble(SoThu2));
            if (KeyFormat.equals("DauPhay")) {
                txt1 = txt1.replace(",", "/");
                txt1 = txt1.replace(".", ",");
                txt1 = txt1.replace("/", ".");
                txt2 = txt2.replace(",", "/");
                txt2 = txt2.replace(".", ",");
                txt2 = txt2.replace("/", ".");
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
            } else
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
        }
    }

    private void ClickSoNam() {
        if (PhepTinh == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu1Cham == "")
                SoThu1 = SoThu1 + "5";
            else {
                if (flag1 == 0) {
                    SoThu1 = SoThu1 + ".5";
                    flag1 = 1;
                } else
                    SoThu1 = SoThu1 + "5";
            }
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt);
            } else
                txtPhepTinh.setText(txt);
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu2Cham == "")
                SoThu2 = SoThu2 + "5";
            else {
                if (flag2 == 0) {
                    SoThu2 = SoThu2 + ".5";
                    flag2 = 1;
                } else
                    SoThu2 = SoThu2 + "5";
            }
            String txt1 = format.format(Double.parseDouble(SoThu1));
            String txt2 = format.format(Double.parseDouble(SoThu2));
            if (KeyFormat.equals("DauPhay")) {
                txt1 = txt1.replace(",", "/");
                txt1 = txt1.replace(".", ",");
                txt1 = txt1.replace("/", ".");
                txt2 = txt2.replace(",", "/");
                txt2 = txt2.replace(".", ",");
                txt2 = txt2.replace("/", ".");
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
            } else
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
        }
    }

    private void ClickSoSau() {
        if (PhepTinh == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu1Cham == "")
                SoThu1 = SoThu1 + "6";
            else {
                if (flag1 == 0) {
                    SoThu1 = SoThu1 + ".6";
                    flag1 = 1;
                } else
                    SoThu1 = SoThu1 + "6";
            }
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt);
            } else
                txtPhepTinh.setText(txt);
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu2Cham == "")
                SoThu2 = SoThu2 + "6";
            else {
                if (flag2 == 0) {
                    SoThu2 = SoThu2 + ".6";
                    flag2 = 1;
                } else
                    SoThu2 = SoThu2 + "6";
            }
            String txt1 = format.format(Double.parseDouble(SoThu1));
            String txt2 = format.format(Double.parseDouble(SoThu2));
            if (KeyFormat.equals("DauPhay")) {
                txt1 = txt1.replace(",", "/");
                txt1 = txt1.replace(".", ",");
                txt1 = txt1.replace("/", ".");
                txt2 = txt2.replace(",", "/");
                txt2 = txt2.replace(".", ",");
                txt2 = txt2.replace("/", ".");
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
            } else
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
        }
    }

    private void ClickSoBay() {
        if (PhepTinh == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu1Cham == "")
                SoThu1 = SoThu1 + "7";
            else {
                if (flag1 == 0) {
                    SoThu1 = SoThu1 + ".7";
                    flag1 = 1;
                } else
                    SoThu1 = SoThu1 + "7";
            }
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt);
            } else
                txtPhepTinh.setText(txt);
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu2Cham == "")
                SoThu2 = SoThu2 + "7";
            else {
                if (flag2 == 0) {
                    SoThu2 = SoThu2 + ".7";
                    flag2 = 1;
                } else
                    SoThu2 = SoThu2 + "7";
            }
            String txt1 = format.format(Double.parseDouble(SoThu1));
            String txt2 = format.format(Double.parseDouble(SoThu2));
            if (KeyFormat.equals("DauPhay")) {
                txt1 = txt1.replace(",", "/");
                txt1 = txt1.replace(".", ",");
                txt1 = txt1.replace("/", ".");
                txt2 = txt2.replace(",", "/");
                txt2 = txt2.replace(".", ",");
                txt2 = txt2.replace("/", ".");
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
            } else
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
        }
    }

    private void ClickSoTam() {
        if (PhepTinh == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu1Cham == "")
                SoThu1 = SoThu1 + "8";
            else {
                if (flag1 == 0) {
                    SoThu1 = SoThu1 + ".8";
                    flag1 = 1;
                } else
                    SoThu1 = SoThu1 + "8";
            }
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt);
            } else
                txtPhepTinh.setText(txt);
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu2Cham == "")
                SoThu2 = SoThu2 + "8";
            else {
                if (flag2 == 0) {
                    SoThu2 = SoThu2 + ".8";
                    flag2 = 1;
                } else
                    SoThu2 = SoThu2 + "8";
            }
            String txt1 = format.format(Double.parseDouble(SoThu1));
            String txt2 = format.format(Double.parseDouble(SoThu2));
            if (KeyFormat.equals("DauPhay")) {
                txt1 = txt1.replace(",", "/");
                txt1 = txt1.replace(".", ",");
                txt1 = txt1.replace("/", ".");
                txt2 = txt2.replace(",", "/");
                txt2 = txt2.replace(".", ",");
                txt2 = txt2.replace("/", ".");
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
            } else
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
        }
    }

    private void ClickSoChin() {
        if (PhepTinh == "") {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu1Cham == "")
                SoThu1 = SoThu1 + "9";
            else {
                if (flag1 == 0) {
                    SoThu1 = SoThu1 + ".9";
                    flag1 = 1;
                } else
                    SoThu1 = SoThu1 + "9";
            }
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt);
            } else
                txtPhepTinh.setText(txt);
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
            if (SoThu2Cham == "")
                SoThu2 = SoThu2 + "9";
            else {
                if (flag2 == 0) {
                    SoThu2 = SoThu2 + ".9";
                    flag2 = 1;
                } else
                    SoThu2 = SoThu2 + "9";
            }
            String txt1 = format.format(Double.parseDouble(SoThu1));
            String txt2 = format.format(Double.parseDouble(SoThu2));
            if (KeyFormat.equals("DauPhay")) {
                txt1 = txt1.replace(",", "/");
                txt1 = txt1.replace(".", ",");
                txt1 = txt1.replace("/", ".");
                txt2 = txt2.replace(",", "/");
                txt2 = txt2.replace(".", ",");
                txt2 = txt2.replace("/", ".");
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
            } else
                txtPhepTinh.setText(txt1 + PhepTinh + txt2);
        }
    }

    private void ClickPhepCong() {
        DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
        if (PhepTinh != "" && SoThu2 != "") {
            ClickDauBang();
            if (flag1 == 1 || flag2 == 1) {
                flag1 = 1;
                flag2 = 0;
                SoThu1Cham = ".";
                SoThu2Cham = "";
            } else {
                flag1 = flag2 = 0;
                SoThu1Cham = SoThu2Cham = "";
            }
            SoThu2 = "";
            PhepTinh = "+";
            SoThu1 = String.valueOf(KetQua);
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt + PhepTinh);
            } else
                txtPhepTinh.setText(txt + PhepTinh);
        } else {
            PhepTinh = "+";
            if (SoThu1 == "")
                SoThu1 = "0";
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt + PhepTinh);
            } else
                txtPhepTinh.setText(txt + PhepTinh);
        }
    }

    private void ClickPhepTru() {
        DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
        if (PhepTinh != "" && SoThu2 != "") {
            ClickDauBang();
            if (flag1 == 1 || flag2 == 1) {
                flag1 = 1;
                flag2 = 0;
                SoThu1Cham = ".";
                SoThu2Cham = "";
            } else {
                flag1 = flag2 = 0;
                SoThu1Cham = SoThu2Cham = "";
            }
            SoThu2 = "";
            PhepTinh = "-";
            SoThu1 = String.valueOf(KetQua);
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt + PhepTinh);
            } else
                txtPhepTinh.setText(txt + PhepTinh);
        } else {
            PhepTinh = "-";
            if (SoThu1 == "")
                SoThu1 = "0";
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt + PhepTinh);
            } else
                txtPhepTinh.setText(txt + PhepTinh);
        }
    }

    private void ClickPhepNhan() {
        DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
        if (PhepTinh != "" && SoThu2 != "") {
            ClickDauBang();
            if (flag1 == 1 || flag2 == 1) {
                flag1 = 1;
                flag2 = 0;
                SoThu1Cham = ".";
                SoThu2Cham = "";
            } else {
                flag1 = flag2 = 0;
                SoThu1Cham = SoThu2Cham = "";
            }
            SoThu2 = "";
            PhepTinh = "x";
            SoThu1 = String.valueOf(KetQua);
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt + PhepTinh);
            } else
                txtPhepTinh.setText(txt + PhepTinh);
        } else {
            PhepTinh = "x";
            if (SoThu1 == "")
                SoThu1 = "0";
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt + PhepTinh);
            } else
                txtPhepTinh.setText(txt + PhepTinh);
        }
    }

    private void ClickPhepChia() {
        DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
        if (PhepTinh != "" && SoThu2 != "") {
            ClickDauBang();
            if (flag1 == 1 || flag2 == 1) {
                flag1 = 1;
                flag2 = 0;
                SoThu1Cham = ".";
                SoThu2Cham = "";
            } else {
                flag1 = flag2 = 0;
                SoThu1Cham = SoThu2Cham = "";
            }
            SoThu2 = "";
            PhepTinh = "÷";
            SoThu1 = String.valueOf(KetQua);
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt + PhepTinh);
            } else
                txtPhepTinh.setText(txt + PhepTinh);
        } else {
            PhepTinh = "÷";
            if (SoThu1 == "")
                SoThu1 = "0";
            String txt = format.format(Double.parseDouble(SoThu1));
            if (KeyFormat.equals("DauPhay")) {
                txt = txt.replace(",", "/");
                txt = txt.replace(".", ",");
                txt = txt.replace("/", ".");
                txtPhepTinh.setText(txt + PhepTinh);
            } else
                txtPhepTinh.setText(txt + PhepTinh);
        }
    }

    private void ClickDauBang() {
        DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
        if (PhepTinh == "+") {
            Double So1 = Double.parseDouble(SoThu1);
            Double So2 = Double.parseDouble(SoThu2);
            KetQua = So1 + So2;
            String txtKQ = format.format(KetQua);
            if (KeyFormat.equals("DauPhay")) {
                txtKQ = txtKQ.replace(",", "/");
                txtKQ = txtKQ.replace(".", ",");
                txtKQ = txtKQ.replace("/", ".");
                txtKetQua.setText(String.valueOf(txtKQ));
            } else
                txtKetQua.setText(String.valueOf(txtKQ));
        } else if (PhepTinh == "-") {
            Double So1 = Double.parseDouble(SoThu1);
            Double So2 = Double.parseDouble(SoThu2);
            KetQua = So1 - So2;
            String txtKQ = format.format(KetQua);
            if (KeyFormat.equals("DauPhay")) {
                txtKQ = txtKQ.replace(",", "/");
                txtKQ = txtKQ.replace(".", ",");
                txtKQ = txtKQ.replace("/", ".");
                txtKetQua.setText(String.valueOf(txtKQ));
            } else
                txtKetQua.setText(String.valueOf(txtKQ));
        } else if (PhepTinh == "x") {
            Double So1 = Double.parseDouble(SoThu1);
            Double So2 = Double.parseDouble(SoThu2);
            KetQua = So1 * So2;
            String txtKQ = format.format(KetQua);
            if (KeyFormat.equals("DauPhay")) {
                txtKQ = txtKQ.replace(",", "/");
                txtKQ = txtKQ.replace(".", ",");
                txtKQ = txtKQ.replace("/", ".");
                txtKetQua.setText(String.valueOf(txtKQ));
            } else
                txtKetQua.setText(String.valueOf(txtKQ));
        } else if (PhepTinh == "÷") {
            Double So1 = Double.parseDouble(SoThu1);
            Double So2 = Double.parseDouble(SoThu2);
            KetQua = So1 / So2;
            String txtKQ = format.format(KetQua);
            if (KeyFormat.equals("DauPhay")) {
                txtKQ = txtKQ.replace(",", "/");
                txtKQ = txtKQ.replace(".", ",");
                txtKQ = txtKQ.replace("/", ".");
                txtKetQua.setText(String.valueOf(txtKQ));
            } else
                txtKetQua.setText(String.valueOf(txtKQ));
        } else {
            KetQua = Double.parseDouble(SoThu1);
            String txtKQ = format.format(KetQua);
            if (KeyFormat.equals("DauPhay")) {
                txtKQ = txtKQ.replace(",", "/");
                txtKQ = txtKQ.replace(".", ",");
                txtKQ = txtKQ.replace("/", ".");
                txtKetQua.setText(String.valueOf(txtKQ));
            } else
                txtKetQua.setText(String.valueOf(txtKQ));
        }
    }

    private void ClickClear() {
        SoThu1 = SoThu1Cham = SoThu2 = SoThu2Cham = PhepTinh = "";
        flag1 = flag2 = 0;
        txtPhepTinh.setText("");
        txtKetQua.setText("0");
    }

    private void ClickPhanTram() {
        if (SoThu1 != "" && SoThu2 != "") {
            if (PhepTinh == "+") {
                Double So1 = Double.parseDouble(SoThu1);
                Double So2 = Double.parseDouble(SoThu2);
                KetQua = So1 + So2;
                KetQua = KetQua / 100;
                String txtKQ = String.valueOf(KetQua);
                if (KeyFormat.equals("DauPhay")) {
                    txtKQ = txtKQ.replace(",", "/");
                    txtKQ = txtKQ.replace(".", ",");
                    txtKQ = txtKQ.replace("/", ".");
                    txtKetQua.setText(String.valueOf(txtKQ));
                } else
                    txtKetQua.setText(String.valueOf(txtKQ));
            } else if (PhepTinh == "-") {
                Double So1 = Double.parseDouble(SoThu1);
                Double So2 = Double.parseDouble(SoThu2);
                KetQua = So1 - So2;
                KetQua = KetQua / 100;
                String txtKQ = String.valueOf(KetQua);
                if (KeyFormat.equals("DauPhay")) {
                    txtKQ = txtKQ.replace(",", "/");
                    txtKQ = txtKQ.replace(".", ",");
                    txtKQ = txtKQ.replace("/", ".");
                    txtKetQua.setText(String.valueOf(txtKQ));
                } else
                    txtKetQua.setText(String.valueOf(txtKQ));
            } else if (PhepTinh == "x") {
                Double So1 = Double.parseDouble(SoThu1);
                Double So2 = Double.parseDouble(SoThu2);
                KetQua = So1 * So2;
                KetQua = KetQua / 100;
                String txtKQ = String.valueOf(KetQua);
                if (KeyFormat.equals("DauPhay")) {
                    txtKQ = txtKQ.replace(",", "/");
                    txtKQ = txtKQ.replace(".", ",");
                    txtKQ = txtKQ.replace("/", ".");
                    txtKetQua.setText(String.valueOf(txtKQ));
                } else
                    txtKetQua.setText(String.valueOf(txtKQ));
            } else if (PhepTinh == "÷") {
                Double So1 = Double.parseDouble(SoThu1);
                Double So2 = Double.parseDouble(SoThu2);
                KetQua = So1 / So2;
                KetQua = KetQua / 100;
                String txtKQ = String.valueOf(KetQua);
                if (KeyFormat.equals("DauPhay")) {
                    txtKQ = txtKQ.replace(",", "/");
                    txtKQ = txtKQ.replace(".", ",");
                    txtKQ = txtKQ.replace("/", ".");
                    txtKetQua.setText(String.valueOf(txtKQ));
                } else
                    txtKetQua.setText(String.valueOf(txtKQ));
            }
        } else if (SoThu1 != "" && SoThu2 == "") {
            String txtKQ = String.valueOf(Double.parseDouble(SoThu1) / 100);
            if (KeyFormat.equals("DauPhay")) {
                txtKQ = txtKQ.replace(",", "/");
                txtKQ = txtKQ.replace(".", ",");
                txtKQ = txtKQ.replace("/", ".");
                txtKetQua.setText(String.valueOf(txtKQ));
            } else
                txtKetQua.setText(String.valueOf(txtKQ));
        }
    }

    private void ClickXoa() {
        DecimalFormat format = new DecimalFormat("###,###,###,###,###.####################");
        if (SoThu2 != "") {
            if (SoThu2.length() > 1) {
                SoThu2 = SoThu2.substring(0, SoThu2.length() - 1);
                if (SoThu2.charAt(SoThu2.length() - 1) == '.')
                    SoThu2 = SoThu2.substring(0, SoThu2.length() - 1);
                if (SoThu2.charAt(SoThu2.length() - 1) == '0') {
                    String txt1 = format.format(Double.parseDouble(SoThu1));
                    String txt2 = format.format(Double.parseDouble(SoThu2 + "1"));
                    txtPhepTinh.setText(txt1 + PhepTinh + txt2.substring(0, txt2.length() - 1));
                } else {
                    String txt1 = format.format(Double.parseDouble(SoThu1));
                    String txt2 = format.format(Double.parseDouble(SoThu2));
                    txtPhepTinh.setText(txt1 + PhepTinh + txt2);
                }
            } else {
                SoThu2 = "";
                String txt = format.format(Double.parseDouble(SoThu1));
                txtPhepTinh.setText(txt + PhepTinh);
            }
        } else if (SoThu2 == "" && PhepTinh != "") {
            PhepTinh = "";
            String txt = format.format(Double.parseDouble(SoThu1));
            txtPhepTinh.setText(txt);
        } else {
            if (SoThu1.length() > 1) {
                SoThu1 = SoThu1.substring(0, SoThu1.length() - 1);
                if (SoThu1.charAt(SoThu1.length() - 1) == '.')
                    SoThu1 = SoThu1.substring(0, SoThu1.length() - 1);
                if (SoThu1.charAt(SoThu1.length() - 1) == '0') {
                    String txt = format.format(Double.parseDouble(SoThu1 + "1"));
                    txtPhepTinh.setText(txt.substring(0, txt.length() - 1));
                } else {
                    String txt = format.format(Double.parseDouble(SoThu1));
                    txtPhepTinh.setText(txt);
                }
            } else {
                SoThu1 = "";
                SoThu2 = "";
                PhepTinh = "";
                txtPhepTinh.setText("");
            }
        }
    }
}
