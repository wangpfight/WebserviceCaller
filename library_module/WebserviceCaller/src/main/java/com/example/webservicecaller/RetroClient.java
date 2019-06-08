package com.example.webservicecaller;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class RetroClient {

    public static final class Builder {
        private String base_url;
        private static final int DEFAULT_TIMEOUT_TIME = 120;
        private int timeout;
        private Context context;
        private String loading_message;
        private IResult iResult;
        private RetroClient retroClient;
        private Call<ResponseBody> call;

        public void setTimeOut(int timeout) {
            this.timeout = timeout;
        }

        public Builder() {}

        public Builder(Context context) {
            this.context = context;
        }

        public void addListener(IResult iResult) {
            this.iResult = iResult;
        }

        public void addBaseUrl(String base_url) {
            this.base_url = base_url;
        }

        public void setMessage(String message){
            this.loading_message = message;
        }

        public class ServiceCaller {
            private ServiceCaller(){
            }
            public <T> T getRetrofitAPI(Class<T> apiClass) {
                if(retroClient == null){
                    throw new UnsupportedOperationException("Client Not Initiated");
                }
                return retroClient.getRetrofitAPI(apiClass);
            }

            /**
             * Call service synchronous
             * @param call
             */
            public  IResult.ServerResponse<String> callServiceSyn(Call<ResponseBody> call) {
                return retroClient.callService(call);
            }

            public void callService(Call<ResponseBody> call) {
                retroClient.callService(context,loading_message,call);
            }

            public void callService(Call<ResponseBody> call,String loading_message) {
                Builder.this.loading_message = loading_message;
                retroClient.callService(context,loading_message,call);
            }
        }


        public ServiceCaller build(){
            createRetroClient();
            ServiceCaller serviceCaller =  new ServiceCaller();
            return  serviceCaller;
        }

        private void createRetroClient() {
            if (timeout <= 0) {
                timeout = DEFAULT_TIMEOUT_TIME;
            }

            if (base_url == null || "".equals(base_url)) {
                throw new UnsupportedOperationException("BaseUrl not added");
            }

            if(iResult != null) {
                retroClient = new RetroClient(base_url, timeout, iResult);
            }else {
                retroClient = new RetroClient(base_url, timeout);
            }

        }
    }

    private Retrofit retrofit;
    private IResult iResult;
    private static final int DEFAULT_TIMEOUT_TIME = 120;
    private ProgressDialog progressDialog;
    private static final String TAG = "RetroClient";

    private <T> T getRetrofitAPI(Class<T> apiClass) {
        return retrofit.create(apiClass);
    }

    private RetroClient(String BASE_URL, int timeout_time, IResult iResult) {
        this.iResult = iResult;
        createRetrofitAdapter(BASE_URL, timeout_time);
    }

    private RetroClient(String BASE_URL, int timeout_time) {
        createRetrofitAdapter(BASE_URL, timeout_time);
    }

    private void callService(Context context, String loading_message, Call<ResponseBody> call) {
        if (loading_message == null || !"".equals(loading_message)) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(loading_message);
            progressDialog.show();
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dismissDialog(progressDialog);
                if (iResult != null) {
                    try {
                        InputStream inputStream = response.body().byteStream();
                        String string_response = convertToString(inputStream);
                        IResult.ServerResponse<String> serverResponse = new IResult.ServerResponse<String>();
                        serverResponse.addResponse(string_response);
                        Log.d(TAG, "Response:\n" + string_response);
                        iResult.onResult(serverResponse);
                    } catch (Exception e){
                        if (iResult != null) {
                            iResult.onError(e);
                        }
                        Log.d(TAG, Log.getStackTraceString(e));
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable error) {
                Log.d(TAG, Log.getStackTraceString(error));
                dismissDialog(progressDialog);
                if (iResult != null)
                    iResult.onError(error);
            }
        });

    }

    /**
     * This is service call in synchronous way , need call this from other thread
     * @param call
     */
    private IResult.ServerResponse<String> callService(Call<ResponseBody> call)  {
        IResult.ServerResponse<String> serverResponse = new IResult.ServerResponse<String>();
        try {
            Response<ResponseBody> response = call.execute();
            InputStream inputStream = response.body().byteStream();
            String string_response = convertToString(inputStream);
            serverResponse.addResponse(string_response);
        } catch (Exception e) {
            Log.d(TAG, Log.getStackTraceString(e));
        }
        return serverResponse;
    }


    private void dismissDialog(ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    private static  String convertToString(InputStream inputStream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }


    private void createRetrofitAdapter(String base_url, int timeout_time_in_seconds) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(timeout_time_in_seconds * 1000, TimeUnit.MILLISECONDS)
                .build();
        Retrofit.Builder retrofit_builder = new Retrofit.Builder();
        retrofit_builder.client(okHttpClient);
        retrofit_builder.baseUrl(base_url);
        retrofit_builder.addConverterFactory(GsonConverterFactory.create());
        retrofit = retrofit_builder.build();
    }

}

