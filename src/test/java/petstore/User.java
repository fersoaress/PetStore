package petstore;

import com.google.gson.Gson;
import entities.Users;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class User {
    //Atributos
    String jsonBody;
    String ct = "application/json";
    String uri = "https://petstore.swagger.io/v2/";
    Users user = new Users();
    Gson gson = new Gson();

    //Métodos
    @Test (priority = 0)
    public void testCreateUser(){
        //Configura
        user.id = 2572;
        user.username = "fernandass";
        user.firstName = "Fernanda";
        user.lastName = "Soares";
        user.email = "teste@teste.com.br";
        user.password = "654321";
        user.phone = "515656565656";
        user.userStatus = 1;

        jsonBody = gson.toJson(user);

        String message = "2572";

        //Executa
        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri + "user")
        //Valida
        .then()
                .log().all()
                .statusCode(200)
                .body("message", is(message))

        ;
    }
    @Test (priority = 1)
    public void testResearchUser(){
        //Configura
        //Não precisa configurar nada pois a entrada já foi configurada no testCreateUser
        //Executa
        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uri + "user/" + user.username)
        .then()
                .log().all()
                .statusCode(200)
                .body("username", is(user.username))
        ;

        //Valida
    }
}
