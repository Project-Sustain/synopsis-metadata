package sustain.synopsis.metadata.rest;

import org.junit.Assert;
import org.junit.Test;
import sustain.synopsis.metadata.rest.user.UserCredentials;

import java.lang.reflect.Field;

public class UserCredentialsTest {

    public boolean emailValidates(String email) throws NoSuchFieldException {
        Field field = UserCredentials.class.getDeclaredField("email");
        javax.validation.constraints.Pattern[] annotations = field.getAnnotationsByType(javax.validation.constraints.Pattern.class);
        return email.matches(annotations[0].regexp());
    }

    public boolean passwordValidates(String password) throws NoSuchFieldException {
        Field field = UserCredentials.class.getDeclaredField("password");
        javax.validation.constraints.Pattern[] annotations = field.getAnnotationsByType(javax.validation.constraints.Pattern.class);
        return password.matches(annotations[0].regexp());
    }

    @Test
    public void testValidEmail() throws NoSuchFieldException {
        Assert.assertTrue(emailValidates("keegan.millard@rams.colostate.edu"));
    }

    @Test
    public void testInvalidEmail() throws NoSuchFieldException {
        Assert.assertFalse(emailValidates("keegan.millardrams.colostate.edu"));
    }

    @Test
    public void testValidPasswords() throws NoSuchFieldException {
        Assert.assertTrue(passwordValidates("Mypassword1!"));
        Assert.assertTrue(passwordValidates("mypasswordisverylong"));
        Assert.assertTrue(passwordValidates("mypasswordisvery!long1"));
    }

    @Test
    public void testInValidPasswords() throws NoSuchFieldException {
        Assert.assertFalse(passwordValidates("@#$)(*@*"));
        Assert.assertFalse(passwordValidates(""));
        Assert.assertFalse(passwordValidates("2"));
        Assert.assertFalse(passwordValidates("Mypassword!"));
        Assert.assertFalse(passwordValidates("Mypassword1"));
        Assert.assertFalse(passwordValidates("Mypasswordisfartoolongthisisreallyquietexcessivedontyouthink"));
    }






}
