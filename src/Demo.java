import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Demo {
    public static void main(String[] args) throws Exception
    {
        /* 需指定Key(iv仅CBC需要)
         * 默认非HEX模式, utf-8字符编码 */

        SM4Utils sm4 = new SM4Utils();
        String sm4Key = SM4Utils.getHexKey(16);
        sm4.setSecretKey(sm4Key);
        sm4.setHexString(false);
        sm4.setEnCoding("utf-8");
        System.out.printf("ECB模式, Key=%s \n" , sm4Key);

        String src = "中文字段abc123";
        String cipherText = sm4.encryptData_ECB(src);
        String plainText = sm4.decryptData_ECB(cipherText);
        System.out.printf("密文:%s \n明文:%s\n" , cipherText , plainText);
//        System.out.printf("\n");
//
//        String iv = SM4Utils.getHexKey(16);
//        System.out.printf("CBC模式, Key=%s Iv=%s \n" , sm4Key, iv);
//        sm4.setIv(iv);
//
//        cipherText = sm4.encryptData_CBC(src);
//        plainText = sm4.decryptData_CBC(cipherText);
//        System.out.printf("密文:%s \n明文:%s\n" , cipherText , plainText);
//
//        /**/
//        System.out.println("十六进制形式");
//        sm4.setHexString(true);
//        sm4Key = SM4Utils.getHexKey(32);
//        sm4.setSecretKey(sm4Key);
//
//        cipherText = sm4.encryptData_ECB(src);
//        plainText = sm4.decryptData_ECB(cipherText);
//        System.out.printf("密文:%s \n明文:%s\n" , cipherText , plainText);
//        System.out.printf("\n");
//
//        iv = SM4Utils.getHexKey(32);
//        System.out.printf("CBC模式, Key=%s Iv=%s \n" , sm4Key, iv);
//        sm4.setIv(iv);
//
//        cipherText = sm4.encryptData_CBC("abc123中文字段");
//        plainText = sm4.decryptData_CBC(cipherText);
//        System.out.printf("密文:%s \n明文:%s\n" , cipherText , plainText);
//
//        System.out.println("RSA签名Demo");
//        SHA256withRSA rsaUtil = new SHA256withRSA();
//        String rsaPubKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnLwqIch5nh9R/pf8/jFKr2LnPvCkXKHPPbN5CoJSybnUj7Zpmag9jrDTIZA+JDkp3zIF80+WRC8lNU8yNMCigJlB0O91t/865ei9ukHESAX8skiMmu+c5LMSgvh1q5mt6BVzG1dcE1C/YnAjFM+UpUjr+e7CYPDQMElbs9vih+kcWRPUcA5eiYHZxAeKnPWjSDt3oiSYMTM+ZkDwQtWtMDhzbIhza9C2ASo6RcCKXRG/6XvtwkoH5izq70sDsqXejZwtlRFOky/J8eKoCvDdITrECeQIvwNKErZTVpld8E9jQSiukNyiRqVumN4sih8C+Zz8NgLpWaeaxYhNZ3IMzQIDAQAB";
//        String rsaPriKey="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCcvCohyHmeH1H+l/z+MUqvYuc+8KRcoc89s3kKglLJudSPtmmZqD2OsNMhkD4kOSnfMgXzT5ZELyU1TzI0wKKAmUHQ73W3/zrl6L26QcRIBfyySIya75zksxKC+HWrma3oFXMbV1wTUL9icCMUz5SlSOv57sJg8NAwSVuz2+KH6RxZE9RwDl6JgdnEB4qc9aNIO3eiJJgxMz5mQPBC1a0wOHNsiHNr0LYBKjpFwIpdEb/pe+3CSgfmLOrvSwOypd6NnC2VEU6TL8nx4qgK8N0hOsQJ5Ai/A0oStlNWmV3wT2NBKK6Q3KJGpW6Y3iyKHwL5nPw2AulZp5rFiE1ncgzNAgMBAAECggEAchE/WHbejAWfVvqmVRjqkdoZEeVwuTnJ6c3EObG/LrDf0fefwwR+SGrIPZUMpLA1mQp+6iTx542oNrP8hgfsPEtNaxHbSp2l780Cx5s1jnVNAzNpoRjhxk/NGSRZ/xlJR67UpwFdMXGC19FktrCcLe50TuFE9ehMm9fHtY5Bv0b0YOze3I36tpX8vxREqfYvd18p5uOLuCrJu1+ju2kNh7auolO95I/vpIAXDIOFe/bGBgOwZEeMFwxw2lGarRRTe9iDNPcPFpBFfhJjfXkpS+uUdzgNsorVTEvu/MY9aF5Vff5knHrLwwlr1MWTHiWhQ0exaQstswrutIbs8SgqwQKBgQDQTTerRkJ2Zt6FG34npg39cb+zg80X2LrcK2zpe5rIaAVIggL3eN9r6r+LwUM+KF9HWsQtSGIMDXTwcDDIMNdaVuY7ypDLdhH5I8SB7vlZMQ6AvqtoVVCeTonS2EQ9pmpeK3NnPIxPZe5LiG5jigEelVs8bJE4KGESRNyGAql5SwKBgQDAoBPPdPS+zcmdMokx2ink+H+JsIS+oSxfRuL5UnRMLJFXRCocEMDq/2cvRAZY1SLJpJB4udeUSUIG05esGWKm0kGABGn9lmDoLDJoUkJdLiZr0BeJmTpmmMyaNbz7oGVU+GczxuCNoSNvKT9k2P7kxe/iDEZftF0cCucysmObRwKBgAMtqnPKlqkH2M1fJgWk2wHLAonSsQZ0ICXL49WMNUhpjesVUMZF6eLTqiiPwl7obsu/7s5iFFzqt6YTkCr908tlGA5BgQQZwRx0F0OESfm6beY5ApSM+LXxrNiyxBfMukLFlvU0T0/G1mQAqO6L5Ih67vF1FqIsu45zzomc8MkRAoGAJ+0GLin/0My2zW58IWkm5d7cEJ4V5a1ilR0MTsFYebGDLqNiVw7h7dOif5mJ9z+2RjVl/KAInyPQG7of5fwdBpbuHKrNjVJuI7GFgToLp2S5cf9f4ZVxFXL2dcBNu5ozZrlWKtU+Fh0gSxoxmaWgr6qXXjNNIukvo3BfG5L+MmECgYEAwV4svjlDuKtQNaLmYW4J9wXcKzU4vwpqkIfx37qf14Le0P5gGtbeENuHG1GibG77oyDLPSv6iiL7xz4oZdG6GhwwV3ugrAjB1c4We9RInVbdrHoLmHi1Y2wCL0C1kCVchaUBo9aslPG9m1AAOVlNLpdmE9IcgzESPZF7SNMpP18=";
//        String signText  = rsaUtil.sign("abc123".getBytes() , rsaPriKey);
//        System.out.println(rsaUtil.verify("abc123".getBytes(),signText,rsaPubKey));
    }
}
