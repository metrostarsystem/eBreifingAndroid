/*
Copyright (C) 2017 MetroStar Systems

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

The full license text can be found is the included LICENSE file.

You can freely use any of this software which you make publicly
available at no charge.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

package com.metrostarsystems.ebriefing.BookPage.Annotations.Tools;

public class AnnotationHighlighter extends AbstractAnnotationTool {
	
	private AnnotationHighlighter() {
		super();
	}
	
	private AnnotationHighlighter(Builder build) {
		super(build);
	}
	
	public static class Builder extends AbstractAnnotationTool.Builder {
		
		public Builder(int id) {
			super(id);
		}
		
		public AnnotationHighlighter build() {
			return new AnnotationHighlighter(this);
		}
	}

	public static AnnotationHighlighter HIGHLIGHTER() { return (AnnotationHighlighter) new AnnotationHighlighter.Builder(AbstractAnnotationTool.HIGHLIGHTER).width(70).build(); }
}
