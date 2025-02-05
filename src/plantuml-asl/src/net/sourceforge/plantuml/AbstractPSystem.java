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
package net.sourceforge.plantuml;

import static net.sourceforge.plantuml.ugraphic.ImageBuilder.imageBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

import net.sourceforge.plantuml.command.BlocLines;
import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.ProtectedCommand;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.DisplayPositioned;
import net.sourceforge.plantuml.cucadiagram.DisplayPositionned;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.VerticalAlignment;
import net.sourceforge.plantuml.stats.StatsUtilsIncrement;
import net.sourceforge.plantuml.style.ClockwiseTopRightBottomLeft;
import net.sourceforge.plantuml.ugraphic.ImageBuilder;
import net.sourceforge.plantuml.ugraphic.color.NoSuchColorException;
import net.sourceforge.plantuml.version.License;
import net.sourceforge.plantuml.version.Version;

public abstract class AbstractPSystem implements Diagram {

	private final UmlSource source;
	private Scale scale;
	private int splitPagesHorizontal = 1;
	private int splitPagesVertical = 1;

	public AbstractPSystem(UmlSource source) {
		this.source = Objects.requireNonNull(source);
	}

	private String getVersion() {
		final StringBuilder toAppend = new StringBuilder();
		toAppend.append("PlantUML version ");
		toAppend.append(Version.versionString());
		toAppend.append("(" + Version.compileTimeString() + ")\n");
		toAppend.append("(" + License.getCurrent() + " source distribution)\n");
		for (String name : OptionPrint.interestingProperties()) {
			toAppend.append(name);
			toAppend.append(BackSlash.CHAR_NEWLINE);
		}
		return toAppend.toString();
	}

	final public String getMetadata() {
		if (source == null) {
			return getVersion();
		}
		final String rawString = source.getRawString();
		final String plainString = source.getPlainString();
		if (rawString != null && rawString.equals(plainString)) {
			return rawString + BackSlash.NEWLINE + getVersion();
		}
		return rawString + BackSlash.NEWLINE + plainString + BackSlash.NEWLINE + getVersion();
	}

	final public UmlSource getSource() {
		return source;
	}

	final public long seed() {
		if (source == null) {
			return 42;
		}
		return getSource().seed();
	}

	public int getNbImages() {
		return 1;
	}

	@Override
	public int getSplitPagesHorizontal() {
		return splitPagesHorizontal;
	}

	public void setSplitPagesHorizontal(int splitPagesHorizontal) {
		this.splitPagesHorizontal = splitPagesHorizontal;
	}

	@Override
	public int getSplitPagesVertical() {
		return splitPagesVertical;
	}

	public void setSplitPagesVertical(int splitPagesVertical) {
		this.splitPagesVertical = splitPagesVertical;
	}

	public DisplayPositionned getTitle() {
		if (source == null) {
			return DisplayPositioned.single(Display.empty(), HorizontalAlignment.CENTER, VerticalAlignment.TOP);
		}
		return DisplayPositioned.single(source.getTitle(), HorizontalAlignment.CENTER, VerticalAlignment.TOP);
	}

	public String getWarningOrError() {
		return null;
	}

	public String checkFinalError() {
		return null;
	}

	public void makeDiagramReady() {
	}

	public boolean isOk() {
		return true;
	}

	public CommandExecutionResult executeCommand(Command cmd, BlocLines lines) {
		cmd = new ProtectedCommand(cmd);
		try {
			return cmd.execute(this, lines);
		} catch (NoSuchColorException e) {
			return CommandExecutionResult.badColor();
		}
	}

	public boolean hasUrl() {
		return false;
	}

	final public ImageData exportDiagram(OutputStream os, int index, FileFormatOption fileFormatOption)
			throws IOException {
		final long now = System.currentTimeMillis();
		try {
//			if (this instanceof TitledDiagram) {
//				final TitledDiagram titledDiagram = (TitledDiagram) this;
//				final StyleBuilder styleBuilder = titledDiagram.getCurrentStyleBuilder();
//				if (styleBuilder != null) {
//					styleBuilder.printMe();
//				}
//			}
			return exportDiagramNow(os, index, fileFormatOption);
		} finally {
			if (OptionFlags.getInstance().isEnableStats()) {
				StatsUtilsIncrement.onceMoreGenerate(System.currentTimeMillis() - now, getClass(),
						fileFormatOption.getFileFormat());
			}
		}
	}

	final public void setScale(Scale scale) {
		this.scale = scale;
	}

	final public Scale getScale() {
		return scale;
	}

	public ImageBuilder createImageBuilder(FileFormatOption fileFormatOption) throws IOException {
		return imageBuilder(fileFormatOption);
	}

	// TODO "index" isnt really being used
	protected abstract ImageData exportDiagramNow(OutputStream os, int index, FileFormatOption fileFormatOption)
			throws IOException;

	public ClockwiseTopRightBottomLeft getDefaultMargins() {
		return ClockwiseTopRightBottomLeft.same(0);
	}
	
	@Override
	public Display getTitleDisplay() {
		return null;
	}

}
