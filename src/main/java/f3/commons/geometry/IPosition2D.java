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

/**
 * @author n3k0nation
 *
 */
public interface IPosition2D {
	
	int getX();
	int getY();
	
	IPosition2D setX(int x);
	IPosition2D setY(int y);
	IPosition2D setXY(int x, int y);
	
	default IPosition2D set(IPosition2D position) {
		return setXY(position.getX(), position.getY());
	}
	
	default IPosition2D add(IPosition2D position) {
		return setXY(position.getX() + getX(), position.getY() + getY());
	}
	
	default IPosition2D sub(IPosition2D position) {
		return setXY(getX() - position.getX(), getY() - position.getY());
	}
	
	default Vector2 toVector2() {
		return new Vector2(getX(), getY());
	}
}
