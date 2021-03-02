import api.common.exception.InvalidResponseException;
import com.petclinic.api.onlineshopcontroller.ServicesApiClient;
import com.petclinic.api.onlineshopcontroller.online.data.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ServicesApiTest {

    static String apiUrl;
    static Integer statusCode;

    SoftAssertions softly = new SoftAssertions();

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }

    @Test
    public void getServices_StatusCode() throws InvalidResponseException {
        ServicesApiClient client = new ServicesApiClient(apiUrl, "api/shop/getproducts");
        statusCode = client.getStatusCode();
        softly.assertThat(statusCode).isEqualTo(200);
    }

    @Test
    public void getDetails_getProducts() throws InvalidResponseException {
        ServicesApiClient client = new ServicesApiClient(apiUrl, "api/shop/getproducts");
        Response[] response = client.getProductDetails();

        System.out.println("\n The response is "+" "+response[0]);
        statusCode = client.getStatusCode();

        softly.assertThat(response[0].getName()).as("Name").isEqualTo("Kennel Kitchen");
        softly.assertThat(response[0].getId()).as("ID").isEqualTo(1);
        softly.assertThat(response[0].getAmount()).as("Amount").isEqualTo(635);
        softly.assertThat(response[0].getCurrency()).as("Currency").isEqualTo("$");
        softly.assertThat(statusCode).isEqualTo(200);
        softly.assertAll();




    }

    @Test
    public void addDetails_addToCart() throws InvalidResponseException {
        ServicesApiClient client = new ServicesApiClient(apiUrl,"api/shop/addtocart/");
        Response response1 = client.addToCart(Response.builder()
        .amount(451)
        .currency("$")
        .id(88)
        .name("Goat").build());



        softly.assertThat(response1.getAmount()).isEqualTo(451);
        softly.assertThat(response1.getCurrency()).isEqualTo("$");
        softly.assertThat(response1.getName()).isEqualTo("Goat");


        softly.assertAll();


    }

    @Test
    public void getDetails_orderSummary() throws InvalidResponseException {

        ServicesApiClient client1 = new ServicesApiClient(apiUrl,"api/shop/ordersummary/");
        Response response = client1.getOrderSummary().getContent();
        Integer Total = response.getTotalAmount();

        System.out.println("The total amount in the cart is"+" "+Total);

        softly.assertThat(response.getAmount()).isEqualTo("1010");
        softly.assertThat(response.getCurrency()).isEqualTo("INR");
        softly.assertThat(response.getName()).isEqualTo("sweetcake");
        softly.assertAll();

    }


}
