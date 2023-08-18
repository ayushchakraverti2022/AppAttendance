package com.example.attendance;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Recycler_adapter extends RecyclerView.Adapter<Recycler_adapter.ViewHolder> {
    View v;
    Context context;
    private ArrayList<String> student_list_A = new ArrayList<String>();
    ArrayList<String> student_list_present = new ArrayList<String>();
    String currentclassname;

    public Recycler_adapter(ArrayList<String> student_list, Context context, String currentclass) {
        this.context = context;
        student_list_A = student_list;
        this.currentclassname = currentclass;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(context).inflate(R.layout.student_layout, parent, false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    public View getView() {

        return v;

    }

    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (student_list_A != null && student_list_A.size() > 0) {
            holder.checkBox.setText(student_list_A.get(position).substring(0, 20).toUpperCase());
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.checkBox.isChecked()) {
                        student_list_present.add(student_list_A.get(position));
                        Toast.makeText(context, student_list_A.get(position), Toast.LENGTH_SHORT).show();
                    } else {
                        student_list_present.add("Not present");
                    }
                }
            });
            holder.checkBox.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Delete Student?")
                            .setIcon(R.drawable.ic_baseline_delete_24)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    student_list_A.remove(student_list_A.get(position));
                                    File f = new File(Environment.getExternalStorageDirectory(),
                                            "/Attendance/.private/" + currentclassname + ".xlsx");
                                    try {
                                        FileWriter fw = new FileWriter(f);
                                        for (int i = 0; i < student_list_A.size(); i++) {
                                            fw.write(student_list_A.get(i) + "\n");
                                        }
                                        fw.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    notifyItemRemoved(position);
                                }
                            }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.show();
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return student_list_A.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.studentcheckbox);
        }
    }

}
