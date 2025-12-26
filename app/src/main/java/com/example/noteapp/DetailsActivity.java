package com.example.noteapp;

import static android.app.ProgressDialog.show;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    Button btnEdit, btnDelete;
    EditText etNoteTitle, etNoteDetails;
    DBHelper dbHelper;

    int noteID;
    String noteTitle, noteDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        dbHelper = new DBHelper(this);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        etNoteTitle = findViewById(R.id.etNoteTitle);
        etNoteDetails = findViewById(R.id.etNoteDetails);
        setupButton();
        setupNoteData();

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(btnEdit.getText().equals("Add Note")){
                    saveNote();
                }
                else if(btnEdit.getText().equals("Edit Note")){
                    updateNote();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                confirmDelete();
            }
        });
        }

        private void setupNoteData(){
            Intent intent = getIntent();
            if(intent != null && intent.getStringExtra("action").equals("Edit")){
                noteID=intent.getIntExtra("note_id", 0);
                noteTitle = intent.getStringExtra("note_title");
                noteDetails = intent.getStringExtra("note_details");

                etNoteTitle.setText(noteTitle);
                etNoteDetails.setText(noteDetails);
            }
        }
        private void setupButton(){
            Intent intent = getIntent();
            if(intent!=null && intent.getStringExtra("action").equals("Add")){
                btnEdit.setText("Add Note");
                btnDelete.setVisibility(View.GONE);
            }
        }
        private void saveNote(){
            String title = etNoteTitle.getText().toString().trim();
            String details = etNoteDetails.getText().toString().trim();

            if(title.isEmpty() || details.isEmpty()) {
                Toast.makeText(this, "Please enter both title and details",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            long result = dbHelper.insertNote(title,details);
            if(result != -1){
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Failed to save note", Toast.LENGTH_SHORT).show();
            }
        }

        private void updateNote(){
            String title = etNoteTitle.getText().toString().trim();
            String details = etNoteDetails.getText().toString().trim();

            if(title.isEmpty() || details.isEmpty()) {
                Toast.makeText(this, "Please enter both title and details",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            long result = dbHelper.updateNote(noteID, title,details);
            if(result != -1){
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Failed to save note", Toast.LENGTH_SHORT).show();
            }
        }

        private void confirmDelete() {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Note")
                    .setMessage("Are you sure you want to delete this note?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteNote();
                }
            })
            .setNegativeButton("No", null)
                .show();
        }
        private void deleteNote(){
            if(noteID != 0){
                int result = dbHelper.deleteNote(noteID);
                if(result>0){
                    Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Failed to delete note",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
}


