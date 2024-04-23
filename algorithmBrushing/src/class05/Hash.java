package class05;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class Hash {

    private MessageDigest hash;

    public Hash(String algorithm) {
        try {
            hash = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String hashCode(String input) {
        return DatatypeConverter.printHexBinary(hash.digest(input.getBytes())).toUpperCase();
    }

}
