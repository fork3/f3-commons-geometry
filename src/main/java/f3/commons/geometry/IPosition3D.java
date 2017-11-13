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
public interface IPosition3D extends IPosition2D {
	
	int getZ();
	
	
	@Override
	IPosition3D setX(int x);
	
	@Override
	IPosition3D setY(int y);
	
	IPosition3D setZ(int z);
	
	@Override
	IPosition3D setXY(int x, int y);
	
	IPosition3D setXYZ(int x, int y, int z);
	
	@Override
	default IPosition3D set(IPosition2D position) {
		return setXY(position.getX(), position.getY());
	}
	
	@Override
	default IPosition3D add(IPosition2D position) {
		return setXY(position.getX() + getX(), position.getY() + getY());
	}
	
	@Override
	default IPosition3D sub(IPosition2D position) {
		return setXY(getX() - position.getX(), getY() - position.getY());
	}
	
	default IPosition3D set(IPosition3D position) {
		return setXYZ(position.getX(), position.getY(), position.getZ());
	}
	
	default IPosition3D add(IPosition3D position) {
		return setXYZ(position.getX() + getX(), position.getY() + getY(), position.getZ() + getZ());
	}
	
	default IPosition3D sub(IPosition3D position) {
		return setXYZ(getX() - position.getX(), getY() - position.getY(), getZ() - position.getZ());
	}
	
	default Vector3 toVector3() {
		return new Vector3(getX(), getY(), getZ());
	}
}
