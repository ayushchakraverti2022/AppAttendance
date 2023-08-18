package com.example.attendance;

import static com.example.attendance.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.spec.EncodedKeySpec;
import java.sql.Struct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    public ImageView imageViewopenfolder;
    public TextView createclassmessage;
    public ImageView imageViewcreateclass;
    public DateFormat df = new SimpleDateFormat("dd\\MM\\yyyy");
    public File privatefullpath = new File(Environment.getExternalStorageDirectory(), "/Attendance/.private/");
    public String privateclassfile_fullname = "classname.txt";
    public Date d = new Date();
    public String current_date = df.format(d);
    public String selected_classfolder_fullpath;
    public Spinner spinner;
    public String current_class_halfname = "";
    public ImageView imageViewdeleteclass;
    public ArrayList<String> classes_array = new ArrayList<>();
    public ArrayList<String> student_array_with_roll_num = new ArrayList<String>();
    public ArrayList<String> student_present = new ArrayList<String>();
    public Recycler_adapter adapter;
    public ImageView imageViewaddstudent;
    public Button saveattendance_button;
    public static RecyclerView recyclerView;
    public int REQ_CODE = 1;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        // Find ids
        imageViewopenfolder = findViewById(id.openfolder);
        saveattendance_button = findViewById(id.Button);
        createclassmessage = findViewById(R.id.createclassmessage);
        spinner = findViewById(id.classname);
        imageViewdeleteclass = findViewById(id.deleteclass);
        imageViewaddstudent = findViewById(id.addstudent);
        recyclerView = findViewById(id.recyclerView);
        imageViewcreateclass = findViewById(id.createclass);
        // Storage permission
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQ_CODE);
        }

        // open folder function
        imageViewopenfolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = String
                        .valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
                Uri uri = Uri.parse(path);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "*/*");
                startActivity(intent);
            }
        });

        // class name loader and adapter
        File f = new File(privatefullpath, privateclassfile_fullname);
        if (f.exists()) {
            imageViewopenfolder.setVisibility(View.VISIBLE);
            imageViewdeleteclass.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            createclassmessage.setVisibility(View.GONE);
        }
        System.out.println(current_class_halfname);
        if (f.exists()) {
            try {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    classes_array.add(sc.nextLine());
                }
                sc.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        ArrayAdapter<String> load_classname_adapter = new ArrayAdapter<>(this, layout.spinner_layout, classes_array);
        spinner.setAdapter(load_classname_adapter);

        // class selector spinner function

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                current_class_halfname = spinner.getSelectedItem().toString();
                File f = new File(privatefullpath, current_class_halfname + ".xlsx");
                if (f.exists()) {
                    student_array_with_roll_num.clear();
                    try {
                        Scanner sc = new Scanner(f);
                        while (sc.hasNextLine()) {
                            String srn = sc.nextLine();
                            student_array_with_roll_num.add(srn);
                            adapter = new Recycler_adapter(student_array_with_roll_num, MainActivity.this,
                                    current_class_halfname);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            recyclerView.setAdapter(adapter);
                        }
                        sc.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    f = new File(privatefullpath.getParent(), "/" + current_class_halfname + "/"); // create class
                                                                                                   // namefolder
                    f.mkdirs();
                    student_array_with_roll_num.clear();
                    adapter = new Recycler_adapter(student_array_with_roll_num, MainActivity.this,
                            current_class_halfname);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // save attendance function
        saveattendance_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (student_array_with_roll_num.size() > 0) {
                    current_class_halfname = spinner.getSelectedItem().toString();
                    File createfolder = new File(Environment.getExternalStorageDirectory(),
                            "/Attendance/" + current_class_halfname + "/");
                    createfolder.mkdirs();
                    String filename = current_date + ".xlsx";
                    File file = new File(createfolder, filename);
                    try {
                        student_present = adapter.student_list_present;
                        FileWriter fw = new FileWriter(file);
                        if (student_present.size() > 0) {
                            for (int i = 0; i < student_present.size(); i++) {
                                fw.write(student_present.get(i) + "\n");
                            }
                        } else {
                            fw.write("None present");
                        }

                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Create a class and student firstly.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // delete class function
        imageViewdeleteclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete Class?")
                        .setIcon(drawable.ic_baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                classes_array.remove(current_class_halfname);
                                File f = new File(privatefullpath, privateclassfile_fullname);
                                try {
                                    FileWriter fw = new FileWriter(f);
                                    for (int i = 0; i < classes_array.size(); i++) {
                                        fw.write(classes_array.get(i) + "\n");
                                    }
                                    fw.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (classes_array.size() > 0) {
                                    spinner.setSelection(1);
                                } else {
                                    spinner.setSelection(-1);
                                    student_array_with_roll_num.clear();
                                    adapter.notifyDataSetChanged();
                                }
                                f = new File(privatefullpath.getParent(), current_class_halfname);
                                if (f.exists()) {
                                    f.delete();
                                    f = new File(privatefullpath, current_class_halfname + ".xlsx");
                                    if (f.exists()) {
                                        f.delete();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this,
                                            "Class already deleted.\n Select/Create different class", Toast.LENGTH_LONG)
                                            .show();
                                }
                                if (classes_array.size() == 0) {
                                    f = new File(privatefullpath, privateclassfile_fullname);
                                    f.delete();
                                }

                                load_classname_adapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

            }
        });
        // create class function
        imageViewcreateclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dg = new Dialog(MainActivity.this);
                dg.setContentView(layout.dialogbox);
                TextView textView = dg.findViewById(id.dialogtitle);
                textView.setText("Create Class");
                EditText classname = dg.findViewById(id.Student_name);
                EditText studentROll = dg.findViewById(id.Student_roll_number);
                studentROll.setVisibility(View.GONE);
                Button btn = dg.findViewById(id.savedata);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (classname.getText().toString().equals("")) {
                            Toast.makeText(MainActivity.this, "Please fill all data", Toast.LENGTH_LONG).show();
                        } else {
                            classes_array.add(classname.getText().toString().trim());
                            privatefullpath.mkdirs();
                            File f = new File(privatefullpath, privateclassfile_fullname);
                            try {
                                FileWriter fw = new FileWriter(f, true);
                                String cn = classes_array.get(classes_array.size() - 1) + "\n";
                                fw.write(cn);
                                fw.close();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (classes_array.size() != 0) {
                                imageViewopenfolder.setVisibility(View.VISIBLE);
                                imageViewdeleteclass.setVisibility(View.VISIBLE);
                                spinner.setVisibility(View.VISIBLE);
                                createclassmessage.setVisibility(View.GONE);
                            }
                            Toast.makeText(MainActivity.this, "Go to Attendance Folder in File-Manager. ",
                                    Toast.LENGTH_LONG).show();

                            load_classname_adapter.notifyDataSetChanged();
                        }
                        dg.dismiss();
                    }
                });
                dg.show();
            }
        });
        // add student function
        imageViewaddstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Objects.equals(current_class_halfname, "")) {
                    Dialog dg = new Dialog(MainActivity.this);
                    dg.setContentView(layout.dialogbox);
                    EditText studentname = dg.findViewById(id.Student_name);
                    EditText studentROll = dg.findViewById(id.Student_roll_number);
                    Button btn = dg.findViewById(id.savedata);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (studentname.getText().toString().equals("")) {
                                Toast.makeText(MainActivity.this, "Please fill all data", Toast.LENGTH_LONG).show();
                            } else if (studentROll.getText().toString().equals("")) {
                                Toast.makeText(MainActivity.this, "Please fill all data", Toast.LENGTH_LONG).show();
                            } else {
                                String name = studentname.getText().toString().toUpperCase().trim();
                                String roll = studentROll.getText().toString().toUpperCase().trim();
                                String fullintro = String.format("%-20s" + "%s", name, roll);
                                student_array_with_roll_num.add(fullintro);
                                File f = new File(privatefullpath, current_class_halfname + ".xlsx");
                                try {
                                    FileWriter fw = new FileWriter(f, true);
                                    fw.write(fullintro + "\n");
                                    fw.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                adapter.notifyItemInserted(student_array_with_roll_num.size() - 1);
                                recyclerView.scrollToPosition(student_array_with_roll_num.size() - 1);

                            }
                            dg.dismiss();
                        }
                    });
                    dg.show();
                }
                ;
            }// if ends
        });// ends

    }

};
