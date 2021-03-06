/*
 *   Copyright (c) 2012 Mike Heath.  All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/**
 * @author Mike Heath <elcapo@gmail.com>
 */
public class SimpleCertSpike {
	public static void main(String[] args) throws Exception {
		// Generate an RSA keypair
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		System.out.println(keyPair.getPublic().getFormat());
		ByteArrayOutputStream certificateStream = new ByteArrayOutputStream();
		final byte[] encodedPublicKey = keyPair.getPublic().getEncoded();
		certificateStream.write(encodedPublicKey);

		final byte[] cert = certificateStream.toByteArray();
		System.out.println("Cert length: " + cert.length);

		MessageDigest md = MessageDigest.getInstance("SHA-1");
		final byte[] hash = md.digest(cert);

		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
		final byte[] sig = cipher.doFinal(hash);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		final byte[] decryptedHash = cipher.doFinal(sig);

		System.out.println("Sig length: " + sig.length);
		System.out.println("Hash: " + Arrays.toString(hash));
		System.out.println("Decrypted Hash: " + Arrays.toString(decryptedHash));
	}
}
