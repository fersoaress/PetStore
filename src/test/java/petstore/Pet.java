package petstore;

import com.google.gson.Gson;
import entities.Pets;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Pet {
    // Atributos
    String jsonBody;
    String ct = "application/json";
    String uri = "https://petstore.swagger.io/v2/";

    Pets pet = new Pets();
    Gson gson = new Gson();
    //Métodos
    @Test
    public void testCreatePet(){
        //Configura
        pet.id = 209;
        pet.name = "Blair";
        pet.status = "available";
        pet.category.id = 22;
        pet.category.name = "Doguinho";
        pet.tag.id = 89;
        pet.tag.name = "nometag";

        jsonBody = gson.toJson(pet);

        //Executa

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri + "pet")
        //Valida
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(pet.id))
                .body("category.id", is(pet.category.id))
        ;
    }
}
