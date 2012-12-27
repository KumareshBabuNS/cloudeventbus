package cloudeventbus.pki;

import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * @author Mike Heath <heathma@ldschurch.org>
 */
public class CertificateStoreLoaderTest {

	@Test
	public void storeAndLoad() throws Exception {
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		final Certificate certificate1 = new Certificate(Certificate.Type.AUTHORITY, 1l, 2l, System.currentTimeMillis(), keyPair.getPublic(), Arrays.asList("foo.*"), Arrays.asList("bar.*"), null, null);
		final Certificate certificate2 = new Certificate(Certificate.Type.SERVER, 3l, 4l, System.currentTimeMillis(), keyPair.getPublic(), Arrays.asList("foo2.*"), Arrays.asList("bar2.*"), "Certificate 2", null);

		final List<Certificate> certificates = new ArrayList<>();
		certificates.add(certificate1);
		certificates.add(certificate2);

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		CertificateStoreLoader.store(out, certificates);

		final ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		final List<Certificate> copiedCertificates = new ArrayList<>();
		CertificateStoreLoader.load(in, copiedCertificates);

		assertEquals(2, copiedCertificates.size());
		final Iterator<Certificate> certificateIterator = copiedCertificates.iterator();
		assertEquals(certificate1, certificateIterator.next());
		assertEquals(certificate2, certificateIterator.next());
		assertFalse(certificateIterator.hasNext());
	}
}