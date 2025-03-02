package com.example.chang_won_choi_fetch;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chang_won_choi_fetch.api.ApiService;
import com.example.chang_won_choi_fetch.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Main Activity", "Starting");

        // Create Retrofit client
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Make the API call
        apiService.getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                // Check if the response is successful
                if (response.isSuccessful() && response.body() != null) {
                    itemList = response.body();
                    Log.i("Main Activity", itemList.get(2).getName());
                } else {
                    Log.e("Main Activity", "Error retrieving data");
                    Toast.makeText(MainActivity.this, "Error retrieving data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e("Main Activity", "Network error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}