package sciapi.api.def.matrix;

import java.util.*;

import sciapi.api.abstraction.numerics.IReal;
import sciapi.api.basis.matrix.*;
import sciapi.api.basis.temporaries.DTempManager;
import sciapi.api.basis.temporaries.TempUtil;
import sciapi.api.def.euclidian.EVector;
import sciapi.api.def.numerics.DReal;

public class DMatrix implements ITransformMatrix<DMatrix, EVector, IReal> {
	
	private int nr, nc;
	
	private IReal value[][];
	
	private static List<List<TempUtil>> temps = new ArrayList();
	
	/**
	 * Creates a Matrix as Zero Matrix.
	 * */
	public DMatrix(int pnr, int pnc){
		pnr = nr;
		pnc = nc;
		
		value = new DReal[nr][nc];
		for(IReal[] vr : value)
			for(IReal v : vr)
				v = new DReal();
	}


	@Override
	public boolean isZero() {
		for(IReal[] vr : value)
			for(IReal v : vr)
				if(!v.isZero())
					return false;
		return true;
	}

	@Override
	public DMatrix mult(IReal sc) {
		DMatrix dm = new DMatrix(nr, nc);
		
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				dm.value[i][j].setmult(value[i][j], sc);
			}
		
		return dm;
	}

	@Override
	public DMatrix div(IReal sc) {
		DMatrix dm = new DMatrix(nr, nc);
		
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				dm.value[i][j].setdiv(value[i][j], sc);
			}
		
		return dm;
	}

	@Override
	public DMatrix setmult(DMatrix a, IReal sc) {		
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				value[i][j].setmult(a.value[i][j], sc);
			}
		
		return this;
	}

	@Override
	public DMatrix setdiv(DMatrix a, IReal sc) {
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				value[i][j].setdiv(a.value[i][j], sc);
			}
		
		return this;
	}

	@Override
	public DMatrix add(DMatrix a) {		
		checkMatrixSame(this, a, "add");
		
		DMatrix dm = new DMatrix(nr, nc);
		
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				dm.value[i][j].setadd(value[i][j], a.value[i][j]);
			}

		return dm;
	}

	@Override
	public DMatrix sub(DMatrix a) {
		checkMatrixSame(this, a, "sub");
		
		DMatrix dm = new DMatrix(nr, nc);
		
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				dm.value[i][j].setsub(value[i][j], a.value[i][j]);
			}

		return dm;
	}

	@Override
	public DMatrix getZero() {
		return new DMatrix(nr, nc);
	}

	@Override
	public DMatrix addinv() {
		DMatrix dm = new DMatrix(nr, nc);
		
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				dm.value[i][j].setaddinv(value[i][j]);
			}

		return dm;
	}

	@Override
	public DMatrix setadd(DMatrix a, DMatrix b) {
		checkMatrixSame(a, b, "add");
		checkMatrixSame(this, a, "add");
				
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				value[i][j].setadd(a.value[i][j], b.value[i][j]);
			}

		return this;
	}

	@Override
	public DMatrix setsub(DMatrix a, DMatrix b) {
		checkMatrixSame(a, b, "add");
		checkMatrixSame(this, a, "add");
				
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				value[i][j].setadd(a.value[i][j], b.value[i][j]);
			}

		return this;
	}

	@Override
	public DMatrix setZero() {
		for(IReal[] vr : value)
			for(IReal v : vr)
				v.setZero();
		
		return this;
	}

	@Override
	public DMatrix setaddinv(DMatrix a) {
		checkMatrixSame(this, a, "setaddinv");
				
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				value[i][j].setaddinv(a.value[i][j]);
			}

		return this;
	}

	@Override
	public IReal getElement(int i, int j) {
		return value[i][j];
	}

	@Override
	public DMatrix Transpose() {
		DMatrix dm = new DMatrix(nc, nr);
		
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				value[j][i].set(this.value[i][j]);
			}
		
		return dm;
	}

	@Override
	public DMatrix setasTranspose(DMatrix mat) {
		checkMatrixTrable(this, mat, "setaddinv");
		
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				value[j][i].set(mat.value[i][j]);
			}
		
		return this;
	}

	@Override
	public DMatrix mult(DMatrix a) {
		checkMatrixMultable(this, a, "mult");
		
		DMatrix dm = new DMatrix(nr, a.nc);
		DReal temp = DTempManager.getDReal();
		
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < a.nc; j++){
				for(int k = 0; k < nc; k++){
					dm.value[i][j].setadd(dm.value[i][j], temp.setmult(value[i][k], a.value[k][j]));
				}
			}
		
		DTempManager.release(temp);
		
		return dm;
	}

	@Override
	public DMatrix setmult(DMatrix a, DMatrix b) {
		checkMatrixMultable(a, b, "setmult");
		checkMatrixMulted(this, a, b, "setmult");
		
		DReal temp = DTempManager.getDReal();
		
		for(int i = 0; i < a.nr; i++)
			for(int j = 0; j < b.nc; j++){
				value[i][j].setZero();
			
				for(int k = 0; k < a.nc; k++){
					value[i][j].setadd(value[i][j], temp.setmult(a.value[i][k], b.value[k][j]));
				}
			}
		
		DTempManager.release(temp);

		return this;
	}

	@Override
	public DReal Determinant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DReal setasDeterminant(IReal a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public EVector getTransformed(EVector vec) {
		this.checkTransformable(this, vec, "DMatrix#getTransformed");
		
		EVector tvec = new EVector(nr);
		DReal temp = DTempManager.getDReal();
		
		for(int i = 0; i < nr; i++){
			for(int k = 0; k < nc; k++){
				tvec.getCoord(i).setadd(tvec.getCoord(i), temp.setmult(value[i][k], vec.getCoord(k)));
			}
		}
		
		DTempManager.release(temp);
		
		return tvec;
	}


	@Override
	public EVector setasTransformed(EVector set, EVector vec) {
		this.checkTransformable(this, vec, "DMatrix#setasTransformed");
		this.checkTransformable(this, set, "DMatrix#setasTransformed");
		
		DReal temp = DTempManager.getDReal();
		
		for(int i = 0; i < nr; i++){
			set.getCoord(i).setZero();
			for(int k = 0; k < nc; k++){
				set.getCoord(i).setadd(set.getCoord(i), temp.setmult(value[i][k], vec.getCoord(k)));
			}
		}
		
		DTempManager.release(temp);
		
		return set;
	}
	

	@Override
	public int getRowNum() {
		return nr;
	}

	@Override
	public int getColumnNum() {
		return nc;
	}
	

	@Override
	public DMatrix getNew() {
		return new DMatrix(nr, nc);
	}


	@Override
	public DMatrix set(DMatrix par) {
		checkMatrixSame(this, par, "set");
		for(int i = 0; i < nr; i++)
			for(int j = 0; j < nc; j++){
				value[i][j].set(par.value[i][j]);
			}
		
		return this;
	}
	
	
	@Override
	public DMatrix getTemporary() {
		List<TempUtil> tl;
		
		if(this.getRowNum() > temps.size()){
			for(int i = temps.size() + 1; i <= this.getRowNum(); i++){
				tl = new ArrayList();
				for(int j = 1; j <= this.getColumnNum(); j++){
					tl.add(new TempUtil(new DMatrix(i, j)));
				}
				temps.add(tl);
			}
		}
		
		tl = temps.get(this.getRowNum() - 1);
		
		if(this.getColumnNum() > tl.size()){
			for(int j = temps.size() + 1; j <= this.getColumnNum(); j++){
				tl.add(new TempUtil(new DMatrix(this.getRowNum(), j)));
			}
		}
		
		return (DMatrix) tl.get(getColumnNum()-1).getTemp();
	}


	@Override
	public void remove(DMatrix tval) {
		if(tval.getRowNum() > temps.size())
			return;
		
		List<TempUtil> tl = temps.get(this.getRowNum() - 1);
		
		if(tval.getColumnNum() > tl.size())
			return;
		
		tl.get(tval.getColumnNum()-1).release(tval);
	}
	
	
	private static void checkMatrixSame(DMatrix p1, DMatrix p2, String proc){
		if(p1.nr == p2.nr && p1.nc == p2.nc)
			return;
		throw new MatrixSizeException(p1, p2, proc);
	}
	
	private static void checkMatrixTrable(DMatrix p1, DMatrix p2, String proc){
		if(p1.nr == p2.nc && p1.nc == p2.nr)
			return;
		throw new MatrixSizeException(p1, p2, proc);
	}
	
	private static void checkMatrixMultable(DMatrix p1, DMatrix p2, String proc){
		if(p1.nc == p2.nr)
			return;
		throw new MatrixSizeException(p1, p2, proc);
	}
	
	private static void checkMatrixMulted(DMatrix obj, DMatrix p1, DMatrix p2, String proc){
		if(obj.nr == p1.nr && obj.nc == p2.nc)
			return;
		throw new MatrixSizeException(p1, p2, proc);
	}
	
	private static void checkTransformable(DMatrix p, EVector v, String proc){
		if(p.nc == v.getDimension())
			return;
		throw new MatrixSizeException(p, v, proc);
	}
}
