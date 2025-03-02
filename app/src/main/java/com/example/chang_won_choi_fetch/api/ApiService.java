package com.example.chang_won_choi_fetch.api;

import com.example.chang_won_choi_fetch.Item;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

/***
 * An Interface to handle HTTP request to Fetch API.
 ***/
public interface ApiService {
    @GET("hiring.json")
    Call<List<Item>> getItems();
}
