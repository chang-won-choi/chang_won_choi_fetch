package com.example.chang_won_choi_fetch;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chang_won_choi_fetch.adapter.ItemsAdapter;
import com.example.chang_won_choi_fetch.api.ApiService;
import com.example.chang_won_choi_fetch.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> itemList = new ArrayList<>();
    private ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up RecyclerView with custom Adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemsAdapter = new ItemsAdapter(new ArrayList<>());
        recyclerView.setAdapter(itemsAdapter);

        // Create Retrofit client
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Make the API call
        apiService.getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                // Check if the response is successful
                if (response.isSuccessful() && response.body() != null) {
                    itemList = response.body();
                    // Filter items without name
                    filterItems();
                    // Sort the items by listId and then name
                    sortItems();
                    // Pass updated list to the adapter
                    itemsAdapter.updateItems(itemList);
                    Log.i("Main Activity", "Items retrieved");
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

    /***
     * Function to filter out the items without name
     ***/
    private void filterItems() {
        // Iterate in reverse to not affect the array as item gets removed
        for (int i = itemList.size()-1; i>=0; i--) {
            Item item = itemList.get(i);
            // Check if name is null or ""
            if (item.getName() == null || item.getName().trim().isEmpty()) {
                itemList.remove(i);
            }
        }
        Log.i("Main Activity", itemList.get(0).getName());
    }

    /***
     * Function to sort the items list by listId and then name
     ***/
    private void sortItems() {
        Collections.sort(itemList, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                // If listId different, then sort using listId
                if (o1.getListId() != o2.getListId()) {
                    return Integer.compare(o1.getListId(), o2.getListId());
                }
                // Else use name
                return o1.getName().compareTo(o2.getName());
            }
        });
        Log.i("Main Activity", itemList.get(0).getListId() + " " + itemList.get(0).getName());
        Log.i("Main Activity", itemList.get(1).getListId() + " " + itemList.get(1).getName());
        Log.i("Main Activity", itemList.get(2).getListId() + " " + itemList.get(2).getName());
        Log.i("Main Activity", itemList.get(3).getListId() + " " + itemList.get(3).getName());
    }
}