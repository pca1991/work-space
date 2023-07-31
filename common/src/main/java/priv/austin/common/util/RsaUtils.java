package priv.austin.common.util;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/**
 * @author Austin
 * @description RSA工具类
 * @date 2023/7/28 16:42
 */
public class RsaUtils {
    private static final String ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final int KEY_SIZE = 2048;

    // 生成RSA密钥对
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }
    // 字符串转公钥
    public static PublicKey toPublicKey(String publicKeyString) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        return KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(publicKeyBytes));
    }
    // 字符串转私钥
    public static PrivateKey toPrivateKey( String privateKeyString) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        return KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
    }
    // 密钥转字符串
    public static String toKeyString(Key key) throws Exception {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
    // 使用公钥加密数据
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        //return new String(Base64.encodeBase64( cipher.doFinal(data.getBytes())));
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }
    // 使用私钥解密数据
    public static String decrypt(String encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedData = cipher.doFinal( Base64.getDecoder().decode(encryptedData.getBytes()));
        return new String(decryptedData);
    }
    // 使用私钥对数据进行签名
    public static String sign(byte[] data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);
        return Base64.getEncoder().encodeToString(signature.sign());
    }
    // 使用公钥验证签名
    public static boolean verify(byte[] data, String signature, PublicKey publicKey) throws Exception {
        Signature verifySignature = Signature.getInstance(SIGNATURE_ALGORITHM);
        verifySignature.initVerify(publicKey);
        verifySignature.update(data);
        return verifySignature.verify( Base64.getDecoder().decode(signature.getBytes()));
    }

    public static void main(String[] args) {
        try {
            KeyPair keyPair = generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            System.out.println("公钥：" +   toKeyString(publicKey));
            PrivateKey privateKey = keyPair.getPrivate();
            System.out.println("私钥：" + toKeyString(privateKey));

            String data = "Hello World!";
            String encryptedData = encrypt(data, publicKey);
            System.out.println("加密后数据：" + encryptedData);
            String decryptedData = decrypt(encryptedData, privateKey);
            System.out.println("解密后数据：" + decryptedData);
            String signature = sign(data.getBytes(), privateKey);
            System.out.println("签名结果：" + signature);
            boolean verify = verify(data.getBytes(), signature, publicKey);
            System.out.println("验签结果：" + verify);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
