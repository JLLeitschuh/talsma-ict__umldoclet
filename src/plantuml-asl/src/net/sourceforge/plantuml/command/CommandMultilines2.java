/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
 *
 * Project Info:  https://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * https://plantuml.com/patreon (only 1$ per month!)
 * https://plantuml.com/paypal
 * 
 * This file is part of PlantUML.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * Original Author:  Arnaud Roques
 */
package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.StringLocated;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.Matcher2;
import net.sourceforge.plantuml.command.regex.MyPattern;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.ugraphic.color.NoSuchColorException;

public abstract class CommandMultilines2<S extends Diagram> implements Command<S> {

	private final IRegex starting;

	private final MultilinesStrategy strategy;

	public CommandMultilines2(IRegex patternStart, MultilinesStrategy strategy) {
		if (patternStart.getPattern().startsWith("^") == false || patternStart.getPattern().endsWith("$") == false) {
			throw new IllegalArgumentException("Bad pattern " + patternStart.getPattern());
		}
		this.strategy = strategy;
		this.starting = patternStart;
	}

	public boolean syntaxWithFinalBracket() {
		return false;
	}

	public abstract String getPatternEnd();

	public String[] getDescription() {
		return new String[] { "START: " + starting.getPattern(), "END: " + getPatternEnd() };
	}

	final public CommandControl isValid(BlocLines lines) {
		lines = lines.cleanList(strategy);
		if (isCommandForbidden())
			return CommandControl.NOT_OK;

		if (syntaxWithFinalBracket()) {
			if (lines.size() == 1 && lines.getFirst().getTrimmed().getString().endsWith("{") == false) {
				final String vline = ((StringLocated) lines.getAt(0)).getString() + " {";
				if (isValid(BlocLines.singleString(vline)) == CommandControl.OK_PARTIAL)
					return CommandControl.OK_PARTIAL;

				return CommandControl.NOT_OK;
			}
			lines = lines.eventuallyMoveBracket();
		}
		final StringLocated first = lines.getFirst();
		if (first == null)
			return CommandControl.NOT_OK;

		final boolean result1 = starting.match(first.getTrimmed());
		if (result1 == false)
			return CommandControl.NOT_OK;

		if (lines.size() == 1)
			return CommandControl.OK_PARTIAL;

		final Matcher2 m1 = MyPattern.cmpile(getPatternEnd()).matcher(lines.getLast().getTrimmed().getString());
		if (m1.matches() == false)
			return CommandControl.OK_PARTIAL;

		return finalVerification(lines);
	}

	public final CommandExecutionResult execute(S system, BlocLines lines) {
		lines = lines.cleanList(strategy);
		if (syntaxWithFinalBracket())
			lines = lines.eventuallyMoveBracket();

		try {
			return executeNow(system, lines);
		} catch (NoSuchColorException e) {
			return CommandExecutionResult.badColor();
		}
	}

	protected abstract CommandExecutionResult executeNow(S system, BlocLines lines) throws NoSuchColorException;

	protected boolean isCommandForbidden() {
		return false;
	}

	protected CommandControl finalVerification(BlocLines lines) {
		return CommandControl.OK;
	}

	protected final IRegex getStartingPattern() {
		return starting;
	}

}
