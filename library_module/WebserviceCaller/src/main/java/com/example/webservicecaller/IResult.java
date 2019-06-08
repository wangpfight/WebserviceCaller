package com.example.webservicecaller;

public interface IResult {

    class ServerResponse<T> {
        private T response;
        public void addResponse(T response){
            this.response = response;
        }

        public T getResponse() {
            return response;
        }

    }

    public void onResult(ServerResponse<String>  serverResponse);
    public void onError(Throwable throwable);
}
