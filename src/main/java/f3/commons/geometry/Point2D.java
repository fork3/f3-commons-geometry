/*
 * Copyright (c) 2010-2017 fork3
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE 
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR 
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package f3.commons.geometry;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author n3k0nation
 *
 */
@EqualsAndHashCode
@ToString
public class Point2D implements IPosition2D {
	
	private int x, y;
	
	public Point2D() {
	}
	
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point2D(IPosition2D position) {
		this(position.getX(), position.getY());
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public Point2D setX(int x) {
		this.x = x;
		return this;
	}

	@Override
	public Point2D setY(int y) {
		this.y = y;
		return this;
	}

	@Override
	public Point2D setXY(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

}
