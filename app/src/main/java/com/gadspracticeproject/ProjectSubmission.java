package com.gadspracticeproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectSubmission extends AppCompatActivity {

    AppCompatButton submit;
    Dialog confirmationDialog;
    private static final String TAG = "ProjectSubmission";
    EditText email, first, last, link;
    ImageButton back;
    String emailAddress, firstName, lastName, projectLink;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);
        setUpUi();

        back.setOnClickListener(view -> finish());


        submit.setOnClickListener(view -> {
            firstName = first.getText().toString();
            lastName = last.getText().toString();
            emailAddress = email.getText().toString();
            projectLink = link.getText().toString();
            Log.d(TAG, "onClick: Clicked");
            if (firstName.isEmpty() || lastName.isEmpty() || emailAddress.isEmpty() || projectLink.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onClick: Empty");

            } else {
                showConfirmationDialog();
            }


        });
    }

    private void setUpUi() {
        back = findViewById(R.id.back);
        email = findViewById(R.id.email);
        first = findViewById(R.id.first_name);
        last = findViewById(R.id.last_name);
        link = findViewById(R.id.project_link);
        submit = findViewById(R.id.submit_button);


        progressDialog = new ProgressDialog(ProjectSubmission.this, R.style.MyGravity);

        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


    }

    private void showConfirmationDialog() {
        confirmationDialog = new Dialog(this);
        confirmationDialog.setContentView(R.layout.confirmation_dialog);
        confirmationDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        AppCompatButton confirmButton = confirmationDialog.findViewById(R.id.confirm_button);
        ImageButton cancel = confirmationDialog.findViewById(R.id.cancel_button);
        cancel.setOnClickListener(view -> confirmationDialog.dismiss());
        confirmButton.setOnClickListener(view -> {
            confirmationDialog.dismiss();
            progressDialog.show();
            sendPostRequest();


            Log.d(TAG, "onClick: Confirm");

        });
        confirmationDialog.show();

    }

    private void showSuccessDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.success_dialog);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();

    }

    private void showErrorDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.error_dialog);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();

    }

    private void sendPostRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d(TAG, "onCreateView: Retrofit Gotten");
        ApiRepo apiRepo = retrofit.create(ApiRepo.class);
        Call<Void> call = apiRepo.submitForm(
                firstName, lastName, emailAddress, projectLink
        );
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    progressDialog.dismiss();
                    showErrorDialog();
                    Log.d(TAG, "onResponse: " + response.message());
                    return;
                }

                progressDialog.dismiss();
                showSuccessDialog();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();
                showErrorDialog();
                Log.d(TAG, "onFailure:" + t.getMessage());

            }
        });
    }
}