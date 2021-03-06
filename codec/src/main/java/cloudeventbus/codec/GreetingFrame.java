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

import java.util.regex.Pattern;

/**
 * @author Mike Heath <elcapo@gmail.com>
 */
public class GreetingFrame implements Frame {
	private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s");

	private final int version;
	private final String agent;
	private final long id;

	public GreetingFrame(int version, String agent, long id) {
		if (WHITESPACE_PATTERN.matcher(agent).find()) {
			throw new IllegalArgumentException("agent cannot contain whitespace.");
		}
		this.version = version;
		this.agent = agent;
		this.id = id;
	}

	public String getAgent() {
		return agent;
	}

	public int getVersion() {
		return version;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Greeting version=" + version + " agent='" + agent + "\' id=" + id;
	}

	@Override
	public FrameType getFrameType() {
		return FrameType.GREETING;
	}
}
