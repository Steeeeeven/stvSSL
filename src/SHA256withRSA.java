import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SHA256withRSA {
    /*用私钥加签*/
    public String sign( byte[] srcData, String privateKey)
            throws Exception
    {
        byte[] privateKeyByte = Base64.getDecoder().decode(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(
                privateKeyByte);
        PrivateKey pK = keyFactory.generatePrivate(encodedKeySpec);

        // 加签
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initSign(pK);
        signature.update(srcData);
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /*用公钥验签*/
    public String verify(byte[] srcData, String targetSign,
                         String pubKey) throws Exception
    {
        // 生成公钥
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] publicKeyByte = Base64.getDecoder().decode(pubKey);
        X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(
                publicKeyByte);
        PublicKey publicKey = keyFactory.generatePublic(encodedKeySpec);

        // 验签
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initVerify(publicKey);
        signature.update(srcData);
        boolean result = signature.verify(Base64.getDecoder().decode(targetSign));
        return result + "";
    }
}
