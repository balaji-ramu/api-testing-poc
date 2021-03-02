package com.petclinic.api.onlineshopcontroller;

import api.common.ApiClient;
import api.common.ApiRequest;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import com.google.gson.GsonBuilder;
import com.petclinic.api.onlineshopcontroller.online.data.Response;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;

public class ServicesApiClient extends ApiClient {

    public ServicesApiClient(String baseUrl, String basePath) {
        super(baseUrl, basePath);

        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));
    }

    public Integer getStatusCode() throws InvalidResponseException {

        ApiResponse  response = caller.executeRequest(getRequest(), Method.GET, Response.class);
        return response.getHttpStatusCode();

    }

    public Integer postStatusCode() throws InvalidResponseException {

        ApiResponse  response = caller.executeRequest(getRequest(), Method.POST, Response.class);
        return response.getHttpStatusCode();

    }


    public Response[] getProductDetails() throws InvalidResponseException {

        ApiResponse<Response[]> response = caller.executeRequest(getRequest(), Method.GET, Response[].class);
        return response.getContent();


    }

    public Response addToCart(Response response) throws InvalidResponseException {

        ApiRequest request = getRequest().withBody(response).withHeader("Content-Type", "application/json");
        ApiResponse<Response> response1 = caller.executeRequest(request, Method.POST, Response.class);
        return response1.getContent();


    }

   public ApiResponse<Response> getOrderSummary() throws InvalidResponseException {

        ApiResponse<Response> response = caller.executeRequest(getRequest(), Method.GET, Response.class);
        return response;
   }




}

