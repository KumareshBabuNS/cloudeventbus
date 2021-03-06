/*
 *   Copyright (c) 2013 Mike Heath.  All rights reserved.
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
package cloudeventbus.client;

import cloudeventbus.Subject;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;


/**
 * @author Mike Heath <elcapo@gmail.com>
 */
public class DefaultMessageTest {

	@Test
	public void defaultMessageFields() {
		final String subject = "some.subject";
		final String requestReply = Subject.createRequestReplySubject().toString();
		final String body = "Message body";
		final DefaultMessage message = new DefaultMessage(subject, requestReply, body);
		assertEquals(message.getSubject(), subject);
		assertEquals(message.getReplySubject(), requestReply);
		assertEquals(message.getBody(), body);
		assertTrue(message.isRequest());
	}

	@Test(expectedExceptions = UnsupportedOperationException.class)
	public void reply() {
		new DefaultMessage("subject", null, "body").reply("This should break.");
	}

	@Test(expectedExceptions = UnsupportedOperationException.class)
	public void delayedReply() {
		new DefaultMessage("subject", null, "body").reply("This should break.", 1, TimeUnit.MILLISECONDS);
	}

}
