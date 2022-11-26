package com.example.attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attendance.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public Button button;
    public EditText editText;
    public String cmate[] =  new String[102];
    private static final  int WRITE_EXTERNAL_STORAGE_CODE = 1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                            String[] permissions  = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            requestPermissions(permissions,WRITE_EXTERNAL_STORAGE_CODE);
                        }else{
                            savetxtfile(cmate);
                        }
                    }else{
                        savetxtfile(cmate);
                    }
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case WRITE_EXTERNAL_STORAGE_CODE:{
                if(grantResults.length>1 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    savetxtfile(cmate);
                }else{
                    Toast.makeText(this, "Storage permission is required to store", Toast.LENGTH_SHORT);
                }
            }
        }
    }

    private void savetxtfile(String edata[]) {
        String tstamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(System.currentTimeMillis());
        try{
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File dir = new File(path + "/Attendance/");
            dir.mkdirs();
            String filename = tstamp +".xlsx";
            File file = new File(dir, filename);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i =0;i<102;i++){
               if(edata[i]!=null)
                   bw.write(edata[i]+"\n");
            }

            bw.close();
            Toast.makeText(this, filename +" is saved to /n" + dir,Toast.LENGTH_SHORT).show();
        }catch (Exception e){

            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }
    @SuppressLint("NonConstantResourceId")
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox1:
                if (checked) {
                    cmate[0] = "Abhash Tiwari";
                }else{
                    cmate[0] = null;
                }break;
            case R.id.checkBox2:
                if (checked) {
                    cmate[1] = "Abhay Bairagi";
                }else {
                    cmate[1] = null;
                }break;
            case R.id.checkBox3:
                if (checked) {
                    cmate[2] = "Abhi Agarwal";
                }else{
                    cmate[2] = null;
                }break;
            case R.id.checkBox4:
                if (checked) {
                    cmate[3] = "Abhinav Anand";
                }else {
                    cmate[3] = null;
                }break;
            case R.id.checkBox5:
                if (checked) {
                    cmate[4] = "Adarsh Singh";
                }else{
                    cmate[4] = null;
                }break;
            case R.id.checkBox6:
                if (checked) {
                    cmate[5] = "Aditya Goswami";
                }else {
                    cmate[5] = null;
                }break;
            case R.id.checkBox7:
                if (checked) {
                    cmate[6] = "Aditya Sharma";
                }else{
                    cmate[6] = null;
                }break;
            case R.id.checkBox8:
                if (checked) {
                    cmate[7] = "Aditya Sijaria";
                }else {
                    cmate[7] = null;
                }break;
            case R.id.checkBox9:
                if (checked) {
                    cmate[8] = "Akhil Chaturvedi";
                }else{
                    cmate[8] = null;
                }break;
            case R.id.checkBox10:
                if (checked) {
                    cmate[9] = "Akhilesh Yadav";
                }else {
                    cmate[9] = null;
                }break;
            case R.id.checkBox11:
                if (checked) {
                    cmate[10] = "Akshat Agarwal";
                }else{
                    cmate[10] = null;
                }break;
            case R.id.checkBox12:
                if (checked) {
                    cmate[11] = "Akshikaa Singh";
                }else {
                    cmate[11] = null;
                }break;
            case R.id.checkBox13:
                if (checked) {
                    cmate[12] = "Alex Livingston";
                }else{
                    cmate[12] = null;
                }break;
            case R.id.checkBox14:
                if (checked) {
                    cmate[13] = "Amber Soni";
                }else {
                    cmate[13] = null;
                }break;
            case R.id.checkBox15:
                if (checked) {
                    cmate[14] = "Anmol Sahu";
                }else{
                    cmate[14] = null;
                }break;
            case R.id.checkBox16:
                if (checked) {
                    cmate[15] = "Anuj kumar Mahra";
                }else {
                    cmate[15] = null;
                }break;
            case R.id.checkBox17:
                if (checked) {
                    cmate[16] = "Anushka Awelkar";
                }else{
                    cmate[16] = null;
                }break;
            case R.id.checkBox18:
                if (checked) {
                    cmate[17] = "Apurv Dube";
                }else {
                    cmate[17] = null;
                }break;
            case R.id.checkBox19:
                if (checked) {
                    cmate[18] = "Arpita Pathak";
                }else{
                    cmate[18] = null;
                }break;
            case R.id.checkBox20:
                if (checked) {
                    cmate[19] = "Arunika Bhattacharya";
                }else {
                    cmate[19] = null;
                }break;
            case R.id.checkBox21:
                if (checked) {
                    cmate[20] = "Arunish Nema";
                }else{
                    cmate[20] = null;
                }break;
            case R.id.checkBox22:
                if (checked) {
                    cmate[21] = "Aryan Nagar";
                }else {
                    cmate[21] = null;
                }break;
            case R.id.checkBox23:
                if (checked) {
                    cmate[22] = "Aviral Jain";
                }else{
                    cmate[22] = null;
                }break;
            case R.id.checkBox24:
                if (checked) {
                    cmate[23] = "Ayush Chakraverti";
                }else {
                    cmate[23] = null;
                }break;
            case R.id.checkBox25:
                if (checked) {
                    cmate[24] = "Ayush Shukla";
                }else{
                    cmate[24] = null;
                }break;
            case R.id.checkBox26:
                if (checked) {
                    cmate[25] = "Chhotelal Prajapati";
                }else {
                    cmate[25] = null;
                }break;
            case R.id.checkBox27:
                if (checked) {
                    cmate[26] = "Deepak Singh Porte";
                }else{
                    cmate[26] = null;
                }break;
            case R.id.checkBox28:
                if (checked) {
                    cmate[27] = "Dev Panvar";
                }else {
                    cmate[27] = null;
                }break;
            case R.id.checkBox29:
                if (checked) {
                    cmate[28] = "Dewanshi Asathi";
                }else{
                    cmate[28] = null;
                }break;
            case R.id.checkBox30:
                if (checked) {
                    cmate[29] = "Dhananjay Sarathe";
                }else {
                    cmate[29] = null;
                }break;
            case R.id.checkBox31:
                if (checked) {
                    cmate[30] = "Dheerap Singh Tanwar";
                }else{
                    cmate[30] = null;
                }break;
            case R.id.checkBox32:
                if (checked) {
                    cmate[31] = "Dhruv Soni";
                }else {
                    cmate[31] = null;
                }break;
            case R.id.checkBox33:
                if (checked) {
                    cmate[32] = "Divyanshu Tiwari";
                }else{
                    cmate[32] = null;
                }break;
            case R.id.checkBox34:
                if (checked) {
                    cmate[33] = "Divyata Pandey";
                }else {
                    cmate[33] = null;
                }break;
            case R.id.checkBox35:
                if (checked) {
                    cmate[34] = "Dream Pradhan";
                }else{
                    cmate[34] = null;
                }break;
            case R.id.checkBox36:
                if (checked) {
                    cmate[35] = "Ekta kumari";
                }else {
                    cmate[35] = null;
                }break;
            case R.id.checkBox37:
                if (checked) {
                    cmate[36] = "Gyarsilal Solanki";
                }else{
                    cmate[36] = null;
                }break;
            case R.id.checkBox38:
                if (checked) {
                    cmate[37] = "Harshit Parte";
                }else {
                    cmate[37] = null;
                }break;
            case R.id.checkBox39:
                if (checked) {
                    cmate[38] = "Harshit Goyal";
                }else{
                    cmate[38] = null;
                }break;
            case R.id.checkBox40:
                if (checked) {
                    cmate[41] = "Harshit Rai";
                }else {
                    cmate[41] = null;
                }break;
            case R.id.checkBox41:
                if (checked) {
                    cmate[40] = "Harshita GajBhiye";
                }else{
                    cmate[40] = null;
                }break;
            case R.id.checkBox42:
                if (checked) {
                    cmate[42] = "Harshita Kulchandani";
                }else {
                    cmate[42] = null;
                }break;
            case R.id.checkBox43:
                if (checked) {
                    cmate[42] = "Harshita Pandey";
                }else{
                    cmate[42] = null;
                }break;
            case R.id.checkBox44:
                if (checked) {
                    cmate[43] = "Himanshu Dhurvey";
                }else {
                    cmate[43] = null;
                }break;
            case R.id.checkBox45:
                if (checked) {
                    cmate[44] = "Himanshu Singh Senger";
                }else{
                    cmate[44] = null;
                }break;
            case R.id.checkBox46:
                if (checked) {
                    cmate[45] = "Himanshu Yadav";
                }else {
                    cmate[45] = null;
                }break;
            case R.id.checkBox47:
                if (checked) {
                    cmate[46] = "Jai Prakash Valecha";
                }else{
                    cmate[46] = null;
                }break;
            case R.id.checkBox48:
                if (checked) {
                    cmate[47] =  "Jeet Vijaywargiya";
                }else {
                    cmate[47] = null;
                }break;
            case R.id.checkBox49:
                if (checked) {
                    cmate[48] =  "Jitendra Kumar";
                }else{
                    cmate[48] = null;
                }break;
            case R.id.checkBox50:
                if (checked) {
                    cmate[49] = "Kamlesh Mali";
                }else {
                    cmate[49] = null;
                }break;
            case R.id.checkBox51:
                if (checked) {
                    cmate[50] =  "Kirti Gupta" ;
                }else{
                    cmate[50] = null;
                }break;
            case R.id.checkBox52:
                if (checked) {
                    cmate[51] =  "Kuljeet Singh Chauhan";
                }else {
                    cmate[51] = null;
                }break;
            case R.id.checkBox53:
                if (checked) {
                    cmate[52] =  "Mahesh Barapatre";
                }else{
                    cmate[52] = null;
                }break;
            case R.id.checkBox54:
                if (checked) {
                    cmate[53] = "Malay kumar Jain";
                }else {
                    cmate[53] = null;
                }break;
            case R.id.checkBox55:
                if (checked) {
                    cmate[54] = "Manish Anuragi" ;
                }else{
                    cmate[54] = null;
                }break;
            case R.id.checkBox56:
                if (checked) {
                    cmate[55] = "Mansi Sen" ;
                }else {
                    cmate[55] = null;
                }break;
            case R.id.checkBox57:
                if (checked) {
                    cmate[56] =  "Nagendra Singh lodh";
                }else{
                    cmate[56] = null;
                }break;
            case R.id.checkBox58:
                if (checked) {
                    cmate[57] =  "Neha Tumdam" ;
                }else {
                    cmate[57] = null;
                }break;
            case R.id.checkBox59:
                if (checked) {
                    cmate[58] =  "Nimit Kumar Soni";
                }else{
                    cmate[58] = null;
                }break;
            case R.id.checkBox60:
                if (checked) {
                    cmate[59] = "Nitesh Mandrai";
                }else {
                    cmate[59] = null;
                }break;
            case R.id.checkBox61:
                if (checked) {
                    cmate[62] =  "Parnika Upadhyay" ;
                }else{
                    cmate[62] = null;
                }break;
            case R.id.checkBox62:
                if (checked) {
                    cmate[61] = "Prasiddhi Rathore";
                }else {
                    cmate[61] = null;
                }break;
            case R.id.checkBox63:
                if (checked) {
                    cmate[62] =  "Priyanka Tiwari" ;
                }else{
                    cmate[62] = null;
                }break;
            case R.id.checkBox64:
                if (checked) {
                    cmate[63] ="Priyanshi Malviya" ;
                }else {
                    cmate[63] = null;
                }break;
            case R.id.checkBox65:
                if (checked) {
                    cmate[64] = "Rajesh Varkade";
                }else{
                    cmate[64] = null;
                }break;
            case R.id.checkBox66:
                if (checked) {
                    cmate[65] = "Rajneesh Diwedi";
                }else{
                    cmate[65] = null;
                }break;
            case R.id.checkBox67:
                if (checked) {
                    cmate[66] = "Rashi Gotiya";
                }else {
                    cmate[66] = null;
                }break;
            case R.id.checkBox68:
                if (checked) {
                    cmate[67] = "Raunak Gupta";
                }else {
                    cmate[67] = null;
                }break;
            case R.id.checkBox69:
                if (checked) {
                    cmate[68] = "Rishabh Prajapati";
                }else{
                    cmate[68] = null;
                }break;
            case R.id.checkBox70:
                if (checked) {
                    cmate[69] = "Sachendra Sing Thakur";
                }else {
                    cmate[69] = null;
                }break;
            case R.id.checkBox71:
                if (checked) {
                    cmate[70] = "Sakhi Sen";
                }else{
                    cmate[70] = null;
                }break;
            case R.id.checkBox72:
                if (checked) {
                    cmate[71] = "Samarpan Davis";
                }else {
                    cmate[71] = null;
                }break;
            case R.id.checkBox73:
                if (checked) {
                    cmate[72] = "Sambhav Jain";
                }else{
                    cmate[72] = null;
                }break;
            case R.id.checkBox74:
                if (checked) {
                    cmate[73] = "Sanskar Verma";
                }else {
                    cmate[73] = null;
                }break;
            case R.id.checkBox75:
                if (checked) {
                    cmate[74] = "Sheetal Dhurve";
                }else{
                    cmate[74] = null;
                }break;
            case R.id.checkBox76:
                if (checked) {
                    cmate[75] = "Shiv Kumar Bhalavi";
                }else {
                    cmate[75] = null;
                }break;
            case R.id.checkBox77:
                if (checked) {
                    cmate[76] = "Shivam Gupta";
                }else{
                    cmate[76] = null;
                }break;
            case R.id.checkBox78:
                if (checked) {
                    cmate[77] = "Shubham Dawar";
                }else {
                    cmate[77] = null;
                }break;
            case R.id.checkBox79:
                if (checked) {
                    cmate[78] = "Simran Raj";
                }else{
                    cmate[78] = null;
                }break;
            case R.id.checkBox80:
                if (checked) {
                    cmate[79] = "Sonal Jaiswar";
                }else {
                    cmate[79] = null;
                }break;
            case R.id.checkBox81:
                if (checked) {
                    cmate[80] = "Sparsh Shrivastava";
                }else{
                    cmate[80] = null;
                }break;

            case R.id.checkBox82:
                if (checked) {
                    cmate[81] = "Shudhanshu Tiwari";
                }else{
                    cmate[81] = null;
                }break;
            case R.id.checkBox83:
                if (checked) {
                    cmate[82] = "Summit Rawat";
                }else {
                    cmate[82] = null;
                }break;
            case R.id.checkBox84:
                if (checked) {
                    cmate[83] = "Tarun Kumar Ghormare";
                }else{
                    cmate[83] = null;
                }break;
            case R.id.checkBox85:
                if (checked) {
                    cmate[84] = "Tilakraj Paraste";
                }else {
                    cmate[84] = null;
                }break;
            case R.id.checkBox86:
                if (checked) {
                    cmate[85] = "Varsa Bhoure";
                }else{
                    cmate[85] = null;
                }break;
            case R.id.checkBox87:
                if (checked) {
                    cmate[86] = "Vijen Kumar Jatav";
                }else {
                    cmate[86] = null;
                }break;
            case R.id.checkBox88:
                if (checked) {
                    cmate[87] = "Vishakha Rajput";
                }else{
                    cmate[87] = null;
                }break;
            case R.id.checkBox89:
                if (checked) {
                    cmate[88] = "Vishal Nagi";
                }else {
                    cmate[88] = null;
                }break;
            case R.id.checkBox90:
                if (checked) {
                    cmate[89] = "Vivek Kumar saket";
                }else{
                    cmate[89] = null;
                }break;
            case R.id.checkBox91:
                if (checked) {
                    cmate[90] = "Yogesh Kaushal";
                }else {
                    cmate[90] = null;
                }break;
            case R.id.checkBox92:
                if (checked) {
                    cmate[91] = "Urgen Dolma";
                }else{
                    cmate[91] = null;
                }break;
            case R.id.checkBox93:
                if (checked) {
                    cmate[92] = "Aastha Parihar";
                }else {
                    cmate[92] = null;
                }break;
            case R.id.checkBox94:
                if (checked) {
                    cmate[93] = "Laxmi Tripathi";
                }else{
                    cmate[93] = null;
                }break;
            case R.id.checkBox95:
                if (checked) {
                    cmate[94] = "Mamta Panda";
                }else {
                    cmate[94] = null;
                }break;
            case R.id.checkBox96:
                if (checked) {
                    cmate[95] = "Annapurna Dwivedi";
                }else{
                    cmate[95] = null;
                }break;
            case R.id.checkBox97:
                if (checked) {
                    cmate[96] = "Riya Bopche";
                }else {
                    cmate[96] = null;
                }break;
            case R.id.checkBox98:
                if (checked) {
                    cmate[97] = "Dhyan kamal";
                }else{
                    cmate[97] = null;
                }break;
            case R.id.checkBox99:
                if (checked) {
                    cmate[98] = "Mithlesh Ahirwar";
                }else {
                    cmate[98] = null;
                }break;
            case R.id.checkBox100:
                if (checked) {
                    cmate[99] = "Ajay Sarathe";
                }else{
                    cmate[99] = null;
                }break;
            case R.id.checkBox101:
                if (checked) {
                    cmate[100] = "Laxmikant Nayak";
                }else {
                    cmate[100] = null;
                }break;
            case R.id.checkBox102:
                if (checked) {
                    cmate[101] = "Jaishri Masram";
                }else{
                    cmate[101] = null;
                }break;

            // TODO: Veggie sandwich
        }
    }



}