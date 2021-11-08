package com.s2icode.android_rsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class MainActivity extends AppCompatActivity {

    private byte[] publicKey;
    private byte[] privateKey;
    private byte[] encrypt;
    private String data;
    private byte[] decrypt;

    // https://www.jianshu.com/p/7841eae98d16
    // https://blog.csdn.net/qq_43685172/article/details/87729149
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button encryptButton = findViewById(R.id.s2i_encrypt);
        Button decryptButton = findViewById(R.id.s2i_decrypt);
        Button keypairButton = findViewById(R.id.s2i_keypair);
        final TextView encryptText = findViewById(R.id.s2i_encrypt_content);
        final TextView decryptText = findViewById(R.id.s2i_decrypt_content);

        final int keyLength = 1024;
        data = ((EditText) findViewById(R.id.s2i_encrypt_text)).getText().toString();

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用公钥加密
                try {
                    encrypt = encryptByPublicKey(((EditText) findViewById(R.id.s2i_encrypt_text)).getText().toString().getBytes(), publicKey);
                    encryptText.setText(byteArrayToString(encrypt));
                    Log.d("TAG", "加密后的数据：" + byteArrayToString(encrypt));
                } catch (Exception e) {
                    encrypt = null;
                    e.printStackTrace();
                }
            }
        });
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //用私钥解密
                    decrypt = decryptByPrivateKey(encrypt, privateKey);
                    decryptText.setText( new String(decrypt, "utf-8"));
                    Log.d("TAG", "解密后的数据：" + new String(decrypt, "utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        keypairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    KeyPair keyPair = generateRSAKeyPair(keyLength);
                    //生成密钥对
                    //获取公钥
                    publicKey = getPublicKey(keyPair);
                    //获取私钥
                    privateKey = getPrivateKey(keyPair);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String byteArrayToString(byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }

        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = hexArray[v >>> 4];
            hexChars[i * 2 +1] = hexArray[v & 0x0F];
        }
        return  new String(hexChars);
    }

    /** 生成密钥对，即公钥和私钥。key长度是512-2048，一般为1024 */
    public static KeyPair generateRSAKeyPair(int keyLength) throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
        kpg.initialize(keyLength);
        return kpg.genKeyPair();
    }

    /** 获取公钥，打印为48-12613448136942-12272-122-913111503-126115048-12...等等一长串用-拼接的数字 */
    public static byte[] getPublicKey(KeyPair keyPair) {
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        return rsaPublicKey.getEncoded();
    }

    /** 获取私钥，同上 */
    public static byte[] getPrivateKey(KeyPair keyPair) {
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        return rsaPrivateKey.getEncoded();
    }

    /**RSA算法*/
    public static final String RSA = "RSA";
    /**加密方式，android的*/
//  public static final String TRANSFORMATION = "RSA/None/NoPadding";
    /**加密方式，标准jdk的*/
    public static final String TRANSFORMATION = "RSA/None/PKCS1Padding";

    /** 使用公钥加密 */
    public static byte[] encryptByPublicKey(byte[] data, byte[] publicKey) throws Exception {
        // 得到公钥对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        // 加密数据
        Cipher cp = Cipher.getInstance(TRANSFORMATION);
        cp.init(Cipher.ENCRYPT_MODE, pubKey);
        return cp.doFinal(data);
    }

    /** 使用私钥解密 */
    public static byte[] decryptByPrivateKey(byte[] encrypted, byte[] privateKey) throws Exception {
        // 得到私钥对象
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory kf = KeyFactory.getInstance(RSA);
        PrivateKey keyPrivate = kf.generatePrivate(keySpec);
        // 解密数据
        Cipher cp = Cipher.getInstance(TRANSFORMATION);
        cp.init(Cipher.DECRYPT_MODE, keyPrivate);
        return cp.doFinal(encrypted);
    }
}