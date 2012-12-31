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
package cloudeventbus.codec;

import cloudeventbus.Subject;
import io.netty.buffer.ByteBuf;

/**
 * @author Mike Heath <elcapo@gmail.com>
 */
public class AbstractMessageFrame implements Frame {
	private final Subject subject;
	private final Subject replySubject;
	private final ByteBuf body;

	public AbstractMessageFrame(Subject subject, Subject replySubject, ByteBuf body) {
		this.subject = subject;
		this.replySubject = replySubject;
		this.body = body;
	}

	public Subject getSubject() {
		return subject;
	}

	public Subject getReplySubject() {
		return replySubject;
	}

	public ByteBuf getBody() {
		return body;
	}
}
